package com.lion.sbb2.answer.entity;

import com.lion.sbb2.question.entity.Question;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(columnDefinition = "TEXT")
    String content;
    @CreatedDate
    LocalDateTime createDate;
    @LastModifiedDate
    LocalDateTime modifyDate;
    @ManyToOne
    Question question;

}
