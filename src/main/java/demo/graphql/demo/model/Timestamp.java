package demo.graphql.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Timestamp {
    public Timestamp(String time) {
        this.time = time;
    }

    public Timestamp() {}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String time;

    public Integer getId() { return id; }

    public String getTime() {
        return time;
    }
}
