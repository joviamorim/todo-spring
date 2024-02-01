package com.jovi.estudoSpring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    public interface CreateUser {

    }

    public interface UpdateUser {

    }

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 15)
    @Size(min = 5, max = 15)
    @NotBlank
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 8)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    @Column(name = "tasks")
    @OneToMany(mappedBy = "user")
    private List<Task> tasks = new ArrayList<Task>();
}
