package assignment1;


import java.time.LocalDate;

public abstract class Contract {
    protected int id;
    protected LocalDate dateBegin;
    protected  LocalDate dateEnd;
    protected int number;
    protected Person person;

    public Contract(int id, LocalDate dateBegin, LocalDate dateEnd, int number, Person person) {
        this.id = id;
        this.dateBegin = dateBegin;
        this.dateEnd = dateEnd;
        this.number = number;
        this.person = person;
    }

    /**
     * геттер
     * @return
     */
    public int getId(){
        return this.id;
    }
    /**
     * геттер
     * @return
     */
    public LocalDate getDateBegin(){
        return  dateBegin;
    }
    /**
     * геттер
     * @return
     */
    public LocalDate getDateEnd(){
        return  dateEnd;
    }
    /**
     * геттер
     * @return
     */
    public int getNumber(){
        return number;
    }
    /**
     * геттер
     * @return
     */
    public String getPerson(){
        return person.toString();
    }
}
