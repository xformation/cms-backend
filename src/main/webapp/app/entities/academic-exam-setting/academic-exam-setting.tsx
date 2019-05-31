import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './academic-exam-setting.reducer';
import { IAcademicExamSetting } from 'app/shared/model/academic-exam-setting.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IAcademicExamSettingProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IAcademicExamSettingState {
  search: string;
}

export class AcademicExamSetting extends React.Component<IAcademicExamSettingProps, IAcademicExamSettingState> {
  state: IAcademicExamSettingState = {
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
    const { academicExamSettingList, match } = this.props;
    return (
      <div>
        <h2 id="academic-exam-setting-heading">
          Academic Exam Settings
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Academic Exam Setting
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
                <th>Exam Type</th>
                <th>Semester</th>
                <th>Subject</th>
                <th>Exam Date</th>
                <th>Day</th>
                <th>Duration</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Grade Type</th>
                <th>Total</th>
                <th>Passing</th>
                <th>Actions</th>
                <th>Start Date</th>
                <th>End Date</th>
                <th>Department</th>
                <th>Academicyear</th>
                <th>Section</th>
                <th>Batch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {academicExamSettingList.map((academicExamSetting, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${academicExamSetting.id}`} color="link" size="sm">
                      {academicExamSetting.id}
                    </Button>
                  </td>
                  <td>{academicExamSetting.examType}</td>
                  <td>{academicExamSetting.semester}</td>
                  <td>{academicExamSetting.subject}</td>
                  <td>
                    <TextFormat type="date" value={academicExamSetting.examDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{academicExamSetting.day}</td>
                  <td>{academicExamSetting.duration}</td>
                  <td>{academicExamSetting.startTime}</td>
                  <td>{academicExamSetting.endTime}</td>
                  <td>{academicExamSetting.gradeType}</td>
                  <td>{academicExamSetting.total}</td>
                  <td>{academicExamSetting.passing}</td>
                  <td>{academicExamSetting.actions}</td>
                  <td>
                    <TextFormat type="date" value={academicExamSetting.startDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    <TextFormat type="date" value={academicExamSetting.endDate} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>
                    {academicExamSetting.departmentId ? (
                      <Link to={`department/${academicExamSetting.departmentId}`}>{academicExamSetting.departmentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {academicExamSetting.academicyearId ? (
                      <Link to={`academic-year/${academicExamSetting.academicyearId}`}>{academicExamSetting.academicyearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {academicExamSetting.sectionId ? (
                      <Link to={`section/${academicExamSetting.sectionId}`}>{academicExamSetting.sectionId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {academicExamSetting.batchId ? (
                      <Link to={`batch/${academicExamSetting.batchId}`}>{academicExamSetting.batchId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${academicExamSetting.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicExamSetting.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${academicExamSetting.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ academicExamSetting }: IRootState) => ({
  academicExamSettingList: academicExamSetting.entities
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
)(AcademicExamSetting);
