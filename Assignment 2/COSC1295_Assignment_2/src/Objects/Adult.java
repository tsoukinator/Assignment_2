package Objects;

import java.util.ArrayList;

public class Adult extends User {
	private int partnerID;
	private ArrayList<Integer> Friends = new ArrayList<Integer>();
	private ArrayList<Integer> Children = new ArrayList<Integer>();
	private ArrayList<Integer> Classmates = new ArrayList<Integer>();
	private ArrayList<Integer> Colleagues = new ArrayList<Integer>();

	public Adult(int accID, String Name, int Age, String Status, String Image, String Gender, String State, int partnerID, ArrayList<Integer> Friends, ArrayList<Integer> Children, ArrayList<Integer> Classmates, ArrayList<Integer> Colleagues) {
	        super(accID, Name, Age, Status, Image, Gender, State);

	this.setPartner(-1);       
	}

    public void setFriend(int f1) {
    	this.Friends.add(f1);
    }
    
    public ArrayList<Integer> getFriends() {
    	return Friends;
    }
    
    public void setFriends(ArrayList<Integer> Friends) {
    	this.Friends = Friends;
    }
    
        public void setChildren(ArrayList<Integer> Children) {
        	this.Children = Children;
    }
        public ArrayList<Integer> getChildren() {
        	return Children;
        }    
    
        public void setChild(int ch1) {
        	this.Children.add(ch1);
    }
        
    	public ArrayList<Integer> getClassmates() {
        	return Classmates;
    }
        
        public void setClassmate(int c1) {
        	this.Classmates.add(c1);
    }
        
    	public ArrayList<Integer> getColleagues() {
        	return Colleagues;
    }
        
        public void setColleague(int w1) {
        	this.Colleagues.add(w1);
    } 
    
        public void setPartner(int partnerID) {
        	this.partnerID = partnerID;
        }
        
	    public int getPartner() {
	        return partnerID;
	    }

}