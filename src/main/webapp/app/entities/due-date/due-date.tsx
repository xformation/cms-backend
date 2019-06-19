import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './due-date.reducer';
import { IDueDate } from 'app/shared/model/due-date.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDueDateProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IDueDateState {
  search: string;
}

export class DueDate extends React.Component<IDueDateProps, IDueDateState> {
  state: IDueDateState = {
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
    this.setState({ search: '' }, () => {
      this.props.getEntities();
    });
  };

  handleSearch = event => this.setState({ search: event.target.value });

  render() {
    const { dueDateList, match } = this.props;
    return (
      <div>
        <h2 id="due-date-heading">
          Due Dates
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Due Date
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
                <th>Payment Method</th>
                <th>Installments</th>
                <th>Day Desc</th>
                <th>Payment Date</th>
                <th>Frequency</th>
                <th>College</th>
                <th>Branch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dueDateList.map((dueDate, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${dueDate.id}`} color="link" size="sm">
                      {dueDate.id}
                    </Button>
                  </td>
                  <td>{dueDate.paymentMethod}</td>
                  <td>{dueDate.installments}</td>
                  <td>{dueDate.dayDesc}</td>
                  <td>
                    <TextFormat type="date" value={dueDate.paymentDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{dueDate.frequency}</td>
                  <td>{dueDate.collegeId ? <Link to={`college/${dueDate.collegeId}`}>{dueDate.collegeId}</Link> : ''}</td>
                  <td>{dueDate.branchId ? <Link to={`branch/${dueDate.branchId}`}>{dueDate.branchId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${dueDate.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dueDate.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${dueDate.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ dueDate }: IRootState) => ({
  dueDateList: dueDate.entities
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
)(DueDate);
