package pkg;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class TeaMainMenu extends JFrame implements ActionListener{
	JButton check =  new JButton("��     Ϣ     ��     ѯ");
	JButton insert = new JButton("��     Ϣ     ¼     ��");
	JButton view =   new JButton("��     Ϣ     �     ��");
	JButton help =   new JButton("��     ��     ˵     ��");
	public TeaMainMenu(){
		super("ѧ��ѡ����Ϣ����ϵͳ");
//		ImageIcon bg = new ImageIcon("3.jpg");
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
		add(check);
		add(insert);
		add(view);
		add(help);
		check.addActionListener(this);
		insert.addActionListener(this);
		view.addActionListener(this);
		help.addActionListener(this);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==check){new Check();}
		if(e.getSource()==insert){new Insert();}
		if(e.getSource()==view){new View();}
		if(e.getSource()==help){new Help();}
		
	}
	public static void main(String args[])
	{
	new StuMainMenu();
	}

}
