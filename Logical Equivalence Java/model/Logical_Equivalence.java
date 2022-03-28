package model;

import java.util.List;

import model.Operators.OperatorType;
import model.Operators.Unit;

public class Logical_Equivalence {
    private String equation1;
    private String equation2;
    private String parsedLogicalEquivalenceEquation1;
    private String parsedLogicalEquivalenceEquation2;
    private String finalparsedlogicalEquivalenceEquation;
    private Unit logicalEquivalenceUnit;

    public Logical_Equivalence() {
        this.equation1 = "";
        this.equation2 = "";
        this.parsedLogicalEquivalenceEquation1 = "";
        this.parsedLogicalEquivalenceEquation2 = "";
        this.finalparsedlogicalEquivalenceEquation = "";
        this.logicalEquivalenceUnit = null;
    }

    public void setEquation1(String equation) {
        this.equation1 = equation;
    }
    public void setEquation2(String equation) {
        this.equation2 = equation;
    }

    public boolean getlogicalEquivalence() throws LogicalEquivalenceException{
        if (this.logicalEquivalenceUnit == null) throw new LogicalEquivalenceException("Equations must be evaluated before evaluating their logical equivalence.");
        boolean[] finalTruthTable = logicalEquivalenceUnit.getTruthTable();
        for (boolean unit : finalTruthTable) {
            if (unit == false) return false;
        }
        return true;
    }
    public List<List<boolean[]>> getTruthTables() throws LogicalEquivalenceException{
        if (this.logicalEquivalenceUnit == null) throw new LogicalEquivalenceException("Equations must be evaluated before accessing their truth tables.");
        //TODO : returns a list containing all the truth tables of every operation done in a tree like structure
        //   breadth first search
        return null;
    }

    public void evaluate() throws LogicalEquivalenceException{
        if (equation1 == "" && equation2 == "") throw new LogicalEquivalenceException("Both equations needed to evaluate equivalence.");  
        this.parsedLogicalEquivalenceEquation1 = new Equation_Parser(equation1).toString();
        this.parsedLogicalEquivalenceEquation2 = new Equation_Parser(equation2).toString();
        this.finalparsedlogicalEquivalenceEquation = String.format("%s%s%s", OperatorType.LOGICALLY_EQUAL.toString(), parsedLogicalEquivalenceEquation1, parsedLogicalEquivalenceEquation2);
        this.logicalEquivalenceUnit = new Parse_Reader(finalparsedlogicalEquivalenceEquation).getUnit();
    }
}
