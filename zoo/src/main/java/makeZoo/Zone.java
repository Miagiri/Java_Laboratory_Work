package makeZoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Zone<D extends Animal> {
    private String nameZone;
    private int quantity=0;
    protected static List<Valleres> zoneValleres = new ArrayList<>();

    public Zone(String nameZone) {
        this.nameZone = nameZone;
    }
    public String getNameZone() {
        return nameZone;
    }
    public void setNameZone(String nameZone) {
        this.nameZone = nameZone;
    }
    public void addValleres(Valleres valleres){
        zoneValleres.add(valleres);
        quantity++;
    }
    public static void clearSection(){
        zoneValleres.clear();
    }
    public String toString(){
        return nameZone+" колличество вальеров "+quantity;
    }

    public void showInfo(){
        System.out.println(Arrays.toString(zoneValleres.toArray()));
    }
}
