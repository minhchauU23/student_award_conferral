package com.devptit.award_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AwardRule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    float score;
    @ManyToOne
    @JoinColumn(name = "award_id", referencedColumnName = "id")
    Award award;
}
