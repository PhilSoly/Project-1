package driver;
import java.net.InetAddress;
import java.net.UnknownHostException;

//import adapter.*;
import client.Client;
import exception.AutoException;
import server.Server;

public class Driver {
	
	public static void main(String[] args) throws AutoException{	
		//create an instance of FileIO in order to use all the methods to create an automotive from the text file, along with serialization/deserialization
		//CreateAuto a1 = new BuildAuto();
		//a1.buildAuto("src/util/carOptions.txt");
		//a1.buildAutoProp("propertiesFile/carProp.txt");
		//a1.printAuto("Wagon Focus");
		//UpdateAuto a2 = new BuildAuto();
		//a2.updateOptionSetName("FWF", "Color", "Bucks");
		//a2.updateOptionPrice("FWF", "Transmission", "manual", 900);
		//call two editoptions to simulate two threads at once accessing the same option
		/*
		EditAuto a3 = new BuildAuto();
		a3.editOptionPrice("Wagon Focus", "Transmission", "manual", 1545, 1);
		EditAuto a4 = new BuildAuto();
		a4.editOptionPrice("Wagon Focus", "Transmission", "manual", 8985, 2);
		a1.printAuto("Wagon Focus");
		*/
		//CreateAuto a3 = new BuildAuto();
		//a3.buildAuto("src/util/carOptions2.txt");
		//a3.printAuto("This model");
		try{ //make server and client on the same port
			int PORT = 322;
			Server s = new Server(PORT);
			Client c = new Client(InetAddress.getLocalHost().getHostName(), PORT);
			s.start();
			c.start();
		}catch (UnknownHostException e){
			System.err.println ("Unable to find local host");
		}
	}
}