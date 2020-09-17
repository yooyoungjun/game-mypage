package game;

import game.config.kafka.KafkaProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class MypageViewHandler {


    @Autowired
    private MypageRepository mypageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenMissionAchieved_then_CREATE_1 (@Payload MissionAchieved missionAchieved) {
        try {
            if (missionAchieved.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setMissionId(missionAchieved.getId());
                mypage.setMissionStatus(missionAchieved.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    /*
    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocated_then_CREATE_2 (@Payload Allocated allocated) {
        try {
            if (allocated.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setRewardId(allocated.getId());
                mypage.setRewardStatus(allocated.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenIssued_then_CREATE_3 (@Payload Issued issued) {
        try {
            if (issued.isMe()) {
                // view 객체 생성
                Mypage mypage = new Mypage();
                // view 객체에 이벤트의 Value 를 set 함
                mypage.setRewardId(issued.getId());
                mypage.setRewardStatus(issued.getStatus());
                // view 레파지 토리에 save
                mypageRepository.save(mypage);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    */

    @StreamListener(KafkaProcessor.INPUT)
    public void whenAllocated_then_UPDATE_1(@Payload Allocated allocated) {
        try {
            if (allocated.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByMissionId(allocated.getMissionId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setRewardId(allocated.getId());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenIssued_then_UPDATE_2(@Payload Issued issued) {
        try {
            if (issued.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByRewardId(issued.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setRewardStatus(issued.getStatus());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenExchanged_then_UPDATE_3(@Payload Exchanged exchanged) {
        try {
            if (exchanged.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByRewardId(exchanged.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setRewardStatus(exchanged.getStatus());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void whenSentMessage_then_UPDATE_4(@Payload SentMessage sentMessage) {
        try {
            if (sentMessage.isMe()) {
                // view 객체 조회
                List<Mypage> mypageList = mypageRepository.findByMessageId(sentMessage.getId());
                for(Mypage mypage : mypageList){
                    // view 객체에 이벤트의 eventDirectValue 를 set 함
                    mypage.setMessageStatus(sentMessage.getStatus());
                    // view 레파지 토리에 save
                    mypageRepository.save(mypage);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}