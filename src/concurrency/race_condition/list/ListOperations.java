package concurrency.race_condition.list;

public interface ListOperations {
    void add(Object element);

    void delete(int index);

    void clear();

    int getSize();
    void printElements();
}
