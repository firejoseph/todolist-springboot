package com.example.todolist.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Task {
    
    Integer id;
    
    String userId;
    
    String content;
    
    Boolean completed;
}
