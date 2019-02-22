import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './transport-route.reducer';
import { ITransportRoute } from 'app/shared/model/transport-route.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransportRouteDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class TransportRouteDetail extends React.Component<ITransportRouteDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { transportRouteEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            TransportRoute [<b>{transportRouteEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="routeName">Route Name</span>
            </dt>
            <dd>{transportRouteEntity.routeName}</dd>
            <dt>
              <span id="routeDetails">Route Details</span>
            </dt>
            <dd>{transportRouteEntity.routeDetails}</dd>
            <dt>
              <span id="routeMapUrl">Route Map Url</span>
            </dt>
            <dd>{transportRouteEntity.routeMapUrl}</dd>
          </dl>
          <Button tag={Link} to="/entity/transport-route" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/transport-route/${transportRouteEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ transportRoute }: IRootState) => ({
  transportRouteEntity: transportRoute.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(TransportRouteDetail);
