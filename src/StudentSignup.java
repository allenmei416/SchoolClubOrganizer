import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class StudentSignup  extends EasyApp  {
	
	Button signUpButton;
	Button back;
	
	Label title;
	Label name;
	Label email;
	Label grade;
	Label cover;
	Label password;
	Label incorrect;
	
	TextField titleEnter;
	TextField nameEnter;
	TextField emailEnter;
	TextField gradeEnter;
	TextField passwordEnter;
	
	boolean found = false;
	boolean nameCorrect = true;
	boolean emailCorrect = true;
	boolean gradeCorrect = true;
	boolean passCorrect = true;
	
	final String incorrectName = "Name is not valid.";
	final String incorrectEmail = "Email is not valid.";
	final String incorrectGrade = "Grade is not valid.";
	final String incorrectPass = "Password must be 8 chracters long.";
	String incorrectMessage = "";

	public StudentSignup() {
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Sign up");
    	
    	//setting labels
		this.title = this.addLabel("Fill in information: ", 180, 60, 800, 25, this);
		this.name = this.addLabel("Name ", 180, 117, 60, 15, this);
        this.email = this.addLabel("Email ", 180, 147, 60, 15, this);
        this.grade = this.addLabel("Grade ", 180, 177, 60, 15, this);
        this.password = this.addLabel("Password ", 180, 207, 60, 15, this);
        
        //setting text fields
        this.nameEnter = this.addTextField("", 300, 117, 165, 25, this);
        this.emailEnter = this.addTextField("", 300, 147, 165, 25, this);
        this.gradeEnter = this.addTextField("", 300, 177, 165, 25, this);
        this.passwordEnter = this.addTextField("", 300, 207, 165, 25, this);
        
        //setting buttons
    	this.signUpButton = this.addButton("Sign up",180,270,100,30, this);
        this.title.setFont(new Font("Arial", 0, 30));
        this.back = this.addButton("Back", 5, 37, 60, 30, this);
	}
	
    @Override
    public void actions(final Object source, final String command) throws IOException, ClassNotFoundException {
    	
    	//sign up button requested
    	if (source == this.signUpButton) {
    		if (checkInfo() == true) {
    			signStudentUp();
    			this.dispose();
	        	new Login("s");
    		} else {
    			if (nameCorrect == false)
        			incorrectMessage += incorrectName + "\n" ;
        		if (emailCorrect == false)
        			incorrectMessage += incorrectEmail + "\n" ;
        		if (gradeCorrect == false)
        			incorrectMessage += incorrectGrade + "\n" ;
        		if (passCorrect == false)
        			incorrectMessage += incorrectPass;
        		outputString(incorrectMessage);
        	}
        }
    	
    	nameCorrect = true;
		emailCorrect = true;
		gradeCorrect = true;
		passCorrect = true;
		incorrectMessage = "";
		
        if (source == this.back) {
        	this.dispose();
        	new Login("s");
        }
	}
	
    //checking user info reaches requirements
    public boolean checkInfo() {
    	boolean correct = true;
    	final String checkName = this.nameEnter.getText();
    	final String checkUserEmail = (this.emailEnter.getText()).toLowerCase();
		int checkCurrentGrade = 0;
    	
		try {
	    	checkCurrentGrade = Integer.parseInt(this.gradeEnter.getText());
		} catch(Exception e){
			gradeCorrect = false;
		}
		
    	final String checkUserPass = this.passwordEnter.getText();
    	
    	for (int i = 0; i < checkName.length(); i++) {
    		if ((Character.isLetter(checkName.charAt(i)) == false && checkName.charAt(i) != ' ')) {
    			nameCorrect = false;
    		}
    	}
    	
    	if (checkUserEmail.contains("@student.tdsb.on.ca") == false) {
        	emailCorrect = false;
        }
    	
    	 for (int i = 0; i < checkUserEmail.length(); i++) {
                if ((checkUserEmail.charAt(i) == ' ')) {
                	emailCorrect = false;
                }
         }
    	 
    	 for (int i = 0; i < checkUserEmail.length(); i++) {
    		 
             if (checkUserEmail.charAt(i) == '@') {
    			 found = true;
            	 for (int j = i+1; j <checkUserEmail.length(); j ++) {
            		 if (checkUserEmail.charAt(j) == '@') {
            			 emailCorrect = false;
            		 }
            	 }
             }
             
             if (i == checkUserEmail.length() - 1 && checkUserEmail.charAt(i) != '@' && found == false) {
            	 emailCorrect = false;
             }
    	 }
    	 
    	 if(checkCurrentGrade < 9 || checkCurrentGrade > 12) {
    		 gradeCorrect = false;
    	 }
     	 
    	 if (checkUserPass.length() < 8) {
    		 passCorrect = false;
    	 }
    		
    	 if (passCorrect == false || gradeCorrect == false || emailCorrect == false || nameCorrect == false)
    		 return false;
    	 return true;
    }
    
    //student sign up info stored in students.txt
    void signStudentUp() throws ClassNotFoundException, IOException{
    	Runner r = Runner.getInstance();
    	final String fullName = this.nameEnter.getText();
    	final String userEmail = (this.emailEnter.getText()).toLowerCase();
    	final int currentGrade = Integer.parseInt(this.gradeEnter.getText());
    	final String userPass = this.passwordEnter.getText();
    	
    	r.studentSignup(r.makeNewID(),fullName, userEmail, userPass,currentGrade);
    }
}


