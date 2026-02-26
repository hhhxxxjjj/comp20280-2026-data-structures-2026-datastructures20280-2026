package project20280.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LinkedBinaryTreeTestTrees2 {

    @Test
    void testRootToPaths() {
        Integer[] inorder = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        Integer[] preorder = {5, 1, 0, 4, 2, 3, 7, 6, 8};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);

        System.out.println(bt.toBinaryTreeString());

        assertEquals("[[5, 1, 0], [5, 1, 4, 2, 3], [5, 7, 6], [5, 7, 8]]", bt.rootToLeafPaths().toString());
    }

    @Test
    void testConstruct() {
        Integer[] inorder = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30};
        Integer[] preorder = {18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
                17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);

        System.out.println(bt.toBinaryTreeString());

        assertEquals(30, bt.size());
        assertEquals(18, bt.root().getElement());
    }

    @Test
    void testWidth() {
        Integer[] inorder = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
                19, 20, 21, 22};
        Integer[] preorder = {6, 5, 3, 2, 1, 0, 4, 17, 10, 9, 8, 7, 16, 14, 13, 12, 11, 15, 21,
                20, 19, 18, 22};
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.construct(inorder, preorder);

        System.out.println(bt.toBinaryTreeString());

        assertEquals(13, bt.width());
    }
}
