import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.io.IOException;

public class Login extends EasyApp{
	
	Button signUpButton;
	Button loginButton;
	Button exit;
	Button back;
	
	Label email;
	Label password;
	Label title;
	Label incorrect;
	
	TextField userEnter;
	TextField passEnter;
	
	String user;
	String username;
	int idUser;
	boolean correctInfo = false;
	
	
	public Login(String inputUser) {
		this.user = inputUser;
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Log in");
    	
    	//setting title
		this.title = this.addLabel("Sign up or log in: ", 180, 110, 800, 35, this);
		this.title.setFont(new Font("Arial", 0, 30));
		
		//setting labels
        this.email = this.addLabel("Email ", 180, 160, 60, 15, this);
        this.password = this.addLabel("Password ", 180, 200, 60, 15, this);
        
        //setting text fields
        this.userEnter = this.addTextField("", 290, 160, 165, 25, this);
        this.passEnter = this.addTextField("", 290, 200, 165, 25, this);
        
        //setting buttons
    	this.signUpButton = this.addButton("Sign up",185, 290, 100, 30, this);
    	this.loginButton = this.addButton("Log in", 185, 250, 100, 30, this);
    	this.exit = this.addButton("Exit", 503, 300, 100, 40, this);
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
        
    }
	
	@Override
	public void actions(final Object source, final String command) throws IOException, ClassNotFoundException {
		Runner r = Runner.getInstance();
		
		//depending on whether the user chose student or teacher, the correct login check will be performed
        if (source == this.loginButton) {
            //student login
        	if (user == "s") {
				checkLoginStudent();
				 if (correctInfo == false){
					 outputString("Email or password is incorrect");
				} else	{
					idUser = r.getStudentObjectID(username);
					this.dispose();
		        	new StudentHomepage(idUser);
		        }
            }            
        	
        	//teacher login
            if (user == "t") { 
            	checkLoginTeacher(); 
            	if (correctInfo == false){ 
            		outputString("Email or password is incorrect");
            	} else	{ 
            		idUser = r.getTeacherObjectID(username);
            		this.dispose(); 
            		new TeachersHomepage(idUser); 
            	} 
            }
        }
        
        //sign up button requested
        if (source == this.signUpButton) {
			if (user == "s") {
				this.dispose();
        		new StudentSignup();
			}
			
			if (user == "t") {
				this.dispose();
				new TeacherSignup();
			}
        }
        
        //back button requested
        if (source == this.back) {
        	this.dispose();
        	new SCOP();
        }
        
        //exit button requested
        if (source == this.exit) {
            System.exit(0);
        }
    }
	
	//student login requested
	void  checkLoginStudent () throws ClassNotFoundException, IOException{
		Runner r = Runner.getInstance();
		
		username = (this.userEnter.getText()).toLowerCase();
		final String password = this.passEnter.getText();
		int result = r.StudentLogin(username, password);
		
		if (result == -1)
			this.correctInfo = false;
		else
			this.correctInfo = true;
	}
	
	//teacher login requested
	void  checkLoginTeacher () throws ClassNotFoundException, IOException{
		Runner r = Runner.getInstance();
		
		username = (this.userEnter.getText()).toLowerCase();
		final String password = this.passEnter.getText();
		int result = r.TeacherLogin(username, password);
		
		if (result == -1)
			this.correctInfo = false;
		else
			this.correctInfo = true;
	}
}

