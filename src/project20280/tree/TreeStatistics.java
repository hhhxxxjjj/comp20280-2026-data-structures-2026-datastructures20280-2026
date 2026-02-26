package project20280.tree;

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
