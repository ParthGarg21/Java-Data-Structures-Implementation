/*
AVL Tree is a Self Balancing Binary contains Tree
The following is the implementation of AVL Tree
Its objects can be declared as :
   AVL bst = new AVL();

   This Class gives you the following functionalities:

   1) Adding data into the BST with SELF BALANCING FEATURE.
   ==> add(int data) method

   2) Removing data form the BST with SELF BALANCING FEATURE.
   ==> remove(int data) method
   
   3) Displaying the BST in PREORDER Format.
   ==> display() method
   
   4) Getting the HEIGHT of the BST.
   ==> height() method
   
   5) Getting the SIZE of the BST.
   ==> size() method

   6) containsING for a DATA in the BST.
   ==> contains() method
   
   7) Checking if the BST is EMPTY or NOT.
   ==> isEmpty() method

   8) Also, you can also DIRECTLY System.out.println(bst) to get the PREORDER TRAVERSAL in String Format.

*/

public class AVL {
    // PRIVATE MEMBERS ====================================

    private class Node {
        private int data = 0;
        private int height = 0;
        private int balance = 0;
        private Node left = null;
        private Node right = null;

        public Node(int data) {
            this.data = data;
        }
    }

    private int maxValue(Node node) {
        while (node.right != null) {
            node = node.right;
        }

        return node.data;
    }

    private void update(Node node) {
        int lh = (node.left == null) ? -1 : node.left.height;
        int rh = (node.right == null) ? -1 : node.right.height;

        node.height = Math.max(lh, rh) + 1;
        node.balance = lh - rh;
    }

    private Node leftleftRotation(Node node) {
        Node x = node;
        Node y = x.left;
        Node yRight = y.right;

        y.right = x;
        x.left = yRight;

        update(x);
        update(y);

        return y;
    }

    private Node rightrightRotation(Node node) {
        Node x = node;
        Node y = x.right;
        Node yLeft = y.left;

        y.left = x;
        x.right = yLeft;

        update(x);
        update(y);

        return y;
    }

    private Node add(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data >= root.data) {
            root.right = add(root.right, data);
        } else {
            root.left = add(root.left, data);
        }

        update(root);

        if (root.balance == 2) {
            if (root.left.balance == -1) {
                root.left = rightrightRotation(root.left);
            }
            root = leftleftRotation(root);
        } else if (root.balance == -2) {
            if (root.right.balance == 1) {
                root.right = leftleftRotation(root.right);
            }

            root = rightrightRotation(root);
        }

        return root;
    }

    private Node remove(Node root, int data) {
        if (root == null) {
            return null;
        }

        if (root.data == data) {
            if (root.left == null && root.right == null) {
                return null;
            }

            if (root.left == null) {
                return root.right;
            }

            if (root.right == null) {
                return root.left;
            }

            root.data = maxValue(root.left);

            root.left = remove(root.left, root.data);
            update(root);

            if (root.balance == 2) {
                if (root.left.balance == -1) {
                    root.left = rightrightRotation(root.left);
                }
                root = leftleftRotation(root);
            } else if (root.balance == -2) {
                if (root.left.balance == 1) {
                    root.right = leftleftRotation(root.right);
                }

                root = rightrightRotation(root);
            }

            return root;
        }

        if (data > root.data) {
            root.right = remove(root.right, data);
        } else {
            root.left = remove(root.left, data);
        }

        update(root);

        if (root.balance == 2) {
            if (root.left.balance == -1) {
                root.left = rightrightRotation(root.left);
            }
            root = leftleftRotation(root);
        } else if (root.balance == -2) {
            if (root.left.balance == 1) {
                root.right = leftleftRotation(root.right);
            }

            root = rightrightRotation(root);
        }
        return root;
    }

    private void display(Node root) {
        if (root == null) {
            return;
        }

        System.out.print((root.left == null ? "." : root.left.data) + " -> ");
        System.out.print(root.data);
        System.out.println(" <- " + (root.right == null ? "." : root.right.data));

        display(root.left);
        display(root.right);
    }

    private int height(Node root) {
        if (root == null) {
            return -1;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }

    private int size(Node root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + size(root.right) + 1;
    }

    private boolean contains(Node node, int data) {
        while (node != null) {
            if (node.data == data) {
                return true;
            }

            if (data > node.data) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        return false;
    }

    private Node root = null;

    private void preOrder(Node node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.data + ", ");
        preOrder(node.left, sb);
        preOrder(node.right, sb);

    }

    // PUBLIC MEMBERS ====================================

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        preOrder(root, sb);
        sb.deleteCharAt(sb.length() - 1);
        sb.deleteCharAt(sb.length() - 1);
        sb.append("]");
        return sb.toString();
    }

    public void add(int data) {
        root = add(root, data);
    }

    public void remove(int data) {
        root = remove(root, data);
    }

    public void display() {
        display(root);
    }

    public int size() {
        return size(root);
    }

    public int height() {
        return height(root);
    }

    public boolean contains(int data) {
        return contains(root, data);
    }

    public boolean isEmpty() {
        return root == null;
    }

}