package tb;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import java.util.List;

public class MovementTable {

    private Multimap<Character, Character> movements = ArrayListMultimap.create();

    public void addMovement(char from, char to) {
        movements.put(from, to);
    }

    public boolean containsKey(char key) {
        return movements.containsKey(key);
    }

    public List<Character> getDestinationsFrom(char code) {
        return Lists.newArrayList(movements.get(code));
    }
}
