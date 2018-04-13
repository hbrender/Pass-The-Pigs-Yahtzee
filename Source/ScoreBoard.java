public class ScoreBoard {
    private int[] currentHandScore;
    private boolean[] hasScore;
    private int[] finalScore;
    private Hand hand;
    private Pig[] handArray;

    public ScoreBoard(Hand h) {
        constructorCurrentHand(h);
        finalScore = new int[12];
        hasScore = new boolean[12];
    }

    public void constructorCurrentHand(Hand h) {
        currentHandScore = new int[12];
        hand = h;
        handArray = hand.getHandArray();
    }

    public void setCurrentScore() {
        if (!hasScore[0]){
            currentHandScore[0] = calculateSide();
        }
        if (!hasScore[1]){
            currentHandScore[1] = calculateRazorback();
        }
        if (!hasScore[2]){
            currentHandScore[2] = calculateTrotter();
        }
        if (!hasScore[3]){
            currentHandScore[3] = calculateSnouter();
        }
        if (!hasScore[4]) {
            currentHandScore[4] = calculateLeaningJowler();
        }
        if (!hasScore[5]) {
            currentHandScore[5] = calculateSider();
        }
        if (!hasScore[6]) {
            currentHandScore[6] = calculateDoubleRazorback();
        }
        if (!hasScore[7]) {
            currentHandScore[7] = calculateDoubleTrotter();
        }
        if (!hasScore[8]) {
            currentHandScore[8] = calculateDoubleSnouter();
        }
        if (!hasScore[9]) {
            currentHandScore[9] = calculateDoubleLeaningJowler();
        }
        if (!hasScore[10]) {
            currentHandScore[10] = calculateMixedCombo();
        }
        //if (!hasScore[11]) {
            //currentHandScore[11] = calculatePiggyBack();
        //}
    }

    public int calculateSide() {
        int currentCount = 0;
        for (int i = 0; i < 4; i++) {
            if (hand.getHandArray()[i].getPigVal() == Pig.CurPig.SIDE) {
                currentCount++;
            }
        }
        return 1 * currentCount;
    }

    public int calculateRazorback() {
        int currentCount = 0;
        for (int i = 0; i < 4; i++) {
            if (handArray[i].getPigVal() == Pig.CurPig.RAZORBACK) {
                currentCount++;
            }
        }
        return 3 * currentCount;
    }

    public int calculateTrotter() {
        int currentCount = 0;
        for (int i = 0; i < 4; i++) {
            if (handArray[i].getPigVal() == Pig.CurPig.TROTTER) {
                currentCount++;
            }
        }
        return 5 * currentCount;
    }

    public int calculateSnouter() {
        int currentCount = 0;
        for (int i = 0; i < 4; i++) {
            if (handArray[i].getPigVal() == Pig.CurPig.SNOUTER) {
                currentCount++;
            }
        }
        return 7 * currentCount;
    }

    public int calculateLeaningJowler() {
        int currentCount = 0;
        for (int i = 0; i < 4; i++) {
            if (handArray[i].getPigVal() == Pig.CurPig.LEANING_JOWLER) {
                currentCount++;
            }
        }
        return 10 * currentCount;
    }

    public int calculateSider() {
        int sider = calculateSide();
        // if all pigs are side
        if (sider == 4) {
            return 20;
        } else {
            return 0;
        }
    }

    public int calculateDoubleRazorback() {
        int razorback = calculateRazorback();
        // at least two pig razorback
        if (razorback >= 6) {
            return 25;
        } else {
            return 0;
        }
    }

    public int calculateDoubleTrotter() {
        int trotter = calculateTrotter();
        // at least two pig trotter
        if (trotter >= 10) {
            return 30;
        } else {
            return 0;
        }
    }

    public int calculateDoubleSnouter() {
        int snouter = calculateSnouter();
        // at least two pig snouter
        if (snouter >= 14) {
            return 35;
        } else {
            return 0;
        }
    }

    public int calculateDoubleLeaningJowler() {
        int leaningJowler = calculateRazorback();
        // at least two pig leaning jowler
        if (leaningJowler >= 20) {
            return 40;
        } else {
            return 0;
        }
    }

    public int calculateMixedCombo() {
        int total = 0;
        for (int i = 0; i < 4; i++) {
            if (handArray[i].getPigVal() == Pig.CurPig.SIDE) {
                total += 1;
            } else if (handArray[i].getPigVal() == Pig.CurPig.RAZORBACK) {
                total += 3;
            } else if (handArray[i].getPigVal() == Pig.CurPig.TROTTER) {
                total += 5;
            } else if (handArray[i].getPigVal() == Pig.CurPig.SNOUTER) {
                total += 7;
            } else {
                total += 10;
            }
        }
    }

    // when button row is pressed, this function will be called
    public void setRowScore(int rowNum) {
        finalScore[rowNum - 1] = currentHandScore[rowNum - 1];
        hasScore[rowNum - 1] = true;
    }

    public int calculateBonus() {
        int upperTotal = 0;
        for (int i = 0; i < 5; i++) {
            upperTotal += finalScore[i];
        }
        if (upperTotal >= 50) {
            return 35;
        } else {
            return 0;
        }
    }

    public int calculateFinalScore() {
        int total = 0;
        for (int i = 0; i < 12; i++) {
            total += finalScore[i];
        }
        total += calculateBonus();
        return total;
    }
}
