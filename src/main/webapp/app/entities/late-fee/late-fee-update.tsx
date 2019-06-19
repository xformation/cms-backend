import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { IAcademicYear } from 'app/shared/model/academic-year.model';
import { getEntities as getAcademicYears } from 'app/entities/academic-year/academic-year.reducer';
import { ITerm } from 'app/shared/model/term.model';
import { getEntities as getTerms } from 'app/entities/term/term.reducer';
import { getEntity, updateEntity, createEntity, reset } from './late-fee.reducer';
import { ILateFee } from 'app/shared/model/late-fee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ILateFeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILateFeeUpdateState {
  isNew: boolean;
  collegeId: string;
  branchId: string;
  academicYearId: string;
  termId: string;
}

export class LateFeeUpdate extends React.Component<ILateFeeUpdateProps, ILateFeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      collegeId: '0',
      branchId: '0',
      academicYearId: '0',
      termId: '0',
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

    this.props.getColleges();
    this.props.getBranches();
    this.props.getAcademicYears();
    this.props.getTerms();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { lateFeeEntity } = this.props;
      const entity = {
        ...lateFeeEntity,
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
    this.props.history.push('/entity/late-fee');
  };

  render() {
    const { lateFeeEntity, colleges, branches, academicYears, terms, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.lateFee.home.createOrEditLabel">Create or edit a LateFee</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : lateFeeEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="late-fee-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="isAutoLateFeeLabel" for="isAutoLateFee">
                    Is Auto Late Fee
                  </Label>
                  <AvField
                    id="late-fee-isAutoLateFee"
                    type="text"
                    name="isAutoLateFee"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="lateFeeDaysLabel" for="lateFeeDays">
                    Late Fee Days
                  </Label>
                  <AvField id="late-fee-lateFeeDays" type="string" className="form-control" name="lateFeeDays" />
                </AvGroup>
                <AvGroup>
                  <Label id="chargeTypeLabel" for="chargeType">
                    Charge Type
                  </Label>
                  <AvField id="late-fee-chargeType" type="text" name="chargeType" />
                </AvGroup>
                <AvGroup>
                  <Label id="fixedChargesLabel" for="fixedCharges">
                    Fixed Charges
                  </Label>
                  <AvField id="late-fee-fixedCharges" type="string" className="form-control" name="fixedCharges" />
                </AvGroup>
                <AvGroup>
                  <Label id="percentChargesLabel" for="percentCharges">
                    Percent Charges
                  </Label>
                  <AvField id="late-fee-percentCharges" type="text" name="percentCharges" />
                </AvGroup>
                <AvGroup>
                  <Label id="lateFeeFrequencyLabel" for="lateFeeFrequency">
                    Late Fee Frequency
                  </Label>
                  <AvField id="late-fee-lateFeeFrequency" type="text" name="lateFeeFrequency" />
                </AvGroup>
                <AvGroup>
                  <Label id="lateFeeRepeatDaysLabel" for="lateFeeRepeatDays">
                    Late Fee Repeat Days
                  </Label>
                  <AvField id="late-fee-lateFeeRepeatDays" type="string" className="form-control" name="lateFeeRepeatDays" />
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="late-fee-college" type="select" className="form-control" name="collegeId">
                    <option value="" key="0" />
                    {colleges
                      ? colleges.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="late-fee-branch" type="select" className="form-control" name="branchId">
                    <option value="" key="0" />
                    {branches
                      ? branches.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="academicYear.id">Academic Year</Label>
                  <AvInput id="late-fee-academicYear" type="select" className="form-control" name="academicYearId">
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
                <AvGroup>
                  <Label for="term.id">Term</Label>
                  <AvInput id="late-fee-term" type="select" className="form-control" name="termId">
                    <option value="" key="0" />
                    {terms
                      ? terms.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/late-fee" replace color="info">
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
  colleges: storeState.college.entities,
  branches: storeState.branch.entities,
  academicYears: storeState.academicYear.entities,
  terms: storeState.term.entities,
  lateFeeEntity: storeState.lateFee.entity,
  loading: storeState.lateFee.loading,
  updating: storeState.lateFee.updating,
  updateSuccess: storeState.lateFee.updateSuccess
});

const mapDispatchToProps = {
  getColleges,
  getBranches,
  getAcademicYears,
  getTerms,
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
)(LateFeeUpdate);
