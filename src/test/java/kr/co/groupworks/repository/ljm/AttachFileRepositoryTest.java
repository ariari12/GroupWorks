package kr.co.groupworks.repository.ljm;

import kr.co.groupworks.dto.ljm.vo.AttachmentFileVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;

@Slf4j
@Transactional
@SpringBootTest
class AttachFileRepositoryTest {
    @Autowired
    private AttachmentFileRepository attachmentFileRepository;

    @Test @DisplayName("fileLoad Test")
    public void fileLoadTest() {
        attachmentFileRepository.findAll().forEach(e -> {
            File file = new File(e.getSavePath());
            if(file.exists()) {
                log.info("file exists: {}", new AttachmentFileVO(e));
            }
        });
    }

}