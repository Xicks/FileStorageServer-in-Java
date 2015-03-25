import java.io.*;
import java.net.*;

/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Class: FileStorageHandler
 * Thread that handles each connection to the server. Receives a File from the client
 * and saves in the server.
 */
 
public class FileStorageHandler implements Runnable{

    private Socket s;
	
    //************************************ReverseHandler()************************************//
    public FileStorageHandler(Socket s)
    {
        this.s = s;
    }
    //************************************reverse()*******************************************//
    
    //************************************run()***********************************************//
    @Override
    public void run() {
        try {
            try{
                InputStream in = s.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(isr);
		String filename = reader.readLine();
		System.out.println(s.getRemoteSocketAddress() + " -> Receiving file: " + filename);
		File file = new File(filename);
		FileOutputStream out = new FileOutputStream(file);
		int c;
		while ((c = in.read()) != -1) {
                    out.write(c);
		}
				System.out.println(s.getRemoteSocketAddress() + " -> " + file.length() + " bytes received");
                in.close();
                isr.close();
                reader.close();
                out.close();
            }catch(SocketException e){
                System.err.println(e);
            }finally {s.close();}
        }catch(IOException e){
            System.err.println(e);
        }
        System.out.println(s.getRemoteSocketAddress() + " Connection ended!");
    }
}
