package concurrency.race_condition.list;

public interface ListOperations {
    void addElementThreadUnsafe(Object element);
    void deleteElementThreadUnsafe(int index);

    void addElementThreadSafe(Object element);
    void deleteElementThreadSafe(int index);

    void clear();

    int getSize();
    void printElements();
}
