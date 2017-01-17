package com.noname.mrch.gameobject;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.noname.mrch.GameWorld;
import com.noname.mrch.gui.Gui;
import com.noname.mrch.gui.InteractionBox;

/**
 * Contains the character interaction logic; question, give and accuse
 */

public class Interaction{
    private final int MINIMUM_APPEARANCE_CLUE_COUNT = 2;

    private InteractionBox interactionBox;
    private GameWorld gameWorld;
    private Gui gui;

    private GameCharacter interactingCharacter;

    public Interaction(InteractionBox interactionBox){
        this.interactionBox = interactionBox;
        gameWorld = interactionBox.getGameWorld();
        gui = interactionBox.getGui();
    }

    public void setInteractingCharacter(GameCharacter interactingCharacter) {
        this.interactingCharacter = interactingCharacter;
        interactionBox.setDialogue(interactingCharacter.getGreeting());
    }

    public GameCharacter getInteractingCharacter() {
        return interactingCharacter;
    }

    /**
     * Logic for question interaction
     * @param questionStyle question style selected by the player
     */

    public void question(QuestioningStyle questionStyle){
        if (interactingCharacter.isAccused()) {
            interactionBox.setDialogue("Go Away");
        }

        else if (interactingCharacter.getClueList().size == 0){
            interactionBox.setDialogue("I have no clue");
        }

        // when the only clue the character has is the motive clue and the player had found less than 3 clues
        else if (interactingCharacter.getClueList().size == 1 &&
                interactingCharacter.getClueList().peek().getClueType() == ClueType.MOTIVE &&
                gameWorld.getNotebook().getClueList().size < 3) {
            interactionBox.setDialogue("I'll tell you something later");
        }

        else{
            int diff = Math.abs(questionStyle.getValue() - interactingCharacter.getPersonality().getValue());
            int chanceOfSuccess = MathUtils.random(1, 9)+ diff;

            if (chanceOfSuccess >= 5){
                // motive clue is always added first so the last index clue will always be an appearance clue
                Clue clue = interactingCharacter.getClueList().pop();

                gameWorld.getNotebook().addClue(clue);

                gui.displayInfo(clue, clue.getDescription());

                interactionBox.setDialogue(clue.getResponse());

            } else {
                interactionBox.setDialogue(interactingCharacter.getQuestionFailResponse());
            }
        }

        interactionBox.haltQuestion();
    }

    /**
     * Logic for give interaction
     * @param givenItem item given to the character by the player
     */

    public void give(Item givenItem){
        if (givenItem.getLinkedPersonId() == interactingCharacter.getId()){
            interactionBox.setDialogue("giveSuccessDialogue");
            gameWorld.getNotebook().removeItem(givenItem);

            Item returnItem = interactingCharacter.getItemList().get(0);
            gameWorld.getNotebook().addItem(returnItem);
            interactingCharacter.removeItem(returnItem);

            gui.displayInfo(returnItem, returnItem.getDescription());
        }

        else{
            interactionBox.setDialogue("giveFailDialogue");
        }
        interactionBox.haltGive();
    }


    /**
     * Logic for accuse interaction
     * Checks whether the selected clues relate to the murderer.
     * @param selectedClueArray Array of clues that is presented by the player
     */

    public void accuse(Array<Clue> selectedClueArray){
        //clues that are required to be in the selected clue array
        Clue weaponClue = gameWorld.getClueManager().getWeaponClue();
        Clue motiveClue = gameWorld.getClueManager().getMotiveClue();
        Array<Clue> appearanceClue = gameWorld.getClueManager().getAppearanceClueArray();
        int appearanceClueCount = 0;

        //boolean flags that all have to be true to succeed in accusation
        boolean weaponCluePresent = false;
        boolean motiveCluePresent = false;
        boolean appearanceCluePresent = false;

        for (Clue clue : selectedClueArray) {
            if (clue.getClueType() == ClueType.WEAPON){
                if (clue.getId() == weaponClue.getId()){
                    weaponCluePresent = true;
                }
                else{
                    break;
                }
            }

            else if (clue.getClueType() == ClueType.MOTIVE){
                if (clue.getId() == motiveClue.getId()){
                    motiveCluePresent = true;
                }
                else {
                    break;
                }
            }

            else if (clue.getClueType() == ClueType.APPEARANCE){
                if (appearanceClue.contains(clue, false)){
                    appearanceClueCount++;
                }
                else{
                    break;
                }
            }
        }

        if (appearanceClueCount >= MINIMUM_APPEARANCE_CLUE_COUNT){
            appearanceCluePresent = true;
        }

        //if accusation is successful
        if (weaponCluePresent && motiveCluePresent && appearanceCluePresent){
            gui.displayInfo(null, "GAME CLEAR!");
        }
        else{
            interactingCharacter.setAccused(true);
            interactionBox.setDialogue("accuseFailDialogue");
        }
    }
}
