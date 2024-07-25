package kr.co.groupworks.entity.yhj.board;

import jakarta.persistence.*;
import kr.co.groupworks.entity.cis.Department;
import kr.co.groupworks.entity.cis.Employee;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long boardId;

    @Column(nullable = false)
    private String title;

    @Column
    private String subject;

    @Column(nullable = false)
    private String content;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate = LocalDateTime.now();

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "ip_address")
    private String ipAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardStatus status = BoardStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "board_type", nullable = false)
    private BoardType boardType;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
}

// 게시판 상태 ( 활성 / 비활성 )
enum BoardStatus {
    ACTIVE,
    INACTIVE
}

// 게시판 타입 ( 공지 / 부서 )
enum BoardType {
    NOTICE,
    DEPARTMENT
}