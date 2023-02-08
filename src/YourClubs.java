import java.awt.*;
import java.io.IOException;

import javax.swing.JTable;

public class YourClubs extends EasyApp{
	
	Label title;
	Label clubName;
	Label meetTime;
	Label leaveClub;
	
	Button back;
	Button leave;
	
	JTable listClubs;
	
	TextField clubNameLeave;

	int idUser;
	String [][] arrayOfClubs;
	
	public YourClubs(int idUser) throws IOException, ClassNotFoundException {
		Runner r = Runner.getInstance();
		this.idUser = idUser;
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Your Clubs");
    	this.title = this.addLabel("Your Clubs", 310, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting buttons
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
    	this.leave = this.addButton("Leave", 200, 450, 70, 30, this);
    	
		
		//setting labels
		this.clubName = this.addLabel("Club Name", 255, 185, 70, 35, this);
		this.meetTime = this.addLabel("Meet Time", 455, 185, 89, 35, this);
		this.leaveClub = this.addLabel("Leave club by entering club name:", 200, 416, 800, 35, this);
		
		//collect students clubs into an array
		arrayOfClubs = r.arrYourClubs(idUser);
		
		//setting table
		this.listClubs = this.addJTable(arrayOfClubs, 195, 230, 400, 150, this);
		
		//setting textfield
		this.clubNameLeave = this.addTextField("", 280, 450, 165, 25, this);
		
	}
	
	//Actions for user interactions
	@Override
	public void actions(final Object source, final String command) throws IOException, ClassNotFoundException {
		Runner r = Runner.getInstance();
		//back button requested
		if (source == this.back) {
			this.dispose();
			new StudentHomepage(idUser);
		}
		
		//leave club requested
		if (source == this.leave) {
			int tempId = r.getClubObjectID(this.clubNameLeave.getText());
			if (tempId != 0){
				this.dispose();
				r.removeMemberFromClub(idUser, tempId);
				r.removeClubFromYouClubs(idUser, tempId);
				new StudentHomepage(idUser);
			} else {
				outputString("Club was not found.");
			}
		}
	}
}
