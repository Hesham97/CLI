package commandLineInterpriter;

import java.nio.file.Files;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.io.IOException;
import java.io.IOException;

public class Terminal {

	private static final String USER_NAME = System.getProperty("user.name");
	private static final String DEFULT_PATH = "/home/" + USER_NAME + "/";
	private static String currentPath;

	//
	/*
	 * TODO we must add the commands here as it defined as final private static
	 * final Command commands[];
	 */
	public Terminal() {
		currentPath = DEFULT_PATH;
	}

	public static String getCurrentPath() {
		return currentPath;
	}

	public static String getUserName() {
		return USER_NAME;
	}

	// han4of han3ml eh hena hansibha kda wla eh
	public void pwd() {
		System.out.println(currentPath);
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
		if (path.equals("") || path.equals("~")) {
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
			Gui.centerTextArea.append("mkdir: cannot create directory `" + filename + "’: File exists");
		}
	}

	/*
	 * lam nige ncall el fn dia han4of m3aha -r wla l2 lw m3aha hanb3t 1 lw l2 0
	 */
	public void rmdir(String filename, boolean r) {
		File file = new File(currentPath + "/" + filename);
		if (file.exists()) {
			if (!r) {
				file.delete();
			}
			if (r) {
				File[] files = file.listFiles();
				for (File temp : files) {
					temp.delete();
				}

			} else {
				Gui.centerTextArea.append("mkdir: cannot delete directory `" + filename + "’: no such directory.");
			}
		}

	}

	public String cp(String fileNameOne, String fileNameTwo, int casesCounter) throws Exception {
		File fileOne;
		File fileTwo;

		fileOne = new File(currentPath + "/" + fileNameOne);
		fileTwo = new File(currentPath + "/" + fileNameTwo);

		if (casesCounter == 1) {

			if (fileOne.exists()) {
				this.copy(fileOne.toString(), fileTwo.toString());

			}

			else {
				return "no such file name " + fileNameOne;
			}

		}

		else if (casesCounter == 2) {

			if (fileOne.exists()) {
				this.copy(fileOne.toString(), fileTwo.toString() + fileNameOne);

			}

			else {
				return "no such file name " + fileNameOne;
			}

		}

		else if (casesCounter == 3) {
			this.copydir(fileOne, fileTwo);
		}

		return " ";
	}

	private void copy(String f1, String f2) throws Exception {

		File FileName = new File(f1);
		FileInputStream in = new FileInputStream(FileName.toString());
		int filelen = (int) FileName.length();
		byte Bytes[] = new byte[filelen];

		int x = in.read(Bytes);

		FileOutputStream out = new FileOutputStream(f2);

		for (int i = 0; i < filelen; i++) {
			out.write(Bytes[i]);
		}

		// close files
		in.close();
		out.close();

	}

	private boolean createFile(String f1) throws Exception {

		File FileName = new File(f1);

		return FileName.createNewFile();
	}

	private boolean copydir(File f1, File f2) throws Exception {

		if (f1.isDirectory()) {
			if (!f2.isDirectory()) {

				f2.mkdir();
			}

			FileUtils.copyDirectory(f1, f2);
			return true;

		} else {
			Gui.centerTextArea.append("in valid dir");
			return false;
		}
	}

	public boolean mv(String fileNameOne, String fileNameTwo, int casesCounter) throws Exception {
		File fileOne;
		File fileTwo;

		fileOne = new File(currentPath + "/" + fileNameOne);
		fileTwo = new File(currentPath + "/" + fileNameTwo);
		if (casesCounter == 1)

		{
			if (fileOne.exists()) {
				this.copy(fileOne.toString(), fileTwo.toString());
				fileOne.delete();
			}

			else {
				return false;
			}
		}

		if (casesCounter == 2)

		{
			if (fileOne.exists()) {
				if (!fileTwo.isDirectory()) {
					fileTwo.mkdir();
				}

				this.copy(fileOne.toString(), fileTwo.toString() + fileNameOne);
				fileOne.delete();
			}

			else {
				return false;
			}
		}

		if (casesCounter == 3)

		{
			if (fileOne.isDirectory()) {
				if (!fileTwo.isDirectory()) {
					fileTwo.mkdir();
				}

				this.copydir(fileOne, fileTwo);
				this.rmdir(fileNameOne, true);
			}

			else {
				return false;
			}
		}

		return false;

	}

}
