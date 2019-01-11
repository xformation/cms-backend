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

import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Resolver for PetClinic Queries
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Component
public class Query implements GraphQLQueryResolver {

<<<<<<< HEAD

    private final LocationRepository locationRepository;
=======
    private final StudentRepository studentRepository;
    private final InstituteRepository instituteRepository;
    private final CollegeRepository collegeRepository;
    private final BranchRepository branchRepository;
    private final StudentYearRepository studentYearRepository;
    private final SemesterRepository semesterRepository;
    private final PeriodsRepository periodsRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
>>>>>>> c8d5b007289a6c93fd618448714fb1032fc459ba
    private final LegalEntityRepository legalEntityRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;

<<<<<<< HEAD
    public Query(LocationRepository locationRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository) {
        this.locationRepository = locationRepository;
=======
    public Query(StudentRepository studentRepository, InstituteRepository instituteRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, StudentYearRepository studentYearRepository, SemesterRepository semesterRepository, PeriodsRepository periodsRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentsRepository departmentsRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicDepartmentRepository academicDepartmentRepository, AcademicSubjectRepository academicSubjectRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository=instituteRepository;
        this.collegeRepository=collegeRepository;
        this.branchRepository = branchRepository;
        this.studentYearRepository = studentYearRepository;
        this.semesterRepository = semesterRepository;
        this.periodsRepository = periodsRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
>>>>>>> c8d5b007289a6c93fd618448714fb1032fc459ba
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
    }


<<<<<<< HEAD

    public Location location(long id)
=======
    public Branch branch(long id)
    {
        return branchRepository.findById(id).get();
    }

    public List<Branch> branches()
    {
        return Lists.newArrayList(branchRepository.findAll());
    }

    public StudentYear studentYear(long id)
    {
        return studentYearRepository.findById(id).get();
    }

    public List<StudentYear> studentYears()
    {
        return Lists.newArrayList(studentYearRepository.findAll());
    }

    public Semester semester(long id)
    {
        return semesterRepository.findById(id).get();
    }

    public List<Semester> semesters()
    {
        return Lists.newArrayList(semesterRepository.findAll());
    }

    public Periods period(long id)
    {
        return periodsRepository.findById(id).get();
    }

    public List<Periods> periods()
    {
        return Lists.newArrayList(periodsRepository.findAll());
    }

    public Section section(long id)
    {
        return sectionRepository.findById(id).get();
    }

    public List<Section> sections()
    {
        return Lists.newArrayList(sectionRepository.findAll());
    }

    public Subject subject(long id)
>>>>>>> c8d5b007289a6c93fd618448714fb1032fc459ba
    {
        return locationRepository.findById(id).get();
    }

    public List<Location> locations()
    {
        return Lists.newArrayList(locationRepository.findAll());
    }



    public LegalEntity legalEntity(long id)
    {
        return legalEntityRepository.findById(id).get();
    }

    public List<LegalEntity> legalEntities()
    {
        return Lists.newArrayList(legalEntityRepository.findAll());
    }


    public AuthorizedSignatory authorizedSignatory(long id)
    {
        return authorizedSignatoryRepository.findById(id).get();
    }

    public List<AuthorizedSignatory> authorizedSignatories()
    {
        return Lists.newArrayList(authorizedSignatoryRepository.findAll());
    }

}
