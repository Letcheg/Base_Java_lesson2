package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    @Override
    protected int getIndex(String uuid) {
        if (uuid.matches("uuid[1-9]\\d{0,5}") == false || Integer.parseInt(uuid.substring(4)) > STORAGE_LIMIT) {
            return -10001;
        }
        for (int i = 0; i < storageSize; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -10002;
    }

    @Override
    protected void addResumeInStorage(Resume resume) {
        storage[storageSize] = resume;
    }


}
