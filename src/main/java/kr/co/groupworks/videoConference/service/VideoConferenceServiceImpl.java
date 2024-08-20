package kr.co.groupworks.videoConference.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
@Slf4j
public class VideoConferenceServiceImpl implements VideoConferenceService {
    private final Set<String> rooms = ConcurrentHashMap.newKeySet();
    @Override
    public void addRoom(String roomId) {
        rooms.add(roomId);
    }

    @Override
    public boolean roomExists(String roomId) {
        log.info("roomId 목록 : " + rooms);
        return rooms.contains(roomId);
    }
}
