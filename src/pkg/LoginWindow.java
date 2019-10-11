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
	JLabel user = new JLabel("用 户 名:");
	JLabel password = new JLabel("密      码:");
	JLabel usertype = new JLabel("用户类型:");
	JButton login = new JButton("登录");
	JButton cancel = new JButton("取消");
	JButton forget = new JButton("修改密码");
	ButtonGroup buttongroup = new ButtonGroup();
	JRadioButton manager = new JRadioButton("管理员");
	JRadioButton teacher = new JRadioButton("教师");
	JRadioButton student = new JRadioButton("学生  ");
	JTextField text1 = new JTextField(18);				//用户名
	JPasswordField text2 = new JPasswordField(18);		//密码
	JPanel jp_top;
	JPanel jp_center;
	JPanel jp_floor;

	static String username = "admin";
	static String psword = "000000";

	public LoginWindow() {
		super("学生选课信息管理系统");		//JFram 构造函数，即应用程序标题

		setSize(350, 200);
		setVisible(true);			//窗口可见
		setResizable(true);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));	//设置流式布局
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
		add(forget);					//将组件加入到面板
//		manager.setOpaque(false);
//		student.setOpaque(false);
//		teacher.setOpaque(false);
		buttongroup.add(manager);	//加入多斥作用域，一个按钮作用时其他按钮失效
		buttongroup.add(student);
		buttongroup.add(teacher);
		manager.addItemListener(this);		//添加监听
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
	public void actionPerformed(ActionEvent e) {			//捕捉动作
		if (e.getSource() == login) {						//如果点击的是登录键
			if (manager.isSelected()) {						//如果选中管理员登录
				if (text1.getText().equals("admin") && new String(text2.getPassword()).equals("000000")) {
					new MainMenu();							//登录成功则跳转主界面
					dispose();
				}
				if (text1.getText().equals("")) {			//判断管理员账号密码是否正确
					JOptionPane.showMessageDialog(null, "请输入用户名");
				} else if ((new String(text2.getPassword()).equals(""))) {
					JOptionPane.showMessageDialog(null, "请输入密码");
				} else if (!(text1.getText().equals("admin"))) {
					JOptionPane.showMessageDialog(null, "请输入正确的用户名");
				} else if (!(new String(text2.getPassword()).equals("000000"))) {
					JOptionPane.showMessageDialog(null, "请输入正确的密码");
				}
			}

			if (student.isSelected()) {						//如果选中的是学生
				if (text1.getText().equals("")) {			//获取用户名与“”比较，即为空时进入
					JOptionPane.showMessageDialog(null, "请输入用户名");
				} else if ((new String(text2.getPassword()).equals(""))) {		//获取输入的密码与 “” 比较，即为空时进入
					JOptionPane.showMessageDialog(null, "请输入密码");
				} else {
					String name = String.valueOf(text1.getText());
					//String name = text1.getText();
					String mima = new String(text2.getPassword());
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");		//连接数据库
						String sql = "select * from studentid ";			//SQL语句
						PreparedStatement pstm = conn.prepareStatement(sql);
						ResultSet sr = pstm.executeQuery();			//执行数据库查询语句
						while (sr.next()) {
							String name1 = sr.getString("stuid").trim();		//获得指定列“stuid"的值,trim()去掉开头和结尾的空格
							String mima1 = sr.getString("stupassword").trim();	//获得指定列“stupassword"的值，trim()去掉开头和结尾的空格
							if (!name1.equals(name)) {
								continue;										//账号不匹配，continue
							} else if (!(mima1.equals(mima))) {					//账号匹配，密码不匹配
								JOptionPane.showMessageDialog(null, "请输入正确的密码");
								return;											//返回
							} else {
								new StuMainMenu();								//登陆成功则跳转学生主界面
								dispose();
								return;
							}
						}
						JOptionPane.showMessageDialog(null, "用户名不存在");		//如果搜索完都没有，用户名不存在
					} catch (ClassNotFoundException ex) {						
						ex.printStackTrace();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}

			}

			if (teacher.isSelected()) {
				if (text1.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "请输入用户名");
				} else if ((new String(text2.getPassword()).equals(""))) {
					JOptionPane.showMessageDialog(null, "请输入密码");
				} else {
					String name = String.valueOf(text1.getText());
					String mima = new String(text2.getPassword());
					try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
						Connection conn = DriverManager.getConnection(
								"jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
						String sql = "select * from teacherid ";
						PreparedStatement pstm = conn.prepareStatement(sql);
						ResultSet sr = pstm.executeQuery();
						while (sr.next()) {
							String name1 = sr.getString("teaid").trim();
							String mima1 = sr.getString("teapassword").trim();
							if (!name1.equals(name)) {
								continue;
							} else if (!(mima1.equals(mima))) {
								JOptionPane.showMessageDialog(null, "请输入正确的密码");
								return;
							} else {
								new TeaMainMenu();				//登陆成功则跳转教师主界面
								dispose();
								return;
							}

						}
						JOptionPane.showMessageDialog(null, "用户名不存在");				//如果搜索完都没有，用户名不存在
					} catch (ClassNotFoundException ex) {
						ex.printStackTrace();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
			}
		} else if (e.getSource() == cancel) {				//点击取消
			dispose();
			System.exit(0);
		} else if (e.getSource() == forget) {				//点击修改密码
			new Forget();
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {				//实现继承的抽象方法
		if (e.getSource() == manager) {
		} else if (e.getSource() == student) {
		}
	}

	public static void main(String args[]) {
		new LoginWindow(); 									// 程序入口
	}
}