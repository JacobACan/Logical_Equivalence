package model.Operators;

public class Operator_NOT extends Operator{
    private Unit leftOperator;
    public Operator_NOT(int size, boolean[] truthTable, Operator leftOperator, Operator rightOperator) {
        super(size, truthTable);
        this.leftOperator = leftOperator;
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = leftOperator.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (!truthTable1[i]);
            i++;
        }
    }
}
