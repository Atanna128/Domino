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
        if(this.rightValue != temp.rightValue && this.rightValue != temp.leftValue)
            return false;
        return true;
    }

}