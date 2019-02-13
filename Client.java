import java.io.*;
import java.net.*;
public class Client implements Runnable
{
    PrintStream streamToServer;
    BufferedReader streamFromServer;
    Socket toServer;
	Thread thread;
	String str;
	public static int location=370,wppm=0;
    public Client()
    {
       		connectToServer();
		thread = new Thread(this);
		thread.start();
    }
    private void connectToServer()
    {
        try{
            toServer = new Socket("169.254.159.123",4002);
            streamFromServer = new BufferedReader(new InputStreamReader((toServer.getInputStream())));
            streamToServer = new PrintStream(toServer.getOutputStream());
        }
        catch(Exception e)
        {
                System.out.println("ni ho pa rha server se connect");
        }       
    }
	public void run() {
		while(true) {
			try {
				str = MultiPlayerC.x_car + " " + MultiPlayerC.wpm;
				streamToServer.println(str);
				String yak[] = streamFromServer.readLine().split(" ");
				location = Integer.parseInt(yak[0]);
				wppm = Integer.parseInt(yak[1]);
			}
			catch(Exception e){
				System.out.println("thread run mein dikkat");
			}
		}
	}	
    public static void main(String args[])
    {
        new Client();
    }
}