package Design;

import javax.swing.*;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import copier.CopyFile;
import copier.Main;
import copier.ShutDown;

import java.awt.List;

public class Window extends JFrame {
	private JTextField textField;
	private JProgressBar progTotal;
	private JProgressBar progressBarRem;
	private JTextField textField_1;
	private File selectedfile[];
	private File selectedDestination[];
	private String Destination_selected="";
	private String files_selected = "";
	private String editedString="";
	private String editedString_2="";
	private String[] parts;
	private String[] parts_1;
	private String[] partsname;
	private String[] partsname_1;
	private String filename="";
	private String Destinationname="";
	private JCheckBox chckbxShutDownAfter;
	private JMenuItem aboutMenuItem;
	private JMenuItem addFilesMenuItem;
	private JMenuItem addDestinationsMenuItem;
	private JMenuItem changeThemeMenuItem;
	private JMenuItem clearFilesListMenuItem;
	private JMenuItem clearDestinationListMenuItem;
	private JMenu editMenu;
    private JMenuItem exitMenuItem;
    private JMenu fileMenu;
    private JMenu helpMenu;
    private javax.swing.JMenuBar mainMenuBar;
    public static int CNT = 0;
    private long totalSize;
    private long totalCompleted=0;
    private long getSize=0;
    
	private boolean shutDownCheck;
	private boolean copyCheck;
	
	public Timer timer;
	private JSpinner spinner;
	//private Font fontI;
	
	private JLabel timerLabel = new JLabel("");
	
	
	
	List list = new List();
	List list_1=new List();
	
	private Font fontI;
	public Window()  {
		super("FileCopier");
		
		
		
		setAlwaysOnTop(false);
		setLocationByPlatform(true);
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 672, 227);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		createfontI();
		
		JLabel lblSources = new JLabel("Sources");
		
		lblSources.setFont(fontI);
		
		

		lblSources.setSize(new Dimension(100, 30));
		lblSources.setBounds(10, 11, 109, 14);
		panel.add(lblSources);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(10, 35, 389, 22);
		panel.add(textField);
		
		mainMenuBar=new JMenuBar();
		fileMenu=new JMenu();
		
		fileMenu.setText("File");
		addFilesMenuItem=new JMenuItem();

        addFilesMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_B, java.awt.event.InputEvent.CTRL_MASK));
        addFilesMenuItem.setText("Add Files");
        addFilesMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browseFile();
			}
		});
        fileMenu.add(addFilesMenuItem);
        
        addDestinationsMenuItem=new JMenuItem();

        addDestinationsMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        addDestinationsMenuItem.setText("Browse Destination");
        addDestinationsMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				browseDestination();
			}
		});
        fileMenu.add(addDestinationsMenuItem);
        
        exitMenuItem=new JMenuItem();
        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        fileMenu.add(exitMenuItem);
        
        mainMenuBar.add(fileMenu);
        
        editMenu=new JMenu();
        editMenu.setText("Edit");
        
        clearFilesListMenuItem=new JMenuItem();
        clearFilesListMenuItem.setText("Clear files list");
        clearFilesListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	            	
            	clearAll_Source();
               
            }
        });
        editMenu.add(clearFilesListMenuItem);
        
        clearDestinationListMenuItem=new JMenuItem();
        clearDestinationListMenuItem.setText("Clear Destination list");
        clearDestinationListMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	            	
            	clearAll_Destination();
               
            }
        });
        editMenu.add(clearDestinationListMenuItem);
        
        mainMenuBar.add(editMenu);
        
        helpMenu=new JMenu();
        helpMenu.setText("Help");
        
        changeThemeMenuItem=new JMenuItem();
        changeThemeMenuItem.setText("Change Theme");
        changeThemeMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeThemeMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(changeThemeMenuItem);
        
        
        aboutMenuItem=new JMenuItem();
        aboutMenuItem.setText("About");
        aboutMenuItem.addActionListener (new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

            	JOptionPane.showMessageDialog(null, "Developed by\nMd. Faojul Ahsan\nKhulna University Of Engineering & Technology\nSefet E Rahman \nKhulna University Of Engineering & Technology\nCopyright © 2015. All rights reserved.", "About", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        		
     
        helpMenu.add(aboutMenuItem);

        mainMenuBar.add(helpMenu);

        setJMenuBar(mainMenuBar);

		
		JButton btnBrowse = new JButton("Browse");
		btnBrowse.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				browseFile();
						
			}
		});
		btnBrowse.setBounds(409, 35, 109, 23);
		panel.add(btnBrowse);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				list.removeAll();
				parts = editedString.split(";");
				for(int i=0;i<parts.length;i++)
				{
					list.add(parts[i]);
				}
				partsname=filename.split(";");   
				
				textField.setText("");
				
			}
		});
		btnAdd.setBounds(528, 35, 107, 23);
		panel.add(btnAdd);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 77, 625, 125);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblSelectedFiles = new JLabel("Selected Files");
		lblSelectedFiles.setFont(fontI);
		lblSelectedFiles.setBounds(0, 0, 139, 14);
		panel_2.add(lblSelectedFiles);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(213, 49, -152, 22);
		panel_2.add(textArea);
		
		list.setBounds(10, 20, 498, 95);
		panel_2.add(list);
		
		JButton btnClearAll = new JButton("Clear All");
		btnClearAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clearAll_Source();
				
			}
		});
		btnClearAll.setBounds(518, 20, 107, 23);
		panel_2.add(btnClearAll);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLocation(0, 227);
		panel_1.setSize(new Dimension(672, 382));
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 37, 393, 23);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnBrowse_1 = new JButton("Browse");
		btnBrowse_1.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			browseDestination();
				
			}
		});
		btnBrowse_1.setBounds(413, 37, 114, 23);
		panel_1.add(btnBrowse_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(10, 224, 625, 143);
		panel_1.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblCopyState = new JLabel("Copy State");
		lblCopyState.setFont(fontI);
		lblCopyState.setBounds(10, 0, 228, 25);
		panel_3.add(lblCopyState);
		
		JLabel lblRemaining = new JLabel("Current File :");
		lblRemaining.setFont(fontI);
		lblRemaining.setBounds(60, 36, 167, 14);
		panel_3.add(lblRemaining);
		
		JLabel lblTime = new JLabel("All Files :");
		lblTime.setFont(fontI);
		lblTime.setBounds(60, 80, 119, 14);
		panel_3.add(lblTime);
		
	    chckbxShutDownAfter = new JCheckBox("Shut Down After Copy                                                    Shut Down Delay Seconds");
	    chckbxShutDownAfter.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent event) {
	            JCheckBox cb = (JCheckBox) event.getSource();
	            if (cb.isSelected()) {
	                //ShutDown shut= new ShutDown();
	            	setShutDownCheck(true);
	            } else {
	            	
	            	setShutDownCheck(false);
	                // check box is unselected, do something else
	            }
	        }
	    });
		
		chckbxShutDownAfter.setFont(fontI);
		chckbxShutDownAfter.setBounds(36, 113, 470, 23);
		panel_3.add(chckbxShutDownAfter);
		
		 progressBarRem = new JProgressBar();
		progressBarRem.setBounds(203, 30, 241, 32);
		progressBarRem.setStringPainted(true);
		progressBarRem.setFont(new Font("arial", Font.BOLD, 12));
		progressBarRem.setString("0%");
		progressBarRem.setValue(0);
		panel_3.add(progressBarRem);
		
		progTotal = new JProgressBar();
		progTotal.setBounds(203, 74, 241, 32);
		progTotal.setStringPainted(true);
		progTotal.setFont(new Font("arial", Font.BOLD, 12));
		progTotal.setString("0%");
		progTotal.setValue(0);

		panel_3.add(progTotal);
		
		JButton btnStart = new JButton("Copy");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean test = true;
				while(test){
				
					
				if(editedString.equals("")){	
					JOptionPane.showMessageDialog(null, "Please select file(s)!!!", "ERROR", JOptionPane.WARNING_MESSAGE , null);
					test = false;
					continue;
					
				}
					
				if(editedString_2.equals("")){
					
					JOptionPane.showMessageDialog(null, "Please select a destination!!!", "ERROR", JOptionPane.WARNING_MESSAGE , null);
					test = false;
					continue;
					
				}
				
					
				
				new Thread(new Runnable() {
					
					@SuppressWarnings("serial")
					@Override
					public void run() {
						
						
//						int i=0;
//						 totalSize = 0;
//						for( i=0;i<parts.length;i++)
//						{
//							File f = new File(parts[i]);
//							totalSize+= f.length();							
//						}
//						for( i=0;i<parts.length;i++)
//						{
//							new CopyFile(parts[i],editedString_2+"\\"+partsname[i], Window.this);
//							progressBarRem.setString("100%");
//							progressBarRem.setValue(100);
//						}
						
						
						int i=0;
						int j=0;
						 totalSize = 0;
						 for(j=0;j<parts_1.length;j++)   //loops for multiple destination
						{
							 for( i=0;i<parts.length;i++)  //loops for multiple selected files
						{
							File f = new File(parts[i]);
							totalSize+= f.length();							
						}
						}
						
						for(j=0;j<parts_1.length;j++)   
						
						{
							for( i=0;i<parts.length;i++)
						{
							new CopyFile(parts[i],parts_1[j]+"\\"+partsname[i], Window.this);
							progressBarRem.setString("100%");
							progressBarRem.setValue(100);
						}
						}
						
						setCopyCheck(true);
						textField_1.setText("");
						editedString_2 = "";
						Destination_selected = "";
					
						clearAll_Source();
						clearAll_Destination();
						ShutDown shut= new ShutDown();
						
							
						if(isShutDownCheck() == true){
							
							String[] buttons = { "Shutdown Now", "Cancel Shutdown" };
							
							
							int timerDelay = 1000;
							new Timer(timerDelay , new ActionListener() {
						         int timeLeft = (Integer) spinner.getValue();

						         
						         @Override
						         public void actionPerformed(ActionEvent e) {
						            if (timeLeft >= 0) {                                     
						               timerLabel.setText(Window.CNT+ "Files Copying Completed. Shutting DOWN in " + timeLeft + " seconds");
						               timeLeft--;
						            } else {
						               ((Timer)e.getSource()).stop();
						               java.awt.Window win = SwingUtilities.getWindowAncestor(timerLabel);
						               win.setVisible(false);
						            }
						         }
						      }){{setInitialDelay(0);}}.start();

						   
							int test = 0;
						      
							int Doubletest = JOptionPane.showOptionDialog(null, timerLabel, "Confirmation",
									   JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
							
							if(Doubletest == 1)
								test = Doubletest;
																
							if(test == 0 && isShutDownCheck() == true)
								shut.doShut();
							
						}
						
						
						progTotal.setString("100%");
						progTotal.setValue(100);
						JOptionPane.showMessageDialog(null, Window.CNT+ "Files Copying Completed", "CopyState", JOptionPane.INFORMATION_MESSAGE);
						Window.CNT=0;
						progTotal.setString("0%");
						progTotal.setValue(0);
						progressBarRem.setString("0%");
						progressBarRem.setValue(0);
						
					}
				}).start();

				test = false;
			}
		}
		});
		
		btnStart.setBounds(457, 38, 158, 56);
		panel_3.add(btnStart);
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		SpinnerModel sm = new SpinnerNumberModel(60, 0, 9999, 1);       
		spinner = new JSpinner(sm);
		spinner.setEditor(new JSpinner.NumberEditor(spinner, "0000"));
		spinner.setBounds(512, 111, 60, 25);
		panel_3.add(spinner);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 83, 625, 130);
		panel_1.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDestinationList = new JLabel("Destination List");
		lblDestinationList.setFont(fontI);
		lblDestinationList.setBounds(0, 0, 136, 20);
		panel_4.add(lblDestinationList);
		
		list_1 = new List();
		list_1.setBounds(10, 24, 498, 95);
		panel_4.add(list_1);
		
		JButton button = new JButton("Clear All");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				clearAll_Destination();
				
			}
		});
		button.setBounds(518, 24, 107, 23);
		panel_4.add(button);
		
		JButton btnAdd_1 = new JButton("Add");
		btnAdd_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				list_1.removeAll();
				parts_1 = editedString_2.split(";");
				for(int i=0;i<parts_1.length;i++)
				{
					list_1.add(parts_1[i]);
				}
				partsname_1=Destinationname.split(";");   
				
				textField_1.setText("");
				
			}
		});
		btnAdd_1.setBounds(537, 37, 98, 23);
		panel_1.add(btnAdd_1);
		
		JLabel lblDestinations = new JLabel("Destinations");
		lblDestinations.setFont(fontI);
		lblDestinations.setBounds(10, 11, 130, 14);
		panel_1.add(lblDestinations);
		
		
		
	}
	private void changeThemeMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeThemeMenuItemActionPerformed
        ThemeManager themeManager = new ThemeManager(this, true);
        themeManager.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        themeManager.setLocationRelativeTo(this);
        
        themeManager.setVisible(true);
    }

	public void setCompleted(final int val, final int totalVal){
		Thread t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				progressBarRem.setString(val+"%");
				progressBarRem.setValue(val);

			}
		});
		t.start();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				totalCompleted+= totalVal;
				int perc = (int)(totalCompleted*100.0/totalSize);
				progTotal.setString(perc+"%");
				progTotal.setValue(perc);
			}
		}).start();
	}
	private void browseFile() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setSize(250, 250);
		fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		fileChooser.setMultiSelectionEnabled(isEnabled());
