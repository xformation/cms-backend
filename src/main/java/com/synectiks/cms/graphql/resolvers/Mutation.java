package com.synectiks.cms.graphql.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.google.common.collect.Lists;
import com.synectiks.cms.domain.Student;
import com.synectiks.cms.graphql.types.Institute.*;
import com.synectiks.cms.graphql.types.Student.AddStudentInput;
import com.synectiks.cms.graphql.types.Student.AddStudentPayload;
import com.synectiks.cms.model.Institute;
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

    public Mutation(StudentRepository studentRepository, InstituteRepository instituteRepository) {
        this.studentRepository = studentRepository;
        this.instituteRepository = instituteRepository;
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
}
