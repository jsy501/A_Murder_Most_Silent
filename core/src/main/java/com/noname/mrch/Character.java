package main.java.com.noname.mrch;

public class Character extends HasObject{

	private int id;
	private String name;
	private int personality, role;
	private Boolean isFalseAccused;
	private Dialogue dialogue;

	public int getPersonality() {
		return personality;
	}

	public Boolean isFalseAccused() {
		return isFalseAccused;
	}

	public void setFalseAccused(Boolean falseAccused) {
		isFalseAccused = falseAccused;
	}

	public String getName() {
		return name;
	}
}