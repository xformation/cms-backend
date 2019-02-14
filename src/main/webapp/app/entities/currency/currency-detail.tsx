import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './currency.reducer';
import { ICurrency } from 'app/shared/model/currency.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICurrencyDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class CurrencyDetail extends React.Component<ICurrencyDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { currencyEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            Currency [<b>{currencyEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="currencyName">Currency Name</span>
            </dt>
            <dd>{currencyEntity.currencyName}</dd>
            <dt>
              <span id="currencyCode">Currency Code</span>
            </dt>
            <dd>{currencyEntity.currencyCode}</dd>
            <dt>
              <span id="currencySymbol">Currency Symbol</span>
            </dt>
            <dd>{currencyEntity.currencySymbol}</dd>
            <dt>Country</dt>
            <dd>{currencyEntity.countryId ? currencyEntity.countryId : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/currency" replace color="info">
            <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/currency/${currencyEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ currency }: IRootState) => ({
  currencyEntity: currency.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(CurrencyDetail);
