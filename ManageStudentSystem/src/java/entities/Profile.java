/**
 * 
 */
package entities;

/**
 * @author CuongDH
 *
 */
public class Profile {
	
	private int profileId;
	private String firstname;
	private String middlename;
	private String lastname;
	private String dob;
	private int accountId;
	/**
	 * @return the profileId
	 */
	public int getProfileId() {
		return profileId;
	}
	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the middlename
	 */
	public String getMiddlename() {
		return middlename;
	}
	/**
	 * @param middlename the middlename to set
	 */
	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
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
	 * @return the accountId
	 */
	public int getAccountId() {
		return accountId;
	}
	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	
}
