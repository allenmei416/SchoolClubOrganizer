import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTable;

public class Timetable extends EasyApp{
	JTable MonMorn;
	JTable MonLun;
	JTable MonAft;
	JTable TuesMorn;
	JTable TuesLun;
	JTable TuesAft;
	JTable WedMorn;
	JTable WedLun;
	JTable WedAft;
	JTable ThuMorn;
	JTable ThuLun;
	JTable ThuAft;
	JTable FriMorn;
	JTable FriLun;
	JTable FriAft;
	
	Label Before;
	Label Lunch;
	Label After;
	Label Monday;
	Label Tuesday;
	Label Wednesday;
	Label Thursday;
	Label Friday;
	Label title;
	
	Button back;
	
	String originalUser = "";
	int idUser;
	
	
	public Timetable(String user, int idUser) throws ClassNotFoundException, IOException {
		Runner r = Runner.getInstance();
		originalUser = user;
		this.idUser = idUser;
		this.setBounds(50, 50, 1230, 500);
    	this.setTitle("Club Timetable");
    	this.title = this.addLabel("Club Timetable", 550, 60, 210, 25, this);

    	this.title.setFont(new Font("Arial", 0, 30));
    	
    	//setting button
    	this.back = this.addButton("Back", 5, 37, 60, 30, this);
    	
    	//setting labels
    	this.Before = this.addLabel("Before School", 80, 160, 90, 35, this);
    	this.Lunch = this.addLabel("Lunch", 80, 260, 50, 35, this);
    	this.After = this.addLabel("After School", 80, 360, 90, 35, this);
    	this.Monday = this.addLabel("Monday", 210, 110, 50, 35, this);
    	this.Tuesday = this.addLabel("Tuesday", 410, 110, 50, 35, this);
    	this.Wednesday = this.addLabel("Wednesday", 610, 110, 90, 35, this);
    	this.Thursday = this.addLabel("Thursday", 810, 110, 55, 35, this);
    	this.Friday = this.addLabel("Friday", 1010, 110, 50, 35, this);
    	
    	//setting tables
		this.MonMorn = this.addJTable(r.getClubByOperatingTime(DAYS.Monday, SCHOOLPERIOD.Morning), 210, 160, 180, 95, this);
		this.MonLun = this.addJTable(r.getClubByOperatingTime(DAYS.Monday, SCHOOLPERIOD.Lunch), 210, 260, 180, 95, this);
		this.MonAft = this.addJTable(r.getClubByOperatingTime(DAYS.Monday, SCHOOLPERIOD.Dismissal), 210, 360, 180, 95, this);
		this.TuesMorn = this.addJTable(r.getClubByOperatingTime(DAYS.Tuesday, SCHOOLPERIOD.Morning), 410, 160, 180, 95, this);
		this.TuesLun = this.addJTable(r.getClubByOperatingTime(DAYS.Tuesday, SCHOOLPERIOD.Lunch), 410, 260, 180, 95, this);
		this.TuesAft = this.addJTable(r.getClubByOperatingTime(DAYS.Tuesday, SCHOOLPERIOD.Dismissal), 410, 360, 180, 95, this);
		this.WedMorn = this.addJTable(r.getClubByOperatingTime(DAYS.Wednesday, SCHOOLPERIOD.Morning), 610, 160, 180, 95, this);
		this.WedLun = this.addJTable(r.getClubByOperatingTime(DAYS.Wednesday, SCHOOLPERIOD.Lunch), 610, 260, 180, 95, this);
		this.WedAft = this.addJTable(r.getClubByOperatingTime(DAYS.Wednesday, SCHOOLPERIOD.Dismissal), 610, 360, 180, 95, this);
		this.ThuMorn = this.addJTable(r.getClubByOperatingTime(DAYS.Thursday, SCHOOLPERIOD.Morning), 810, 160, 180, 95, this);
		this.ThuLun = this.addJTable(r.getClubByOperatingTime(DAYS.Thursday, SCHOOLPERIOD.Lunch), 810, 260, 180, 95, this);
		this.ThuAft = this.addJTable(r.getClubByOperatingTime(DAYS.Thursday, SCHOOLPERIOD.Dismissal), 810, 360, 180, 95, this);
		this.FriMorn = this.addJTable(r.getClubByOperatingTime(DAYS.Friday, SCHOOLPERIOD.Morning), 1010, 160, 180, 95, this);
		this.FriLun = this.addJTable(r.getClubByOperatingTime(DAYS.Friday, SCHOOLPERIOD.Lunch), 1010, 260, 180, 95, this);
		this.FriAft = this.addJTable(r.getClubByOperatingTime(DAYS.Friday, SCHOOLPERIOD.Dismissal), 1010, 360, 180, 95, this);
	}
	
	//Actions for user interactions
	@Override
	public void actions(final Object source, final String command) {     
        if (source == this.back) {
        	this.dispose();
        	if (originalUser == "s")
        		new StudentHomepage(idUser);
        	else
        		new TeachersHomepage(idUser);
        }
    }
}
