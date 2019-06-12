package cc.openhome;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class AddressBook extends JFrame {
  // Specify the size of five string fields in the record
  final static int NAME_SIZE = 32;
  final static int STREET_SIZE = 32;
  final static int CITY_SIZE = 20;
  final static int STATE_SIZE = 2;
  final static int ZIP_SIZE =5;
  final static int RECORD_SIZE =
    (NAME_SIZE + STREET_SIZE + CITY_SIZE + STATE_SIZE + ZIP_SIZE);
  // Access address.dat using RandomAccessFile
  private RandomAccessFile raf;
  private long previousPointer;
  // Text fields
  private JTextField jtfName = new JTextField(NAME_SIZE);
  private JTextField jtfStreet = new JTextField(STREET_SIZE);
  private JTextField jtfCity = new JTextField(CITY_SIZE);
  private JTextField jtfState = new JTextField(STATE_SIZE);
  private JTextField jtfZip = new JTextField(ZIP_SIZE);

  // Buttons
  private JButton jbtAdd = new JButton("Add");
  private JButton jbtFirst = new JButton("First");
  private JButton jbtNext = new JButton("Next");
  private JButton jbtPrevious = new JButton("Previous");
  private JButton jbtLast = new JButton("Last");

  public AddressBook() {
    // Open or create a random access file
    try {
      raf = new RandomAccessFile("address.txt", "rw");
    }
    catch(IOException ex) {
      System.out.print("Error: " + ex);
      System.exit(1);
    }

    // Panel p1 for holding labels Name, Street, and City
    JPanel p1 = new JPanel();
    p1.setLayout(new GridLayout(3, 1));
    p1.add(new JLabel("Name"));
    p1.add(new JLabel("Street"));
    p1.add(new JLabel("City"));

    // Panel jpState for holding state
    JPanel jpState = new JPanel();
    jpState.setLayout(new BorderLayout());
    jpState.add(new JLabel("State"), BorderLayout.WEST);
    jpState.add(jtfState, BorderLayout.CENTER);

    // Panel jpZip for holding zip
    JPanel jpZip = new JPanel();
    jpZip.setLayout(new BorderLayout());
    jpZip.add(new JLabel("Zip"), BorderLayout.WEST);
    jpZip.add(jtfZip, BorderLayout.CENTER);

    // Panel p2 for holding jpState and jpZip
    JPanel p2 = new JPanel();
    p2.setLayout(new BorderLayout());
    p2.add(jpState, BorderLayout.WEST);
    p2.add(jpZip, BorderLayout.CENTER);

    // Panel p3 for holding jtfCity and p2
    JPanel p3 = new JPanel();
    p3.setLayout(new BorderLayout());
    p3.add(jtfCity, BorderLayout.CENTER);
    p3.add(p2, BorderLayout.EAST);

    // Panel p4 for holding jtfName, jtfStreet, and p3
    JPanel p4 = new JPanel();
    p4.setLayout(new GridLayout(3, 1));
    p4.add(jtfName);
    p4.add(jtfStreet);
    p4.add(p3);

    // Place p1 and p4 into jpAddress
    JPanel jpAddress = new JPanel(new BorderLayout());
    jpAddress.add(p1, BorderLayout.WEST);
    jpAddress.add(p4, BorderLayout.CENTER);

    // Set the panel with line border
    jpAddress.setBorder(new BevelBorder(BevelBorder.RAISED));

    // Add buttons to a panel
    JPanel jpButton = new JPanel();
    jpButton.add(jbtAdd);
    jpButton.add(jbtFirst);
    jpButton.add(jbtNext);
    jpButton.add(jbtPrevious);
    jpButton.add(jbtLast);

    // Add jpAddress and jpButton to the frame
    add(jpAddress, BorderLayout.CENTER);
    add(jpButton, BorderLayout.SOUTH);

    jbtAdd.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        writeAddress();
      }
    });
    jbtFirst.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          if (raf.length() > 0) readAddress(0);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    jbtNext.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
          long currentPosition = raf.getFilePointer();
          if (currentPosition < raf.length())
            readAddress(currentPosition);
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    jbtPrevious.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        try {
        	previousPointer = previousPointer-2;
        	if(previousPointer>0) {
            	raf.seek(previousPointer);
            	int c = -1;
            	while(previousPointer>=0) {
            		c = raf.read();
            		if(c=='\n') {
            			readAddress(raf.getFilePointer());
            			break;
            		}
        	        raf.seek(previousPointer);
        	        if (previousPointer == 0) {
        	        	readAddress(0);
        	        }
        	        previousPointer--;
            	}
        	}
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });
    jbtLast.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        try { 
        	long last = raf.length()-2;
        	raf.seek(last);
        	int c = -1;
        	while(last>=0) {
        		c = raf.read();
        		if(c=='\n') {
        			readAddress(raf.getFilePointer());
        			break;
        		}
    	        raf.seek(last);
    	        if (last == 0) {
    	        	readAddress(0);
    	        }
    	        last--;
        	}
        }
        catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    });

    // Display the first record if exists
    try {
      if (raf.length() > 0) readAddress(0);
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /** Write a record at the end of the file */
  public void writeAddress() {
    try {
      raf.seek(raf.length());
      FixedLengthStringIO.writeFixedLengthString(
        jtfName.getText(), jtfName.getText().length(), raf);
      FixedLengthStringIO.writeFixedLengthString(
        jtfStreet.getText(), jtfStreet.getText().length(), raf);
      FixedLengthStringIO.writeFixedLengthString(
        jtfCity.getText(), jtfCity.getText().length(), raf);
      FixedLengthStringIO.writeFixedLengthString(
        jtfState.getText(), jtfState.getText().length(), raf);
      FixedLengthStringIO.writeFixedLengthString(
    	Integer.parseInt(jtfZip.getText()), raf);
      raf.write("\r\n".getBytes());
    }
    catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  /** Read a record at the specified position */
  public void readAddress(long position) throws IOException {
    raf.seek(position);
    previousPointer = position;
    String temp = FixedLengthStringIO.readFixedLengthString(raf);
    long l = raf.getFilePointer();
    String[] result = temp.split(" ");
    jtfName.setText(result[0]);
    jtfStreet.setText(result[1]);
    jtfCity.setText(result[2]);
    jtfState.setText(result[3]);
    jtfZip.setText(result[4]);
  }

  public static void main(String[] args) {
    AddressBook frame = new AddressBook();
    frame.pack();
    frame.setTitle("AddressBook");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}