package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.domain.*;
import com.synectiks.cms.domain.Semester;
import com.synectiks.cms.graphql.types.AuthorizedSignatory.*;
import com.synectiks.cms.graphql.types.College.*;
import com.synectiks.cms.graphql.types.CollegeBranches.*;
import com.synectiks.cms.graphql.types.Institute.*;
import com.synectiks.cms.graphql.types.LegalEntity.*;
import com.synectiks.cms.graphql.types.Periods.*;
import com.synectiks.cms.graphql.types.Section.*;
import com.synectiks.cms.graphql.types.Semester.*;
import com.synectiks.cms.graphql.types.Student.*;
import com.synectiks.cms.graphql.types.StudentYear.*;
import com.synectiks.cms.graphql.types.Subject.*;
import com.synectiks.cms.graphql.types.Teacher.*;
import com.synectiks.cms.model.Institute;
import com.synectiks.cms.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


@Component
public class Mutation implements GraphQLMutationResolver {

    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);
    private final InstituteRepository instituteRepository;
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;
    private final StudentYearRepository studentYearRepository;
    private final SemesterRepository semesterRepository;
    private final CollegeBranchesRepository collegeBranchesRepository;
    private final PeriodsRepository periodsRepository;
    private final SectionRepository sectionRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final LegalEntityRepository legalEntityRepository;
    private final AuthorizedSignatoryRepository authorizedSignatoryRepository;

    public Mutation(StudentRepository studentRepository, InstituteRepository instituteRepository, CollegeRepository collegeRepository, StudentYearRepository studentYearRepository, SemesterRepository semesterRepository, CollegeBranchesRepository collegeBranchesRepository, PeriodsRepository periodsRepository, SectionRepository sectionRepository, SubjectRepository subjectRepository, TeacherRepository teacherRepository, LegalEntityRepository legalEntityRepository, AuthorizedSignatoryRepository authorizedSignatoryRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
        this.collegeRepository = collegeRepository;
        this.studentYearRepository = studentYearRepository;
        this.semesterRepository = semesterRepository;
        this.collegeBranchesRepository = collegeBranchesRepository;
        this.periodsRepository = periodsRepository;
        this.sectionRepository = sectionRepository;
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.legalEntityRepository = legalEntityRepository;
        this.authorizedSignatoryRepository = authorizedSignatoryRepository;
    }

    public AddStudentPayload addStudent(AddStudentInput addStudentInput) {
        final Teacher teacher = teacherRepository.findById(addStudentInput.getTeacherId()).get();
        final Student student = new Student();
        student.setsName(addStudentInput.getsName());
        student.setAttendance(addStudentInput.getAttendance());
        student.setElectiveSub(addStudentInput.getElectiveSub());
        student.setTeacher(teacher);
        studentRepository.save(student);
       return new AddStudentPayload(student);
    }

    public UpdateStudentPayload updateStudent(UpdateStudentInput updateStudentInput) {
        Student student = studentRepository.findById(updateStudentInput.getId()).get();
        if (updateStudentInput.getsName() != null) {
            student.setsName(updateStudentInput.getsName());
        }
        if (updateStudentInput.getAttendance() != null) {
            student.setAttendance(updateStudentInput.getAttendance());
        }

        if (updateStudentInput.getElectiveSub() != null) {
            student.setElectiveSub(updateStudentInput.getElectiveSub());
        }
        studentRepository.save(student);

        return new UpdateStudentPayload(student);
    }

    public RemoveStudentPayload removeStudent(RemoveStudentInput removeStudentInput) {
        Student student  = studentRepository.findById(removeStudentInput.getStudentId()).get();
        studentRepository.delete(student);
        return new RemoveStudentPayload(Lists.newArrayList(studentRepository.findAll()));
    }


    public AddInstitutePayload addInstitute(AddInstituteInput addInstituteInput) {
        final Institute institute = new Institute();
        institute.setCode(addInstituteInput.getCode());
        institute.setName(addInstituteInput.getName());
        institute.setYear(addInstituteInput.getYear());

        instituteRepository.save(institute);

        return new AddInstitutePayload(institute);
    }

    public AddStudentYearPayload addStudentYear(AddStudentYearInput addStudentYearInput) {
        final StudentYear studentYear = new StudentYear();
        studentYear.setsYear(addStudentYearInput.getsYear());
        studentYearRepository.save(studentYear);

        return new AddStudentYearPayload(studentYear);
    }

    public UpdateStudentYearPayload updateStudentYear(UpdateStudentYearInput updateStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(updateStudentYearInput.getId()).get();
        if (updateStudentYearInput.getsYear() != null) {
            studentYear.setsYear(updateStudentYearInput.getsYear());
        }

        studentYearRepository.save(studentYear);

        return new UpdateStudentYearPayload(studentYear);
    }

    public RemoveStudentYearPayload removeStudentYear(RemoveStudentYearInput removeStudentYearInput) {
        StudentYear studentYear = studentYearRepository.findById(removeStudentYearInput.getStudentYearId()).get();
        studentYearRepository.delete(studentYear);

        return new RemoveStudentYearPayload(Lists.newArrayList(studentYearRepository.findAll()));
    }

    public AddSemesterPayload addSemester(AddSemesterInput addSemesterInput) {
        final Semester semester = new Semester();
        semester.setSem(addSemesterInput.getSem());
        semesterRepository.save(semester);

        return new AddSemesterPayload(semester);
    }

    public UpdateSemesterPayload updateSemester(UpdateSemesterInput updateSemesterInput) {
        Semester semester = semesterRepository.findById(updateSemesterInput.getId()).get();
        if (updateSemesterInput.getSem() != null) {
            semester.setSem(updateSemesterInput.getSem());
        }

        semesterRepository.save(semester);

        return new UpdateSemesterPayload(semester);
    }

    public RemoveSemesterPayload removeSemester(RemoveSemesterInput removeSemesterInput) {
        Semester semester  = semesterRepository.findById(removeSemesterInput.getSemesterId()).get();
        semesterRepository.delete(semester);
        return new RemoveSemesterPayload(Lists.newArrayList(semesterRepository.findAll()));
    }


    public UpdateInstitutePayload updateInstitute(UpdateInstituteInput updateInstituteInput) {
        Institute institute = instituteRepository.findById(updateInstituteInput.getInstituteId());
        if (updateInstituteInput.getCode() != null) {
            institute.setCode(updateInstituteInput.getCode());
        }

        if (updateInstituteInput.getName() != null) {
            institute.setName(updateInstituteInput.getName());
        }

        if (updateInstituteInput.getYear() != null) {
            institute.setYear(updateInstituteInput.getYear());
        }

        instituteRepository.save(institute);

        return new UpdateInstitutePayload(institute);
    }

    public RemoveInstitutePayload removeInstitute(RemoveInstituteInput removeInstituteInput) {
        Institute institute = instituteRepository.findById(removeInstituteInput.getInstituteId());
        instituteRepository.delete(institute);

        return new RemoveInstitutePayload(Lists.newArrayList(instituteRepository.findAll()));
    }

    public AddCollegePayload addCollege(AddCollegeInput addCollegeInput) {
        final College college = new College();
        college.setShortName(addCollegeInput.getShortName());
        college.setLogo(addCollegeInput.getLogo());
        college.setBackgroundImage(addCollegeInput.getBackgroundImage());
        college.setInstructionInformation(addCollegeInput.getInstructionInformation());

        collegeRepository.save(college);

        return new AddCollegePayload(college);
    }

    public UpdateCollegePayload updateCollege(UpdateCollegeInput updateCollegeInput) {
        College college = collegeRepository.findById(updateCollegeInput.getId()).get();
        if (updateCollegeInput.getShortName() != null) {
            college.setShortName(updateCollegeInput.getShortName());
        }

        if (updateCollegeInput.getLogo() != null) {
            college.setLogo(updateCollegeInput.getLogo());
        }

        if (updateCollegeInput.getBackgroundImage() != null) {
            college.setBackgroundImage(updateCollegeInput.getBackgroundImage());
        }

        if (updateCollegeInput.getInstructionInformation() != null) {
            college.setInstructionInformation(updateCollegeInput.getInstructionInformation());
        }

        collegeRepository.save(college);

        return new UpdateCollegePayload(college);
    }

    public RemoveCollegePayload removeCollege(RemoveCollegeInput removeCollegeInput) {
        College college = collegeRepository.getOne(removeCollegeInput.getCollegeId());
        collegeRepository.delete(college);

        return new RemoveCollegePayload(Lists.newArrayList(collegeRepository.findAll()));
    }

    public AddCollegeBranchesPayload addCollegeBranches(AddCollegeBranchesInput addCollegeBranchesInput) {
        final CollegeBranches collegeBranches = new CollegeBranches();
        collegeBranches.setBranchName(addCollegeBranchesInput.getBranchName());
        collegeBranches.setDescription(addCollegeBranchesInput.getDescription());
        collegeBranches.setCollegeHead(addCollegeBranchesInput.getCollegeHead());
        collegeBranchesRepository.save(collegeBranches);

        return new AddCollegeBranchesPayload(collegeBranches);
    }

    public UpdateCollegeBranchesPayload updateCollegeBranches(UpdateCollegeBranchesInput updateCollegeBranchesInput) {
        CollegeBranches collegeBranches = collegeBranchesRepository.findById(updateCollegeBranchesInput.getId()).get();
        if (updateCollegeBranchesInput.getBranchName() != null) {
            collegeBranches.setBranchName(updateCollegeBranchesInput.getBranchName());
        }

        if (updateCollegeBranchesInput.getDescription() != null) {
            collegeBranches.setDescription(updateCollegeBranchesInput.getDescription());
        }

        if (updateCollegeBranchesInput.getCollegeHead() != null) {
            collegeBranches.setCollegeHead(updateCollegeBranchesInput.getCollegeHead());
        }
        collegeBranchesRepository.save(collegeBranches);

        return new UpdateCollegeBranchesPayload(collegeBranches);
    }

    public RemoveCollegeBranchesPayload removeCollegeBranches(RemoveCollegeBranchesInput removeCollegeBranchesInput) {
        CollegeBranches collegeBranches  = collegeBranchesRepository.findById(removeCollegeBranchesInput.getCollegeBranchesId()).get();
        collegeBranchesRepository.delete(collegeBranches);
        return new RemoveCollegeBranchesPayload(Lists.newArrayList(collegeBranchesRepository.findAll()));
    }

    public AddPeriodsPayload addPeriods(AddPeriodsInput addPeriodsInput) {
        final Section section = sectionRepository.findById(addPeriodsInput.getSectionId()).get();
        final Periods periods = new Periods();
        periods.setPeriods(addPeriodsInput.getPeriods());
        periods.setSection(section);
        periodsRepository.save(periods);

        return new AddPeriodsPayload(periods);
    }

    public UpdatePeriodsPayload updatePeriods(UpdatePeriodsInput updatePeriodsInput) {
        Periods periods = periodsRepository.findById(updatePeriodsInput.getId()).get();
        if (updatePeriodsInput.getPeriods() != null) {
            periods.setPeriods(updatePeriodsInput.getPeriods());
        }

        periodsRepository.save(periods);

        return new UpdatePeriodsPayload(periods);
    }

    public RemovePeriodsPayload removePeriods(RemovePeriodsInput removePeriodsInput) {
        Periods periods  = periodsRepository.findById(removePeriodsInput.getPeriodsId()).get();
        periodsRepository.delete(periods);
        return new RemovePeriodsPayload(Lists.newArrayList(periodsRepository.findAll()));
    }

    public AddSectionPayload addSection(AddSectionInput addSectionInput) {
        final StudentYear studentYear = studentYearRepository.findById(addSectionInput.getStudentYearId()).get();
        final Section section = new Section();
        section.setSection(addSectionInput.getSection());
        section.setStudentyear(studentYear);
        sectionRepository.save(section);

        return new AddSectionPayload(section);
    }

    public UpdateSectionPayload updateSection(UpdateSectionInput updateSectionInput) {
        Section section = sectionRepository.findById(updateSectionInput.getId()).get();
        if (updateSectionInput.getSection() != null) {
            section.setSection(updateSectionInput.getSection());
        }
        sectionRepository.save(section);

        return new UpdateSectionPayload(section);
    }

    public RemoveSectionPayload removeSection(RemoveSectionInput removeSectionInput) {
        Section section  = sectionRepository.findById(removeSectionInput.getSectionId()).get();
        sectionRepository.delete(section);
        return new RemoveSectionPayload(Lists.newArrayList(sectionRepository.findAll()));
    }

    public AddSubjectPayload addSubject(AddSubjectInput addSubjectInput) {
        final Periods periods = periodsRepository.findById(addSubjectInput.getPeriodsId()).get();
        final Teacher teacher = teacherRepository.findById(addSubjectInput.getTeacherId()).get();
        final Student student = studentRepository.findById(addSubjectInput.getStudentId()).get();

        final Subject subject = new Subject();
        subject.setCommonSub(addSubjectInput.getCommonSub());
        subject.setElectiveSub(addSubjectInput.getElectiveSub());
        subject.setPeriods(periods);
        subject.setStudent(student);
        subject.setTeacher(teacher);

        subjectRepository.save(subject);

        return new AddSubjectPayload(subject);
    }

    public UpdateSubjectPayload updateSubject(UpdateSubjectInput updateSubjectInput) {
        Subject subject = subjectRepository.findById(updateSubjectInput.getId()).get();
        if (updateSubjectInput.getCommonSub() != null) {
            subject.setCommonSub(updateSubjectInput.getCommonSub());
        }
        if (updateSubjectInput.getElectiveSub() != null) {
            subject.setElectiveSub(updateSubjectInput.getElectiveSub());
        }

        subjectRepository.save(subject);

        return new UpdateSubjectPayload(subject);
    }

    public RemoveSubjectPayload removeSubject(RemoveSubjectInput removeSubjectInput) {
        Subject subject  = subjectRepository.findById(removeSubjectInput.getSubjectId()).get();
        subjectRepository.delete(subject);
        return new RemoveSubjectPayload(Lists.newArrayList(subjectRepository.findAll()));
    }

    public AddTeacherPayload addTeacher(AddTeacherInput addTeacherInput) {
        final Periods periods = periodsRepository.findById(addTeacherInput.getPeriodsId()).get();
        final Teacher teacher = new Teacher();
        teacher.settName(addTeacherInput.gettName());
        teacher.setPeriods(periods);

        teacherRepository.save(teacher);

        return new AddTeacherPayload(teacher);
    }

    public UpdateTeacherPayload updateTeacher(UpdateTeacherInput updateTeacherInput) {
        Teacher teacher = teacherRepository.findById(updateTeacherInput.getId()).get();
        if (updateTeacherInput.gettName() != null) {
            teacher.settName(updateTeacherInput.gettName());
        }
        teacherRepository.save(teacher);

        return new UpdateTeacherPayload(teacher);
    }

    public RemoveTeacherPayload removeTeacher(RemoveTeacherInput removeTeacherInput) {
        Teacher teacher  = teacherRepository.findById(removeTeacherInput.getTeacherId()).get();
        teacherRepository.delete(teacher);
        return new RemoveTeacherPayload(Lists.newArrayList(teacherRepository.findAll()));
    }

    public AddAuthorizedSignatoryPayload addAuthorizedSignatory(AddAuthorizedSignatoryInput addAuthorizedSignatoryInput) {
        final AuthorizedSignatory authorizedSignatory = new AuthorizedSignatory();
        authorizedSignatory.setSignatoryName(addAuthorizedSignatoryInput.getSignatoryName());
        authorizedSignatory.setSignatoryFatherName(addAuthorizedSignatoryInput.getSignatoryFatherName());
        authorizedSignatory.setSignatoryDesignation(addAuthorizedSignatoryInput.getSignatoryDesignation());
        authorizedSignatory.setAddress(addAuthorizedSignatoryInput.getAddress());
        authorizedSignatory.setEmail(addAuthorizedSignatoryInput.getEmail());
        authorizedSignatory.setPanCardNumber(addAuthorizedSignatoryInput.getPanCardNumber());
        authorizedSignatoryRepository.save(authorizedSignatory);

        return new AddAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public UpdateAuthorizedSignatoryPayload updateAuthorizedSignatory(UpdateAuthorizedSignatoryInput updateAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(updateAuthorizedSignatoryInput.getId()).get();
        if (updateAuthorizedSignatoryInput.getSignatoryName() != null) {
            authorizedSignatory.setSignatoryName(updateAuthorizedSignatoryInput.getSignatoryName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryFatherName() != null) {
            authorizedSignatory.setSignatoryFatherName(updateAuthorizedSignatoryInput.getSignatoryFatherName());
        }
        if (updateAuthorizedSignatoryInput.getSignatoryDesignation() != null) {
            authorizedSignatory.setSignatoryDesignation(updateAuthorizedSignatoryInput.getSignatoryDesignation());
        }

        if (updateAuthorizedSignatoryInput.getAddress() != null) {
            authorizedSignatory.setAddress(updateAuthorizedSignatoryInput.getAddress());
        }

        if (updateAuthorizedSignatoryInput.getEmail() != null) {
            authorizedSignatory.setEmail(updateAuthorizedSignatoryInput.getEmail());
        }

        if (updateAuthorizedSignatoryInput.getPanCardNumber() != null) {
            authorizedSignatory.setPanCardNumber(updateAuthorizedSignatoryInput.getPanCardNumber());
        }

        authorizedSignatoryRepository.save(authorizedSignatory);

        return new UpdateAuthorizedSignatoryPayload(authorizedSignatory);
    }

    public RemoveAuthorizedSignatoryPayload removeAuthorizedSignatory(RemoveAuthorizedSignatoryInput removeAuthorizedSignatoryInput) {
        AuthorizedSignatory authorizedSignatory  = authorizedSignatoryRepository.findById(removeAuthorizedSignatoryInput.getAuthorizedSignatoryId()).get();
        authorizedSignatoryRepository.delete(authorizedSignatory);
        return new RemoveAuthorizedSignatoryPayload(Lists.newArrayList(authorizedSignatoryRepository.findAll()));
    }

    public AddLegalEntityPayload addLegalEntity(AddLegalEntityInput addLegalEntityInput) {
        final AuthorizedSignatory authorizedSignatory = authorizedSignatoryRepository.findById(addLegalEntityInput.getAuthorizedSignatoryId()).get();
        final LegalEntity legalEntity = new LegalEntity();
        legalEntity.setLogo(addLegalEntityInput.getLogo());
        legalEntity.setLegalNameOfTheCollege(addLegalEntityInput.getLegalNameOfTheCollege());
        legalEntity.setTypeOfCollege(addLegalEntityInput.getTypeOfCollege());
        legalEntity.setDateOfIncorporation(addLegalEntityInput.getDateOfIncorporation());
        legalEntity.setRegisteredOfficeAddress(addLegalEntityInput.getRegisteredOfficeAddress());
        legalEntity.setCollegeIdentificationNumber(addLegalEntityInput.getCollegeIdentificationNumber());
        legalEntity.setPan(addLegalEntityInput.getPan());
        legalEntity.setTan(addLegalEntityInput.getTan());
        legalEntity.setTanCircleNumber(addLegalEntityInput.getTanCircleNumber());
        legalEntity.setCitTdsLocation(addLegalEntityInput.getCitTdsLocation());
        legalEntity.setFormSignatory(addLegalEntityInput.getFormSignatory());
        legalEntity.setPfNumber(addLegalEntityInput.getPfNumber());
        legalEntity.setRegistrationDate(addLegalEntityInput.getRegistrationDate());
        legalEntity.setEsiNumber(addLegalEntityInput.getEsiNumber());
        legalEntity.setPtRegistrationDate(addLegalEntityInput.getPtRegistrationDate());
        legalEntity.setPtSignatory(addLegalEntityInput.getPtSignatory());
        legalEntity.setPtNumber(addLegalEntityInput.getPtNumber());
        legalEntity.setAuthorizedSignatory(authorizedSignatory);

        legalEntityRepository.save(legalEntity);

        return new AddLegalEntityPayload(legalEntity);
    }

    public UpdateLegalEntityPayload updateLegalEntity(UpdateLegalEntityInput updateLegalEntityInput) {
        LegalEntity legalEntity = legalEntityRepository.findById(updateLegalEntityInput.getId()).get();
        if (updateLegalEntityInput.getLogo() != null) {
            legalEntity.setLogo(updateLegalEntityInput.getLogo());
        }
        if (updateLegalEntityInput.getLegalNameOfTheCollege() != null) {
            legalEntity.setLegalNameOfTheCollege(updateLegalEntityInput.getLegalNameOfTheCollege());
        }
        if (updateLegalEntityInput.getTypeOfCollege() != null) {
            legalEntity.setTypeOfCollege(updateLegalEntityInput.getTypeOfCollege());
        }
        if (updateLegalEntityInput.getDateOfIncorporation() != null) {
            legalEntity.setDateOfIncorporation(updateLegalEntityInput.getDateOfIncorporation());
        }
        if (updateLegalEntityInput.getRegisteredOfficeAddress() != null) {
            legalEntity.setRegisteredOfficeAddress(updateLegalEntityInput.getRegisteredOfficeAddress());
        }
        if (updateLegalEntityInput.getCollegeIdentificationNumber() != null) {
            legalEntity.setCollegeIdentificationNumber(updateLegalEntityInput.getCollegeIdentificationNumber());
        }
        if (updateLegalEntityInput.getPan() != null) {
            legalEntity.setPan(updateLegalEntityInput.getPan());
        }
        if (updateLegalEntityInput.getTan() != null) {
            legalEntity.setTan(updateLegalEntityInput.getTan());
        }
        if (updateLegalEntityInput.getTanCircleNumber() != null) {
            legalEntity.setTanCircleNumber(updateLegalEntityInput.getTanCircleNumber());
        }
        if (updateLegalEntityInput.getCitTdsLocation() != null) {
            legalEntity.setCitTdsLocation(updateLegalEntityInput.getCitTdsLocation());
        }
        if (updateLegalEntityInput.getFormSignatory() != null) {
            legalEntity.setFormSignatory(updateLegalEntityInput.getFormSignatory());
        }
        if (updateLegalEntityInput.getPfNumber() != null) {
            legalEntity.setPfNumber(updateLegalEntityInput.getPfNumber());
        }
        if (updateLegalEntityInput.getRegistrationDate() != null) {
            legalEntity.setRegistrationDate(updateLegalEntityInput.getRegistrationDate());
        }
        if (updateLegalEntityInput.getEsiNumber() != null) {
            legalEntity.setEsiNumber(updateLegalEntityInput.getEsiNumber());
        }
        if (updateLegalEntityInput.getPtRegistrationDate() != null) {
            legalEntity.setPtRegistrationDate(updateLegalEntityInput.getPtRegistrationDate());
        }
        if (updateLegalEntityInput.getPtSignatory() != null) {
            legalEntity.setPtSignatory(updateLegalEntityInput.getPtSignatory());
        }
        if (updateLegalEntityInput.getPtNumber() != null) {
            legalEntity.setPtNumber(updateLegalEntityInput.getPtNumber());
        }

        legalEntityRepository.save(legalEntity);

        return new UpdateLegalEntityPayload(legalEntity);
    }

    public RemoveLegalEntityPayload removeLegalEntity(RemoveLegalEntityInput removeLegalEntityInput) {
        LegalEntity legalEntity  = legalEntityRepository.findById(removeLegalEntityInput.getLegalEntityId()).get();
        legalEntityRepository.delete(legalEntity);
        return new RemoveLegalEntityPayload(Lists.newArrayList(legalEntityRepository.findAll()));
    }

}
