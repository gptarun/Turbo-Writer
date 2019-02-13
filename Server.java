import java.io.*;
import java.net.*;
public class Server implements Runnable
{
    static ServerSocket serverSocket;
    PrintStream streamToClient;
    BufferedReader streamFromClient;
    Socket fromClient;
    public static int location=370,wppm;
    String str;
    //static int count = 0;
    Thread thread;
    public Server()
    {
        thread = new Thread(this);
        thread.start();
    }
    public void run()
    {
        try{
            while(true)
            {
                fromClient = serverSocket.accept();
                streamFromClient = new BufferedReader(new InputStreamReader((fromClient.getInputStream())));
                streamToClient = new PrintStream(fromClient.getOutputStream());
				while(true) {
					String yak[] = streamFromClient.readLine().split(" ");
					location = Integer.parseInt(yak[0]);
					wppm = Integer.parseInt(yak[1]);
					str = MultiPlayerS.x_car + " " + MultiPlayerS.wpm;
					streamToClient.println(str);
				}
            }
        }
        catch(Exception e){
            System.out.println("Exception "+e);         
        }
        finally{
            try{
                fromClient.close();
            }
            catch(Exception e)
            {
                System.out.println("could not close connection "+e);
            }
        }
         
    }
public static void main(String args[])  
{
       try{
            serverSocket = new ServerSocket(1001);
        }
        catch(Exception e)
        {
            System.out.println("Socket could not be created"+e);
        }
    new Server();
}
}