package copier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import Design.Window;

public class CopyFile {

	private Window windo;
	private long fileSize;
	private String getextension="";
	private  String newdestination="";

	public CopyFile(String source, String destination, Window w) {
		this.windo = w;
		
		InputStream inStream = null;
		OutputStream outStream = null;

		try {

			File file1 = new File(source);
			fileSize = file1.length();
			File file2 = new File(destination);
			if(file2.exists()&&Main.DUPLICATE==0){
				
				   String[] buttons = { "Replace", "Keep both", "Skip" };
				   Main.DUPLICATE = JOptionPane.showOptionDialog(null, "Choose Your action ?", "Confirmation",
				   JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[1]);
			}
			else if(file2.exists()&&Main.DUPLICATE==1){
				int i = destination.lastIndexOf('.');
				if (i > 0) {
				    getextension = destination.substring(i+1);
				}
				newdestination=destination.substring(0, destination.lastIndexOf('.'));
				
				newdestination+="-"+System.currentTimeMillis()+"."+getextension;
				   file2 = new File(newdestination);
			}
			else if(file2.exists()&&(Main.DUPLICATE==2||Main.DUPLICATE==-1))
				return;
			inStream = new FileInputStream(file1);
			outStream = new FileOutputStream(file2);
			long completed = 0;

			byte[] buffer = new byte[1024*20];
			int length;
			
			while ((length = inStream.read(buffer)) > 0) {
				outStream.write(buffer, 0, length);
				completed+= length;
				windo.setCompleted((int)(completed*100.0/fileSize), length);
			}
			windo.setCompleted(100,length);
			if (inStream != null)
				inStream.close();
			if (outStream != null)
				outStream.close();

			System.out.println(" File Copied..");
			Window.CNT++;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
