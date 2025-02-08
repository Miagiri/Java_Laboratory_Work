package org.example;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import static java.lang.Math.*;

public class Laboratory4 {
    private int N; //начальное число отрезков
    private double epsilon;//точность
    private double a; //предел интегрирования
    private double b; //предел интегрирования
    private double hMin; //наименьшее допустимое значение шага
    private int IER = 0;//индикатор ошибки
    private double I=0;//вычисленное значение интеграла
    private double Ih=0;//вычисленное значение интеграла для погрешности
    private double Eps=100;//точночть полученного решения
    private double H;//шаг с которым было получено решение
    private double Hh;//шаг с которым было получено решение для погрешности
    public String file; //путь к файлу
    Random random = new Random();


    //конструктор
    public Laboratory4(String file) {
        this.file = file;
    }

    //метод для считывания значений из файла
    private void readFile() {
        try {

            FileReader filereader = new FileReader(file); //создаём объект считывающий файл
            CSVReader csvReader = new CSVReader(filereader);//считываем файл как csv;

            String[] nextRecord = csvReader.readNext();//строка для считывния в неё данных из файла
            a = Double.parseDouble(nextRecord[0]);//считываем переменную переводя строку в число
            b = Double.parseDouble(nextRecord[1]);
            if (b<=a){
                IER=3;//ошибка входных данных
            }
            nextRecord = csvReader.readNext();
            epsilon = Double.parseDouble(nextRecord[0]);

            nextRecord = csvReader.readNext();
            N = Integer.parseInt(nextRecord[0]);

            nextRecord = csvReader.readNext();
            hMin = Double.parseDouble(nextRecord[0]);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private double F(double x){
        return x;
    }

    public void counter() throws IOException {
        System.out.println("Лабораторная работа:численное интегрирование\n" +
                "Формула средних прямоугольников \n"+
                "Выполнила студентка 3 курса 1 группы Мягкова Ирина");
        readFile();
        if(IER==3){
            System.out.println("Ошибка входных данных b<=a IER="+IER);
            return;
        }

        double x1;
        double x2;
        int j=0;
        double n=1;
        double EpsLast;
        do {
            EpsLast=Eps;
            H=(b-a)/(N*n);
            if (H<hMin) {
                IER=2;
                System.out.println("Решение не получено, шаг стал недопустимо мал IER=" + IER);
                return;
            }
            do{
                x1=a+j*H;
                x2=a+(j+1)*H;
                I+=F((x2+x1)/2)*H;
                j++;
            }while (x2!=b);
            j=0;
            Hh=H/2;
            do{
                x1=a+j*Hh;
                x2=a+(j+1)*Hh;
                Ih+=F((x2+x1)/2)*Hh;
                j++;
            }while (x2!=b);
            j=0;
            n++;
            Eps=abs((I-Ih)/(0.25-1));
        }while (Eps>epsilon && Eps!=EpsLast && Eps<EpsLast);

        if (EpsLast == Eps || EpsLast<Eps) {
            IER=1;
            System.out.println("Решение не получено, погрешность перестала уменьшаться IER=" + IER);
            return;
        }

        System.out.println("IER="+IER+" I="+I+" Eps="+Eps+" H="+H);
        //запись ответов в файл
        String[] answer = new String[4];
        answer[0]="IER="+IER;
        answer[1]="I="+I;
        answer[2]="Eps="+Eps;
        answer[3]="H="+H;
        CSVWriter writer = new CSVWriter(new FileWriter("C:\\Users\\User\\OneDrive\\Documents\\лабы5сем\\выводЛабораторная4.csv"));
        writer.writeNext(answer, false);
        writer.close();
    }

}
