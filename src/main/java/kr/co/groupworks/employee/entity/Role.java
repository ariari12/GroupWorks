package kr.co.groupworks.employee.entity;

import lombok.Getter;

@Getter
public enum Role {
    ASSOCIATE("팀원", 1),
    JUNIOR("파트장",2),
    SENIOR("팀장",3),
    MANAGER("본부장",4);

    private final String name;
    private final int grade;

    Role(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }
}
