package partitions;

/**
 *
 * @author Евгения
 */
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.datatransfer.DataFlavor;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;
import java.util.Calendar;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class DragoDrop extends JFrame{
	
	private JFrame frame; 									//frame is the graphical window
	private JPanel upper, middle, lower, framePanel;		//framePanel is the big panel that fills frame, and inside of it are 3 new panels
	private JComboBox day, month, year;						//this is where you set the date
	private JLabel path, pathLabel, dateLabel;				//this is text around in the window
	private String[] days, months, years;					
	private int currentDay, currentMonth, currentYear;
	private JRadioButton dateToday, dateFile, dateOther;
	private ButtonGroup dateSettings;
	private JLabel picLabel;
	private BufferedImage myPicture;
	private String destDirectory, fileName;


	public static void main(String[] args){
		new DragoDrop();
	}
	
	
	
	public DragoDrop(){
		gui();
	}
	
	
	
	public void gui(){
		

		frame = new JFrame("Drag and Drop Program"); 
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		
		framePanel = new JPanel(); //
		framePanel.setLayout(new BorderLayout());
		
		
		dateLabel = new JLabel();
		dateLabel.setText("Date");
		
		pathLabel = new JLabel();
		pathLabel.setText("Path:");
		
		path = new JLabel();
		path.setText("");
		
		days = new String[31];
		for(int i=1; i<=31; i++){
			days[i-1] = Integer.toString(i);
		}
		
		months = new String[12];
		for(int i=1; i<=12; i++){
			months[i-1] = Integer.toString(i);
		}
		
		
		years = new String[50];
		for(int i=1; i<=50; i++){
			years[i-1] = Integer.toString(i+1985);
		}
		
		day = new JComboBox(days);
		month = new JComboBox(months);
		year = new JComboBox(years);
		
		Calendar calendar = Calendar.getInstance();
		currentDay = calendar.get(Calendar.DAY_OF_MONTH);
		currentMonth = 1+calendar.get(Calendar.MONTH); //Months are 0 indexed 
		currentYear = calendar.get(Calendar.YEAR);
		day.setSelectedItem(Integer.toString(currentDay));
		month.setSelectedItem(Integer.toString(currentMonth));
		year.setSelectedItem(Integer.toString(currentYear));
		
		dateFile = new JRadioButton("Use date of file", false);
		dateOther = new JRadioButton("Set date", true);
		dateFile.setBackground(null);
		dateOther.setBackground(null);
		
		dateSettings = new ButtonGroup();
		dateSettings.add(dateOther);
		dateSettings.add(dateFile);
		
		
		

		try {
			myPicture = ImageIO.read(new File("src/partitions/ready.jpg"));
			picLabel = new JLabel(new ImageIcon(myPicture));
		} catch (IOException e) {
			e.printStackTrace();
		}

		
		
		
		
		upper = new JPanel();
		upper.setBackground(Color.GRAY);
		upper.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		middle = new JPanel();
		middle.setBackground(Color.GRAY);
		middle.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		lower = new JPanel();
		lower.setBackground(Color.WHITE);
		lower.setPreferredSize(new Dimension(600, 300));
		lower.setDropTarget(new DropTarget(){
			public synchronized void drop(DropTargetDropEvent evt) {
				try{
					evt.acceptDrop(DnDConstants.ACTION_COPY);
					List<File> droppedFiles = (List<File>) evt.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);
					for (File file : droppedFiles){
						path.setText(file.getAbsolutePath());
						if(dateFile.isSelected()){
							Path pathForDate = Paths.get(file.getAbsolutePath());
							BasicFileAttributes attributes = Files.readAttributes(pathForDate, BasicFileAttributes.class);
							FileTime creationTime = attributes.creationTime();
							String[] part1 = creationTime.toString().split("T");
							String[] part2 = part1[0].split("-");
							System.out.println(creationTime);
							if(part2[2].startsWith("0")){
								part2[2] = part2[2].substring(1);
							}
							
							if(part2[1].startsWith("0")){
								part2[1] = part2[1].substring(1);
							}
							
							
							day.setSelectedItem(part2[2]);
							month.setSelectedItem(part2[1]);
							year.setSelectedItem(part2[0]);

						}
						
						destDirectory = (String)day.getSelectedItem()+"-"+month.getSelectedItem()+"-"+year.getSelectedItem();
						
						
						File destination = new File("Documents/"+destDirectory);
						
						String fullDestinationString = destination.toString();
						Path fullDestinationPath = Paths.get(fullDestinationString);
						  
					
						
						
						
						// if the directory date does not exist, create it
						  if (!destination.exists()) {
						    boolean result = false;

						    try{
						        destination.mkdir();
						        result = true;
						     } catch(SecurityException se){
						    	 se.printStackTrace();
						        break;
						     }        
						     if(result) {    
						       System.out.println("DIR created");  
						     }
						  }
						  
						  
						
						
						try{
							 
								File source = new File(file.getAbsolutePath());
								fileName = source.getName();
								
								
								Path pathSource = source.toPath();
								Path pathDestination = fullDestinationPath.resolve(fileName);
								
								System.out.println(pathDestination.toString());
								
								Files.move(pathSource, pathDestination);
					    	   
					    	}catch(Exception e){
					    		e.printStackTrace();
					    		break;
					    	}    
						
						
						
						
						
						    try {
								myPicture = ImageIO.read(new File("src/partitions/success.jpg"));
								picLabel = new JLabel(new ImageIcon(myPicture));
								lower.removeAll();
								lower.updateUI();
								lower.add(picLabel);
								


							} catch (IOException e) {
								e.printStackTrace();
								break;
							}
							
						

						
					}
				}catch (Exception ex) {
					
					ex.printStackTrace();
					try {
						myPicture = ImageIO.read(new File("src/partitions/error.jpg"));
						picLabel = new JLabel(new ImageIcon(myPicture));
						lower.removeAll();
						lower.updateUI();
						lower.add(picLabel);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					
					
					
				}
				
				
				
			}
			
			
		});
		
		
		System.out.println("Ready");
		

		upper.add(dateLabel);
		upper.add(day);
		upper.add(month);
		upper.add(year);
		upper.add(dateOther);
		upper.add(dateFile);
		

		
		middle.add(pathLabel);
		middle.add(path);
		
		lower.add(picLabel);
		
		framePanel.add(upper, BorderLayout.NORTH);
		framePanel.add(middle, BorderLayout.CENTER);
		framePanel.add(lower, BorderLayout.SOUTH);
		
		frame.setVisible(true);
		frame.setSize(600, 400);
		frame.add(framePanel);

	}
	
	
	
	
	
}


