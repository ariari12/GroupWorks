package kr.co.groupworks;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing// spring data jpa Auditing 설정 필수
//@ComponentScan(basePackages = {"kr.co.groupworks.util.mapper", "kr.co.groupworks"})
public class GroupWorksApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupWorksApplication.class, args);
    }


    @Bean
    JPAQueryFactory jpaQueryFactory(EntityManager em){
        return new JPAQueryFactory(em);
    }

}
