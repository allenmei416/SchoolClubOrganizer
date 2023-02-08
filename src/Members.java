import java.awt.Button;
import java.awt.Choice;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class Members extends EasyApp {

    Label title;
    Label name;
    Label email;
    Label grade;
    Label removeMember;
    
    Button remove;
    Button back;
    
    TextField studentEmail;

    JTable membersList;
    
    int idUser;
    int clubId;
    String [][] arr;


    public Members(int idUser, int clubId) throws ClassNotFoundException, IOException {
        this.idUser = idUser;
        this.clubId = clubId;
        Runner r = Runner.getInstance();

        this.setBounds(50, 50, 800, 500);
        this.setTitle("Members");
        
        this.title = this.addLabel("Members", 336, 110, 800, 35, this);
        this.title.setFont(new Font("Arial", 0, 30));
        
        //setting button
        this.remove = this.addButton("Remove", 200, 450, 70, 30, this);
        this.back = this.addButton("Back", 5, 37, 60, 30, this);
        
        //setting label
        this.name = this.addLabel("Name", 260, 160, 60, 35, this);
        this.email = this.addLabel("Email", 390, 160, 60, 35, this);
        this.grade = this.addLabel("Grade", 520, 160, 60, 35, this);
        this.removeMember = this.addLabel("Remove member by entering email:", 200, 416, 800, 35, this);
        
        //setting text field
        this.studentEmail = this.addTextField("", 280, 450, 165, 25, this);
        
        //array arr filled with members of a club
        arr = r.toMembersList(clubId);
        
        //table displayed
        this.membersList = this.addJTable(arr, 210, 200, 400, 200, this);

    }

    @Override

    public void actions(final Object source, final String command) throws ClassNotFoundException, IOException {
    	Runner r = Runner.getInstance();
    	
    	//back button requested
        if (source == this.back) {
            this.dispose();
            new ClubSlide(clubId, idUser); 
        }
        
        //removal of a student requested
        if (source == this.remove) {
        	int tempId = r.getStudentObjectID(this.studentEmail.getText());
        	if (tempId != 0){ 
	            this.dispose();
	            r.removeMemberFromClub(tempId, clubId);
	            r.removeClubFromYouClubs(tempId, clubId);
	            new ClubSlide(clubId, idUser);
        	} else {
        		outputString("Student was not found.");
        	}
        }
    }
}


