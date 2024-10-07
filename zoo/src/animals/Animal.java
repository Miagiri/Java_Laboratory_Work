package animals;

//не обязательно реализовывать все методы интерфейса в абстрактном классе
public abstract class Animal implements Movable {
    private String name;
    private int age;
    private int numberOfTines;

    public  Animal(String name,int age, int numberOfTines){
        this.name = name;
        this.age = age;
        this.numberOfTines = numberOfTines;
    }

    public Animal(){}

    protected void voice(){
        System.out.println("animal voice");
    }

    /**
     * геттер
     * @return возраст
     */
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName(){
        return name;
    }


    public String toString(){
        String str = name + ", " + age + ", " + numberOfTines;
        return str;
    }
}
