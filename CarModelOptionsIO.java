package client;

import java.io.IOException;
import java.util.Properties;
import adapter.BuildAuto;

public class CarModelOptionsIO{
	//call FileIO to make a properties file to give to the server
	public Properties uploadProp(String filename) throws IOException{
		BuildAuto ba = new BuildAuto();
		return ba.uploadProp(filename);
	}
	
}
