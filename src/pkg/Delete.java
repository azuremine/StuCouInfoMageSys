package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Delete extends JFrame implements ActionListener {
	JButton stuDelete = new JButton("1.ѧ     ��     ��     Ϣ     ɾ     ��");
	JButton teaDelete = new JButton("2.��     ʦ     ��     Ϣ     ɾ     ��");
	JButton couDelete = new JButton("3.��     ��     ��     Ϣ     ɾ     ��");
	JButton colDelete = new JButton("4.Ժ     ϵ     ��     Ϣ     ɾ     ��");
	JButton claDelete = new JButton("5.ѡ     ��     ��     Ϣ     ɾ     ��");
	JButton breturn = new JButton("������һ��");

	public Delete() {
		super("��Ϣɾ��");
		setSize(250, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(stuDelete);
		add(teaDelete);
		add(couDelete);
		add(colDelete);
		add(claDelete);
		add(breturn);
		stuDelete.addActionListener(this);
		teaDelete.addActionListener(this);
		couDelete.addActionListener(this);
		colDelete.addActionListener(this);
		claDelete.addActionListener(this);
		breturn.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stuDelete) {
			new StuDelete();
		}
		if (e.getSource() == teaDelete) {
			new TeaDelete();
		}
		if (e.getSource() == couDelete) {
			new CouDelete();
		}
		if (e.getSource() == colDelete) {
			new ColDelete();
		}
		if (e.getSource() == claDelete) {
			new ClaDelete();
		}
		if (e.getSource() == breturn) {
			dispose();
		}
	}
}