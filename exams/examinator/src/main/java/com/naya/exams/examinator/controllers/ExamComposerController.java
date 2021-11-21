package com.naya.exams.examinator.controllers;

import com.naya.exams.examinator.model.Exam;
import com.naya.exams.examinator.model.Exercise;
import com.naya.exams.examinator.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

/**
 * @author Evgeny Borisov
 */
@RestController
@RequestMapping("/exams")
public class ExamComposerController {
    private final RestTemplate restTemplate;

    private int number = 1;

    @Autowired
    public ExamComposerController(@LoadBalanced RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/exam")
    public Exam createExam(@RequestBody Map<String, Integer> examSpec) {
        List<Section> sections = examSpec.entrySet().stream().map(entry -> {
            String title = entry.getKey();
            String url = getServiceUrl(title, entry.getValue());
            Exercise[] exercises = restTemplate.getForObject(url, Exercise[].class);
            return Section.builder().exercises(Arrays.asList(exercises)).title(title).build();
        }).collect(toList());
        return Exam.builder().sections(sections).title("Best exam #" + number++).build();
    }

    public String getServiceUrl(final String service, final int amount) {
        return "http://" + service + "/exercise/random?amount=" + amount;
    }
}
