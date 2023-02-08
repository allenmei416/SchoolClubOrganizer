import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.io.IOException;

public class TeachersHomepage extends EasyApp {

	Label title;
	
	Button manageClubs;
	Button createAClub;
	Button logout;
	Button timetable;
	
	int idUser;
	
    public TeachersHomepage (int idUser) { 	
    	this.idUser = idUser;
    	this.setBounds(50, 50, 800, 500);
    	this.setTitle("Teacher Home");
    	
    	this.title = this.addLabel("Welcome Teachers!", 267, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting buttons
    	this.manageClubs = this.addButton("Manage Clubs",185, 190, 180, 80, this);
    	this.createAClub = this.addButton("Create a Club", 425, 190, 180, 80, this);
    	this.timetable = this.addButton("Timetable", 185, 300, 180, 40, this);
    	this.logout = this.addButton("Log out", 425, 300, 180, 40, this);
    }
    
    @Override
    public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
        
    	//create a club requested
        if (source == this.createAClub) {
        	this.dispose();
            new CreateClub(idUser);
        }
        
        //managing a club requested
        if (source == this.manageClubs) {
        	this.dispose();
        	new ManageClub(idUser);
        }
        
        //timetable requested
        if (source == this.timetable) {
        	this.dispose();
        	new Timetable("t", idUser);
        }
        
        //logout requested
        if (source == this.logout) {
        	this.dispose();
            new SCOP();
        }
    }
}
