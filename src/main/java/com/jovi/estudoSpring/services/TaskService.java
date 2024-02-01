package com.jovi.estudoSpring.services;

import com.jovi.estudoSpring.models.Task;
import com.jovi.estudoSpring.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task findTaskById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);
        return task.orElseThrow(() -> new RuntimeException(
                "Tarefa não encontrada! Id: " + id + ", tipo: " + Task.class.getName()
        ));
    }

    public List<Task> findAllTasksById(Long userId) {
        List<Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
    }

    @Transactional
    public Task create(Task obj) {
        obj.setId(null);
        /*
        A parte comentada é se caso a task guardasse o nome do usuário
        User user = this.userService.findUserById(obj.getUserId());
        obj.setUser(user);
        */
        obj = this.taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findTaskById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);
    }

    public void delete(Long id) {
        findTaskById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possível excluir. Erro: " + e);
        }
    }
}
