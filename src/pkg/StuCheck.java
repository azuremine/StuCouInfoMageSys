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

	JFrame f = new JFrame("��ѯѧ����Ϣ");

	DefaultTableModel model;
	JTable table = new JTable(); // ������
	JScrollPane jsp = new JScrollPane(table); // Ϊtable���Ӵ�ֱ������

	JTextField sno = new JTextField(20);
	JTextField sname = new JTextField(20);
	JPanel jp = new JPanel(new GridLayout(1, 1, 10, 10));	//1��1�У�ˮƽ����ֱ10
	JButton check = new JButton("��ѯ");
	JButton breturn = new JButton("����");

	public StuCheck() {
		jp.setOpaque(false);
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("ѧ��ѧ��", SwingConstants.CENTER);
		JLabel u2 = new JLabel("ѧ������", SwingConstants.CENTER);
		jp.add(u1);
		jp.add(sno);
		jp.add(u2);
		jp.add(sname);
		jp.add(check);
		jp.add(breturn);
		check.setBounds(50, 30, 65, 20);
		f.add(jsp);
		f.addWindowListener(new WindowHandler());
		check.addActionListener(new TextHandler(2)); // �󶨵����¼�
		breturn.addActionListener(new TextHandler(3)); // �󶨵����¼�
		f.setSize(650, 500);	//���ڴ�С
		f.setVisible(true);		//���ڿɼ�
		f.setLocationRelativeTo(null);	//�����Զ�����
	}

	// ������ť����
	class TextHandler implements ActionListener {
		int sel;

		TextHandler(int sel) {
			this.sel = sel;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == check) {
				table.setModel(check());	//����check()����װ�����ݵ�DefaultTableModel����
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
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sql = "select * from student where 1=1 ";

			//��������ѯ��ƴ�Ӳ�ѯ�ַ������
			if (!num.equals("") && num != null) {
				sql += "and sno = " + "'" + num + "'";
			}
			if (!name.equals("") && name != null) {
				sql += "and sname = " + "'" + name + "'";
			}
			
			Statement pstm = conn.createStatement();
			ResultSet sr = pstm.executeQuery(sql);	//ִ�����ݿ�������õ����ؽ����

			String info[] = { "ѧ��", "����", "�Ա�", "����", "ϵ��" }; 	//modelģ��ͷ
			String stuinfo[] = { "sno", "sname", "ssex", "sage", "sdept" };	//�������ݿ���ֶ�

			model = new DefaultTableModel(null, info); // ʵ����һ����λ����Ϊnull����������Ϊinfo��DefaultTableModel����

			while (sr.next()) {
				Vector<String> row = new Vector<String>(); //������Զ�����String���� row
				for (int i = 0; i < info.length; i++) {
					row.add(sr.getString(stuinfo[i])); //��������
				}
				model.addRow(row); // ���һ�����ݵ�model�Ľ�β
			}
			return model;	//����DefaultTableModel����
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
