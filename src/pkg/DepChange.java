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

public class DepChange {
	JFrame f = new JFrame("院系信息修改");
	JTextField dno = new JTextField(20);
	JTextField dname = new JTextField(20);
	JTextField dmanager = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("修改完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public DepChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("要修改的院系号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(dno);
		JLabel u2 = new JLabel("请输入院系名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(dname);
		JLabel u3 = new JLabel("请输入院主任：", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(dmanager);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		dno.addActionListener(new TextHandler(1));
		dname.addActionListener(new TextHandler(2));
		dmanager.addActionListener(new TextHandler(3));
		finish.addActionListener(new TextHandler(4));
		fanhui.addActionListener(new TextHandler(5));
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
		String manager;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb = String.valueOf(dno.getText());
		name = String.valueOf(dname.getText());
		manager = String.valueOf(dmanager.getText());
		int a;
		if (numb.equals("") || name.equals("") || manager.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update department set dname = ? , dmanager = ? where dno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, name);
				pstm.setString(2, manager);
				pstm.setString(3, numb);
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
