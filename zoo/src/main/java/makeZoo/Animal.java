package makeZoo;

public abstract class Animal implements Movable,Talking {
//    зоопарк: разные зоны 3 (с разными животными), в каждой зоне вальеры с одним и тем же животным(есть)
//    В вальер можно передать животное только того типа который есть в нём(есть)
//    Если выдаёт животное которое не относится вылетает исключение(собственное invalidAnimalTypeExeption)(есть)
//    3 зоны, класс вальер, млекопитающе, водоплавающие, птицы. Животные имеют общий метод мув, голос(есть)
//    у вальера был метод showInfo который будет показывать информацию о животном(гендер)(есть)
//    у каждой зоны был статический метод clearSection который убирает все вальеры(есть)
    private String name;
    private String age;
    private Gender gender;
    private String type;
    private String nameOfValleres;

    public Animal(String name, String age,Gender gender,String type) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }
    public String getType() {
        return type;
    }

    public Gender getGender() {
        return gender;
    }

    public String getNameOfValleres() {
        return nameOfValleres;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setType(String type) {
        this.age = type;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setNameOfValleres(String nameOfValleres) {
        this.nameOfValleres = nameOfValleres;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                '}';
    }
}
