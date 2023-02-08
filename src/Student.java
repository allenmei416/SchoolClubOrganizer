import java.io.Serializable;
import java.util.ArrayList;

public class Student extends  Person implements Serializable{

    private int grade;
    private ArrayList<Integer> memberOf = new ArrayList<>();

    public Student(String name, String password, int id, String email, int grade) {
        super(id, name, email, password);
        this.grade = grade;
    }
    
    //adding club to your clubs
    public void addClub(int id){
        this.memberOf.add(id);
    }
    
    //getters and setters
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}

	public ArrayList<Integer> getMemberOf() {
		return memberOf;
	}
    
}