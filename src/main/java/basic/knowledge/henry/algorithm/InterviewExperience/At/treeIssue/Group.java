package basic.knowledge.henry.algorithm.InterviewExperience.At.treeIssue;

import java.util.HashSet;
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
}
