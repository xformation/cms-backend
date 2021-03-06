import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAttendanceMaster } from 'app/shared/model/attendance-master.model';
import { getEntities as getAttendanceMasters } from 'app/entities/attendance-master/attendance-master.reducer';
import { getEntity, updateEntity, createEntity, reset } from './lecture.reducer';
import { ILecture } from 'app/shared/model/lecture.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILectureUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILectureUpdateState {
  isNew: boolean;
  attendancemasterId: string;
}

export class LectureUpdate extends React.Component<ILectureUpdateProps, ILectureUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      attendancemasterId: '0',
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

    this.props.getAttendanceMasters();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { lectureEntity } = this.props;
      const entity = {
        ...lectureEntity,
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
    this.props.history.push('/entity/lecture');
  };

  render() {
    const { lectureEntity, attendanceMasters, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.lecture.home.createOrEditLabel">Create or edit a Lecture</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : lectureEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="lecture-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="lecDateLabel" for="lecDate">
                    Lec Date
                  </Label>
                  <AvField
                    id="lecture-lecDate"
                    type="date"
                    className="form-control"
                    name="lecDate"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastUpdatedOnLabel" for="lastUpdatedOn">
                    Last Updated On
                  </Label>
                  <AvField
                    id="lecture-lastUpdatedOn"
                    type="date"
                    className="form-control"
                    name="lastUpdatedOn"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lastUpdatedByLabel" for="lastUpdatedBy">
                    Last Updated By
                  </Label>
                  <AvField
                    id="lecture-lastUpdatedBy"
                    type="text"
                    name="lastUpdatedBy"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="startTimeLabel" for="startTime">
                    Start Time
                  </Label>
                  <AvField
                    id="lecture-startTime"
                    type="text"
                    name="startTime"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="endTimeLabel" for="endTime">
                    End Time
                  </Label>
                  <AvField
                    id="lecture-endTime"
                    type="text"
                    name="endTime"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="attendancemaster.id">Attendancemaster</Label>
                  <AvInput id="lecture-attendancemaster" type="select" className="form-control" name="attendancemasterId">
                    <option value="" key="0" />
                    {attendanceMasters
                      ? attendanceMasters.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/lecture" replace color="info">
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
  attendanceMasters: storeState.attendanceMaster.entities,
  lectureEntity: storeState.lecture.entity,
  loading: storeState.lecture.loading,
  updating: storeState.lecture.updating,
  updateSuccess: storeState.lecture.updateSuccess
});

const mapDispatchToProps = {
  getAttendanceMasters,
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
)(LectureUpdate);
