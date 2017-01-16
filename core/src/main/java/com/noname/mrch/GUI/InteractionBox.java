package com.noname.mrch.gui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gameobject.Interaction;
import com.noname.mrch.gameobject.Item;
import com.noname.mrch.gameobject.Player;

public class InteractionBox extends Table{
    private Interaction interaction;
    private GameWorld gameWorld;
    private Gui gui;

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

        interaction = new Interaction(this);

        align(Align.left);

        initChoiceTable();
        add(choiceTable).size(Value.percentWidth(0.2f, this), Value.percentHeight(1f, this)).left();

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
                interaction.question(player.getQuestionSet().get(0));
            }
        });

        TextButton questionStyle_2 = new TextButton(player.getQuestionSet().get(1).getQuestion(), skin);
        questionStyle_2.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                interaction.question(player.getQuestionSet().get(1));
            }
        });

        TextButton questionStyle_3 = new TextButton(player.getQuestionSet().get(2).getQuestion(), skin);
        questionStyle_3.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                interaction.question(player.getQuestionSet().get(2));
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

    public void haltQuestion(){
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
                    interaction.give(item);
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

    public void haltGive(){
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

    public void setDialogue(String dialogue){
        dialogueBox.setText(dialogue);
    }

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    public Gui getGui() {
        return gui;
    }

    public Interaction getInteraction() {
        return interaction;
    }
}
