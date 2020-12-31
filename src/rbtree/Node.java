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
enum COLOR
{
    RED,
    BLACK
}
public class Node {
    private Integer key;
   private Node parent;
   private Node right;
   private Node left;
   private COLOR color;
   public Node() {
       key= null;
       color=COLOR.BLACK;
   }
   public Node(Integer key) {
        this.key = key;
        right = left = parent= null;
        color = COLOR.RED;
    }

    public Integer getKey() {
        return key;
    }
    public void setKey(Integer key) {
        this.key = key;
    }
    public Node getParent() {
        return parent;
    }
    public void setParent(Node parent) {
        this.parent = parent;
    }
    public Node getRight() {
        return right;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Node getLeft() {
        return left;
    }
    public void setLeft(Node left) {
        this.left = left;
    }
    public COLOR getColor() {
        return color;
    }
    public void setColor(COLOR color) {
        this.color = color;
    }
    public String toString() {
		return "" + key;
	}
}
