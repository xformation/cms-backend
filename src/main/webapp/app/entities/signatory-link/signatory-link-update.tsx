import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
import { getEntities as getAuthorizedSignatories } from 'app/entities/authorized-signatory/authorized-signatory.reducer';
import { ILegalEntity } from 'app/shared/model/legal-entity.model';
import { getEntities as getLegalEntities } from 'app/entities/legal-entity/legal-entity.reducer';
import { getEntity, updateEntity, createEntity, reset } from './signatory-link.reducer';
import { ISignatoryLink } from 'app/shared/model/signatory-link.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ISignatoryLinkUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ISignatoryLinkUpdateState {
  isNew: boolean;
  authorizedSignatoryId: string;
  legalEntityId: string;
}

export class SignatoryLinkUpdate extends React.Component<ISignatoryLinkUpdateProps, ISignatoryLinkUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
      authorizedSignatoryId: '0',
      legalEntityId: '0',
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

    this.props.getAuthorizedSignatories();
    this.props.getLegalEntities();
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { signatoryLinkEntity } = this.props;
      const entity = {
        ...signatoryLinkEntity,
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
    this.props.history.push('/entity/signatory-link');
  };

  render() {
    const { signatoryLinkEntity, authorizedSignatories, legalEntities, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.signatoryLink.home.createOrEditLabel">Create or edit a SignatoryLink</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : signatoryLinkEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="signatory-link-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="descLabel" for="desc">
                    Desc
                  </Label>
                  <AvField id="signatory-link-desc" type="text" name="desc" />
                </AvGroup>
                <AvGroup>
                  <Label for="authorizedSignatory.id">Authorized Signatory</Label>
                  <AvInput id="signatory-link-authorizedSignatory" type="select" className="form-control" name="authorizedSignatoryId">
                    <option value="" key="0" />
                    {authorizedSignatories
                      ? authorizedSignatories.map(otherEntity => (
                          <option value={otherEntity.id} key={otherEntity.id}>
                            {otherEntity.id}
                          </option>
                        ))
                      : null}
                  </AvInput>
                </AvGroup>
                <AvGroup>
                  <Label for="legalEntity.id">Legal Entity</Label>
                  <AvInput id="signatory-link-legalEntity" type="select" className="form-control" name="legalEntityId">
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
                <Button tag={Link} id="cancel-save" to="/entity/signatory-link" replace color="info">
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
  authorizedSignatories: storeState.authorizedSignatory.entities,
  legalEntities: storeState.legalEntity.entities,
  signatoryLinkEntity: storeState.signatoryLink.entity,
  loading: storeState.signatoryLink.loading,
  updating: storeState.signatoryLink.updating,
  updateSuccess: storeState.signatoryLink.updateSuccess
});

const mapDispatchToProps = {
  getAuthorizedSignatories,
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
)(SignatoryLinkUpdate);
