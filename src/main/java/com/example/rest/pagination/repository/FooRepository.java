package com.example.rest.pagination.repository;

import com.example.rest.pagination.entity.Foo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface FooRepository extends CrudRepository<Foo, Long> {

    Page<Foo> findAll(Pageable pageable);

}
