package com.example.rest.pagination.controller;

import com.example.rest.pagination.entity.Foo;
import com.example.rest.pagination.exception.InvalidRangeException;
import com.example.rest.pagination.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("foos")
public class FooController {

    @Value("${foo.page.size}")
    private int size;

    @Autowired
    private FooRepository fooRepository;

    @GetMapping
    public ResponseEntity<List<Foo>> findAll() {
        return findPaginated(0);
    }


    @GetMapping(value = "", params = "page")
    public ResponseEntity<List<Foo>> findPaginated(@RequestParam int page) {
        Page<Foo> fooPage = fooRepository.findAll(new PageRequest(page, size));

        if (page > fooPage.getTotalPages() || page < 0) {
            throw new InvalidRangeException("Requested page not satisfiable");
        } else {
            HttpHeaders headers = new HttpHeaders();
            headers.add("number", String.valueOf(fooPage.getNumber()));
            headers.add("numberOfElements", String.valueOf(fooPage.getNumberOfElements()));
            headers.add("size", String.valueOf(fooPage.getSize()));
            headers.add("totalElements", String.valueOf(fooPage.getTotalElements()));
            headers.add("totalPages", String.valueOf(fooPage.getTotalPages()));
            headers.add("first", String.valueOf(fooPage.isFirst()));
            headers.add("last", String.valueOf(fooPage.isLast()));

            return new ResponseEntity<>(fooPage.getContent(), headers, HttpStatus.PARTIAL_CONTENT);
        }

    }

}
