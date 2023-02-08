import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Club implements Serializable {

    private int id;
    private String clubName;
    private String clubDesc;
    private int teacherId;
    private ArrayList<Integer> members = new ArrayList<>();
    private HashMap<DAYS, SCHOOLPERIOD> operatingTimes = new HashMap<>();

    public Club(int id, String clubName, String clubDesc, int teacherId) {
        this.id = id;
        this.clubName = clubName;
        this.clubDesc = clubDesc;
        this.teacherId = teacherId;
    }
    
    //manipulating members ArrayList
    public void addMember(int id){
        this.members.add(id);
    }
    
    public void removeMember (int id) {
    	this.members.remove(id);
    }

    //manipulating opperating times
    public void addOperatingTime(DAYS day, SCHOOLPERIOD period){
    	operatingTimes.put(day, period);
    }

    public void removeOperatingTime(DAYS day, SCHOOLPERIOD period){
        operatingTimes.remove(day);
    }

    //getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClubName() {
		return clubName;
	}

	public void setClubName(String clubName) {
		this.clubName = clubName;
	}

	public String getClubDesc() {
		return clubDesc;
	}

	public void setClubDesc(String clubDesc) {
		this.clubDesc = clubDesc;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public HashMap<DAYS, SCHOOLPERIOD> getOperatingTimes() {
		return operatingTimes;
	}

	public ArrayList<Integer> getMembers() {
		return members;
	}
    
}