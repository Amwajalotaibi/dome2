package com.example.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String message;

//    private Integer userId; //هذا يجيني من تسجيل الدخول

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "todoId",referencedColumnName = "id")
    private MyUser myUser;
}
