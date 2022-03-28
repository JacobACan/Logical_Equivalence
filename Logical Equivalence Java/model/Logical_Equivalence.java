package model;

import java.util.List;

import model.Operators.Unit;

public class Logical_Equivalence {
    private String equation1;
    private String equation2;
    private String logicalEquivalenceEquation;
    private String parsedLogicalEquivalenceEquation;
    private Unit logicalEquivalenceUnit;
    private boolean logicalEquivalence;

    public Logical_Equivalence() {
        this.equation1 = "";
        this.equation2 = "";
        this.logicalEquivalenceEquation = "";
        this.parsedLogicalEquivalenceEquation = "";
        this.logicalEquivalenceUnit = null;
        this.logicalEquivalence = false;
    }

    public void setEquation1(String equation) {
        this.equation1 = equation;
    }
    public void setEquation2(String equation) {
        this.equation2 = equation;
    }

    public boolean getlogicalEquivalence() throws LogicalEquivalenceException{
        if (this.logicalEquivalenceUnit == null) throw new LogicalEquivalenceException("Equations must be evaluated before evaluating their logical equivalence.");
        return this.logicalEquivalence;
    }
    public List<List<boolean[]>> getTruthTables() throws LogicalEquivalenceException{
        if (this.logicalEquivalenceUnit == null) throw new LogicalEquivalenceException("Equations must be evaluated before accessing their truth tables.");
        //TODO : returns a list containing all the truth tables of every operation done in a tree like structure
        //   breadth first search
        return null;
    }

  private void evaluate() throws LogicalEquivalenceException{
        if (equation1 == "" && equation2 == "") throw new LogicalEquivalenceException("Both equations needed to evaluate equivalence.");    
        //TODO : evaluates logical equvalence between two equations
  }
  

    

    public static void main(String[] args) {
        
    }
}
