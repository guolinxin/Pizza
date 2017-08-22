package com.yell.pizza;

import com.yell.pizza.service.PizzaService;
import org.apache.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;


/**
 * Springboot project to print pizza menu from json input
 */
@SpringBootApplication
public class PizzaApplication implements CommandLineRunner {
    private static final Logger LOGGER = Logger.getLogger(PizzaApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(PizzaApplication.class, args);
    }

    @Override
    public void run(String... strings) throws Exception {

        LOGGER.info("Application started with command-line arguments: {} . \n To kill this application, press Ctrl + C." );

        // read data from file
        File pizzaFile = ResourceUtils.getFile("classpath:data/pizzas.json");
        File recipesFile = ResourceUtils.getFile("classpath:data/recipes.json");
        String pizzasJson = new String(Files.readAllBytes(pizzaFile.toPath()));
        String recipesJson = new String(Files.readAllBytes(recipesFile.toPath()));
        PizzaService pizzaService = new PizzaService();

        // print menu
        pizzaService.printMenu(pizzasJson, recipesJson);

    }
}
