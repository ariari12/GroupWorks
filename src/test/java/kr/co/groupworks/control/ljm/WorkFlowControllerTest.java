package kr.co.groupworks.control.ljm;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@Slf4j
@WebMvcTest(controllers = WorkFlowController.class)
class WorkFlowControllerTest {
    @Autowired
    MockMvc mvc;

    @Test @DisplayName("Detail Info Test")
    public void detailInfoTest() {
        try {
            mvc.perform(get("/work-flow/detail/3"))
                    .andDo(print());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}