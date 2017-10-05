package commandLineInterpriter;

import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Terminal {
	
	private static final String USER_NAME=System.getProperty("user.name");
	private static final String  DEFULT_PATH=System.getProperty("user.dir");
	private String currentPath;
	
	/*TODO we must add the commands here as it 
	 * defined as final 
	private static final Command commands[];
	*/
	public Terminal() {
		currentPath = DEFULT_PATH; 
	}
	
	public String getCurrentPath() { return currentPath;}
	
	//han4of han3ml eh hena hansibha kda wla  eh 
	public void pwd() {System.out.println(currentPath);}

	
	public void ls(){
		File file = new File(currentPath);
		File[] dirList = file.listFiles();
		for(File temp :dirList){
			System.out.println(temp.getName());
		}
	}
	
	public void cd(String path) {
		if(path.equals("")||path.equals("~")) {
			System.setProperty("user.dir",DEFULT_PATH);
		}
	}
	
	public void getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));

	}
	
	public void mkdir(String filename) {
		File file = new File(currentPath + "/" + filename);
		if(!file.exists()) {
			file.mkdir();
		}
		else {
			System.out.println("mkdir: cannot create directory `"+filename+"’: File exists");
		}
		
	}
	
	
	
	
	
	

}
