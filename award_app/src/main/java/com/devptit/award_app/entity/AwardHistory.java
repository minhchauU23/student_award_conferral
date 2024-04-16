package com.devptit.award_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AwardHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String studentID;
    String eventID;
    LocalDate conferDate;
    String description;
    @ManyToOne
    Award award;
}
