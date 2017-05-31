package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import kr.or.connect.todo.domain.Todo;

import static kr.or.connect.todo.persistence.TodoSqls.*;

@Repository
public class TodoDao {
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	
	RowMapper<Todo> rowMapper = BeanPropertyRowMapper.newInstance(Todo.class);
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		//Not necessary "completed", "date" When insert
		this.insertAction = new SimpleJdbcInsert(dataSource)
				.withTableName("todo")
				.usingColumns("TODO")
				.usingGeneratedKeyColumns("id");
	}
	
	//	SELECT_ALL
	public List<Todo> selectAll() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
	
	//	SELECT_BY_ID
	public Todo selectById(Integer id) {
		
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(SELECT_BY_ID, params, rowMapper);	
	}
	
	
	//	INSERT
	public Integer insert(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}
	
	//	UPDATE
 	public int update(Todo todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(UPDATE, params);
	}
 	
 	//	DELETE
	public int deleteById(Integer id) {
			Map<String, ?> params = Collections.singletonMap("id", id);
			return jdbc.update(DELETE_BY_ID, params);
	}
}
