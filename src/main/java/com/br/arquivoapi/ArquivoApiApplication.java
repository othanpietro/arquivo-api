package com.br.arquivoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@SpringBootApplication
public class ArquivoApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquivoApiApplication.class, args);
    }

}
