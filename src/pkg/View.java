package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class View extends JFrame implements ActionListener {
	JFrame f = new JFrame("�������");
	JButton breturn = new JButton("������һ��");
	DefaultTableModel model;
	JTable table = new JTable();
	JScrollPane jsp = new JScrollPane(table);	//���ӹ�����
	JButton jb1 = new JButton("ѧ����Ϣ���");
	JButton jb2 = new JButton("��ʦ��Ϣ���");
	JButton jb3 = new JButton("�γ���Ϣ���");
	JButton jb4 = new JButton("Ժϵ��Ϣ���");
	JButton jb5 = new JButton("ѡ����Ϣ���");
	JButton jb6 = new JButton("��ӡ");

	public View() {
		super("�������");
		f.setSize(600, 600);
		f.setVisible(true);
		f.setResizable(false);
		f.setLocationRelativeTo(null);
		f.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 10));
		f.add(jb1);
		f.add(jb2);
		f.add(jb3);
		f.add(jb4);
		f.add(jb5);
		f.add(jb6);
		f.add(breturn);
		f.add(jsp);

		breturn.addActionListener(this);
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		jb5.addActionListener(this);
		jb6.addActionListener(this);
	}

	public DefaultTableModel stuview() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select * from student ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet sr = pstm.executeQuery();

			String info[] = { "ѧ��", "����", "�Ա�", "����", "ϵ��" };
			String stuinfo[] = { "sno", "sname", "ssex", "sage", "sdept" };
			model = new DefaultTableModel(null, info);
			while (sr.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < info.length; i++)
					row.add(sr.getString(stuinfo[i] + ""));
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

	public DefaultTableModel teaview() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select * from teacher ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet sr = pstm.executeQuery();

			String info[] = { "�̹���", "��ʦ��", "�Ա�", "����", "ѧ��", "ְ��", "�����γ�1", "�����γ�2", "�����γ�3" };
			String teainfo[] = { "tno", "tname", "tsex", "tage", "teb", "tpt", "cno1", "cno2", "cno3" };
			model = new DefaultTableModel(null, info);
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

	public DefaultTableModel couview() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select * from course ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet sr = pstm.executeQuery();

			String info[] = { "�γ̺�", "�γ���", "���пα��", "ѧ��" };
			String couinfo[] = { "cno", "cname", "cpno", "ccredit" };
			model = new DefaultTableModel(null, info);
			while (sr.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < info.length; i++)
					row.add(sr.getString(couinfo[i] + ""));
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

	public DefaultTableModel colview() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select * from department ";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet sr = pstm.executeQuery();

			String info[] = { "ϵ���", "ϵ��", "ϵ����" };
			String colinfo[] = { "dno", "dname", "dmanager" };
			model = new DefaultTableModel(null, info);

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

	public DefaultTableModel claview() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select a.sno,sname,a.cno,cname,a.tno,tname,grade from sct a,student b,teacher c,course d  where a.sno = b.sno and a.tno = c.tno and a.cno = d.cno";
			PreparedStatement pstm = conn.prepareStatement(sql);
			ResultSet sr = pstm.executeQuery();
			String info[] = { "ѧ��", "ѧ����", "�γ̺�", "�γ���", "�̹���", "��ʦ", "�ɼ�" };
			String calinfo[] = { "sno", "sname", "cno", "cname", "tno", "tname", "grade" };
			model = new DefaultTableModel(null, info);

			while (sr.next()) {
				Vector<String> row = new Vector<String>();
				for (int i = 0; i < info.length; i++)
					row.add(sr.getString(calinfo[i] + ""));
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

	public void Print() {
		try {
			if (table.print()) {
				JOptionPane.showMessageDialog(null, "��ӡ�ɹ�");
			}
		} catch (java.awt.print.PrinterException e) {
			JOptionPane.showMessageDialog(null, "��ӡʧ��");
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb1) {
			table.setModel(stuview());
		}
		;
		if (e.getSource() == jb2) {
			table.setModel(teaview());
		}
		;
		if (e.getSource() == jb3) {
			table.setModel(couview());
		}
		;
		if (e.getSource() == jb4) {
			table.setModel(colview());
		}
		;
		if (e.getSource() == jb5) {
			table.setModel(claview());
		}
		;
		if (e.getSource() == jb6) {
			Print();
		}
		if (e.getSource() == breturn) {
			f.dispose();
		}
	}

	public static void main(String args[]) {
		new View();
	}
}