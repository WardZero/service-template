package com.wfdss.nextgen.servicetemplate.repository;

import com.wfdss.nextgen.servicetemplate.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
