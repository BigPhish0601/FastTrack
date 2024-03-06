
public class Device {

	private int deviceID; //holds the device number
	private int bagNumber; //hold the bags number 
	private String ownersFirst; //hold the bag owners fist name
	private String ownersLast; //holds the bag owners last name
	
	//constructor
	public Device(int id, int bag, String fName, String lName){
		deviceID = id;
		bagNumber = bag;
		ownersFirst = fName;
		ownersLast = lName;
	}//end constructor
	
	public int getDeviceID(){
		return deviceID;
	}
	
	public int getBagNumber(){
		return bagNumber;
	}
	
	public String getFirstName(){
		return ownersFirst;
	}
	
	public String getLastName(){
		return ownersLast;
	}
}
