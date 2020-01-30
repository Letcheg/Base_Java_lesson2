package com.basejava.webapp.storage;

import com.basejava.webapp.exception.ExistStorageException;
import com.basejava.webapp.exception.NotExistStorageException;
import com.basejava.webapp.exception.StorageException;
import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int size = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void addResumeToStorage(Resume resume, int index);

    protected abstract void deletion(String uuid, int index);

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
        System.out.println("Все резюме удалены.");
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено. ");
        }
    }

    public void save(Resume resume) {
        if (size >= STORAGE_LIMIT) {
            throw new StorageException("Хранилище заполнено", resume.getUuid());
        } else {
            int index = getIndex(resume.getUuid());
            if (index >= 0) {
                throw new ExistStorageException(resume.getUuid());
            } else {
                addResumeToStorage(resume, index);
                size++;
                System.out.println("Резюме " + resume.getUuid() + " сохранено в хранилище.  " + "Общее количество резюме " + size);
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deletion(uuid, index);
            storage[size - 1] = null;
            size--;
            System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
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
