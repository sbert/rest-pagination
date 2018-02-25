package com.example.rest.pagination.controller;

import com.example.rest.pagination.entity.Foo;
import com.example.rest.pagination.exception.InvalidRangeException;
import com.example.rest.pagination.repository.FooRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*


              MAUVAISE PRATIQUE

              A NE PAS IMPLEMENTER

*/


@RestController
@RequestMapping("page/foos")
public class PageFooController {

    @Value("${foo.page.size}")
    private int size;

    @Autowired
    private FooRepository fooRepository;

    @GetMapping
    public ResponseEntity<Page<Foo>> findAll() {
        return findPaginated(0);
    }


    @GetMapping(value = "", params = "page")
    public ResponseEntity<Page<Foo>> findPaginated(@RequestParam int page) {
        Page<Foo> fooPage = fooRepository.findAll(new PageRequest(page, size));

        if (page > fooPage.getTotalPages() || page < 0) {
            throw new InvalidRangeException("Requested page not satisfiable");
        } else {
            return new ResponseEntity<>(fooPage, HttpStatus.PARTIAL_CONTENT);
        }
    }

}
