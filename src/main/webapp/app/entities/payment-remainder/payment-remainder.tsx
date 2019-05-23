import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './payment-remainder.reducer';
import { IPaymentRemainder } from 'app/shared/model/payment-remainder.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPaymentRemainderProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IPaymentRemainderState {
  search: string;
}

export class PaymentRemainder extends React.Component<IPaymentRemainderProps, IPaymentRemainderState> {
  state: IPaymentRemainderState = {
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
    const { paymentRemainderList, match } = this.props;
    return (
      <div>
        <h2 id="payment-remainder-heading">
          Payment Remainders
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Payment Remainder
          </Link>
        </h2>
        <Row>
          <Col sm="12">
            <AvForm onSubmit={this.search}>
              <AvGroup>
                <InputGroup>
                  <AvInput type="text" name="search" value={this.state.search} onChange={this.handleSearch} placeholder="Search" />
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
                <th>ID</th>
                <th>Fee Remainder</th>
                <th>Notice Day</th>
                <th>Over Due Remainder</th>
                <th>Remainder Recipients</th>
                <th>Due Date</th>
                <th>College</th>
                <th>Branch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {paymentRemainderList.map((paymentRemainder, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${paymentRemainder.id}`} color="link" size="sm">
                      {paymentRemainder.id}
                    </Button>
                  </td>
                  <td>{paymentRemainder.feeRemainder}</td>
                  <td>{paymentRemainder.noticeDay}</td>
                  <td>{paymentRemainder.overDueRemainder}</td>
                  <td>{paymentRemainder.remainderRecipients}</td>
                  <td>
                    {paymentRemainder.dueDateId ? (
                      <Link to={`due-date/${paymentRemainder.dueDateId}`}>{paymentRemainder.dueDateId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {paymentRemainder.collegeId ? (
                      <Link to={`college/${paymentRemainder.collegeId}`}>{paymentRemainder.collegeId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {paymentRemainder.branchId ? <Link to={`branch/${paymentRemainder.branchId}`}>{paymentRemainder.branchId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${paymentRemainder.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${paymentRemainder.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${paymentRemainder.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
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

const mapStateToProps = ({ paymentRemainder }: IRootState) => ({
  paymentRemainderList: paymentRemainder.entities
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
)(PaymentRemainder);
