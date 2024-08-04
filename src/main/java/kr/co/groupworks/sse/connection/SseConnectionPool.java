package kr.co.groupworks.sse.connection;

import kr.co.groupworks.sse.connection.ifs.ConnectionPoolIfs;
import kr.co.groupworks.sse.connection.model.UserSseConnection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SseConnectionPool implements ConnectionPoolIfs<String, UserSseConnection> {
    private static final Map<String, UserSseConnection> connectionPool = new ConcurrentHashMap<>();


    @Override
    public void addSession(String uniqueKey, UserSseConnection session) {
        connectionPool.put(uniqueKey, session);
    }

    @Override
    public UserSseConnection getSession(String uniqueKey) {
        return connectionPool.get(uniqueKey);
    }

    @Override
    public void onCompletionCallback(UserSseConnection session) {
        log.info("call back connection pool completion : {}",session);
        connectionPool.remove(session.getUniqueKey());
    }
}
