package kr.co.groupworks.notification.sse.sse.connection.model;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.groupworks.notification.sse.sse.connection.ifs.ConnectionPoolIfs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Getter
@ToString
@EqualsAndHashCode
public class UserSseConnection {

    private final String uniqueKey;
    private final ObjectMapper objectMapper;
    private final SseEmitter sseEmitter;
    private final ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs;

    private UserSseConnection(String uniqueKey,
                              ObjectMapper objectMapper,
                              ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs) {
        // key 초기화
        this.uniqueKey = uniqueKey;
        // objectMapper 초기화
        this.objectMapper = objectMapper;
        // sse 초기화
        this.sseEmitter = new SseEmitter(1000L * 60);
        // 콜백 초기화
        this.connectionPoolIfs = connectionPoolIfs;

        // on completion
        this.sseEmitter.onCompletion(() -> {
            // connection pool remove
            this.connectionPoolIfs.onCompletionCallback(this);
        });
        // on timeout
        this.sseEmitter.onTimeout(() -> {
            this.sseEmitter.complete();
        });


        // onopen 메시지
        this.sendMessage("onopen","connect");

    }

    public static UserSseConnection connect(String uniqueKey, ObjectMapper objectMapper,
                                            ConnectionPoolIfs<String, UserSseConnection> connectionPoolIfs){
        return new UserSseConnection(uniqueKey, objectMapper, connectionPoolIfs);
    };

    public void sendMessage(String eventName, Object data) {
        try {
            String json = objectMapper.writeValueAsString(data);
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .name(eventName)
                    .data(json);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }

    public void sendMessage(Object data){

        try {
            String json = objectMapper.writeValueAsString(data);
            SseEmitter.SseEventBuilder event = SseEmitter.event()
                    .data(json);
            this.sseEmitter.send(event);
        } catch (IOException e) {
            this.sseEmitter.completeWithError(e);
        }
    }
}
