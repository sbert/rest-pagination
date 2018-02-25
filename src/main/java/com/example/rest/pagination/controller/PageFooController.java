package com.example.rest.pagination.controller;

import com.example.rest.pagination.entity.Foo;
import com.example.rest.pagination.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
@RequestMapping("page/foos")
public class PageFooController {

    @Value("${foo.page.size}")
    private int size;

    @Autowired
    private FooRepository fooRepository;

    @GetMapping
    public Page<Foo> findAll() {
        return findPaginated(0);
    }


    @GetMapping(value = "", params = "page")
    public Page<Foo> findPaginated(@RequestParam int page) {
        PageRequest pageRequest = new PageRequest(page, size);
        return fooRepository.findAll(pageRequest);
    }

}
