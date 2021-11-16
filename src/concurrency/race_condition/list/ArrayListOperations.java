package concurrency.race_condition.list;

import java.util.ArrayList;
import java.util.List;

public class ArrayListOperations implements ListOperations{
    public List<Object> dataList = new ArrayList<>();

    @Override
    public void addElementThreadUnsafe(Object element) {
        this.dataList.add(element);
        //System.out.println("Added "+element+" to dataList");
    }

    @Override
    public void deleteElementThreadUnsafe(int index) {
        this.dataList.remove(index);
        //System.out.println("Removed element at index "+index+" from dataList");
    }

    @Override
    public void addElementThreadSafe(Object element) {
        synchronized (this) {
            this.dataList.add(element);
        }
    }

    @Override
    public void deleteElementThreadSafe(int index) {
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
