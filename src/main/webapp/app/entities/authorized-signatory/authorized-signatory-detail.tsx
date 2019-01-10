import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
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
            <Translate contentKey="cmsApp.authorizedSignatory.detail.title">AuthorizedSignatory</Translate> [<b>
              {authorizedSignatoryEntity.id}
            </b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="signatoryName">
                <Translate contentKey="cmsApp.authorizedSignatory.signatoryName">Signatory Name</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryName}</dd>
            <dt>
              <span id="signatoryFatherName">
                <Translate contentKey="cmsApp.authorizedSignatory.signatoryFatherName">Signatory Father Name</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryFatherName}</dd>
            <dt>
              <span id="signatoryDesignation">
                <Translate contentKey="cmsApp.authorizedSignatory.signatoryDesignation">Signatory Designation</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.signatoryDesignation}</dd>
            <dt>
              <span id="address">
                <Translate contentKey="cmsApp.authorizedSignatory.address">Address</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.address}</dd>
            <dt>
              <span id="email">
                <Translate contentKey="cmsApp.authorizedSignatory.email">Email</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.email}</dd>
            <dt>
              <span id="panCardNumber">
                <Translate contentKey="cmsApp.authorizedSignatory.panCardNumber">Pan Card Number</Translate>
              </span>
            </dt>
            <dd>{authorizedSignatoryEntity.panCardNumber}</dd>
            <dt>
              <Translate contentKey="cmsApp.authorizedSignatory.legalentity">Legalentity</Translate>
            </dt>
            <dd>{authorizedSignatoryEntity.legalentityId ? authorizedSignatoryEntity.legalentityId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/authorized-signatory" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/authorized-signatory/${authorizedSignatoryEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
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
