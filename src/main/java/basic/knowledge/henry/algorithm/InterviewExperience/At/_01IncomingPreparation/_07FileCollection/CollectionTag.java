package basic.knowledge.henry.algorithm.InterviewExperience.At._01IncomingPreparation._07FileCollection;

import java.util.HashSet;
import java.util.Objects;

public class  CollectionTag {

    String name;

    int totalSize;

    public CollectionTag(String name,int totalSize) {
        this.name = name;
        this.totalSize = totalSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CollectionTag that = (CollectionTag) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "CollectionTag{" +
                "name='" + name + '\'' +
                ", totalSize=" + totalSize +
                '}';
    }
}
