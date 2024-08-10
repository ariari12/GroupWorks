package kr.co.groupworks.sse.connection.ifs;

import kr.co.groupworks.sse.connection.model.UserSseConnection;

public interface ConnectionPoolIfs<T, R> {
    void addSession(T uniqueKey, R session);
    R getSession(T uniqueKey);

    void onCompletionCallback(R session);
}
