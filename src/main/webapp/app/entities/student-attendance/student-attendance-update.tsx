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
import { ILecture } from 'app/shared/model/lecture.model';
import { getEntities as getLectures } from 'app/entities/lecture/lecture.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
// import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IStudentAttendanceUpdateState {
  isNew: boolean;
  studentId: string;
  lectureId: string;
}

export class StudentAttendanceUpdate extends React.Component<IStudentAttendanceUpdateProps, IStudentAttendanceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: '0',
      lectureId: '0',
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentWillUpdate(nextProps, nextState) {
    if (nextProps.updateSuccess !== this.props.updateSuccess && nextProps.updateSuccess) {
      this.handleClose();
    }
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getStudents();
    this.props.getLectures();
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
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/student-attendance');
  };

  render() {
    const { studentAttendanceEntity, students, lectures, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentAttendance.home.createOrEditLabel">Create or edit a StudentAttendance</h2>
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
                    <Label for="id">ID</Label>
                    <AvInput id="student-attendance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="attendanceStatusLabel">Attendance Status</Label>
                  <AvInput
                    id="student-attendance-attendanceStatus"
                    type="select"
                    className="form-control"
                    name="attendanceStatus"
                    value={(!isNew && studentAttendanceEntity.attendanceStatus) || 'PRESENT'}
                  >
                    <option value="PRESENT">PRESENT</option>
                    <option value="ABSENT">ABSENT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField id="student-attendance-comments" type="text" name="comments" />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput id="student-attendance-student" type="select" className="form-control" name="studentId">
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
                  <Label for="lecture.id">Lecture</Label>
                  <AvInput id="student-attendance-lecture" type="select" className="form-control" name="lectureId">
                    <option value="" key="0" />
                    {lectures
                      ? lectures.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student-attendance" replace color="info">
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
  lectures: storeState.lecture.entities,
  studentAttendanceEntity: storeState.studentAttendance.entity,
  loading: storeState.studentAttendance.loading,
  updating: storeState.studentAttendance.updating,
  updateSuccess: storeState.studentAttendance.updateSuccess
});

const mapDispatchToProps = {
  getStudents,
  getLectures,
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
