package com.adidas.subscriptionservice.services;

import com.adidas.subscriptionservice.dao.SubscriptionDAO;
import com.adidas.subscriptionservice.exceptions.InvalidRequestException;
import com.adidas.subscriptionservice.exceptions.InvalidUserInputException;
import com.adidas.subscriptionservice.models.Subscriber;
import org.apache.commons.validator.DateValidator;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Locale;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionDAO subscriptionDAO;

    @Transactional
    public Subscriber subscribeToNewsletter(Subscriber subscriber) throws InvalidRequestException, InvalidUserInputException {
        this.validateRequestInput(subscriber);
        this.validateSubscriberInput(subscriber);
        subscriber = subscriptionDAO.createSubscriber(subscriber);
        subscriptionDAO.subscribe(subscriber);
        return subscriber;
    }

    protected void validateRequestInput(Subscriber subscriber) throws InvalidRequestException {
        try {
            Assert.notNull(subscriber.getNewsletterId(), "Subscriber Newsletter Campaign is mandatory!");
            try{
                Integer.parseInt(subscriber.getNewsletterId());
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("Subscriber Newsletter Campaign has a wrong format!");
            }
        } catch (IllegalArgumentException e){
            throw new InvalidRequestException(e.getMessage());
        }
    }

    protected void validateSubscriberInput(Subscriber subscriber) throws InvalidUserInputException {
        try {
            Assert.notNull(subscriber.getEmail(), "Subscriber email is mandatory!");
            Assert.hasText(subscriber.getEmail(), "Subscriber email is mandatory!");
            Assert.isTrue(EmailValidator.getInstance().isValid(subscriber.getEmail()), "Invalid Subscriber email!");

            Assert.notNull(subscriber.getDateOfBirth(), "Subscriber Date of Birth is mandatory!");
            Assert.hasText(subscriber.getDateOfBirth(), "Subscriber Date of Birth is mandatory!");

            Assert.isTrue(subscriber.isConsentGranted(), "Subscriber must consenst Newsletter subscription!");

        } catch (IllegalArgumentException e){
            throw new InvalidUserInputException(e.getMessage());
        }
    }
}
