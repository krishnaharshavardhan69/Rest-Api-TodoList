package tuto.demo;

import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import tuto.demo.model.Todo;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository TodoRepository;

    //Create
  public Todo createTodo(Todo todo){
      return TodoRepository.save(todo);
  }

    //Display all
  public List<Todo> getTodos() {
      return TodoRepository.findAll();
  }



     //Search by ID
    public Todo getTodoById(Long id) {
        return TodoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found with id " + id));
    }

    //Update
    public Todo updateTodo(Todo todo) {
        return TodoRepository.save(todo);
    }
    //Delete
    public void deleteTodo(Long id) {
        TodoRepository.delete(getTodoById(id));
    }

    public Page<Todo> getAllTodoPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return TodoRepository.findAll(pageable);
    }

}
