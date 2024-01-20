package demo.graphql.demo.controller;

import demo.graphql.demo.repository.TimestampRepository;
import demo.graphql.demo.model.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClicksController {

    private final TimestampRepository repository;
    List<String> timestamps = new ArrayList<>();

    @Autowired
    public ClicksController(TimestampRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/clicks")
    public @ResponseBody
    List<String> getClicks(){
        return repository.findAll().stream().map(Timestamp::getTime).collect(Collectors.toList());
    }

    @PostMapping("/clicks")
    public void postClicks(String timestamp){
        Timestamp time = new Timestamp(timestamp);
        repository.save(time);
    }

    @QueryMapping
    public Timestamp[] getTimestamps(){
        var timestamps = repository.findAll().toArray(new Timestamp[0]);
        return timestamps;
    }

    @MutationMapping
    public Timestamp saveTimestamp(@Argument String time){
        Timestamp timestamp = new Timestamp(time);
        repository.save(timestamp);
        return repository.getByTime(time);
    }
}
