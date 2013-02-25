package adjuggler.test.fixture;

public class Person {
	protected String firstName;
	protected String lastName;
	protected String rank;
	protected Long serialNumber;

	public Person(){
	}

	public Person(String firstName, String lastName, String rank, Long serialNumber){
		this.firstName= firstName;
		this.lastName= lastName;
		this.rank= rank;
		this.serialNumber= serialNumber;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public Long getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Long serialNumber) {
		this.serialNumber = serialNumber;
	}	
}
