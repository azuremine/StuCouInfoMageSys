package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Insert extends JFrame implements ActionListener {
	JButton stuinsert = new JButton("1.学     生     信     息     录     入");
	JButton teainsert = new JButton("2.教     师     信     息     录     入");
	JButton couinsert = new JButton("3.课     程     信     息     录     入");
	JButton colinsert = new JButton("4.院     系     信     息     录     入");
	JButton clainsert = new JButton("5.选     课     信     息     录     入");
	JButton breturn = new JButton("返回上一层");

	public Insert() {
		super("信息录入");
		setSize(250, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(stuinsert);
		add(teainsert);
		add(couinsert);
		add(colinsert);
		add(clainsert);
		add(breturn);
		stuinsert.addActionListener(this);
		teainsert.addActionListener(this);
		couinsert.addActionListener(this);
		colinsert.addActionListener(this);
		clainsert.addActionListener(this);
		breturn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stuinsert) {
			new Stuinsert();
			dispose();
		}
		if (e.getSource() == teainsert) {
			new Teainsert();
			dispose();
		}
		if (e.getSource() == couinsert) {
			new Couinsert();
			dispose();
		}
		if (e.getSource() == colinsert) {
			new Colinsert();
			dispose();
		}
		if (e.getSource() == clainsert) {
			new Calinsert();
			dispose();
		}
		if (e.getSource() == breturn) {
			dispose();
		}
	}
	
	public static void main(String[] args){
		new Insert();
	}
}
