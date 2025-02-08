package org.example;

import java.io.IOException;
import java.util.*;
import static java.lang.Math.abs;

public class Laboratory3 {
    private final int N = 10; //кол-во узлов интерполяции
    private final int K = 4;
    private List<Double> valueA = new ArrayList<>();//значения A
    private List<Double> valueC = new ArrayList<>();//значения C
    private List<Double> valueB = new ArrayList<>();//значения B
    private List<Double> valueP = new ArrayList<>();//значения P
    private List<Double> valueQ = new ArrayList<>();//значения Q
    private Double[] valueX = new Double[N];//значения X
    private Double[] valueXNew = new Double[N];//значения X новые
    private Double[] valueF = new Double[N];//значения F
    public double dX = 0;

    //конструктор
    public Laboratory3() {
    }

    //метод для заполнения матрицы
    private void buildMatrix() {
        Random random = new Random();
        for (int i = 0; i < N; i++) {
            valueP.add(random.nextDouble(20) - 10);
            valueQ.add(random.nextDouble(20) - 10);
            /*if (i%2==0){
                valueP.add(8.0);
                valueQ.add(9.0);
            }else{
                valueP.add(9.0);
                valueQ.add(8.0);
            }*/
        }

        valueA.add(0.0);
        for (int i = 1; i < N; i++) {
            if (i != K + 1 & i != K + 2) {
                valueA.add(random.nextDouble(20) - 10);
               /* if (i % 2 == 0) {
                    valueA.add(3.0);
                }else{
                    valueA.add(2.0);
                }*/
            } else if (i == K + 1) {
                valueA.add(valueP.get(K + 1));
            } else {
                valueA.add(valueQ.get(K + 2));
            }
        }
        for (int i = 0; i < N - 1; i++) {
            if (i != K - 1 & i != K) {
                valueC.add(random.nextDouble(20) - 10);
                /*if (i % 2 == 0) {
                    valueC.add(5.0);
                }else{
                    valueC.add(4.0);
                }*/
            } else if (i == K - 1) {
                valueC.add(valueP.get(K - 1));
            } else {
                valueC.add(valueQ.get(K));
            }
        }
        valueC.add(0.0);
        for (int i = 0; i < N; i++) {
            if (i != K & i != K + 1) {
                valueB.add(random.nextDouble(20) - 10);
                /*if (i % 2 == 0) {
                    valueB.add(6.0);
                }else{
                    valueB.add(7.0);
                }*/
            } else if (i == K) {
                valueB.add(valueP.get(K));
            } else {
                valueB.add(valueQ.get(K + 1));
            }
        }

        for (int i = 0; i < N; i++) {
            //valueX[i] = random.nextDouble(20) - 10;
            valueX[i]=1.0;
        }

        valueF[0]=valueB.getFirst()*valueX[0]+valueC.getFirst()*valueX[1]+valueP.getFirst()*valueX[K]+valueQ.getFirst()*valueX[K+1];
        for (int i = 1; i < N-1; i++) {
            valueF[i]=valueA.get(i)*valueX[i-1]+valueB.get(i)*valueX[i]+valueC.get(i)*valueX[i+1]+valueP.get(i)*valueX[K]+valueQ.get(i)*valueX[K+1];
        }
        valueF[N-1]=valueP.getLast()*valueX[K]+valueQ.getLast()*valueX[K+1]+valueA.getLast()*valueX[N-2]+valueB.getLast()*valueX[N-1];
        valueF[K-1]=valueA.get(K-1)*valueX[K-2]+valueB.get(K-1)*valueX[K-1]+valueP.get(K-1)*valueX[K]+valueQ.get(K-1)*valueX[K+1];
        valueF[K]=valueA.get(K)*valueX[K-1]+valueP.get(K)*valueX[K]+valueQ.get(K)*valueX[K+1];
        valueF[K+1]=valueC.get(K+1)*valueX[K+2]+valueP.get(K+1)*valueX[K]+valueQ.get(K+1)*valueX[K+1];
        valueF[K+2]=valueC.get(K+2)*valueX[K+3]+valueP.get(K+2)*valueX[K]+valueQ.get(K+2)*valueX[K+1]+valueB.get(K+2)*valueX[K+2];

        /*for (int i = 0; i < N; i++) {
            System.out.println(valueX[i]);
        }
        System.out.println("\n");*/
    }

