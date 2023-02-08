import javax.swing.JOptionPane;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ActionEvent;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.awt.Image;
import java.awt.FileDialog;
import java.io.IOException;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Choice;
import java.awt.List;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Button;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.LayoutManager;
import java.awt.MenuBar;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.Frame;

public class EasyApp extends Frame implements ActionListener, ItemListener, MouseListener, KeyListener
{
    MenuBar menubar;

    public EasyApp() {
        this.menubar = this.addMenuBar();
        this.setLayout(null);
        this.setSize(600, 400);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent evt) {
                evt.getWindow().dispose();
            }
        });
        this.setVisible(true);
        this.toFront();
        this.requestFocus();
    }

    public MenuBar makeMenus(final String[] menus) {
        for (int x = 0; x < menus.length; ++x) {
            if (menus[x].length() > 0) {
                int d = menus[x].indexOf("|");
                if (d < 0) {
                    d = menus[x].length();
                }
                final Menu newmenu = this.addMenu(menus[x].substring(0, d), this.menubar);
                while (d >= 0 && d < menus[x].length()) {
                    int e = menus[x].indexOf("|", d + 1);
                    if (e < 0) {
                        e = menus[x].length();
                    }
                    final MenuItem newmi = this.addMenuItem(menus[x].substring(d + 1, e), newmenu, this);
                    newmi.setActionCommand(newmenu.getActionCommand() + "|" + newmi.getActionCommand());
                    d = e;
                }
            }
        }
        return this.menubar;
    }

    public Button addButton(final String text, final int left, final int top, final int width, final int height, final ActionListener listener) {
        final Button newComp = new Button(text);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addActionListener(listener);
        }
        return newComp;
    }

    public Checkbox addCheckbox(final String text, final int left, final int top, final int width, final int height, final ItemListener listener) {
        final Checkbox newComp = new Checkbox(text);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addItemListener(listener);
        }
        return newComp;
    }

    public TextField addTextField(final String text, final int left, final int top, final int width, final int height, final ActionListener listener) {
        final TextField newComp = new TextField(text);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addActionListener(listener);
        }
        return newComp;
    }

    public TextArea addTextArea(final String text, final int left, final int top, final int width, final int height, final KeyListener listener) {
        final TextArea newComp = new TextArea(text);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addKeyListener(listener);
        }
        return newComp;
    }

    public Label addLabel(final String text, final int left, final int top, final int width, final int height, final MouseListener listener) {
        final Label newComp = new Label(text);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addMouseListener(listener);
        }
        return newComp;
    }

    public JLabel addJLabel(final ImageIcon img, final int left, final int top, final int width, final int height, final MouseListener listener) {
        final JLabel newComp = new JLabel(img);
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addMouseListener(listener);
        }
        return newComp;
    }

    public List addList(String words, final int left, final int top, final int width, final int height, final ItemListener listener) {
        final List newComp = new List();
        while (words.length() > 0) {
            final int s = words.indexOf("|");
            if (s < 0) {
                newComp.add(words);
                words = "";
            }
            else {
                newComp.add(words.substring(0, s));
                words = words.substring(s + 1);
            }
        }
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addItemListener(listener);
        }
        return newComp;
    }

    public Choice addChoice(String words, final int left, final int top, final int width, final int height, final ItemListener listener) {
        final Choice newComp = new Choice();
        while (words.length() > 0) {
            final int s = words.indexOf("|");
            if (s < 0) {
                newComp.add(words);
                words = "";
            }
            else {
                newComp.add(words.substring(0, s));
                words = words.substring(s + 1);
            }
        }
        this.add(newComp);
        newComp.setBounds(left, top, width, height);
        if (listener != null) {
            newComp.addItemListener(listener);
        }
        return newComp;
    }

    public JTable addJTable(final String[][] data, final int left, final int top, final int width, final int height, final ActionListener listener) {
        final String[] heads = new String[data[0].length];
        for (int s = 0; s < heads.length; ++s) {
            heads[s] = "";
        }
        final JTable newComp = new JTable(data, heads);
        final JScrollPane newComps = new JScrollPane(newComp);
        this.add(newComps);
        newComps.setBounds(left, top, width, height);
        return newComp;
    }

    public MenuItem addMenuItem(final String mText, final Menu mu, final ActionListener listener) {
        final MenuItem c = new MenuItem(mText);
        mu.add(c);
        if (listener != null) {
            c.addActionListener(listener);
        }
        return c;
    }

    public Menu addMenu(final String mText, final Menu mu) {
        final Menu c = new Menu(mText);
        mu.add(c);
        return c;
    }

    public Menu addMenu(final String mText, final MenuBar mb) {
        final Menu c = new Menu(mText);
        mb.add(c);
        return c;
    }

    public Menu addMenu(final String mText) {
        int d = mText.indexOf("|");
        if (d < 0) {
            d = mText.length();
        }
        final String id = mText.substring(0, d);
        final Menu c = new Menu(id);
        this.menubar.add(c);
        while (d < mText.length()) {
            int e = mText.indexOf("|", d + 1);
            if (e < 0) {
                e = mText.length();
            }
            final String cmd = mText.substring(d + 1, e);
            final MenuItem mi = this.addMenuItem(cmd, c, this);
            mi.setActionCommand(id + "|" + cmd);
            c.add(mi);
            d = e;
        }
        return c;
    }

    public MenuBar addMenuBar() {
        final MenuBar c = new MenuBar();
        this.setMenuBar(c);
        return c;
    }

    public Process runProgram(final String programName) {
        Process handle = null;
        try {
            handle = Runtime.getRuntime().exec(programName);
        }
        catch (IOException ex) {}
        return handle;
    }

    public String chooseFile() {
        final FileDialog fd = new FileDialog(this);
        fd.setVisible(true);
        return fd.getDirectory() + fd.getFile();
    }

    public Image loadImage(final String filename) {
        final ImageIcon im = new ImageIcon(filename);
        int c = 0;
        while (++c < 100000 && im.getImageLoadStatus() != 8) {}
        return im.getImage();
    }

    public boolean loadList(final List list, final String filename) {
        try {
            list.removeAll();
            final BufferedReader infile = new BufferedReader(new FileReader(filename));
            while (infile.ready()) {
                list.add(infile.readLine());
            }
            infile.close();
            return true;
        }
        catch (IOException e) {
            this.output(e.toString());
            return false;
        }
    }

    public boolean saveList(final String filename, final List list) {
        try {
            final PrintWriter outfo = new PrintWriter(new FileWriter(filename));
            for (int c = 0; c < list.getItemCount(); ++c) {
                outfo.println(list.getItem(c));
            }
            outfo.close();
            return true;
        }
        catch (IOException e) {
            this.output(e.toString());
            return false;
        }
    }

    public boolean loadArray(final String[] array, final String filename) {
        try {
            final BufferedReader countFile = new BufferedReader(new FileReader(filename));
            int s = 0;
            while (countFile.ready()) {
                array[s] = countFile.readLine();
                ++s;
            }
            array[s] = null;
            countFile.close();
            return true;
        }
        catch (Exception e) {
            this.output(e.toString());
            return false;
        }
    }

    public boolean saveArray(final String filename, final String[] array) {
        try {
            final PrintWriter outfo = new PrintWriter(new FileWriter(filename));
            for (int c = 0; array[c] != null; ++c) {
                outfo.println(array[c]);
            }
            outfo.close();
            return true;
        }
        catch (IOException e) {
            this.output(e.toString());
            return false;
        }
    }

    public void sortArray(final String[] arr) {
        int swaps = 0;
        do {
            swaps = 0;
            for (int c = 0; arr[c + 1] != null; ++c) {
                if (arr[c].compareTo(arr[c + 1]) > 0) {
                    final String temp = arr[c];
                    arr[c] = arr[c + 1];
                    arr[c + 1] = temp;
                    ++swaps;
                }
            }
        } while (swaps != 0);
    }

    public String getType(final Object ob) {
        final String c = ob.getClass().toString();
        int p = c.lastIndexOf(".");
        if (p < 0) {
            p = -1;
        }
        return c.substring(p + 1);
    }

    @Override
    public void actionPerformed(final ActionEvent evt) {
        try {
			this.actions(evt.getSource(), evt.getActionCommand());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @Override
    public void itemStateChanged(final ItemEvent evt) {
        final Object source = evt.getSource();
        final String t = this.getType(source);
        String command = "";
        if (t.equals("List")) {
            command = ((List)source).getSelectedItem();
        }
        else if (t.equals("Choice")) {
            command = ((Choice)source).getSelectedItem();
        }
        else {
            command = evt.getItem().toString();
        }
        try {
			this.actions(source, command);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void actions(final Object source, final String command) throws IOException, ClassNotFoundException {
        this.output(source.toString() + ":" + command);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        final Object source = e.getSource();
        try {
			this.actions(source, "");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
    }

    @Override
    public void mouseExited(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
    }

    @Override
    public void keyTyped(final KeyEvent e) {
        final Object source = e.getSource();
        try {
			this.actions(source, e.getKeyChar() + "");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @Override
    public void keyPressed(final KeyEvent e) {
    }

    @Override
    public void keyReleased(final KeyEvent e) {
    }

    public void output(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void outputString(final String message) {
        this.output(message);
    }

    public void output(final char info) {
        this.output(info + "");
    }

    public void output(final byte info) {
        this.output(info + "");
    }

    public void output(final int info) {
        this.output(info + "");
    }

    public void output(final long info) {
        this.output(info + "");
    }

    public void output(final double info) {
        this.output(info + "");
    }

    public void output(final boolean info) {
        this.output(info + "");
    }

    public String input(final String prompt) {
        return JOptionPane.showInputDialog(this, prompt);
    }

    public String inputString(final String prompt) {
        return this.input(prompt);
    }

    public String input() {
        return this.input("");
    }

    public char inputChar(final String prompt) {
        char result = '\0';
        try {
            result = this.input(prompt).charAt(0);
        }
        catch (Exception e) {
            result = '\0';
        }
        return result;
    }

    public byte inputByte(final String prompt) {
        byte result = 0;
        try {
            result = Byte.valueOf(this.input(prompt).trim());
        }
        catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public int inputInt(final String prompt) {
        int result = 0;
        try {
            result = Integer.valueOf(this.input(prompt).trim());
        }
        catch (Exception e) {
            result = 0;
        }
        return result;
    }

    public long inputLong(final String prompt) {
        long result = 0L;
        try {
            result = Long.valueOf(this.input(prompt).trim());
        }
        catch (Exception e) {
            result = 0L;
        }
        return result;
    }

    public double inputDouble(final String prompt) {
        double result = 0.0;
        try {
            result = Double.valueOf(this.input(prompt).trim());
        }
        catch (Exception e) {
            result = 0.0;
        }
        return result;
    }

    public boolean inputBoolean(final String prompt) {
        boolean result = false;
        try {
            result = Boolean.valueOf(this.input(prompt).trim());
        }
        catch (Exception e) {
            result = false;
        }
        return result;
    }
}