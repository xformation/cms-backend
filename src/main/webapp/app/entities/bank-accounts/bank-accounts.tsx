import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './bank-accounts.reducer';
import { IBankAccounts } from 'app/shared/model/bank-accounts.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBankAccountsProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IBankAccountsState {
  search: string;
}

export class BankAccounts extends React.Component<IBankAccountsProps, IBankAccountsState> {
  state: IBankAccountsState = {
    search: ''
  };

  componentDidMount() {
    this.props.getEntities();
  }

  search = () => {
    if (this.state.search) {
      this.props.getSearchEntities(this.state.search);
    }
  };

  clear = () => {
    this.props.getEntities();
    this.setState({
      search: ''
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { bankAccountsList, match } = this.props;
    return (
      <div>
        <h2 id="bank-accounts-heading">
          <Translate contentKey="cmsApp.bankAccounts.home.title">Bank Accounts</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.bankAccounts.home.createLabel">Create new Bank Accounts</Translate>
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput
                    type="text"
                    name="search"
                    value={this.state.search}
                    onChange={this.handleSearch}
                    placeholder={translate('cmsApp.bankAccounts.home.search')}
                  />
                  <Button className="input-group-addon">
                    <FontAwesomeIcon icon="search" />
                  </Button>
                  <Button type="reset" className="input-group-addon" onClick={this.clear}>
                    <FontAwesomeIcon icon="trash" />
                  </Button>
                </InputGroup>
              </AvGroup>
            </AvForm>
          </Col>
        </Row>
        <div className="table-responsive">
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.nameOfBank">Name Of Bank</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.typeOfAccount">Type Of Account</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.ifsCode">Ifs Code</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.branch">Branch</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.bankAccounts.corporateId">Corporate Id</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bankAccountsList.map((bankAccounts, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${bankAccounts.id}`} color="link" size="sm">
                      {bankAccounts.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`cmsApp.NameOfBank.${bankAccounts.nameOfBank}`} />
                  </td>
                  <td>{bankAccounts.accountNumber}</td>
                  <td>{bankAccounts.typeOfAccount}</td>
                  <td>{bankAccounts.ifsCode}</td>
                  <td>{bankAccounts.branch}</td>
                  <td>{bankAccounts.corporateId}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${bankAccounts.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankAccounts.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${bankAccounts.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        </div>
      </div>
    );
  }
}

const mapStateToProps = ({ bankAccounts }: IRootState) => ({
  bankAccountsList: bankAccounts.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BankAccounts);
