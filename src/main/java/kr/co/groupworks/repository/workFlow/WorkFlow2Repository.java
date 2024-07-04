package kr.co.groupworks.repository.workFlow;


import kr.co.groupworks.entity.workFlow.WorkFlow2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlow2Repository extends JpaRepository<WorkFlow2, Integer> {
}
