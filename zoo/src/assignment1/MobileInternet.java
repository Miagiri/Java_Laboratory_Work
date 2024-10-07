package assignment1;

import java.time.LocalDate;

public class MobileInternet<T> extends Contract {
    private int numberOfMin;
    private T sms;
    private int Gb;

    /**
     * Конструктор мобильный интернет
     * @param ID
     * @param dateBegin
     * @param dateEnd
     * @param number
     * @param person
     * @param numberOfMin
     * @param sms
     * @param Gb
     */
    public MobileInternet(int ID, LocalDate dateBegin, LocalDate dateEnd, int number, Person person, int numberOfMin, T sms, int Gb){
        super(ID, dateBegin, dateEnd, number, person);
        this.numberOfMin=numberOfMin;
        this.sms=sms;
        this.Gb=Gb;
    }
    /**
     * геттер
     * @return
     */
    public int getNumberOfMin() {

        return numberOfMin;
    }
    /**
     * геттер
     * @return
     */
    public T getSms(){
        return sms;
    }
    /**
     * геттер
     * @return
     */
    public int getGb(){
        return Gb;
    }
    /**
     * геттер
     * @return
     */
    @Override
    public String toString(){
        return id +" "+dateBegin+" "+dateEnd+" "+number+" "+numberOfMin+" "+sms+" "+Gb;
    }
}
