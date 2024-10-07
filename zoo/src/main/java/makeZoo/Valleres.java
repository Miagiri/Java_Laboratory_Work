package makeZoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Valleres<T extends Animal> {
    private int quantity = 0;
    private final String name;
    private static List<Animal> animals = new ArrayList<>();

    public Valleres(String name) {
        this.name = name;
    }

    public int getNumberOfAnimals() {
        return quantity;
    }

    public String getName() {
        return name;
    }

//    public void addNewAnimal(String name, Animal animal) throws InvalidAnimalTypeException {
//        String type=animal.getType();
//        if(Objects.equals(type, typeOfAnimal)){
//            numberOfAnimals++;
//            animal.changeValeres(name);
//        }
//        else {
//            throw new InvalidAnimalTypeException("Exeption:wrong type of animal!");
//        }
//    }
    public void addNewAnimal(Animal animal){
        quantity++;
        animals.add(animal);
    }
    public String toString(){
        return name+" колличество животных "+quantity;
    }
    public void showInfo(){
        System.out.println(Arrays.toString(animals.toArray()));
    }
}
