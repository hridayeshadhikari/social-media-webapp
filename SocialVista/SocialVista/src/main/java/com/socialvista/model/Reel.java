package com.socialvista.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String video;
    private String caption;

    @ManyToMany
    List<User> liked=new ArrayList<>();
    @OneToMany
    List<Comment> comment =new ArrayList<>();

    @ManyToOne
    private User user;
}
