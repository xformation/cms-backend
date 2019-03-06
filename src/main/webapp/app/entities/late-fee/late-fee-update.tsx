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
import { getEntity, updateEntity, createEntity, reset } from './late-fee.reducer';
import { ILateFee } from 'app/shared/model/late-fee.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';

export interface ILateFeeUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ILateFeeUpdateState {
  isNew: boolean;
  collegeId: string;
  branchId: string;
}

export class LateFeeUpdate extends React.Component<ILateFeeUpdateProps, ILateFeeUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      collegeId: '0',
      branchId: '0',
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
    const { lateFeeEntity, colleges, branches, loading, updating } = this.props;
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
                  <Label id="assignLateFeeLabel">Assign Late Fee</Label>
                  <AvInput
                    id="late-fee-assignLateFee"
                    type="select"
                    className="form-control"
                    name="assignLateFee"
                    value={(!isNew && lateFeeEntity.assignLateFee) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="lateFeeDaysLabel" for="lateFeeDays">
                    Late Fee Days
                  </Label>
                  <AvField
                    id="late-fee-lateFeeDays"
                    type="string"
                    className="form-control"
                    name="lateFeeDays"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="fixedLabel">Fixed</Label>
                  <AvInput
                    id="late-fee-fixed"
                    type="select"
                    className="form-control"
                    name="fixed"
                    value={(!isNew && lateFeeEntity.fixed) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="percentageLabel">Percentage</Label>
                  <AvInput
                    id="late-fee-percentage"
                    type="select"
                    className="form-control"
                    name="percentage"
                    value={(!isNew && lateFeeEntity.percentage) || 'ACTIVE'}
                  >
                    <option value="ACTIVE">ACTIVE</option>
                    <option value="DEACTIVE">DEACTIVE</option>
                  </AvInput>
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
                  <AvField id="late-fee-percentCharges" type="string" className="form-control" name="percentCharges" />
                </AvGroup>
                <AvGroup>
                  <Label id="lateFeeAssignmentFrequencyLabel">Late Fee Assignment Frequency</Label>
                  <AvInput
                    id="late-fee-lateFeeAssignmentFrequency"
                    type="select"
                    className="form-control"
                    name="lateFeeAssignmentFrequency"
                    value={(!isNew && lateFeeEntity.lateFeeAssignmentFrequency) || 'WEEKLY'}
                  >
                    <option value="WEEKLY">WEEKLY</option>
                    <option value="MONTHLY">MONTHLY</option>
                    <option value="QUARTERLY">QUARTERLY</option>
                    <option value="HALFYEARLY">HALFYEARLY</option>
                    <option value="ANNUALLY">ANNUALLY</option>
                  </AvInput>
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
  lateFeeEntity: storeState.lateFee.entity,
  loading: storeState.lateFee.loading,
  updating: storeState.lateFee.updating,
  updateSuccess: storeState.lateFee.updateSuccess
});

const mapDispatchToProps = {
  getColleges,
  getBranches,
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
