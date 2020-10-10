public class domino {
    private int leftValue;
    private int rightValue;

    public domino(int leftValue, int rightValue) {
        this.leftValue = leftValue;
        this.rightValue = rightValue;
    }

    public void setLeftValue(int leftValue){ this.leftValue = leftValue;}

    public void setRightValue(int rightValue){ this.rightValue = rightValue;}

    public int getLeftValue() {
        return leftValue;
    }

    public int getRightValue() {
        return rightValue;
    }

    public void flip(){
        int temp = this.leftValue;
        this.leftValue = this.rightValue;
        this.rightValue = temp;
    }

    public boolean isDouble(){
        if(this.rightValue == this.leftValue)
            return true;
        return false;
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
}