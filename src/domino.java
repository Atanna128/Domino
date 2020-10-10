public class domino {
    private int leftValue;
    private int rightValue;

    public domino(int leftValue, int rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public int getLeftValue() {
        return leftValue;
    }

    public int getRightValue() {
        return rightValue;
    }
    public boolean isMatched(domino temp){
        if(this.leftValue != temp.rightValue)
            return false;
        return true;
    }

    public boolean isMatchedFlip(domino temp){
        if(this.rightValue != temp.rightValue)
            return false;
        return true;
    }
    public void flip(domino domino){
        int temp = domino.getLeftValue();
        domino.leftValue = domino.rightValue;
        domino.rightValue = temp;
    }
}