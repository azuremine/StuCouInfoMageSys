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

public class Colinsert {
	JFrame f = new JFrame("院系信息录入");
	JTextField dno = new JTextField(20);
	JTextField dname = new JTextField(20);
	JTextField dmanager = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("录入完成");
	JButton fanhui = new JButton("返回");
	JPanel jp = new JPanel(new GridLayout(5, 2, 10, 10));

	public Colinsert() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("系编号：", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(dno);
		JLabel u2 = new JLabel("系名：", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(dname);
		JLabel u3 = new JLabel("系主任：", SwingConstants.LEFT);
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
		String manager;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=学生选课管理信息系统";
		numb = String.valueOf(dno.getText());
		name = String.valueOf(dname.getText());
		manager = String.valueOf(dmanager.getText());
		//String query = "select * from department";
		String sql = "insert into department (dno,dname,dmanager) values(" + "'" + numb + "','" + name + "','" + manager
				+ "')";
		if (numb.equals("") || name.equals("") || manager.equals("")) {
			JOptionPane.showMessageDialog(null, "请输入完整信息");
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
