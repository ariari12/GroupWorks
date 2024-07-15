package kr.co.groupworks.entity.cis;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QEmployee is a Querydsl query type for Employee
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEmployee extends EntityPathBase<Employee> {

    private static final long serialVersionUID = -355293206L;

    public static final QEmployee employee = new QEmployee("employee");

    public final StringPath address = createString("address");

    public final NumberPath<Integer> departmentId = createNumber("departmentId", Integer.class);

    public final StringPath departmentName = createString("departmentName");

    public final StringPath email = createString("email");

    public final NumberPath<Long> employeeId = createNumber("employeeId", Long.class);

    public final StringPath employeeName = createString("employeeName");

    public final StringPath employeePW = createString("employeePW");

    public final StringPath gender = createString("gender");

    public final DateTimePath<java.time.LocalDateTime> joinDate = createDateTime("joinDate", java.time.LocalDateTime.class);

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> rankId = createNumber("rankId", Integer.class);

    public final StringPath rankName = createString("rankName");

    public final NumberPath<Integer> salary = createNumber("salary", Integer.class);

    public final NumberPath<Integer> supervisorId = createNumber("supervisorId", Integer.class);

    public QEmployee(String variable) {
        super(Employee.class, forVariable(variable));
    }

    public QEmployee(Path<? extends Employee> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEmployee(PathMetadata metadata) {
        super(Employee.class, metadata);
    }

}

