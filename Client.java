package client;

import java.net.*;
import model.Automotive;
import java.io.*;
//simple client class derived from DSS
public class Client extends Thread implements SocketClientInterface, SocketClientConstants {
	
	private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket sock;
    private String strHost;
    private int iPort;
    private BufferedReader buff;
    private Object objCheck; //an object that gets information from the server
    boolean looping;

    public Client(String strHost, int iPort) {
    	System.out.println("Client made!");
    	setPort(iPort);
		setHost(strHost);
		objCheck = null;
    }

    public void run(){
    	System.out.println("Running client thread");
    	if (openConnection()){
    		handleSession();
    		closeSession();
    	}
    }

    public boolean openConnection(){
    	try {
    		System.out.println("Testing socket");
    		sock = new Socket(strHost, iPort);
    		System.out.println("Socket works");
    	}catch(IOException socketError){
    		if (DEBUG) 
    			System.err.println("Unable to connect to " + strHost);
    	    return false;
    	}
    	try {
    		System.out.println("Obtaining server stream");
    		output = new ObjectOutputStream(sock.getOutputStream());
    		System.out.println("Obtaining server stream");
    		input = new ObjectInputStream(sock.getInputStream());
    	    System.out.println("Obtaining server stream");
    	    buff = new BufferedReader(new InputStreamReader(System.in));
    	    System.out.println("Server stream obtained!");
    	   }catch (Exception e){
    		   if(DEBUG) 
    			   System.err.println("Unable to obtain stream to/from " + strHost);
    		   return false;
    	   }
    	return true;
    }

    public void handleSession(){
    	if (DEBUG) System.out.println ("Handling session with " + strHost + ":" + iPort);
    	serverInput();
    	looping = true;
    	do{
    		handleInput();
    	}while(looping);
    	System.out.println("It's over for good -Client");
    }       

    public void sendServer(Object Output){
    	try {
    		output.writeObject(Output);
    	}catch (IOException e){
    		if (DEBUG) 
    			System.out.println("Error writing to " + strHost);
    	}
    }
    
    public void serverInput(){
		try {
			objCheck = input.readObject();
		}catch (ClassNotFoundException | IOException e) {
			System.out.println("Error --- " + e.toString());
		}
	}
    //go through user/server input and process the command
    @SuppressWarnings("unchecked")
	public void handleInput(){
    	int operation = 0;
    	System.out.println("Enter 1 to upload properties, 2 to print an auto, 3 is nothing, -1 ends");
    	try {
			operation = Integer.parseInt(buff.readLine());
			sendServer(operation);
		} catch (NumberFormatException e) {
			System.out.println("Error --- " + e.toString());
		} catch (IOException e) {
			System.out.println("Error --- " + e.toString());
		}
    	switch(operation){
    	case 1:
    		System.out.println("Operation 1 from client. Enter path for property file");
    		//String propFile = "src/util/carProp.txt";
    		CarModelOptionsIO cm = new CarModelOptionsIO();
    		try {
				sendServer(cm.uploadProp(buff.readLine()));
			} catch (IOException e) {
				System.out.println("Error --- " + e.toString());
			}
    		System.out.println("Sending auto properties");
    		break;
    	case 2:
    		System.out.println("Here's all the car models -Client");
    		serverInput();
    		System.out.println((String)objCheck);
    		System.out.println("Which car do you want to see? -Client");
    		try {
				sendServer(buff.readLine());
				System.out.println("Sent the model -Client");
			} catch (IOException e) {
				System.out.println("Error --- " + e.toString());
			}
    		serverInput();
    		System.out.println("Got the car for option configue -Client");
    		@SuppressWarnings("rawtypes")
			SelectCarOption sc = new SelectCarOption(); 
    		//System.out.println(((Automotive)objCheck).print());
    		sc.configureAuto((Automotive)objCheck);
    		break;
    	case -1:
    		System.out.println("It's over -Client");
    		looping = false;
    		break;
    	default:
    		System.out.println("Enter a proper operation -Client");
    		break;
    	}
    		
    }
    
    public void closeSession(){
    	try {
    		output = null;
    		input = null;
    		sock.close();
    	} catch (IOException e){
    		if (DEBUG) System.err.println("Error closing socket to " + strHost);
    	}       
    }

    public void setHost(String strHost){
        this.strHost = strHost;
	}

	public void setPort(int iPort){
        this.iPort = iPort;
	}

}
