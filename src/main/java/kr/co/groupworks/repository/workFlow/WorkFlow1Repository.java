package kr.co.groupworks.repository.workFlow;


import kr.co.groupworks.entity.workFlow.WorkFlow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkFlow1Repository extends JpaRepository<WorkFlow, Integer> {
}
