package model.Operators;

public class Operator_IF extends Operator{
    private Unit leftOperator;
    private Unit rightOperator;
    public Operator_IF(int size, boolean[] truthTable, Operator leftOperator, Operator rightOperator) {
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
}
