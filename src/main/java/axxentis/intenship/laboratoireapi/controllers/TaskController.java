package axxentis.intenship.laboratoireapi.controllers;

import axxentis.intenship.laboratoireapi.dto.request.TaskDto;
import axxentis.intenship.laboratoireapi.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping(value = "/api/task")
public class TaskController {
    private final TaskService taskService;

    @PostMapping("/add")
    public ResponseEntity<String> addTask(@RequestBody TaskDto taskDto) {
        return taskService.addNewTask(taskDto);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping(value = "/{scheduleTitle}")
    public ResponseEntity<List<TaskDto>> getAllTasksBySchedule(@PathVariable(value = "scheduleTitle") String scheduleTitle) {
        return taskService.getAllTasksBySchedule(scheduleTitle);
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateTask(@RequestBody TaskDto taskDto) {
        return taskService.updateTask(taskDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteTask(@RequestParam(name = "task_id") Long taskId) {
        return taskService.deleteTask(taskId);
    }
}
