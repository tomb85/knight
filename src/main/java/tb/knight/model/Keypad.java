package tb.knight.model;

import com.google.common.collect.Maps;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.Map;

public class Keypad {

    private Map<Position, Character> keys = Maps.newHashMap();

    public void addKey(char code, int x, int y) {
        keys.put(new Position(x, y), code);
    }

    public MovementTable asMovementTable() {
        MovementTable table = new MovementTable();
        for (Position from : keys.keySet()) {
            for (int dx = -2; dx <= 2; dx++) {
                for (int dy = -2; dy <= 2; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) == 3) {
                        Character to = keys.get(new Position(from.getX() + dx, from.getY() + dy));
                        if (to != null) {
                            table.addMovement(keys.get(from), to);
                        }
                    }
                }
            }
        }
        return table;
    }

    private class Position {

        private final int x;
        private final int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (obj == this) {
                return true;
            }
            if (obj.getClass() != getClass()) {
                return false;
            }
            Position rhs = (Position) obj;
            return new EqualsBuilder()
                    .append(x, rhs.x)
                    .append(y, rhs.y)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).
                    append(x).
                    append(y).
                    toHashCode();
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}