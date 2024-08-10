package kr.co.groupworks.comment.entity;

import jakarta.persistence.*;
import kr.co.groupworks.board.entity.Board;
import kr.co.groupworks.employee.entity.Employee;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Employee employee;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    // @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> childComments = new ArrayList<>();

    public void updateContent(String content) {
        this.content = content;
    }
}