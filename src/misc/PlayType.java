package misc;

import java.util.HashMap;
import java.util.Map;

public enum PlayType
{
	EFM(0), Team(1), TP(2);
	
	private int feature;
    private static Map<Integer, Object> map = new HashMap<>();

    private PlayType(int value) {
        this.feature = value;
    }

    static {
        for (PlayType feature : PlayType.values()) {
            map.put(feature.feature, feature);
        }
    }

    public static PlayType valueOf(int feature) {
        return (PlayType) map.get(feature);
    }

    public int getValue() {
        return feature;
    }
}
