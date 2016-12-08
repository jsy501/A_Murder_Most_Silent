package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.GameCharacter;
import com.noname.mrch.gameobject.NoteBook;
import com.noname.mrch.gameobject.Player;

public class InteractionBox extends Table{
    private GameWorld gameWorld;
    private Gui gui;

    private GameCharacter interactingCharacter;

    private Skin skin;

    private Table choiceTable;

    private Stack secondColumnStack;
    private Table questionTable;

    private TextButton dialogueBox;

    public InteractionBox(Skin skin, Gui gui, GameWorld gameWorld){
        super(skin);

        this.gameWorld = gameWorld;
        this.gui = gui;
        this.skin = skin;

        align(Align.left);

        initChoiceTable();
        add(choiceTable).size(Value.percentWidth(0.2f, this), Value.percentHeight(1f, this));

        initQuestionTable(gameWorld.getPlayer());
        initGiveTable(gameWorld.getNotebook());

        secondColumnStack = new Stack();
        add(secondColumnStack).size(Value.percentWidth(0.2f, this), Value.percentHeight(1f, this));
        secondColumnStack.add(questionTable);

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
                showQuestionTable();
            }
        });
        TextButton give = new TextButton("GIVE", skin);
        TextButton accuse = new TextButton("ACCUSE", skin);
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

    private void initQuestionTable(Player player){
        questionTable = new Table();
        questionTable.setBackground(skin.getDrawable("default-round"));
        TextButton questionStyle_1 = new TextButton(player.getQuestionSet().get(0).getQuestion(), skin);
        TextButton questionStyle_2 = new TextButton(player.getQuestionSet().get(1).getQuestion(), skin);
        TextButton questionStyle_3 = new TextButton(player.getQuestionSet().get(2).getQuestion(), skin);
        TextButton cancel = new TextButton("CANCEL", skin);
        cancel.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                hideQuestionTable();
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

    private void initGiveTable(NoteBook notebook) {
    }

    private void showQuestionTable(){
        choiceTable.setTouchable(Touchable.disabled);
        questionTable.setVisible(true);
    }

    private void hideQuestionTable(){
        choiceTable.setTouchable(Touchable.enabled);
        questionTable.setVisible(false);
    }

    private void setDialogue(String dialogue){
        dialogueBox.setText(dialogue);
    }
}
