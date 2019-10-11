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

public class CouChange {
	JFrame f = new JFrame("课程信息修改");
	JTextField cno = new JTextField(20);
	JTextField cname = new JTextField(20);
	JTextField cpno = new JTextField(20);
	JTextField ccredit = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("修改完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public CouChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("要修改的课程号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(cno);
		JLabel u2 = new JLabel("请输入课程名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(cname);
		JLabel u3 = new JLabel("请输入先行课编号：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(cpno);
		JLabel u4 = new JLabel("请输入学分：", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(ccredit);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		cno.addActionListener(new TextHandler(1));
		cname.addActionListener(new TextHandler(2));
		cpno.addActionListener(new TextHandler(3));
		ccredit.addActionListener(new TextHandler(4));
		finish.addActionListener(new TextHandler(5));
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
		String numb;
		String name;
		String pno;
		String credit;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb = String.valueOf(cno.getText());
		name = String.valueOf(cname.getText());
		pno = String.valueOf(cpno.getText());
		credit = String.valueOf(ccredit.getText());
		if (credit.equals("")) {
			credit = "0";
		}
		int a;
		if (numb == "" || name == "" || pno == "" || credit == "0") {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update course set cname = ? , cpno = ? , ccredit = ?  where cno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, name);
				pstm.setString(2, pno);
				pstm.setString(3, credit);
				pstm.setString(4, numb);
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
