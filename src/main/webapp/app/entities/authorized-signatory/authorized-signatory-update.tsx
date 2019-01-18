import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILegalEntity } from 'app/shared/model/legal-entity.model';
import { getEntities as getLegalEntities } from 'app/entities/legal-entity/legal-entity.reducer';
import { getEntity, updateEntity, createEntity, reset } from './authorized-signatory.reducer';
import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { keysToValues } from 'app/shared/util/entity-utils';

export interface IAuthorizedSignatoryUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export interface IAuthorizedSignatoryUpdateState {
  isNew: boolean;
  legalEntityId: number;
}

export class AuthorizedSignatoryUpdate extends React.Component<IAuthorizedSignatoryUpdateProps, IAuthorizedSignatoryUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      legalEntityId: 0,
      isNew: !this.props.match.params || !this.props.match.params.id
    };
  }

  componentDidMount() {
    if (this.state.isNew) {
      this.props.reset();
    } else {
      this.props.getEntity(this.props.match.params.id);
    }

    this.props.getLegalEntities();
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
      this.handleClose();
    }
  };

  handleClose = () => {
    this.props.history.push('/entity/authorized-signatory');
  };

  legalEntityUpdate = element => {
    const id = element.target.value.toString();
    if (id === '') {
      this.setState({
        legalEntityId: -1
      });
    } else {
      for (const i in this.props.legalEntities) {
        if (id === this.props.legalEntities[i].id.toString()) {
          this.setState({
            legalEntityId: this.props.legalEntities[i].id
          });
        }
      }
    }
  };

  render() {
    const { authorizedSignatoryEntity, legalEntities, loading, updating } = this.props;
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
                  <Label id="addressLabel" for="address">
                    Address
                  </Label>
                  <AvField
                    id="authorized-signatory-address"
                    type="text"
                    name="address"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
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
                  <Label for="legalEntity.id">Legal Entity</Label>
                  <AvInput
                    id="authorized-signatory-legalEntity"
                    type="select"
                    className="form-control"
                    name="legalEntityId"
                    onChange={this.legalEntityUpdate}
                  >
                    <option value="" key="0" />
                    {legalEntities
                      ? legalEntities.map(otherEntity => (
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
  legalEntities: storeState.legalEntity.entities,
  authorizedSignatoryEntity: storeState.authorizedSignatory.entity,
  loading: storeState.authorizedSignatory.loading,
  updating: storeState.authorizedSignatory.updating
});

const mapDispatchToProps = {
  getLegalEntities,
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
