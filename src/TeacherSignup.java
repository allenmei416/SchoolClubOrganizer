import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;

public class TeacherSignup extends EasyApp {
	
	Button signUpButton;
	Button back;
	
	Label title;
	Label name;
	Label email;
	Label password;
	
	TextField titleEnter;
	TextField nameEnter;
	TextField emailEnter;
	TextField passwordEnter;
	
	boolean found = false;
	boolean nameCorrect = true;
	boolean emailCorrect = true;
	boolean passCorrect = true;

	final String incorrectName = "Name is not valid.";
	final String incorrectEmail = "Email is not valid.";
	final String incorrectPass = "Password must be 8 chracters long.";
	String incorrectMessage = "";

	public TeacherSignup() {
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Sign up");
        this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting labels
		this.title = this.addLabel("Fill in information: ", 180, 60, 800, 25, this);
		this.name = this.addLabel("Name ", 180, 117, 60, 15, this);
        this.email = this.addLabel("Email ", 180, 147, 60, 15, this);
        this.password = this.addLabel("Password ", 180, 177, 60, 15, this);
        
        //setting text fields
        this.nameEnter = this.addTextField("", 300, 117, 165, 25, this);
        this.emailEnter = this.addTextField("", 300, 147, 165, 25, this);
        this.passwordEnter = this.addTextField("", 300, 177, 165, 25, this);
        
        //setting buttons
    	this.signUpButton = this.addButton("Sign up",180,210,100,30, this);
        this.back = this.addButton("Back", 5, 37, 60, 30, this);
	}
	
    @Override
    public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
    	//sign up button requested by teacher
    	if (source == this.signUpButton) {
    		if (checkInfo() == true) {
    			signTeacherUp();
    			this.dispose();
	        	new Login("t");
	        } else {
				if (nameCorrect == false)
					incorrectMessage += incorrectName + "\n" ;
				if (emailCorrect == false)
					incorrectMessage += incorrectEmail + "\n" ;
				if (passCorrect == false)
					incorrectMessage += incorrectPass;
				outputString(incorrectMessage);
			}
		}

    	nameCorrect = true;
		emailCorrect = true;
		passCorrect = true;
		incorrectMessage = "";
		
        if (source == this.back) {
        	this.dispose();
        	new Login("t");
        }
    }
    
    //checking if user info fits requirements
    public boolean checkInfo() {
    	final String checkName = this.nameEnter.getText();
    	final String checkUserEmail = (this.emailEnter.getText()).toLowerCase();
    	final String checkUserPass = this.passwordEnter.getText();
    	
    	for (int i = 0; i < checkName.length(); i++) {
    		if ((Character.isLetter(checkName.charAt(i)) == false && checkName.charAt(i) != ' ')) {
    			nameCorrect = false;
    		}
    	}
    	
    	if (checkUserEmail.contains("@tdsb.on.ca") == false) {
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

    	 if (checkUserPass.length() < 8) {
    		 passCorrect = false;
    	 }
    		
    	 if (passCorrect == false || emailCorrect == false || nameCorrect == false)
    		 return false;
    	 return true;	 
    }
    
    //storing teacher info in text files
    void signTeacherUp() throws ClassNotFoundException, IOException{
    	Runner r = Runner.getInstance();
    	final String fullName = this.nameEnter.getText();
    	final String userEmail = (this.emailEnter.getText()).toLowerCase();
    	final String userPass = this.passwordEnter.getText();
    	
    	r.teacherSignup(r.makeNewID(),fullName, userEmail, userPass);
    }
}


