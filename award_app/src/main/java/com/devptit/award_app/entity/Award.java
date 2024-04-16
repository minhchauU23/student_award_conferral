package com.devptit.award_app.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Award {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;
    String name;
    String description;
    String majorOfStudent;

    @OneToMany(mappedBy = "award")
    Set<AwardRule> awardRules;

    @OneToMany(mappedBy = "award")
    Set<AwardHistory> awardHistories;
}
