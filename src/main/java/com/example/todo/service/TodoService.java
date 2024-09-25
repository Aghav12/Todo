package com.example.todo.service;

import com.example.todo.utils.TodoDto;

import java.util.List;

public interface TodoService {

	TodoDto addTodo(TodoDto todoDto);

	TodoDto getTodo(Long Id);

	List<TodoDto> getAllTodo();

	TodoDto updateTodo(TodoDto todoDto, long Id);

	void deleteTodo(Long Id);

	TodoDto completedTodo(Long Id);

	TodoDto inCompleteTodo(Long Id);
}
