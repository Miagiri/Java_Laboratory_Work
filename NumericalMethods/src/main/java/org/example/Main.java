package org.example;

import java.io.IOException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        String file="C:\\Users\\User\\OneDrive\\Documents\\лабы5сем\\значенияДляЛабораторной4.csv";
        Laboratory4 laba = new Laboratory4(file);
        laba.counter();
    }
}