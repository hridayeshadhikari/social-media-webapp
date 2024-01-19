package com.socialvista.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String content;
    private String image;

    @ManyToOne
    private User user;
    @JsonIgnore
    @ManyToOne
    private Chat chat;
    private LocalDateTime timestamp;
}