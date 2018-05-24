package Objects;

public class Relation implements Comparable {

		private int ID;
    	private String Subject;
    	private String Prospect;
    	private String Relation;

    	
    	public Relation(int ID, String Subject, String Prospect, String Relation) {
    		this.setID(ID);
    		this.setSubject(Subject);
    		this.setProspect(Prospect);
    		this.setRelation(Relation);
    	}

    	public void setID(int ID) {
    	    this.ID = ID;
    	}

    	public int getID() {
    		return ID;
    	}	    	
    	
    	public String getSubject() {
    		return Subject;
    	}
    	
    	public void setSubject(String Subject) {
    	    this.Subject = Subject;
    	}

    	public String getProspect() {
    		return Prospect;
    	}
    	
    	public void setProspect(String Prospect) {
    	    this.Prospect = Prospect;
    	}

    	public String getRelation() {
    		return Relation;
    	}

    	public void setRelation(String Relation) {
    	    this.Relation = Relation;
    	}

		@Override
		public int compareTo(Object arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

}