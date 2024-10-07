package makeZoo;

import java.util.ArrayList;
import java.util.List;

public class BirdZone<BIRD extends Birds> extends Zone {
    private static final String nameZone ="Птицы";
    private List<Valleres> zoneValleres = new ArrayList<>();

    public BirdZone() {
        super(nameZone);
    }
}
