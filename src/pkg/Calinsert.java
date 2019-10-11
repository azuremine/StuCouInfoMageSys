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

public class Calinsert {
	JFrame f = new JFrame("ѡ����Ϣ¼��");
	JTextField sno = new JTextField(20);
	JTextField cno = new JTextField(20);
	JTextField tno = new JTextField(20);
	JTextField grade = new JTextField(20);
	JTextArea ta = new JTextArea(5, 10);
	JButton courseCheck = new JButton("�γ̲�ѯ");
	JButton finish = new JButton("¼�����");
	JButton fanhui = new JButton("����");
	JPanel jp = new JPanel(new GridLayout(6, 2, 10, 10));

	public Calinsert() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("ѧ��ѧ�ţ�", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(sno);
		JLabel u2 = new JLabel("�γ̺ţ�", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(cno);
		JLabel u3 = new JLabel("�̹��ţ�", SwingConstants.LEFT);
		jp.add(u3);
		jp.add(tno);
		JLabel u4 = new JLabel("�ɼ���", SwingConstants.LEFT);
		jp.add(u4);
		jp.add(grade);
		jp.add(finish);
		jp.add(fanhui);
		jp.add(courseCheck);
		f.getContentPane().add("Center", ta);
		sno.addActionListener(new TextHandler(1));
		cno.addActionListener(new TextHandler(2));
		tno.addActionListener(new TextHandler(3));
		grade.addActionListener(new TextHandler(4));
		finish.addActionListener(new TextHandler(5));
		fanhui.addActionListener(new TextHandler(6));
		courseCheck.addActionListener(new TextHandler(7));
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
			if (e.getSource() == courseCheck) {
				new CouCheck();
			}
		}
	}

	public void newinsert() {
		String numb1;
		String numb2;
		String numb3;
		String sgrade;
		String url = "jdbc:sqlserver://localhost:1433;DatabaseName=ѧ��ѡ�ι�����Ϣϵͳ";
		numb1 = String.valueOf(sno.getText());
		numb2 = String.valueOf(cno.getText());
		numb3 = String.valueOf(tno.getText());
		sgrade = String.valueOf(grade.getText());
		if (sgrade.equals("")) {
			sgrade = "0";
		}
		//String query = "select * from sct";
		String sql = "insert into sct (sno,cno,tno,grade) values(" + "'" + numb1 + "','" + numb2 + "','" + numb3 + "',"
				+ sgrade + ")";
		if (numb1 == "" || numb2 == "" || numb3 == "" || sgrade == "0") {
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
