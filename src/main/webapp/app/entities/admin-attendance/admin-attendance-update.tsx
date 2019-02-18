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
import { getEntity, updateEntity, createEntity, reset } from './admin-attendance.reducer';
import { IAdminAttendance } from 'app/shared/model/admin-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IAdminAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAdminAttendanceUpdateState {
  isNew: boolean;
  studentId: number;
  lectureId: number;
}

export class AdminAttendanceUpdate extends React.Component<IAdminAttendanceUpdateProps, IAdminAttendanceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: 0,
      lectureId: 0,
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
    this.props.getLectures();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { adminAttendanceEntity } = this.props;
      const entity = {
        ...adminAttendanceEntity,
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
    this.props.history.push('/entity/admin-attendance');
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

  lectureUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        lectureId: -1
      });
    } else {
      for (const i in this.props.lectures) {
        if (id === this.props.lectures[i].id.toString()) {
          this.setState({
            lectureId: this.props.lectures[i].id
          });
        }
      }
    }
  };

  render() {
    const { adminAttendanceEntity, students, lectures, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.adminAttendance.home.createOrEditLabel">Create or edit a AdminAttendance</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : adminAttendanceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="admin-attendance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="attendanceStatusLabel">Attendance Status</Label>
                  <AvInput
                    id="admin-attendance-attendanceStatus"
                    type="select"
                    className="form-control"
                    name="attendanceStatus"
                    value={(!isNew && adminAttendanceEntity.attendanceStatus) || 'PRESENT'}
                  >
                    <option value="PRESENT">PRESENT</option>
                    <option value="ABSENT">ABSENT</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="commentsLabel" for="comments">
                    Comments
                  </Label>
                  <AvField
                    id="admin-attendance-comments"
                    type="text"
                    name="comments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput
                    id="admin-attendance-student"
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
                  <Label for="lecture.id">Lecture</Label>
                  <AvInput
                    id="admin-attendance-lecture"
                    type="select"
                    className="form-control"
                    name="lectureId"
                    onChange={this.lectureUpdate}
                  >
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
                <Button tag={Link} id="cancel-save" to="/entity/admin-attendance" replace color="info">
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
  adminAttendanceEntity: storeState.adminAttendance.entity,
  loading: storeState.adminAttendance.loading,
  updating: storeState.adminAttendance.updating
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
)(AdminAttendanceUpdate);
