package org.example;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CalculatorTest {

    @Test
    public void parser() {
        String exp = "-sin1+(31-pi)/6";
        String exp1 ="(100-50)/2";
        String exp2 ="sin1*sin1+cos1*cos1";
        String exp3 ="sinpi+2/2*(32-30)";
        String exp4 ="e*0/20+6";
        String[] actual ={"-0.8414709848078965", "31", "3.14","-", "6", "/", "+"};
        String[] actual1 ={"100","50","-","2","/"};
        String[] actual2 ={"0.8414709848078965","0.8414709848078965","*","0.5403023058681398","0.5403023058681398","*","+"};
        String[] actual3 ={"0.0015926529164868282","2","2","/","32","30","-","*","+"};
        String[] actual4 ={"2.71","0","*","20","/","6","+"};
        assertEquals(Calculator.parsRPN(Calculator.parser(exp)), actual);
        assertEquals(Calculator.parsRPN(Calculator.parser(exp1)), actual1);
        assertEquals(Calculator.parsRPN(Calculator.parser(exp2)), actual2);
        assertEquals(Calculator.parsRPN(Calculator.parser(exp3)), actual3);
        assertEquals(Calculator.parsRPN(Calculator.parser(exp4)), actual4);

    }
    @Test
    public void evalAnswer() {
        String[] exp = {"-sin1+(31-pi)/6","(100-50)/2","sin1*sin1+cos1*cos1","sinpi+2/2*(32-30)","e*0/20+6"};
        double[] expected = new double[5];
        for(int i=0;i<exp.length;i++){
            expected[i]=Calculator.evalAnswer(Calculator.parsRPN(Calculator.parser(exp[i])));
        }
        double[] actual={3.801862348525437,25.0,1.0,2.0015926529164867,6.0};
        //assertEquals(expected, actual);
        for (int i = 0; i < expected.length-1; i++) {
            assertEquals(expected[i], actual[i], 0.0);
        }
    }
}