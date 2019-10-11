package pkg;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class StuMainMenu extends JFrame implements ActionListener{
	JButton insert = new JButton("选     课     录     入");
	JButton check =  new JButton("信     息     查     询");
	JButton view =   new JButton("信     息     浏     览");
	JButton help =   new JButton("帮     助     说     明");
	public StuMainMenu(){
		super("学生选课信息管理系统");
//		ImageIcon bg = new ImageIcon("4.jpg");
//		JLabel label = new JLabel(bg);
//		label.setBounds(0,0,bg.getIconWidth(),bg.getIconHeight());
//		getLayeredPane().add(label,new Integer(Integer.MIN_VALUE)); 
//		JPanel jp=(JPanel)getContentPane();
//		jp.setOpaque(false);
		setSize(250,350);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		setLayout(new FlowLayout(FlowLayout.CENTER,15,10));
		add(insert);
		add(check);
		add(view);
		add(help);
		insert.addActionListener(this);
		check.addActionListener(this);
		view.addActionListener(this);
		help.addActionListener(this);	
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==insert){new Calinsert();}
	    if(e.getSource()==check){new Check();}
		if(e.getSource()==view){new View();}
		if(e.getSource()==help){new Help();}
		
		
	}
	public static void main(String args[])
	{
	new StuMainMenu();
	}

}
