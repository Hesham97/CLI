package commandLineInterpriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.IOException;

public class Terminal {

	private static final String USER_NAME = System.getProperty("user.name");
	private static final String DEFULT_PATH = "/home/" + USER_NAME
			;
	private static String currentPath;
	public static int moreCounter;
	public static boolean escIsPressed;
	public static String[] moreArray;
	public static String[] arrayOFCommands;
	public static int[] numberOfArguments;
	public static String[] commandArgs;
	public static String[] commandDescription;

	//
	/*
	 * TODO we must add the commands here as it defined as final private static
	 * final Command commands[];
	 */
	public Terminal() {
		currentPath = DEFULT_PATH;
		moreCounter = 0;
		escIsPressed = false;
		arrayOFCommands = new String[16];
		numberOfArguments = new int[16];
		commandArgs = new String[16];
		commandDescription = new String[16];

		arrayOFCommands[0] = "clear";
		numberOfArguments[0] = 0;
		arrayOFCommands[1] = "cd";
		numberOfArguments[1] = 1;
		arrayOFCommands[2] = "ls";
		numberOfArguments[2] = 0;
		arrayOFCommands[3] = "cp";
		numberOfArguments[3] = 2;
		arrayOFCommands[4] = "mv";
		numberOfArguments[4] = 2;
		arrayOFCommands[5] = "rm";
		numberOfArguments[5] = 1;
		arrayOFCommands[6] = "mkdir";
		numberOfArguments[6] = 1;
		arrayOFCommands[7] = "rmdir";
		numberOfArguments[7] = 1;
		arrayOFCommands[8] = "cat";
		numberOfArguments[8] = 0;
		arrayOFCommands[9] = "more";
		numberOfArguments[9] = 1;
		arrayOFCommands[10] = "less";
		numberOfArguments[10] = 1;
		arrayOFCommands[11] = "pwd";
		numberOfArguments[11] = 0;
		arrayOFCommands[12] = "find";
		numberOfArguments[12] = 2;
		arrayOFCommands[13] = "args";
		numberOfArguments[13] = 1;
		arrayOFCommands[14] = "help";
		numberOfArguments[14] = 0;
		arrayOFCommands[15] = "date";
		numberOfArguments[15] = 0;

		commandArgs[0] = "no args";
		commandArgs[1] = "Path";
		commandArgs[2] = "no args";
		commandArgs[3] = "Path1, Path2";
		commandArgs[4] = "Path1, Path2";
		commandArgs[5] = "Name of file";
		commandArgs[6] = "Name of Directory";
		commandArgs[7] = "Name of Directory";
		commandArgs[8] = "Name of file, Other use using > and >>";
		commandArgs[9] = "Name of file";
		commandArgs[10] = "Name of file";
		commandArgs[11] = "No args";
		commandArgs[12] = "Name of file";
		commandArgs[13] = "Name of Command";
		commandArgs[14] = "No args";
		commandArgs[15] = "No args";

		commandDescription[0] = "Command Desc";
		commandDescription[1] = "Command Desc";
		commandDescription[2] = "Command Desc";
		commandDescription[3] = "Command Desc";
		commandDescription[4] = "Command Desc";
		commandDescription[5] = "Command Desc";
		commandDescription[6] = "Command Desc";
		commandDescription[7] = "Command Desc";
		commandDescription[8] = "Command Desc";
		commandDescription[9] = "Command Desc";
		commandDescription[10] = "Command Desc";
		commandDescription[11] = "Command Desc";
		commandDescription[12] = "Command Desc";
		commandDescription[13] = "Command Desc";
		commandDescription[14] = "Command Desc";
		commandDescription[15] = "Command Desc";

	}


	public static void help() {
		
		for (int i = 0; i < arrayOFCommands.length; i++) {
			Gui.centerTextArea.append(arrayOFCommands[i]+" :\n");
			Gui.centerTextArea.append("\t\t Desription: "+commandDescription[i]+" :\n");
			Gui.centerTextArea.append("\t\t has " + numberOfArguments[i] + "Args" + "\n");
			Gui.centerTextArea.append( "\t\t Args " + commandArgs[i] + "\n");
		}
	}
	
	public static void args(String temp) {
		int index = -1;
		for (int i = 0; i < arrayOFCommands.length; i++) {
			System.out.println();
			if (arrayOFCommands[i].equals(temp))
				index = i;
		}
		if (index >= 0) {
			Gui.centerTextArea.append(temp + ": has " + numberOfArguments[index] + "Args" + "\n");
			Gui.centerTextArea.append(temp + ": Args " + commandArgs[index] + "\n");
		} else {
			Gui.centerTextArea.append("no such command\n");
		}
	}

	public static String getCurrentPath() {
		return currentPath;
	}

	public static String getUserName() {
		return USER_NAME;
	}

	// han4of han3ml eh hena hansibha kda wla eh
	public void pwd() {
		Gui.centerTextArea.append(currentPath);
	}

