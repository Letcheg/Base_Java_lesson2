package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    private static final int STORAGE_LIMIT = 10000;
    private int size = 0;
    private Resume[] storage = new Resume[STORAGE_LIMIT];


    private int checkExist(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = checkExist(resume.getUuid());
        if (index == -1) {
            System.out.println("Ошибка: Резюме " + resume.getUuid() + " не найдено. ");
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        if (checkExist(resume.getUuid()) != -1) {
            System.out.println("Ошибка: Резюме " + resume.getUuid() + " уже существует. ");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("Ошибка: Память хранилища заполнена. ");
        } else {
            storage[size] = resume;
            size++;
        }
    }


    public Resume get(String uuid) {
        int index = checkExist(uuid);
        if (index == -1) {
            System.out.print("Ошибка: Резюме " + uuid + " не найдено. ");
            return null;
        } else {
            return storage[index];
        }
    }


    public void delete(String uuid) {
        int index = checkExist(uuid);
        if (index == -1) {
            System.out.println("Ошибка: Резюме " + uuid + " не найдено. ");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
            System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }
}
