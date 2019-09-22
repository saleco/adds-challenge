package com.adidas.publicservice.resources;

import com.adidas.publicservice.models.Subscriber;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.StringTokenizer;

@RestController
@RequestMapping("api")
@Api(value = "Public Resources API")
public class PublicResource {

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "Customer newsletter subscription Public API")
    @PostMapping("/newsletter/subscribe")
    public ResponseEntity subscribeToNewsletter(@RequestBody Subscriber subscriber){
            ResponseEntity<Subscriber> responseEntity = restTemplate.postForEntity("http://subscription-service/subscriptions/subscribe", subscriber, Subscriber.class);
            if(responseEntity.getStatusCode().equals(HttpStatus.OK) && !responseEntity.getHeaders().containsKey("error")){
                restTemplate.getForEntity("http://email-service/emails/subscription/send/" + subscriber.getEmail() + "/" + subscriber.getNewsletterId(), Object.class);
                return responseEntity;
            } else {
                return new ResponseEntity<Object>(responseEntity.getHeaders().get("errors"), HttpStatus.BAD_REQUEST);
            }
    }
}
