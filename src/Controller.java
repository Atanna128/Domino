import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.util.concurrent.ThreadLocalRandom;
public class Controller implements Initializable{
    public ListView<String> dominoList,boneyardList;
    public Text currentPlayer;
    public Button choosenTrain,gate1,gate2,gate3,gate4;
    public TextArea train0,train1,train2,train3,train4,numberPlayer,numberComputer;
    private static final int START_CARD_NUMBER = 8;
    Player [] players;
    int current = 1;

    public void mexicanTrain(ActionEvent event) throws IOException {
        choosenTrain.setText("Mexican Train");
    }

    public void player1Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 1");
    }

    public void player2Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 2");
    }

    public void player3Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 3");
    }

    public void player4Train(ActionEvent event) throws IOException {
        choosenTrain.setText("Train 4");
    }

    public void skip(ActionEvent event) throws IOException {
        if (current ==1 && gate1.getText().contains("close")) gate1.setText("open");
        if (current ==2 && gate2.getText().contains("close")) gate2.setText("open");
        if (current ==3 && gate3.getText().contains("close")) gate3.setText("open");
        if (current ==4 && gate4.getText().contains("close")) gate4.setText("open");
        if (current<4) current+=1;
        else current= 1;
        currentPlayer.setText(Integer.toString(current));
        dominoList.getItems().clear();
        for (domino domino: players[current-1].left_card) {
            dominoList.getItems().add(domino.getLeftValue()+" | "+ domino.getRightValue());
        }
    }
    public void draw(ActionEvent event) throws IOException {
        boneyardList.getSelectionModel().select(0);
        String name = boneyardList.getSelectionModel().getSelectedItems().toString();
        boneyardList.getItems().remove(0);
        name = name.charAt(1) + " | "+ name.charAt(5);
        dominoList.getItems().add(name);
        domino swap = new domino(name.charAt(0),name.charAt(4));
        players[current-1].left_card.add(swap);
    }

    public void startGame(ActionEvent event) throws IOException, NumberFormatException{
        int humanPlayers = 0;
        int computerPlayers = 0;
        String number1 = numberPlayer.getText();
        if (!number1.equals("")) {
            humanPlayers = Integer.parseInt(number1);
        }
        String number2 = numberComputer.getText();
        if (!number2.equals("")) {
            computerPlayers = Integer.parseInt(number2);
        }
        domino[] initDecks = initDecks(humanPlayers, computerPlayers);
        gameLogic(humanPlayers, computerPlayers, initDecks);
        train0.setText("[9|9] ");
    }

    private void nextPlayer(){
        if(players[current-1].left_card.size() ==0){
            int count = current-1;
            currentPlayer.setText("Player " + count+ " win!");
        }else {
        if (current<4) current+=1;
        else current= 1;
        if (current ==1 && gate1.getText().contains("close")) gate1.setText("open");
        if (current ==2 && gate2.getText().contains("close")) gate2.setText("open");
        if (current ==3 && gate3.getText().contains("close")) gate3.setText("open");
        if (current ==4 && gate4.getText().contains("close")) gate4.setText("open");

            currentPlayer.setText(Integer.toString(current));
            dominoList.getItems().clear();
            for (domino domino : players[current - 1].left_card) {
                dominoList.getItems().add(domino.getLeftValue() + " | " + domino.getRightValue());
            }
        }
    }
    private boolean validTrain(TextArea train, String nextDomino){
        int last = train.getText().length();
        char a = train.getText().charAt(last-3);
        if (nextDomino.charAt(0) == a ||nextDomino.charAt(4)==a)
            return true;
        return false;
    }
    private String flip (String name){
        char a = name.charAt(0);
        char b = name.charAt(4);
        return b+" | "+a;
    }
    public void select(ActionEvent event)throws IOException {
        String name = dominoList.getSelectionModel().getSelectedItem();
        int index = dominoList.getSelectionModel().getSelectedIndex();
        if(name != null){
            String currentTr = choosenTrain.getText();
            System.out.println(name);
            if (currentTr.contains("Mexican") && validTrain(train0,name)) {
                train0.setEditable(true);
                dominoList.getItems().remove(name);
                players[current-1].left_card.remove(index);
                if (train0.getText().charAt(train0.getText().length()-3) == name.charAt(4)) {
                    name = flip(name);
                }
                train0.setText(train0.getText() + "  [" + name + "] ");
                train0.setEditable(false);
                if (name.charAt(4) == name.charAt(0)){
                }else nextPlayer();

            } else if (currentTr.contains("1")) {
                train1.setEditable(true);
                dominoList.getItems().remove(name);
                players[current-1].left_card.remove(index);
                if (train1.getText().length()>4){
                    if (train1.getText().charAt(train1.getText().length()-3) == name.charAt(4)) {
                        name = flip(name);
                    }
                }
                train1.setText(train1.getText() + "  [" + name + "] ");
                train1.setEditable(false);
                if (current ==1) gate1.setText("close");
                if (name.charAt(4) == name.charAt(0)){
                }else nextPlayer();

            } else if (currentTr.contains("2")) {
                train2.setEditable(true);
                dominoList.getItems().remove(name);
                players[current-1].left_card.remove(index);
                if (train2.getText().length()>4) {
                    if (train2.getText().charAt(train2.getText().length() - 3) == name.charAt(4)) {
                        name = flip(name);
                    }
                }
                train2.setText(train2.getText() + "  [" + name + "] ");
                train2.setEditable(false);
                if (current ==2) gate2.setText("close");
                if (name.charAt(4) == name.charAt(0)){
                }else nextPlayer();
            } else if (currentTr.contains("3")) {
                train3.setEditable(true);
                dominoList.getItems().remove(name);
                players[current-1].left_card.remove(index);
                if (train3.getText().length()>4) {
                    if (train3.getText().charAt(train3.getText().length() - 3) == name.charAt(4)) {
                        name = flip(name);
                    }
                }
                train3.setText(train3.getText() + "  [" + name + "] ");
                train3.setEditable(false);
                if (current ==3) gate3.setText("close");
                if (name.charAt(4) == name.charAt(0)){
                }else nextPlayer();


            } else if (currentTr.contains("4")) {
                train4.setEditable(true);
                dominoList.getItems().remove(name);
                players[current-1].left_card.remove(index);
                if (train4.getText().length()>4) {
                    if (train4.getText().charAt(train4.getText().length() - 3) == name.charAt(4)) {
                        name = flip(name);
                    }
                }
                train4.setText(train4.getText() + "  [" + name + "] ");
                train4.setEditable(false);
                if (current ==4) gate4.setText("close");
                if (name.charAt(4) == name.charAt(0)){
                }else nextPlayer();
            }
        }


    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setupGUI();
    }
    public static void shuffleArray(domino[] ar) {
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            domino a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
    }
    private void setupGUI(){
        train0.setEditable(false);
        train1.setEditable(false);
        train2.setEditable(false);
        train3.setEditable(false);
        train4.setEditable(false);
        gate1.setText("close");
        gate2.setText("close");
        gate3.setText("close");
        gate4.setText("close");
    }
    public static domino[] initDecks(int humanCount, int computerCount) {
        //logic to establish decks dynamically based off number of players
        domino[] startDeck = new domino[] {
                new domino(0,0),
                new domino(1,0),
                new domino(1,1),
                new domino(2,0),
                new domino(2,1),
                new domino(2,2),
                new domino(3,0),
                new domino(3,1),
                new domino(3,2),
                new domino(3,3),
                new domino(4,0),
                new domino(4,1),
                new domino(4,2),
                new domino(4,3),
                new domino(4,4),
                new domino(5,0),
                new domino(5,1),
                new domino(5,2),
                new domino(5,3),
                new domino(5,4),
                new domino(5,5),
                new domino(6,0),
                new domino(6,1),
                new domino(6,2),
                new domino(6,3),
                new domino(6,4),
                new domino(6,5),
                new domino(6,6),
                new domino(7,0),
                new domino(7,1),
                new domino(7,2),
                new domino(7,3),
                new domino(7,4),
                new domino(7,5),
                new domino(7,6),
                new domino(7,7),
                new domino(8,0),
                new domino(8,1),
                new domino(8,2),
                new domino(8,3),
                new domino(8,4),
                new domino(8,5),
                new domino(8,6),
                new domino(8,7),
                new domino(8,8),
                new domino(9,0),
                new domino(9,1),
                new domino(9,2),
                new domino(9,3),
                new domino(9,4),
                new domino(9,5),
                new domino(9,6),
                new domino(9,7),
                new domino(9,8),
        };
//        System.out.println("Deck Length -> " + startDeck.length);
        return startDeck;
    }

    public  void gameLogic(int humanPlayer, int computerPlayer, domino[] initDecks) {
            //GAME SETUP : PLAYER, CARD, INFO OF TRAIN ...
            boolean game_on = true;
            int number_player = humanPlayer+computerPlayer;

            // khoi tao nguoi choi va chia bai
            int j, i, l, k = 0; //j to count players, i to run loop to get card from start card pool (everytime is (k=8) card/player
            players = new Player[number_player+2]; // khoi tao human, computer, mexican(train dung chung), card pool
            shuffleArray(initDecks);

            for (j = 0; j < number_player+1; j++){
                l = 0;
                if(j != number_player) {
                domino[] temp = new domino[START_CARD_NUMBER];
                for (i = k; i < k + START_CARD_NUMBER; i++) {
                    temp[l++] = initDecks[i];
                }
                k += 8;
                if(j < humanPlayer) {
                    players[j] = new Player(temp, "Player" + (j + 1), false);
                    }
                else
                    players[j] = new Player(temp, "Computer"+(j+1), false);
            }
                else {
                domino[] boneyard = new domino[initDecks.length - 8 * number_player];
                for (i = k; i < initDecks.length; i++) {
                    boneyard[l++] = initDecks[i];
                }
                players[j] = new Player(boneyard, "Boneyard", false);
            }
        }

        domino[] temp_mexican = new domino[]{new domino(9,9)};
        players[number_player+1] = new Player(temp_mexican, "Mexican Train", true);

        //START GAME
        currentPlayer.setText("1");
        int boneyard = number_player;
        int mexican = number_player+1;
        for (domino domino: players[boneyard].left_card) {
            boneyardList.getItems().add(domino.getLeftValue()+" | "+ domino.getRightValue());
        }
        for (domino domino: players[current-1].left_card) {
            dominoList.getItems().add(domino.getLeftValue()+" | "+ domino.getRightValue());
        }





    }
}
