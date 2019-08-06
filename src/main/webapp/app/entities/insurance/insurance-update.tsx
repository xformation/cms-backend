import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IVehicle } from 'app/shared/model/vehicle.model';
import { getEntities as getVehicles } from 'app/entities/vehicle/vehicle.reducer';
import { getEntity, updateEntity, createEntity, reset } from './insurance.reducer';
import { IInsurance } from 'app/shared/model/insurance.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IInsuranceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface IInsuranceUpdateState {
  isNew: boolean;
  vehicleId: string;
}

export class InsuranceUpdate extends React.Component<IInsuranceUpdateProps, IInsuranceUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      vehicleId: '0',
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

    this.props.getVehicles();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { insuranceEntity } = this.props;
      const entity = {
        ...insuranceEntity,
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
    this.props.history.push('/entity/insurance');
  };

  render() {
    const { insuranceEntity, vehicles, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.insurance.home.createOrEditLabel">Create or edit a Insurance</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : insuranceEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="insurance-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="insuranceCompanyLabel" for="insuranceCompany">
                    Insurance Company
                  </Label>
                  <AvField
                    id="insurance-insuranceCompany"
                    type="text"
                    name="insuranceCompany"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="typeOfInsuranceLabel">Type Of Insurance</Label>
                  <AvInput
                    id="insurance-typeOfInsurance"
                    type="select"
                    className="form-control"
                    name="typeOfInsurance"
                    value={(!isNew && insuranceEntity.typeOfInsurance) || 'LIABILITY'}
                  >
                    <option value="LIABILITY">LIABILITY</option>
                    <option value="COLLISION">COLLISION</option>
                    <option value="COMPREHENSIVE">COMPREHENSIVE</option>
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label id="dateOfInsuranceLabel" for="dateOfInsurance">
                    Date Of Insurance
                  </Label>
                  <AvField
                    id="insurance-dateOfInsurance"
                    type="date"
                    className="form-control"
                    name="dateOfInsurance"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="validTillLabel" for="validTill">
                    Valid Till
                  </Label>
                  <AvField
                    id="insurance-validTill"
                    type="date"
                    className="form-control"
                    name="validTill"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/insurance" replace color="info">
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
  vehicles: storeState.vehicle.entities,
  insuranceEntity: storeState.insurance.entity,
  loading: storeState.insurance.loading,
  updating: storeState.insurance.updating,
  updateSuccess: storeState.insurance.updateSuccess
});

const mapDispatchToProps = {
  getVehicles,
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
)(InsuranceUpdate);
