package pkg;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class LoginWindow extends JFrame implements ActionListener, ItemListener {
	JLabel user = new JLabel("�� �� ��:");
	JLabel password = new JLabel("��      ��:");
	JLabel usertype = new JLabel("�û�����:");
	JButton login = new JButton("��¼");
	JButton cancel = new JButton("ȡ��");
	JButton forget = new JButton("�޸�����");
	ButtonGroup buttongroup = new ButtonGroup();
	JRadioButton manager = new JRadioButton("����Ա");
	JRadioButton teacher = new JRadioButton("��ʦ");
	JRadioButton student = new JRadioButton("ѧ��  ");
	JTextField text1 = new JTextField(18);				//�û���
	JPasswordField text2 = new JPasswordField(18);		//����
	JPanel jp_top;
	JPanel jp_center;
	JPanel jp_floor;

	static String username = "admin";
	static String psword = "000000";

	public LoginWindow() {
		super("ѧ��ѡ����Ϣ����ϵͳ");		//JFram ���캯������Ӧ�ó������

		setSize(350, 200);
		setVisible(true);			//���ڿɼ�
		setResizable(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));	//������ʽ����
		add(user);
		add(text1);
		add(password);
		add(text2);
		add(usertype);
		add(manager);
		add(student);
		add(teacher);
		add(login);
		add(cancel);
		add(forget);					//��������뵽���
//		manager.setOpaque(false);
//		student.setOpaque(false);
//		teacher.setOpaque(false);
		buttongroup.add(manager);	//������������һ����ť����ʱ������ťʧЧ
		buttongroup.add(student);
		buttongroup.add(teacher);
		manager.addItemListener(this);		//��Ӽ���
		student.addItemListener(this);
		teacher.addItemListener(this);
		login.addActionListener(this);
		cancel.addActionListener(this);
		forget.addActionListener(this);

		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screen.width - 300) / 2, (screen.height - 220) / 2);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {			//��׽����
		if (e.getSource() == login) {						//���������ǵ�¼��
			if (manager.isSelected()) {						//���ѡ�й���Ա��¼
				if (text1.getText().equals("admin") && new String(text2.getPassword()).equals("000000")) {
					new MainMenu();							//��¼�ɹ�����ת������
					dispose();
				}
				if (text1.getText().equals("")) {			//�жϹ���Ա�˺������Ƿ���ȷ
					JOptionPane.showMessageDialog(null, "�������û���");
				} else if ((new String(text2.getPassword()).equals(""))) {
					JOptionPane.showMessageDialog(null, "����������");
				} else if (!(text1.getText().equals("admin"))) {
					JOptionPane.showMessageDialog(null, "��������ȷ���û���");
				} else if (!(new String(text2.getPassword()).equals("000000"))) {
					JOptionPane.showMessageDialog(null, "��������ȷ������");
				}
			}

			if (student.isSelected()) {						//���ѡ�е���ѧ��
				if (text1.getText().equals("")) {			//��ȡ�û����롰���Ƚϣ���Ϊ��ʱ����
					JOptionPane.showMessageDialog(null, "�������û���");
				} else if ((new String(text2.getPassword()).equals(""))) {		//��ȡ����������� ���� �Ƚϣ���Ϊ��ʱ����
					JOptionPane.showMessageDialog(null, "����������");
				} else {
					String name = String.valueOf(text1.getText());
					//String name = text1.getText();
					String mima = new String(text2.getPassword());
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");		//�������ݿ�
						String sql = "select * from studentid ";			//SQL���
						PreparedStatement pstm = conn.prepareStatement(sql);
						ResultSet sr = pstm.executeQuery();			//ִ�����ݿ��ѯ���
						while (sr.next()) {
							String name1 = sr.getString("stuid").trim();		//���ָ���С�stuid"��ֵ,trim()ȥ����ͷ�ͽ�β�Ŀո�
							String mima1 = sr.getString("stupassword").trim();	//���ָ���С�stupassword"��ֵ��trim()ȥ����ͷ�ͽ�β�Ŀո�
							if (!name1.equals(name)) {
								continue;										//�˺Ų�ƥ�䣬continue
							} else if (!(mima1.equals(mima))) {					//�˺�ƥ�䣬���벻ƥ��
								JOptionPane.showMessageDialog(null, "��������ȷ������");
								return;											//����
							} else {
								new StuMainMenu();								//��½�ɹ�����תѧ��������
								dispose();
								return;
							}
						}
						JOptionPane.showMessageDialog(null, "�û���������");		//��������궼û�У��û���������
					} catch (ClassNotFoundException ex) {						
						ex.printStackTrace();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

			}

			if (teacher.isSelected()) {
				if (text1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "�������û���");
				} else if ((new String(text2.getPassword()).equals(""))) {
					JOptionPane.showMessageDialog(null, "����������");
				} else {
					String name = String.valueOf(text1.getText());
					String mima = new String(text2.getPassword());
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
						String sql = "select * from teacherid ";
						PreparedStatement pstm = conn.prepareStatement(sql);
						ResultSet sr = pstm.executeQuery();
						while (sr.next()) {
							String name1 = sr.getString("teaid").trim();
							String mima1 = sr.getString("teapassword").trim();
							if (!name1.equals(name)) {
								continue;
							} else if (!(mima1.equals(mima))) {
								JOptionPane.showMessageDialog(null, "��������ȷ������");
								return;
							} else {
								new TeaMainMenu();				//��½�ɹ�����ת��ʦ������
								dispose();
								return;
							}

						}
						JOptionPane.showMessageDialog(null, "�û���������");				//��������궼û�У��û���������
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == cancel) {				//���ȡ��
			dispose();
			System.exit(0);
		} else if (e.getSource() == forget) {				//����޸�����
			new Forget();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {				//ʵ�ּ̳еĳ��󷽷�
		if (e.getSource() == manager) {
		} else if (e.getSource() == student) {
		}
	}

	public static void main(String args[]) {
		new LoginWindow(); 									// �������
	}
}