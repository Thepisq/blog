package com.liushaonetwork.blog.app.controller.front;

import com.liushaonetwork.blog.app.service.SysTopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 13496
 */
@RestController
public class TopicController {
    private final SysTopicService topicService;

    @Autowired
    public TopicController(SysTopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping("/topic/getHotTopic")
    public ResponseEntity<?> getHotTopic(@RequestParam(defaultValue = "1") int page) {
        return ResponseEntity.ok(topicService.getSysTopics(page));
    }
}
