package makeZoo;

public class Vuiture extends Birds{

    public Vuiture(String name, String age, Gender gender,String type) {
        super(name, age, gender,type);
    }

    @Override
    public void move() {
        System.out.println("fly");
    }

    @Override
    public void voice() {
        System.out.println("whistling");
    }
}
