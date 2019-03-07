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
import { ICity } from 'app/shared/model/city.model';
import { getEntities as getCities } from 'app/entities/city/city.reducer';
import { IState } from 'app/shared/model/state.model';
import { getEntities as getStates } from 'app/entities/state/state.reducer';
import { getEntity, updateEntity, createEntity, reset } from './branch.reducer';
import { IBranch } from 'app/shared/model/branch.model';
// tslint:disable-next-line:no-unused-variable

export interface IBranchUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IBranchUpdateState {
  isNew: boolean;
  collegeId: string;
  cityId: string;
  stateId: string;
}

export class BranchUpdate extends React.Component<IBranchUpdateProps, IBranchUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      collegeId: '0',
      cityId: '0',
      stateId: '0',
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
    this.props.getCities();
    this.props.getStates();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { branchEntity } = this.props;
      const entity = {
        ...branchEntity,
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
    this.props.history.push('/entity/branch');
  };

  render() {
    const { branchEntity, colleges, cities, states, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.branch.home.createOrEditLabel">Create or edit a Branch</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : branchEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="branch-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="branchNameLabel" for="branchName">
                    Branch Name
                  </Label>
                  <AvField
                    id="branch-branchName"
                    type="text"
                    name="branchName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="address1Label" for="address1">
                    Address 1
                  </Label>
                  <AvField
                    id="branch-address1"
                    type="text"
                    name="address1"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="address2Label" for="address2">
                    Address 2
                  </Label>
                  <AvField
                    id="branch-address2"
                    type="text"
                    name="address2"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="branchHeadLabel" for="branchHead">
                    Branch Head
                  </Label>
                  <AvField
                    id="branch-branchHead"
                    type="text"
                    name="branchHead"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="college.id">College</Label>
                  <AvInput id="branch-college" type="select" className="form-control" name="collegeId">
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
                  <Label for="city.id">City</Label>
                  <AvInput id="branch-city" type="select" className="form-control" name="cityId">
                    <option value="" key="0" />
                    {cities
                      ? cities.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="state.id">State</Label>
                  <AvInput id="branch-state" type="select" className="form-control" name="stateId">
                    <option value="" key="0" />
                    {states
                      ? states.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/branch" replace color="info">
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
  cities: storeState.city.entities,
  states: storeState.state.entities,
  branchEntity: storeState.branch.entity,
  loading: storeState.branch.loading,
  updating: storeState.branch.updating,
  updateSuccess: storeState.branch.updateSuccess
});

const mapDispatchToProps = {
  getColleges,
  getCities,
  getStates,
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
)(BranchUpdate);
