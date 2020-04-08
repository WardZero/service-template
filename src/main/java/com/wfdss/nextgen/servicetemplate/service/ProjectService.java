package com.wfdss.nextgen.servicetemplate.service;

import com.wfdss.nextgen.servicetemplate.model.Project;
import com.wfdss.nextgen.servicetemplate.repository.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    /**
     * Returns a Slice of all Project objects in the database.
     *
     * @return Slice<Project> objects
     */
    public Slice<Project> findAll(Pageable pageable) {
        return projectRepository.findAll(pageable);
    }
}
