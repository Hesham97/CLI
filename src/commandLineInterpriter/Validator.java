package commandLineInterpriter;

import java.util.regex.Pattern;

public class Validator {

	private Terminal terminal;

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
			}
			excute(commands, null);

		} else {
			excute(null, inputbuffer);

		}
	}

	public void excute(String[] command, String commands) throws Exception {
		String[] args = null;
		if (command == null) {
			args = commands.split(Pattern.quote(" "));

		} else {
			for (String x : command) {
				if (x.contains(" ")) {
					args = x.split(Pattern.quote(" "));
				}
			}

		}
		if (args[0].equals("cd")) {
			terminal.cd(args[1]);
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
		} else if(args[0].equals("more")) {
			if(args.length>0) {
				System.out.println("Hi");
				terminal.more(args[1]);
			}else {
				
			}
		}
	}
}
