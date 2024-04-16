package com.devptit.award_app.mapper;

import com.devptit.award_app.controller.Award;
import com.devptit.award_app.dto.request.AwardHistoryCreateRequest;
import com.devptit.award_app.dto.request.AwardHistoryUpdateRequest;
import com.devptit.award_app.dto.response.AwardHistoryResponse;
import com.devptit.award_app.entity.AwardHistory;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AwardHistoryMapper {

    void update( AwardHistoryUpdateRequest request, @MappingTarget AwardHistory awardHistory);

    AwardHistoryResponse toAwardHistoryResponse(AwardHistory awardHistory);
    AwardHistory toAwardHistory(AwardHistoryUpdateRequest awardHistoryUpdateRequest);

    AwardHistory toAwardHistory(AwardHistoryCreateRequest request);
}
