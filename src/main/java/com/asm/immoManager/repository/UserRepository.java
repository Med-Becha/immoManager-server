package com.asm.immoManager.repository;

import org.springframework.data.repository.CrudRepository;

import com.asm.immoManager.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {

}