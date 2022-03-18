package model.Operators;

public class Operator_LOGICALLY_EQUAL extends Operator{
    private Unit leftOperator;
    private Unit rightOperator;
    public Operator_LOGICALLY_EQUAL(int size, boolean[] truthTable, Operator leftOperator, Operator rightOperator) {
        super(size, truthTable);
        this.leftOperator = leftOperator;
        this.rightOperator = rightOperator;
    }

    @Override
    public void evaluate() {
        boolean[] truthTable1 = leftOperator.getTruthTable();
        boolean[] truthTable2 = rightOperator.getTruthTable();
        int i = 0;
        while(i < size) {
            truthTable[i] = (truthTable1[i] && truthTable2[i]);
            i++;
        }
    }
}
