package model.Operators;

public abstract class Operator implements Unit{
    protected boolean[] truthTable;
    protected int size;

    public Operator(int size) {
        this.size = size;
        this.truthTable = new boolean[size];
    }

    public boolean[] getTruthTable() {
        return truthTable;
    }
}
