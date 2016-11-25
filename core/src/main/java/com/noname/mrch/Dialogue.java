package main.java.com.noname.mrch;



import java.util.*;

/**
 * Created by PPPPPP on 2016/11/24.
 */
public class Dialogue {
    public Map<String, List<String>> dialogues;
    public Character character;
    public int questionStyle;

    public int getQuestionStyle() {
        return questionStyle;
    }

    public void setQuestionStyle(int questionStyle) {
        this.questionStyle = questionStyle;
    }

    public Dialogue(Character character, String fileName) {
        this.character = character;
        this.dialogues= LoadDialogues.loadFromFile(fileName);
    }
    //greeting by "/"
    public List getGreeting() {
        List greeting;
        greeting = dialogues.get("/");
        return greeting;
    }

    //byes by '*'
    public List getBye(){
        List bye;
        bye = dialogues.get("*");
        return bye;
    }
    //Question key "%"
    public List getQuestion(int playerPersonality){
        List question = null ;
        for (String a : dialogues.get("%")){
            if (Integer.valueOf(a.substring(0,1))==playerPersonality){
                question.add(a.substring(1));
            }

        }
        return question;
    }

    public Map questionChoice(){
        Map<String, List<String>> questionchoice = new HashMap<String, List<String>>();
        for (String a : dialogues.get("%")){if (!a.isEmpty()){
            List<String> questions = questionchoice.get(a.substring(0,1));
            if (questions == null) {
                questions = new ArrayList<String>();
                questionchoice.put(a.substring(0,1),questions);
            }
            questions.add(a.substring(1));
            }

        }
        return questionchoice;
    }


    //Use "@" represents dialogues when the item match
    //Use "#" represents dialogues when not match
    public List getGive(Item item){
        List give ;
        if(character.getItemList().contains(item,true)){
            NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove item from player and character.
            character.removeClue(character.getClueList().get(0));
            character.removeItem(item);
            return give = dialogues.get("@");
        }
        else{
            return give = dialogues.get("#");

        }
    }

    public void getResponse(Player player, int questionSytle){
        Random rn = new Random();
        if (character.getPersonality() == 2){
            NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove clue from player and character.
            character.removeClue(character.getClueList().get(0));
        }

        if(character.getPersonality() == 1){
            int  n = rn.nextInt(10) + 1;
            if(player.getPersonality()+questionSytle>0 && n>=8){
                NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove clue from player and character.
                character.removeClue(character.getClueList().get(0));
            }
        }
        else{
            int  n = rn.nextInt(10) + 1;
            if(n>=5){
                NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove clue from player and character.
                character.removeClue(character.getClueList().get(0));
            }
        }



        if(character.getPersonality() == 0){
            int  n = rn.nextInt(10) + 1;
            if(player.getPersonality()+questionSytle>1 && n>=5){
                NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove clue from player and character.
                character.removeClue(character.getClueList().get(0));
            }
        }
        else{
            int  n = rn.nextInt(10) + 1;
            if(n>=7){
                NoteBook.addClue(character.getClueList().get(0));  //add target clue to notebook remove clue from player and character.
                character.removeClue(character.getClueList().get(0));
            }
        }
    }

}