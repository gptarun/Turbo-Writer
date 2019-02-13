import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class MultiOption implements ActionListener {

	static int count=1;
	
	JFrame base;
	Icon icn1;
	Icon icn2;
	JLabel jLabel1;
	JButton Host;
	JButton Find;

	public MultiOption() {
		base = new JFrame("TurboWriter");
		base.setLayout(null);
		ImageIcon bi = new ImageIcon("dd.png");
		base.setIconImage(bi.getImage());
		
		jLabel1 = new JLabel(new ImageIcon("q.jpg"));
		jLabel1.setBounds(0,0,1366,768);			
		base.setContentPane(jLabel1);
		
		Host = new JButton();
		Host.setBounds(950,200,148,45);
		Host.setFont(new Font("New Times Roman",Font.BOLD,20));
		Host.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		icn1 = new ImageIcon("hg.jpg");
		Host.setIcon(icn1);
		Host.addActionListener(this);
		jLabel1.add(Host);
		
		Find = new JButton();
		Find.setBounds(950,300,148,45);
		Find.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		icn2 = new ImageIcon("fg.jpg");
		Find.setIcon(icn2);
		Find.setFont(new Font("New Times Roman",Font.BOLD,20));
		jLabel1.add(Find);
		Find.addActionListener(this);
		
		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		base.setExtendedState(JFrame.MAXIMIZED_BOTH);
		base.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		if(ae.getSource()==Host)
		{
			base.setVisible(false);
			base.dispose();
			new Server();
			new MultiPlayerS();
		}
		else if(ae.getSource()==Find)
		{
			base.setVisible(false);
			base.dispose();
			new Client();
			new MultiPlayerC();
		}
	}
		
	
	public static void main(String args[]) {
		new MultiOption();
	}
}	