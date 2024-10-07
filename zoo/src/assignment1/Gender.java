package assignment1;

public enum Gender {
    MALE("male"),
    FEMALE("female");

    private String name;

    public String getName() {
        return name;
    }

    Gender(String name) {
        this.name = name;
    }
}
