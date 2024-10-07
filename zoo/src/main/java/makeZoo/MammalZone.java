package makeZoo;

import java.util.ArrayList;
import java.util.List;

public class MammalZone<MAMAL extends Mammal> extends Zone {
    private static final String nameZone ="Млекопитающие";
    private List<Valleres> zoneValleres = new ArrayList<>();

    public MammalZone() {
        super(nameZone);
    }

}
