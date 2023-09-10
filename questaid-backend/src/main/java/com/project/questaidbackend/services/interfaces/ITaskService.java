package com.project.questaidbackend.services.interfaces;

import com.project.questaidbackend.models.Task;

import java.util.List;

public interface ITaskService {
    Task addTask(Task task, Long memberId);
    Task completeTask(Long taskId);
    List<Task> getAllRemainingTasksByClubMember(Long memberId);
    List<Task> getAllRemainingTasksByClub(Long clubId);
    List<Task> getAllTasksDoneByClubMember(Long memberId);
    List<Task> getAllTasksDoneByClub(Long clubId);
    double getProductivityByClub(Long clubId);
}
