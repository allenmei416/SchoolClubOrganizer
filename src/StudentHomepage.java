import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.io.IOException;

public class StudentHomepage extends EasyApp {
	
	Label title;
	Button yourClub;
	Button joinAClub;
	Button logout;
	Button timetable;
	int idUser;
	
    public StudentHomepage (int idUser) {
    	this.idUser = idUser;
    	this.setBounds(50, 50, 800, 500);
    	this.setTitle("Student Home");
    	
    	this.title = this.addLabel("Welcome Students!", 267, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting buttons
    	this.yourClub = this.addButton("Your Clubs",185, 190, 180, 80, this);
    	this.joinAClub = this.addButton("Join a Club", 425, 190, 180, 80, this);
    	this.timetable = this.addButton("Timetable", 185, 300, 180, 40, this);
    	this.logout = this.addButton("Log out", 425, 300, 180, 40, this);
    }
    
    @Override
    public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
    	
		//student requests to access all clubs
		if (source == this.joinAClub) {
        	this.dispose();
        	new JoinAClub(idUser);
        }
    	
		//student requests all clubs that they are a member of
    	if (source == this.yourClub) {
			if (r.sizeYourClubs(idUser) > 0) {
				this.dispose();
				new YourClubs(idUser);
			} else {
				outputString("You have no clubs.");
			}
        }
    	
    	//timetable requested
    	if (source == this.timetable) {
        	this.dispose();
        	new Timetable("s", idUser);
        }
    	
    	//log out requested
        if (source == this.logout) {
        	this.dispose();
        	new SCOP();
        }
    }
}
