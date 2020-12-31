/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

/**
 *
 * @author Jena
 */
public class RBTree {
    protected Node root;
    protected Node NULL = null;


    public int getLevel() {
        return this.getlevel(this.root);
    }

    void inorder(Node p) {
        if (p != NULL) {
            inorder(p.getLeft());
            System.out.print(p.getKey() + " ");
            inorder(p.getRight());
        }
    }

    void printInorder() {
        inorder(root);
        System.out.println("");
    }

    private int getlevel(Node node) {
        if (node != null) {
            int right_depth;
            int left_depth = this.getlevel(node.getLeft());
            return left_depth > (right_depth = this.getlevel(node.getRight())) ? left_depth + 1 : right_depth + 1;
        }
        return 0;
    }

    public Node getRoot() {
        return root;
    }

    private Node leftRotation(Node x) {
        Node z = x.getRight();
        x.setRight(z.getLeft());
        if (z.getLeft() != null)
            z.getLeft().setParent(x);
        z.setLeft(x);
        z.setParent(x.getParent());
        if (x.getParent() != null) {
            if (x == x.getParent().getLeft())
                x.getParent().setLeft(z);
            else
                x.getParent().setRight(z);
        } else
            root = z;
        x.setParent(z);
        return z;
    }

    private Node rightRotation(Node x) //the same leftRotation (swap left & right)
    {
        Node z = x.getLeft();
        x.setLeft(z.getRight());
        if (z.getRight() != null)
            z.getRight().setParent(x);
        z.setRight(x);
        z.setParent(x.getParent());
        if (x.getParent() != null) {
            if (x == x.getParent().getRight())
                x.getParent().setRight(z);
            else
                x.getParent().setLeft(z);
        } else
            root = z;
        x.setParent(z);
        return z;
    }

    private void printHelper(Node root, String space, boolean last) {
        // print the tree structure on the screen
        if (root != null) {
            System.out.print(space);
            if (last) {
                System.out.print("R----");
                space += "     ";
            } else {
                System.out.print("L----");
                space += "|    ";
            }

            String sColor = root.getColor() == COLOR.BLACK ? "BLACK" : "RED";
            System.out.println(root.getKey() + "(" + sColor + ")");
            printHelper(root.getLeft(), space, false);
            printHelper(root.getRight(), space, true);
        }
    }

    private void clear(Node p) {
        if (p != null) {
            clear(p.getLeft());
            clear(p.getRight());
            System.gc();
            p = null;
        }
    }

    public void clear() {
        Node n = null;
        root = n;
    }

    public void insert(Integer item) {
        Node x = new Node(item);
        x.setLeft(NULL);
        x.setRight(NULL);
        if (root == null) {
            root = x;
            root.setLeft(NULL);
            root.setRight(NULL);
            root.setColor(COLOR.BLACK); //set root color=black
            return;
        }
        Node temp = root;
        while (!(item < temp.getKey() && temp.getLeft() == null) && !(item >= temp.getKey() && temp.getRight() == null)) {
            if (item < temp.getKey())
                temp = temp.getLeft();
            else
                temp = temp.getRight();
        }
        if (item < temp.getKey())
            temp.setLeft(x);
        else
            temp.setRight(x);

        x.setParent(temp);

        while (x != root && x.getParent().getColor() == COLOR.RED) {
            if (x.getParent() == x.getParent().getParent().getLeft())  // if parent is left child
            {
                Node uncle = x.getParent().getParent().getRight();
                if (uncle != null && uncle.getColor() == COLOR.RED)   //case 1 uncle is red
                {
                    x.getParent().setColor(COLOR.BLACK);
                    uncle.setColor(COLOR.BLACK);
                    x.getParent().getParent().setColor(COLOR.RED);
                    x = x.getParent().getParent();

                } else    // case uncle is black
                {
                    if (x.getParent().getRight() == x) //case 2
                    {
                        x = x.getParent();
                        leftRotation(x);
                    }
                    x.getParent().setColor(COLOR.BLACK);
                    x.getParent().getParent().setColor(COLOR.RED);
                    rightRotation(x.getParent().getParent());

                }
            } else    // if parent is right child
            {
                Node uncle = x.getParent().getParent().getLeft();
                if (uncle != null && uncle.getColor() == COLOR.RED)   //case 1 uncle is red
                {
                    x.getParent().setColor(COLOR.BLACK);
                    uncle.setColor(COLOR.BLACK);
                    x.getParent().getParent().setColor(COLOR.RED);
                    x = x.getParent().getParent();

                } else    // case uncle is black
                {
                    if (x.getParent().getLeft() == x) //case 2
                    {
                        x = x.getParent();
                        rightRotation(x);
                    }
                    x.getParent().setColor(COLOR.BLACK);
                    x.getParent().getParent().setColor(COLOR.RED);
                    leftRotation(x.getParent().getParent());

                }
            }
        }
        root.setColor(COLOR.BLACK); //root color =black
    }

