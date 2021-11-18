package concurrency.race_condition.list;

import java.util.ArrayList;
import java.util.List;

public class SafeListWithSynchronize implements ListOperations{
    private List<Object> dataList = new ArrayList<>();

    @Override
    public void add(Object element) {
        synchronized (this) { // Or, make the method synchronized
            this.dataList.add(element);
        }
    }

    @Override
    public void delete(int index) {
        synchronized (this) {
            this.dataList.remove(index);
        }
    }

    @Override
    public void clear() {
        this.dataList.clear();
    }

    @Override
    public int getSize() {
        return this.dataList.size();
    }

    @Override
    public void printElements() {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < this.dataList.size(); i++) {
            res.append(i).append(" ");
        }
        System.out.println(res);
    }
}
