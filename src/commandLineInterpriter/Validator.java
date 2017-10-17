package commandLineInterpriter;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Validator {

	private Terminal terminal;
	FileWriter fw;
	public static String[] args = null;

	public Validator() {
		terminal = new Terminal();
	}

	public void Validate(String inputbuffer) throws Exception {

		int index = inputbuffer.indexOf("$");
		String[] commands = null;
		inputbuffer = inputbuffer.substring(index + 2, inputbuffer.length());
		if (inputbuffer.contains("|")) {
			commands = inputbuffer.split(Pattern.quote("|"));
			for (String x : commands) {
				System.out.println(x);
				excute(commands, null);
			}
			

		} else {
			excute(null, inputbuffer);

		}
	}

	public void excute(String[] command, String commands) throws Exception {
		if (command == null) {
			args = commands.split(Pattern.quote(" "));

		} else {
			for (String x : command) {
				if (x.contains(" ")) {
					args = x.split(Pattern.quote(" "));
				}
			}

		}
		
		if(args[0].equals("")&&!args[1].equals(""))
		{
			for(int i=0;i<args.length-1;i++)
			{
				args[i]=args[i+1];
			
			}
		}
		for(String x :args)
		{
			System.out.println(x+"z");
		}
		if (args[0].equals("cd")) {
			if (args.length >= 2)
				terminal.cd(args[1]);
			else
				terminal.cd("");
		} else if (args[0].equals("ls")) {
			terminal.ls();
		} else if (args[0].equals("pwd")) {
			terminal.pwd();
		} else if (args[0].equals("date")) {
			terminal.getDate();
		} else if (args[0].equals("mkdir")) {
			terminal.mkdir(args[1]);
		} else if (args[0].equals("rmdir")) {
			if (args.length > 2) {
				terminal.rmdir(args[1], args[2]);
			} else {
				terminal.rmdir(args[1], "");
			}
		} else if (args[0].equals("cp")) {
			terminal.cp(args[1], args[2]);
		} else if (args[0].equals("mv")) {
			terminal.mv(args[1], args[2]);
		} else if (args[0].equals("more")) {
			if (args.length > 0) {
				System.out.println("Hi");
				Terminal.more(args[1], 0);
			} else {
				Gui.centerTextArea.append("Invalied Argument \n");
			}
		} else if (args[0].equals("clear")) {
			Terminal.clear();
		} else if (args[0].equals("args")) {
			if (args.length >= 2) {
				Terminal.args(args[1]);
				System.out.println("argsssssssss");
			} else
				Gui.centerTextArea.append("Args Must have Argument\n");
		}else if (args[0].equals("cat")) {
			String s;
			System.out.println(args.length);

			if (args.length == 2) {
				s = terminal.cat(args[1]);
				System.out.println("ss");
				Gui.centerTextArea.append(s+"\n");
			}

			else if (args.length == 3) {
				if (!(terminal.check(">>",args) || !(terminal.check(">",args)))) {
					s = terminal.cat(args[1], args[2]);
					Gui.centerTextArea.append(s+"\n");
				} else {
					s = "invalid operation";
				}
			} else if (args.length == 4) {
				if ((terminal.check(">>",args) || (terminal.check(">",args)))) {
					System.out.println("jjjjjjjj");
					s = terminal.cat(args[1]);
					if (args[2].equals(">")) {
						System.out.println("jss");
						terminal.cp(args[1], args[3]);
					}

					else if (args[2].equals(">>")) {
						String fileTwoText = new Scanner(Terminal.getCurrentPath()+args[3]).useDelimiter("\\A").next();
						fileTwoText = fileTwoText + s;
						BufferedWriter p = new BufferedWriter(new FileWriter(Terminal.getCurrentPath()+args[3]));
						p.write(fileTwoText);
						p.close();
					}

				} else {
					s = "invalid operation";
				}
			}

		}
		
		else if(args[0].equals("help"))
		{
			terminal.help();
		}
		
		else if(args[0].equals("?"))
		{
			if(args.length==2)
			{
				terminal.question(args[1]);
			}
			
			else
			{
				Gui.centerTextArea.append("invalid command");
			}
		}
		
		else if(args[0].equals("exit"))
		{
			terminal.Exit();
		}
		
		else
		{
			Gui.centerTextArea.append("iinvalid command");
		}
	}
}
