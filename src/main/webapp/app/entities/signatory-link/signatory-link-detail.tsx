import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './signatory-link.reducer';
import { ISignatoryLink } from 'app/shared/model/signatory-link.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISignatoryLinkDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class SignatoryLinkDetail extends React.Component<ISignatoryLinkDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { signatoryLinkEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            SignatoryLink [<b>{signatoryLinkEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="desc">Desc</span>
            </dt>
            <dd>{signatoryLinkEntity.desc}</dd>
            <dt>Authorized Signatory</dt>
            <dd>{signatoryLinkEntity.authorizedSignatoryId ? signatoryLinkEntity.authorizedSignatoryId : ''}</dd>
            <dt>Legal Entity</dt>
            <dd>{signatoryLinkEntity.legalEntityId ? signatoryLinkEntity.legalEntityId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/signatory-link" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/signatory-link/${signatoryLinkEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ signatoryLink }: IRootState) => ({
  signatoryLinkEntity: signatoryLink.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(SignatoryLinkDetail);
