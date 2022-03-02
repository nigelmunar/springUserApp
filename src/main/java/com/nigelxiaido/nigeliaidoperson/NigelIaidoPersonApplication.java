package com.nigelxiaido.nigeliaidoperson;

import com.nigelxiaido.nigeliaidoperson.exceptions.CustomGlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@Import(CustomGlobalExceptionHandler.class)
public class NigelIaidoPersonApplication {
    public static void main(String[] args) { SpringApplication.run(NigelIaidoPersonApplication.class, args); }
}
