package project20280.tree;

/**
 * Q5: Pseudocode for finding the diameter (width) of a binary tree.
 *
 * The width is the number of nodes on the longest path between any two nodes.
 * This path may or may not pass through the root.
 *
 * PSEUDOCODE:
 *
 *   function width(tree):
 *       if tree is empty:
 *           return 0
 *       result = 0
 *       widthHelper(tree.root, result)
 *       return result
 *
 *   function widthHelper(node, result):
 *       if node is null:
 *           return 0
 *
 *       leftHeight = widthHelper(node.left, result)
 *       rightHeight = widthHelper(node.right, result)
 *
 *       // the path through this node has leftHeight + rightHeight + 1 nodes
 *       pathThroughNode = leftHeight + rightHeight + 1
 *
 *       // update the global maximum if this path is longer
 *       if pathThroughNode > result:
 *           result = pathThroughNode
 *
 *       // return the height of this subtree (used by the parent)
 *       return 1 + max(leftHeight, rightHeight)
 *
 *
 * Q6: Generate random binary trees and compute average height.
 *     For n = 50 to 5000 in steps of 50, generate 100 random trees
 *     of size n and compute the average height.
 *
 *     Also computes average width (from Q5 optional extension).
 */
public class TreeStatistics {

    public static void main(String[] args) {
        System.out.println("n,avgHeight,avgWidth");

        for (int n = 50; n <= 5000; n += 50) {
            double totalHeight = 0;
            double totalWidth = 0;

            for (int trial = 0; trial < 100; trial++) {
                LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(n);
                totalHeight += bt.height();
                totalWidth += bt.width();
            }

            double avgHeight = totalHeight / 100.0;
            double avgWidth = totalWidth / 100.0;
            System.out.printf("%d,%.2f,%.2f%n", n, avgHeight, avgWidth);
        }
    }
}
