import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.text.*;
import javax.swing.border.*;
import java.util.*;
import java.util.Date; 
import java.util.Timer; 
import java.util.TimerTask; 

class SinglePlayer extends JFrame implements KeyListener
	 {
  		JLabel l_road,l_car,l_bg,l_word,l_flag,l_player;
		Image i,i2;
  		JTextArea ta_para;   
 		JTextField tf_type;
		Highlighter hilite;
  		static JFrame frame = new JFrame("Turbo Writer");
  		int y_car=125;
		static int time=0, count=0,wpm=0;
 		private Highlighter.HighlightPainter redPainter;

  		String para1[] = {"Paragraphs", "are", "the", "building", "blocks", "of", "papers.", "Many", "students", "define" ,"paragraphs","in" ,"terms" ,"of", "length:", "a", "paragraph", "is", "a", "group", "of" ,"at" ,"least", "five" ,"sentences,", "a", "paragraph", "is", "half", "a" ,"page", "long," ,"etc." ,"In", "reality,", "though,","the", "unity", "and" ,"coherence", "of", "ideas", "among", "sentences", "is", "what", "constitutes", "a" ,"paragraph."};

		int high_in=0;
		int high_fin=10;

  		String para = "Paragraphs are the building blocks of papers. Many students define paragraphs in terms of length: a paragraph is a group of at least five sentences, a paragraph is half a page long, etc. In reality, though, the unity and coherence of ideas among sentences is what constitutes a paragraph.";

 		static int x_car=370;

		public SinglePlayer()
		{
			this.cal();
  			this.type();
		}
 
		public static void main(String args[])
 		{
  			SinglePlayer p = new SinglePlayer();
 		}
		public void cal()
		{
			Timer timer = new Timer();
			timer.scheduleAtFixedRate(new TimerTask(){ public void run() { time++; } }, new Date(), 1000); 
		}
		public void type()
 		{
  			ImageIcon imageIcon = new ImageIcon("textbg.jpg"); 					// load the image to a imageIcon
  			Image image = imageIcon.getImage(); // transform it 
  			Image newimg = image.getScaledInstance(1366, 768,  java.awt.Image.SCALE_SMOOTH); 	// scale it the smooth way  
  			imageIcon = new ImageIcon(newimg); 							 // transform i
  			l_bg = new JLabel(imageIcon);
  			frame.setContentPane(l_bg);
  
  			redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.red);		//Highlighter for text area.

  			l_road = new JLabel(new ImageIcon("road.jpg"));
  	
  			ta_para = new JTextArea(para,6,55)							//Here disable the background of text area		
   			{
     				{
					setOpaque(false);
   				}
   			};										//text area
  			ta_para.setEditable(false);								// working on text area
  			ta_para.setForeground(Color.WHITE);
  			ta_para.setLineWrap(true);
  			ta_para.setWrapStyleWord(true);
  			ta_para.setBounds(370,250,610,150); 							//x , y , l_x , l_y
  			Font f = new Font("SansSarif",Font.BOLD,22);
  			ta_para.setFont(f); 
  
  			try {										//implements the highlighter on text area.
          				ta_para.getHighlighter().addHighlight(high_in,high_fin, redPainter);	
        			      } catch (BadLocationException ble) {
        			      }

  			l_road.setBounds(370,100,600,100); 
  			l_car = new JLabel(new ImageIcon("car1.png"));						// labels
  			l_car.setBounds(x_car,y_car,57,25);
  			l_bg.add(l_car);									// car labels
  			l_word = new JLabel();
  			l_word.setBounds(1000,100,100,100);  
			l_word.setText("wpm = "+wpm);
  			l_flag = new JLabel(new ImageIcon("flag.png"));
  			l_flag.setBounds(50,54,1870,150);
  			l_bg.add(l_flag);

  			tf_type = new JTextField(50);								//text field
  			tf_type.setBounds(370,500,600,30);
  			tf_type.requestFocus();
			
			l_player = new JLabel();
			l_player.setText("(Your Car)");
			l_player.setBounds(300,90,100,100);  
  										//adding the listeners
  			tf_type.addKeyListener(this);

  			l_bg.setLayout(null);
  			l_bg.add(l_road);
  			l_bg.add(ta_para);
  			l_bg.add(tf_type);
  			l_bg.add(l_word);
			l_bg.add(l_player);

  			frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
  			frame.setSize(1366,768); //683,384
  			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
  			frame.setVisible(true);
 		}
 		public void keyPressed(KeyEvent e)
 		{
			wpm = (count+1)*60/time;
		    	l_word.setText("wpm = "+wpm);
			
			if (e.getKeyCode() == e.VK_SPACE)
	 		{
	   			String word = tf_type.getText().trim();
	   			if(word.equals(para1[count]))
	    			{
					tf_type.setBackground(Color.white);
					//l_word.setText("wpm = "+);
	     				x_car+=11;
	    	 			l_car.setBounds(x_car,y_car,57,25);
	     				tf_type.setText("");	
	     				word="";
	     				count++;						//count = (number of words typed-1)
					if(count==para1.length)
					{
						tf_type.setEditable(false);
						tf_type.setText("Congratulation Your score = "+wpm);
					}
	     				ta_para.getHighlighter().removeAllHighlights();
	     				high_in = high_fin + 1;
  	     				high_fin = high_fin+para1[count].length()+1;
	      				try 
 	       				{											
         	         					ta_para.getHighlighter().addHighlight(high_in,high_fin, redPainter);	
        	       				} 
	       				catch (BadLocationException ble) {} 
	    			}
				else
				{
					tf_type.setBackground(Color.yellow);
				}
			}
		 }
 		public void keyReleased(KeyEvent e) {}
 		public void keyTyped(KeyEvent e) {}

}
