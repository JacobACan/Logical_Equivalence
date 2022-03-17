package model;

public class Operator implements Unit{
    private boolean[] truthTable;
    private int size;
    public Operator(int size) {
        this.size = size;
        this.truthTable = new boolean[size];
    }

    public boolean[] getTruthTable() {
        return truthTable;
    }

    public void And(Unit unit1, Unit unit2) {
        boolean[] truthTable1 = unit1.getTruthTable();
        boolean[] truthTable2 = unit2.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] && truthTable2[i]);
            i++;
        }
    }
    public void Or(Unit unit1, Unit unit2) {
        boolean[] truthTable1 = unit1.getTruthTable();
        boolean[] truthTable2 = unit2.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] || truthTable2[i]);
            i++;
        }
    }
    public void Not(Unit unit1) {
        boolean[] truthTable1 = unit1.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (!truthTable1[i]);
            i++;
        }
    }
    
    public void If(Unit unit1, Unit unit2) {
        boolean[] truthTable1 = unit1.getTruthTable();
        boolean[] truthTable2 = unit2.getTruthTable();
        int i = 0;
        while(i < size) {
            if (!truthTable[i]) {
                truthTable[i] = true;
            } else if (truthTable2[i]) {
                truthTable1[i] = true;
            } else {
                truthTable[i] = false;
            }
            i++;
        }
    }
    public void Iff(Unit unit1, Unit unit2) {
        boolean[] truthTable1 = unit1.getTruthTable();
        boolean[] truthTable2 = unit2.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] && truthTable2[i]);
            i++;
        }
    }
    public void logically_equivalent(Unit unit1, Unit unit2) {
        boolean[] truthTable1 = unit1.getTruthTable();
        boolean[] truthTable2 = unit2.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] && truthTable2[i]);
            i++;
        }
    }
}
