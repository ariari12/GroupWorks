package kr.co.groupworks.videoConference.service;

public interface VideoConferenceService {
    public void addRoom(String roomId);
    public boolean roomExists(String roomId);
}
