package com.example.temporal;

import com.example.temporal.workers.BureauWorker;
import com.example.temporal.workers.SignalWorker;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Author - lakshya.jain <br>
 * Date - 04/07/2023
 */

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @PostConstruct
    public void postConstruct(){
        //BureauWorker.main();
        SignalWorker.main();
    }

}
