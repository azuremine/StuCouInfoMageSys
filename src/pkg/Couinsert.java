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

public class Couinsert {
	JFrame f = new JFrame("�γ���Ϣ¼��");
	JTextField cno = new JTextField(20);
	JTextField cname = new JTextField(20);
	JTextField cpno = new JTextField(20);
	JTextField ccredit = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton finish = new JButton("¼�����");
	JButton fanhui = new JButton("����");
	JPanel jp = new JPanel(new GridLayout(5, 2, 10, 10));

	public Couinsert() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("�γ̺ţ�", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(cno);
		JLabel u2 = new JLabel("�γ�����", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(cname);
		JLabel u3 = new JLabel("���пα�ţ�", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(cpno);
		JLabel u4 = new JLabel("ѧ�֣�", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(ccredit);
		jp.add(finish);
		jp.add(fanhui);
		f.getContentPane().add("Center", ta);
		cno.addActionListener(new TextHandler(1));
		cname.addActionListener(new TextHandler(2));
		cpno.addActionListener(new TextHandler(3));
		ccredit.addActionListener(new TextHandler(4));
		finish.addActionListener(new TextHandler(5));
		fanhui.addActionListener(new TextHandler(6));
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
		String numb1;
		String numb2;
		String name;
		String credit;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ѧ��ѡ�ι�����Ϣϵͳ";
		numb1 = String.valueOf(cno.getText());
		numb2 = String.valueOf(cpno.getText());
		name = String.valueOf(cname.getText());
		credit = String.valueOf(ccredit.getText());
		if (credit.equals("")) {
			credit = "0";
		}
		//String query = "select * from course";
		String sql = "insert into course (cno,cname,cpno,ccredit) values(" + "'" + numb1 + "','" + name + "','" + numb2
				+ "'," + credit + ")";
		if (numb1 == "" || numb2 == "" || name == "" || credit == "0") {
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
