import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class ConsoleDomino{
    private static final int START_CARD_NUMBER_4 = 10;
    private static final int START_CARD_NUMBER_3 = 13;
    private static final int START_CARD_NUMBER_2 = 15;

    public static void shuffleArray(domino[] ar)
    {
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
    public static void validatePlay() throws Exception {

        domino[] dominos = new domino[] {
                new domino(2, 4),
                new domino(4, 2),
                new domino(2, 5),
                new domino(5, 2)
        };

        domino previous = null;
        for (domino current : dominos) {
            if (previous != null) {
                if (current.getLeftValue() != previous.getRightValue()) {
                    throw new Exception("Illegal arrangement detected");
                }
            }
            previous = current;
        }

        System.out.println("Domino at index 3 " + dominos[3].getLeftValue() + "," + dominos[3].getRightValue());
    }

    public static void initGame() {
        //intial varaibles
        int humanPlayers;
        int computerPlayers;

        //dialogue options
        System.out.println("Please enter the number of human players:");
        Scanner scanHuman = new Scanner(System.in);
        humanPlayers = scanHuman.nextInt();

        System.out.println("Please enter the number of computer players:");
        Scanner scanComputer = new Scanner(System.in);
        computerPlayers = scanComputer.nextInt();

        //establish decks
        System.out.println("Humans: " + humanPlayers + " - Computers: " + computerPlayers);
        domino[] initDecks = initDecks(humanPlayers, computerPlayers);

        //switch to gameLogic
        gameLogic(humanPlayers, computerPlayers, initDecks);
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
//                new domino(9,9)
        };
        System.out.println("Deck Length -> " + startDeck.length);
        return startDeck;
    }

    public static void gameLogic(int humanPlayer, int computerPlayer, domino[] initDecks) {
        //GAME SETUP : PLAYER, CARD, INFO OF TRAIN ...
        boolean game_on = true;
        int number_player = humanPlayer+computerPlayer;
        int start_card_number;
        Ai aiProcessing = new Ai();
        switch (number_player){
            case 2:start_card_number = START_CARD_NUMBER_2;break;
            case 3:start_card_number = START_CARD_NUMBER_3;break;
            case 4:start_card_number = START_CARD_NUMBER_4;break;
            default:start_card_number = 8;break;
        }

        // khoi tao nguoi choi va chia bai
        int j, i, l, k = 0; //j to count players, i to run loop to get card from start card pool (everytime is (k=8) card/player
        Player [] players = new Player[number_player+2]; // khoi tao human, computer, mexican(train dung chung), card pool
        shuffleArray(initDecks);

        for (j = 0; j < number_player+1; j++){
            l = 0;
            if(j != number_player) {
                domino[] temp = new domino[start_card_number];
                for (i = k; i < k + start_card_number; i++) {
                    temp[l++] = initDecks[i];
                }
                k += 8;
                if(j < humanPlayer)
                    players[j] = new Player(temp, "Player"+(j+1), false);
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
        players[number_player+1].train = new ArrayList<>(Arrays.asList(temp_mexican));

        //GAME REALLY STARTS NOW !
        while(game_on){
            String choice;
            int index;
            Scanner scanner= new Scanner(System.in);
            int mexican = number_player+1;
            int boneyard = number_player;

            //PLAYER PLAYS PART
            for(i = 0; i < humanPlayer; i++){
                do {
                    //GAME INFO PER TURN = GAME STATE PART + BOARD PART

                    int count = 0, count2 = 0;
                    // GAME STATE PART
                    System.out.println("##### GameState #####");
                    System.out.println("Humans:");
                    for(j = 0; j < humanPlayer; j++){
                        players[j].showCardInHand();
                        count++;
                    }

                    System.out.println("Computers: ");
                    for(j = count ; j < number_player; j++){
                        players[j].showCardInHand();
                    }
                    System.out.println();

                    System.out.println("Center: [9 | 9]");

                    //BOARD PART
                    System.out.println("Board: ");
                    for(j = 0; j < humanPlayer; j++){
                        players[j].showCardInTrain();
                        count2++;
                    }

                    for(j = count2 ; j < number_player; j++){
                        players[j].showCardInTrain();
                    }
                    players[mexican].showCardInTrain();
                    players[boneyard].showCardInHand();

                    System.out.println("Current Player:"+(i+1));
                    System.out.println(players[i].name + "'s Turn");
                    System.out.println("[p] play domino");
                    System.out.println("[d] draw from boneyard");
                    System.out.println("[q] quit");
                    choice = scanner.nextLine();

                    //Choice play
                    if (choice.charAt(0) == 'p') {
                        System.out.println("Which domino?");
                        index = scanner.nextInt();
                        boolean open_flag = true;
                        do {
                            String temp = scanner.nextLine();
                            System.out.println("Which train?");
                            String train = scanner.nextLine();
                            for (Player player : players)
                                if ((player.name.equals(train) && player.open_train ) || players[i].name.equals(train) ){
                                    open_flag = false;
                                    domino card_to_play = players[i].left_card.get(index);
                                    System.out.println("Flip?");
                                    choice = scanner.nextLine();

                                    domino latest_card_on_train;
                                    int lastindex;
                                    Player player_train = players[i];
                                    if (players[i].name.equals(train)){
                                        lastindex = players[i].train.size()-1;
                                        if(lastindex < 0)
                                            latest_card_on_train = new domino(9,9);
                                        else {
                                            latest_card_on_train = players[i].train.get(lastindex);
                                        }
                                    }
                                    else {
                                        lastindex = player.train.size() - 1;
                                        if(lastindex < 0)
                                            latest_card_on_train = new domino(9,9);
                                        else {
                                            latest_card_on_train = player.train.get(lastindex);
                                            player_train = player;
                                        }
                                    }

                                    if(choice.equals("Yes")){
                                        if(card_to_play.isMatchedFlip(latest_card_on_train)) {
                                            card_to_play.flip();
                                            player_train.train.add(card_to_play);
                                            players[i].playCard(index);
                                            players[i].open_train = false;
                                            if(!card_to_play.isDouble())
                                                choice = Character.toString('q');
                                        }
                                        else{
                                            System.out.println("Wrong card !");
                                            break;
                                        }
                                    }
                                    else{
                                        if(card_to_play.isMatched(latest_card_on_train)) {
                                            player_train.train.add(card_to_play);
                                            players[i].playCard(index);
                                            players[i].open_train = false;
                                            if(!card_to_play.isDouble())
                                                choice = Character.toString('q');
                                        }
                                        else{
                                            System.out.println("Wrong card !");
                                            break;
                                        }
                                    }

                                    if(players[i].left_card.size()==0)
                                        game_on = false;
                                    break;
                                }
                            if (open_flag) {
                                System.out.println("That train is not open, please try again!");
                                System.out.println("To come back to menu press b");
                                choice = scanner.nextLine();
                                if (choice.charAt(0) == 'b')
                                    break;
                            }
                        } while (open_flag);
                    }
                    else if (choice.charAt(0) == 'd') {
                        domino draw_card = players[boneyard].left_card.remove(0);
                        players[i].drawCard(draw_card);
                        players[i].open_train = true;
                    }
                }while(choice.charAt(0) != 'q');
            }
            aiProcessing.aiPlayProcess(players,mexican,boneyard,humanPlayer,game_on);
        }
        for (int m=0; m<number_player; m++){
            System.out.println(players[m].name+": "+players[m].getTotalPoint());
        }
    }

    public static void run() throws Exception {
        System.out.println("Welcome to Mexican Train!");
        System.out.println("If you do not know the rules they can be found here -> https://www.mexicantrainrulesandstrategies.com");
        System.out.println("Up to 4 players can play with any mix of computer and human players.");
        System.out.println("-Player selection here");

        initGame();
//        validatePlay();
    }

    public void showGameState(){

    }
}