package Objects;

import java.util.ArrayList;

public class Infant extends User {
	private ArrayList<Integer> Parents = new ArrayList<Integer>();
	
	public Infant(int accID, String Name, int Age, String Status, String Image, String Gender, String State, ArrayList<Integer> Parents) {
	        super(accID, Name, Age, Status, Image, Gender, State);
	        // this.setParents(Parents);
	        }
		// TODO Auto-generated constructor stub
	
	public ArrayList<Integer> getParents() {
    	return Parents;
    }
    
    public void setParents(ArrayList<Integer> Parents) {
    	this.Parents = Parents;
    }
    
    public void setParent(int p1) {
    	this.Parents.add(p1);
    }   
	
}
