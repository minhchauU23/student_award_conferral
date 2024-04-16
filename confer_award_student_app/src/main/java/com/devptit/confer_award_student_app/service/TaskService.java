package com.devptit.confer_award_student_app.service;

import com.devptit.confer_award_student_app.dto.request.AwardHistoryCreateRequest;
import com.devptit.confer_award_student_app.dto.request.StudentAwardUpdateRequest;
import com.devptit.confer_award_student_app.dto.request.TaskCreateRequest;
import com.devptit.confer_award_student_app.dto.response.APIResponse;
import com.devptit.confer_award_student_app.dto.response.TaskResponse;
import com.devptit.confer_award_student_app.entity.LogTask;
import com.devptit.confer_award_student_app.entity.Task;
import com.devptit.confer_award_student_app.exception.AppException;
import com.devptit.confer_award_student_app.exception.EnumState;
import com.devptit.confer_award_student_app.exception.ErrorCode;
import com.devptit.confer_award_student_app.mapper.TaskMapper;
import com.devptit.confer_award_student_app.repository.LogTaskRepository;
import com.devptit.confer_award_student_app.repository.TaskRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    TaskRepository taskRepository;
    LogTaskRepository logTaskRepository;
    TaskMapper taskMapper;

    public TaskResponse start(TaskCreateRequest request){
       TaskResponse taskResponse = createTask(request);
       updateTask(taskResponse.getId(), EnumState.CREATED.getState(), EnumState.CREATED.getDescription());
       running(taskResponse);
       return taskResponse;
    }


    public TaskResponse createTask(TaskCreateRequest request){
        Task task = taskMapper.toTask(request);
        return taskMapper.toTaskResponse(taskRepository.save(task));
    }


    public TaskResponse getTask(String taskID){
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        return taskMapper.toTaskResponse(task);
    }

    public boolean deleteTask(String taskID){
        taskRepository.deleteById(taskID);
        return true;
    }

    @Async
    public void running(TaskResponse request){
            Future<APIResponse> eventDetail = callGetApi("http://127.0.0.1:9993/event_app/events/" + request.getEventId(),1 ,request);
            LinkedHashMap eventResponse;
            LinkedHashMap studentTranScript;
            List<LinkedHashMap> awardRule;
            if(eventDetail != null && eventDetail.isDone() ){
                try {
                    updateTask(request.getId(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getState(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getDescription());

                    eventResponse  = (LinkedHashMap) eventDetail.get().getResult();

                    if(!verify( LocalDate.parse((String)eventResponse.get("startDate")),
                            LocalDate.parse((String) eventResponse.get("endDate")) )){
                        updateTask(request.getId(), EnumState.OUTSIDE_EVENT_TIME.getState(),EnumState.OUTSIDE_EVENT_TIME.getDescription());
                    }
                    else{
                        updateTask(request.getId(), EnumState.VERIFY_EVENT_SUCCESS.getState(), EnumState.VERIFY_EVENT_SUCCESS.getDescription());

                        Future<APIResponse> studentTranScriptResponse =
                                callGetApi("http://localhost:9995/student_app/student_transcript/"+request.getStudentID(), 2, request);
                        if(studentTranScriptResponse!=null && studentTranScriptResponse.isDone()){
                            studentTranScript = (LinkedHashMap) studentTranScriptResponse.get().getResult();
                            Future<APIResponse> awardResponse =
                                    callGetApi("http://127.0.0.1:9994/award_app/awards/"+ request.getAwardId()+"/conferral_rules", 3, request);
                            if(awardResponse !=null && awardResponse.isDone()){
                                awardRule = (ArrayList<LinkedHashMap>) awardResponse.get().getResult();
                                if(verifyStudentTranscript(studentTranScript, awardRule, request)){
//                                 sendNotification();
                                    AwardHistoryCreateRequest awardHistoryCreateRequest = new AwardHistoryCreateRequest();
                                    awardHistoryCreateRequest.setStudentID(request.getStudentID());
                                    awardHistoryCreateRequest.setEventID(request.getEventId());
                                    awardHistoryCreateRequest.setConferDate(LocalDate.now());
                                    awardHistoryCreateRequest.setDescription("conferral");
                                    Future<APIResponse> updateAwardHistory =
                                            callPostAPi("http://127.0.0.1:9994/award_app/awards/"+request.getAwardId()+"/award_conferral",
                                                    request, 1,awardHistoryCreateRequest);
                                    if(updateAwardHistory!=null && updateAwardHistory.isDone()){

                                        StudentAwardUpdateRequest studentAwardUpdateRequest = new StudentAwardUpdateRequest();
                                        studentAwardUpdateRequest.setAwardId(request.getAwardId());
                                        studentAwardUpdateRequest.setEventID(request.getEventId());
                                        studentAwardUpdateRequest.setConferDate(LocalDate.now());
                                        Future<APIResponse> updateStudentAward =
                                                callPostAPi("http://localhost:9995/student_app/student_transcript/" + request.getStudentID(),
                                                        request, 2,studentAwardUpdateRequest);
                                    }
                                }
                            }

                        }
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            }
            updateTask(request.getId(), EnumState.END_TASK.getState(), EnumState.END_TASK.getDescription());

    }



    private void updateTask(String taskID, String state, String description){
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        LogTask logTask = new LogTask();
        logTask.setTime(LocalDateTime.now());
        logTask.setState(state);
        logTask.setDescription(description);
        logTask.setTask(task);
        logTaskRepository.save(logTask);
    }

    @Async
    private Future<APIResponse> callGetApi(String url, int type, TaskResponse taskResponse){
        RestClient restClient = RestClient.create();
        while (true){
            try{
                log.error(restClient.get().uri(url).retrieve().toString());
                APIResponse apiResponse =
                        restClient.get().uri(url).retrieve().body(APIResponse.class);
                int code = apiResponse.getCode();
                if(code == 200){
                    switch (type){
                        case 1: updateTask(taskResponse.getId(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getState(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getDescription());
                        case 2: updateTask(taskResponse.getId(), EnumState.GET_STUDENT_TRANSCRIPT_SUCCESS.getState(), EnumState.GET_STUDENT_TRANSCRIPT_SUCCESS.getDescription());
                        case 3: updateTask(taskResponse.getId(), EnumState.GET_AWARD_RULE_SUCCESS.getState(), EnumState.GET_AWARD_RULE_SUCCESS.getDescription());
                    }
                    return new AsyncResult<APIResponse>(apiResponse);
                }
                else if(code == 400){
                    updateTask(taskResponse.getId(), EnumState.KEY_INVALID.getState(), EnumState.KEY_INVALID.getDescription());
                    return null;
                }
                else {
                    return null;
                }

            }
            catch (Exception ex){
                log.error(ex.getMessage());
                switch (type){
                    case 1: updateTask(taskResponse.getId(), EnumState.GETTING_EVENT_DESCRIPTION.getState(), EnumState.GETTING_EVENT_DESCRIPTION.getDescription());
                    case 2: updateTask(taskResponse.getId(), EnumState.GETTING_STUDENT_TRANSCRIPT.getState(), EnumState.GETTING_EVENT_DESCRIPTION.getDescription());
                    case 3: updateTask(taskResponse.getId(), EnumState.GETTING_AWARD_RULE.getState(), EnumState.GETTING_AWARD_RULE.getDescription());
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private boolean verify(LocalDate startDate, LocalDate endDate){
        LocalDate now = LocalDate.now();
        return now.isAfter(startDate)
                && now.isBefore(endDate);
    }
    private boolean verifyStudentTranscript(LinkedHashMap<String, Object> studentTranscript,
                                            List<LinkedHashMap> awardRule, TaskResponse taskResponse){
        for (LinkedHashMap lk : awardRule){
            if(!studentTranscript.containsKey(lk.get("name")) || (Double)studentTranscript.get(lk.get("name")) < (Double) lk.get("score")){
                updateTask(taskResponse.getId(), EnumState.STUDENT_NOT_ELIGIBLE.getState(), EnumState.STUDENT_NOT_ELIGIBLE.getDescription());
                return false;
            }
        }
        updateTask(taskResponse.getId(), EnumState.VERIFY_STUDENT_TRANSCRIPT_SUCCESS.getState(), EnumState.VERIFY_STUDENT_TRANSCRIPT_SUCCESS.getDescription());
        return true;
    }

    @Async
    private Future<APIResponse> sendNotification(String url, String message){

        return null;
    }

    @Async
    private Future<APIResponse> callPostAPi(String url,TaskResponse taskResponse, int type, Object t){
        RestClient restClient = RestClient.create();
        while (true){
            try{
                APIResponse apiResponse =
                        restClient.post().uri(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(t)
                                .retrieve().body(APIResponse.class);
                int code = apiResponse.getCode();
                if(code == 200) {
                    if(type == 1){
                        updateTask(taskResponse.getId(), EnumState.UPDATE_AWARD_HISTORY_SUCCESS.getState(), EnumState.UPDATE_AWARD_HISTORY_SUCCESS.getDescription());
                    }
                    else{
                        updateTask(taskResponse.getId(), EnumState.UPDATE_STUDENT_TRANSCRIPT_SUCCESS.getState(), EnumState.UPDATE_STUDENT_TRANSCRIPT_SUCCESS.getDescription());
                    }
                    return new AsyncResult<APIResponse>(apiResponse);
                }
                else if(code == 400){
                    updateTask(taskResponse.getId(), EnumState.KEY_INVALID.getState(), EnumState.KEY_INVALID.getDescription());
                    return null;
                }
                break;
            }
            catch (Exception ex){
                if(type == 1){
                    updateTask(taskResponse.getId(), EnumState.UPDATING_AWARD_HISTORY.getState(), EnumState.UPDATING_AWARD_HISTORY.getDescription());
                }
                else{
                    updateTask(taskResponse.getId(), EnumState.UPDATING_STUDENT_TRANSCRIPT.getState(), EnumState.UPDATING_STUDENT_TRANSCRIPT.getDescription());
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                }
            }
        }
        return null;
    }

}
