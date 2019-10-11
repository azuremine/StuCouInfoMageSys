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
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class ClaDelete {
	JFrame f = new JFrame("ѡ����Ϣɾ��");
	JTextField cno = new JTextField(20);
	JTextField sno = new JTextField(20);
	JPanel jp = new JPanel(new GridLayout(3, 1, 10, 10));
	JButton delete = new JButton("ɾ��");
	JButton breturn = new JButton("������һ��");

	public ClaDelete() {
		f.getContentPane().setLayout(new BorderLayout(0, 10));
		f.getContentPane().add("North", jp);
		JLabel u1 = new JLabel("������γ̺�", SwingConstants.LEFT);
		jp.add(u1);
		jp.add(cno);
		JLabel u2 = new JLabel("������ѧ��ѧ��", SwingConstants.LEFT);
		jp.add(u2);
		jp.add(sno);
		jp.add(delete);
		jp.add(breturn);
		delete.setBounds(50, 30, 65, 20);
		cno.addActionListener(new TextHandler(1));
		sno.addActionListener(new TextHandler(4));
		f.addWindowListener(new WindowHandler());
		delete.addActionListener(new TextHandler(2));
		breturn.addActionListener(new TextHandler(3));
		f.setSize(450, 250);
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
			if (e.getSource() == delete) {
				delete();
				f.dispose();
			}
			if (e.getSource() == breturn) {
				f.dispose();
			}
		}
	}

	public void delete() {
		String num1, num2;
		num1 = String.valueOf(cno.getText());
		num2 = String.valueOf(sno.getText());
		int a;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
			String sqldelete = "DELETE FROM sct WHERE sno = ? and cno = ?";
			System.out.print(num1);
			System.out.print(num2);
			PreparedStatement pstm = conn.prepareStatement(sqldelete);
			pstm.setString(1, num2);
			pstm.setString(2, num1);
			a = pstm.executeUpdate();
			if (a > 0) {
				JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
			} else {
				JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
			}
			pstm.close();
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	class WindowHandler extends WindowAdapter {
		@Override
		public void windowClosing(WindowEvent e) {
			System.exit(1);
		}
	}
}
