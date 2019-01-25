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

import java.util.List;

import com.synectiks.cms.domain.*;
import com.synectiks.cms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.filter.studentattendance.DailyAttendanceVo;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterImpl;
import com.synectiks.cms.filter.studentattendance.StudentAttendanceFilterInput;

/**
 * Resolver for PetClinic Queries
 *
 * @author Nils Hartmann (nils@nilshartmann.net)
 */
@Component
public class Query implements GraphQLQueryResolver {


	private final AcademicYearRepository academicYearRepository;
	private final AttendanceMasterRepository attendanceMasterRepository;
	private final AuthorizedSignatoryRepository authorizedSignatoryRepository;
	private final BankAccountsRepository bankAccountsRepository;
	private final BatchRepository batchRepository;
	private final BranchRepository branchRepository;
	private final CollegeRepository collegeRepository;
	private final DepartmentRepository departmentRepository;
	private final HolidayRepository holidayRepository;
	private final LectureRepository lectureRepository;
//  private final InstituteRepository instituteRepository;
	private final LegalEntityRepository legalEntityRepository;
	private final LocationRepository locationRepository;
	private final SectionRepository sectionRepository;
//    private final SemesterRepository semesterRepository;
    private final StudentRepository studentRepository;
    private final StudentAttendanceRepository studentAttendanceRepository;
//    private final StudentYearRepository studentYearRepository;
    private final SubjectRepository subjectRepository;
    private final TeachRepository teachRepository;
    private final TeacherRepository teacherRepository;
    private final TermRepository termRepository;
    private final StudentSubjectRepository studentSubjectRepository;

    @Autowired
    private StudentAttendanceFilterImpl studentAttendanceFilterImpl;

    public Query(LectureRepository lectureRepository, AttendanceMasterRepository attendanceMasterRepository,TeachRepository teachRepository, BatchRepository batchRepository, StudentRepository studentRepository, CollegeRepository collegeRepository, BranchRepository branchRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository, BankAccountsRepository bankAccountsRepository, DepartmentRepository departmentRepository, LocationRepository locationRepository, StudentAttendanceRepository studentAttendanceRepository, AcademicYearRepository academicYearRepository, HolidayRepository holidayRepository, TermRepository termRepository, StudentSubjectRepository studentSubjectRepository) {
        this.batchRepository = batchRepository;
    	this.studentRepository = studentRepository;
//        this.instituteRepository=instituteRepository;
        this.collegeRepository=collegeRepository;
        this.branchRepository = branchRepository;
//        this.studentYearRepository = studentYearRepository;
//        this.semesterRepository = semesterRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
        this.bankAccountsRepository = bankAccountsRepository;
        this.departmentRepository = departmentRepository;
        this.locationRepository = locationRepository;
        this.studentAttendanceRepository = studentAttendanceRepository;
        this.academicYearRepository = academicYearRepository;
        this.holidayRepository = holidayRepository;
        this.termRepository = termRepository;
        this.teachRepository =  teachRepository;
        this.attendanceMasterRepository = attendanceMasterRepository;
        this.lectureRepository= lectureRepository;
        this.studentSubjectRepository = studentSubjectRepository;
    }

    public Student student(long id)
    {
        return studentRepository.findById(id).get();
    }

    public List<Student> students()
    {
        return Lists.newArrayList(studentRepository.findAll());
    }

    public StudentSubject studentSubject(long id)
    {
        return studentSubjectRepository.findById(id).get();
    }

    public List<StudentSubject> studentSubjects()
    {
        return Lists.newArrayList(studentSubjectRepository.findAll());
    }

   /* public List<Institute> institutes() {
        return Lists.newArrayList(instituteRepository.findAll());
    }

    public Institute institute(int id) {
        return instituteRepository.findById(id);
    }*/

    public College college(long id)
    {
        return collegeRepository.findById(id).get();
    }

    public List<College> colleges()
    {
        return Lists.newArrayList(collegeRepository.findAll());
    }

    public Branch branch(long id)
    {
        return branchRepository.findById(id).get();
    }

    public List<Branch> branches()
    {
        return Lists.newArrayList(branchRepository.findAll());
    }

    public Batch batch(long id)
    {
        return batchRepository.findById(id).get();
    }

    public List<Batch> batches()
    {
        return Lists.newArrayList(batchRepository.findAll());
    }

    /*public StudentYear studentYear(long id)
    {
        return studentYearRepository.findById(id).get();
    }

    public List<StudentYear> studentYears()
    {
        return Lists.newArrayList(studentYearRepository.findAll());
    }*/

   /* public Semester semester(long id)
    {
        return semesterRepository.findById(id).get();
    }

    public List<Semester> semesters()
    {
        return Lists.newArrayList(semesterRepository.findAll());
    }*/

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

    public Department department(long id)
    {
        return departmentRepository.findById(id).get();
    }

    public List<Department> departments()
    {
        return Lists.newArrayList(departmentRepository.findAll());
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

    public Teach teach(long id){
        return teachRepository.findById(id).get();
    }

    public List<Teach> teaches(){
        return Lists.newArrayList(teachRepository.findAll());
    }

    public AttendanceMaster attendanceMaster(long id){
        return attendanceMasterRepository.findById(id).get();
    }

    public List<AttendanceMaster> attendanceMasters(){
        return Lists.newArrayList(attendanceMasterRepository.findAll());
    }

    public Lecture lecture(long id){
        return lectureRepository.findById(id).get();
    }

    public List<Lecture> lectures(){
        return Lists.newArrayList(lectureRepository.findAll());
    }

    public List<DailyAttendanceVo> getDailyStudentAttendance(StudentAttendanceFilterInput filter) {
    	return Lists.newArrayList(studentAttendanceFilterImpl.getStudenceAttendance(filter));
    }

}
