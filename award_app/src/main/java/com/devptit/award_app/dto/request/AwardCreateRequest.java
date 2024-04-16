package com.devptit.award_app.dto.request;

import com.devptit.award_app.entity.AwardHistory;
import com.devptit.award_app.entity.AwardRule;
import jakarta.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AwardCreateRequest {
    String id;
    String name;
    String description;
    String majorOfStudent;
}
