package com.devptit.award_app.mapper;

import com.devptit.award_app.dto.request.AwardCreateRequest;
import com.devptit.award_app.dto.response.AwardResponse;
import com.devptit.award_app.entity.Award;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AwardMapper {
    AwardResponse toAwardResponse(Award award);
    Award toAward(AwardCreateRequest request);
}
