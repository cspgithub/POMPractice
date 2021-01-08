package enums;

import java.util.HashMap;
import java.util.Map;

public enum EnumType {

	TEAM_APPROVER("admin"), TEAM_LEADER("leader"),
	LOCAL_ENV("local"),REMOTE_ENV("remote");

	private static final Map<String, EnumType> BY_LABEL = new HashMap<>();
	public final String label;

	EnumType(String label) {
		this.label = label;
	}

	static {
		for (EnumType e : values()) {
			BY_LABEL.put(e.label, e);
		}
	}
	public static EnumType valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

}
