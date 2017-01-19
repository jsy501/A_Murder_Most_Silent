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
    /**
     * MINIMUM_APPEARANCE_CLUE_COUNT contains an integer that defines the minimum number of appearance clues needed for
     * a successful accusation to take place
     */
    private final int MINIMUM_APPEARANCE_CLUE_COUNT = 2;

    /**
     * interactionBox is used for the interaction box gui
     * gameWorld is a manager that holds all objects
     * gui is the gui it is needed for sending results of interaction to display
     */
    private InteractionBox interactionBox;
    private GameWorld gameWorld;
    private Gui gui;

    /**
     * interactingCharacter is the target of the interaction
     */
    private GameCharacter interactingCharacter;

    /**
     * Constructor that initialises the interaction class for use to be run at game start
     * @param interactionBox Needs to be passed to the class so that it can reference the rest of the game
     */
    public Interaction(InteractionBox interactionBox){
        this.interactionBox = interactionBox;
        gameWorld = interactionBox.getGameWorld();
        gui = interactionBox.getGui();
    }

    /**
     * Run at the start of an interaction event this sets the interacting character parameter
     * and then displays the characters greeting dialogue on screen using the interaction box
     * defined at initialisation
     * @param interactingCharacter required for other functions
     */
    public void setInteractingCharacter(GameCharacter interactingCharacter) {
        this.interactingCharacter = interactingCharacter;
        interactionBox.setDialogue(interactingCharacter.getGreeting());
    }

    public GameCharacter getInteractingCharacter() {
        return interactingCharacter;
    }

    /**
     * Logic for question interaction
     *
     * The method checks whether the target character has been accused first displaying the appropriate output if so
     *
     * then it checks if the target character has any clues to give if so it displays the "I have no clue" string on the display
     *
     * next it checks if the target character has the motive clue, the player has found less than 3 clues and the target character only haas 1 cluep
     * if all these conditions are true then the character will reply to the player with "i'll tell you something later
     *
     * finally if none of the above conditions are satisfied a simple algorithm is used to calculate whether the interaction was a success and if a clue should
     * be returned or not below are the steps explained in english:
     *
     * The player chooses a questioning style this relates to an integer from 1 to 9
     * the target character also has a personality value, the absolute difference between
     * the two is calculated and then added together with a random number in the range of 1 to 9
     * to calculate the chance of success
     * if the chance of success is greater than or equal to 5 then the questioning was a success and a clue will be given to the player
     * if it is less than 5 then the characters fail response will be displayed on screen
     *
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
                // motive clue is always added first so the popped clue will always be an appearance clue
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
     *
     * Works via a give and take system when a player finds an item they can then
     * give it to a character if the target character is elated to the item
     * a new item is given back.
     *
     * Once all return items are exhausted a key is returned which can be used
     * to open the locked room
     *
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
     * If they all do and 1 weapon clue, 1 motive clue and the MINIMUM_APPEARANCE_CLUE_COUNT
     * or more appearance clues are presented the accusation is a success
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
