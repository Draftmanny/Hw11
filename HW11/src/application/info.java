package application;

import java.io.Serializable;

public class info implements Serializable {
	String name;
	int pNumber;
	String dob;
	String email;
	
	public info(String name, int pNumber, String dob, String email) {
		super();
		this.name = name;
		this.pNumber = pNumber;
		this.dob = dob;
		this.email = email;
	}
	

	@Override
	public String toString() {
		return "info [name=" + name + ", pNumber=" + pNumber + ", dob=" + dob + ", email=" + email + "]";
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the pNumber
	 */
	public int getpNumber() {
		return pNumber;
	}


	/**
	 * @param pNumber the pNumber to set
	 */
	public void setpNumber(int pNumber) {
		this.pNumber = pNumber;
	}


	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}


	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}


	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}


	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
