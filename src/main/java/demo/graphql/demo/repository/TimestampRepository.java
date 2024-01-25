package demo.graphql.demo.repository;

import demo.graphql.demo.model.Timestamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimestampRepository extends JpaRepository<Timestamp, Integer> {
    public List<Timestamp> getByTime(String time);
}
