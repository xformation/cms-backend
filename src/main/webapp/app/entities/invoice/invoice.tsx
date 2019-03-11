import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './invoice.reducer';
import { IInvoice } from 'app/shared/model/invoice.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IInvoiceProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IInvoiceState {
  search: string;
}

export class Invoice extends React.Component<IInvoiceProps, IInvoiceState> {
  state: IInvoiceState = {
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
    const { invoiceList, match } = this.props;
    return (
      <div>
        <h2 id="invoice-heading">
          Invoices
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Invoice
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
                <th>Invoice Number</th>
                <th>Amount Paid</th>
                <th>Payment Date</th>
                <th>Next Payment Date</th>
                <th>Out Standing Amount</th>
                <th>Mode Of Payment</th>
                <th>Cheque Number</th>
                <th>Demand Draft Number</th>
                <th>Online Txn Ref Number</th>
                <th>Payment Status</th>
                <th>Comments</th>
                <th>Updated By</th>
                <th>Updated On</th>
                <th>Fee Category</th>
                <th>Fee Details</th>
                <th>Due Date</th>
                <th>Payment Remainder</th>
                <th>College</th>
                <th>Branch</th>
                <th>Student</th>
                <th>Academic Year</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {invoiceList.map((invoice, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${invoice.id}`} color="link" size="sm">
                      {invoice.id}
                    </Button>
                  </td>
                  <td>{invoice.invoiceNumber}</td>
                  <td>{invoice.amountPaid}</td>
                  <td>
                    <TextFormat type="date" value={invoice.paymentDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={invoice.nextPaymentDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{invoice.outStandingAmount}</td>
                  <td>{invoice.modeOfPayment}</td>
                  <td>{invoice.chequeNumber}</td>
                  <td>{invoice.demandDraftNumber}</td>
                  <td>{invoice.onlineTxnRefNumber}</td>
                  <td>{invoice.paymentStatus}</td>
                  <td>{invoice.comments}</td>
                  <td>{invoice.updatedBy}</td>
                  <td>
                    <TextFormat type="date" value={invoice.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{invoice.feeCategoryId ? <Link to={`feeCategory/${invoice.feeCategoryId}`}>{invoice.feeCategoryId}</Link> : ''}</td>
                  <td>{invoice.feeDetailsId ? <Link to={`feeDetails/${invoice.feeDetailsId}`}>{invoice.feeDetailsId}</Link> : ''}</td>
                  <td>{invoice.dueDateId ? <Link to={`dueDate/${invoice.dueDateId}`}>{invoice.dueDateId}</Link> : ''}</td>
                  <td>
                    {invoice.paymentRemainderId ? (
                      <Link to={`paymentRemainder/${invoice.paymentRemainderId}`}>{invoice.paymentRemainderId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{invoice.collegeId ? <Link to={`college/${invoice.collegeId}`}>{invoice.collegeId}</Link> : ''}</td>
                  <td>{invoice.branchId ? <Link to={`branch/${invoice.branchId}`}>{invoice.branchId}</Link> : ''}</td>
                  <td>{invoice.studentId ? <Link to={`student/${invoice.studentId}`}>{invoice.studentId}</Link> : ''}</td>
                  <td>
                    {invoice.academicYearId ? <Link to={`academicYear/${invoice.academicYearId}`}>{invoice.academicYearId}</Link> : ''}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${invoice.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${invoice.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${invoice.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ invoice }: IRootState) => ({
  invoiceList: invoice.entities
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
)(Invoice);