    //метод для решения СЛАУ
    public void counter() throws IOException {
        System.out.println("Лабораторная работа: Решение систем линейных уравнений с разреженными матрицами специального вида\n" + "Задание 1 вариант 1 \n" + "Выполнила студентки 3 курса 1 группы Мягкова Ирина");
        dX = 0;
        //заполняем матрицу
        buildMatrix();
        /*System.out.println(valueC);
        System.out.println(valueB);
        System.out.println(valueA);
        System.out.println("\n" + valueP);
        System.out.println(valueQ + "\n");
        for (int i = 0; i < N; i++) {
            System.out.println(valueF[i]);
        }
        System.out.println("\n");*/


        double R = 0;
        for (int i = 0; i < K; i++) {
            R = 1 / valueB.get(i);
            valueB.set(i, 1.0);
            valueC.set(i, R * valueC.get(i));
            valueF[i]=R * valueF[i];
            valueP.set(i, valueP.get(i) * R);
            valueQ.set(i, valueQ.get(i) * R);
            R = valueA.get(i + 1);
            valueA.set(i + 1, 0.0);
            valueF[i + 1]= valueF[i + 1] - R*valueF[i];
            valueB.set(i + 1, valueB.get(i + 1) - R * valueC.get(i));


            valueP.set(i + 1, valueP.get(i + 1) - R * valueP.get(i));
            if (i == K - 2) {
                valueC.set(i + 1, valueP.get(i + 1));
            } else if (i == K - 1) {
                valueB.set(i + 1, valueP.get(i + 1));
            }

            valueQ.set(i + 1, valueQ.get(i + 1) - R * valueQ.get(i));
            if (i == K - 1) {
                valueC.set(i + 1, valueQ.get(i + 1));
            }

        }


        for (int i = N - 1; i > K + 1; i--) {
            R = 1 / valueB.get(i);
            valueB.set(i, 1.0);
            valueA.set(i, R * valueA.get(i));
            valueF[i]= R * valueF[i];
            valueP.set(i, valueP.get(i) * R);
            valueQ.set(i, valueQ.get(i) * R);
            R = valueC.get(i - 1);
            valueC.set(i - 1, 0.0);
            valueF[i - 1]= valueF[i - 1] - R* valueF[i];

            valueB.set(i - 1, valueB.get(i - 1) - R * valueA.get(i));

            valueP.set(i - 1, valueP.get(i - 1) - R * valueP.get(i));
            if (i == K + 2) {
                valueA.set(i - 1, valueP.get(i - 1));
            }

            valueQ.set(i - 1, valueQ.get(i - 1) - R * valueQ.get(i));
            if (i == K + 3) {
                valueA.set(i - 1, valueQ.get(i - 1));
            } else if (i == K + 2) {
                valueB.set(i - 1, valueQ.get(i - 1));
            }
        }

        valueF[K]=valueP.get(K)*valueX[K]+valueQ.get(K)*valueX[K+1];
        valueF[K+1]=valueP.get(K+1)*valueX[K]+valueQ.get(K+1)*valueX[K+1];

       /* System.out.println("\n" + valueC);
        System.out.println(valueB);
        System.out.println(valueA);
        System.out.println("\n" + valueP);
        System.out.println(valueQ);
        for (int i = 0; i < N; i++) {
            System.out.println(valueF[i]);
        }
        System.out.println("\n");

        for (int i = 0; i < N; i++) {
            if (i != K - 1 & i != K & i != K + 1 & i != K + 2) {
                System.out.println(valueA.get(i) + valueB.get(i) + valueC.get(i) + valueP.get(i) + valueQ.get(i));
            } else if (i == K - 1) {
                System.out.println(valueA.get(i) + valueB.get(i) + valueP.get(i) + valueQ.get(i));
            } else if (i == K) {
                System.out.println(valueA.get(i) + valueP.get(i) + valueQ.get(i));
            } else if (i == K + 1) {
                System.out.println(valueC.get(i) + valueP.get(i) + valueQ.get(i));
            } else {
                System.out.println(valueB.get(i) + valueC.get(i) + valueP.get(i) + valueQ.get(i));
            }
        }
        System.out.println("\n");*/

        //обнуление p q
        R = valueP.get(K);
        valueP.set(K, 1.0);
        valueB.set(K, 1.0);
        valueQ.set(K, valueQ.get(K) / R);
        valueC.set(K, valueQ.get(K));
        valueF[K]=valueF[K]/R;
        for (int j = K + 1; j < N; j++) {
            R = valueP.get(j);
            valueF[j]=valueF[j]-R*valueF[K];
            valueP.set(j, 0.0);
            valueQ.set(j, valueQ.get(j) - R * valueQ.get(K));
            if (j == K + 1) {
                valueA.set(j, valueP.get(j));
                valueB.set(j, valueQ.get(j));
            }
            if (K == K + 2) {
                valueA.set(j, valueQ.get(j));
            }
        }

        R = valueQ.get(K + 1);
        valueQ.set(K + 1, 1.0);
        valueB.set(K + 1, 1.0);

        valueF[K+1]=valueF[K+1]/R;
        for (int j = K + 2; j < N; j++) {
            R = valueQ.get(j);
            valueF[j]=valueF[j]-valueF[K+1]*R;
            valueQ.set(j, 0.0);
            if (j == K + 2) {
                valueA.set(j, valueQ.get(j));
            }
        }

        for (int j = K; j >= 0; j--) {
            R = valueQ.get(j);
            valueF[j]=valueF[j]-R*valueF[K+1];
            valueQ.set(j, 0.0);
            if (j == K) {
                valueC.set(j, valueQ.get(j));
            }
        }

        for (int j = K - 1; j >= 0; j--) {
            R = valueP.get(j);
            valueF[j]=valueF[j]-valueF[K]*R;
            valueP.set(j, 0.0);
            if (j == K - 1) {
                valueC.set(j, valueP.get(j));
            }
        }

        //нахождение X
        valueXNew[K-1]=valueF[K-1];
        valueXNew[K]=valueF[K];
        valueXNew[K+1]=valueF[K+1];
        valueXNew[K+2]=valueF[K+2];
        for (int i = K+3; i < N; i++) {
            valueXNew[i]=valueF[i]-valueA.get(i)*valueXNew[i-1];
        }
        for (int i = K-2; i >= 0; i--) {
            valueXNew[i]=valueF[i]-valueC.get(i)*valueXNew[i+1];
        }

           for (int i = 0; i < N; i++) {
            System.out.println(valueXNew[i]);
        }
        System.out.println("\n");

        for (int i = 0; i < N; i++) {
            if (abs((valueXNew[i] - valueX[i]) / valueX[i]) > dX) {
                dX = abs((valueX[i] - valueXNew[i]) / valueX[i]);
            }
        }
       System.out.println(dX);

    }

}