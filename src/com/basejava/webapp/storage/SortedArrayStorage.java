package com.basejava.webapp.storage;

import com.basejava.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        if (uuid.matches("uuid[1-9]\\d*")){
            return Arrays.binarySearch(storage, 0, size, searchKey);
        }
        else {
            return -10001;
        }
    }

    @Override
    public void save(Resume resume) {
        int saveIndex = getIndex(resume.getUuid());
        if (saveIndex == -10001){
            System.out.println("Ошибка: Некорректный формат идентификатора " + resume.getUuid() + " != uuid1...uuid10000");
        }
        else if (size >= STORAGE_LIMIT) {
            System.out.println("Ошибка: Память хранилища заполнена. ");
        }
        else if (saveIndex > 0) {
                System.out.println("Ошибка: Резюме " + resume.getUuid() + " уже существует. ");
            }
        else {
                if (size>(-saveIndex)-1){
                    for ( int i = size; i > (-saveIndex)-1; i--){
                        storage[i]=storage[i-1];
                    }
                    storage[(-saveIndex)-1] = resume;
                }
                else {
                    storage[size] = resume;
                }
                System.out.println("Резюме " + resume.getUuid() + " добавлено в хранилище.  ");
                size++;
            }
        }
}
