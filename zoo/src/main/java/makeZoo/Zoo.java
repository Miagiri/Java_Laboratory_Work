package makeZoo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Zoo {
    private String nameZoo;
    private List<Zone> zooZone = new ArrayList<>();


    public Zoo(String nameZoo) {
        this.nameZoo = nameZoo;
    }
    public void addZone(Zone zone){
        zooZone.add(zone);
    }


    public String getNameZoo() {
        return nameZoo;
    }

    public void showInfo(){
        System.out.println(Arrays.toString(zooZone.toArray()));
    }

    public void setNameZoo(String nameZoo) {
        this.nameZoo = nameZoo;
    }
}
