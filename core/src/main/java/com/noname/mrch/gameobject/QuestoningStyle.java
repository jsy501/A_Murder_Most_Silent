package com.noname.mrch.gameobject;

/**
 * enum holding the questioning styles and the associated
 * default values.
 */
public enum QuestoningStyle {
    Q1(1, "what do you know"), Q2(2, "what do you know"),
    Q3(3, "what do you know"), Q4(4,"what do you know"),
    Q5(5,"what do you know"), Q6(6,"what do you know"),
    Q7(7,"what do you know"), Q8(8,"what do you know"),
    Q9(9,"what do you know");

    private int value;
    private String question;

    private QuestoningStyle(int value, String question) {
        this.value = value;
        this.question = question;
    }

    public int getValue(){
        return value;
    }

    public String getQuestion() {
        return question;
    }
}
