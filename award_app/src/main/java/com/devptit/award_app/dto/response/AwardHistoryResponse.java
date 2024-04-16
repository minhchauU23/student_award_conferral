package com.devptit.award_app.dto.response;

import com.devptit.award_app.entity.Award;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AwardHistoryResponse {
    String id;
    String studentID;
    String eventID;
    LocalDate conferDate;
    String description;
}
