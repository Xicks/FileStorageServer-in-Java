import java.io.IOException;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Class: FileStorageServer
 * This class receives the connection and starts a thread to handle it.
 * This server is multiuser
 */
 
public class FileStorageServer {

    //************************************main()**********************************************//
    public static void main(String[] args) {
        final int port = 16001;
    	try(ServerSocket ss = new ServerSocket(port))
    	{
			System.out.println("Server running on port: " + port);
            while(true)
            {
                Socket s = ss.accept();	
                Runnable r = new FileStorageHandler(s);
				System.out.println(s.getRemoteSocketAddress() + " Connection started!");
				new Thread(r).start();	
            }
    	}catch(IOException e){
        	System.err.println(e.getMessage());
    	}
    }
}
