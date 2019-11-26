// Pair class implementation. Holds two values and nothing more.

import java.util.*;

class Pair<T> {
    private T first;
    private T second;

    public Pair(T firstElement, T secondElement) {
        first = firstElement;
        second = secondElement;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public void setFirst(T value) {
        first = value;
    }

    public void setSecond(T value) {
        second = value;
    }

    public void newValues(ArrayList<T> values) {
        if (values.size() != 2)
            System.err.println("Error: Please provide an array of size 2");
        first = values.get(0);
        second = values.get(1);
    }

    public String toString() {
        return (first + ", " + second);
    }

}