package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Check extends JFrame implements ActionListener {
	JButton stucheck = new JButton("1.ѧ     ��     ��     Ϣ     ��     ѯ");
	JButton teacheck = new JButton("2.��     ʦ     ��     Ϣ     ��     ѯ");
	JButton coucheck = new JButton("3.��     ��     ��     Ϣ     ��     ѯ");
	JButton colcheck = new JButton("4.Ժ     ϵ     ��     Ϣ     ��     ѯ");
	JButton clacheck = new JButton("5.ѡ     ��     ��     Ϣ     ��     ѯ");
	JButton breturn = new JButton("������һ��");

	public Check() {
		super("��Ϣ��ѯ");
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
