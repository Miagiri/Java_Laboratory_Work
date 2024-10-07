import makeZoo.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public Main() throws FileNotFoundException {
    }

    public static void main(String[] args) throws IOException {
        /*Person person = new Person(1, "a", "b", "c", null, Gender.MALE, 2);
        Movable movable = null;
        Cat cat = new Cat();
        Dog dog = new Dog();

        Scanner scanner = new Scanner(System.in);

        String name = scanner.nextLine();

        if (name == "cat") {
            movable = cat;
        } else if (name == "dog") {
            movable = dog;
        }

        movable.move();*/
        //статические методы и переменные привязанны к классу а не объекту (но можно вызвать через объект)
        //на момент инициализации статического метода нестатического не существует

//        int a = 5;
//        int b = 5;
//
//
//
//        String str1 = "123";
//        String str2 = "123";
//
//        System.out.println(str1.equals(str2));
//
//
//        List<String> list = new ArrayList();
//
//
//        list.add("hello");
//
//
//        List<Integer> list1 = new LinkedList<>();
//
//        Set<String> set = new HashSet<>();
//
//        Horse horse = new Horse("skyler", 45, Gender.FEMALE);
//
//        Valleres<Horse> sparrowValleres = new Valleres<>("sparrow", "any");
//
//        sparrowValleres.addNewAnimal("", horse);
//
//        Map<Long, String> map = new HashMap<>();
//
//        map.put(1L, "s1");
//        map.put(1L, "s2");
//
//        String value = map.get(1L);
//
//        System.out.println(value);
//
//        Comparator<Long> comparator = new LongComparator();
//
//        Set<Long> set1 = new TreeSet<>(((o1, o2) -> Long.compare(o1, o2)));
//
//        Function<String, Integer> function = str -> Integer.valueOf(str);
//
//        set1.add(2L);
//        set1.add(1L);
//        set1.add(23L);
//        set1.add(0L);
//
//
//        Integer i = function.apply("3");
//
//        System.out.println(set1);
//
//        File file=new File("C:\\Users\\User\\OneDrive\\Documents\\лаба1.txt");
//        InputStream inputStream = null;
//        try {
//             inputStream= new FileInputStream(file);
//            byte[] arr = inputStream.readAllBytes();
//            System.out.println(Arrays.toString(arr));
//            FileReader fileReader = new FileReader(file);
//            Scanner scanner = new Scanner(fileReader);
//
//            while(scanner.hasNext()) {
//
//                System.out.println(scanner.nextLine());
//            }
//
//
//            Zone zone = new
//                    Zone("") {
//                        @Override
//                        public String getNameZone() {
//                            return super.getNameZone();
//                        }
//
//                        @Override
//                        public void setNameZone(String nameZone) {
//                            super.setNameZone(nameZone);
//                        }
//
//                        @Override
//                        public void addValleres(Valleres valleres) {
//                            super.addValleres(valleres);
//                        }
//                    };
//
//            zone.addValleres(new Valleres("", ""));
//
//            return;
//
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        } finally {
//            //opencsv
//            inputStream.close();
//            System.out.println("reached finally");
//        }

        //Map<Class<? extends Birds>, List<Valleres>>
        Zoo zoo = new Zoo("Зоопарк");
        Class<Zoo> zooClass = Zoo.class;

        BirdZone<Birds> birdZone = new BirdZone<>();
        MammalZone<Mammal> mammalZone = new MammalZone<>();
        WaterfowlZone<Waterfowl> waterfowlZone = new WaterfowlZone<>();

        String birdAnimalFile = "C:\\Users\\User\\Downloads\\животныеПтицы.csv";
        String mammalAnimalFile = "C:\\Users\\User\\Downloads\\животныеМлекопитающие.csv";
        String waterfowlAnimalFile = "C:\\Users\\User\\Downloads\\животныеВодоплавающие.csv";

        List<List<String>> animal = readDataLineByLine(birdAnimalFile);
        List<Animal> bird = buildAnimals(animal);
        Map<String, Valleres<?>> birdValleres = buildValleres(bird);

        animal = readDataLineByLine(mammalAnimalFile);
        List<Animal> mammal = buildAnimals(animal);
        Map<String, Valleres<?>> mammalValleres = buildValleres(mammal);

        animal = readDataLineByLine(waterfowlAnimalFile);
        List<Animal> waterfowl = buildAnimals(animal);
        Map<String, Valleres<?>> weterfowlValleres = buildValleres(waterfowl);

        birdZone = (BirdZone<Birds>) buildZone(birdValleres, birdZone.getNameZone());
        mammalZone = (MammalZone<Mammal>) buildZone(mammalValleres, mammalZone.getNameZone());
        waterfowlZone = (WaterfowlZone<Waterfowl>) buildZone(weterfowlValleres, waterfowlZone.getNameZone());

        zoo.addZone(birdZone);
        zoo.addZone(mammalZone);
        zoo.addZone(waterfowlZone);
    }

    static class LongComparator implements Comparator<Long> {

        @Override
        public int compare(Long o1, Long o2) {
            return Long.compare(o1, o2);
        }
    }

    public static List<List<String>> readDataLineByLine(String file) {
        List<List<String>> list = new ArrayList<>();

        try {

            FileReader filereader = new FileReader(file);

            CSVReader csvReader = new CSVReader(filereader);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                List<String> str = new ArrayList<>();
                for (String cell : nextRecord) {
                    str.add(cell);
                }
                list.add(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<Animal> buildAnimals(List<List<String>> parsedData) {
        List<Animal> animals = new ArrayList<>();
        String name;
        String age;
        String type;
        String genderStr;
        Gender gender;
        for (int i = 0; i < parsedData.size(); i++) {
            name = parsedData.get(i).get(0);
            age = parsedData.get(i).get(1);
            genderStr = parsedData.get(i).get(2);
            gender = Gender.valueOf(genderStr);
            type = parsedData.get(i).get(3);
            Animal animal;
            switch (type) {
                case "Hawk":
                    animal = new Hawk(name, age, gender, type);
                    animals.add(animal);
                case "Dove":
                    animal = new Dove(name, age, gender, type);
                    animals.add(animal);
                case "Vuiture":
                    animal = new Dove(name, age, gender, type);
                    animals.add(animal);
                case "Elephant":
                    animal = new Elephant(name, age, gender, type);
                    animals.add(animal);
                case "Giraffe":
                    animal = new Giraffe(name, age, gender, type);
                    animals.add(animal);
                case "Lion":
                    animal = new Lion(name, age, gender, type);
                    animals.add(animal);
                case "Penguin":
                    animal = new Penguin(name, age, gender, type);
                    animals.add(animal);
                case "Seal":
                    animal = new Seal(name, age, gender, type);
                    animals.add(animal);
                case "Salmon":
                    animal = new Salmon(name, age, gender, type);
                    animals.add(animal);
            }

        }
        return animals;
    }

    public static Map<String, Valleres<?>> buildValleres(List<Animal> animals) {
        Map<String, Valleres<?>> valleresMap = new HashMap<>();
        Valleres valleres;
        for (Animal animal : animals) {

        }
        for (int i = 0; i < animals.size(); i++) {
            if (valleresMap.get(animals.get(i).getType()) != null) {
                valleresMap.get(animals.get(i).getType()).addNewAnimal(animals.get(i));
            } else {
                valleres = new Valleres<>(animals.get(i).getType());
                valleresMap.put(animals.get(i).getType(), valleres);
                valleresMap.get(animals.get(i).getType()).addNewAnimal(animals.get(i));
            }
        }
        return valleresMap;
    }

    public static Zone<?> buildZone(Map<String, Valleres<?>> valleres, String name) {
        Zone<?> zone = new Zone<>(name);
        for (Map.Entry<String, Valleres<?>> entry : valleres.entrySet()) {
            zone.addValleres(entry.getValue());
        }
        return zone;
    }
}

