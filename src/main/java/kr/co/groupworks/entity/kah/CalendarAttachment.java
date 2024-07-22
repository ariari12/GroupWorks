package kr.co.groupworks.entity.kah;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@ToString(exclude = {"calendar"})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class CalendarAttachment {
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

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

}
