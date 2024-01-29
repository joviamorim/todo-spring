package com.jovi.estudoSpring.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_task")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User userId;

    @Column(name = "description", nullable = false, length = 255)
    @Size(max = 255)
    @NotBlank
    private String description;
}
