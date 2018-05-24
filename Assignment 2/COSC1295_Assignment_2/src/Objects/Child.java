package Objects;

import java.util.ArrayList;

public class Child extends User {
	private ArrayList<Integer> Parents = new ArrayList<Integer>();
	private ArrayList<Integer> Friends = new ArrayList<Integer>();
	private ArrayList<Integer> Classmates = new ArrayList<Integer>();
	
	public Child(int accID, String Name, int Age, String Status, String Image, String Gender, String State, ArrayList<Integer> Parents, ArrayList<Integer> Friends, ArrayList<Integer> Classmates) {
        super(accID, Name, Age, Status, Image, Gender, State);

        }

	public ArrayList<Integer> getParents() {
    	return Parents;
    }
    
    public void setParents(ArrayList<Integer> Parents) {
    	this.Parents = Parents;
    }
    
    public void setParent(int p1) {
    	this.Parents.add(p1);
    }   
    
    public ArrayList<Integer> getFriends() {
    	return Friends;
    }
    
    public void setFriends(ArrayList<Integer> Friends) {
    	this.Friends = Friends;
    }
    
    public void setFriend(int f1) {
    	this.Friends.add(f1);
    }
    
	public ArrayList<Integer> getClassmates() {
    	return Classmates;
    }
    
    public void setClassmate(int c1) {
    	this.Classmates.add(c1);
    }    



}
