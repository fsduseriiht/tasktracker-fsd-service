package com.cts.fsd.tasktracker.mapper;

import org.springframework.stereotype.Component;

import com.cts.fsd.tasktracker.entity.ParentTaskEntity;
import com.cts.fsd.tasktracker.entity.TaskEntity;
import com.cts.fsd.tasktracker.pojo.ParentTaskPOJO;
import com.cts.fsd.tasktracker.pojo.TaskPOJO;

/**
 * @author Amitabha Das [420652]
 *
 */
@Component
public class TaskTrackerMapper {
	
	public ParentTaskEntity mapParentTaskPojoToEntity (ParentTaskPOJO parentTaskPOJO){
		ParentTaskEntity parentTaskEntity = new ParentTaskEntity();
		
		if( parentTaskPOJO != null ) {
			parentTaskEntity.setParentId(parentTaskPOJO.getParentId());
			parentTaskEntity.setParentTask(parentTaskPOJO.getParentTask());
		}
		
		return parentTaskEntity;
	}
	
	
	public ParentTaskPOJO mapParentTaskEntityToPojo (ParentTaskEntity parentTaskEntity){
		ParentTaskPOJO parentTaskPOJO = new ParentTaskPOJO();
		
		if( parentTaskEntity != null ) {
			parentTaskPOJO.setParentId(new Long(parentTaskEntity.getParentId()).intValue());
			parentTaskPOJO.setParentTask(parentTaskEntity.getParentTask());
		}
		
		return parentTaskPOJO;
	}
	
	public TaskEntity mapTaskPojoToEntity (TaskPOJO taskPOJO){
		TaskEntity taskEntity = new TaskEntity();
//		ParentTaskEntity parentTaskEntity = new ParentTaskEntity();
		
		if( taskPOJO != null ) {
			taskEntity.setTaskId(taskPOJO.getTaskId());
			
//			parentTaskEntity.setParentId(taskPOJO.getParentId());
//			parentTaskEntity.setParentTask(taskPOJO.getParentTask());
//			taskEntity.setParentTaskEntity(parentTaskEntity);
			
			taskEntity.setTask(taskPOJO.getTask());
			taskEntity.setStartDate(new java.sql.Date(taskPOJO.getStartDate().getTime()));
			taskEntity.setEndDate(new java.sql.Date(taskPOJO.getEndDate().getTime()));
			taskEntity.setPriority(taskPOJO.getPriority());
			
		}
		
		return taskEntity;
	}
	
	public TaskPOJO mapTaskEntityToPojo (TaskEntity taskEntity){
		TaskPOJO taskPOJO = new TaskPOJO();
		
		if( taskEntity != null ) {
			taskPOJO.setTaskId(new Long(taskEntity.getTaskId()).intValue());
			taskPOJO.setTask(taskEntity.getTask());
			
			taskPOJO.setParentId((taskEntity.getParentTaskEntity() != null ? new Long(taskEntity.getParentTaskEntity().getParentId()).intValue() : 0));
			taskPOJO.setParentTask((taskEntity.getParentTaskEntity() != null ? taskEntity.getParentTaskEntity().getParentTask() : "NULL"));
			
			taskPOJO.setStartDate(new java.util.Date(taskEntity.getStartDate().getTime()));
			taskPOJO.setEndDate(new java.util.Date(taskEntity.getEndDate().getTime()));
			taskPOJO.setPriority(taskEntity.getPriority());
		}
		
		return taskPOJO;
	}
	
}
