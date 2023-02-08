import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ItemListener;
import java.io.IOException;

public class CreateClub extends EasyApp {
	
	Button createButton;
	Button back;
	
	Label title;
	Label nameClub;
	Label clubDescription;
	Label dayClub;
	Label periodClub;
	
	TextField nameEnter;
	TextField clubDescriptionEnter;
	
	Choice days;
	Choice periods;
	
	int idUser;
	
	public CreateClub(int idUser) throws IOException, ClassNotFoundException {
		Runner r = Runner.getInstance();
		
		this.idUser = idUser;
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Create Club");
    	
    	//setting labels
		this.title = this.addLabel("Fill in information: ", 180, 60, 800, 25, this);
		this.nameClub = this.addLabel("Name ", 180, 117, 60, 15, this);
        this.clubDescription = this.addLabel("Description ", 180, 147, 70, 15, this);
        this.dayClub = this.addLabel("Day of club ", 180, 190, 65, 15, this);
        this.periodClub = this.addLabel("Period of club ", 280, 190, 80, 15, this);
        this.title.setFont(new Font("Arial", 0, 30));         
        
        //setting text fields
        this.nameEnter = this.addTextField("", 300, 117, 165, 25, this);
        this.clubDescriptionEnter = this.addTextField("", 300, 147, 165, 25, this);
        
        //setting buttons
    	this.createButton = this.addButton("Create",180,280,100,30, this);
        this.back = this.addButton("Back", 5, 37, 60, 30, this);
        
        //setting choices
        this.days = this.addChoice(r.getDaysStringRepresentation(), 180, 210, 80, 25, this);
        this.periods = this.addChoice(r.getSchoolPeriodStringRepresentation(), 280, 210, 105, 25, this);
	}
	
	//actions for user interactions
    @Override
    public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
        //button for creating a club
    	if (source == this.createButton) {
        	storeClub();
        	this.dispose();
        	new TeachersHomepage(idUser);
        }
        
    	//back button requested
        if (source == this.back) {
        	this.dispose();
        	new TeachersHomepage(idUser);
        }
    }
    
    //method to store club in text file clubs.txt
    void  storeClub () throws ClassNotFoundException, IOException{
		Runner r = Runner.getInstance();
		final String name = this.nameEnter.getText();
		final String description = this.clubDescriptionEnter.getText();
		final String day = this.days.getItem(days.getSelectedIndex());
		final String period = this.periods.getItem(periods.getSelectedIndex());
		int newId = r.makeNewID();

		if (name.compareTo("")!=0 && description.compareTo("")!=0) {
			r.createClub(newId, name, description, idUser);
			r.addClubOperatingTime(day, period, newId);			
		}	
	}
}




