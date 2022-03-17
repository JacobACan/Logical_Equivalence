package model;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class Operator_ReaderTest {
   @Test
   public void parseTest() {
      Operator_Reader string1 = new Operator_Reader("(p → q v (q → p) ^ s v r)");
      Operator_Reader string2 = new Operator_Reader("(q → p) ^ p");
      Operator_Reader string8 = new Operator_Reader("(q → p) ^ (p)");
      Operator_Reader string3 = new Operator_Reader("(r ^ (q → s) ^ q ^ p)"); 
      Operator_Reader string4 = new Operator_Reader("(¬r ^ (q → s) ^ q ^ p)");
      Operator_Reader string5 = new Operator_Reader("(¬r ^ (q → s) ^ q ^ p) ^ (¬r ^ (¬q → ¬s) ^ q ^ ¬p)");
      Operator_Reader string6 = new Operator_Reader("(¬r ^ (q → s) ^ q ^ p)) ^ (¬r ^ (¬q → ¬s) ^ q ^ ¬p)");
      Operator_Reader string7 = new Operator_Reader("¬r^(q→s)^q^p)^(¬r^(q→s)^q^p");

      System.out.println(string5);
   }
}
