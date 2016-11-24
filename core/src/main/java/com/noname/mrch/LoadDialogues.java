package com.noname.mrch;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Created by PPPPPP on 2016/11/23.
 */
public class LoadDialogues {

    public LoadDialogues() {
    }

    public static Map<String, List<String>> loadFromFile(String fileName){

        Map<String, List<String>> dialogues = new HashMap<String, List<String>>();

        List<String> lines;
        try {
            Path x = Paths.get(System.getProperty("user.dir") +
                    File.separator + fileName);
            lines = Files.readAllLines(x);
        } catch (IOException e) {
            return null;
        }
        lines.remove(0);
        for (String line : lines) {

            if (!line.isEmpty()){
                List<String> diaL = dialogues.get(line.substring(0,1));
                if (diaL == null) {
                    diaL = new ArrayList<String>();
                    dialogues.put(line.substring(0,1), diaL);
                }
                diaL.add(line.substring(1));
            }


        }
        return dialogues;
    }
}
