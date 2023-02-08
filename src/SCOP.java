import java.io.IOException;
import java.io.RandomAccessFile;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.Button;
import java.awt.TextField;
import java.awt.Label;

public class SCOP extends EasyApp {
	Label title;
	
    Button teacherButton;
    Button studentButton;
    Button exit;
    
    public String select = "";
    
    //main method for program
    public static void main(final String[] args) {
        new SCOP();
    }
    
    public SCOP() {
    	this.setBounds(50, 50, 800, 500);
    	this.setTitle("Home");
    	
    	this.title = this.addLabel("Welcome to the Club Organizer", 180, 110, 800, 35, this);
    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting buttons
    	this.teacherButton = this.addButton("Teacher",185, 190, 180, 80, this);
    	this.studentButton = this.addButton("Student", 425, 190, 180, 80, this);
    	this.exit = this.addButton("Exit", 503, 300, 100, 40, this);
    }
    
    @Override
    public void actions(final Object source, final String command) {	
        //teacher button requested
    	if (source == this.teacherButton) {
        	select = "t";
            new Login(select);
            this.dispose();
        }
    	//student button requested
        if (source == this.studentButton) {
        	select = "s";
        	new Login(select);
        	this.dispose();
        }
        //exit button requested
        if (source == this.exit) {
            System.exit(0);
        }
    }
}
