package org.example;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*System.out.println("введите математическое выражение");
        Scanner expr = new Scanner(System.in);
        String mathExpr = expr.nextLine();*/
        String mathExpr = "(100-50)/2";
        System.out.println(mathExpr);
        System.out.println(Calculator.parser(mathExpr));
        System.out.println(Calculator.evalAnswer(Calculator.parsRPN(Calculator.parser(mathExpr))));
    }
}
