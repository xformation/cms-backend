import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudentYear } from 'app/shared/model/student-year.model';
import { getEntities as getStudentYears } from 'app/entities/student-year/student-year.reducer';
import { IDepartments } from 'app/shared/model/departments.model';
import { getEntities as getDepartments } from 'app/entities/departments/departments.reducer';
import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { ISemester } from 'app/shared/model/semester.model';
import { getEntities as getSemesters } from 'app/entities/semester/semester.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { IPeriods } from 'app/shared/model/periods.model';
import { getEntities as getPeriods } from 'app/entities/periods/periods.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IStudentAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export interface IStudentAttendanceUpdateState {
  isNew: boolean;
  studentYearid: any;
  departmentsid: any;
  subjectid: any;
  semesterid: any;
  sectionid: any;
  periodsid: any;
  studentid: any;
}

export class StudentAttendanceUpdate extends React.Component<IStudentAttendanceUpdateProps, IStudentAttendanceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentYearId: 0,
      departmentsId: 0,
      subjectId: 0,
      semesterId: 0,
      sectionId: 0,
      periodsId: 0,
      studentId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getStudentYears();
    this.props.getDepartments();
    this.props.getSubjects();
    this.props.getSemesters();
    this.props.getSections();
    this.props.getPeriods();
    this.props.getStudents();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentAttendanceEntity } = this.props;
      const entity = {
        ...studentAttendanceEntity,
        ...values
      };

      if (this.state.isNew) {
        this.props.createEntity(entity);
      } else {
        this.props.updateEntity(entity);
      }
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/student-attendance');
  };

  studentYearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        studentYearId: -1
      });
    } else {
      for (const i in this.props.studentYears) {
        if (id === this.props.studentYears[i].id.toString()) {
          this.setState({
            studentYearId: this.props.studentYears[i].id
          });
        }
      }
    }
  };

  departmentsUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        departmentsId: -1
      });
    } else {
      for (const i in this.props.departments) {
        if (id === this.props.departments[i].id.toString()) {
          this.setState({
            departmentsId: this.props.departments[i].id
          });
        }
      }
    }
  };

  subjectUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        subjectId: -1
      });
    } else {
      for (const i in this.props.subjects) {
        if (id === this.props.subjects[i].id.toString()) {
          this.setState({
            subjectId: this.props.subjects[i].id
          });
        }
      }
    }
  };

  semesterUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        semesterId: -1
      });
    } else {
      for (const i in this.props.semesters) {
        if (id === this.props.semesters[i].id.toString()) {
          this.setState({
            semesterId: this.props.semesters[i].id
          });
        }
      }
    }
  };

  sectionUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        sectionId: -1
      });
    } else {
      for (const i in this.props.sections) {
        if (id === this.props.sections[i].id.toString()) {
          this.setState({
            sectionId: this.props.sections[i].id
          });
        }
      }
    }
  };

  periodsUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        periodsId: -1
      });
    } else {
      for (const i in this.props.periods) {
        if (id === this.props.periods[i].id.toString()) {
          this.setState({
            periodsId: this.props.periods[i].id
          });
        }
      }
    }
  };

  studentUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        studentId: -1
      });
    } else {
      for (const i in this.props.students) {
        if (id === this.props.students[i].id.toString()) {
          this.setState({
            studentId: this.props.students[i].id
          });
        }
      }
    }
  };

  render() {
    const {
      studentAttendanceEntity,
      studentYears,
      departments,
      subjects,
      semesters,
      sections,
      periods,
      students,
      loading,
      updating
    } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentAttendance.home.createOrEditLabel">
              <Translate contentKey="cmsApp.studentAttendance.home.createOrEditLabel">Create or edit a StudentAttendance</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentAttendanceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="student-attendance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="attendanceDateLabel" for="attendanceDate">
                    <Translate contentKey="cmsApp.studentAttendance.attendanceDate">Attendance Date</Translate>
                  </Label>
                  <AvField
                    id="student-attendance-attendanceDate"
                    type="date"
                    className="form-control"
                    name="attendanceDate"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="sNameLabel" for="sName">
                    <Translate contentKey="cmsApp.studentAttendance.sName">S Name</Translate>
                  </Label>
                  <AvField
                    id="student-attendance-sName"
                    type="text"
                    name="sName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="statusLabel">
                    <Translate contentKey="cmsApp.studentAttendance.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && studentAttendanceEntity.status) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">
                      <Translate contentKey="cmsApp.Status.ACTIVE" />
                    </option>
                    <option value="DEACTIVE">
                      <Translate contentKey="cmsApp.Status.DEACTIVE" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    <Translate contentKey="cmsApp.studentAttendance.comments">Comments</Translate>
                  </Label>
                  <AvField
                    id="student-attendance-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="studentYear.id">
                    <Translate contentKey="cmsApp.studentAttendance.studentYear">Student Year</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-studentYear"
                    type="select"
                    className="form-control"
                    name="studentYearId"
                    onChange={this.studentYearUpdate}
                  >
                    <option value="" key="0" />
                    {studentYears
                      ? studentYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="departments.id">
                    <Translate contentKey="cmsApp.studentAttendance.departments">Departments</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-departments"
                    type="select"
                    className="form-control"
                    name="departmentsId"
                    onChange={this.departmentsUpdate}
                  >
                    <option value="" key="0" />
                    {departments
                      ? departments.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="subject.id">
                    <Translate contentKey="cmsApp.studentAttendance.subject">Subject</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-subject"
                    type="select"
                    className="form-control"
                    name="subjectId"
                    onChange={this.subjectUpdate}
                  >
                    <option value="" key="0" />
                    {subjects
                      ? subjects.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="semester.id">
                    <Translate contentKey="cmsApp.studentAttendance.semester">Semester</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-semester"
                    type="select"
                    className="form-control"
                    name="semesterId"
                    onChange={this.semesterUpdate}
                  >
                    <option value="" key="0" />
                    {semesters
                      ? semesters.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="section.id">
                    <Translate contentKey="cmsApp.studentAttendance.section">Section</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-section"
                    type="select"
                    className="form-control"
                    name="sectionId"
                    onChange={this.sectionUpdate}
                  >
                    <option value="" key="0" />
                    {sections
                      ? sections.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="periods.id">
                    <Translate contentKey="cmsApp.studentAttendance.periods">Periods</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-periods"
                    type="select"
                    className="form-control"
                    name="periodsId"
                    onChange={this.periodsUpdate}
                  >
                    <option value="" key="0" />
                    {periods
                      ? periods.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">
                    <Translate contentKey="cmsApp.studentAttendance.student">Student</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-student"
                    type="select"
                    className="form-control"
                    name="studentId"
                    onChange={this.studentUpdate}
                  >
                    <option value="" key="0" />
                    {students
                      ? students.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student-attendance" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
                </Button>
              </AvForm>
            )}
          </Col>
        </Row>
      </div>
    );
  }
}

const mapStateToProps = (storeState: IRootState) => ({
  studentYears: storeState.studentYear.entities,
  departments: storeState.departments.entities,
  subjects: storeState.subject.entities,
  semesters: storeState.semester.entities,
  sections: storeState.section.entities,
  periods: storeState.periods.entities,
  students: storeState.student.entities,
  studentAttendanceEntity: storeState.studentAttendance.entity,
  loading: storeState.studentAttendance.loading,
  updating: storeState.studentAttendance.updating
});

const mapDispatchToProps = {
  getStudentYears,
  getDepartments,
  getSubjects,
  getSemesters,
  getSections,
  getPeriods,
  getStudents,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(StudentAttendanceUpdate);
