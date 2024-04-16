package com.devptit.award_app.mapper;

import com.devptit.award_app.dto.request.AwardRuleCreateRequest;
import com.devptit.award_app.dto.response.AwardRuleResponse;
import com.devptit.award_app.entity.AwardRule;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AwardRuleMapper {
    AwardRuleResponse toAwardRuleResponse(AwardRule awardRule);
    AwardRule toAwardRule(AwardRuleCreateRequest request);
    List<AwardRuleResponse> toAwardRuleResponses(List<AwardRule> awardRules);
}
