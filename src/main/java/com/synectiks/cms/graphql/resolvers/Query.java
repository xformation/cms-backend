/*
 * Copyright 2016-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;

import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.model.Institute;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.InstituteRepository;
import com.synectiks.cms.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolver for PetClinic Queries
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Component
public class Query implements GraphQLQueryResolver {

    private final StudentRepository studentRepository;
    private final InstituteRepository instituteRepository;
    public final CollegeRepository collegeRepository;
    public Query(StudentRepository studentRepository, InstituteRepository instituteRepository, CollegeRepository collegeRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository=instituteRepository;
        this.collegeRepository=collegeRepository;
    }

    public Student student(long id)
    {
        return studentRepository.getOne(id);
    }

    public List<Student> students()
    {
        return Lists.newArrayList(studentRepository.findAll());
    }

    public List<Institute> institutes() {
        return Lists.newArrayList(instituteRepository.findAll());
    }

    public Institute institute(int id) {
        return instituteRepository.findById(id);
    }

    public College college(long id)
    {
        return collegeRepository.getOne(id);
    }

    public List<College> colleges()
    {
        return Lists.newArrayList(collegeRepository.findAll());
    }


}
