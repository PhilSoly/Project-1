package adapter;

import java.io.IOException;
import java.util.Properties;
import exception.AutoException;
import model.Automotive;
import util.FileIO;
import model.Containment;
import scale.EditOptions;


public abstract class proxyAutomobile extends Thread{ //extend thread to use start() thread functions
	//create a static Automotive to be used across multiple interfaces
	private static Containment<Automotive> carModels = new Containment<>();
	
	//use a try-catch to check for any exceptions when building the file
	public void buildAuto(String fileName){
		FileIO test = new FileIO();
		try {
			Automotive a1 = test.buildAutoObject(fileName);
			carModels.addAutomotive(a1.getModel(), a1);
			System.out.println("making car " + a1.getModel());
		} catch (AutoException e) { //if an exception is found, fix it, print it, and log it
			e.printStackTrace();
			System.out.println("Attempt to fix");
			e.fix(e.getErrorNum());
			e.log();
		}
	}
	public void buildAutoProp(Properties prop){
		FileIO test = new FileIO();
		try {
			Automotive a1 = test.buildAutoProp(prop);
			carModels.addAutomotive(a1.getModel(), a1);
			System.out.println("making car " + a1.getModel());
		} catch (AutoException e) { //if an exception is found, fix it, print it, and log it
			e.printStackTrace();
			System.out.println("Attempt to fix");
			e.fix(e.getErrorNum());
			e.log();
		}
	}
	//a print function that can be used from the interface
	public void printAuto(String modelName){
		carModels.printAutomotive(modelName);
	}
	public Automotive getAutomotive(String modelName){
		return carModels.getAutomotive(modelName);
	}
	//print all models
	public String getAutoModel(){
		return carModels.getCarModels();
	}
	//allow the user to change an optionset name
	public void updateOptionSetName(String ModelName, String OptionSetName, String newName){
		carModels.updateOptionSetName(ModelName, OptionSetName, newName);
	}
	//allow the user to change an option price: object locking
	//also returns a boolean depending on whether or not the update option was successful
	public boolean updateOptionPrice(String ModelName, String OptionSetName, String OptionName, float newPrice){
		boolean done = false;
		//try to update, if not, return false and have the thread wait
		try{
			carModels.updateOptionPrice(ModelName, OptionSetName, OptionName, newPrice);
			done = true;
		}catch (Exception e){
			System.out.println("Exception when updating Option + e: " + e.toString());
		}
		return done;
	}
	//allow the user to fix an automobile error, given the user knows the errornum
	public void fixAuto(int errornum){
		AutoException e = new AutoException("Fix Auto", errornum);
		e.fix(errornum);
	}
	//interface for option
	public void getOptionChoice(String modelName, String setName){
		carModels.getAutomotive(modelName).getOptionChoiceName(setName);
	}
	
	public void setOptionChoice(String modelName, String setName, String optionName){
		carModels.getAutomotive(modelName).setOptionChoice(setName, optionName);
	}
	//create an editoptions with all the proper parameters for updating an option, then start the thread
	public void editOptionPrice(String Modelname, String OptionName, String Option, float newprice, int threadno){
		EditOptions e = new EditOptions(Modelname, OptionName, Option, newprice, threadno);
		e.start();
	}
	
	public Properties uploadProp(String filename) throws IOException{
		FileIO get = new FileIO();
		return get.makeProp(filename);
	}
	
	
}
