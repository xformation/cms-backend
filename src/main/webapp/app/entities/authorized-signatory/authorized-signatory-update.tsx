import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IBranch } from 'app/shared/model/branch.model';
import { getEntities as getBranches } from 'app/entities/branch/branch.reducer';
import { ICollege } from 'app/shared/model/college.model';
import { getEntities as getColleges } from 'app/entities/college/college.reducer';
import { getEntity, updateEntity, createEntity, reset } from './authorized-signatory.reducer';
import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IAuthorizedSignatoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IAuthorizedSignatoryUpdateState {
  isNew: boolean;
  branchId: string;
  collegeId: string;
}

export class AuthorizedSignatoryUpdate extends React.Component<IAuthorizedSignatoryUpdateProps, IAuthorizedSignatoryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      branchId: '0',
      collegeId: '0',
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

    this.props.getBranches();
    this.props.getColleges();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { authorizedSignatoryEntity } = this.props;
      const entity = {
        ...authorizedSignatoryEntity,
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
    this.props.history.push('/entity/authorized-signatory');
  };

  render() {
    const { authorizedSignatoryEntity, branches, colleges, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.authorizedSignatory.home.createOrEditLabel">Create or edit a AuthorizedSignatory</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : authorizedSignatoryEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="authorized-signatory-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="signatoryNameLabel" for="signatoryName">
                    Signatory Name
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryName"
                    type="text"
                    name="signatoryName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="signatoryFatherNameLabel" for="signatoryFatherName">
                    Signatory Father Name
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryFatherName"
                    type="text"
                    name="signatoryFatherName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="signatoryDesignationLabel" for="signatoryDesignation">
                    Signatory Designation
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryDesignation"
                    type="text"
                    name="signatoryDesignation"
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
                    id="authorized-signatory-address1"
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
                  <AvField id="authorized-signatory-address2" type="text" name="address2" />
                </AvGroup>
                <AvGroup>
                  <Label id="address3Label" for="address3">
                    Address 3
                  </Label>
                  <AvField id="authorized-signatory-address3" type="text" name="address3" />
                </AvGroup>
                <AvGroup>
                  <Label id="address4Label" for="address4">
                    Address 4
                  </Label>
                  <AvField id="authorized-signatory-address4" type="text" name="address4" />
                </AvGroup>
                <AvGroup>
                  <Label id="address5Label" for="address5">
                    Address 5
                  </Label>
                  <AvField id="authorized-signatory-address5" type="text" name="address5" />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    Email
                  </Label>
                  <AvField
                    id="authorized-signatory-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="panCardNumberLabel" for="panCardNumber">
                    Pan Card Number
                  </Label>
                  <AvField
                    id="authorized-signatory-panCardNumber"
                    type="text"
                    name="panCardNumber"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="branch.id">Branch</Label>
                  <AvInput id="authorized-signatory-branch" type="select" className="form-control" name="branchId">
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
                  <Label for="college.id">College</Label>
                  <AvInput id="authorized-signatory-college" type="select" className="form-control" name="collegeId">
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
                <Button tag={Link} id="cancel-save" to="/entity/authorized-signatory" replace color="info">
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
  branches: storeState.branch.entities,
  colleges: storeState.college.entities,
  authorizedSignatoryEntity: storeState.authorizedSignatory.entity,
  loading: storeState.authorizedSignatory.loading,
  updating: storeState.authorizedSignatory.updating,
  updateSuccess: storeState.authorizedSignatory.updateSuccess
});

const mapDispatchToProps = {
  getBranches,
  getColleges,
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
)(AuthorizedSignatoryUpdate);
