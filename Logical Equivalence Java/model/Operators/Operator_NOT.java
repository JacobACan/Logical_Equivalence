package model.Operators;


public class Operator_NOT extends Operator{
    private Unit rightUnit;
    public Operator_NOT(int size, Unit rightUnit) {
        super(size);
        this.rightUnit = rightUnit;
        evaluate();
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = rightUnit.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (!truthTable1[i]);
            i++;
        }
    }
}
