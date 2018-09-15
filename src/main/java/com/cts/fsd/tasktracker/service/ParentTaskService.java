package com.cts.fsd.tasktracker.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fsd.tasktracker.entity.ParentTaskEntity;
import com.cts.fsd.tasktracker.exception.ResourceNotFoundException;
import com.cts.fsd.tasktracker.mapper.TaskTrackerMapper;
import com.cts.fsd.tasktracker.pojo.ParentTaskPOJO;
import com.cts.fsd.tasktracker.repo.ParentTaskRepository;

@Service
public class ParentTaskService {
	
	@Autowired
	protected TaskTrackerMapper mapper;
	
	@Autowired
	protected ParentTaskRepository parentTaskRepository;
	
	
	public List<ParentTaskPOJO> createParentTasks(List<ParentTaskPOJO> parentTaskPOJOList){
		List<ParentTaskEntity> parentTaskEntityList = new ArrayList<ParentTaskEntity>();
		List<ParentTaskPOJO> returnPojoList = new ArrayList<ParentTaskPOJO>();
		
		if (null != parentTaskPOJOList && !parentTaskPOJOList.isEmpty()) {
			for(ParentTaskPOJO parentTaskPOJO :  parentTaskPOJOList ) {
				ParentTaskEntity parentTaskEntity = mapper.mapParentTaskPojoToEntity(parentTaskPOJO);
				System.out.println("after mapping to entity parentTaskEntity = " + parentTaskEntity.toString());
				parentTaskEntityList.add(parentTaskEntity);
			}
		}
		System.out.println("Before Save parentTaskEntityList = " + parentTaskEntityList.toString());
		List<ParentTaskEntity> dbResponse = parentTaskRepository.saveAll(parentTaskEntityList);
		System.out.println("createParentTasks() dbResponse = " + dbResponse.toString());
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			for(ParentTaskEntity parentTaskEntity :  dbResponse ) {
				ParentTaskPOJO parentTaskPOJO = mapper.mapParentTaskEntityToPojo(parentTaskEntity);
				returnPojoList.add(parentTaskPOJO);
			}
		}
		
		return returnPojoList;
	}
	
	public List<ParentTaskPOJO> getAllParentTasks(){
		
		List<ParentTaskEntity> dbResponse = parentTaskRepository.findAll();
		System.out.println("getAllParentTasks() dbResponse = " + dbResponse);
		
		List<ParentTaskPOJO> returnPojoList = new ArrayList<ParentTaskPOJO>();
		
		if (null != dbResponse && !dbResponse.isEmpty()) {
			for(ParentTaskEntity parentTaskEntity :  dbResponse ) {
				ParentTaskPOJO parentTaskPOJO = mapper.mapParentTaskEntityToPojo(parentTaskEntity);
				returnPojoList.add(parentTaskPOJO);
			}
		}
		return returnPojoList;
	}
	
	
	public ParentTaskEntity getParentTaskById(int parentId){
		
		ParentTaskEntity parentTaskFromDB = null;
		
		try {
			parentTaskFromDB = parentTaskRepository.findById(Long.valueOf(parentId)).get();
			System.out.println("getParentTaskById successfully returned ParentTaskEntity from DB :: " + parentTaskFromDB.toString());
		} catch (NoSuchElementException e) {
			parentTaskFromDB = null;
			System.out.println("getParentTaskById NOT successfull...\nNoSuchElementException encountered... ResourceNotFoundException thrown " + e);
			throw new ResourceNotFoundException("ParentTaskEntity" , "parentId" , parentId);
		} catch (Exception e ) {
			parentTaskFromDB = null;
			System.out.println("Exception encountered..." + e);
		}
		return parentTaskFromDB;
	}
	
	
	
	public ParentTaskPOJO editParentTaskById(int parentId , ParentTaskPOJO parentTaskPOJO){
		String editResponse = "";
		ParentTaskEntity parentTaskFromDB = null ;
		ParentTaskPOJO returnPOJO = null;
		try {
			parentTaskFromDB =  getParentTaskById(parentId);
			System.out.println("Updating parentTaskFromDB = " + parentTaskFromDB.toString());
			parentTaskFromDB.setParentTask(parentTaskPOJO.getParentTask());
			
			parentTaskFromDB =  parentTaskRepository.save(parentTaskFromDB);
			editResponse = "ParentTask ID("+parentId+") updated, " + parentTaskFromDB.toString();
			
			returnPOJO = mapper.mapParentTaskEntityToPojo(parentTaskFromDB);
		} catch(ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			editResponse = "Things are not updated as record does not exist... ";
			parentTaskFromDB = null;
			returnPOJO = null;
		} catch(Exception e ) {
			System.out.println("Exception encountered..." + e);
			editResponse = "Things are not updated due to Exception... " + e.getMessage();
			parentTaskFromDB = null;
			returnPOJO = null;
		}
		System.out.println("After Parent Task Update :: " + editResponse);
		
		return returnPOJO;
	}

	public boolean removeParentTaskById(int parentId) {
		String deleteResponse = "";
		ParentTaskEntity parentTaskFromDB = null;
		boolean returnResponse = false;
		System.out.println("Before Delete ParentTask By Id("+parentId+")");
		try {
			parentTaskFromDB =  getParentTaskById(parentId);
			System.out.println("Deleting parentTaskFromDB = " + parentTaskFromDB.toString());
			parentTaskRepository.deleteParentTaskById(Long.valueOf(parentTaskFromDB.getParentId()));
			deleteResponse = "ParentTask ID("+parentId+") Deleted, Record No More exists,";
			returnResponse = true;
		} catch (ResourceNotFoundException e ) {
			System.out.println("ResourceNotFoundException encountered..." + e);
			deleteResponse = "Things are not deleted as record does not exist... ";
			parentTaskFromDB = null;
			returnResponse = false;
		} catch (Exception e ) {
			System.out.println("Exception encountered..." + e);
			deleteResponse = "Things are not deleted due to Exception... " + e.getMessage();
			parentTaskFromDB = null;
			returnResponse = false;
		}
		
		System.out.println("After Delete :: " + deleteResponse);
		return returnResponse;
	}
	
	
	
	
}
