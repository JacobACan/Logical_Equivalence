package model.Operators;


public class Operator_OR extends Operator{
    private Unit leftUnit;
    private Unit rightUnit;
    public Operator_OR(int size, Unit leftUnit, Unit rightUnit) {
        super(size);
        this.leftUnit = leftUnit;
        this.rightUnit = rightUnit;
        evaluate();
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = leftUnit.getTruthTable();
        boolean[] truthTable2 = rightUnit.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] || truthTable2[i]);
            i++;
        }
    }
}
