package kr.co.groupworks.repository.cis;

import kr.co.groupworks.entity.cis.Employee;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EmployeeRepository extends ElasticsearchRepository<Employee, Integer> {
}
