package com.noname.mrch.gui;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.SplitPane;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.Clue;
import com.noname.mrch.gameobject.ClueType;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Player;
import com.noname.mrch.gameobject.QuestioningStyle;

public class InteractionBox extends Table{
    private GameWorld gameWorld;
    private Gui gui;

    private GameCharacter interactingCharacter;

    private Skin skin;

    private Table choiceTable;

    private Stack secondColumnStack;
    private Table questionTable;
    private Table giveTable;
    private ScrollPane giveScrollPane;

    private AccuseWindow accuseWindow;

    private TextButton dialogueBox;

    public InteractionBox(Skin skin, Gui gui, GameWorld gameWorld){
        super(skin);

        this.gameWorld = gameWorld;
        this.gui = gui;
        this.skin = skin;

        align(Align.left);

        initChoiceTable();
        add(choiceTable).size(Value.percentWidth(0.2f, this), Value.percentHeight(1f, this));

        initQuestionTable();

        initGiveTable();

        initAccuseWindow();

        secondColumnStack = new Stack();
        add(secondColumnStack).size(Value.percentWidth(0.2f, this), Value.percentHeight(1f, this));
        secondColumnStack.add(questionTable);
        secondColumnStack.add(giveScrollPane);

        dialogueBox = new TextButton("", skin);
        add(dialogueBox).size(Value.percentWidth(0.6f, this), Value.percentHeight(1f, this));
    }

    public void setInteractingCharacter(GameCharacter interactingCharacter) {
        this.interactingCharacter = interactingCharacter;
        setDialogue(interactingCharacter.getGreeting());
    }

    private void initChoiceTable(){
        TextButton question = new TextButton("QUESTION", skin);
        question.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startQuestion();
            }
        });

        TextButton give = new TextButton("GIVE", skin);
        give.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startGive();
            }
        });

        TextButton accuse = new TextButton("ACCUSE", skin);
        accuse.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                startAccuse();
            }
        });

        TextButton ignore = new TextButton("IGNORE", skin);
        ignore.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gui.haltInteraction();
            }
        });

        choiceTable = new Table();
        choiceTable.setBackground(skin.getDrawable("default-round"));
        choiceTable.add(question);
        choiceTable.row();
        choiceTable.add(give);
        choiceTable.row();
        choiceTable.add(accuse);
        choiceTable.row();
        choiceTable.add(ignore);
    }

    private void initQuestionTable(){
        Player player = gameWorld.getPlayer();

        questionTable = new Table();
        questionTable.setBackground(skin.getDrawable("default-round"));
        TextButton questionStyle_1 = new TextButton(player.getQuestionSet().get(0).getQuestion(), skin);
        questionStyle_1.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                question(player.getQuestionSet().get(0));
            }
        });

        TextButton questionStyle_2 = new TextButton(player.getQuestionSet().get(1).getQuestion(), skin);
        questionStyle_2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                question(player.getQuestionSet().get(1));
            }
        });

        TextButton questionStyle_3 = new TextButton(player.getQuestionSet().get(2).getQuestion(), skin);
        questionStyle_3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                question(player.getQuestionSet().get(2));
            }
        });

        TextButton cancel = new TextButton("CANCEL", skin);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                haltQuestion();
            }
        });

        questionTable.add(questionStyle_1);
        questionTable.row();
        questionTable.add(questionStyle_2);
        questionTable.row();
        questionTable.add(questionStyle_3);
        questionTable.row();
        questionTable.add(cancel);

        questionTable.setVisible(false);
    }

    private void startQuestion(){
        choiceTable.setTouchable(Touchable.disabled);
        questionTable.setVisible(true);
    }

    private void question(QuestioningStyle questionStyle) {
        if (interactingCharacter.isAccused()) {
            setDialogue("Go Away");
        }

        else if (interactingCharacter.getClueList().size == 0){
            setDialogue("I have no clue");
        }

        // when the only clue the character has is the motive clue and the player had found less than 3 clues
        else if (interactingCharacter.getClueList().size == 1 &&
                interactingCharacter.getClueList().peek().getClueType() == ClueType.MOTIVE &&
                gameWorld.getNotebook().getClueList().size < 3) {
            setDialogue("I'll tell you something later");
        }

        else{
            int diff = Math.abs(questionStyle.getValue() - interactingCharacter.getPersonality().getValue());
            int chanceOfSuccess = MathUtils.random(1, 9)+ diff;

            if (chanceOfSuccess >= 5){
                // motive clue is always added first so the last index clue will always be an appearance clue
                Clue clue = interactingCharacter.getClueList().pop();

                gameWorld.getNotebook().addClue(clue);

                gui.displayInfo(clue, clue.getDescription());

                setDialogue(clue.getResponse());

            } else {
                setDialogue(interactingCharacter.getQuestionFailResponse());
            }
        }

        haltQuestion();
    }

    private void haltQuestion(){
        choiceTable.setTouchable(Touchable.enabled);
        questionTable.setVisible(false);
    }

    private void initGiveTable() {
        giveTable = new Table();
        giveTable.setBackground(skin.getDrawable("default-round"));

        giveScrollPane = new ScrollPane(giveTable, skin);
        giveScrollPane.setVisible(false);
    }

    private void startGive(){
        choiceTable.setTouchable(Touchable.disabled);
        giveScrollPane.setVisible(true);

        giveTable.clear();

        Array<Item> itemArray = gameWorld.getNotebook().getItemList();

        for (int i =0; i < itemArray.size; i++){
            Item item = itemArray.get(i);
            TextButton itemChoice = new TextButton(item.getName(), skin);
            itemChoice.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    give(item);
                }
            });

            giveTable.add(itemChoice);
            giveTable.row();
        }

        TextButton cancel = new TextButton("Cancel", skin);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                haltGive();
            }
        });
        giveTable.add(cancel);
    }

    private void give(Item givenItem){
        if (givenItem.getLinkedPersonId() == interactingCharacter.getId()){
            setDialogue("giveSuccessDialogue");
            gameWorld.getNotebook().removeItem(givenItem);

            Item returnItem = interactingCharacter.getItemList().get(0);
            gameWorld.getNotebook().addItem(returnItem);
            interactingCharacter.removeItem(returnItem);

            gui.displayInfo(returnItem, returnItem.getDescription());
        }

        else{
            setDialogue("giveFailDialogue");
        }
        haltGive();
    }

    private void haltGive(){
        choiceTable.setTouchable(Touchable.enabled);
        giveScrollPane.setVisible(false);
    }

    private void initAccuseWindow(){
        accuseWindow = new AccuseWindow(skin, gui, gameWorld);
    }

    private void startAccuse(){
        accuseWindow.initialize();
        accuseWindow.show(gui.getStage());
    }

    void accuse(){

    }

    private void setDialogue(String dialogue){
        dialogueBox.setText(dialogue);
    }

    public GameCharacter getInteractingCharacter() {
        return interactingCharacter;
    }
}
