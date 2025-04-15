public class Player {
    private String name;
    private int[] pits;
    private int store;

    public Player(String name) {
        this.name = name;
        this.pits = new int[6];
        for (int i = 0; i < 6; i++) pits[i] = 4; // 4 seeds per pit
        this.store = 0;
    }

    public String getName() {
        return name;
    }

    public int[] getPits() {
        return pits;
    }

    public int getStore() {
        return store;
    }

    public void addToStore(int count) {
        store += count;
    }

    public void setPit(int index, int value) {
        pits[index] = value;
    }

    public int getPit(int index) {
        return pits[index];
    }

    public void incrementPit(int index) {
        pits[index]++;
    }

    public boolean isEmpty() {
        for (int pit : pits)
            if (pit != 0) return false;
        return true;
    }

    public void collectRemainingSeeds() {
        for (int i = 0; i < 6; i++) {
            store += pits[i];
            pits[i] = 0;
        }
    }
}
