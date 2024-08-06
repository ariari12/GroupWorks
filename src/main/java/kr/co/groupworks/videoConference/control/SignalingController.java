package kr.co.groupworks.videoConference.control;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Hidden
@Slf4j
@RestController
@RequestMapping("/videoConference")
public class SignalingController {

    @RequestMapping("")
    public ModelAndView videoConference() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("videoConference/videoConferenceMenu.html");
        return modelAndView;
    }
    //offer 정보를 주고 받기 위한 websocket
    //camKey : 각 요청하는 캠의 key , roomId : 룸 아이디
    @MessageMapping("/peer/offer/{camKey}/{roomId}")
    @SendTo("/topic/peer/offer/{camKey}/{roomId}")
    public String PeerHandleOffer(@Payload String offer, @DestinationVariable(value = "roomId") String roomId,
                                  @DestinationVariable(value = "camKey") String camKey) {
        log.info("[OFFER] {} : {}", camKey, offer);
        return offer;
    }

    //iceCandidate 정보를 주고 받기 위한 webSocket
    //camKey : 각 요청하는 캠의 key , roomId : 룸 아이디
    @MessageMapping("/peer/iceCandidate/{camKey}/{roomId}")
    @SendTo("/topic/peer/iceCandidate/{camKey}/{roomId}")
    public String PeerHandleIceCandidate(@Payload String candidate, @DestinationVariable(value = "roomId") String roomId,
                                         @DestinationVariable(value = "camKey") String camKey) {
        log.info("[ICECANDIDATE] {} : {}", camKey, candidate);
        return candidate;
    }

    //

    @MessageMapping("/peer/answer/{camKey}/{roomId}")
    @SendTo("/topic/peer/answer/{camKey}/{roomId}")
    public String PeerHandleAnswer(@Payload String answer, @DestinationVariable(value = "roomId") String roomId,
                                   @DestinationVariable(value = "camKey") String camKey) {
        log.info("[ANSWER] {} : {}", camKey, answer);
        return answer;
    }

    //camKey 를 받기위해 신호를 보내는 webSocket
    @MessageMapping("/call/key")
    @SendTo("/topic/call/key")
    public String callKey(@Payload String message) {
        log.info("[Key] : {}", message);
        return message;
    }

    //자신의 camKey 를 모든 연결된 세션에 보내는 webSocket
    @MessageMapping("/send/key")
    @SendTo("/topic/send/key")
    public String sendKey(@Payload String message) {
        return message;
    }

}