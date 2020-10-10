import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    ArrayList<domino> left_card;
    ArrayList<domino> train;
    boolean open_train;
    String name;
    int point = 0;

    public Player(domino[] left_card, String name, boolean open_train) {
        this.left_card = new ArrayList<>(Arrays.asList(left_card));
        this.open_train = open_train;
        this.train = null;
        this.name = name;
    }

    public void drawCard(domino new_card){
       this.left_card.add(new_card);
    }

    public void setOpen(boolean open_train){
        this.open_train = open_train;
    }

    public domino playCard(int index){
       return this.left_card.remove(index);
    }

    public void showCardInHand(){
        System.out.println(this.name+": ");
        for (domino temp : this.left_card)
            System.out.print("["+temp.getLeftValue() + " | " + temp.getRightValue()+"], ");
        System.out.println();
    }

    public void showCardInTrain(){
        System.out.println(this.name+"("+this.open_train+"):");
        if (this.train != null) {
            for (domino temp : this.train)
                System.out.print("[" + temp.getLeftValue() + " | " + temp.getRightValue() + "], ");
        }
        System.out.println();
    }

    public int getTotalPoint(){
        for (domino temp : this.left_card)
            this.point += (temp.getLeftValue()+ temp.getRightValue());
        return this.point;
    }
}
