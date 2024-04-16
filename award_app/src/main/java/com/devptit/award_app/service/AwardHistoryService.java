package com.devptit.award_app.service;

import com.devptit.award_app.dto.request.AwardHistoryCreateRequest;
import com.devptit.award_app.dto.request.AwardHistoryUpdateRequest;
import com.devptit.award_app.dto.response.AwardHistoryResponse;
import com.devptit.award_app.entity.Award;
import com.devptit.award_app.entity.AwardHistory;
import com.devptit.award_app.exception.AppException;
import com.devptit.award_app.exception.ErrorCode;
import com.devptit.award_app.mapper.AwardHistoryMapper;
import com.devptit.award_app.repository.AwardHistoryRepository;
import com.devptit.award_app.repository.AwardRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AwardHistoryService {
    AwardHistoryRepository awardHistoryRepository;
    AwardRepository awardRepository;
    AwardHistoryMapper awardHistoryMapper;
    public AwardHistoryResponse updateHistory(String awardHistoryID, AwardHistoryUpdateRequest request) {
        AwardHistory awardHistory = awardHistoryRepository.findById(awardHistoryID).orElseThrow(()->new AppException(ErrorCode.KEY_INVALID));
        awardHistoryMapper.update(request, awardHistory);
        return awardHistoryMapper.toAwardHistoryResponse(awardHistoryRepository.save(awardHistory));
    }

    public AwardHistoryResponse createHistory(String awardId,AwardHistoryCreateRequest request){
        Award award = awardRepository.findById(awardId).orElseThrow(()->new AppException(ErrorCode.KEY_INVALID));
        AwardHistory awardHistory = awardHistoryMapper.toAwardHistory(request);
        awardHistory.setAward(award);
        return awardHistoryMapper.toAwardHistoryResponse(awardHistoryRepository.save(awardHistory));
    }

}
