package com.adidas.emailservice.resources;

import com.adidas.emailservice.model.EmailDetails;
import com.adidas.emailservice.services.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "Emails API")
@RequestMapping("/emails")
public class EmailResource {

    @Autowired
    private EmailService emailService;

    @ApiOperation(value = "Sends a campaign email for the given subscriber email")
    @GetMapping("/subscription/send/{subscriberEmail}/{campaignId}")
    public ResponseEntity sendSubscriptionEmail(@PathVariable(name = "subscriberEmail") String subscriberEmail, @PathVariable(name = "campaignId") Integer campaignId){
        try {
            EmailDetails emailDetails = new EmailDetails();
            emailDetails.setEmailBody("Welcome to Adidas Newsletter Campaign #" + campaignId);
            emailDetails.setEmailSubject("Adidas Newsletter");
            emailDetails.setFrom("salloszraj@gmail.com");
            emailDetails.setEmailTo(subscriberEmail);
            emailService.sendNotificaitoin(emailDetails);
            return ResponseEntity.ok().build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
