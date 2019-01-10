import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './bank-accounts.reducer';
import { IBankAccounts } from 'app/shared/model/bank-accounts.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankAccountsDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: number }> {}

export class BankAccountsDetail extends React.Component<IBankAccountsDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bankAccountsEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="cmsApp.bankAccounts.detail.title">BankAccounts</Translate> [<b>{bankAccountsEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="nameOfBank">
                <Translate contentKey="cmsApp.bankAccounts.nameOfBank">Name Of Bank</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.nameOfBank}</dd>
            <dt>
              <span id="accountNumber">
                <Translate contentKey="cmsApp.bankAccounts.accountNumber">Account Number</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.accountNumber}</dd>
            <dt>
              <span id="typeOfAccount">
                <Translate contentKey="cmsApp.bankAccounts.typeOfAccount">Type Of Account</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.typeOfAccount}</dd>
            <dt>
              <span id="ifsCode">
                <Translate contentKey="cmsApp.bankAccounts.ifsCode">Ifs Code</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.ifsCode}</dd>
            <dt>
              <span id="branch">
                <Translate contentKey="cmsApp.bankAccounts.branch">Branch</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.branch}</dd>
            <dt>
              <span id="corporateId">
                <Translate contentKey="cmsApp.bankAccounts.corporateId">Corporate Id</Translate>
              </span>
            </dt>
            <dd>{bankAccountsEntity.corporateId}</dd>
          </dl>
          <Button tag={Link} to="/entity/bank-accounts" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/bank-accounts/${bankAccountsEntity.id}/edit`} replace color="primary">
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

const mapStateToProps = ({ bankAccounts }: IRootState) => ({
  bankAccountsEntity: bankAccounts.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankAccountsDetail);
