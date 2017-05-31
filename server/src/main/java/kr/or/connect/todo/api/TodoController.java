package kr.or.connect.todo.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;
import org.springframework.http.HttpStatus;


@RestController
@RequestMapping("/api/todos")
public class TodoController {

	private final TodoService todoService;
	
	//for Filtering
	private static final int ALL=1;
	
	@Autowired
	public TodoController(TodoService todoService) {
		this.todoService = todoService;
	}
	
	@GetMapping
	Collection<Todo> readList() {
		return todoService.findAll(ALL);
	}

	@GetMapping("/{completed}")
	Collection<Todo> read(@PathVariable  Integer completed) {
		return todoService.findAll(completed);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Todo create(@RequestBody Todo todo) {
		return todoService.insert(todo);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody Todo todo) {
		todo.setId(id);
		todoService.update(todo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		todoService.delete(id);
	}
}
