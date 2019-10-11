package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Help extends JFrame implements ActionListener {
	JTextArea help = new JTextArea("本系统模拟学生选课的部分管理功能.\n" + "学生入校注册后需统一记录学生个人基本信息，\n" + "对于面向学生开设的相关课程需要记录每门课程的基本信息，\n"
			+ "每个任课教师规定其可主讲三门课程，\n" + "学生选课时系统将相应的选课信息记录入库，\n" + "考试结束后需在相应的选课记录中补上考试成绩。\n", 2, 20);

	JButton breturn = new JButton("返回上一层");

	public Help() {
		super("帮助信息");
		setSize(300, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(help);
		help.setEditable(false);
		add(breturn);
		breturn.addActionListener(this);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == breturn) {
			dispose();
		}
	}

	public static void main(String args[]) {
		new Help();
	}
}