package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.sun.jdi.Value;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Laboratory2 {
    private int N; //кол-во узлов интерполяции
    private double XX; //значение аргумента
    private double YY=0; //вычисленное значение в точке ХХ
    private double A; //краевое условие
    private double B; //краевое условие
    private int IER=0;//индикатор ошибки
    HashMap<Double,Double> dataXY=new HashMap<>(); // значения векторов X и Y в словаре
    List<Double> valueX = new ArrayList<>();//значения Х
    public String file; //путь к файлу
    List<Double> valueH = new ArrayList<>();//значения h
    List<Double> valueA = new ArrayList<>();//значения A
    List<Double> valueC = new ArrayList<>();//значения C
    List<Double> valueB = new ArrayList<>();//значения B
    List<Double> valueF = new ArrayList<>();//значения F
    List<Double> valueV = new ArrayList<>();//значения V
    List<Double> valueXi = new ArrayList<>();//значения Xi
    List<Double> valueZ = new ArrayList<>();//значения Z

    //конструктор
    public Laboratory2(String file){
        this.file=file;
    }

    //метод для считывания значений из файла
    private void readFile(){
        try {

            FileReader filereader = new FileReader(file); //создаём объект считывающий файл
            CSVReader csvReader = new CSVReader(filereader);//считываем файл как csv;

            String[] nextRecord=csvReader.readNext();//строка для считывния в неё данных из файла
            N=Integer.parseInt(nextRecord[0]);//считываем переменную переводя строку в число

            nextRecord=csvReader.readNext();
            XX=Double.parseDouble(nextRecord[0]);

            nextRecord=csvReader.readNext();
            A=Double.parseDouble(nextRecord[0]);
            B=Double.parseDouble(nextRecord[1]);

            while ((nextRecord = csvReader.readNext()) != null) {
                valueX.add(Double.parseDouble(nextRecord[0]));
                dataXY.put(Double.parseDouble(nextRecord[0]),Double.parseDouble(nextRecord[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        //проверка на принадлежность отрезку
        if(XX< valueX.get(0) || XX>valueX.get(valueX.size()-1)){
            IER=3;
        }

        //проверка сортировки по возрастанию
        for (int i=0;i<valueX.size()-1;i++){
            if(valueX.get(i)>=valueX.get(i+1)){
                IER=2;
                break;
            }
        }

        //возможность проверки возможности построения кубического сплайна
        if(N<2){
            IER=1;
        }

    }
    //метод для выбора отрезка
    private static List<Double> closest(Double XX, List<Double> valueX) {
        List<Double> closest = new ArrayList<>();
        List<Double> diffs = new ArrayList<>();
        for (Double x : valueX) {
            final Double diff = Math.abs(x - XX); //модуль разности

            //проверка правильной сортировки по возрастанию
            int index = Collections.binarySearch(diffs, diff);
            if (index < 0) {
                index = -(index + 1);
            }

            if (index >= 2) {
                continue;
            }

            closest.add(index, x);
            diffs.add(index, diff);

            if (closest.size() > 2) {
                closest.remove(2);
                diffs.remove(2);
            }
        }
        return closest;
    }

    //метод трёхточечной прогонки

    private void threePointRun (){
        //заполняем массив размеров отрезков
        valueH.add(0.0);
        for (int i = 1; i < N; i++) {
            valueH.add(valueX.get(i)-valueX.get(i-1));
        }

        //заполняем массив F
        valueF.add((3/valueH.get(1))*((((dataXY.get(valueX.get(1))-dataXY.get(valueX.get(0)))/valueH.get(1))-A)));
        for (int i = 1; i < N-1; i++) {
            double a =dataXY.get(valueX.get(i));
            double b =dataXY.get(valueX.get(i+1));
            double c =dataXY.get(valueX.get(i-1));
            double h= valueH.get(i);
            double h1=valueH.get(i+1);
           valueF.add(6*(((b-a)/h1)-((a-c)/h)));
        }
        valueF.add(B);

        //заполняем массив B
        valueB.add(0.5);
        for (int i = 1; i < N-1; i++) {
            valueB.add(valueH.get(i+1));
        }
        valueB.add(0.0);

        //заполняем массив C
        valueC.add(1.0);
        for (int i = 1; i < N-1; i++) {
            valueC.add(2*(valueH.get(i)+valueH.get(i+1)));
        }
        valueC.add(1.0);

        //заполняем массив A
        valueA.add(0.0);
        for (int i = 1; i < N-1; i++) {
            valueA.add(valueH.get(i));
        }
        valueA.add(0.0);

        //заполняем массив Xi
        valueXi.add(-(valueB.getFirst()/valueC.getFirst()));
        for (int i = 1; i < N-1; i++) {
            valueXi.add(-(valueB.get(i)/(valueC.get(i)+valueA.get(i)*valueXi.get(i-1))));
        }
        valueXi.add(-1*(valueA.getLast()/valueC.getLast()));

        //заполняем массив V
        valueV.add(valueF.getFirst()/valueC.getFirst());
        for (int i = 1; i < N-1; i++) {
            valueV.add((valueF.get(i)-valueA.get(i)*valueV.get(i-1))/(valueC.get(i)+valueA.get(i)*valueXi.get(i-1)));
        }
        valueV.add(valueF.getLast()/valueC.getLast());

        //заполняем массив Z
        double a =valueV.getLast();
        double b =valueXi.getLast();
        double c =valueXi.getLast();
        double d= valueV.getLast();
        valueZ.add((a+b*d)/(1-b*c));
        int j=0;
        for (int i =N-2; i >= 0; i--) {
            valueZ.add(valueV.get(i)+ (valueXi.get(i)*valueZ.get(j)));
            j++;
        }
        Collections.reverse(valueZ);//меняем элементы местами в нужном порядке
    }

    //метод для вычисления сплайна
    public void counter() throws IOException {
        System.out.println("Лабораторная работа:интерполирование кубическими сплайнами\n" +
                "Задание 2 вариант 2\n" +
                "Вычисление значения первой производной таблично заданной функции f(x) взаданной точке с помощью кубического сплайна. \n"+
                "Выполнила студентка 3 курса 1 группы Мягкова Ирина");
        readFile();
        //поиск ошибки
        switch (IER){
            case 0: {
                break;
            }
            case 1:{
                System.out.println("Кубический сплайн не может быть построен IER="+IER);
                return;
            }
            case 2:{
                System.out.println("Нарушен порядок возрастания аргумента IER="+IER);
                return;
            }
            case 3:{
                System.out.println("Аргумент XX не пренадлежит отрезку значений x IER="+IER);
                return;
            }
        }

        threePointRun();

        List<Double> closest =closest(XX,valueX);//выбранный отрезок сплайна
        Collections.reverse(closest);
        int i=1;
        for(int j=1;j<valueX.size();j++){
            if(valueX.get(j)==closest.getLast()){
                i=j;
                break;
            }
        }

        //вычисление коэфицентов b и d
        double ci=valueZ.get(i);
        double ci1=valueZ.get(i+1);
        double ai=dataXY.get(valueX.get(i));
        double ai1=dataXY.get(valueX.get(i+1));

        double di1=(ci-ci1)/(valueX.get(i)-valueX.get(i+1));

        double bi1=(ai-ai1-(ci1/2)*Math.pow(valueX.get(i)-valueX.get(i+1),2)-(di1/6)*Math.pow(valueX.get(i)-valueX.get(i+1),3))/(valueX.get(i)-valueX.get(i+1));

        double bi=bi1+ci1*(valueX.get(i)-valueX.get(i+1))+(di1/2)*Math.pow(valueX.get(i)-valueX.get(i+1),2);

        double di=(valueZ.get(i-1)-ci)/(closest.getFirst()-closest.getLast());
        //-6*(bi-(valueH.get(i)/2)*ci-((dataXY.get(valueX.get(i))-dataXY.get(valueX.get(i-1)))/valueH.get(i)))/Math.pow(valueH.get(i),2);

        double Si=ai+bi*(XX-closest.getLast())+(ci/2)*Math.pow(XX-closest.getLast(),2)+(di/6)*Math.pow(XX-closest.getLast(),3);
        System.out.printf("%.4f",Si);
        //формула производной сплайна
        YY=bi+ci*(XX-closest.getLast())+(di/2)*Math.pow((XX-closest.getLast()),2);
        System.out.println("\nYY="+YY+" IER="+IER);
        //запись ответов в файл
        String[] answer = new String[2];
        answer[0]="YY="+YY;
        answer[1]="IER="+IER;
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\User\\OneDrive\\Documents\\лабы5сем\\выводЛабораторная2_2.csv"));
        writer.writeNext(answer, false);
        writer.close();
    }
}



/*
константа
10,
0.1,
0,0,
-5,5
-3.5,5
-1,5
-0.1,5
0,5
0.2,5
1,5
3,5
5.6,5
10,5

линейная
10,
0.1,
1,0,
-5,-5
-3.5,-3.5
-1,-1
-0.1,-0.1
0,0
0.2,0.2
1,1
3,3
5.6,5.6
10,10

квадратичная
10,
0.1,
-10,2,
-5,25
-3.5,12.25
-1,1
-0.1,0.01
0,0
0.2,0.04
1,1
3,9
5.6,31.36
10,100

кубическая
10,
0.1,
75,60,
-5,-125
-3.5,-42.875
-1,-1
-0.1,-0.001
0,0
0.2,0.008
1,1
3,27
5.6,175.616
10,1000
 */