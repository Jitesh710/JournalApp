package net.engineeringdigest.journalApp.repository;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUsersForSentimentAnalysis() {

            Query query = new Query();
            query.addCriteria(Criteria.where("email").exists(true));
            query.addCriteria(Criteria.where("sentimentAnalyis").is(true));
            List<User> users = mongoTemplate.find(query, User.class);
            return users;
    }
}
