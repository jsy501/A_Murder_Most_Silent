package com.noname.mrch.gameobject;

/**
 * enum holding the questioning styles and the associated
 * default values.
 */
public enum QuestioningStyle {
    Q1(1, "This is question style 1"), Q2(2, "This is question style 2"),
    Q3(3, "This is question style 3"), Q4(4, "This is question style 4"),
    Q5(5, "This is question style 5"), Q6(6, "This is question style 6"),
    Q7(7, "This is question style 7"), Q8(8, "This is question style 8"),
    Q9(9, "This is question style 9");

    private int value;
    private String question;

    QuestioningStyle(int value, String question) {
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
