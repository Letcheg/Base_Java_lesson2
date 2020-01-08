package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }

    @Override
    protected void addResumeToStorage(Resume resume, int index) {
        index = -index - 1;//позиция, в которой должен находиться ненайденный результат binarySearch. Бинарный поиск(метод getIndex), если не найдено искомое, возвращает отрицательное число = позиция (с 1) искомого в упорядоченном массиве
        if (index < size & size != 0) {//сохранение упорядоченности массива при записи нового элемента
            System.arraycopy(storage, index, storage, index+1, size - index);
        }
        storage[index] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка удаления: Резюме " + uuid + " не найдено. ");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
            System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
        }
    }
}


