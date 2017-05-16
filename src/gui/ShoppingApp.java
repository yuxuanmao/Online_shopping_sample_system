package gui;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import project.Category;
import project.Database;
import project.Graph;
import project.Inventory;
import project.Product;

public class ShoppingApp {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Database database = new Database();
		Category c = new Category("Anime","hi");
		Category m = new Category("Manga","hooo");
		Product p1 = new Product("a",25.34,"The legend of the legendary hero","product1.jpg",5, c);
		Product p2 = new Product("b",32.34,"Madomagi Homura is Hentai","product2.jpg",8, c);
		Product p3 = new Product("c",10.34,"This is a ninja","product3.jpg",34, m);
		Product p4 = new Product("d",11.12,"This is a ninja","product4.jpg",12, m);
		Product p5 = new Product("e",90.02,"She is the family leader","product5.jpg",86, m);
		Product p6 = new Product("f",80.84,"The legendary knight","product6.jpg",91, m);
		Product p7 = new Product("g",45.45,"amazing","product7.jpg",31, c);
		Product p8 = new Product("h",100.12,"Robot Girls","product8.jpg",45, c);
		Product p9 = new Product("k",231.64,"She is the family leader","product9.jpg",56, c);
		Product p10 = new Product("l",130.34,"She is the family leader","product10.jpg",45, c);
		Product p11  = new Product("m",985.89,"She is the family leader","product11.jpg",13, c);
		
		Inventory inv = new Inventory("123");
		inv.add(p1);
		inv.add(p2);
		inv.add(p3);
		inv.add(p4);
		inv.add(p5);
		inv.add(p6);
		inv.add(p7);
		inv.add(p8);
		inv.add(p9);
		inv.add(p10);
		inv.add(p11);
		inv.addCategory(1, c);
		inv.addCategory(2, m);
		final Store s = new Store(database);
		s.setSize(600, 400);
		s.setVisible(true);
		s.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		s.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				if (!s.hasLoggedOut()) {
					if (JOptionPane.showConfirmDialog(s,
							"You may lose unsaved work! Are you sure?",
							"Really Closing?", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
						System.exit(0);
					}
				} else
					System.exit(0);
			}
		});
	}

}
