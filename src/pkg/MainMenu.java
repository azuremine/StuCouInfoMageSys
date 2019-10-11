package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenu extends JFrame implements ActionListener {
	JButton check = new JButton("信     息     查     询");
	JButton insert = new JButton("信     息     录     入");
	JButton delete = new JButton("信     息     删     除");
	JButton change = new JButton("信     息     修     改");
	JButton view = new JButton("信     息     浏     览");
	JButton help = new JButton("帮     助     说     明");

	public MainMenu() {
		super("学生选课信息管理系统");
		setVisible(true);
		setSize(250, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(check);
		add(insert);
		add(delete);
		add(change);
		add(view);
		add(help);
		check.addActionListener(this);
		insert.addActionListener(this);
		delete.addActionListener(this);
		change.addActionListener(this);
		view.addActionListener(this);
		help.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == check) {
			new Check();
		}
		if (e.getSource() == insert) {
			new Insert();
		}
		if (e.getSource() == delete) {
			new Delete();
		}
		if (e.getSource() == change) {
			new Change();
		}
		if (e.getSource() == view) {
			new View();
		}
		if (e.getSource() == help) {
			new Help();
		}

	}

	public static void main(String args[]) {
		new MainMenu();
	}

}
