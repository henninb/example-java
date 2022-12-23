package example.services;

import example.domain.Todo;
import example.repositories.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TodoService {

    @Autowired
    protected TodoRepository todoRepository;

    public List<Todo> findAllTodos()  {
        return todoRepository.findAll();
    }

    public void save( Todo todo) {
        todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

}
