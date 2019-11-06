package com.basejava.webapp.model;



/**
 * Initial resume class
 */
public class Resume implements Comparable<Resume> {

    // Unique identifier
    private String uuid;

    public String getUuid() {
        return uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        return uuid.equals(resume.uuid);

    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return uuid;
    }

    @Override
    public int compareTo(Resume anotherResume) {
        int numThisUuid = Integer.parseInt(this.uuid.substring(4));
        int numAnotherUuid = Integer.parseInt(anotherResume.uuid.substring(4));
        if (numThisUuid < numAnotherUuid) {
            return -1;
        }
        else if (numThisUuid > numAnotherUuid) {
            return 1;
        }
        else {
            return 0;
        }
    }
}

