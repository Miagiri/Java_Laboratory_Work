package assignment1;

import java.time.LocalDate;

public class DigitalTV extends Contract {

    private String channelPac;

    /**
     * конструктор цифровое телевидение
     * @param ID
     * @param dateBegin
     * @param dateEnd
     * @param number
     * @param person
     * @param channelPac
     */
    public DigitalTV(int ID, LocalDate dateBegin, LocalDate dateEnd, int number, Person person, String channelPac){
        super(ID, dateBegin, dateEnd, number, person);
        this.channelPac=channelPac;
    }
    /**
     * геттер
     * @return
     */
    public String getChannelPac(){
        return channelPac;
    }
    /**
     * геттер
     * @return
     */
    public String toString(){
        return id +" "+dateBegin+" "+dateEnd+" "+number+" "+channelPac;
    }
}
