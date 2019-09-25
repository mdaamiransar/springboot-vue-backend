package com.todo.controller;

import java.util.List;

import javax.validation.Valid;

//import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.todo.domain.Todo;
import com.todo.repository.TodoRepository;


@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class TodoController 
{   	
//	protected static Logger logger = Logger.getLogger(UserController.class);
	
	@Autowired
	private TodoRepository todoRepository;
	
	@GetMapping("/todo")
    public List<Todo> findAll() {

        return todoRepository.findAll();
    }
	
	@GetMapping("/todo/{id}")
    public Todo getOne(@PathVariable(value = "id") Integer id) {

        return todoRepository.getOne(id);
    }
	
//	@GetMapping("/todo/{id}")
//    public ResponseEntity<Todo> findById(@PathVariable(value = "id") Integer employeeId) {
//		Todo entity = todoRepository.getOne(employeeId);
//        if(entity == null) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok().body(entity);
//    }
	
	@PostMapping("/todo")
    public Todo create(@Valid @RequestBody Todo entity) {
//		logger.info("--- Todo ---- "+ entity);
		return todoRepository.save(entity);
    }

	// Update a Note
	@PutMapping("/employee/{id}")
	public Todo updateNote(@PathVariable(value = "id") Integer noteId,
	                                        @Valid @RequestBody Todo noteDetails) {

		Todo note = ((Todo) todoRepository.getOne(noteId));
	           // .orElseThrow(() -> new ResourceNotFoundException("Todo", "id", noteId));

	    note.setTitle(noteDetails.getTitle());
	    note.setCompleted(noteDetails.getCompleted());

	    Todo updatedNote = todoRepository.save(note);
	    return updatedNote;
	}
	
	@PutMapping("/todo/{id}")
    public ResponseEntity<Todo> update(@PathVariable(value = "id") Integer employeeId,
                                           @Valid @RequestBody Todo entityDetails) {
		Todo entity = todoRepository.getOne(employeeId);
        if(entity == null) {
            return ResponseEntity.notFound().build();
        }
        entity.setTitle(entityDetails.getTitle());
        entity.setCompleted(entityDetails.getCompleted());
        
        Todo updatedEntity = todoRepository.save(entity);
        return ResponseEntity.ok(updatedEntity);
    }
	
	@DeleteMapping("/todo/{id}")
    public ResponseEntity<Todo> delete(@PathVariable Integer id) {
		Todo entity = todoRepository.getOne(id);
        if(entity == null) {
            return ResponseEntity.notFound().build();
        }

        todoRepository.delete(entity);
        return ResponseEntity.ok().build();
    }
	
	@RequestMapping(value = "/todo/{id}/activation", method=RequestMethod.GET)
	@ResponseBody
	public String manageActivation(@PathVariable Integer id) {
		
		Todo entity = todoRepository.getOne(id);
		Boolean isActive = entity.getCompleted();
		entity.setCompleted(!isActive);
		todoRepository.save(entity);		
		return (isActive)? "Category Dectivated Successfully!": "Category Activated Successfully";
	}

}