    public boolean search(Integer item) {
        Node temp = root;
        while (temp != null) {
            if (temp.getKey() == item) {
                System.out.println("Found item");
                return true;
            } else if (temp.getKey() > item) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        System.out.println("Not found item");
        return false;
    }

    public Node search2(Integer item) {
        Node temp = root;
        while (temp != null) {
            if (temp.getKey() == item) {
                return temp;
            } else if (temp.getKey() > item) {
                temp = temp.getLeft();
            } else {
                temp = temp.getRight();
            }
        }
        return null;
    }


    public void print() {
        printHelper(this.root, "", true);
    }

    public Node successor(Node x) {
        Node node = x;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void delete(Integer key) {
        Node z = search2(key);
        if (z == null)
            return;
        Node x, y;
        if (z.getLeft() == null || z.getRight() == null)
            y = z;
        else {
            y = successor(z.getRight());
            z.setKey(y.getKey());
        }

        if (y.getRight() != null)
            x = y.getRight();
        else
            x = y.getLeft();
        if (x == null) {
            x = new Node();
            x.setColor(COLOR.BLACK);
        }
        x.setParent(y.getParent());
        if (y.getParent() == null)
            root = x;
        else

            if (y == y.getParent().getLeft()) // left child
                y.getParent().setLeft(x);
            else
                y.getParent().setRight(x);

        if (y.getColor() == COLOR.BLACK)
               rbTreeFix(root,x);

        if (x.getKey() == null) // x is a pretend node
            if (x==x.getParent().getLeft())
                x.getParent().setLeft(null);
            else
                x.getParent().setRight(null);


    }

    private Node rbTreeFix(Node root, Node x) {
        Node y;
        while (x != root && x.getColor() == COLOR.BLACK) // not case (1)
            if (x == x.getParent().getLeft()) { // x is left child
                y = x.getParent().getRight(); // y is x’s sibling
                if (y.getColor() == COLOR.RED) { // case (2)
                    y.setColor(COLOR.BLACK);
                    x.getParent().setColor(COLOR.RED); // p was black
                     leftRotation(x.getParent());
                    y = x.getParent().getRight(); // new sibling
                }
                if(x.getParent().getColor()==COLOR.RED)
                {
                    x.getParent().setColor(COLOR.BLACK);
                    leftRotation(x.getParent());
                    break;

                }
                else if (y.getLeft().getColor() == COLOR.BLACK && y.getRight().getColor() == COLOR.BLACK) {
                    // nephews are black - case (3)
                    y.setColor(COLOR.RED);
                    x = x.getParent();
                } else { // case (4)
                    if (y.getRight().getColor() == COLOR.BLACK) {
                        // subcase (i)
                        y.getLeft().setColor(COLOR.BLACK);
                        y.setColor(COLOR.RED);
                        rightRotation(y);
                        y = x.getParent().getRight();
                    }
                    // subcase (ii)
                    y.setColor(x.getParent().getColor());
                    x.getParent().setColor(COLOR.BLACK);
                    y.getRight().setColor(COLOR.BLACK);
                    leftRotation( x.getParent());
                    x = root; // we can finish

                }
            } else {// x is right child - symmetric
                y = x.getParent().getLeft(); // y is x’s sibling
                if (y.getColor() == COLOR.RED) { // case (2)
                    y.setColor(COLOR.BLACK);
                    x.getParent().setColor(COLOR.RED); // p was black
                    rightRotation(x.getParent());
                    y = x.getParent().getLeft(); // new sibling
                }
                if (y.getRight().getColor() == COLOR.BLACK && y.getLeft().getColor() == COLOR.BLACK) {
                    // nephews are black - case (3)
                    y.setColor(COLOR.RED);
                    x = x.getParent();
                } else { // case (4)
                    if (y.getLeft().getColor() == COLOR.BLACK) {
                        // subcase (i)
                        y.getRight().setColor(COLOR.BLACK);
                        y.setColor(COLOR.RED);
                        leftRotation(y);
                        y = x.getParent().getLeft();
                    }
                    // subcase (ii)
                    y.setColor(x.getParent().getColor());
                    x.getParent().setColor(COLOR.BLACK);
                    y.getLeft().setColor(COLOR.BLACK);
                    rightRotation( x.getParent());
                    x = root; // we can finish

                }

            }
        // end while loop
        x.setColor(COLOR.BLACK);
        return root;

    }
}