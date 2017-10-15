package commandLineInterpriter;

import java.io.FileNotFoundException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gui gui = new Gui();
		Terminal t = new Terminal();
		try {
			System.out.println(t.cat("/home/hesham/Desktop/test.txt","/home/hesham/Desktop/ww.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//t.cd("2do.txt");
		//t.ls();
	}

}

