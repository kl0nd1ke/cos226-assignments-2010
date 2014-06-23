/* KdTree.java
 * Name: Vladimir Costescu
 * Login: costescu
 * Precept: P02A
 * Description: Implements a 2d tree.
 * Dependencies: Point, RectHV, Stack, StdDraw
 */

public class KdTree {
    private Node root;
    private int size;

    private class Node {
        private Point p;
        private RectHV rect;
        // Subtrees (left/bottom and right/top)
        private Node lb;
        private Node rt;
    }

    // construct an empty KdTree
    public KdTree() {
        size = 0;
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the tree
    public int size() {
        return size;
    }

    // add the point p to the tree
    public void insert(Point p) {
        // First insertion case
        if (isEmpty()) {
            RectHV unitSquare = new RectHV(0, 0, 1, 1);
            root = new Node();
            root.p = p;
            root.rect = unitSquare;
        }
        // Recursive case (start at the root)
        else insert(p, root, 0);
        // Increment the size variable
        size++;
    }

    private void insert(Point p, Node n, int level) {
        // Do not insert duplicate points
        if (p.equals(n.p)) return;
        
        Node subtree;
        // True if x, false if y (true if even, false if odd)
        boolean isX = level % 2 == 0;
        // True if left, false if right
        boolean isLB;
        
        // Consider x coordinates
        if (isX) {
            if (p.x() < n.p.x()) { subtree = n.lb; isLB = true; } 
            else { subtree = n.rt; isLB = false; }
        }
        // Consider y coordinates
        else {
            if (p.y() < n.p.y()) { subtree = n.lb; isLB = true; } 
            else { subtree = n.rt; isLB = false; }
        }
        
        // Base case
        if (subtree == null) {
            subtree = new Node();
            RectHV rect;
            // x coordinate, left subtree
            if (isX && isLB) { rect = new RectHV(n.rect.xmin(), n.rect.ymin(), 
                    n.p.x(), n.rect.ymax()); n.lb = subtree; } 
            // x coordinate, right subtree
            else if (isX && !isLB) { rect = new RectHV(n.p.x(), n.rect.ymin(), 
                    n.rect.xmax(), n.rect.ymax()); n.rt = subtree; } 
            // y coordinate, left subtree (i.e. bottom)
            else if (!isX && isLB) { rect = new RectHV(n.rect.xmin(), 
                    n.rect.ymin(), n.rect.xmax(), n.p.y()); n.lb = subtree; } 
            // y coordinate, right subtree (i.e. top)
            else { rect = new RectHV(n.rect.xmin(), n.p.y(), n.rect.xmax(), 
                    n.rect.ymax()); n.rt = subtree; }
            // Initialize subtree node
            subtree.p = p;
            subtree.rect = rect;
        }
        // Recursive case
        else {
            insert(p, subtree, level + 1);
        }
    }

    // does the tree contain p?
    public boolean contains(Point p) {
        // If the tree is empty then it cannot contain p
        if (isEmpty()) return false;
        // Recursive case (start at the root)
        else return contains(p, root, 0);
    }
    
    private boolean contains(Point p, Node n, int level) {
        // Base case 1 - not found
        if (n == null) return false;
        // Base case 2 - found
        else if (p.equals(n.p)) return true;
        // Recursive case
        else {
            // True if x, false if y (true if even, false if odd)
            boolean isX = level % 2 == 0;
            // x nodes
            if (isX) {
                if (p.x() < n.p.x()) return contains(p, n.lb, level + 1);
                else return contains(p, n.rt, level + 1);
            }
            // y nodes
            else {
                if (p.y() < n.p.y()) return contains(p, n.lb, level + 1);
                else return contains(p, n.rt, level + 1);
            }
        }
    }

    // draw all of the points to standard draw
    public void draw() {
        draw(root, 0);
    }
    
    private void draw(Node n, int level) {
        // Draw line
        StdDraw.setPenRadius();
        boolean isX = level % 2 == 0;
        if (isX) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.BLUE);
        n.rect.draw();
        // Draw point
        StdDraw.setPenColor();
        StdDraw.setPenRadius(.01);
        n.p.draw();
        // Recursively draw left subtree
        if (n.lb != null) draw(n.lb, level + 1);
        // Recursively draw right subtree
        if (n.rt != null) draw(n.rt, level + 1);
    }

    // points in the tree that are in the rectangle
    public Iterable<Point> range(RectHV rect) {
        if (isEmpty()) return null;
        else {
            Stack<Point> rectPoints = new Stack<Point>();
            // Method modifies rectPoints
            range(rect, rectPoints, root);
            return rectPoints;
        }
    }
    
    private void range(RectHV rect, Stack<Point> rectPoints, Node n) {
        // Add point to stack if inside rect
        if (rect.contains(n.p)) rectPoints.push(n.p);
        // Examine left subtree if rectangles intersect
        if (n.lb != null && rect.intersects(n.lb.rect)) 
            range(rect, rectPoints, n.lb);
        // Examine right subtree if rectangles intersect
        if (n.rt != null && rect.intersects(n.rt.rect)) 
            range(rect, rectPoints, n.rt);
    }

    // nearest neighbor in the tree to p (null if set is empty)
    public Point nearest(Point p) {
        if (isEmpty()) return null;
        else return nearest(p, root, 0, root).p;
    }
    
    private Node nearest(Point p, Node n, int level, Node closest) {
        // Base case - leaf node hit
        if (n.lb == null && n.rt == null) {
            return n;
        }
        // Recursive case
        else {
            Node subtree;
            Node other;
            Node candidate;
            double closestDist = closest.p.distanceTo(p);
            double candDist;
            
            // Determine in which subtree the query point would be inserted
            boolean isX = level % 2 == 0;
            if (isX) {
                if (p.x() < n.p.x()) { subtree = n.lb; other = n.rt; }
                else { subtree = n.rt; other = n.lb; }
            }
            else {
                if (p.y() < n.p.y()) { subtree = n.lb; other = n.rt; }
                else { subtree = n.rt; other = n.lb; }
            }
            
            // Consider the subtree in which the query point would be inserted
            if (subtree != null && closestDist > subtree.rect.distanceTo(p)) {
                candidate = nearest(p, subtree, level + 1, closest);
                candDist = candidate.p.distanceTo(p);
                if (candDist < closestDist) {
                    closest = candidate;
                    closestDist = candDist;
                }
            }
            // Consider the other subtree
            if (other != null && closestDist > other.rect.distanceTo(p)) {
                candidate = nearest(p, other, level + 1, closest);
                candDist = candidate.p.distanceTo(p);
                if (candDist < closestDist) {
                    closest = candidate;
                    closestDist = candDist;
                }
            }
            // Consider the point in the node
            candidate = n;
            candDist = candidate.p.distanceTo(p);
            if (candDist < closestDist) {
                closest = candidate;
                closestDist = candDist;
            }
            return closest;
        }  
    }
}
