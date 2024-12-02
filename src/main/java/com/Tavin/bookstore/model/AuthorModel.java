package com.Tavin.bookstore.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.awt.print.Book;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_author")
@Data
@EntityListeners(AuditingEntityListener.class)
public class AuthorModel{


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "data_of_birth", nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "nacionality" , length = 50, nullable = false)
    private String nationality;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY
//            , cascade = CascadeType.ALL
    )
    private List<BookModel> book;

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @JoinColumn(name = "id_user")
    @ManyToOne
    private UserModel user;

}
