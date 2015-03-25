import java.io.*;
import java.net.*;
import javax.swing.JFileChooser;
/**
 * Author: Leonardo Schick
 * Date: 03/17/2015
 * Class: FileStorage
 * This class opens a connection with the server using a socket to send a file.
 */
 
public class FileStorageClient {
	
    //************************************main()**********************************************//
    public static void main(String[] args) throws Exception
    {
        File file = null;
        if(args.length > 0){
            file = new File("./" + args[0]);
        }else{
            JFileChooser jf = new JFileChooser();
            if(jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                file = jf.getSelectedFile();
            }else{
                return;
            }
        }
        if(file == null || !file.exists())
        {
            System.err.println("File not found!");
            return;
        }
       	Socket s;
         try{
            try{
                String computerName = InetAddress.getLocalHost().getHostName();
		//Creates a socket using the port 16001
		s = new Socket(computerName,16001);
                FileInputStream in = new FileInputStream(file);
                OutputStream out = s.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(out);
                BufferedWriter writer = new BufferedWriter(osw);
				System.out.println("Sending " + file.getName() + " (" + file.length()+ " bytes)");
		writer.write(file.getName()+"\n");
		writer.flush();
		int c;
		while ((c = in.read()) != -1) {
                    out.write(c);
		}
		System.out.println("File sent...");
                out.close();
                osw.close();
                writer.close();
                s.close();
            }catch(SocketException e){
                System.err.println(e);
            }
        }catch(IOException e){
            System.err.println(e);    
        }
    }		
}

