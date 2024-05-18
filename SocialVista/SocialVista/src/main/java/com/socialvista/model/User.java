package com.socialvista.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "User_details")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String password;
    private String gender;

    private List<Integer> followers = new ArrayList<>();


    private List<Integer> following = new ArrayList<>();
    @OneToMany
    private List<Post> usersPost=new ArrayList<>();
    @JsonIgnore
    @ManyToMany
    private List<Post> savePost = new ArrayList<>();



}
