package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected void addResumeToStorage(Resume resume, int index) {
        storage[size] = resume;
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка удаления: Резюме " + uuid + " не найдено. ");
        } else {
            storage[index] = storage[size-1];
            storage[size - 1] = null;
            size--;
            System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
        }
    }
}
