package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.domain.College;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.graphql.types.College.*;
import com.synectiks.cms.graphql.types.Institute.*;
import com.synectiks.cms.graphql.types.Student.AddStudentInput;
import com.synectiks.cms.graphql.types.Student.AddStudentPayload;
import com.synectiks.cms.model.Institute;
import com.synectiks.cms.repository.CollegeRepository;
import com.synectiks.cms.repository.InstituteRepository;
import com.synectiks.cms.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class Mutation implements GraphQLMutationResolver {
    private final static Logger logger = LoggerFactory.getLogger(Mutation.class);
    private final InstituteRepository instituteRepository;
    private final StudentRepository studentRepository;
    private final CollegeRepository collegeRepository;

    public Mutation(StudentRepository studentRepository, InstituteRepository instituteRepository, CollegeRepository collegeRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
        this.collegeRepository = collegeRepository;
    }

    public AddStudentPayload addStudent(AddStudentInput addStudentInput) {
        final Student student = new Student();
        student.setsName(addStudentInput.getsName());
        student.setAttendance(addStudentInput.getAttendance());
        student.setElectiveSub(addStudentInput.getElectiveSub());
        student.setTeacher(addStudentInput.getTeacher());
        studentRepository.save(student);
       return new AddStudentPayload(student);
    }

    public AddInstitutePayload addInstitute(AddInstituteInput addInstituteInput) {
        final Institute institute = new Institute();
        institute.setCode(addInstituteInput.getCode());
        institute.setName(addInstituteInput.getName());
        institute.setYear(addInstituteInput.getYear());

        instituteRepository.save(institute);

        return new AddInstitutePayload(institute);
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
        College college = collegeRepository.getOne(updateCollegeInput.getCollegeId());
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
}
