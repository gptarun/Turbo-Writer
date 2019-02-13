import javax.swing.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class Da implements ActionListener {

	static int count=1;
	
	JFrame base;
	JLabel jLabel1;
	AudioClip sound1;
	AudioClip sound2;
	Icon icn1;
	Icon icn2;
	JButton jButton1;
	JButton jButton2;
	JButton jButton3;
	JButton jButton4;

	
	public Da() {
		base = new JFrame("TurboWriter");
		base.setLayout(null);
		ImageIcon bi = new ImageIcon("dd.png");
		base.setIconImage(bi.getImage());
		
		jLabel1 = new JLabel(new ImageIcon("q.jpg"));
		jLabel1.setBounds(0,0,1366,768);			
		base.setContentPane(jLabel1);
		
		File wavFile = new File("song.wav");
		File wavFile2 = new File("beffect.wav");
		try{
			sound1 = Applet.newAudioClip(wavFile.toURL());
			sound2 = Applet.newAudioClip(wavFile2.toURL());
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		jButton1 = new JButton();
		jButton1.setBounds(950,200,148,45);
		jButton1.setFont(new Font("New Times Roman",Font.BOLD,20));
		jButton1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Icon x = new ImageIcon("sp.jpg");
		jButton1.setIcon(x);
		jButton1.addActionListener(this);
		jLabel1.add(jButton1);
		
		jButton2 = new JButton();
		jButton2.setBounds(950,300,148,45);
		jButton2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Icon y = new ImageIcon("mp.jpg");
		jButton2.setIcon(y);
		jButton2.addActionListener(this);
		jButton2.setFont(new Font("New Times Roman",Font.BOLD,20));
		jLabel1.add(jButton2);
		
		jButton3 = new JButton();
		jButton3.setBounds(950,400,148,45);
		jButton3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		Icon z = new ImageIcon("exit.jpg");
		jButton3.setIcon(z);
		jButton3.setFont(new Font("New Times Roman",Font.BOLD,20));
		jButton3.addActionListener(this);
		jLabel1.add(jButton3);
		
		
		jButton4 = new JButton();
		jButton4.setBounds(1300,20,40,33);
		jButton4.setFont(new Font("New Times Roman",Font.BOLD,20));
		icn1 = new ImageIcon("on.jpg");
		icn2 = new ImageIcon("off.jpg");
		jButton4.setIcon(icn1);
		jButton4.addActionListener(this);
		jLabel1.add(jButton4);

		base.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		base.setExtendedState(JFrame.MAXIMIZED_BOTH);
		sound1.loop();
		base.setVisible(true);	
	}
	
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==jButton4) {
			count++;
			if(count%2==0) {
				jButton4.setIcon(icn2);
				sound1.stop();
			}	
			else {
				sound1.loop();
				jButton4.setIcon(icn1);
			}	
		}

		else if(ae.getSource()==jButton3) {
			sound2.play();
			try {
				Thread.sleep(1000);
			}
			catch(InterruptedException ex) {
				ex.printStackTrace();
			}	
			System.exit(1);		
		}

		else if(ae.getSource()==jButton1) {
			sound2.play();
			base.setVisible(false);
			new SinglePlayer();
		}
		
		else if(ae.getSource()==jButton2) {
			sound2.play();
			base.setVisible(false);
			new MultiOption();
		}
		
	}
		
	
	public static void main(String args[]) {
		new Da();
	}
}	