import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextArea;
import java.io.IOException;

public class JoinAClub extends EasyApp{
	
	Choice clubList;
	
	Button choose;
	Button back;
	Button joinClub;
	
	Label clubDescription;
	Label clubMeetTimes;
	Label title;
	Label dropList;
	
	int idUser;
	int clubId;
	String allClubNames = "";
	
	public JoinAClub(int idUser) throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
		
		this.idUser = idUser;
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Join a Club");
    	
    	this.title = this.addLabel("Join a Club", 267, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//collecting all clubs into one string 
    	for (int i = 0; i < (r.toArrayFromListManage()).length; i++) {
    		allClubNames += r.toArrayFromListManage()[i] + "|";   		
		}
    	
    	//setting choice
		this.clubList = this.addChoice("Select|" + allClubNames, 270, 180, 150, 40, this);
		
		//setting labels
		this.title = this.addLabel("Join a Club", 267, 110, 800, 35, this);
		this.dropList = this.addLabel("Choose a club", 160, 180, 85, 35, this);
		
		//setting buttons
		this.joinClub = this.addButton("Join Club", 160, 360, 80, 40, this);
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
	}
	
	//actions for user interactions
	public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
		
		String name = this.clubList.getItem(clubList.getSelectedIndex());
		int id = r.getClubObjectID(name);
		
		//a club from the list of clubs is chosen 
        if (source == this.clubList && id!= 0) {
        	clubId = r.getClubObjectID(clubList.getItem(clubList.getSelectedIndex()));
			outputString("Club description: " + r.getClubObjectDesc(clubId) + "\nClub meet times: " + r.getClubOperatingTimesByID(clubId));
        }
        
        //student requests to join the club chosen
		if (source == this.joinClub && id!= 0) {
			if (r.alreadyMember(idUser, r.getClubObjectID(clubList.getItem(clubList.getSelectedIndex()))) == false) {
				r.addMemberToClub(idUser, r.getClubObjectID(clubList.getItem(clubList.getSelectedIndex())));
				r.addClubToYouClubs(idUser, r.getClubObjectID(clubList.getItem(clubList.getSelectedIndex())));
				this.dispose();
				new StudentHomepage(idUser);
			} else {
				outputString("Already a member.");
			}
        }
		
		//back button requested
		if (source == this.back) {
			this.dispose();
			new StudentHomepage(idUser);
		}

    }

}
