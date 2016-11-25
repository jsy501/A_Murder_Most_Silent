package main.java.com.noname.mrch;

public class Room extends HasObject {


	private int id;
	private Boolean isLocked;
	private String name;

	
	public Room(int id, String name, boolean locked){
		this.id = id;
		this.name = name;
		this.isLocked = locked;
	}
}
	
	
