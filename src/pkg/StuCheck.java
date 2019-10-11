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

public class StuCheck {

	JFrame f = new JFrame("查询学生信息");

	DefaultTableModel model;
	JTable table = new JTable(); // 表格组件
	JScrollPane jsp = new JScrollPane(table); // 为table增加垂直滚动条

	JTextField sno = new JTextField(20);
	JTextField sname = new JTextField(20);
	JPanel jp = new JPanel(new GridLayout(1, 1, 10, 10));	//1行1列，水平、垂直10
	JButton check = new JButton("查询");
	JButton breturn = new JButton("返回");

	public StuCheck() {
		jp.setOpaque(false);
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("学生学号", SwingConstants.CENTER);
		JLabel u2 = new JLabel("学生姓名", SwingConstants.CENTER);
		jp.add(u1);
		jp.add(sno);
		jp.add(u2);
		jp.add(sname);
		jp.add(check);
		jp.add(breturn);
		check.setBounds(50, 30, 65, 20);
		f.add(jsp);
		f.addWindowListener(new WindowHandler());
		check.addActionListener(new TextHandler(2)); // 绑定单击事件
		breturn.addActionListener(new TextHandler(3)); // 绑定单击事件
		f.setSize(650, 500);	//窗口大小
		f.setVisible(true);		//窗口可见
		f.setLocationRelativeTo(null);	//窗口自动居中
	}

	// 监听按钮触发
	class TextHandler implements ActionListener {
		int sel;

		TextHandler(int sel) {
			this.sel = sel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == check) {
				table.setModel(check());	//设置check()返回装有数据的DefaultTableModel对象
			}
			if (e.getSource() == breturn) {
				f.dispose();
			}
		}
	}

	public DefaultTableModel check() {
		String num = sno.getText();
		String name = sname.getText();
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
			String sql = "select * from student where 1=1 ";

			//多条件查询，拼接查询字符串语句
			if (!num.equals("") && num != null) {
				sql += "and sno = " + "'" + num + "'";
			}
			if (!name.equals("") && name != null) {
				sql += "and sname = " + "'" + name + "'";
			}
			
			Statement pstm = conn.createStatement();
			ResultSet sr = pstm.executeQuery(sql);	//执行数据库操作，拿到返回结果集

			String info[] = { "学号", "姓名", "性别", "年龄", "系别" }; 	//model模型头
			String stuinfo[] = { "sno", "sname", "ssex", "sage", "sdept" };	//定义数据库的字段

			model = new DefaultTableModel(null, info); // 实例化一个二位数据为null、列名数组为info的DefaultTableModel对象

			while (sr.next()) {
				Vector<String> row = new Vector<String>(); //定义可自动增长String数组 row
				for (int i = 0; i < info.length; i++) {
					row.add(sr.getString(stuinfo[i])); //增加数据
				}
				model.addRow(row); // 添加一行数据到model的结尾
			}
			return model;	//返回DefaultTableModel对象
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
