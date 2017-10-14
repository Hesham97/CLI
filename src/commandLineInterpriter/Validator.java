package commandLineInterpriter;

import java.util.regex.Pattern;

public class Validator {
	
	private Terminal terminal;
	
	public Validator() {
		terminal = new Terminal();
	}
	
	public String Validate(String inputbuffer) {
		
		int index=inputbuffer.indexOf("$");
		String[] commands = null ;
		inputbuffer = inputbuffer.substring(index+2, inputbuffer.length());
		if(inputbuffer.contains("|")) {
			commands =inputbuffer.split(Pattern.quote("|"));
			for(String x : commands) {
				System.out.println(x);
			}
			excute(commands);

		}
			//excute();

		return inputbuffer;
	}
	
	public void excute(String[] commands) {
		String [] args = null;
		for(String x:commands) {
			if(x.contains(" ")) {
				args =x.split(Pattern.quote(" "));
		}
		else if(args[0].equals("cd")) {
			System.out.println("Here");

			terminal.cd(args[1]);
		}
		else if(args[0].equals("ls")) {
			terminal.ls();
		}
		else if(args[0].equals("pwd")) {
			terminal.pwd();
		}
		else if(args[0].equals("date")) {
			terminal.getDate();
		}
			
		
		
	}
	
}
}
