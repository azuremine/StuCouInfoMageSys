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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ColDelete {
	JFrame f = new JFrame("院系信息删除");
	JTextField dno = new JTextField(20);
	JPanel jp = new JPanel(new GridLayout(1, 1, 10, 10));
	JButton delete = new JButton("删除");
	JButton breturn = new JButton("返回上一层");

	public ColDelete() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("请输入院系号", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(dno);
		jp.add(delete);
		jp.add(breturn);
		delete.setBounds(50, 30, 65, 20);
		dno.addActionListener(new TextHandler(1));
		f.addWindowListener(new WindowHandler());
		delete.addActionListener(new TextHandler(2));
		breturn.addActionListener(new TextHandler(3));
		f.setSize(450, 250);
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
			if (e.getSource() == delete) {
				delete();
				f.dispose();
			}
			if (e.getSource() == breturn) {
				f.dispose();
			}
		}
	}

	public void delete() {
		String num;
		num = String.valueOf(dno.getText());
		int a;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
			String sqldelete = "DELETE FROM department WHERE dno = ?";
			PreparedStatement pstm = conn.prepareStatement(sqldelete);
			pstm.setString(1, num);
			a = pstm.executeUpdate();
			if (a > 0) {
				JOptionPane.showMessageDialog(null, "删除成功");
			} else {
				JOptionPane.showMessageDialog(null, "删除失败");
			}
			pstm.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class WindowHandler extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}
}
