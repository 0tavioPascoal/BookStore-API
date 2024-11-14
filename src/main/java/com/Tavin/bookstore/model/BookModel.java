package com.Tavin.bookstore.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_book")
@Data
@EntityListeners(AuditingEntityListener.class)
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title", length = 150, nullable = false, unique = true)
    private String title;

    @Column(name = "data_publication", nullable = false)
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name ="gender", nullable = false, length = 30)
    private GenderModel gender;

    @Column(name = "price", nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

    @ManyToOne(
//            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "id_autor")
    private AuthorModel author;

    @CreatedDate
    @Column(name = "createdAt")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;

    @Column(name = "id_user")
    private UUID idUser;

}
