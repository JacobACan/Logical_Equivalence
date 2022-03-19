package model.Operators;

public class Operator_IFF extends Operator{
    private Unit leftUnit;
    private Unit rightUnit;
    public Operator_IFF(int size, Unit leftUnit, Unit rightUnit) {
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
            if (!truthTable1[i] && !truthTable2[i]) {
                truthTable[i] = true;
            } else {
                truthTable[i] = (truthTable1[i] && truthTable2[i]);
            }
            i++;
        }
    }
}
