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
import com.synectiks.cms.graphql.types.Holiday.AddHolidayPayload;
import com.synectiks.cms.model.Institute;
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

    private final StudentRepository studentRepository;
    private final InstituteRepository instituteRepository;
    private final CollegeRepository collegeRepository;
    private final CollegeBranchesRepository collegeBranchesRepository;
    private final StudentYearRepository studentYearRepository;
    private final SemesterRepository semesterRepository;
    private final PeriodsRepository periodsRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
    private final BankAccountsRepository bankAccountsRepository;
    private final DepartmentsRepository departmentsRepository;
    private final LocationRepository locationRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
    private final AcademicDepartmentRepository academicDepartmentRepository;
    private final AcademicSubjectRepository academicSubjectRepository;
    private final AcademicYearRepository academicYearRepository;
    private final HolidayRepository holidayRepository;
    private final TermRepository termRepository;


    public Query(StudentRepository studentRepository, InstituteRepository instituteRepository, CollegeRepository collegeRepository, CollegeBranchesRepository collegeBranchesRepository, StudentYearRepository studentYearRepository, SemesterRepository semesterRepository, PeriodsRepository periodsRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentsRepository departmentsRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicDepartmentRepository academicDepartmentRepository, AcademicSubjectRepository academicSubjectRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository=instituteRepository;
        this.collegeRepository=collegeRepository;
        this.collegeBranchesRepository = collegeBranchesRepository;
        this.studentYearRepository = studentYearRepository;
        this.semesterRepository = semesterRepository;
        this.periodsRepository = periodsRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentsRepository = departmentsRepository;
        this.locationRepository = locationRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicDepartmentRepository = academicDepartmentRepository;
        this.academicSubjectRepository = academicSubjectRepository;
        this.academicYearRepository = academicYearRepository;
        this.holidayRepository = holidayRepository;
        this.termRepository = termRepository;
    }

    public Student student(long id)
    {
        return studentRepository.findById(id).get();
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
        return collegeRepository.findById(id).get();
    }

    public List<College> colleges()
    {
        return Lists.newArrayList(collegeRepository.findAll());
    }

    public CollegeBranches collegeBranch(long id)
    {
        return collegeBranchesRepository.findById(id).get();
    }

    public List<CollegeBranches> collegeBranches()
    {
        return Lists.newArrayList(collegeBranchesRepository.findAll());
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
    {
        return subjectRepository.findById(id).get();
    }

    public List<Subject> subjects()
    {
        return Lists.newArrayList(subjectRepository.findAll());
    }

    public Teacher teacher(long id)
    {
        return teacherRepository.findById(id).get();
    }

    public List<Teacher> teachers()
    {
        return Lists.newArrayList(teacherRepository.findAll());
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

    public BankAccounts bankAccount(long id)
    {
        return bankAccountsRepository.findById(id).get();
    }

    public List<BankAccounts> bankAccounts()
    {
        return Lists.newArrayList(bankAccountsRepository.findAll());
    }

    public Departments department(long id)
    {
        return departmentsRepository.findById(id).get();
    }

    public List<Departments> departments()
    {
        return Lists.newArrayList(departmentsRepository.findAll());
    }

    public Location location(long id)
    {
        return locationRepository.findById(id).get();
    }

    public List<Location> locations()
    {
        return Lists.newArrayList(locationRepository.findAll());
    }

    public StudentAttendance studentAttendance(long id)
    {
        return studentAttendanceRepository.findById(id).get();
    }

    public List<StudentAttendance> studentAttendances()
    {
        return Lists.newArrayList(studentAttendanceRepository.findAll());
    }

    public AcademicDepartment academicDepartment(long id)
    {
        return academicDepartmentRepository.findById(id).get();
    }

    public List<AcademicDepartment> academicDepartments()
    {
        return Lists.newArrayList(academicDepartmentRepository.findAll());
    }

    public AcademicSubject academicSubject(long id)
    {
        return academicSubjectRepository.findById(id).get();
    }

    public List<AcademicSubject> academicSubjects()
    {
        return Lists.newArrayList(academicSubjectRepository.findAll());
    }

    public List<AcademicYear> academicYears()
    {
        return Lists.newArrayList(academicYearRepository.findAll());
    }

    public AcademicYear academicYear(long id)
    {
        return academicYearRepository.findById(id).get();
    }

    public List<Holiday> holidays()
    {
        return Lists.newArrayList(holidayRepository.findAll());
    }

    public Holiday holiday(long id)
    {
        return holidayRepository.findById(id).get();
    }


    public List<Term> terms()
    {
        return Lists.newArrayList(termRepository.findAll());
    }

    public Term term(long id)
    {
        return termRepository.findById(id).get();
    }
}
