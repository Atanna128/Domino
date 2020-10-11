import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Ai {
    Boolean draw_flag = false;

    public void aiPlayProcess(Player[] players, int mexican, int boneyard, int humanPlayer, boolean game_on) { //boneyard = number_player
        Set<Integer> possible_value_on_board = new HashSet<Integer>();
        ArrayList<domino> possible_card_in_deck = new ArrayList<domino>();
        int lastindex;
        domino lastcard;

        for (int j = humanPlayer; j < boneyard; j++) {
            //Find all value that can be matched
            if (players[j].left_card.size() == 0) {
                game_on = false;
                break;
            }

            for (int i = 0; i < boneyard; i++) {
                if (players[i].open_train) {
                    if (players[i].train.size() >= 1) {
                        lastindex = players[i].train.size() - 1;
                        lastcard = players[i].train.get(lastindex);
                        possible_value_on_board.add(lastcard.getRightValue());
                    }
                }
                if (i == j) {
                    if (players[i].train.isEmpty())
                        possible_value_on_board.add(9);
                    else {
                        lastindex = players[i].train.size() - 1;
                        lastcard = players[i].train.get(lastindex);
                        possible_value_on_board.add(lastcard.getRightValue());
                    }
                }
            }
            //Find all card that can be played
            for (domino card : players[j].left_card) {
                if (possible_value_on_board.contains(card.getLeftValue()) || possible_value_on_board.contains(card.getRightValue())) {
                    possible_card_in_deck.add(card);
                }
            }
            if (!possible_card_in_deck.isEmpty()) {
                //Find highest double in possible card to play

                domino maxDouble = new domino(-1, -1);
                for (domino card : possible_card_in_deck) {
                    if (card.isDouble()) {
                        if (card.getRightValue() > maxDouble.getRightValue())
                            maxDouble = card;
                    }
                }
                if (maxDouble.getRightValue() >= 0) {
                    for (int i = 0; i < players[j].left_card.size(); i++) {
                        if (players[j].left_card.get(i).getRightValue() == maxDouble.getRightValue() && players[j].left_card.get(i).getLeftValue() == maxDouble.getLeftValue())
                            players[j].playCard(i);
                    }
                    if (players[j].train.size() >= 1) {
                        if (players[j].train.get(players[j].train.size() - 1).getRightValue() == maxDouble.getLeftValue()) {
                            players[j].train.add(maxDouble);
                        }
                    } else {
                        for (Player player : players) {
                            if (player.train.size() >= 1) {
                                if (player.train.get(player.train.size() - 1).getRightValue() == maxDouble.getLeftValue() && player.open_train) {
                                    player.train.add(maxDouble);
                                }
                            }
                        }
                    }
                }
                playACard(players[j], possible_card_in_deck, players, mexican);
                if (draw_flag) {
                    draw_flag = false;
                }
            } else {
                if (!draw_flag) {
                    domino new_card = players[boneyard].left_card.remove(0);
                    players[j].drawCard(new_card);
                    draw_flag = true;
                    aiPlayProcess(players, mexican, boneyard, humanPlayer, game_on);
                } else {
                    draw_flag = false;
                }
            }
        }
    }


    public void playACard(Player player, ArrayList<domino> possible_card_in_deck, Player[] players, int mexican) {
        domino maxDomino = new domino(-1, -1);
        for (domino card : possible_card_in_deck) {
            if (card.getLeftValue() + card.getRightValue() > maxDomino.getLeftValue() + maxDomino.getRightValue()) {
                maxDomino = card;
            }
        }
        int count = 0;
        for (domino card : player.left_card) {
            if (card.getLeftValue() == maxDomino.getLeftValue() && card.getRightValue() == maxDomino.getRightValue()) {
                player.playCard(count);
                break;
            }
            count++;
        }
        if (player.train.size() >= 1) {
            if (maxDomino.isMatched(player.train.get(player.train.size() - 1)))
                player.train.add(maxDomino);
            else if (maxDomino.isMatchedFlip(player.train.get(player.train.size() - 1))) {
                maxDomino.flip();
                player.train.add(maxDomino);
            }
        } else {
            if (player.train.size() == 0) {
                if (maxDomino.getLeftValue() == 9)
                    player.train.add(maxDomino);
                else if (maxDomino.getRightValue() == 9) {
                    maxDomino.flip();
                    player.train.add(maxDomino);
                }
            }
            else if(players[mexican].train.size() == 0)
                if (maxDomino.getLeftValue() == 9)
                    player.train.add(maxDomino);
                else if (maxDomino.getRightValue() == 9) {
                    maxDomino.flip();
                    player.train.add(maxDomino);
                }
            else {
                for (Player temp : players) {
                    if (temp.train.size() >= 1) {
                        if (maxDomino.isMatched(temp.train.get(temp.train.size() - 1)) && temp.open_train) {
                            temp.train.add(maxDomino);
                        } else if (maxDomino.isMatchedFlip(temp.train.get(temp.train.size() - 1)) && temp.open_train) {
                            maxDomino.flip();
                            temp.train.add(maxDomino);
                        }
                    }
                }
            }
        }
    }
}
