import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './type-of-grading.reducer';
import { ITypeOfGrading } from 'app/shared/model/type-of-grading.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITypeOfGradingProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ITypeOfGradingState {
  search: string;
}

export class TypeOfGrading extends React.Component<ITypeOfGradingProps, ITypeOfGradingState> {
  state: ITypeOfGradingState = {
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
    const { typeOfGradingList, match } = this.props;
    return (
      <div>
        <h2 id="type-of-grading-heading">
          Type Of Gradings
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Type Of Grading
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
                <th>Min Marks</th>
                <th>Max Marks</th>
                <th>Grades</th>
                <th>Academic Exam Setting</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {typeOfGradingList.map((typeOfGrading, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${typeOfGrading.id}`} color="link" size="sm">
                      {typeOfGrading.id}
                    </Button>
                  </td>
                  <td>{typeOfGrading.minMarks}</td>
                  <td>{typeOfGrading.maxMarks}</td>
                  <td>{typeOfGrading.grades}</td>
                  <td>
                    {typeOfGrading.academicExamSettingId ? (
                      <Link to={`academic-exam-setting/${typeOfGrading.academicExamSettingId}`}>{typeOfGrading.academicExamSettingId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${typeOfGrading.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${typeOfGrading.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${typeOfGrading.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ typeOfGrading }: IRootState) => ({
  typeOfGradingList: typeOfGrading.entities
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
)(TypeOfGrading);
