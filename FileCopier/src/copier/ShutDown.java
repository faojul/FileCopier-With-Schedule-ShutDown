package copier;

import java.io.IOException;

public class ShutDown {
	
	public ShutDown () {

	}
	
	public static void doShut(){
		
		Runtime runtime = Runtime.getRuntime();
		try{
		Process proc = runtime.exec("shutdown -s -t 0");
		}
		catch(IOException ie) {
            ie.printStackTrace();
        }
		System.exit(0);
	}
}
