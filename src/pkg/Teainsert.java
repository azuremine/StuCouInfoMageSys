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

public class Teainsert {
	JFrame f = new JFrame("��ʦ��Ϣ¼��");
	JTextField tno = new JTextField(20);
	JTextField tname = new JTextField(10);
	JTextField tsex = new JTextField(20);
	JTextField tage = new JTextField(20);
	JTextField teb = new JTextField(20);
	JTextField tpt = new JTextField(20);
	JTextField cno1 = new JTextField(20);
	JTextField cno2 = new JTextField(20);
	JTextField cno3 = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("¼�����");
	JButton fanhui = new JButton("����");
	JPanel jp = new JPanel(new GridLayout(10, 2, 10, 10));

	public Teainsert() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("�̹��ţ�", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(tno);
		JLabel u2 = new JLabel("��ʦ������", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(tname);
		JLabel u3 = new JLabel("��ʦ�Ա�", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(tsex);
		JLabel u4 = new JLabel("��ʦ���䣺", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(tage);
		JLabel u5 = new JLabel("��ʦѧ����", SwingConstants.LEFT);
		jp.add(u5);
		jp.add(teb);
		JLabel u6 = new JLabel("��ʦְ�ƣ�", SwingConstants.LEFT);
		jp.add(u6);
		jp.add(tpt);
		JLabel u7 = new JLabel("�����γ�1��", SwingConstants.LEFT);
		jp.add(u7);
		jp.add(cno1);
		JLabel u8 = new JLabel("�����γ�2��", SwingConstants.LEFT);
		jp.add(u8);
		jp.add(cno2);
		JLabel u9 = new JLabel("�����γ�3��", SwingConstants.LEFT);
		jp.add(u9);
		jp.add(cno3);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		tno.addActionListener(new TextHandler(1));
		tname.addActionListener(new TextHandler(2));
		tsex.addActionListener(new TextHandler(3));
		tage.addActionListener(new TextHandler(4));
		teb.addActionListener(new TextHandler(5));
		tpt.addActionListener(new TextHandler(6));
		cno1.addActionListener(new TextHandler(7));
		cno1.addActionListener(new TextHandler(8));
		cno1.addActionListener(new TextHandler(9));
		finish.addActionListener(new TextHandler(10));
		fanhui.addActionListener(new TextHandler(11));
		f.addWindowListener(new WindowHandler());
		f.setSize(450, 450);
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
		String sex;
		String age;
		String steb;
		String stpt;
		String scno1, scno2, scno3;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ѧ��ѡ�ι�����Ϣϵͳ";
		numb = String.valueOf(tno.getText());
		name = String.valueOf(tname.getText());
		sex = String.valueOf(tsex.getText());
		age = String.valueOf(tage.getText());
		if (age.equals(""))
			age = "0";
		steb = String.valueOf(teb.getText());
		stpt = String.valueOf(tpt.getText());
		scno1 = String.valueOf(cno1.getText());
		scno2 = String.valueOf(cno2.getText());
		scno3 = String.valueOf(cno3.getText());
		//String query = "select * from teacher";
		String sql = "insert into teacher (tno,tname,tsex,tage,teb,tpt,cno1,cno2,cno3) values(" + "'" + numb + "','"
				+ name + "','" + sex + "'," + age + ",'" + steb + "','" + stpt + "','" + scno1 + "','" + scno2 + "','"
				+ scno3 + "')";
		if (numb == "" || name == "" || sex == "" || age == "0" || steb == "" || stpt == "" || scno1 == ""
				|| scno2 == "" || scno3 == "") {
			JOptionPane.showMessageDialog(null, "������������Ϣ");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				Statement st = conn.createStatement();
				//st.executeQuery(query);
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(null, "¼�����");
				st.close();
				conn.close();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(null, "¼��ʧ��");
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
