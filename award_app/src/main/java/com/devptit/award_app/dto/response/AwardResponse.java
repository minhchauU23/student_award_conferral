package com.devptit.award_app.dto.response;

import com.devptit.award_app.entity.AwardHistory;
import com.devptit.award_app.entity.AwardRule;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AwardResponse {
    String id;
    String name;
    String majorOfStudent;
    String description;
    Set<AwardRuleResponse> awardRules;
}
