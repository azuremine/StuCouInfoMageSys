package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Check extends JFrame implements ActionListener {
	JButton stucheck = new JButton("1.学     生     信     息     查     询");
	JButton teacheck = new JButton("2.教     师     信     息     查     询");
	JButton coucheck = new JButton("3.课     程     信     息     查     询");
	JButton colcheck = new JButton("4.院     系     信     息     查     询");
	JButton clacheck = new JButton("5.选     课     信     息     查     询");
	JButton breturn = new JButton("返回上一层");

	public Check() {
		super("信息查询");
		setSize(250, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(stucheck);
		add(teacheck);
		add(coucheck);
		add(colcheck);
		add(clacheck);
		add(breturn);
		stucheck.addActionListener(this);
		teacheck.addActionListener(this);
		coucheck.addActionListener(this);
		colcheck.addActionListener(this);
		clacheck.addActionListener(this);
		breturn.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stucheck) {
			new StuCheck();
		}
		if (e.getSource() == teacheck) {
			new TeaCheck();
		}
		if (e.getSource() == coucheck) {
			new CouCheck();
		}
		if (e.getSource() == colcheck) {
			new ColCheck();
		}
		if (e.getSource() == clacheck) {
			new CalCheck();
		}
		if (e.getSource() == breturn) {
			dispose();
		}
	}
	
	public static void main(String[] args){
		new Check();
	}
}
