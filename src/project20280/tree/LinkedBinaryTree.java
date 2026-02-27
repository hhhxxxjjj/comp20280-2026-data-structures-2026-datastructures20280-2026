package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor

    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String [] args) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();

        Integer[] arr = new Integer[]{1,
                2, 3,
                4, 5, 6, 7,
                8, 9, 10, 11, 12, 13, 14, 15,
                16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31,
                null, null, null, 35};
        bt.createLevelOrder(arr);
        System.out.println(bt.toBinaryTreeString());

        System.out.println("Height: " + bt.height());

        int[] callCount = new int[]{0};
        int h = bt.heightWithCount(bt.root(), callCount);
        System.out.println("Height (with count): " + h);
        System.out.println("Number of recursive calls: " + callCount[0]);

        System.out.println("Diameter: " + bt.diameter());

        System.out.println("External nodes: " + bt.countExternal());
        System.out.println("Left external nodes: " + bt.countLeftExternal());

        System.out.println("Descendants of root: " + bt.countDescendants(bt.root()));

        System.out.println("\n--- Q4 Preorder EXAMFUN ---");
        LinkedBinaryTree<String> preTree = new LinkedBinaryTree<>();
        preTree.buildPreorderEXAMFUN();
        System.out.println(preTree.toBinaryTreeString());
        System.out.println("Preorder: ");
        for (Position<String> p : preTree.preorder()) {
            System.out.print(p.getElement());
        }
        System.out.println();

        System.out.println("\n--- Q4 Inorder EXAMFUN ---");
        LinkedBinaryTree<String> inTree = new LinkedBinaryTree<>();
        inTree.buildInorderEXAMFUN();
        System.out.println(inTree.toBinaryTreeString());
        System.out.print("Inorder: ");
        for (Position<String> p : inTree.inorder()) {
            System.out.print(p.getElement());
        }
        System.out.println();

        System.out.println("\n--- Q4 Postorder EXAMFUN ---");
        LinkedBinaryTree<String> postTree = new LinkedBinaryTree<>();
        postTree.buildPostorderEXAMFUN();
        System.out.println(postTree.toBinaryTreeString());
        System.out.print("Postorder: ");
        for (Position<String> p : postTree.postorder()) {
            System.out.print(p.getElement());
        }
        System.out.println();

        // --- Q3: construct from inorder + preorder ---
        System.out.println("\n--- Q3: construct from inorder + preorder ---");
        Integer[] inorder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer[] preorder = {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
                17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
        LinkedBinaryTree<Integer> bt3 = new LinkedBinaryTree<>();
        bt3.construct(inorder, preorder);
        System.out.println(bt3.toBinaryTreeString());

        // --- Q4: root to leaf paths ---
        System.out.println("\n--- Q4: root to leaf paths ---");
        Integer[] inorder4 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] preorder4 = {5, 1, 0, 4, 2, 3, 7, 6, 8};
        LinkedBinaryTree<Integer> bt4 = new LinkedBinaryTree<>();
        bt4.construct(inorder4, preorder4);
        System.out.println(bt4.toBinaryTreeString());
        System.out.println(bt4.rootToLeafPaths());

        // --- Q5: width (diameter in nodes) ---
        System.out.println("\n--- Q5: width ---");
        Integer[] inorder5 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22};
        Integer[] preorder5 = {6, 5, 3, 2, 1, 0, 4, 17, 10, 9, 8, 7, 16, 14, 13, 12, 11, 15, 21,
                20, 19, 18, 22};
        LinkedBinaryTree<Integer> bt5 = new LinkedBinaryTree<>();
        bt5.construct(inorder5, preorder5);
        System.out.println(bt5.toBinaryTreeString());
        System.out.println("Width: " + bt5.width());

        // --- Q9: print leaf nodes ---
        System.out.println("\n--- Q9: Leaf Nodes ---");
        LinkedBinaryTree<String> q9tree = new LinkedBinaryTree<>();
        Position<String> a = q9tree.addRoot("A");
        Position<String> b = q9tree.addLeft(a, "B");
        Position<String> c = q9tree.addRight(a, "C");
        Position<String> d = q9tree.addLeft(b, "D");
        Position<String> e = q9tree.addRight(b, "E");
        Position<String> f = q9tree.addRight(c, "F");
        Position<String> g = q9tree.addLeft(e, "G");
        Position<String> hh = q9tree.addRight(e, "H");
        System.out.println(q9tree.toBinaryTreeString());
        System.out.println("Leaves: " + q9tree.getLeaves());
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return ((Node<E>) p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        if (isEmpty()) {
            addRoot(e);
        } else {
            addRecursive(root, e);
        }
    }

    // recursively add Nodes to binary tree in proper position
    @SuppressWarnings("unchecked")
    private Node<E> addRecursive(Node<E> p, E e) {
        if (p == null) {
            return createNode(e, null, null, null);
        }
        Comparable<E> comp = (Comparable<E>) e;
        if (comp.compareTo(p.getElement()) < 0) {
            if (p.getLeft() == null) {
                Node<E> newNode = createNode(e, p, null, null);
                p.setLeft(newNode);
                size++;
                return newNode;
            } else {
                return addRecursive(p.getLeft(), e);
            }
        } else {
            if (p.getRight() == null) {
                Node<E> newNode = createNode(e, p, null, null);
                p.setRight(newNode);
                size++;
                return newNode;
            } else {
                return addRecursive(p.getRight(), e);
            }
        }
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E old = node.getElement();
        node.setElement(e);
        return old;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(p)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent());
        if (node == root)
            root = child;
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // defunct
        return temp;
    }

    public String toString() {
        return positions().toString();
    }

    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        if (i < l.size() && l.get(i) != null) {
            Node<E> n = createNode(l.get(i), p, null, null);
            size++;
            n.setLeft(createLevelOrderHelper(l, n, 2 * i + 1));
            n.setRight(createLevelOrderHelper(l, n, 2 * i + 2));
            return n;
        }
        return null;
    }

    public void createLevelOrder(E[] arr) {
        root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        if (i < arr.length && arr[i] != null) {
            Node<E> n = createNode(arr[i], p, null, null);
            size++;
            n.setLeft(createLevelOrderHelper(arr, n, 2 * i + 1));
            n.setRight(createLevelOrderHelper(arr, n, 2 * i + 2));
            return n;
        }
        return null;
    }

    // Q3: construct binary tree from inorder and preorder traversal
    public void construct(E[] inorder, E[] preorder) {
        size = 0;
        root = constructHelper(inorder, 0, inorder.length - 1,
                preorder, 0, preorder.length - 1, null);
    }

    private Node<E> constructHelper(E[] inorder, int inStart, int inEnd,
                                    E[] preorder, int preStart, int preEnd,
                                    Node<E> parent) {
        if (inStart > inEnd || preStart > preEnd) {
            return null;
        }

        E rootVal = preorder[preStart];
        Node<E> node = createNode(rootVal, parent, null, null);
        size++;

        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i].equals(rootVal)) {
                rootIndex = i;
                break;
            }
        }

        int leftSize = rootIndex - inStart;

        node.setLeft(constructHelper(inorder, inStart, rootIndex - 1,
                preorder, preStart + 1, preStart + leftSize, node));
        node.setRight(constructHelper(inorder, rootIndex + 1, inEnd,
                preorder, preStart + leftSize + 1, preEnd, node));

        return node;
    }

    // Q4: find all root-to-leaf paths
    public ArrayList<ArrayList<E>> rootToLeafPaths() {
        ArrayList<ArrayList<E>> result = new ArrayList<>();
        ArrayList<E> currentPath = new ArrayList<>();
        rootToLeafPathsHelper(root, currentPath, result);
        return result;
    }

    private void rootToLeafPathsHelper(Position<E> p, ArrayList<E> currentPath,
                                       ArrayList<ArrayList<E>> result) {
        if (p == null) {
            return;
        }

        currentPath.add(p.getElement());

        if (isExternal(p)) {
            result.add(new ArrayList<>(currentPath));
        } else {
            rootToLeafPathsHelper(left(p), currentPath, result);
            rootToLeafPathsHelper(right(p), currentPath, result);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    // Q5: width of the binary tree (number of nodes on longest path between any two nodes)
    public int width() {
        if (isEmpty()) return 0;
        int[] result = new int[]{0};
        widthHelper(root(), result);
        return result[0];
    }

    private int widthHelper(Position<E> p, int[] result) {
        if (p == null) return 0;
        int leftHeight = widthHelper(left(p), result);
        int rightHeight = widthHelper(right(p), result);
        int pathThroughNode = leftHeight + rightHeight + 1;
        if (pathThroughNode > result[0]) {
            result[0] = pathThroughNode;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    // Q1(h): height with recursive call counter
    public int heightWithCount(Position<E> p, int[] callCount) {
        callCount[0]++;
        if (p == null) {
            return -1;
        }
        int leftHeight = heightWithCount(left(p), callCount);
        int rightHeight = heightWithCount(right(p), callCount);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Q1(i): diameter of the binary tree
    public int diameter() {
        if (isEmpty()) return 0;
        int[] result = new int[]{0};
        diameterHelper(root(), result);
        return result[0];
    }

    private int diameterHelper(Position<E> p, int[] result) {
        if (p == null) return -1;
        int leftHeight = diameterHelper(left(p), result);
        int rightHeight = diameterHelper(right(p), result);
        int pathThroughNode = leftHeight + rightHeight + 2;
        if (pathThroughNode > result[0]) {
            result[0] = pathThroughNode;
        }
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Q2: count all external nodes
    public int countExternal() {
        return countExternalHelper(root());
    }

    private int countExternalHelper(Position<E> p) {
        if (p == null) return 0;
        if (isExternal(p)) return 1;
        return countExternalHelper(left(p)) + countExternalHelper(right(p));
    }

    // Q3: count only left external nodes
    public int countLeftExternal() {
        return countLeftExternalHelper(root(), false);
    }

    private int countLeftExternalHelper(Position<E> p, boolean isLeftChild) {
        if (p == null) return 0;
        if (isExternal(p)) {
            if (isLeftChild) return 1;
            else return 0;
        }
        return countLeftExternalHelper(left(p), true) + countLeftExternalHelper(right(p), false);
    }

    // Q5: count total number of descendants of a node
    public int countDescendants(Position<E> p) {
        if (p == null) return 0;
        int count = 0;
        for (Position<E> c : children(p)) {
            count += 1 + countDescendants(c);
        }
        return count;
    }

    // Q4: build trees for EXAMFUN traversals
    @SuppressWarnings("unchecked")
    public void buildPreorderEXAMFUN() {
        //        E
        //       / \
        //      X   F
        //     / \   \
        //    A   M   U
        //             \
        //              N
        Position<E> e = addRoot((E) "E");
        Position<E> x = addLeft(e, (E) "X");
        Position<E> f = addRight(e, (E) "F");
        addLeft(x, (E) "A");
        addRight(x, (E) "M");
        Position<E> u = addRight(f, (E) "U");
        addRight(u, (E) "N");
    }

    @SuppressWarnings("unchecked")
    public void buildInorderEXAMFUN() {
        //        M
        //       / \
        //      X   U
        //     / \  / \
        //    E  A F   N
        Position<E> m = addRoot((E) "M");
        Position<E> x = addLeft(m, (E) "X");
        Position<E> u = addRight(m, (E) "U");
        addLeft(x, (E) "E");
        addRight(x, (E) "A");
        addLeft(u, (E) "F");
        addRight(u, (E) "N");
    }

    @SuppressWarnings("unchecked")
    public void buildPostorderEXAMFUN() {
        //         N
        //        / \
        //       F   U
        //      / \
        //     X   M
        //    / \
        //   E   A
        Position<E> n = addRoot((E) "N");
        Position<E> f = addLeft(n, (E) "F");
        addRight(n, (E) "U");
        Position<E> x = addLeft(f, (E) "X");
        addRight(f, (E) "M");
        addLeft(x, (E) "E");
        addRight(x, (E) "A");
    }

    // Q9: Get all leaf nodes from left to right
    public java.util.List<E> getLeaves() {
        java.util.List<E> leaves = new java.util.ArrayList<>();
        getLeavesHelper(root, leaves);
        return leaves;
    }

    private void getLeavesHelper(Position<E> p, java.util.List<E> leaves) {
        if (p == null) {
            return;
        }
        if (isExternal(p)) {
            leaves.add(p.getElement());
            return;
        }
        getLeavesHelper(left(p), leaves);
        getLeavesHelper(right(p), leaves);
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}