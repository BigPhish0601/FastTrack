
public class User {
	private String userName;
	private String password;

	// constructor
	public User(String name, String pswrd) {
		// set the user object name and password
		userName = name;
		password = pswrd;
	}// end constructor

	public String getUserName() {
		return userName;
	}// end method getUserName

	public String getPassword() {
		return password;
	}
}
