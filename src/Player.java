import java.util.Arrays;

public class Player {
    domino[] left_card;
    boolean open_train;

    public Player(domino[] left_card) {
        this.left_card = left_card;
        this.open_train = false;
    }

    public void drawCard(domino new_card){
        Arrays.copyOf(this.left_card, this.left_card.length +1);
        this.left_card[this.left_card.length-1] = new_card;
    }

    public void setOpen(boolean open_train){
        this.open_train = open_train;
    }

    public void playCard(domino played_card){
        domino[] new_left_card = new domino[this.left_card.length-1];
        for(int j = 0, k = 0; j<this.left_card.length; j++){
            if(this.left_card[j].isMatched(played_card))
                continue;
            new_left_card[k++] = this.left_card[j];
        }
    }

    public void showCard(){
        for (domino temp : this.left_card)
            System.out.println(temp.getLeftValue()+", "+temp.getRightValue());
    }
}
