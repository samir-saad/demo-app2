package com.bell.soc.demoapp2.api;

import com.bell.soc.demoapp2.dto.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;


@RestController
@RequestMapping("api/v1/users")
public class UsersController {

    private final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

    private static final String TEMPLATE = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        name = name.replace( '\n' ,  '_' ).replace( '\r' , '_' ).replace( '\t' , '_' );
        logger.info("Greeting {}", name);
        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE, name));
    }
}
