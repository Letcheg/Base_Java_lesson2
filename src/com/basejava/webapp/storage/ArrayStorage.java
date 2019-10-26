package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    private int size = 0;
    private Resume[] storage = new Resume[3];


    private boolean checkExistResume(String uuid) {
        boolean check = false;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                check = true;
            }
        }
        return check;
    }

    public void clear() {
        Arrays.fill(storage, null);
        size = 0;
    }

    public void update(Resume r) {
        if (checkExistResume(r.getUuid())) {
            storage[Arrays.asList(storage).indexOf(r)] = r;
        } else {
            System.out.println("Ошибка: Резюме " + r.getUuid() + " не найдено. ");
        }
    }

    public void save(Resume r) {
        if (checkExistResume(r.getUuid()) == false) {
            if (storage.length > size) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Ошибка: Память хранилища заполнена. ");
            }
        } else {
            System.out.println("Ошибка: Резюме " + r.getUuid() + " уже существует. ");
        }
    }

    public Resume get(String uuid) {
        if (checkExistResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    return storage[i];
                }
            }
        }
        System.out.print("Ошибка: Резюме " + uuid + " не найдено. ");
        return null;

    }


    public void delete(String uuid) {
        if (!checkExistResume(uuid)) {
            System.out.println("Ошибка: Резюме " + uuid + " не найдено. ");
        } else {
            int i;
            for (i = 0; i < size; i++) {
                if (storage[i].getUuid() == uuid) {
                    for (int j = i; j < size - 1; j++) {
                        storage[j] = storage[j + 1];
                    }
                    storage[size - 1] = null;
                    size--;
                    System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
                    break;
                }
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }
}
//test
