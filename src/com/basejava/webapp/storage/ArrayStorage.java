package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    public void save(Resume resume) {
        if (getIndex(resume.getUuid()) != -1) {
            System.out.println("Ошибка: Резюме " + resume.getUuid() + " уже существует. ");
        } else if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: Память хранилища заполнена. ");
        } else {
            storage[size] = resume;
            size++;
        }
    }
}
