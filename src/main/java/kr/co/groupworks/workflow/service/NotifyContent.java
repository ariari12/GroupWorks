package kr.co.groupworks.workflow.service;

import kr.co.groupworks.employee.entity.Employee;
import kr.co.groupworks.workflow.entity.WorkFlowEntity;

@FunctionalInterface
public interface NotifyContent {
    String getContent(WorkFlowEntity w, Employee e);
}