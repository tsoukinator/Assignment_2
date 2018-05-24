package Objects;
// Save as file Account.java

import java.util.ArrayList;
import java.util.List;

public abstract class User {

		private int accID;
		private String Name;
		private int Age; 
		private String Status;
		private String Image;
		private String Gender;
		private String State;

		public User(int accID, String Name, int Age, String Status, String Image, String Gender, String State) {
			this.setID(accID);
			this.setName(Name);
			this.setAge(Age);
			this.setStatus(Status);
			this.setImage(Image);
			this.setGender(Gender);
			this.setState(State);
		}
		
		public int getID() {
	        return accID;
	    }

	    public void setID(int accID) {
	        this.accID = accID;
	    }

	    public String getName() {
	        return Name;
	    }
	    
	    public void setName(String FName) {
	        this.Name = FName;
	    }
	    
	    public int getAge() {
	        return Age;
	    }

	    public void setAge(int Age) {
	        this.Age = Age;
	    }
	    
	    public String getStatus() {
	    	return Status;
	    }
	    
	    public void setStatus(String Status) {
	    	this.Status = Status;
	    }
	    
	    public String getImage() {
	    	return Image;
	    }
	    
	    public void setImage(String Image) {
	    	this.Image = Image;
	    }
	    
	    public String getGender() {
	    	return Gender;
	    }
	    
	    public void setGender(String Gender) {
	    	this.Gender = Gender;
	    }
	    
	    public String getState() {
	    	return State;
	    }
	    
	    public void setState(String State) {
	    	this.State = State;
	    }	    
   
}
