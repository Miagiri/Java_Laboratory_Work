package org.example;


import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Calculator {
    static HashSet<Character> mathSymbol = new HashSet<>(Arrays.asList('(',')','+','-','*','/'));
    static HashSet<Character> digit = new HashSet<>(Arrays.asList('0','1','2','3','4','5','6','7','8','9','.','s','i','n','c','o','p','e'));
    public static ArrayDeque<String> parser(String expr){
        ArrayDeque<String> box1 = new ArrayDeque<>();
        Stack<String> box2 = new Stack<>();
        char symbol;
        String temp= "";
        for (int i = 0; i < expr.length(); i++) {
            symbol=expr.charAt(i);
            if (isDigit(symbol)){
                temp+=(String.valueOf(symbol));
                if(temp.equals("pi")){
                    box1.add("3.14");
                    temp="";
                } else if (temp.equals("e")) {
                    box1.add("2.71");
                    temp="";
                } else if (temp.equals("sin")||temp.equals("-sin")) {
                    int minus=1;
                    if (temp.equals("-sin")){
                        minus=-1;
                    }
                    temp="";
                    i++;
                    while (i!=expr.length()-1 && isDigit(expr.charAt(i+1))){
                        temp+=(String.valueOf(expr.charAt(i)));
                        i++;
                    }
                    temp+=(String.valueOf(expr.charAt(i)));
                    if(temp.equals("pi")){temp="3.14";}
                    if (temp.equals("e")){temp="2.71";}
                    double number=Math.sin(Double.parseDouble(temp))*minus;
                    box1.add(String.valueOf(number));
                    temp="";
                } else if (temp.equals("cos")||temp.equals("-cos")) {
                    int minus=1;
                    if (temp.equals("-cos")){
                        minus=-1;
                    }
                    temp="";
                    i++;
                    while (i!=expr.length()-1 && isDigit(expr.charAt(i+1))){
                        temp+=(String.valueOf(expr.charAt(i)));
                        i++;
                    }
                    temp+=(String.valueOf(expr.charAt(i)));
                    if(temp.equals("pi")){temp="3.14";}
                    if (temp.equals("e")){temp="2.71";}
                    double number=Math.cos(Double.parseDouble(temp))*minus;
                    box1.add(String.valueOf(number));
                    temp="";
                }
                else if(i==expr.length()-1 || !isDigit(expr.charAt(i+1))){
                    box1.add(temp);
                    temp="";
                }
            } else if (isMathSymbol(symbol)){
                if (symbol=='-' && (i==0 || !isDigit(expr.charAt(i-1)))){
                    temp="-";
                }else if (getPriority(symbol)==1){
                    box2.push(String.valueOf(symbol));
                }
                else if (getPriority(symbol)>1){
                    while (!box2.isEmpty()){
                        if(getPriority(box2.peek().charAt(0))>= getPriority(symbol)){
                            box1.add(box2.pop());
                        }
                        else break;
                    }
                    box2.push(String.valueOf(symbol));
                } else if (getPriority(symbol)==-1) {
                    while (getPriority(box2.peek().charAt(0))!=1){
                        box1.add(box2.pop());
                    }
                    box2.pop();
                }
            }
        }
        while (!box2.isEmpty()){
            box1.add(box2.pop());
        }
        return box1;
    }
    private static boolean isDigit(char symbol){
        return digit.contains(symbol);
    }
    private static boolean isMathSymbol(char symbol) {
        return mathSymbol.contains(symbol);
    }
    private static int getPriority(char operator){
        if(operator=='/'||operator=='*'){
            return 3;
        } else if (operator=='-'||operator=='+') {
            return 2;
        } else if (operator=='(') {
            return 1;
        } else if (operator==')') {
            return -1;
        }
        return 0;
    }
    public static String[] parsRPN(ArrayDeque<String> formula){
        String[] expr = new String[formula.size()];
        int size = formula.size();
        for (int i = 0; i < size; i++) {
            expr[i]=formula.pop();
        }
        return expr;
    }
    public static double evalAnswer(String[] token){
        Stack<Double> stack=new Stack<>();
        double value;

        for (String s : token) {
            switch (s) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    value = stack.pop();
                    stack.push(stack.pop() - value);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    value = stack.pop();
                    stack.push(stack.pop() / value);
                    break;
                default:
                    stack.push(Double.parseDouble(s));
            }
        }
        return stack.pop();
    }
}

