import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-attendance.reducer';
import { IStudentAttendance } from 'app/shared/model/student-attendance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStudentAttendanceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentAttendanceUpdateState {
  isNew: boolean;
  studentId: number;
}

export class StudentAttendanceUpdate extends React.Component<IStudentAttendanceUpdateProps, IStudentAttendanceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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

  render() {
    const { studentAttendanceEntity, students, loading, updating } = this.props;
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
                  <Label id="statusLabel">
                    <Translate contentKey="cmsApp.studentAttendance.status">Status</Translate>
                  </Label>
                  <AvInput
                    id="student-attendance-status"
                    type="select"
                    className="form-control"
                    name="status"
                    value={(!isNew && studentAttendanceEntity.status) || 'PRESENT'}
                  >
                    <option value="PRESENT">
                      <Translate contentKey="cmsApp.Status.PRESENT" />
                    </option>
                    <option value="ABSENT">
                      <Translate contentKey="cmsApp.Status.ABSENT" />
                    </option>
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
                  <Label for="student.id">
                    <Translate contentKey="cmsApp.studentAttendance.student">Student</Translate>
                  </Label>
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
  students: storeState.student.entities,
  studentAttendanceEntity: storeState.studentAttendance.entity,
  loading: storeState.studentAttendance.loading,
  updating: storeState.studentAttendance.updating
});

const mapDispatchToProps = {
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
