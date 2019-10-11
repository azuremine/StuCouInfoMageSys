package pkg;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
public class StuMainMenu extends JFrame implements ActionListener{
	JButton insert = new JButton("ѡ     ��     ¼     ��");
	JButton check =  new JButton("��     Ϣ     ��     ѯ");
	JButton view =   new JButton("��     Ϣ     �     ��");
	JButton help =   new JButton("��     ��     ˵     ��");
	public StuMainMenu(){
		super("ѧ��ѡ����Ϣ����ϵͳ");
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
