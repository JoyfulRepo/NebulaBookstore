package ebookstore.com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

import ebookstore.com.demo.book.*;
import ebookstore.com.demo.customer.*;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/customers")
    public List<Customer> customer1() {
        return List.of(new Customer(
                "John Smith",
                CustomerGender.MALE,
                "john01@gmail.com",
                LocalDate.of(2020, 10, 20),
                "0901558999",
                "johnOP10",
                "ks2s1u@)nf7!"));
    }

    @GetMapping("/books")
    public List<Book> book1() {
        return List.of(new Book(
                "Harry Potter",
                80.0,
                BookStatus.AVAILABLE,
                10,
                LocalDate.of(2005, 10, 20),
                "www.harrypotter.com",
                "A wizard kid"));
    }
}