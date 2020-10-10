import java.util.Scanner;
import java.util.Arrays;

public class ConsoleDomino{

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
        initDecks(humanPlayers, computerPlayers);

        //switch to gameLogic
        gameLogic();
    }

    public static void initDecks(int humanCount, int computerCount) {
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
                new domino(9,9)
        };
        System.out.println("Deck Length -> " + startDeck.length);
    }

    public static void gameLogic() {
        //main game logic
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Welcome to Mexican Train!");
        System.out.println("If you do not know the rules they can be found here -> https://www.mexicantrainrulesandstrategies.com");
        System.out.println("Up to 4 players can play with any mix of computer and human players.");
        System.out.println("-Player selection here");

        initGame();
        //validatePlay();
    }
}