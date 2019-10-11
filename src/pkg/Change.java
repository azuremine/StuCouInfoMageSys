package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Change extends JFrame implements ActionListener {
	JButton stuChange = new JButton("1.ѧ     ��     ��     Ϣ     ��     ��");
	JButton teaChange = new JButton("2.��     ʦ     ��     Ϣ     ��     ��");
	JButton couChange = new JButton("3.��     ��     ��     Ϣ     ��     ��");
	JButton colChange = new JButton("4.Ժ     ϵ     ��     Ϣ     ��     ��");
	JButton claChange = new JButton("5.ѡ     ��     ��     Ϣ     ��     ��");
	JButton breturn = new JButton("������һ��");

	public Change() {
		super("��Ϣ�޸�");
		setSize(250, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(stuChange);
		add(teaChange);
		add(couChange);
		add(colChange);
		add(claChange);
		add(breturn);
		stuChange.addActionListener(this);
		teaChange.addActionListener(this);
		couChange.addActionListener(this);
		colChange.addActionListener(this);
		claChange.addActionListener(this);
		breturn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stuChange) {
			new StuChange();
		}
		if (e.getSource() == teaChange) {
			new TeaChange();
		}
		if (e.getSource() == couChange) {
			new CouChange();
		}
		if (e.getSource() == colChange) {
			new DepChange();
		}
		if (e.getSource() == claChange) {
			new ClaChange();
		}
		if (e.getSource() == breturn) {
			dispose();
		}
	}
	
	public static void main(String[] args){
		new Change();
	}
}