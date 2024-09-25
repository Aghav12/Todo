package com.example.todo.controller;

import com.example.todo.service.TodoService;

import com.example.todo.utils.TodoDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {

	private TodoService todoService;

	@PostMapping
	public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
		TodoDto savetodo = todoService.addTodo(todoDto);

		return new ResponseEntity<>(savetodo, HttpStatus.CREATED);
	}

	@GetMapping("{id}")
	public ResponseEntity<TodoDto> getTodo(@PathVariable("id") Long todoId) {
		TodoDto todoDto = todoService.getTodo(todoId);
		return new ResponseEntity<>(todoDto, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<TodoDto>> getAllTodo() {
		List<TodoDto> todo = todoService.getAllTodo();
		return ResponseEntity.ok(todo);
	}

	@PutMapping("{id}")
	public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long todoId) {
		TodoDto updateTodo = todoService.updateTodo(todoDto, todoId);
		return ResponseEntity.ok(updateTodo);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteTodo(@PathVariable("id") Long todoId) {
		todoService.deleteTodo(todoId);
		return ResponseEntity.ok("Todo deleted successfully !!");
	}

	@PatchMapping("{id}/complete")
	public ResponseEntity<TodoDto> completedTodo(@PathVariable("id") Long todoId) {
		TodoDto completedTodo = todoService.completedTodo(todoId);

		return ResponseEntity.ok(completedTodo);
	}

	@PatchMapping("{id}/incomplete")
	public ResponseEntity<TodoDto> inCompletedTodo(@PathVariable("id") Long todoId) {
		TodoDto inCompleteTodo = todoService.inCompleteTodo(todoId);

		return ResponseEntity.ok(inCompleteTodo);
	}
}
