import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IStudent } from 'app/shared/model/student.model';
import { getEntities as getStudents } from 'app/entities/student/student.reducer';
import { IFacility } from 'app/shared/model/facility.model';
import { getEntities as getFacilities } from 'app/entities/facility/facility.reducer';
import { getEntity, updateEntity, createEntity, reset } from './student-facility-link.reducer';
import { IStudentFacilityLink } from 'app/shared/model/student-facility-link.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IStudentFacilityLinkUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IStudentFacilityLinkUpdateState {
  isNew: boolean;
  studentId: number;
  facilityId: number;
}

export class StudentFacilityLinkUpdate extends React.Component<IStudentFacilityLinkUpdateProps, IStudentFacilityLinkUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      studentId: 0,
      facilityId: 0,
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
    this.props.getFacilities();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { studentFacilityLinkEntity } = this.props;
      const entity = {
        ...studentFacilityLinkEntity,
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
    this.props.history.push('/entity/student-facility-link');
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

  facilityUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        facilityId: -1
      });
    } else {
      for (const i in this.props.facilities) {
        if (id === this.props.facilities[i].id.toString()) {
          this.setState({
            facilityId: this.props.facilities[i].id
          });
        }
      }
    }
  };

  render() {
    const isInvalid = false;
    const { studentFacilityLinkEntity, students, facilities, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.studentFacilityLink.home.createOrEditLabel">Create or edit a StudentFacilityLink</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : studentFacilityLinkEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="student-facility-link-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="linkDescLabel" for="linkDesc">
                    Link Desc
                  </Label>
                  <AvField id="student-facility-link-linkDesc" type="text" name="linkDesc" />
                </AvGroup>
                <AvGroup>
                  <Label for="student.id">Student</Label>
                  <AvInput
                    id="student-facility-link-student"
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
                  <Label for="facility.id">Facility</Label>
                  <AvInput
                    id="student-facility-link-facility"
                    type="select"
                    className="form-control"
                    name="facilityId"
                    onChange={this.facilityUpdate}
                  >
                    <option value="" key="0" />
                    {facilities
                      ? facilities.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/student-facility-link" replace color="info">
                  <FontAwesomeIcon icon="arrow-left" />&nbsp;
                  <span className="d-none d-md-inline">Back</span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={isInvalid || updating}>
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
  facilities: storeState.facility.entities,
  studentFacilityLinkEntity: storeState.studentFacilityLink.entity,
  loading: storeState.studentFacilityLink.loading,
  updating: storeState.studentFacilityLink.updating
});

const mapDispatchToProps = {
  getStudents,
  getFacilities,
  getEntity,
  updateEntity,
  createEntity,
  reset
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StudentFacilityLinkUpdate);
