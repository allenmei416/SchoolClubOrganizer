import java.io.Serializable;
import java.util.ArrayList;

public class Teacher extends Person implements Serializable {
	
	private ArrayList<Integer> managerOf = new ArrayList<>();
	
    public Teacher(int id, String name, String email, String password) {
        super(id, name, email, password);
    }
    
    //getters and setters
	public ArrayList<Integer> getManagerOf() {
		return managerOf;
	}

	public void setManagerOf(ArrayList<Integer> managerOf) {
		this.managerOf = managerOf;
	}
	
	//adding club to clubs the teacher manages
	public void addClub(int id){
        this.managerOf.add(id);
    }
}
