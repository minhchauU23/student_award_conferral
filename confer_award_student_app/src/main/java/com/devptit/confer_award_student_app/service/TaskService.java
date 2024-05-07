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
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    TaskRepository taskRepository;
    LogTaskRepository logTaskRepository;
    TaskMapper taskMapper;
    ExecutorService executorService = Executors.newSingleThreadExecutor();
    public TaskResponse start(TaskCreateRequest request){
        Task task = createTask(request);
        updateState(task.getId(), EnumState.CREATED.name(), EnumState.CREATED.getState());
        task = taskRepository.findById(task.getId()).orElseThrow();
        Task finalTask = task;
        executorService.execute(() -> running(finalTask));
        return getTask(task.getId());
    }
    public Task createTask(TaskCreateRequest request){
        Task task = taskMapper.toTask(request);
        return taskRepository.save(task);
    }
    public TaskResponse getTask(String taskID){
        return taskMapper.toTaskResponse(taskRepository.findById(taskID).orElseThrow());
    }

    public Boolean deleteTask(String taskID){
//        executorService.shutdownNow();
        executorService.shutdownNow();
        return true;
    }


    private void updateState(String taskID, String name, String state){
        Task task = taskRepository.findById(taskID).orElseThrow(()-> new AppException(ErrorCode.KEY_INVALID));
        LogTask logTask = new LogTask();
        logTask.setTime(LocalDateTime.now());
        logTask.setState(state);
        logTask.setName(name);
        logTask.setTask(task);
        logTaskRepository.save(logTask);
    }

    public APIResponse getEventDetail(Task task){
        String url = "http://127.0.0.1:9993/event_app/events/" + task.getEventId();
        RestClient restClient = RestClient.create();
        APIResponse apiResponse = new APIResponse<>();
        try{
            apiResponse = restClient.get().uri(url).retrieve()
                    .body(APIResponse.class);
            updateState(task.getId(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getName(), EnumState.GET_EVENT_DESCRIPTION_SUCCESS.getState());
            return apiResponse;
        }
        catch (HttpClientErrorException exception){
            updateState(task.getId(), EnumState.GET_EVENT_DESCRIPTION_FAILED.getName(), EnumState.GET_EVENT_DESCRIPTION_FAILED.getState());
        }

        return null;
    }

    public APIResponse getStudentTranscript(Task task){
        String url = "http://localhost:9995/student_app/student_transcript/" + task.getStudentID();
        RestClient restClient = RestClient.create();
        try {
            APIResponse apiResponse = restClient.get().uri(url).retrieve().body(APIResponse.class);
            updateState(task.getId(), EnumState.GET_STUDENT_TRANSCRIPT_SUCCESS.getName(), EnumState.GET_STUDENT_TRANSCRIPT_SUCCESS.getState());

            return apiResponse;
        }
        catch (HttpClientErrorException exception){
            updateState(task.getId(), EnumState.GET_STUDENT_TRANSCRIPT_FAILED.getName(), EnumState.GET_STUDENT_TRANSCRIPT_FAILED.getState());
        }
        return null;

    }

    public APIResponse getAwardRule(Task task){
        String url = "http://127.0.0.1:9994/award_app/awards/" + task.getAwardId() +"/conferral_rules";
        RestClient restClient = RestClient.create();
        APIResponse apiResponse = new APIResponse();
        try {
            apiResponse = restClient.get().uri(url).retrieve().body(APIResponse.class);
            updateState(task.getId(), EnumState.GET_AWARD_RULE_SUCCESS.getName(), EnumState.GET_AWARD_RULE_SUCCESS.getState());
            return  apiResponse;
        }
        catch (HttpClientErrorException exception){
            updateState(task.getId(), EnumState.GET_AWARD_RULE_FAILED.getName(), EnumState.GET_AWARD_RULE_FAILED.getState());
        }
        return null;
    }

    public APIResponse verifyEvent(APIResponse event){
        return null;
    }

    public APIResponse verifyStudentTranScript(){
        return null;
    }

    public APIResponse updateAwardHistory(Task task){
        AwardHistoryCreateRequest awardHistoryCreateRequest = new AwardHistoryCreateRequest();
        awardHistoryCreateRequest.setStudentID(task.getStudentID());
        awardHistoryCreateRequest.setEventID(task.getEventId());
        awardHistoryCreateRequest.setConferDate(LocalDate.now());
        awardHistoryCreateRequest.setDescription("conferral");
        String url = "http://127.0.0.1:9994/award_app/awards/"+task.getAwardId()+"/award_conferral";
        RestClient restClient = RestClient.create();
        try {
            APIResponse apiResponse = restClient.post().uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(awardHistoryCreateRequest)
                    .retrieve().body(APIResponse.class);
            updateState(task.getId(), EnumState.UPDATE_AWARD_HISTORY_SUCCESS.getName(), EnumState.UPDATE_AWARD_HISTORY_SUCCESS.getState());

            return apiResponse;
        }
        catch (HttpClientErrorException exception){
            updateState(task.getId(), EnumState.UPDATE_AWARD_HISTORY_FAILED.getName(), EnumState.UPDATE_AWARD_HISTORY_FAILED.getState());

        }
        return null;
    }

    public APIResponse updateStudentTranscript(Task task){
        StudentAwardUpdateRequest studentAwardUpdateRequest = new StudentAwardUpdateRequest();
        studentAwardUpdateRequest.setAwardId(task.getAwardId());
        studentAwardUpdateRequest.setEventID(task.getEventId());
        studentAwardUpdateRequest.setConferDate(LocalDate.now());
        String url = "http://localhost:9995/student_app/student_transcript/"+task.getStudentID();
        RestClient restClient = RestClient.create();

        try {
            APIResponse apiResponse = restClient.post().uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(studentAwardUpdateRequest)
                    .retrieve().body(APIResponse.class);
            updateState(task.getId(), EnumState.UPDATE_STUDENT_TRANSCRIPT_SUCCESS.getName(), EnumState.UPDATE_STUDENT_TRANSCRIPT_SUCCESS.getState());

            return apiResponse;

        }
        catch (HttpClientErrorException exception){
            updateState(task.getId(), EnumState.UPDATE_STUDENT_TRANSCRIPT_FAILED.getName(), EnumState.UPDATE_STUDENT_TRANSCRIPT_FAILED.getState());

        }
        return null;
    }

    public boolean verifyEvent(LinkedHashMap eventResponse){
        LocalDate now = LocalDate.now();
        LocalDate startDate = LocalDate.parse((String)eventResponse.get("startDate"));
        LocalDate endDate =   LocalDate.parse((String) eventResponse.get("endDate"));

        return now.isAfter(startDate)
                && now.isBefore(endDate);
    }

    private boolean verifyStudentTranscript(LinkedHashMap<String, Object> studentTranscript,
                                            List<LinkedHashMap> awardRule){
        for (LinkedHashMap lk : awardRule){
            if(!studentTranscript.containsKey(lk.get("name")) || (Double)studentTranscript.get(lk.get("name")) < (Double) lk.get("score")){
                return false;
            }
        }
        return true;
    }
    public void running(Task task){

        try {
            Thread.sleep(4000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }
        APIResponse eventDetail = getEventDetail(task);
        if(eventDetail == null){
            log.error("end task");
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }
        LinkedHashMap eventResponse  = (LinkedHashMap) eventDetail.getResult();
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }


        //verify event
        if(verifyEvent(eventResponse)){
            updateState(task.getId(), EnumState.VERIFY_EVENT_SUCCESS.getName(),EnumState.VERIFY_EVENT_SUCCESS.getState() );
        }
        else{
            updateState(task.getId(), EnumState.VERIFY_EVENT_FAILED.getName(),EnumState.VERIFY_EVENT_FAILED.getState() );
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }


        APIResponse studentTranscript = getStudentTranscript(task);
        if(studentTranscript == null){
            log.error("end task");
            return;
        }
        LinkedHashMap studentTranscriptResponse  = (LinkedHashMap) studentTranscript.getResult();
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }


        APIResponse award = getAwardRule(task);
        if(award == null) return;
        List<LinkedHashMap> awardResponse  = (ArrayList<LinkedHashMap>) award.getResult();
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }


        if(verifyStudentTranscript(studentTranscriptResponse, awardResponse)){
            updateState(task.getId(), EnumState.VERIFY_STUDENT_TRANSCRIPT_SUCCESS.getName(),EnumState.VERIFY_STUDENT_TRANSCRIPT_SUCCESS.getState() );
        }
        else{
            updateState(task.getId(), EnumState.VERIFY_STUDENT_TRANSCRIPT_FAILED.getName(),EnumState.VERIFY_STUDENT_TRANSCRIPT_FAILED.getState() );
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }


        APIResponse updateStudenTranscript =  updateStudentTranscript(task);
        if(updateStudenTranscript == null){
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }

        APIResponse updateAwardHistory = updateAwardHistory(task);
        if(updateAwardHistory == null){
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }
        try {
            Thread.sleep(8000);
            log.error("Sleeping");
        } catch (InterruptedException e) {
            return;
        }

        if(sendNotification()){
            updateState(task.getId(), EnumState.SEND_NOTIFICATION_SUCCESS.getName(),EnumState.SEND_NOTIFICATION_SUCCESS.getState() );
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
        }
        else{
            updateState(task.getId(), EnumState.SEND_NOTIFICATION_FAILED.getName(),EnumState.SEND_NOTIFICATION_FAILED.getState() );
            updateState(task.getId(), EnumState.END_TASK.getName(), EnumState.END_TASK.getState());
            return;
        }


    }

    public boolean sendNotification(){
        return true;
    }


}
