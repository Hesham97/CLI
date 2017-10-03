package commandLineInterpriter;

import java.util.Map;

public class Command {
	
	private String name;
	private String description;
	private Map arguments;
	
	public Command(String name,String description,Map arguments){
		
		this.name = name;
		this.description = description;
		this.arguments= arguments;
		
	}
	

	

}
