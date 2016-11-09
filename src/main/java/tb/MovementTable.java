package tb;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class MovementTable {

    private Multimap<Character, Character> movements = ArrayListMultimap.create();

    public void addMovement(char from, char to) {
        movements.put(from, to);
    }

    public boolean containsKey(char key) {
        return movements.containsKey(key);
    }

    public Collection<Character> getDestinationsFrom(char code) {
        return movements.get(code);
    }

    public Set<Character> getKeys() {
        return movements.keySet();
    }
}
