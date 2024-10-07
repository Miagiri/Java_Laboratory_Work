package makeZoo;

public class Giraffe extends Mammal{
    public Giraffe (String name, String age, Gender gender, String type) {
        super(name, age, gender,type);
    }

    @Override
    public void move() {
        System.out.println("walk");
    }

    @Override
    public void voice() {
        System.out.println("mooing");
    }

}
