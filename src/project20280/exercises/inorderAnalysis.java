package project20280.exercises;

import project20280.tree.LinkedBinaryTree;

public class inorderAnalysis {

    public static void main(String[] args) {
        System.out.println("n,time_ns");

        for (int n = 10; n <= 10000; n += 100) {
            LinkedBinaryTree<Integer> bt = LinkedBinaryTree.makeRandom(n);

            long start = System.nanoTime();
            bt.inorder();
            long elapsed = System.nanoTime() - start;

            System.out.println(n + "," + elapsed);
        }
    }
}