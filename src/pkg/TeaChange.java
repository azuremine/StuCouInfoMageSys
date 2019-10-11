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

public class TeaChange {
	JFrame f = new JFrame("��ʦ��Ϣ�޸�");
	JTextField tno = new JTextField(20);
	JTextField tname = new JTextField(20);
	JTextField tsex = new JTextField(20);
	JTextField tage = new JTextField(20);
	JTextField teb = new JTextField(20);
	JTextField tpt = new JTextField(20);
	JTextField cno1 = new JTextField(20);
	JTextField cno2 = new JTextField(20);
	JTextField cno3 = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("�޸����");
	JButton fanhui = new JButton("����");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public TeaChange() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("Ҫ�޸ĵĽ̹��ţ�", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(tno);
		JLabel u2 = new JLabel("������������", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(tname);
		JLabel u3 = new JLabel("�������Ա�", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(tsex);
		JLabel u4 = new JLabel("���������䣺", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(tage);
		JLabel u5 = new JLabel("������ѧ����", SwingConstants.LEFT);
		jp.add(u5);
		jp.add(teb);
		JLabel u6 = new JLabel("������ְ�ƣ�", SwingConstants.LEFT);
		jp.add(u6);
		jp.add(tpt);
		JLabel u7 = new JLabel("�����������γ�1��", SwingConstants.LEFT);
		jp.add(u7);
		jp.add(cno1);
		JLabel u8 = new JLabel("�����������γ�2��", SwingConstants.LEFT);
		jp.add(u8);
		jp.add(cno2);
		JLabel u9 = new JLabel("�����������γ�3��", SwingConstants.LEFT);
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
		finish.addActionListener(new TextHandler(6));
		fanhui.addActionListener(new TextHandler(7));
		tpt.addActionListener(new TextHandler(5));
		cno1.addActionListener(new TextHandler(5));
		cno2.addActionListener(new TextHandler(5));
		cno3.addActionListener(new TextHandler(5));
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
		String sex;
		String age;
		String tteb;
		String ttpt;
		String ccno1;
		String ccno2;
		String ccno3;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ѧ��ѡ�ι�����Ϣϵͳ";
		numb = String.valueOf(tno.getText());
		name = String.valueOf(tname.getText());
		sex = String.valueOf(tsex.getText());
		age = String.valueOf(tage.getText());
		if (age.equals("")) {
			age = "0";
		}
		tteb = String.valueOf(teb.getText());
		ttpt = String.valueOf(tpt.getText());
		ccno1 = String.valueOf(cno1.getText());
		ccno2 = String.valueOf(cno2.getText());
		ccno3 = String.valueOf(cno3.getText());
		int a;
		if (numb == "" || name == "" || sex == "" || age == "0" || tteb == "" || ttpt == "" || ccno1 == ""
				|| ccno2 == "" || ccno3 == "") {
			JOptionPane.showMessageDialog(null, "������������Ϣ");
		} else {
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
				Connection conn = DriverManager.getConnection(url, "sa", "123");
				String query = "update teacher set tname = ? , tsex = ? , tage = ? , teb =?,tpt=?,cno1=?,cno2=?,cno3=? where tno = ?";
				PreparedStatement pstm = conn.prepareStatement(query);
				pstm.setString(1, name);
				pstm.setString(2, sex);
				pstm.setString(3, age);
				pstm.setString(4, tteb);
				pstm.setString(5, ttpt);
				pstm.setString(6, ccno1);
				pstm.setString(7, ccno2);
				pstm.setString(8, ccno3);
				pstm.setString(9, numb);
				a = pstm.executeUpdate();
				if (a > 0) {
					JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "�޸�ʧ��");
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
