package kr.co.groupworks.calendar.entity;

import jakarta.persistence.*;
import kr.co.groupworks.common.BaseEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "calendar_attachment")
@Getter
@ToString(exclude = {"calendar"})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarAttachment extends BaseEntity {
    @Id
    @Column(name = "calendar_attachment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calendarAttachmentId;
    // 클라이언트가 올린 파일이름
    @Column(name = "file_name")
    private String fileName;
    // 실제 저장 파일 경로
    @Column(name = "file_path")
    private String filePath;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
