package assignment1;

import java.time.LocalDate;
import java.util.Objects;

public class Person {
    private int ID;
    private String name;
    private String lastName;
    private String patronymic;
    private LocalDate birthday;
    private Gender gender;
    private int passport;
    private int age;

    /**
     * конструктор человек
     * @param ID
     * @param name
     * @param lastName
     * @param patronymic
     * @param birthday
     * @param gender
     * @param passport
     */
    public Person(int ID,String name,String lastName,String patronymic, LocalDate birthday, Gender gender,int passport) {
        this.ID=ID;
        this.name=name;
        this.lastName=lastName;
        this.patronymic=patronymic;
        this.age = LocalDate.now().getYear() - birthday.getYear();
        this.gender=gender;
        this.passport=passport;
    }

    /**
     * геттер
     * @return
     */
    public String toString(){
        return lastName+" "+name+" "+patronymic+" "+age+" "+passport;
    }

    /**
     * геттер
     * @return
     */
    public int getAge() {
        return age;
    }
    /**
     * геттер
     * @return
     */
    public String getName() {
        return lastName+" "+name+" "+patronymic;
    }
    /**
     * геттер
     * @return
     */
    public String getBirthday() {
        return birthday.toString();
    }
    /**
     * геттер
     * @return
     */
    public Gender getGender() {
        return gender;
    }
    /**
     * геттер
     * @return
     */
    public int getPas() {
        return passport;
    }
    /**
     * геттер
     * @return
     */
    public int getID() {
        return ID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return ID == person.ID && passport == person.passport && age == person.age && Objects.equals(name, person.name) && Objects.equals(lastName, person.lastName) && Objects.equals(patronymic, person.patronymic) && Objects.equals(birthday, person.birthday) && gender == person.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, lastName, patronymic, birthday, gender, passport, age);
    }
}
