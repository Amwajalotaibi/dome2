package com.example.demo.Service;

import com.example.demo.Model.MyUser;
import com.example.demo.Model.Todo;
import com.example.demo.Repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    public List<Todo> getTodos(Integer userId) {

        return todoRepository.findTodoByUserId(userId);//هذي نسوي ريتير للايدي حقي انا يرجع لستتي
    }

    public void addTodo(Integer userId, Todo todo) {
        todo.setUserId(userId);
        todoRepository.save(todo);
    }

    public void deleteTodo(Integer userId, Integer todoId){
            Todo todo=todoRepository.findTodoById(todoId);

             if(todo.getUserId()!=userId){
                throw new UsernameNotFoundException("Error,authentication");
            }

            todoRepository.save(todo);
        }



        public void updateTodo(Integer userId,Todo todo, Integer todoId){
        Todo oldTodo=todoRepository.findTodoById(todoId);
        if (oldTodo==null) {
            throw new UsernameNotFoundException("Todo not found");
        } if(oldTodo.getUserId()!=userId){
            throw new UsernameNotFoundException("Error, authentication");
        }
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);
    }

}
