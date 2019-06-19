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
import { getEntity, updateEntity, createEntity, reset } from './due-date.reducer';
import { IDueDate } from 'app/shared/model/due-date.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer, convertDateTimeToServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IDueDateUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IDueDateUpdateState {
  isNew: boolean;
  collegeId: string;
  branchId: string;
}

export class DueDateUpdate extends React.Component<IDueDateUpdateProps, IDueDateUpdateState> {
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
      const { dueDateEntity } = this.props;
      const entity = {
        ...dueDateEntity,
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
    this.props.history.push('/entity/due-date');
  };

  render() {
    const { dueDateEntity, colleges, branches, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.dueDate.home.createOrEditLabel">Create or edit a DueDate</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : dueDateEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="due-date-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="paymentMethodLabel" for="paymentMethod">
                    Payment Method
                  </Label>
                  <AvField
                    id="due-date-paymentMethod"
                    type="text"
                    name="paymentMethod"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="installmentsLabel" for="installments">
                    Installments
                  </Label>
                  <AvField
                    id="due-date-installments"
                    type="string"
                    className="form-control"
                    name="installments"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' },
                      number: { value: true, errorMessage: 'This field should be a number.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="dayDescLabel" for="dayDesc">
                    Day Desc
                  </Label>
                  <AvField id="due-date-dayDesc" type="text" name="dayDesc" />
                </AvGroup>
                <AvGroup>
                  <Label id="paymentDayLabel" for="paymentDay">
                    Payment Day
                  </Label>
                  <AvField id="due-date-paymentDay" type="string" className="form-control" name="paymentDay" />
                </AvGroup>
                <AvGroup>
                  <Label id="frequencyLabel">Frequency</Label>
                  <AvInput
                    id="due-date-frequency"
                    type="select"
                    className="form-control"
                    name="frequency"
                    value={(!isNew && dueDateEntity.frequency) || 'WEEKLY'}
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
                  <AvInput id="due-date-college" type="select" className="form-control" name="collegeId">
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
                  <AvInput id="due-date-branch" type="select" className="form-control" name="branchId">
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
                <Button tag={Link} id="cancel-save" to="/entity/due-date" replace color="info">
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
  dueDateEntity: storeState.dueDate.entity,
  loading: storeState.dueDate.loading,
  updating: storeState.dueDate.updating,
  updateSuccess: storeState.dueDate.updateSuccess
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
)(DueDateUpdate);
