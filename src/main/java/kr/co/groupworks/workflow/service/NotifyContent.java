package kr.co.groupworks.workflow.service;

import kr.co.groupworks.employee.entity.Employee;

@FunctionalInterface
public interface NotifyContent {
    String getContent(Employee e);
}