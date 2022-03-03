package model;

public class Proposition implements Unit{
    private boolean[] truthTable;
    private int size;
    public Proposition(int size) {
        this.size = size;
        this.truthTable = new boolean[size];
    }
    public boolean[] getTruthTable() {
        return truthTable;
    }
}
