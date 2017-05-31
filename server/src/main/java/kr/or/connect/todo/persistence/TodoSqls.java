package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String DELETE_BY_ID =
			"DELETE FROM todo WHERE id= :id";
	
	static final String COUNT_UNCOMPLETED_TODO = 
			"SELECT COUNT(*) FROM todo WHERE completed= false";
	
	static final String SELECT_BY_ID = 
			"SELECT id, todo, completed, date FROM todo where id = :id";
	
	static final String UPDATE =
			"UPDATE todo SET\n"
			+ "completed = :completed\n"
			+ "WHERE id = :id";
	
	static final String SELECT_ALL = 
			"SELECT id, todo, completed, date FROM todo ORDER BY date DESC";
	
	static final String SELECT_BY_COMPLETED = 
			"SELECT id, todo, completed, date FROM todo WHERE completed = :completed ORDER BY date DESC";

}
