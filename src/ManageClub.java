import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class ManageClub extends EasyApp {
	
	Label title;
	Label chooseClub;
	
	Button back;
	Button select;
	
	Choice listClubs;
	
	int idUser;
	String allClubNames = "";
	
	public ManageClub(int idUser) throws ClassNotFoundException, IOException {
		this.idUser = idUser;
		Runner r = Runner.getInstance();
		
		this.setBounds(50, 50, 800, 500);
    	this.setTitle("Manage Club");
    	
    	this.title = this.addLabel("Choose A Club:", 280, 120, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting buttons
    	this.select = this.addButton("Select", 370, 400, 60, 30, this);
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
    	
    	//collecting club list into one string
    	for (int i: r.clubsByManagerID(idUser)) {
    		allClubNames += r.getClubObjectName(i) + "|";		
		}
    	
    	//setting choice
    	this.listClubs = this.addChoice("Select|" + allClubNames, 195, 230, 400, 200, this);
	}

	@Override
	 
	public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
		//back button requested
		if (source == this.back) {
			this.dispose();
			new TeachersHomepage(idUser);
		}
		
		//selected club requested to be displayed
		if (source == this.select &&  getClubId()!= 0) {
			Runner r = Runner.getInstance();
			this.dispose();
	        new ClubSlide(getClubId(), idUser);
		}
	}
	 
	//determining which club has been selected
	public int getClubId() throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
		final String name = this.listClubs.getItem(listClubs.getSelectedIndex());
		int id = r.getClubObjectID(name);
		return id;
	 }
}


