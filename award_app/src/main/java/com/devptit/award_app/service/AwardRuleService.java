package com.devptit.award_app.service;

import com.devptit.award_app.dto.request.AwardRuleCreateRequest;
import com.devptit.award_app.dto.response.AwardRuleResponse;
import com.devptit.award_app.entity.Award;
import com.devptit.award_app.entity.AwardRule;
import com.devptit.award_app.mapper.AwardRuleMapper;
import com.devptit.award_app.repository.AwardRepository;
import com.devptit.award_app.repository.AwardRuleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AwardRuleService {
    AwardRepository awardRepository;
    AwardRuleRepository awardRuleRepository;
    AwardRuleMapper awardRuleMapper;

    public AwardRuleResponse createRequest(String awardID, AwardRuleCreateRequest request){
        AwardRule awardRule = awardRuleMapper.toAwardRule(request);
        Award award = awardRepository.findById(awardID).orElseThrow();
        awardRule.setAward(award);

        return awardRuleMapper.toAwardRuleResponse(awardRuleRepository.save(awardRule));
    }

    public List<AwardRuleResponse> findByAwardID(String awardID) {
        List<AwardRule> awardRules = awardRuleRepository.findByAward(awardID);
        return awardRuleMapper.toAwardRuleResponses(awardRules);
    }
}
