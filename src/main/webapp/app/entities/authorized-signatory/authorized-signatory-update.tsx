import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { ILegalEntity } from 'app/shared/model/legal-entity.model';
import { getEntities as getLegalEntities } from 'app/entities/legal-entity/legal-entity.reducer';
import { getEntity, updateEntity, createEntity, reset } from './authorized-signatory.reducer';
import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

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

  render() {
    const { authorizedSignatoryEntity, legalEntities, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.authorizedSignatory.home.createOrEditLabel">
              <Translate contentKey="cmsApp.authorizedSignatory.home.createOrEditLabel">Create or edit a AuthorizedSignatory</Translate>
            </h2>
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
                    <Label for="id">
                      <Translate contentKey="global.field.id">ID</Translate>
                    </Label>
                    <AvInput id="authorized-signatory-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="signatoryNameLabel" for="signatoryName">
                    <Translate contentKey="cmsApp.authorizedSignatory.signatoryName">Signatory Name</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryName"
                    type="text"
                    name="signatoryName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="signatoryFatherNameLabel" for="signatoryFatherName">
                    <Translate contentKey="cmsApp.authorizedSignatory.signatoryFatherName">Signatory Father Name</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryFatherName"
                    type="text"
                    name="signatoryFatherName"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="signatoryDesignationLabel" for="signatoryDesignation">
                    <Translate contentKey="cmsApp.authorizedSignatory.signatoryDesignation">Signatory Designation</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-signatoryDesignation"
                    type="text"
                    name="signatoryDesignation"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="addressLabel" for="address">
                    <Translate contentKey="cmsApp.authorizedSignatory.address">Address</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-address"
                    type="text"
                    name="address"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="emailLabel" for="email">
                    <Translate contentKey="cmsApp.authorizedSignatory.email">Email</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-email"
                    type="text"
                    name="email"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="panCardNumberLabel" for="panCardNumber">
                    <Translate contentKey="cmsApp.authorizedSignatory.panCardNumber">Pan Card Number</Translate>
                  </Label>
                  <AvField
                    id="authorized-signatory-panCardNumber"
                    type="text"
                    name="panCardNumber"
                    validate={{
                      required: { value: true, errorMessage: translate('entity.validation.required') }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label for="legalEntity.id">
                    <Translate contentKey="cmsApp.authorizedSignatory.legalEntity">Legal Entity</Translate>
                  </Label>
                  <AvInput id="authorized-signatory-legalEntity" type="select" className="form-control" name="legalEntityId">
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
                  <span className="d-none d-md-inline">
                    <Translate contentKey="entity.action.back">Back</Translate>
                  </span>
                </Button>
                &nbsp;
                <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                  <FontAwesomeIcon icon="save" />&nbsp;
                  <Translate contentKey="entity.action.save">Save</Translate>
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
