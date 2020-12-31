/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rbtree;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Jena
 */
public class TreeGUI extends JPanel{
    private RBTree tree = new RBTree();
	private Panel treePanel = new Panel(tree);

	public TreeGUI() {
		treePanel.setBackground(new Color(210, 180, 222));
		super.setLayout(new BorderLayout());
		setScrollPane();
		setBottomPanel();
	}

	private void setBottomPanel() {
		final JTextField insertText = new JTextField(10);
                final JTextField searchText = new JTextField(10);
                final JTextField deleteText = new JTextField(10);
		final JButton insert = new JButton();
		try {
			Image icon = ImageIO.read(getClass().getResource("insert" + ".png"));
			insert.setIcon(new ImageIcon(icon));
			insert.setBorderPainted(false);
                        insert.setSize(10,10);
			insert.setFocusPainted(false);
			insert.setContentAreaFilled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final JButton delete = new JButton();
		try {
			Image icon = ImageIO.read(getClass().getResource("delete" + ".png"));
			delete.setIcon(new ImageIcon(icon));
			delete.setBorderPainted(false);
                        delete.setSize(10,10);
			delete.setFocusPainted(false);
			delete.setContentAreaFilled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final JButton clear = new JButton();
		try {
			Image icon = ImageIO.read(getClass().getResource("clear" + ".png"));
			clear.setIcon(new ImageIcon(icon));
			clear.setBorderPainted(false);
                        clear.setSize(10,10);
			clear.setFocusPainted(false);
			clear.setContentAreaFilled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		final JButton search = new JButton();
		try {
			Image icon = ImageIO.read(getClass().getResource("search" + ".png"));
			search.setIcon(new ImageIcon(icon));
			search.setBorderPainted(false);
                        search.setSize(10,10);
			search.setFocusPainted(false);
			search.setContentAreaFilled(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JPanel panel = new JPanel();
                panel.add(insert);
		panel.add(insertText);
		panel.add(delete);
                panel.add(deleteText);
		panel.add(search);
                panel.add(searchText);
		panel.add(clear);
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.SOUTH);

		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (insertText.getText().equals(""))
					return;

				Integer key = Integer.parseInt(insertText.getText());
					tree.insert(key);
					treePanel.repaint();
					insertText.requestFocus();
					insertText.selectAll();
				

			}

		});
                delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (deleteText.getText().equals(""))
					return;

				Integer key = Integer.parseInt(deleteText.getText());
					tree.delete(key);
					treePanel.repaint();
					deleteText.requestFocus();
					deleteText.selectAll();
				

			}

		});
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				if (tree.getRoot() == null)
					JOptionPane
							.showMessageDialog(null, "Tree is already empty");
				else
					tree.clear();

				treePanel.setVariable(0);
				treePanel.repaint();
			}
		});

		search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {

				if (searchText.getText().equals(""))
					return;

				Integer key = Integer.parseInt(searchText.getText());
                                treePanel.setVariable(key);
                                treePanel.repaint();
                                searchText.requestFocus();
                                searchText.selectAll();
			}

		});

		searchText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionEvent) {
				insert.doClick();
			}

		});

	}

	private void setScrollPane() {
		treePanel.setPreferredSize(new Dimension(10000, 5000));

		JScrollPane scroll = new JScrollPane();
		scroll.setViewportView(treePanel);
		scroll.setPreferredSize(new Dimension(750, 500));
                scroll.getViewport().setViewPosition(new Point(4500, 0));
		add(scroll, BorderLayout.CENTER);
	}

}
