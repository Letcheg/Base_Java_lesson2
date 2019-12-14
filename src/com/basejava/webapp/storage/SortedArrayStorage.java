package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        if (uuid.matches("uuid[1-9]\\d{0,5}") == false || Integer.parseInt(uuid.substring(4)) > STORAGE_LIMIT) {
            return -10001;
        } else {
            Resume searchKey = new Resume();
            searchKey.setUuid(uuid);
            return Arrays.binarySearch(storage, 0, storageSize, searchKey);
        }
    }

    @Override
    protected void addResumeInStorage(Resume resume) {
        int binarySearchResult = (-saveIndex) - 1;//позиция, в которой должен находиться ненайденный результат binarySearch. Бинарный поиск(метод getIndex), если не найдено искомое, возвращает отрицательное число = позиция (с 1) искомого в упорядоченном массиве
        if (binarySearchResult < storageSize & storageSize != 0) {//сохранение упорядоченности массива при записи нового элемента
            for (int i = storageSize; i > binarySearchResult; i--) {
                storage[i] = storage[i - 1];
            }
        }
        storage[binarySearchResult] = resume;
    }
}


