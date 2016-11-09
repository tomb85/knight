package tb.knight.model;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.Collection;
import java.util.Set;

public class MovementTable {

    private Multimap<Character, Character> movements = ArrayListMultimap.create();

    public void addMovement(char from, char to) {
        movements.put(from, to);
    }

    public Collection<Character> getDestinationsFrom(char code) {
        return movements.get(code);
    }

    public Set<Character> getKeys() {
        return movements.keySet();
    }
}
