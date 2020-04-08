package com.wfdss.nextgen.servicetemplate.repository;

import com.wfdss.nextgen.servicetemplate.model.HelloWorldMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloWorldMessageRepository extends JpaRepository<HelloWorldMessage, Integer> {
}
