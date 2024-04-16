package com.devptit.award_app.service;

import com.devptit.award_app.dto.request.AwardCreateRequest;
import com.devptit.award_app.dto.response.AwardResponse;
import com.devptit.award_app.entity.Award;
import com.devptit.award_app.exception.AppException;
import com.devptit.award_app.exception.ErrorCode;
import com.devptit.award_app.mapper.AwardMapper;
import com.devptit.award_app.repository.AwardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AwardService {
    AwardRepository awardRepository;
    AwardMapper awardMapper;

    public AwardResponse findById(String awardID){
        Award award = awardRepository.findById(awardID).orElseThrow(()->new AppException(ErrorCode.KEY_INVALID));
        return awardMapper.toAwardResponse(award);
    }

    public AwardResponse create(AwardCreateRequest request){
        Award award = awardMapper.toAward(request);
        return awardMapper.toAwardResponse(awardRepository.save(award));
    }

}
