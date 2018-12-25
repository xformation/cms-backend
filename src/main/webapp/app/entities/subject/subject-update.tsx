import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IPeriods } from 'app/shared/model/periods.model';
import { getEntities as getPeriods } from 'app/entities/periods/periods.reducer';
import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ITeacher } from 'app/shared/model/teacher.model';
import { getEntities as getTeachers } from 'app/entities/teacher/teacher.reducer';
import { getEntity, updateEntity, createEntity, reset } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface ISubjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: any }> {}

export interface ISubjectUpdateState {
  isNew: boolean;
  periodsid: any;
  studentid: any;
  teacherid: any;
}

export class SubjectUpdate extends React.Component<ISubjectUpdateProps, ISubjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      periodsId: 0,
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

    this.props.getPeriods();
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

  teacherUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        teacherId: -1
      });
    } else {
      for (const i in this.props.teachers) {
        if (id === this.props.teachers[i].id.toString()) {
          this.setState({
            teacherId: this.props.teachers[i].id
          });
        }
      }
    }
  };

  render() {
    const { subjectEntity, periods, students, teachers, loading, updating } = this.props;
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
                      <Translate contentKey="cmsApp.Common.MATHS" />
                    </option>
                    <option value="PHYSICS">
                      <Translate contentKey="cmsApp.Common.PHYSICS" />
                    </option>
                    <option value="CHEMISTRY">
                      <Translate contentKey="cmsApp.Common.CHEMISTRY" />
                    </option>
                    <option value="DBMS">
                      <Translate contentKey="cmsApp.Common.DBMS" />
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
                      <Translate contentKey="cmsApp.Elective.JAVA" />
                    </option>
                    <option value="C">
                      <Translate contentKey="cmsApp.Elective.C" />
                    </option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="periods.id">
                    <Translate contentKey="cmsApp.subject.periods">Periods</Translate>
                  </Label>
                  <AvInput id="subject-periods" type="select" className="form-control" name="periodsId" onChange={this.periodsUpdate}>
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
                    <Translate contentKey="cmsApp.subject.student">Student</Translate>
                  </Label>
                  <AvInput id="subject-student" type="select" className="form-control" name="studentId" onChange={this.studentUpdate}>
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
                  <AvInput id="subject-teacher" type="select" className="form-control" name="teacherId" onChange={this.teacherUpdate}>
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
  periods: storeState.periods.entities,
  students: storeState.student.entities,
  teachers: storeState.teacher.entities,
  subjectEntity: storeState.subject.entity,
  loading: storeState.subject.loading,
  updating: storeState.subject.updating
});

const mapDispatchToProps = {
  getPeriods,
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
