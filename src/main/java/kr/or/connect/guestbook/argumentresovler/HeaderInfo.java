package kr.or.connect.guestbook.argumentresovler;

import java.util.HashMap;
import java.util.Map;

public class HeaderInfo {
    private Map<String, String> map;

    public HeaderInfo() {
        map = new HashMap<>();
    }

    public void put(String name, String value) {
        System.out.println(name + ", " + value);
        map.put(name, value);
    }

    public String get(String name) {
        return map.get(name);
    }

    public String toString() {
        String str = "";
        for(String key: map.keySet()) {
            str += key + ", " + map.get(key) + "\n";
        }
        return str;
    }
}
