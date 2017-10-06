package commandLineInterpriter;

import java.io.File;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Terminal {
	
	private static final String USER_NAME=System.getProperty("user.name");
	private static final String  DEFULT_PATH="/home/"+USER_NAME+"/";
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
			currentPath = DEFULT_PATH;	
		}
		else if(path.equals("/")) {
			currentPath= "/";
		}
		else {
			File file = new File(currentPath+"/"+ path);
			if(file.exists()) {
				currentPath = currentPath+"/"+ path;
			}
			else {
				System.out.println("This Dir not found");
			}
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
	
	/* lam nige ncall el fn dia han4of m3aha -r wla l2 lw m3aha hanb3t 1 lw l2 0 
	*/
	public void rmdir(String filename, boolean r) {
		File file = new File(currentPath + "/" + filename);
		if(file.exists()) {
			if(!r) {
				file.delete(); 
				}
			if(r) {
				File [] files = file.listFiles();
				for(File temp :files) {
					temp.delete();
				}
				
			}
		else {
			System.out.println("mkdir: cannot delete directory `"+filename+"’: no such directory.");
		}
		}
		
	}
	
	
	
	
	
	
	

}
