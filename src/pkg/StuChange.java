package pkg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class StuChange {
	JFrame f = new JFrame("学生信息修改");
	JTextField sno = new JTextField(20);
	JTextField sname = new JTextField(20);
	JTextField ssex = new JTextField(20);
	JTextField sage = new JTextField(20);
	JTextField sdept = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("修改完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public StuChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("要修改的学生学号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(sno);
		JLabel u2 = new JLabel("请输入姓名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(sname);
		JLabel u3 = new JLabel("请输入性别：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(ssex);
		JLabel u4 = new JLabel("请输入年龄：", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(sage);
		JLabel u5 = new JLabel("请输入系别：", SwingConstants.LEFT);
		jp.add(u5);
		jp.add(sdept);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		sno.addActionListener(new TextHandler(1));
		sname.addActionListener(new TextHandler(2));
		ssex.addActionListener(new TextHandler(3));
		sage.addActionListener(new TextHandler(4));
		sdept.addActionListener(new TextHandler(5));
		finish.addActionListener(new TextHandler(6));
		fanhui.addActionListener(new TextHandler(7));
		f.addWindowListener(new WindowHandler());
		f.setSize(450, 300);
		f.setResizable(true);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
	}

	class TextHandler implements ActionListener {
		int sel;

		TextHandler(int sel) {
			this.sel = sel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == finish) {
				newchange();
			}
			if (e.getSource() == fanhui) {
				f.dispose();
			}
		}
	}

	public void newchange() {
		String numb;
		String name;
		String sex;
		String age;
		String dept;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb = String.valueOf(sno.getText());
		name = String.valueOf(sname.getText());
		sex = String.valueOf(ssex.getText());
		age = String.valueOf(sage.getText());
		if (age.equals("")) {
			age = "0";
		}
		dept = String.valueOf(sdept.getText());
		int a;
		if (numb == "" || name == "" || sex == "" || age == "0" || dept == "") {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update student set sname = ? , ssex = ? , sage = ? , sdept =? where sno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, name);
				pstm.setString(2, sex);
				pstm.setString(3, age);
				pstm.setString(4, dept);
				pstm.setString(5, numb);
				a = pstm.executeUpdate();
				if (a > 0) {
					JOptionPane.showMessageDialog(null, "修改成功");
				} else {
					JOptionPane.showMessageDialog(null, "修改失败");
				}
				pstm.close();
				conn.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			} catch (java.lang.Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	class WindowHandler extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}
}
