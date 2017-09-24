package qyu.shanda.model;

import java.util.HashMap;

/**
 * Created by MirQ on 17/9/24.
 */
public class ViewObject {
    private HashMap<String, Object> map = new HashMap<>();

    public void set(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }
}
