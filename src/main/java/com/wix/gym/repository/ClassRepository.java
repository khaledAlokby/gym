package com.wix.gym.repository;

import com.wix.gym.model.Class;
import com.wix.gym.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public interface ClassRepository extends JpaRepository<Class, Long> {
    List<Class> findByName(String name);
}
