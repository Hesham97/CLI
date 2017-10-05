package commandLineInterpriter;

import java.io.File;


public class Terminal {
	
	private static final String  DEFULT_PATH=System.getProperty("user.dir");
	private String currentPath;
	
	/*@TO-DO we must add the commands here as it 
	 * defined as final 
	private static final Command commands[];
	*/
	public Terminal() {
		currentPath = DEFULT_PATH; 
	}
	
	String getCurrentPath() { return currentPath;}
	
	void ls(){
		File file = new File(currentPath);
		File[] dirList = file.listFiles();
		for(File temp :dirList){
			System.out.println(temp.getName());
		}
	}
	
	void cd(String path) {
		if(path.equals("")||path.equals("~")) {
			System.setProperty("user.dir",DEFULT_PATH);
		}
	}
	
	
	

}
