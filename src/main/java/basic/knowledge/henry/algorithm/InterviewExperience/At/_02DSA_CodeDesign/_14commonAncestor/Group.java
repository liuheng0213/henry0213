package basic.knowledge.henry.algorithm.InterviewExperience.At._02DSA_CodeDesign._14commonAncestor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Group {
    Set<Group> subGroups = new HashSet();
    Set<Employee> employees = new HashSet<>();

    String name;

    public Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
