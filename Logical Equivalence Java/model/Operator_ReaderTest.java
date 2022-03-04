package model;

import org.junit.Test;
import org.junit.platform.commons.annotation.Testable;

@Testable
public class Operator_ReaderTest {
   @Test
   public void parseTest() {
      Operator_Reader string1 = new Operator_Reader("(p → q v (q → p) ^ s v r)");
      System.out.println(string1);
   }
    

}
