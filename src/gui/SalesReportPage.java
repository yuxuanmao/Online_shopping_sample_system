package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import project.Database;
import project.Product;
import project.SalesReport;


public class SalesReportPage extends JFrame{
	private GridLayout gLayout;
	protected Database database;
	protected int sessionid;
	protected ArrayList<HashMap<Product, Integer>> salereport;
	protected ArrayList<String> cagname;
	JTextArea txtArea;
	public SalesReportPage(Database database, int id){
		super();
		this.database = database;
		this.sessionid = id;
		this.setLayout(new FlowLayout());
		txtArea = new JTextArea(20,20);
		add(txtArea);
		JScrollPane scrollPane = new JScrollPane(txtArea); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(250, 250));
		add(scrollPane);
		txtArea.setEditable(false);
		txtArea.setLineWrap(true);
		

		//SalesReport salesreport = new SalesReport();
		salereport = database.salesReport(sessionid);
		//System.out.println(salereport);
		cagname = database.cagName(sessionid);
		for(int i=0; i< salereport.size();i++){
			txtArea.append(cagname.get(i)+"\n");
			//System.out.print(cagname.get(i));
			ArrayList<Product> plist= new ArrayList<Product>(salereport.get(i).keySet()); 
			for(int j=0; j< plist.size();j++){
				Product p = plist.get(j);
				txtArea.append(p.toString()+"\n");
				//System.out.println(p.toString());
			}
		}
		//System.out.println("sales report check");
	}

}
