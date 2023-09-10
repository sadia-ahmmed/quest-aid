package com.project.questaidbackend.services;

import com.project.questaidbackend.exceptions.EntityNotFoundException;
import com.project.questaidbackend.models.ClubMember;
import com.project.questaidbackend.models.Task;
import com.project.questaidbackend.repository.TaskRepository;
import com.project.questaidbackend.services.interfaces.IClubMemberService;
import com.project.questaidbackend.services.interfaces.ITaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements ITaskService {

    private TaskRepository taskRepository;

    private IClubMemberService clubMemberService;

    @Override
    public Task addTask(Task task, Long memberId) {
        ClubMember clubMember = clubMemberService.getClubMemberAllDetailsById(memberId);
        task.setClub(clubMember.getClub());
        task.setClubMember(clubMember);
        return taskRepository.save(task);
    }

    @Override
    public Task completeTask(Long taskId) {
        Task task = unwrapTask(taskRepository.findById(taskId), taskId);
        task.setStatus(true);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllRemainingTasksByClubMember(Long memberId) {
        return taskRepository.findTaskByClubMemberId(memberId).stream().filter(task -> !task.isStatus()).collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllRemainingTasksByClub(Long clubId) {
        return taskRepository.findTaskByClubId(clubId).stream().filter(task -> !task.isStatus()).collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksDoneByClubMember(Long memberId) {
        return taskRepository.findTaskByClubMemberId(memberId).stream().filter(Task::isStatus).collect(Collectors.toList());
    }

    @Override
    public List<Task> getAllTasksDoneByClub(Long clubId) {
        return taskRepository.findTaskByClubId(clubId).stream().filter(Task::isStatus).collect(Collectors.toList());
    }

    @Override
    public double getProductivityByClub(Long clubId) {
        int doneTasks = getAllTasksDoneByClub(clubId).size();
        int pendingTasks = getAllRemainingTasksByClub(clubId).size();

        return (pendingTasks / doneTasks) * 100;
    }

    static Task unwrapTask(Optional<Task> entity, Long id) {
        if (entity.isPresent()) return entity.get();
        else throw new EntityNotFoundException(id, Task.class);
    }
}
