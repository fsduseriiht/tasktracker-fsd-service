package com.cts.fsd.tasktracker.pojo;

import org.springframework.stereotype.Component;

/**
 * @author Amitabha Das [420652]
 *
 */
@Component
public class ParentTaskPOJO {
	
	private int parentId;

	private String parentTask;

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentTask() {
		return parentTask;
	}

	public void setParentTask(String parentTask) {
		this.parentTask = parentTask;
	}

	@Override
	public String toString() {
		return "ParentTaskPOJO [parentId=" + parentId + ", parentTask=" + parentTask + "]";
	}
	
	
}
