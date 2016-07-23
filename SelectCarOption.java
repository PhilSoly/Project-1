package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import model.Automotive;

public class SelectCarOption<T extends Automotive> {
	//print out current auto options
	public String getAutoChoice(T T){
		return T.printOpts();
	}
	//let user configure options for an auto
	public void configureAuto(T T) {
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Here's all the option's for the auto:");
		T.print();
		System.out.println("And these are your CURRENT options:");
		System.out.println(getAutoChoice(T));
		for(int i=0; i<T.getOptSetSize(); i++){
			System.out.println("Here are the options for " + T.getOptSetName(i));
			for(int j=0; j<T.getOptSize(i); j++){
				System.out.println(j+" "+T.getOptName(i,j)+" with a price of $"+T.getOptPrice(i,j));
			}
			System.out.println("Select your new choice with the integer next to the option");
			try {
				T.setOptionChoice(i, Integer.parseInt(buff.readLine()));
			} catch (NumberFormatException | IOException e) {
				System.out.println("Error --- " + e.toString());
			}
		}
		System.out.println("Everything is set!");
	}	
	
}
