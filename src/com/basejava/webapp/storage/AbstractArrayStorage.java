package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];

    protected int storageSize = 0;

    protected abstract int getIndex(String uuid);

    protected abstract void addResumeInStorage(Resume resume, int indexresume);

    public void clear() {
        Arrays.fill(storage, 0, storageSize, null);
        storageSize = 0;
    }

    public void update(Resume resume) {
        int index = getIndex(resume.getUuid());
        if (index < 0) {
            System.out.println("Ошибка обновления: Резюме " + resume.getUuid() + " не найдено. ");
        } else {
            storage[index] = resume;
            System.out.println("Резюме " + resume.getUuid() + " обновлено. ");
        }
    }

    public void save(Resume resume) {
        if (storageSize >= STORAGE_LIMIT) {
            System.out.println("Ошибка сохранения: Память хранилища заполнена. ");
        } else {
            int indexresume = getIndex(resume.getUuid());
            if (indexresume >= 0) {
                System.out.println("Ошибка сохранения: Резюме " + resume.getUuid() + " уже существует. ");
            } else {
                addResumeInStorage(resume, indexresume);
                storageSize++;
                System.out.println("Резюме " + resume.getUuid() + " сохранено в хранилище.  " + "Общее количество резюме " + storageSize);
            }
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.print("Ошибка поиска: Резюме " + uuid + " не найдено. ");
            return null;
        }
        return storage[index];
    }


    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Ошибка удаления: Резюме " + uuid + " не найдено. ");
        } else {
            System.arraycopy(storage, index + 1, storage, index, storageSize - 1 - index);
            storage[storageSize - 1] = null;
            storageSize--;
            System.out.println("Выполнено: Резюме " + uuid + " удалено. ");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, storageSize);
    }

    public int size() {
        return storageSize;
    }

}
