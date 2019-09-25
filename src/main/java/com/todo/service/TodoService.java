package com.todo.service;

import java.util.List;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.todo.domain.Todo;
import com.todo.repository.TodoRepository;


@Transactional
@Service
public class TodoService {
//protected static Logger logger = Logger.getLogger("service");
	
	@Autowired
	private TodoRepository  todoRepository;
	
	public Boolean create(Todo c) 
	{
		Todo saved =  todoRepository.save(c);
		if (saved == null) 
			return false;
		
		return true;
	}

	public Boolean update(Todo c) 
	{
		/*Category existingCategory =  todoRepository.findOne(c.getId());
		if (existingCategory == null) 
			return false;
		
		existingCategory.setName(c.getName());
		
		Category saved =  todoRepository.save(existingCategory);
		if (saved == null) 
			return false;*/
		
		return true;
	}

	public Boolean delete(Integer id) 
	{
		Todo existingEntity =  todoRepository.getOne(id);
		if (existingEntity == null) 
			return false;
		
		 todoRepository.delete(existingEntity);
		
//		Category deletedCategory =  todoRepository.findOne(id);
//		if (deletedCategory != null) 
//			return false;

		return true;
	}

	public List<Todo> findAll() 
	{
		List<Todo> list =  todoRepository.findAll();
		return list;
	}

	public Todo getOne(Integer id) 
	{
		Todo entity =  todoRepository.getOne(id);
		return entity;
	}

}

