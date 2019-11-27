package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract int getIndex(String uuid);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка: Резюме " + resume.getUuid() + " не найдено. ");
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено. ");
        }
    }

    public void save(Resume resume) {    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.print("Ошибка: Резюме " + uuid + " не найдено. ");
            return null;
        } else {
            return storage[index];
        }
    }

    public void delete(String uuid) { }

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
