package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Insert extends JFrame implements ActionListener {
	JButton stuinsert = new JButton("1.ѧ     ��     ��     Ϣ     ¼     ��");
	JButton teainsert = new JButton("2.��     ʦ     ��     Ϣ     ¼     ��");
	JButton couinsert = new JButton("3.��     ��     ��     Ϣ     ¼     ��");
	JButton colinsert = new JButton("4.Ժ     ϵ     ��     Ϣ     ¼     ��");
	JButton clainsert = new JButton("5.ѡ     ��     ��     Ϣ     ¼     ��");
	JButton breturn = new JButton("������һ��");

	public Insert() {
		super("��Ϣ¼��");
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
