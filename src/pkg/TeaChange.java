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

public class TeaChange {
	JFrame f = new JFrame("教师信息修改");
	JTextField tno = new JTextField(20);
	JTextField tname = new JTextField(20);
	JTextField tsex = new JTextField(20);
	JTextField tage = new JTextField(20);
	JTextField teb = new JTextField(20);
	JTextField tpt = new JTextField(20);
	JTextField cno1 = new JTextField(20);
	JTextField cno2 = new JTextField(20);
	JTextField cno3 = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("修改完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public TeaChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("要修改的教工号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(tno);
		JLabel u2 = new JLabel("请输入姓名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(tname);
		JLabel u3 = new JLabel("请输入性别：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(tsex);
		JLabel u4 = new JLabel("请输入年龄：", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(tage);
		JLabel u5 = new JLabel("请输入学历：", SwingConstants.LEFT);
		jp.add(u5);
		jp.add(teb);
		JLabel u6 = new JLabel("请输入职称：", SwingConstants.LEFT);
		jp.add(u6);
		jp.add(tpt);
		JLabel u7 = new JLabel("请输入主讲课程1：", SwingConstants.LEFT);
		jp.add(u7);
		jp.add(cno1);
		JLabel u8 = new JLabel("请输入主讲课程2：", SwingConstants.LEFT);
		jp.add(u8);
		jp.add(cno2);
		JLabel u9 = new JLabel("请输入主讲课程3：", SwingConstants.LEFT);
		jp.add(u9);
		jp.add(cno3);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		tno.addActionListener(new TextHandler(1));
		tname.addActionListener(new TextHandler(2));
		tsex.addActionListener(new TextHandler(3));
		tage.addActionListener(new TextHandler(4));
		teb.addActionListener(new TextHandler(5));
		finish.addActionListener(new TextHandler(6));
		fanhui.addActionListener(new TextHandler(7));
		tpt.addActionListener(new TextHandler(5));
		cno1.addActionListener(new TextHandler(5));
		cno2.addActionListener(new TextHandler(5));
		cno3.addActionListener(new TextHandler(5));
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
		String tteb;
		String ttpt;
		String ccno1;
		String ccno2;
		String ccno3;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb = String.valueOf(tno.getText());
		name = String.valueOf(tname.getText());
		sex = String.valueOf(tsex.getText());
		age = String.valueOf(tage.getText());
		if (age.equals("")) {
			age = "0";
		}
		tteb = String.valueOf(teb.getText());
		ttpt = String.valueOf(tpt.getText());
		ccno1 = String.valueOf(cno1.getText());
		ccno2 = String.valueOf(cno2.getText());
		ccno3 = String.valueOf(cno3.getText());
		int a;
		if (numb == "" || name == "" || sex == "" || age == "0" || tteb == "" || ttpt == "" || ccno1 == ""
				|| ccno2 == "" || ccno3 == "") {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update teacher set tname = ? , tsex = ? , tage = ? , teb =?,tpt=?,cno1=?,cno2=?,cno3=? where tno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, name);
				pstm.setString(2, sex);
				pstm.setString(3, age);
				pstm.setString(4, tteb);
				pstm.setString(5, ttpt);
				pstm.setString(6, ccno1);
				pstm.setString(7, ccno2);
				pstm.setString(8, ccno3);
				pstm.setString(9, numb);
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
