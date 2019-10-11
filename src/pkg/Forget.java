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
	JLabel user= new JLabel("��    ��    ��:");
	JLabel password= new  JLabel("��            ��:");
	JLabel changepwd= new  JLabel("�� �� �� ��:");
	JLabel usertype = new JLabel("�û�����:");
	JLabel none= new JLabel("     ");
	JButton regis = new JButton("ȷ��");
	JButton cancel = new JButton("ȡ��");
	ButtonGroup buttongroup=new ButtonGroup();
	JRadioButton teacher = new JRadioButton("��ʦ");
	JRadioButton student=  new JRadioButton("ѧ��");
	JTextField text1=new JTextField(15);
	JPasswordField text2=new JPasswordField(15);
	JPasswordField text3=new JPasswordField(15);
	
    public Forget(){
    	super("�޸�����");
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
    	//��������Ӧ��Ļ������������
    	Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    	setLocation((screen.width-300)/2,(screen.height-220)/2);
    	
    	addWindowListener(new WindowAdapter()
    	{@Override
		public void windowClosing(WindowEvent e)
    	{dispose();}
    	});
    	}
    /**
     * ���ü����¼�
     */
    @Override
	public void actionPerformed(ActionEvent e){
    if(student.isSelected()){
    	if(e.getSource()==regis){	//�����������޸İ�ť
    		boolean flag = false;
    		String name = String.valueOf(text1.getText());	//String.value�ɻ�������������ת����String
    		String mima = new String(text2.getPassword());
    		String changemima = new String(text3.getPassword()).trim();
    		try{
    			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=123;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
    			String stutid = "select * from studentid";
    			PreparedStatement pstm1 = conn.prepareStatement(stutid);
    			ResultSet sr = pstm1.executeQuery();
    			while(sr.next()){
    				String name1 = sr.getString("stuid").trim();
    				String mima1 = sr.getString("stupassword").trim();
    				if (!name1.equals(name)) {
						continue;										//�˺Ų�ƥ�䣬continue
					} else if (!(mima1.equals(mima))) {					//�˺�ƥ�䣬���벻ƥ��
						JOptionPane.showMessageDialog(null, "��������ȷ������");
						return;											//����
					} else {
						flag = true;
						String sql = "Update studentid set stupassword = ? where stuid = ?";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1,changemima);								//�޸��û������
						pstm.setString(2, name);
						pstm.executeUpdate();
						pstm.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
						break;
					}
    			}
    			if(!flag){
    				JOptionPane.showMessageDialog(null, "�û���������");
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
    			Connection conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;user=sa;password=000000;databasename=ѧ��ѡ�ι�����Ϣϵͳ");
    			String teatid = "select * from teacherid";
    			PreparedStatement pstm1 = conn.prepareStatement(teatid);
    			ResultSet sr = pstm1.executeQuery();
    			while(sr.next()){
    				String name1 = sr.getString("teaid").trim();
    				String mima1 = sr.getString("teapassword").trim();
    				if (!name1.equals(name)) {
						continue;										//�˺Ų�ƥ�䣬continue
					} else if (!(mima1.equals(mima))) {					//�˺�ƥ�䣬���벻ƥ��
						JOptionPane.showMessageDialog(null, "��������ȷ������");
						return;											//����
					} else {
						flag = true;
						String sql = "Update teacherid set teapassword = ? where teaid = ? ";
						PreparedStatement pstm = conn.prepareStatement(sql);
						pstm.setString(1,changemima);
						pstm.setString(2,name);
						pstm.executeUpdate();
						pstm.close();
						conn.close();
						JOptionPane.showMessageDialog(null, "�޸ĳɹ�");
						break;
					}
    			}
    			if(!flag){
    				JOptionPane.showMessageDialog(null, "�û���������");
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
    

