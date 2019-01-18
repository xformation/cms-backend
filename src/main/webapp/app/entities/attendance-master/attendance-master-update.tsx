import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ITeach } from 'app/shared/model/teach.model';
import { getEntities as getTeaches } from 'app/entities/teach/teach.reducer';
import { ISection } from 'app/shared/model/section.model';
import { getEntities as getSections } from 'app/entities/section/section.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { getEntity, updateEntity, createEntity, reset } from './attendance-master.reducer';
import { IAttendanceMaster } from 'app/shared/model/attendance-master.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IAttendanceMasterUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAttendanceMasterUpdateState {
  isNew: boolean;
  teachId: number;
  sectionId: number;
  academicyearId: number;
}

export class AttendanceMasterUpdate extends React.Component<IAttendanceMasterUpdateProps, IAttendanceMasterUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      teachId: 0,
      sectionId: 0,
      academicyearId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getTeaches();
    this.props.getSections();
    this.props.getAcademicYears();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { attendanceMasterEntity } = this.props;
      const entity = {
        ...attendanceMasterEntity,
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
    this.props.history.push('/entity/attendance-master');
  };

  teachUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        teachId: -1
      });
    } else {
      for (const i in this.props.teaches) {
        if (id === this.props.teaches[i].id.toString()) {
          this.setState({
            teachId: this.props.teaches[i].id
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

  academicyearUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        academicyearId: -1
      });
    } else {
      for (const i in this.props.academicYears) {
        if (id === this.props.academicYears[i].id.toString()) {
          this.setState({
            academicyearId: this.props.academicYears[i].id
          });
        }
      }
    }
  };

  render() {
    const { attendanceMasterEntity, teaches, sections, academicYears, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.attendanceMaster.home.createOrEditLabel">Create or edit a AttendanceMaster</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : attendanceMasterEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="attendance-master-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="descLabel" for="desc">
                    Desc
                  </Label>
                  <AvField id="attendance-master-desc" type="text" name="desc" />
                </AvGroup>
                <AvGroup>
                  <Label for="teach.id">Teach</Label>
                  <AvInput id="attendance-master-teach" type="select" className="form-control" name="teachId" onChange={this.teachUpdate}>
                    <option value="" key="0" />
                    {teaches
                      ? teaches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="section.id">Section</Label>
                  <AvInput
                    id="attendance-master-section"
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
                  <Label for="academicyear.id">Academicyear</Label>
                  <AvInput
                    id="attendance-master-academicyear"
                    type="select"
                    className="form-control"
                    name="academicyearId"
                    onChange={this.academicyearUpdate}
                  >
                    <option value="" key="0" />
                    {academicYears
                      ? academicYears.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/attendance-master" replace color="info">
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
  teaches: storeState.teach.entities,
  sections: storeState.section.entities,
  academicYears: storeState.academicYear.entities,
  attendanceMasterEntity: storeState.attendanceMaster.entity,
  loading: storeState.attendanceMaster.loading,
  updating: storeState.attendanceMaster.updating
});

const mapDispatchToProps = {
  getTeaches,
  getSections,
  getAcademicYears,
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
)(AttendanceMasterUpdate);
