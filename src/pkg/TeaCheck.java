package pkg;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class TeaCheck {
	JFrame f = new JFrame("查询教师信息");

	DefaultTableModel model;
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane(table);

	JTextField tno = new JTextField(20);
	JTextField tname = new JTextField(20);

	JPanel jp = new JPanel(new GridLayout(1, 1, 10, 10));
	JButton check = new JButton("查询");
	JButton breturn = new JButton("返回");
	JPanel jp1 = new JPanel();

	public TeaCheck() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		f.getContentPane().add("South", jp1);
		JLabel u1 = new JLabel("教工号", SwingConstants.LEFT);
		JLabel u2 = new JLabel("教师姓名", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(tno);
		jp.add(u2);
		jp.add(tname);
		jp.add(check);
		jp.add(breturn);
		f.add(jsp);
		check.setBounds(50, 30, 65, 20);
		tno.addActionListener(new TextHandler(1));
		f.addWindowListener(new WindowHandler());
		check.addActionListener(new TextHandler(2));
		breturn.addActionListener(new TextHandler(3));
		f.setSize(650, 500);
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
			if (e.getSource() == check) {
				table.setModel(check());
			}
			if (e.getSource() == breturn) {
				f.dispose();
			}
		}
	}

	public DefaultTableModel check() {
		String num = tno.getText();
		String name = tname.getText();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
			String sql = "select * from teacher where 1=1 ";
			if (!num.equals("") && num != null) {
				sql += "and tno = " + "'" + num + "'";
			}
			if (!name.equals("") && name != null) {
				sql += "and tname = " + "'" + name + "'";
			}
			Statement pstm = conn.createStatement();
			ResultSet sr = pstm.executeQuery(sql);
			String info[] = { "教工号", "姓名", "性别", "年龄", "学历", "职称", "主讲课程1", "主讲课程2", "主讲课程3" };
			String teainfo[] = { "tno", "tname", "tsex", "tage", "teb", "tpt", "cno1", "cno2", "cno3" };
			model = new DefaultTableModel(null, info);
			// Vector head = new Vector();
			// for(int i=0;i<info.length;i++){
			// head.add(info[i]);
			// }
			// model = new DefaultTableModel(null, head);
			// model.setRowCount(0);
			while (sr.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < info.length; i++)
					row.add(sr.getString(teainfo[i] + ""));
				model.addRow(row);

			}
			return model;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	class WindowHandler extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}
}
