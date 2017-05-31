package kr.or.connect.todo.persistence;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.TodoApplication;
import kr.or.connect.todo.domain.Todo;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoApplication.class)
@Transactional
public class TodoDaoTest {
	
	@Autowired
	private TodoDao dao;


 
	@Test
	public void shouldInsertAndSelect() {
		// given
		Todo todo = new Todo("textTodo1");
		

		// when
		Integer id = dao.insert(todo);

		// then
		Todo selected = dao.selectById(id);
		System.out.println(selected.toString());
		assertThat(selected.getTodo(), is("textTodo1"));
	}
	
	@Test
	public void shouldDelete() {
		//given
		Todo todo = new Todo("textTodo1");
		int testId = dao.insert(todo);
		
		//when
		int affected = dao.deleteById(testId);
		
		//then
		assertThat(affected,is(1));
	}
	
	@Test
	public void shouldSelectOrderBY() {
		
		//given
		Todo todo = new Todo("textTodo1");
		int id1 = dao.insert(todo);
		
		Todo todo1 = new Todo("textTodo2");
		int id2 = dao.insert(todo1);
		
		todo = dao.selectById(id1);
		todo1 = dao.selectById(id2);
		
		//when
		List<Todo> todoList = dao.selectAll();
		
		//then
		assertThat(todoList,is(notNullValue()));
		assertThat(todoList.get(0).getDate(),is(todo.getDate()));
		assertThat(todoList.get(1).getDate(),is(todo1.getDate()));
	}


}

