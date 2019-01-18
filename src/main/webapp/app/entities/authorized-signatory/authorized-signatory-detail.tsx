import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './authorized-signatory.reducer';
import { IAuthorizedSignatory } from 'app/shared/model/authorized-signatory.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAuthorizedSignatoryDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class AuthorizedSignatoryDetail extends React.Component<IAuthorizedSignatoryDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { authorizedSignatoryEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            AuthorizedSignatory [<b>{authorizedSignatoryEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="signatoryName">Signatory Name</span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryName}</dd>
            <dt>
              <span id="signatoryFatherName">Signatory Father Name</span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryFatherName}</dd>
            <dt>
              <span id="signatoryDesignation">Signatory Designation</span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryDesignation}</dd>
            <dt>
              <span id="address">Address</span>
            </dt>
            <dd>{authorizedSignatoryEntity.address}</dd>
            <dt>
              <span id="email">Email</span>
            </dt>
            <dd>{authorizedSignatoryEntity.email}</dd>
            <dt>
              <span id="panCardNumber">Pan Card Number</span>
            </dt>
            <dd>{authorizedSignatoryEntity.panCardNumber}</dd>
            <dt>Legal Entity</dt>
            <dd>{authorizedSignatoryEntity.legalEntityId ? authorizedSignatoryEntity.legalEntityId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/authorized-signatory" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/authorized-signatory/${authorizedSignatoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ authorizedSignatory }: IRootState) => ({
  authorizedSignatoryEntity: authorizedSignatory.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(AuthorizedSignatoryDetail);
