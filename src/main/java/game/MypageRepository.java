package game;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MypageRepository extends CrudRepository<Mypage, Long> {

    List<Mypage> findByMissionId(Long missionId);
    List<Mypage> findByRewardId(Long rewardId);

}