	public void ls() {
		File file = new File(currentPath);
		File[] dirList = file.listFiles();
		int c = 0;
		if (dirList != null)

			for (File temp : dirList) {
				Gui.centerTextArea.append(temp.getName());
				if (c != 4)
					Gui.centerTextArea.append("\t\t");
				else {
					Gui.centerTextArea.append("\n");
					c = 0;
				}

				c++;
			}
	}

	public void cd(String path) {
		// cd ..
		if(path.equals("..")) {
			int i, index=-1;
			for(i = currentPath.length()-2; i >= 0; i --){  
				if (currentPath.charAt(i)=='/') {
					index= i;
					break;
				}
			}
			if(index>0)
				currentPath = currentPath.substring(0,index);
			
		}
		else if (path.equals("") || path.equals("~")) {
			currentPath = DEFULT_PATH;
		} else if (path.equals("/")) {
			currentPath = "/";
		} else {
			File file = new File(currentPath + "/" + path);
			if (file.exists()) {
				if (file.isDirectory())
					currentPath = currentPath + "/" + path;
				else
					Gui.centerTextArea.append("This isn't a Dir");
			} else {
				Gui.centerTextArea.append("This Dir not found");
			}
		}
	}

	public void getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		Gui.centerTextArea.append(dateFormat.format(date));

	}

	public void mkdir(String filename) {
		File file = new File(currentPath + "/" + filename);
		if (!file.exists()) {
			file.mkdir();
		} else {
			Gui.centerTextArea.append("mkdir: cannot create directory `" + filename + "’: File exists\n");
		}
	}

	/*
	 * lam nige ncall el fn dia han4of m3aha -r wla l2 lw m3aha hanb3t 1 lw l2 0
	 */
	public void rmdir(String filename, String arg) {
		// @TO-DO check the sec args
		File file = new File(currentPath + "/" + filename);
		File[] files = file.listFiles();

		if (file.exists()) {
			if (files.length > 0) {
				if (arg.equals("-r")) {
					for (File temp : files) {
						temp.delete();
					}
					file.delete();
				} else {
					Gui.centerTextArea.append("rmdir: invalid option -- 'r'\n");
				}
			} else {
				file.delete();
			}
		} else {
			Gui.centerTextArea.append("mkdir: cannot delete directory `" + filename + "’: no such directory.\n");
		}

	}

	public String cp(String fileNameOne, String fileNameTwo) throws Exception {
		File fileOne;
		File fileTwo;

		fileOne = new File(currentPath + "/" + fileNameOne);
		fileTwo = new File(currentPath + "/" + fileNameTwo);

		if (fileOne.isFile()) {

			if (!fileTwo.isDirectory() && fileNameTwo.contains(".txt"))
				if (fileOne.exists()) {
					System.out.println("enter rerere");
					this.copy(fileOne.toString(), fileTwo.toString());
				}

				else {
					return "no such file name " + fileNameOne;
				}

			// second case
			if (!fileTwo.isFile() && !fileNameTwo.contains(".txt"))
				if (fileOne.exists()) {
					System.out.println("enter lol");
					fileTwo.mkdir();
					this.copy(fileOne.toString(), fileTwo.toString() + "/" + fileNameOne);

				}

				else {

					return "no such file name " + fileNameOne;
				}

		}
		// case three
		System.out.println(fileOne.isDirectory());
		if (fileOne.isDirectory()) {
			if (!fileTwo.isFile() && !fileNameTwo.contains(".txt")) {
				System.out.println("enter here");

				this.copydir(fileOne, fileTwo);
			} else {

				return "no such file name " + fileNameOne;
			}
		}

		/*
		 * else if (fileOne.isFile() ) {
		 * if(!fileTwo.isFile()&&!fileNameTwo.contains(".txt")) if (fileOne.exists()) {
		 * System.out.println("enter lol"); this.copy(fileOne.toString(),
		 * fileTwo.toString() + fileNameOne);
		 * 
		 * }
		 * 
		 * else { return "no such file name " + fileNameOne; }
		 * 
		 * }
		 * 
		 * else if (fileOne.isDirectory() &&fileTwo.isDirectory()) {
		 * this.copydir(fileOne, fileTwo); }
		 */

		return " ";
	}

	private void copy(String f1, String f2) throws Exception {

		File FileName = new File(f1);
		FileInputStream in = new FileInputStream(FileName.toString());
		int filelen = (int) FileName.length();
		byte Bytes[] = new byte[filelen];

		@SuppressWarnings("unused")
		int x = in.read(Bytes);

		File temp = new File(f2);
		temp.createNewFile();
		FileOutputStream out = new FileOutputStream(f2);

		for (int i = 0; i < filelen; i++) {
			out.write(Bytes[i]);
		}

		// close files
		in.close();
		out.close();

	}

	private boolean copydir(File f1, File f2) throws Exception {

		if (f1.isDirectory()) {
			if (!f2.isDirectory()) {

				f2.mkdir();
			}

			FileUtils.copyDirectory(f1, f2);
			return true;

		} else {
			Gui.centerTextArea.append("in valid dir \n");
			return false;
		}
	}

	public boolean mv(String fileNameOne, String fileNameTwo) throws Exception {
		File fileOne;
		File fileTwo;

		fileOne = new File(currentPath + "/" + fileNameOne);
		fileTwo = new File(currentPath + "/" + fileNameTwo);

		if (fileOne.isFile()) {

			if (!fileTwo.isDirectory() && fileNameTwo.contains(".txt"))
				if (fileOne.exists()) {
					System.out.println("enter rerere");
					this.copy(fileOne.toString(), fileTwo.toString());
					fileOne.delete();
				}

				else {
					System.out.println("no");
					return false;
				}

			// second case
			System.out.println(!fileTwo.isFile() + " " + fileOne.exists());
			if (!fileTwo.isFile() && !fileNameTwo.contains(".txt"))
				if (fileOne.exists()) {
					System.out.println("enter lol");
					fileTwo.mkdir();
					this.copy(fileOne.toString(), fileTwo.toString() + "/" + fileNameOne);
					fileOne.delete();

				}

				else {
					System.out.println("two no");
					return false;

				}

		}
		// case three
		// F
		System.out.println(fileOne.isDirectory());
		if (!fileOne.isDirectory()) {
			if (!fileTwo.isFile() && !fileNameTwo.contains(".txt")) {
				System.out.println("enter here");

				this.copydir(fileOne, fileTwo);
				this.rmdir(fileNameOne, "-r");
			} else {
				System.out.println("third no");
				return false;
			}
		}
		/*
		 * if (casesCounter == 1)
		 * 
		 * { if (fileOne.exists()) { this.copy(fileOne.toString(), fileTwo.toString());
		 * fileOne.delete(); }
		 * 
		 * else { return false; } }
		 * 
		 * if (casesCounter == 2)
		 * 
		 * { if (fileOne.exists()) { if (!fileTwo.isDirectory()) { fileTwo.mkdir(); }
		 * 
		 * this.copy(fileOne.toString(), fileTwo.toString() + fileNameOne);
		 * fileOne.delete(); }
		 * 
		 * else { return false; } }
		 * 
		 * if (casesCounter == 3)
		 * 
		 * { if (fileOne.isDirectory()) { if (!fileTwo.isDirectory()) { fileTwo.mkdir();
		 * }
		 * 
		 * this.copydir(fileOne, fileTwo); this.rmdir(fileNameOne, "-r"); }
		 * 
		 * else { return false; } }
		 */

		return false;

	}

	@SuppressWarnings("resource")
	public String cat(String pathOne) throws FileNotFoundException {
		File fileOne = new File(currentPath+"/"+pathOne);
		System.out.println(fileOne.getPath());
		System.out.println(fileOne.getPath());

		if (fileOne.exists())
			return new Scanner(fileOne).useDelimiter("\\A").next();
		else 
			System.out.println("wwwwwwwwwww");
		return null;

	}

	@SuppressWarnings("resource")
	public String cat(String pathOne, String pathTwo) throws FileNotFoundException {
		File fileOne = new File(currentPath+"/"+pathOne);
		File fileTwo = new File(currentPath+"/"+pathTwo);
		if (fileOne.exists() && fileTwo.exists())
			return new Scanner(fileOne).useDelimiter("\\A").next() + new Scanner(fileTwo).useDelimiter("\\A").next();
		return null;
	}

	public static void clear() {
		Gui.centerTextArea.setText("");
	}

	public static int more(String arg, int index) throws FileNotFoundException, IOException {

		File file = new File(currentPath + "/" + arg);
		int c = 0;
		if (file.exists()) {

			@SuppressWarnings("resource")
			BufferedReader in = new BufferedReader(new FileReader(currentPath + "/" + arg));
			String str;

			List<String> list = new ArrayList<String>();
			while ((str = in.readLine()) != null) {
				list.add(str);
			}
			moreArray = list.toArray(new String[0]);
			c = 0;

			for (int i = index; i < moreArray.length; i++) {
				if (c < 10) {
					Gui.centerTextArea.append(moreArray[i] + "\n");
					c++;
					moreCounter++;
				} else if (escIsPressed) {
					System.out.println("S");
					c = 0;
				}

			}
		} else {
			Gui.centerTextArea.append("NO SUCH FILE OR DIR \n");
		}
		return c;
	}

	public boolean check(String temp,String s[])
	{
		int index = -1;
		for (int i = 0; i < s.length; i++) {
			System.out.println();
			if (s[i].equals(temp))
			{
				index = i;
			return true;	
			}			
		}
		return false;
	}
	
	public void question(String temp)
	{
		int index = -1;
		for (int i = 0; i < arrayOFCommands.length; i++) {
			System.out.println();
			if (arrayOFCommands[i].equals(temp))
				index = i;
		}
		if (index >= 0) {
			Gui.centerTextArea.append(temp + ": has " + numberOfArguments[index] + "Args" + "\n");
			Gui.centerTextArea.append(temp + ": Args " + commandArgs[index] + "\n");
		} else {
			Gui.centerTextArea.append("no such command\n");
		}
	}
	
	public void Exit()
	{
		
		System.exit(0);
	}
}
