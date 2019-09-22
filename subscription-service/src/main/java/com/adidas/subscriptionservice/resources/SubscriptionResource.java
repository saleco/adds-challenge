package com.adidas.subscriptionservice.resources;

import com.adidas.subscriptionservice.exceptions.InvalidRequestException;
import com.adidas.subscriptionservice.exceptions.InvalidUserInputException;
import com.adidas.subscriptionservice.models.Subscriber;
import com.adidas.subscriptionservice.services.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Subscriptions API")
@RequestMapping("/subscriptions")
public class SubscriptionResource {


    @Autowired
    private SubscriptionService newsletterService;


    @PostMapping("/subscribe")
    @ApiOperation(value = "Subscribes a customer to a given Newsletter campaign.")
    public ResponseEntity<Subscriber> subscribeToNewsletter(@RequestBody Subscriber subscriber){
        try{
            subscriber = newsletterService.subscribeToNewsletter(subscriber);

            return ResponseEntity.ok(subscriber);

        } catch (InvalidUserInputException | InvalidRequestException e) {
            return ResponseEntity.ok().header("error", e.getMessage()).build();
        }

    }
}
