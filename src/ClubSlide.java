import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.io.IOException;

public class ClubSlide extends EasyApp{
	
	Label title;
	Label clubName;
	Label teacherName;
	Label description;
	Label meetTime;
	Label editData;
	Label delete;
	
	TextField changeName;
	TextField changeDescription;
	TextField passToDelete;
	
	Button submitChangeName;
	Button submitChangeDescription;
	Button submitAddDay;
	Button submitRemoveDay;
	Button back;
	Button membersList;
	Button deleteClub;
	
	Choice days;
	Choice periods;
	Choice daysRemove;
	Choice periodsRemove;
	
	int idUser;
	int clubId;
	
	
	public ClubSlide (int clubId, int idUser) throws ClassNotFoundException, IOException {
		this.clubId = clubId;
		this.idUser = idUser;
		Runner r = Runner.getInstance();
		
		this.setBounds(50, 50, 800, 500);
		
    	this.setTitle(r.getClubObjectName(clubId));
    	
    	//setting labels
    	this.title = this.addLabel(r.getClubObjectName(clubId), 150, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	this.clubName = this.addLabel("Club name: " + r.getClubObjectName(clubId), 150, 170, 200, 35, this);
    	this.teacherName = this.addLabel("Teacher name: " + r.getTeacherObjectName(r.getClubObjectTeacherId(clubId)), 150, 200, 200, 35, this);
    	this.description = this.addLabel("Description: " + r.getClubObjectDesc(clubId), 150, 230, 500, 35, this);
    	this.meetTime = this.addLabel("Meet days: " + r.getClubOperatingTimesByID(clubId),150, 260, 600, 35, this);
    	this.editData = this.addLabel("Edit Information", 150, 300, 200, 25, this);
    	this.editData.setFont(new Font("Arial", 0, 18));
    	this.delete = this.addLabel("Enter your password and press Delete Club to delete:", 450, 170, 400, 25, this);
    	
    	//setting text fields
    	this.changeName = this.addTextField("", 150, 330, 165, 25, this);
    	this.changeDescription = this.addTextField("", 150, 360, 165, 25, this);
    	this.passToDelete = this.addTextField("", 450, 200, 165, 25, this);
    	
    	//setting buttons
    	this.submitChangeName = this.addButton("Edit Name",320, 330, 100, 30, this);
    	this.submitChangeDescription = this.addButton("Edit Description",320, 360, 100, 30, this);
    	this.submitAddDay = this.addButton("Add Time",320, 390, 100, 30, this);
		this.submitRemoveDay = this.addButton("Remove Time",320, 420, 100, 30, this);
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
    	this.membersList = this.addButton("Members",500, 330, 180, 80, this);
		this.deleteClub = this.addButton("Delete Club", 625, 195, 100, 30, this);
    	
		//setting choices
        this.days = this.addChoice(r.getDaysStringRepresentation(), 150, 400, 80, 25, this);
        this.periods = this.addChoice(r.getSchoolPeriodStringRepresentation(), 240,400, 75, 25, this);
		this.daysRemove = this.addChoice(r.getDaysStringRepresentation(), 150, 430, 80, 25, this);
		this.periodsRemove = this.addChoice(r.getSchoolPeriodStringRepresentation(), 240,430, 75, 25, this);
	}
	
	//Actions for user interactions
	@Override
	public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
		
		//back button pressed
		if (source == this.back) {        	
			this.dispose();
        	new ManageClub(idUser);
        }
        
		//name change requested
        if (source == this.submitChangeName) {
        	this.dispose();
        	r.editClubObjectName(clubId, this.changeName.getText());
            new ClubSlide(clubId, idUser);
        }
        
        //description change requested
        if (source == this.submitChangeDescription) {
        	this.dispose();
        	r.editClubObjectDesc(clubId, this.changeDescription.getText());
        	new ClubSlide(clubId, idUser);
        }
        
        //operating time added
        if(source == this.submitAddDay) {
        	this.dispose();
        	String chosenDay = days.getItem(days.getSelectedIndex());
        	String chosenSp = periods.getItem(periods.getSelectedIndex());
        	r.addClubOperatingTime(chosenDay, chosenSp, clubId);
        	new ClubSlide(clubId, idUser);
        }
        
        //operating time removed
        if(source == this.submitRemoveDay) {
        	this.dispose();
        	String chosenDayRemove = daysRemove.getItem(daysRemove.getSelectedIndex());
        	String chosenSpRemove = periodsRemove.getItem(periodsRemove.getSelectedIndex());
        	System.out.println(chosenDayRemove + " " + chosenSpRemove);
        	r.removeClubOperatingTime(chosenDayRemove, chosenSpRemove, clubId);
        	new ClubSlide(clubId, idUser);
		}
        
        //members list requested 
		if (source == this.membersList) {
			 if (r.sizeMembers(clubId) > 0) {
				 this.dispose();
				 new Members(idUser, clubId);
			 } else {
				 outputString("There are no members.");
			 }
		}
		
		//delete club requested
		if (source == this.deleteClub){
			if (this.passToDelete.getText() == ""){
				outputString("Please enter your password.");
			} else {
				if (r.deleteClub (this.passToDelete.getText(), idUser, clubId) == true){
					this.dispose();
					new TeachersHomepage(idUser);
				} else {
					outputString("Password is incorrect");
				}
			 }
		 }
    	}
}
