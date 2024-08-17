package kr.co.groupworks.notification.sse.sse.connection.ifs;

public interface ConnectionPoolIfs<T, R> {
    void addSession(T uniqueKey, R session);
    R getSession(T uniqueKey);

    void onCompletionCallback(R session);
}
