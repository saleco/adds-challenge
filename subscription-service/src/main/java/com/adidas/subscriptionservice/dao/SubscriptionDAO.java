package com.adidas.subscriptionservice.dao;

import com.adidas.subscriptionservice.models.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Repository
public class SubscriptionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String CAMPAIGN_SUBSCRIBER_INSERT = "INSERT INTO CAMPAIGN_SUBSCRIBER (CAMPAIGN_ID, SUBSCRIBER_ID) VALUES (?, ?)";
    private static final String SUBSCRIBER_INSERT = "INSERT INTO SUBSCRIBER (first_name, gender, email, date_of_birth) VALUES (?, ?, ?, ?)";

    public Subscriber createSubscriber(Subscriber subscriber){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SUBSCRIBER_INSERT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, subscriber.getFirstName());
            preparedStatement.setString(2, subscriber.getGender());
            preparedStatement.setString(3, subscriber.getEmail());
            preparedStatement.setString(4, subscriber.getDateOfBirth());
            return preparedStatement;
        }, keyHolder);

        subscriber.setId((Integer) keyHolder.getKeys().get("ID"));
        return subscriber;
    }
    public void subscribe(Subscriber subscriber) {
        jdbcTemplate.update(CAMPAIGN_SUBSCRIBER_INSERT,
                new Integer[] {Integer.parseInt(subscriber.getNewsletterId()), subscriber.getId()});
    }
}
