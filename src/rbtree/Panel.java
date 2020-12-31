/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.awt.Color;
import static java.awt.Color.black;
import static java.awt.Color.red;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

import rbtree.Node;
import rbtree.RBTree;
import static java.awt.Color.white;


public class Panel extends JPanel{
   

	private RBTree tree;
	private int radius = 25;//nsf alqtr
	private int zwaya = 50;//alzawya
        int search;
        
        public Panel (RBTree tree)
        {
            this.tree = tree;
        }
        
        public void setVariable(int variable) {
		search = variable;
	}
        @Override
	protected void paintComponent(Graphics graphics) {
		super.paintComponent(graphics);
		if (tree.getRoot()== null)
			return;

		Graphics2D graphics2d = (Graphics2D) graphics;
		graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                //mkan al zhor el node
		paint(graphics2d, (Node) tree.getRoot(), getWidth() / 2, 30,getSpace());

	}
        // graphics   root of tree    width     
        private void paint(Graphics2D graph, Node root, int x, int y, int space) {
		if (x < 0)
                    setPreferredSize(new Dimension(2 * getWidth(), getHeight()));
		if (root.getKey() == search)
                {
                    radius += 5;
                    graph.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
                    radius -= 5;
                }
                //create node
		Color c;    
                if(root.getColor() == COLOR.BLACK)
                    c = black;
                else
                    c = red;
                graph.setColor(c);
                graph.fillOval(x - radius, y - radius, 2 * radius, 2 * radius);
                //color of of key
                graph.setColor(new Color(255,255,255));
                String key = root.toString();
                FontMetrics fm = graph.getFontMetrics();
		double t_width = fm.getStringBounds(key, graph).getWidth();
		graph.drawString(key, (int) (x - t_width + 3), (int) (y + fm.getMaxAscent() / 2));
                //color of line and search
                graph.setColor(Color.BLUE);
                // end creat
		if (root.getLeft() != null) {
			equation(graph, x - space, y + zwaya, x, y);
			paint(graph, (Node) root.getLeft(), x - space, y + zwaya,space / 2);
		}

		if (root.getRight() != null) {
			equation(graph, x + space, y + zwaya, x, y);
			paint(graph, (Node) root.getRight(), x + space, y + zwaya,space / 2);
		}
	}
        
        //equation to get x and y of line start and end
	private void equation(Graphics2D g, int x1, int y1, int x2, int y2) {
		double hypot = Math.hypot(zwaya, x2 - x1);
		int x11 = (int) (x1 + radius * (x2 - x1) / hypot);
		int y11 = (int) (y1 - radius * zwaya / hypot);
		int x21 = (int) (x2 - radius * (x2 - x1) / hypot);
		int y21 = (int) (y2 + radius * zwaya / hypot);
		g.drawLine(x11, y11, x21, y21);
	}
        private int getSpace() {
            int depth = tree.getLevel();
            float exponent = (float) 1.5;
            return (int) Math.pow(depth, exponent) * 50;
	}
}
