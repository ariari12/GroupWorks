package kr.co.groupworks.sse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import kr.co.groupworks.employee.dto.SessionEmployeeDTO;
import kr.co.groupworks.sse.connection.SseConnectionPool;
import kr.co.groupworks.sse.connection.model.UserSseConnection;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class SseApiController {

    private final SseConnectionPool sseConnectionPool;
    private final ObjectMapper objectMapper;

    @GetMapping("/connect")
    public ResponseBodyEmitter connect(@Parameter(hidden = true)
                            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO) {
        log.info("{}",sessionEmployeeDTO);

        UserSseConnection userSseConnection = UserSseConnection.connect(
                sessionEmployeeDTO.getEmployeeId().toString(),
                objectMapper,
                sseConnectionPool
        );
        // session에 추가
        sseConnectionPool.addSession(userSseConnection.getUniqueKey(), userSseConnection);

        return userSseConnection.getSseEmitter();
    }

    @GetMapping("push-event")
    public void pushEvent(
            @Parameter(hidden = true)
            @SessionAttribute(name = "employee") SessionEmployeeDTO sessionEmployeeDTO)
    {
        UserSseConnection userSseConnection = sseConnectionPool.getSession(sessionEmployeeDTO.getEmployeeId().toString());
        Optional.ofNullable(userSseConnection)
                .ifPresent(it->{
                    it.sendMessage("Hello World!");
                });
    }
}
