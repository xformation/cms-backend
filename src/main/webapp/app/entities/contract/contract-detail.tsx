import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './contract.reducer';
import { IContract } from 'app/shared/model/contract.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IContractDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class ContractDetail extends React.Component<IContractDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { contractEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Contract [<b>{contractEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="vendorName">Vendor Name</span>
            </dt>
            <dd>{contractEntity.vendorName}</dd>
            <dt>
              <span id="typeOfOwnerShip">Type Of Owner Ship</span>
            </dt>
            <dd>{contractEntity.typeOfOwnerShip}</dd>
            <dt>
              <span id="durationOfContract">Duration Of Contract</span>
            </dt>
            <dd>{contractEntity.durationOfContract}</dd>
            <dt>
              <span id="startDate">Start Date</span>
            </dt>
            <dd>
              <TextFormat value={contractEntity.startDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="endDate">End Date</span>
            </dt>
            <dd>
              <TextFormat value={contractEntity.endDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
          </dl>
          <Button tag={Link} to="/entity/contract" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/contract/${contractEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ contract }: IRootState) => ({
  contractEntity: contract.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(ContractDetail);
