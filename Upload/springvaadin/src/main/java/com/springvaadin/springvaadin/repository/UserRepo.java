package com.springvaadin.springvaadin.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springvaadin.springvaadin.model.User;


@Repository
public interface UserRepo extends PagingAndSortingRepository<User, Long>{

}
