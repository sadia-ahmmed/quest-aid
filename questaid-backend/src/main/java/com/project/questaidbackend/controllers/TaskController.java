package com.project.questaidbackend.controllers;

import com.project.questaidbackend.models.Task;
import com.project.questaidbackend.services.interfaces.ITaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/task")
public class TaskController {

    private ITaskService taskService;

    @PostMapping("/add/{memberId}")
    public ResponseEntity<Task> addTask(@Valid @RequestBody Task task, @PathVariable Long memberId) {
        return new ResponseEntity<>(taskService.addTask(task, memberId), HttpStatus.CREATED);
    }

    @PutMapping("/complete/{taskId}")
    public ResponseEntity<Task> addTask(@PathVariable Long taskId) {
        return new ResponseEntity<>(taskService.completeTask(taskId), HttpStatus.OK);
    }

    @GetMapping("/{memberId}/member/pending")
    public ResponseEntity<List<Task>> getPendingTasksByMember(@PathVariable Long memberId) {
        return new ResponseEntity<>(taskService.getAllRemainingTasksByClubMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/club/pending")
    public ResponseEntity<List<Task>> getPendingTasksByClub(@PathVariable Long clubId) {
        return new ResponseEntity<>(taskService.getAllRemainingTasksByClub(clubId), HttpStatus.OK);
    }

    @GetMapping("/{memberId}/member/done")
    public ResponseEntity<List<Task>> getTasksDoneByMember(@PathVariable Long memberId) {
        return new ResponseEntity<>(taskService.getAllTasksDoneByClubMember(memberId), HttpStatus.OK);
    }

    @GetMapping("/{clubId}/club/done")
    public ResponseEntity<List<Task>> getTasksDoneByClub(@PathVariable Long clubId) {
        return new ResponseEntity<>(taskService.getAllTasksDoneByClub(clubId), HttpStatus.OK);
    }

}
