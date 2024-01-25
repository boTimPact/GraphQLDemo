package demo.graphql.demo.controller;

import demo.graphql.demo.repository.TimestampRepository;
import demo.graphql.demo.model.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class ClicksController {

    private final TimestampRepository repository;

    @Autowired
    public ClicksController(TimestampRepository repository) { this.repository = repository; }

    /**
     * Rest Actions
     */
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

    /**
     * GraphQL Resolvers
     */
    @QueryMapping
    public Timestamp[] timestamps(){
        return repository.findAll().toArray(new Timestamp[0]);
    }

    @MutationMapping
    public Timestamp timestamp(@Argument String time){
        Timestamp timestamp = new Timestamp(time);
        repository.save(timestamp);
        return repository.getByTime(time).get(0);
    }

    /**
     * Field Resolver
     */
    @SchemaMapping(typeName = "Timestamp", field = "time")
    public String getTime(Timestamp timestamp){
        System.out.println("Schema resolver: time, id: " + timestamp.getId());
        return timestamp.getTime();
    }

//    @SchemaMapping(typeName = "Timestamp", field = "id")
//    public int getId(Timestamp timestamp){
//        System.out.println("Schema resolver id: " + timestamp.getId());
//        return timestamp.getId();
//    }
}
