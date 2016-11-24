package com.noname.mrch;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by PPPPPP on 2016/11/24.
 */
public class Dialogue {
    public Map<String, List<String>> dialogues;

    public Dialogue(String fileName) {

       dialogues= LoadDialogues.loadFromFile(fileName);
    }

    public List getGreeting() {
        List greeting;
        greeting = dialogues.get("/");
    //greeting by "/"
        return greeting;
    }

    public List getBye(){
        List bye;
        bye = dialogues.get("*");
    //byes by '*'
        return bye;
    }


}
