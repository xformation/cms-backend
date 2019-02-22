import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './transport-route.reducer';
import { ITransportRoute } from 'app/shared/model/transport-route.model';
// tslint:disable-next-line:no-unused-variable
import { convertDateTimeFromServer } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITransportRouteUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export interface ITransportRouteUpdateState {
  isNew: boolean;
}

export class TransportRouteUpdate extends React.Component<ITransportRouteUpdateProps, ITransportRouteUpdateState> {
  constructor(props) {
    super(props);
    this.state = {
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
  }

  saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const { transportRouteEntity } = this.props;
      const entity = {
        ...transportRouteEntity,
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
    this.props.history.push('/entity/transport-route');
  };

  render() {
    const { transportRouteEntity, loading, updating } = this.props;
    const { isNew } = this.state;

    return (
      <div>
        <Row className="justify-content-center">
          <Col md="8">
            <h2 id="cmsApp.transportRoute.home.createOrEditLabel">Create or edit a TransportRoute</h2>
          </Col>
        </Row>
        <Row className="justify-content-center">
          <Col md="8">
            {loading ? (
              <p>Loading...</p>
            ) : (
              <AvForm model={isNew ? {} : transportRouteEntity} onSubmit={this.saveEntity}>
                {!isNew ? (
                  <AvGroup>
                    <Label for="id">ID</Label>
                    <AvInput id="transport-route-id" type="text" className="form-control" name="id" required readOnly />
                  </AvGroup>
                ) : null}
                <AvGroup>
                  <Label id="routeNameLabel" for="routeName">
                    Route Name
                  </Label>
                  <AvField
                    id="transport-route-routeName"
                    type="text"
                    name="routeName"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="routeDetailsLabel" for="routeDetails">
                    Route Details
                  </Label>
                  <AvField
                    id="transport-route-routeDetails"
                    type="text"
                    name="routeDetails"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <AvGroup>
                  <Label id="routeMapUrlLabel" for="routeMapUrl">
                    Route Map Url
                  </Label>
                  <AvField
                    id="transport-route-routeMapUrl"
                    type="text"
                    name="routeMapUrl"
                    validate={{
                      required: { value: true, errorMessage: 'This field is required.' }
                    }}
                  />
                </AvGroup>
                <Button tag={Link} id="cancel-save" to="/entity/transport-route" replace color="info">
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
  transportRouteEntity: storeState.transportRoute.entity,
  loading: storeState.transportRoute.loading,
  updating: storeState.transportRoute.updating,
  updateSuccess: storeState.transportRoute.updateSuccess
});

const mapDispatchToProps = {
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
)(TransportRouteUpdate);
