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

public class ColCheck {
	JFrame f = new JFrame("查询院系信息");
	DefaultTableModel model;
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane(table);

	JTextField dno = new JTextField(20);
	JTextField dname = new JTextField(20);
	JPanel jp = new JPanel(new GridLayout(1, 1, 10, 10));
	JButton check = new JButton("查询");
	JButton breturn = new JButton("返回");

	public ColCheck() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("系编号", SwingConstants.LEFT);
		JLabel u2 = new JLabel("系名", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(dno);
		jp.add(u2);
		jp.add(dname);
		jp.add(check);
		jp.add(breturn);
		f.add(jsp);
		check.setBounds(50, 30, 65, 20);
		dno.addActionListener(new TextHandler(1));
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
		String num = String.valueOf(dno.getText());
		String name = dname.getText();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
			String sql = "select * from department where 1=1 ";
			if (!num.equals("") && num != null) {
				sql += "and dno = " + "'" + num + "'";
			}
			if (!name.equals("") && name != null) {
				sql += "and dname = " + "'" + name + "'";
			}
			Statement pstm = conn.createStatement();
			ResultSet sr = pstm.executeQuery(sql);

			String info[] = { "系编号", "系名", "系主任" };
			String colinfo[] = { "dno", "dname", "dmanager" };
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
					row.add(sr.getString(colinfo[i] + ""));
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
