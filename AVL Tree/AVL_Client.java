public class AVL_Client {
    public static void main(String[] args) {
        AVL bst = new AVL();

        bst.add(10);
        bst.add(20);
        bst.add(5);
        bst.add(15);
        bst.add(30);
        bst.add(25);
        bst.add(35);
        bst.add(1);
        bst.add(12);
        bst.add(8);

        bst.display();

        bst.remove(12);
        bst.remove(25);
        bst.remove(10);
        bst.remove(15);

        bst.display();

        System.out.println(bst.size());
        System.out.println(bst.height());
        System.out.println(bst.contains(100));
        System.out.println(bst);
        // bst.add(40);
        // bst.display();
    }
}
