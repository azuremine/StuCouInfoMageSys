package pkg;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Help extends JFrame implements ActionListener {
	JTextArea help = new JTextArea("��ϵͳģ��ѧ��ѡ�εĲ��ֹ�����.\n" + "ѧ����Уע�����ͳһ��¼ѧ�����˻�����Ϣ��\n" + "��������ѧ���������ؿγ���Ҫ��¼ÿ�ſγ̵Ļ�����Ϣ��\n"
			+ "ÿ���ον�ʦ�涨����������ſγ̣�\n" + "ѧ��ѡ��ʱϵͳ����Ӧ��ѡ����Ϣ��¼��⣬\n" + "���Խ�����������Ӧ��ѡ�μ�¼�в��Ͽ��Գɼ���\n", 2, 20);

	JButton breturn = new JButton("������һ��");

	public Help() {
		super("������Ϣ");
		setSize(300, 300);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));
		add(help);
		help.setEditable(false);
		add(breturn);
		breturn.addActionListener(this);
		this.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == breturn) {
			dispose();
		}
	}

	public static void main(String args[]) {
		new Help();
	}
}