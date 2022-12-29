package com.example.todolist.controller;

import com.example.todolist.domain.Task;
import com.example.todolist.mapper.TaskMapper;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class TaskController {
    
    @Resource
    private TaskMapper taskMapper;
    
    @GetMapping("/getTask/{userId}")
    public List<Task> getTask(@PathVariable String userId, HttpServletResponse response) {
        return taskMapper.getAll(userId);
    }
    
    @PostMapping("/insert")
    public int insertTask(@RequestBody Task task) {
        return taskMapper.addTask(task);
    }
    
    @GetMapping("/getUserId")
    public String getUserId(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        if (!Objects.isNull(cookies)) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userId")) {
                    return cookie.getValue();
                }
            }
        }
        String userId = UUID.randomUUID().toString();
        response.addCookie(new Cookie("userId", userId));
        return userId;
    }
    
    @GetMapping("/exists/{userId}/{taskId}")
    public Boolean exists(@PathVariable Integer taskId, @PathVariable String userId) {
        return taskMapper.queryTask(taskId, userId) != null;
    }
    
    @PutMapping("/update")
    public void update(@RequestBody Task task) {
        taskMapper.updateTask(task);
    }
    
    @DeleteMapping("/deleteTask/{id}")
    public void delete(@PathVariable Integer id) {
        taskMapper.deleteTask(id);
    }
}
