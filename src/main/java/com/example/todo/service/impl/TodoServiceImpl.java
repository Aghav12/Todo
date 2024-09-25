package com.example.todo.service.impl;

import lombok.AllArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.example.todo.entities.Todo;
import com.example.todo.repository.TodoRepo;
import com.example.todo.service.TodoService;
import com.example.todo.utils.TodoDto;

@Service
@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

	private TodoRepo tr;
	private ModelMapper modelMapper;

	@Override
	public TodoDto addTodo(TodoDto todoDto) {

		Todo todo = modelMapper.map(todoDto, Todo.class);

		Todo save = tr.save(todo);

		TodoDto todoDoto1 = modelMapper.map(save, TodoDto.class);

		return todoDoto1;
	}

	@Override
	public TodoDto getTodo(Long Id) {
		Todo todo = tr.findById(Id).orElseThrow(() -> new RuntimeException("ToDo not found with given Id:" + Id));
		return modelMapper.map(todo, TodoDto.class);
	}

	@Override
	public List<TodoDto> getAllTodo() {
		List<Todo> todos = tr.findAll();
		return todos.stream().map((todo) -> modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
	}

	@Override
	public TodoDto updateTodo(TodoDto todoDto, long Id) {
		Todo todo = tr.findById(Id).orElseThrow(() -> new RuntimeException("Todo not found with given Id"));

		todo.setTitle(todoDto.getTitle());
		todo.setDiscription(todoDto.getDiscription());
		todo.setCompleated(todoDto.isCompleated());
		Todo updatedTodo = tr.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public void deleteTodo(Long Id) {
		Todo todo = tr.findById(Id).orElseThrow(() -> new RuntimeException("Todo is not found with given ID"));
		tr.delete(todo);
	}

	@Override
	public TodoDto completedTodo(Long Id) {
		Todo todo = tr.findById(Id).orElseThrow(() -> new RuntimeException("Todo not found with given Id"));
		todo.setCompleated(Boolean.TRUE);

		Todo updatedTodo = tr.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

	@Override
	public TodoDto inCompleteTodo(Long Id) {
		Todo todo = tr.findById(Id).orElseThrow(() -> new RuntimeException("Todo not found with given Id "));
		todo.setCompleated(Boolean.FALSE);

		Todo updatedTodo = tr.save(todo);
		return modelMapper.map(updatedTodo, TodoDto.class);
	}

}