//		     	
		if(fileChooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			
			selectedfile = fileChooser.getSelectedFiles();
			
			for(int i = 0;i<selectedfile.length;i++)
			{	
		      if(editedString.replace("\\\\", "\\").contains(selectedfile[i].getAbsolutePath())) //dont take same file from same directory
		    	  continue;
				files_selected+=selectedfile[i].getAbsolutePath()+";";
				editedString= files_selected.replace("\\", "\\\\");
				filename+=selectedfile[i].getName()+";";
				
			
			}
			textField.setText(editedString);
	

		}
	}
	
	private void browseDestination() {
		setCopyCheck(false);
		JFileChooser fileChooser_2 = new JFileChooser();
		fileChooser_2.setSize(250, 250);
		fileChooser_2.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		fileChooser_2.setMultiSelectionEnabled(isEnabled());
//		     	
		if(fileChooser_2.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
		{
			
			selectedDestination = fileChooser_2.getSelectedFiles();
			
			for(int i = 0;i<selectedDestination.length;i++)
			{	
		      if(editedString_2.replace("\\\\", "\\").contains(selectedDestination[i].getAbsolutePath())) //dont take same directory
		    	  continue;
				Destination_selected+=selectedDestination[i].getAbsolutePath()+";";
				editedString_2= Destination_selected.replace("\\", "\\\\");
				Destinationname+=selectedDestination[i].getName()+";";
				
			
			}
			textField_1.setText(editedString_2);	

		}
	}
	
	public void clearAll_Source(){
		
		list.removeAll();
		files_selected = "";
		editedString = "";
		filename = "";	
	}
	public void clearAll_Destination(){
		
		list_1.removeAll();
		Destination_selected = "";
		editedString_2 = "";
		Destinationname = "";	
	}
	
	public Font createfontI(){
		
		try
        {	
            InputStream is = new BufferedInputStream(
                    new FileInputStream("fonts//CALIFI.TTF"));
            fontI = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(17f);
        
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("font not loaded.  Using serif font.");
            fontI = new Font("serif", Font.PLAIN, 17);
        }
		
		return fontI;
		
	}
	
	public boolean isShutDownCheck() {
		return shutDownCheck;
	}
	public void setShutDownCheck(boolean shutDownCheck) {
		this.shutDownCheck = shutDownCheck;
	}
	public boolean isCopyCheck() {
		return copyCheck;
	}
	public void setCopyCheck(boolean copyCheck) {
		this.copyCheck = copyCheck;
	}
}


