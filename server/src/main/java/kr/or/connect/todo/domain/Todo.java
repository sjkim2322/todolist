package kr.or.connect.todo.domain;

import java.util.Date;

public class Todo {

	private Integer id;
	private String todo;
	private boolean completed;
	private Date date;
	
	public Todo() {
	}
	
	public Todo(String todo) {
		this.todo=todo;
	}
	
	public Todo(Integer id,String todo, boolean completed,Date date) {
		this.id=id;
		this.todo=todo;
		this.completed=completed;
		this.date=date;
	}
	
	public Integer getId() {
		return id;
	}


	public String getTodo() {
		return todo;
	}


	public boolean isCompleted() {
		return completed;
	}


	public Date getDate() {
		return date;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setTodo(String todo) {
		this.todo = todo;
	}


	public void setCompleted(boolean completed) {
		this.completed = completed;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", Date=" + date + "]";
	}
	
	
}
