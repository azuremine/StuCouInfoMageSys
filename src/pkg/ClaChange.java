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

public class ClaChange {
	JFrame f = new JFrame("选课信息修改");
	JTextField sno = new JTextField(20);
	JTextField cno = new JTextField(20);
	JTextField tno = new JTextField(20);
	JTextField grade = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("修改完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public ClaChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("要修改的学生学号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(sno);
		JLabel u2 = new JLabel("请输入课程号：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(cno);
		JLabel u3 = new JLabel("请输入教师号：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(tno);
		JLabel u4 = new JLabel("请输入成绩：", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(grade);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		sno.addActionListener(new TextHandler(1));
		cno.addActionListener(new TextHandler(2));
		tno.addActionListener(new TextHandler(3));
		grade.addActionListener(new TextHandler(5));
		finish.addActionListener(new TextHandler(4));
		fanhui.addActionListener(new TextHandler(6));
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
		String numb1;
		String numb2;
		String numb3;
		String ggrade;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb1 = String.valueOf(sno.getText());
		numb2 = String.valueOf(cno.getText());
		numb3 = String.valueOf(tno.getText());
		ggrade = String.valueOf(grade.getText());
		if (ggrade.equals("")) {
			ggrade = "0";
		}
		int a;
		if (numb1 == "" || numb2 == "" || numb3 == "" || ggrade == "0") {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update sct set cno = ? ,tno = ? ,grade = ? where sno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, numb2);
				pstm.setString(2, numb3);
				pstm.setString(3, ggrade);
				pstm.setString(4, numb1);
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
