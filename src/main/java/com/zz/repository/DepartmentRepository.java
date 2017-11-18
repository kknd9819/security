package com.zz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zz.model.Deparment;

@Repository
public interface DepartmentRepository extends JpaRepository<Deparment, Long>{

}
