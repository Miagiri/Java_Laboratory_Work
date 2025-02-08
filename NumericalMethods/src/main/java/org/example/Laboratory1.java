package org.example;
import com.opencsv.*;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Laboratory1 {
    private int N; //кол-во узлов интерполяции
    private double XX; //значение аргумента
    private double YY=0; //вычисленное значение в точке ХХ
    private int m; //степень многочлена Лагранжа
    private int IER=0;//индикатор ошибки
    HashMap<Double,Double> dataXY=new HashMap<>(); // значения векторов X и Y в словаре
    List<Double> valueX = new ArrayList<>();//значения Х
    public String file; //путь к файлу

    //конструктор
    public Laboratory1(String file){
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
            m=Integer.parseInt(nextRecord[0]);

            while ((nextRecord = csvReader.readNext()) != null) {
                valueX.add(Double.parseDouble(nextRecord[0]));
                dataXY.put(Double.parseDouble(nextRecord[0]),Double.parseDouble(nextRecord[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //проверка сортировки по возрастанию
        for (int i=0;i<valueX.size()-1;i++){
            if(valueX.get(i)>=valueX.get(i+1)){
                IER=2;
                break;
            }
        }
        //проверка на принадлежность отрезку
        if(XX> valueX.getFirst() || XX<valueX.getLast()){
            IER=3;
        }
    }
    //метод для выбора ближайших значений
    private static List<Double> closest_m(Double XX, List<Double> valueX, int m) {
        List<Double> closest = new ArrayList<>();
        List<Double> diffs = new ArrayList<>();

        for (Double x : valueX) {
            final Double diff = Math.abs(x - XX); //модуль разности

            //проверка правильной сортировки по возрастанию
            int index = Collections.binarySearch(diffs, diff);
            if (index < 0) {
                index = -(index + 1);
            }

            if (index >= m) {
                continue;
            }

            closest.add(index, x);
            diffs.add(index, diff);

            if (closest.size() > m) {
                closest.remove(m);
                diffs.remove(m);
            }
        }
        return closest;
    }

    //метод для подсчёта интерполяционного значения функции
    public void counter() throws IOException {
        System.out.println("Лабораторная работа:интерполирование и приближение функций\n" +
                "Задание 4\n" +
                "Интерполирование функции с помощью многочлена Лагранжа степени m на неравномерной сетке узлов. \n"+
                "Выполнили студентки 3 курса 1 группы Мягкова Ирина");
        readFile();
        //поиск ошибки
        if(N<m+1){
            IER=1;
            System.out.println("Интерполяционный многочлен степени "+m+" не может быть построен IER="+IER);
            return;
        } else if (IER==2) {
            System.out.println("Нарушен порядок возрастания аргумента IER="+IER);
            return;
        } else if (IER==3) {
            System.out.println("Аргумент XX не пренадлежит отрезку значений x IER="+IER);
            return;
        }
        List<Double> closest =closest_m(XX,valueX,m);//выбранные ближайшие значения ч
        double multiplier=1; // множитель, далее цикл по его вычислению
        for (int i = 0; i < closest.size(); i++) {
            multiplier=1;
            for (int j = 0; j < closest.size(); j++) {
                Double x=closest.get(j);
                Double x_m=closest.get(i);
                if (x!=x_m) {
                    multiplier *= (XX - x) /(x_m-x);
                }
            }
            YY+=dataXY.get(closest.get(i))*multiplier;
        }
        System.out.println("YY="+YY+" IER="+IER);
        //запись ответов в файл
        String[] answer = new String[2];
        answer[0]="YY="+YY;
        answer[1]="IER="+IER;
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\User\\OneDrive\\Documents\\лабы5сем\\выводЛабораторная1_4.csv"));
        writer.writeNext(answer, false);
        writer.close();
    }
}
