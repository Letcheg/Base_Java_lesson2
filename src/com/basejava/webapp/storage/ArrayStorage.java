package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {

    private int size = 0;
    private Resume[] storage = new Resume[10000];


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
        }
        else {
            System.out.println("Ошибка: Резюме " + r.getUuid() + " не найдено. ");
        }
    }

    public void save(Resume resume) {
        if (!checkExistResume(resume.getUuid())) {
            if (storage.length > size) {
                storage[size] = resume;
                size++;
            }
            else {
                System.out.println("Ошибка: Память хранилища заполнена. ");
            }
        }
        else {
            System.out.println("Ошибка: Резюме " + resume.getUuid() + " уже существует. ");
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
                if (storage[i].getUuid().equals(uuid)) {
                    if (size - 1 - i >= 0) System.arraycopy(storage, i + 1, storage, i, size - 1 - i);
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
