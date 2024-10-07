package animals;

public class Cat extends Animal {

    public Cat(String name,int age, int numberOfTines) {
        super(name, age, numberOfTines);
    }

    public Cat() {
    }

    void foo() {
        voice();
    }
     public void voice(){
        System.out.println("mew-mew");
    }

    @Override
    public void move() {

    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Cat cat) && getName().equals(cat.getName());
    }
}
