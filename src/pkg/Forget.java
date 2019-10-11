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
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
public class Forget extends JFrame implements ActionListener,ItemListener{
	JLabel user= new JLabel("用    户    名:");
	JLabel password= new  JLabel("密            码:");
	JLabel changepwd= new  JLabel("修 改 密 码:");
	JLabel usertype = new JLabel("用户类型:");
	JLabel none= new JLabel("     ");
	JButton regis = new JButton("确认");
	JButton cancel = new JButton("取消");
	ButtonGroup buttongroup=new ButtonGroup();
	JRadioButton teacher = new JRadioButton("教师");
	JRadioButton student=  new JRadioButton("学生");
	JTextField text1=new JTextField(15);
	JPasswordField text2=new JPasswordField(15);
	JPasswordField text3=new JPasswordField(15);
	
    public Forget(){
    	super("修改密码");
//    	ImageIcon bg = new ImageIcon("background.jpg");
//    	JLabel label = new JLabel(bg);
//    	label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
//    	getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
//    	JPanel jp=(JPanel)getContentPane();
//    	jp.setOpaque(false);
    	setSize(300,220);
    	setVisible(true);
    	setResizable(false);
    	setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
    	add(user);add(text1);
    	add(password);add(text2);
    	add(changepwd);add(text3);
    	add(usertype);
    	add(student);add(teacher);add(none);
    	add(regis);add(cancel);
    	student.setContentAreaFilled(false);
    	teacher.setOpaque(false);
    	none.setOpaque(false);
    	buttongroup.add(student);
    	buttongroup.add(teacher);
    	student.addItemListener(this);
    	teacher.addItemListener(this);
    	regis.addActionListener(this);
    	cancel.addActionListener(this);
    	//将窗口适应屏幕，设置在中央
    	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screen.width-300)/2,(screen.height-220)/2);
    	
    	addWindowListener(new WindowAdapter()
    	{@Override
		public void windowClosing(WindowEvent e)
    	{dispose();}
    	});
    	}
    /**
     * 设置监听事件
     */
    @Override
	public void actionPerformed(ActionEvent e){
    if(student.isSelected()){
    	if(e.getSource()==regis){	//如果点击的是修改按钮
    		boolean flag = false;
    		String name = String.valueOf(text1.getText());	//String.value由基本的数据类型转换成String
    		String mima = new String(text2.getPassword());
    		String changemima = new String(text3.getPassword()).trim();
    		try{
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=学生选课管理信息系统");
    			String stutid = "select * from studentid";
    			PreparedStatement pstm1 = conn.prepareStatement(stutid);
    			ResultSet sr = pstm1.executeQuery();
    			while(sr.next()){
    				String name1 = sr.getString("stuid").trim();
    				String mima1 = sr.getString("stupassword").trim();
    				if (!name1.equals(name)) {
						continue;										//账号不匹配，continue
					} else if (!(mima1.equals(mima))) {					//账号匹配，密码不匹配
						JOptionPane.showMessageDialog(null, "请输入正确的密码");
						return;											//返回
					} else {
						flag = true;
						String sql = "Update studentid set stupassword = ? where stuid = ?";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1,changemima);								//修改用户密码表
						pstm.setString(2, name);
						pstm.executeUpdate();
						pstm.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "修改成功！");
						break;
					}
    			}
    			if(!flag){
    				JOptionPane.showMessageDialog(null, "用户名不存在");
    				return;
    			}
    		}catch(ClassNotFoundException ex){
    			ex.printStackTrace();
    		}catch(SQLException ex){
    			ex.printStackTrace();
    		}
    	}dispose();
    }
    if(teacher.isSelected()){
    	if(e.getSource()==regis){
    		String name = String.valueOf(text1.getText());
    		String mima = new String(text2.getPassword());
    		String changemima = new String(text3.getPassword()).trim();
    		try{
    			boolean flag = false;
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=000000;databasename=学生选课管理信息系统");
    			String teatid = "select * from teacherid";
    			PreparedStatement pstm1 = conn.prepareStatement(teatid);
    			ResultSet sr = pstm1.executeQuery();
    			while(sr.next()){
    				String name1 = sr.getString("teaid").trim();
    				String mima1 = sr.getString("teapassword").trim();
    				if (!name1.equals(name)) {
						continue;										//账号不匹配，continue
					} else if (!(mima1.equals(mima))) {					//账号匹配，密码不匹配
						JOptionPane.showMessageDialog(null, "请输入正确的密码");
						return;											//返回
					} else {
						flag = true;
						String sql = "Update teacherid set teapassword = ? where teaid = ? ";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1,changemima);
						pstm.setString(2,name);
						pstm.executeUpdate();
						pstm.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "修改成功");
						break;
					}
    			}
    			if(!flag){
    				JOptionPane.showMessageDialog(null, "用户名不存在");
    				return;
    			}
    		}catch(ClassNotFoundException ex){
    			ex.printStackTrace();
    		}catch(SQLException ex){
    			ex.printStackTrace();
    		}
    	}dispose();
    }
    if(e.getSource()==cancel){dispose();}
    }
    
    @Override
	public void itemStateChanged(ItemEvent e)
    {
    }
    public static void main(String args[])
    {
    new LoginWindow();
    }
    }
    

