/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.awt.Color;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.UIManager;

/**
 *
 * @author MGD
 */

public class RedBlack {
	public static void main(String[] args) {
        // TODO code application logic here
        try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		JFrame j = new JFrame();
		j.setTitle("RED BLACK TREE");
                try {
			j.setIconImage(ImageIO.read(TreeGUI.class.getResource("redblack.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(new TreeGUI());
		j.pack();
		j.setVisible(true);


	}
}
