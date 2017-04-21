package se.lars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class TaskController {
    private TaskRepository repository;

    @Autowired
    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/tasks")
    List<Task> getTasks() {
        return this.repository.findAll();
    }

    @GetMapping("/tasks/{id}")
    Optional<Task> getTask(@PathVariable long id) {
        return this.repository.findOne(id);
    }

    @GetMapping("/create")
    Optional<Task> create() {
        Task task = ImmutableTask.of(System.currentTimeMillis(), Optional.empty(), false);
        return repository.save(task);
    }

    @PostMapping("/update")
    Optional<Task> update(@RequestBody Task task) {
        return repository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    void delete(@PathVariable long id) {
        repository.delete(id);
    }
}

