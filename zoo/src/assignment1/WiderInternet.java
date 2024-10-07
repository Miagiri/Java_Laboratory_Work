package assignment1;

import java.time.LocalDate;

public class WiderInternet extends Contract {
    private int connectSpeed;

    /**
     * конструктор проводной интернет
     * @param ID
     * @param dateBegin
     * @param dateEnd
     * @param number
     * @param person
     * @param connectSpeed
     */
    public WiderInternet(int ID, LocalDate dateBegin, LocalDate dateEnd, int number, Person person, int connectSpeed){
        super(ID, dateBegin, dateEnd, number, person);
        this.id =ID;
        this.dateBegin=dateBegin;
        this.dateEnd=dateEnd;
        this.person=person;
        this.connectSpeed=connectSpeed;
    }
    /**
     * геттер
     * @return
     */
    public int getConnectSpeed(){
        return connectSpeed;
    }
    /**
     * геттер
     * @return
     */
    public String toString(){
        return id +" "+dateBegin+" "+dateEnd+" "+number+" "+connectSpeed;
    }
}
