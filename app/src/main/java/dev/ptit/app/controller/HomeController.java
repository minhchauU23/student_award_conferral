package dev.ptit.app.controller;

import dev.ptit.app.dto.request.TaskCreateRequest;
import dev.ptit.app.dto.response.APIResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestClient;

import java.util.LinkedHashMap;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homePage( ){
        return "home";
    }

    @PostMapping("/confer")
    public String confer(
            @RequestParam("eventID") String eventID,
            @RequestParam("studentID") String studentID,
            @RequestParam("awardID") String awardID,
            Model model
    ){
        TaskCreateRequest request = new TaskCreateRequest();
        request.setAwardId(awardID);
        request.setStudentID(studentID);
        request.setEventId(eventID);
        RestClient restClient = RestClient.create();
        APIResponse apiResponse = restClient.post().uri("http://127.0.0.1:9996/confer_student_award/task")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve().body(APIResponse.class);
        LinkedHashMap<String, String> response = (LinkedHashMap<String, String>) apiResponse.getResult();
        model.addAttribute("id",response.get("id"));
        return "confer";
    }

}
