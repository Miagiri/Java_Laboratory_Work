package makeZoo;

public class Mammal extends Animal{
    public Mammal(String name, String age, Gender gender, String type) {
        super(name, age, gender, type);
    }

    public static void addValleres(Valleres<Mammal> valleresM) {
    }

    @Override
    public void move() {
        System.out.println("wolk");
    }

    @Override
    public void voice() {
        System.out.println("voice");
    }
}
