package io.github.gafelix.todo.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;

public class Table {

    @Id
    private String id;
    private Map<Coordinates, Card> cards;

    @AllArgsConstructor
    @Getter
    @Setter
    public static class Coordinates {
        private byte x, y;

        @Override
        public boolean equals(Object otherObject) {
            if(otherObject == null) return false;
            if(otherObject == this) return true;
            if(otherObject.getClass() != Coordinates.class) return false;
            var otherCoordinate = (Coordinates) otherObject;
            return (this.getX() == otherCoordinate.getX() &&
                    this.getY() == otherCoordinate.getY());
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.getX(), this.getY());
        }
    }
}