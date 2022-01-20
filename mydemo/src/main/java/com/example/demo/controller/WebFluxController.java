package com.example.demo.controller;

import com.example.demo.bean.Log;
import com.example.demo.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("webFlux")
public class WebFluxController {
    @Autowired
    LogRepository logRepository;

    @GetMapping("/testMono")
    @ResponseBody
    public Mono<Log> testMono() throws IOException {
        Log log=new Log();
        log.setAge(111);
        return Mono.just(log);
    }

    @GetMapping("/testFlux")
    @ResponseBody
    public Flux<List<Log>> testFlux() throws IOException {
        List<Log> logs= logRepository.findByQuery("%22%", PageRequest.of(1, 20, Sort.by("id").descending()));
        return Flux.just(logs);
    }
}
