package com.example.todolist.mapper;

import com.example.todolist.domain.Task;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface TaskMapper {
    
    List<Task> getAll(String  userId);
    
    int addTask(Task task);
    
    int deleteTask(int id);
    
    int updateTask(Task task);
    
    Task queryTask(Integer id,String userId);
}
