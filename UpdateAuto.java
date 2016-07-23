package adapter;

public interface UpdateAuto {
	
	public void updateOptionSetName(String ModelName, String OptionSetname, String newname);

	public boolean updateOptionPrice(String Modelname, String OptionName, String Option, float newprice);
	
}
