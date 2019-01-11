import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IDepartment } from 'app/shared/model/department.model';
import { getEntities as getDepartments } from 'app/entities/department/department.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { getEntity, updateEntity, createEntity, reset } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISubjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface ISubjectUpdateState {
  isNew: boolean;
  departmentId: number;
  studentId: number;
  teacherId: number;
}

export class SubjectUpdate extends React.Component<ISubjectUpdateProps, ISubjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      departmentId: 0,
      studentId: 0,
      teacherId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getDepartments();
    this.props.getStudents();
    this.props.getTeachers();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { subjectEntity } = this.props;
      const entity = {
        ...subjectEntity,
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
    this.props.history.push('/entity/subject');
  };

  render() {
    const { subjectEntity, departments, students, teachers, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.subject.home.createOrEditLabel">
              <Translate contentKey="cmsApp.subject.home.createOrEditLabel">Create or edit a Subject</Translate>
            </h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : subjectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="subject-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="commonSubLabel">
                    <Translate contentKey="cmsApp.subject.commonSub">Common Sub</Translate>
                  </Label>
                  <AvInput
                    id="subject-commonSub"
                    type="select"
                    className="form-control"
                    name="commonSub"
                    value={(!isNew && subjectEntity.commonSub) || 'MATHS'}
                  >
                    <option value="MATHS">
                      <Translate contentKey="cmsApp.CommonSubEnum.MATHS" />
                    </option>
                    <option value="PHYSICS">
                      <Translate contentKey="cmsApp.CommonSubEnum.PHYSICS" />
                    </option>
                    <option value="CHEMISTRY">
                      <Translate contentKey="cmsApp.CommonSubEnum.CHEMISTRY" />
                    </option>
                    <option value="DBMS">
                      <Translate contentKey="cmsApp.CommonSubEnum.DBMS" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="electiveSubLabel">
                    <Translate contentKey="cmsApp.subject.electiveSub">Elective Sub</Translate>
                  </Label>
                  <AvInput
                    id="subject-electiveSub"
                    type="select"
                    className="form-control"
                    name="electiveSub"
                    value={(!isNew && subjectEntity.electiveSub) || 'JAVA'}
                  >
                    <option value="JAVA">
                      <Translate contentKey="cmsApp.ElectiveEnum.JAVA" />
                    </option>
                    <option value="C">
                      <Translate contentKey="cmsApp.ElectiveEnum.C" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="department.id">
                    <Translate contentKey="cmsApp.subject.department">Department</Translate>
                  </Label>
                  <AvInput id="subject-department" type="select" className="form-control" name="departmentId">
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
                  <Label for="student.id">
                    <Translate contentKey="cmsApp.subject.student">Student</Translate>
                  </Label>
                  <AvInput id="subject-student" type="select" className="form-control" name="studentId">
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
                <AvGroup>
                  <Label for="teacher.id">
                    <Translate contentKey="cmsApp.subject.teacher">Teacher</Translate>
                  </Label>
                  <AvInput id="subject-teacher" type="select" className="form-control" name="teacherId">
                    <option value="" key="0" />
                    {teachers
                      ? teachers.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/subject" replace color="info">
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
  departments: storeState.department.entities,
  students: storeState.student.entities,
  teachers: storeState.teacher.entities,
  subjectEntity: storeState.subject.entity,
  loading: storeState.subject.loading,
  updating: storeState.subject.updating
});

const mapDispatchToProps = {
  getDepartments,
  getStudents,
  getTeachers,
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
)(SubjectUpdate);
