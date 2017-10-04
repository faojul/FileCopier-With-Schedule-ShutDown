package copier;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.UnsupportedLookAndFeelException;

import Design.*;


public class Main  {
	    public static Window app;
	    public static Map<String, String> themenamesMap;
	    static int DUPLICATE = 0;

	    public static void main(String[] args) {

	        try {
	            ThemeManager.populateThemeNamesMap();
	            javax.swing.UIManager.setLookAndFeel(ThemeManager.getLastTheme());
	       }
	        catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
	            try {
	                javax.swing.UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
	            }
	            catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex1) {
	                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
	                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex1);
	            }
	             Logger.getLogger(Main.class.getName()).log(Level.SEVERE,null,ex);
	        }
		
		app=new Window();
		
		app.setSize(660, 660);
		app.setVisible(true);
		app.setAlwaysOnTop(false);
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		app.setLocationRelativeTo(null);
		app.setResizable(false);
        app.setFont(new java.awt.Font("Siyam Rupali", 0, 11));
	}
	

}
