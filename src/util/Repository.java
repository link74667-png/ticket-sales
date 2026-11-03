package util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// Собственный обобщённый класс-хранилище
public class Repository<T> {
    private final List<T> items = new ArrayList<>();

    public void add(T item) { items.add(item); }

    public boolean remove(Predicate<T> condition) { return items.removeIf(condition); }

    public List<T> find(Predicate<T> condition) {
        List<T> result = new ArrayList<>();
        for (T item : items) {
            if (condition.test(item)) result.add(item);
        }
        return result;
    }

    public List<T> getAll() { return new ArrayList<>(items); }

    public int size() { return items.size(); }
}
