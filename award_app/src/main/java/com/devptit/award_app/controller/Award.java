package com.devptit.award_app.controller;

import com.devptit.award_app.dto.request.AwardCreateRequest;
import com.devptit.award_app.dto.request.AwardHistoryCreateRequest;
import com.devptit.award_app.dto.request.AwardHistoryUpdateRequest;
import com.devptit.award_app.dto.request.AwardRuleCreateRequest;
import com.devptit.award_app.dto.response.APIResponse;
import com.devptit.award_app.dto.response.AwardHistoryResponse;
import com.devptit.award_app.dto.response.AwardResponse;
import com.devptit.award_app.dto.response.AwardRuleResponse;
import com.devptit.award_app.service.AwardHistoryService;
import com.devptit.award_app.service.AwardRuleService;
import com.devptit.award_app.service.AwardService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/awards")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class Award {
    AwardService awardService;
    AwardRuleService awardRuleService;
    AwardHistoryService awardHistoryService;
    @GetMapping("/{awardID}")
    public APIResponse<AwardResponse> getDetail(@PathVariable("awardID") String awardID){
        return APIResponse.<AwardResponse>builder()
                .code(200)
                .result(awardService.findById(awardID))
                .build();
    }

    @PostMapping
    public APIResponse<AwardResponse> create(@RequestBody AwardCreateRequest request){
        return APIResponse.<AwardResponse>builder()
                .code(200)
                .result(awardService.create(request))
                .build();
    }

    @GetMapping("/{awardID}/conferral_rules")
    public APIResponse<List<AwardRuleResponse>> getRulesByAwardID(@PathVariable("awardID") String awardID){
        return APIResponse.<List<AwardRuleResponse>>builder()
                .code(200)
                .result(awardRuleService.findByAwardID(awardID))
                .build();
    }

    @PostMapping("/{awardID}/conferral_rules")
    public APIResponse<AwardRuleResponse> createRule(@PathVariable("awardID") String awardID,
                                                     @RequestBody AwardRuleCreateRequest request){
        return APIResponse.<AwardRuleResponse>builder()
                .code(200)
                .result(awardRuleService.createRequest(awardID, request))
                .build();
    }

    @PostMapping("/{awardID}/award_conferral")
    public APIResponse<AwardHistoryResponse> updateHistory(@PathVariable("awardID") String awardID,
                                                           @RequestBody AwardHistoryCreateRequest request
                                                           ){
        return APIResponse.<AwardHistoryResponse>builder()
                .code(200)
                .result(awardHistoryService.createHistory(awardID, request))
                .build();

    }

//    @PostMapping("/award_conferral")
//    public ApiResponse<AwardHistoryResponse> confer(@RequestBody AwardHistoryCreateRequest request){
//        //sendNotifycation
//    }


}
