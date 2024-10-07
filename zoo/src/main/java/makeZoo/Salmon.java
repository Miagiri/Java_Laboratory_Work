package makeZoo;

public class Salmon extends Waterfowl{
    public Salmon(String name, String age, Gender gender, String type) {
        super(name, age, gender,type);
    }

    @Override
    public void move() {
        System.out.println("swim");
    }

    @Override
    public void voice() {
        System.out.println("no");
    }
}
