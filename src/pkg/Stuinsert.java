package pkg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Stuinsert {
	JFrame f = new JFrame("学生信息录入");
	JTextField sno = new JTextField(20);
	JTextField sname = new JTextField(20);
	JTextField ssex = new JTextField(20);
	JTextField sage = new JTextField(20);
	JTextField sdept = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("录入完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public Stuinsert() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("学生学号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(sno);
		JLabel u2 = new JLabel("学生姓名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(sname);
		JLabel u3 = new JLabel("学生性别：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(ssex);
		JLabel u4 = new JLabel("学生年龄：", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(sage);
		JLabel u5 = new JLabel("学生系别：", SwingConstants.LEFT);
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
				newinsert();
			}
			if (e.getSource() == fanhui) {
				f.dispose();
			}
		}
	}

	public void newinsert() {
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
		if (age.equals(""))
			age = "0";
		dept = String.valueOf(sdept.getText());
		//String query = "select * from student";
		String sql = "insert into student (sno,sname,ssex,sage,sdept) values(" + "'" + numb + "','" + name + "','" + sex
				+ "'," + age + ",'" + dept + "')";
		if (numb == "" || name == "" || sex == "" || dept == "" || age == "0") {
			JOptionPane.showMessageDialog(null, "请输入信息");
			;
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				Statement st = conn.createStatement();
				//st.executeQuery(query);
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "录入完成");
				st.close();
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "录入失败");
			} catch (java.lang.Exception ex) {
				ex.printStackTrace();
			}
			f.dispose();
		}
	}

	class WindowHandler extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}
}
