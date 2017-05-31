package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {

	private TodoDao dao;
	private final int ALL=1,COMPLETED=2,UNCOMPLETED=3;
 

	public TodoService(TodoDao dao) {
		this.dao = dao;
	}
	
	public Todo insert(Todo todo) {
		 int id = dao.insert(todo);
		 return dao.selectById(id);
	}
	
	public Collection<Todo> findAll(int filter) {
		
		if(filter==ALL)
			return dao.selectAll();
		else if(filter==COMPLETED)
			return dao.selectByCompleted(true);
		else if(filter==UNCOMPLETED)
			return dao.selectByCompleted(false);
		else return null;
	}
	public boolean update(Todo todo) {
		int count = dao.update(todo);
		return count == 1;
	}
	
	public boolean delete(int id) {
		return dao.deleteById(id) == 1;
	}
}
