package com.example.rest.pagination;

import com.example.rest.pagination.entity.Foo;
import com.example.rest.pagination.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.stream.IntStream;

@SpringBootApplication
public class RestPaginationApplication {

	@Autowired
	private FooRepository fooRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestPaginationApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void populateData() {
        IntStream.range(1, 95).forEach(i -> {
            fooRepository.save(new Foo(i));
        });;
    }
}
