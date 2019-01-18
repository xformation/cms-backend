import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { ISubject } from 'app/shared/model/subject.model';
import { getEntities as getSubjects } from 'app/entities/subject/subject.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-subject.reducer';
import { IStudentSubject } from 'app/shared/model/student-subject.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IStudentSubjectUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentSubjectUpdateState {
  isNew: boolean;
  studentId: number;
  subjectId: number;
}

export class StudentSubjectUpdate extends React.Component<IStudentSubjectUpdateProps, IStudentSubjectUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: 0,
      subjectId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getStudents();
    this.props.getSubjects();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentSubjectEntity } = this.props;
      const entity = {
        ...studentSubjectEntity,
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
    this.props.history.push('/entity/student-subject');
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

  render() {
    const { studentSubjectEntity, students, subjects, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentSubject.home.createOrEditLabel">Create or edit a StudentSubject</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentSubjectEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="student-subject-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField id="student-subject-comments" type="text" name="comments" />
                </AvGroup>
                <AvGroup>
                  <Label id="lastupdatedDateLabel" for="lastupdatedDate">
                    Lastupdated Date
                  </Label>
                  <AvField
                    id="student-subject-lastupdatedDate"
                    type="date"
                    className="form-control"
                    name="lastupdatedDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput
                    id="student-subject-student"
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
                <AvGroup>
                  <Label for="subject.id">Subject</Label>
                  <AvInput
                    id="student-subject-subject"
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
                <Button tag={Link} id="cancel-save" to="/entity/student-subject" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp; Save
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
  students: storeState.student.entities,
  subjects: storeState.subject.entities,
  studentSubjectEntity: storeState.studentSubject.entity,
  loading: storeState.studentSubject.loading,
  updating: storeState.studentSubject.updating
});

const mapDispatchToProps = {
  getStudents,
  getSubjects,
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
)(StudentSubjectUpdate);
