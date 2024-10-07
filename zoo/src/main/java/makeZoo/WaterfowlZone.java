package makeZoo;

import java.util.ArrayList;
import java.util.List;

public class WaterfowlZone<WATERFOWL extends Waterfowl> extends Zone {
    private static final String nameZone ="Водоплавающие";
    private List<Valleres> zoneValleres = new ArrayList<>();

    public WaterfowlZone() {
        super(nameZone);
    }
}
