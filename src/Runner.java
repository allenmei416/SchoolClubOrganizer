import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Runner {

    private static final String TF = "teachers.txt";
    private static final String SF = "students.txt";
    private static final String CF = "clubs.txt";

    ArrayList<Student> students = new ArrayList<>();
    ArrayList<Teacher> teachers = new ArrayList<>();
    ArrayList<Club> clubs = new ArrayList<>();
    
    //loading textfile data into ArrayLists
    protected Runner() throws IOException, ClassNotFoundException {
        loadDBData();
    }

    private static Runner instance = null;
    
    //allows methods outside of Runner class to access methods of Runner class
    public static Runner getInstance() throws ClassNotFoundException, IOException {
    	if (instance == null) {
    		instance = new Runner();
    	}
    	return instance;
    }

    private void loadDBData() throws IOException, ClassNotFoundException {
        loadStudentFromDB();
        loadTeacherFromDB();
        loadClubFromDB();
    }
    
    //loading students.txt content into ArrayList
    //if text file does not exist, it will be created 
    private boolean loadStudentFromDB() throws ClassNotFoundException {
        try (FileInputStream fis = new FileInputStream(SF)) {
            ObjectInputStream ois = new ObjectInputStream(fis);

            Student obj = null;

            boolean isExist = true;

            while (isExist) {
                if (fis.available() != 0) {
                    obj = (Student) ois.readObject();
                    this.students.add(obj);
                } else {
                    isExist = false;
                }
            }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    
    //loading teachers.txt content into ArrayList
    //if text file does not exist, it will be created 
	private boolean loadTeacherFromDB() throws ClassNotFoundException, IOException {
        
        try (FileInputStream fis = new FileInputStream(TF);) {
        	ObjectInputStream ois = new ObjectInputStream(fis);
        	
        	 Teacher obj = null;
        	 boolean isExist = true;
        	 
        	 while(isExist){
                 if (fis.available() != 0){
                     obj = (Teacher) ois.readObject();
                     this.teachers.add(obj);
                 }else{
                     isExist = false;
                 }
             }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
	
	//loading clubs.txt content into ArrayList
    //if text file does not exist, it will be created 
	private boolean loadClubFromDB() throws ClassNotFoundException, IOException {
		try (FileInputStream fis = new FileInputStream(CF);) {
        	ObjectInputStream ois = new ObjectInputStream(fis);
        	
        	 Club obj = null;
        	 boolean isExist = true;

        	 while(isExist){
        		 if (fis.available() != 0){
        			 obj = (Club) ois.readObject();
        			 this.clubs.add(obj);
        		 } else{
        			 isExist = false;
        		 }
        	 }
        } catch (FileNotFoundException e) {
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

	public void teacherSignup(int id, String name, String email, String password) throws IOException {
        this.teachers.add(new Teacher(id, name, email, password));
        saveTeacherToDB();
    }
	
	//saving ArrayList contents to teachers.txt
    private void saveTeacherToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(TF));
        ObjectOutputStream o = new ObjectOutputStream(f);

        for (Teacher t: this.teachers){
            o.writeObject(t);
        }
        o.close();
        f.close();
    }

    public void studentSignup(int id, String name, String email, String password, int grade) throws IOException {
        this.students.add(new Student(name, password, id, email, grade));
        saveStudentToDB();
    }
    
    //saving ArrayList contents to students.txt
    private void saveStudentToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(SF));
        ObjectOutputStream o = new ObjectOutputStream(f);

        for (Student s: this.students){
            o.writeObject(s);
        }
        o.close();
        f.close();
    }

    public int TeacherLogin(String email, String password){
        for (Teacher t : this.teachers){
            if (t.getEmail().equals(email)  && (t.getPassword().equals(password))){
                return t.getId();
            }
        }
        return -1;
    }

    public int StudentLogin(String email, String password){
        for (Student s : this.students){
            if (s.getEmail().equals(email)  && (s.getPassword().equals(password))){
                return s.getId();
            }
        }
        return -1;
    }

    public void createClub (int teacherId, String clubName, String clubDescription, int managerId) throws IOException {
        this.clubs.add(new Club(teacherId, clubName, clubDescription, managerId));
        saveClubToDB();
    }
    
    //saving ArrayList contents to clubs.txt
    private void saveClubToDB() throws IOException {
        FileOutputStream f = new FileOutputStream(new File(CF));
        ObjectOutputStream o = new ObjectOutputStream(f);

        for (Club t: this.clubs){
            o.writeObject(t);
        }
        o.close();
        f.close();
    }
    
    // Student Methods
    
    public int getStudentObjectID(String inputEmail){
        for (Student student : students) {
            if (student.getEmail().compareTo(inputEmail) == 0){
                return student.getId();
            }
        }
        return 0;
    }
    
    public Student getStudentObjectByID(int studentId){
        for (Student student : students) {
            if (student.getId() == studentId){
                return student;
            }
        }
        return null;
    }
    
    public String getStudentObjectName(int studentId){
        for (Student student : students) {
            if (student.getId() == studentId){
                return student.getName();
            }
        }
        return null;
    }
    
    public String getStudentObjectEmail(int studentId){
        for (Student student : students) {
            if (student.getId() == studentId){
                return student.getEmail();
            }
        }
        return null;
    }
    
    public String getStudentObjectGrade(int studentId){
        for (Student student : students) {
            if (student.getId() == studentId){
                return String.valueOf(student.getGrade());
            }
        }
        return null;
    }
    
    public int sizeYourClubs (int studentId){
        Student tempStu = getStudentObjectByID(studentId);
        return (tempStu.getMemberOf()).size();
    }
    
    public void addClubToYouClubs (int studentId, int clubId) throws IOException {
        for (Student selectedStudent : students){
            if (selectedStudent.getId() == studentId){
                selectedStudent.addClub(clubId);
                saveStudentToDB();
            }
        }
    }
    
    public void removeClubFromYouClubs (int studentId, int clubId) throws IOException {
        for (Student selectedStudent : students){
            if (selectedStudent.getId() == studentId){
            	int place = (selectedStudent.getMemberOf()).indexOf(clubId);
                selectedStudent.getMemberOf().remove(place);
                saveStudentToDB();
            }
        }
    }
    
    public String[][] arrYourClubs (int studentId){
        Student tempStu = getStudentObjectByID(studentId);
        String[][] outputArray = new String[(tempStu.getMemberOf()).size()][2];

        for (int i = 0; i  < (tempStu.getMemberOf()).size(); i++) {
            if (tempStu.getId() == studentId) {
                Club tempClub = getClubObjectByID((tempStu.getMemberOf()).get(i));
                outputArray[i][0] = tempClub.getClubName();
                outputArray[i][1] = getClubOperatingTimesByID(tempClub.getId());
            }
        }
        return outputArray;
    }
    
    //teachers method
    
    public int getTeacherObjectID(String inputEmail){
    	for (Teacher teacher : teachers) {
            if (teacher.getEmail().compareTo(inputEmail) == 0){
                return teacher.getId();
            }
        }
        return 0;
    }

    public String getTeacherObjectEmail(int userId){
        for (Teacher teacher : teachers) {
            if (teacher.getId() == userId) {
                return teacher.getEmail();
            }
        }
        return null;
    }
    
    public String getTeacherObjectName(int teacherId){
    	for (Teacher teacher : teachers) {
            if (teacher.getId() == teacherId){
                return teacher.getName();
            }
        }
        return null;
    }
    
    //clubs method
    
    public void addMemberToClub (int studentId, int clubId) throws IOException {
        for (Club selectedClub : clubs){
            if (selectedClub.getId() == clubId){
                selectedClub.getMembers().add(studentId);
                saveClubToDB();
            }
        }
    }

    public void removeMemberFromClub (int studentId, int clubId) throws IOException {
        for (Club selectedClub : clubs){
            if (selectedClub.getId() == clubId){
            	int place = selectedClub.getMembers().indexOf(studentId);
                selectedClub.removeMember(place);
                saveClubToDB();
            }
        }
    }

    public boolean addClubOperatingTime(String dayString, String spString, int ClubID) throws IOException {
        DAYS day = DAYS.valueOf(dayString);
        SCHOOLPERIOD sp = SCHOOLPERIOD.valueOf(spString);
        for (Club club : clubs) {
            if (club.getId() == ClubID) {
                club.addOperatingTime(day, sp);
                saveClubToDB();
                return true;
            }
        }
        return false;
    }

    public boolean removeClubOperatingTime(String dayString, String spString, int ClubID) throws IOException {
        DAYS day = DAYS.valueOf(dayString);
        SCHOOLPERIOD sp = SCHOOLPERIOD.valueOf(spString);
        for (Club club : clubs) {
            if (club.getId() == ClubID) {
                club.removeOperatingTime(day, sp);
                saveClubToDB();
                return true;
            }
        }
        return false;
    }

    public String getDaysStringRepresentation(){
        StringBuilder days = new StringBuilder();

        for (DAYS day : DAYS.values()){
            days.append(day.toString());
            days.append("|");
        }
        days.setLength(days.length() - 1);

        return days.toString();
    }

    public String getSchoolPeriodStringRepresentation(){
        StringBuilder sps = new StringBuilder();

        for (SCHOOLPERIOD sp : SCHOOLPERIOD.values()){
            sps.append(sp.toString());
            sps.append("|");
        }
        sps.setLength(sps.length() - 1);

        return sps.toString();
    }

    public String[][] getClubByOperatingTime(DAYS day, SCHOOLPERIOD sp){
        ArrayList<Integer> selectedClubs = new ArrayList<>();
        for (Club club : clubs){
            for (Map.Entry<DAYS, SCHOOLPERIOD> entry : club.getOperatingTimes().entrySet()){
            	if (entry.getKey() == day && entry.getValue() == sp){
                    selectedClubs.add(club.getId());
                }
            }
        }
        String[][] outputArr = new String [selectedClubs.size()][2];
        if (selectedClubs.size() == 0) {
        	String[][] outputEmpty = new String [1][2];
        	outputEmpty[0][0] = "";
        	outputEmpty[0][1] = "";
        	return outputEmpty;
        } else {
	        for (int i = 0; i <selectedClubs.size(); i++) {
	        	outputArr[i][0] = getClubObjectName(selectedClubs.get(i));
	        	outputArr[i][1] = getTeacherObjectName(getClubObjectTeacherId(selectedClubs.get(i)));
	        }
        }
        return outputArr;
    }
    
    public String getClubOperatingTimesByID(int clubID){
        StringBuilder clubOperatingTimes = new StringBuilder();
        for (Club club : clubs) {
            if (club.getId() == clubID){
                for (Map.Entry me: club.getOperatingTimes().entrySet()){
                    clubOperatingTimes.append(me.getKey().toString());
                    clubOperatingTimes.append(" ");
                    clubOperatingTimes.append(me.getValue().toString());
                    clubOperatingTimes.append(", ");
                }
                if (clubOperatingTimes.length() > 1)
                    clubOperatingTimes.setLength(clubOperatingTimes.length() - 1);
            }
        }
        return clubOperatingTimes.toString();
    }
    
    public Club getClubObjectByID(int clubID){
        for (Club club : clubs) {
            if (club.getId() == clubID){
                return club;
            }
        }
        return null;
    }
    
    public String getClubObjectName(int clubID){
    	for (Club club : clubs) {
            if (club.getId() == clubID){
                return club.getClubName();
            }
        }
        return null;
    }
    
    public int getClubObjectTeacherId(int clubID){
    	for (Club club : clubs) {
            if (club.getId() == clubID){
                return club.getTeacherId();
            }
        }
        return 0;
    }
    
    public String getClubObjectDesc(int clubID){
    	for (Club club : clubs) {
            if (club.getId() == clubID){
                return club.getClubDesc();
            }
        }
        return null;
    }
    
    public int getClubObjectID(String name){
    	for (Club club : clubs) {
            if (club.getClubName().compareTo(name) ==0){
                return club.getId();
            }
        }
        return 0;
    }
    
    public void editClubObjectDesc(int clubID, String newDesc) throws IOException{
    	for (Club club : clubs) {
            if (club.getId() == clubID){
                club.setClubDesc(newDesc);
            }
        }
    	saveClubToDB();
    }
    
    public void editClubObjectName(int clubID, String newName) throws IOException{
    	for (Club club : clubs) {
            if (club.getId() == clubID){
                club.setClubName(newName);
            }
        }
    	saveClubToDB();
    }

    public ArrayList<Integer> getClubsByStudentID(int studentID){
        ArrayList<Integer> studentClubs = new ArrayList<>();
        for (Club club : clubs) {
            if (club.getMembers().contains(studentID)){
                studentClubs.add(club.getId());
            }
        }
        return studentClubs;
    }
    
    public String[] toArrayFromListManage() throws IOException, ClassNotFoundException {   	
        
        String[] outputArray = new String[clubs.size()];
        for (int i = 0; i <clubs.size(); i++) {
        	outputArray[i] = clubs.get(i).getClubName();
        	int id = clubs.get(i).getTeacherId();
        	String nameOfId = "";
        	for (Teacher te : teachers){
                if (id == te.getId())
                	nameOfId = te.getName();
            }
        }
        return outputArray;
    }

    public ArrayList<Integer> getMembersByClub(int clubId){
        return clubs.get(clubId).getMembers();
    }
    
    public ArrayList<Integer> clubsByManagerID(int teacherID){
        ArrayList<Integer> selectedClubs = new ArrayList<>();
        for (Club club : clubs){
            if (club.getTeacherId() == teacherID){
                selectedClubs.add(club.getId());
            }
        }
        return selectedClubs;
    }
    
    public boolean deleteClub (String pass, int userId, int clubId) throws IOException {
        String email = getTeacherObjectEmail(userId);
        if (TeacherLogin(email, pass)!= -1){
            clubs.remove(getClubObjectByID(clubId));
            saveClubToDB();
            return true;
        }
        return false;
    }
    
    public String[][] toMembersList(int clubId) throws IOException, ClassNotFoundException {
        Club tempClub = getClubObjectByID(clubId);
        String[][] outputArray = new String[(tempClub.getMembers()).size()][3];
        for (int i = 0; i  < (tempClub.getMembers()).size(); i++) {
            outputArray[i][0] = getStudentObjectName((tempClub.getMembers()).get(i));
            outputArray[i][1] = getStudentObjectEmail((tempClub.getMembers()).get(i));
            outputArray[i][2] = getStudentObjectGrade((tempClub.getMembers()).get(i));
        }
        return outputArray;
    }

    public int sizeMembers (int clubId){
        Club tempClub = getClubObjectByID(clubId);
        return (tempClub.getMembers()).size();
    }
    
    public boolean alreadyMember(int userId, int clubId){
    	Club tempClub = getClubObjectByID(clubId);
        ArrayList<Integer> membersOfClub = new ArrayList<>();
        membersOfClub = tempClub.getMembers();

        for (int members : membersOfClub) {
            if (members == userId){
                return true;
            }
        }
        return false;
    }
    
    public int makeNewID() {    	
    	boolean unique = true;
    	int number = 0;
    	do {
    		number = (int) (Math.random()*2000);
    		for (Club club : clubs) {
                if (club.getId() == number){
                	unique = false;
                }
            }
    		for (Teacher teacher : teachers) {
                if (teacher.getId() == number){
                	unique = false;
                }
            }
    		for (Student student : students) {
                if (student.getId() == number){
                	unique = false;
                }
    		}    		
    	} while (unique == false);
    	return number;
    }
 }