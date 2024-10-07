package makeZoo;

public class Elephant extends Mammal{
    public Elephant (String name, String age, Gender gender, String type) {
        super(name, age, gender,type);
    }

    @Override
    public void move() {
        System.out.println("walk");
    }

    @Override
    public void voice() {
        System.out.println("vibration");
    }

}
