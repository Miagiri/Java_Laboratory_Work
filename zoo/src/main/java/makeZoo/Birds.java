package makeZoo;

public class Birds extends Animal {
    public Birds(String name, String age, Gender gender,String type) {
        super(name, age, gender,type);
    }

    @Override
    public void move() {
        System.out.println("fly");
    }

    @Override
    public void voice() {
        System.out.println("kar");
    }
}
