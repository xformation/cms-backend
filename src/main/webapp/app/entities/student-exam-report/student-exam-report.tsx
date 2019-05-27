import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './student-exam-report.reducer';
import { IStudentExamReport } from 'app/shared/model/student-exam-report.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IStudentExamReportProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IStudentExamReportState {
  search: string;
}

export class StudentExamReport extends React.Component<IStudentExamReportProps, IStudentExamReportState> {
  state: IStudentExamReportState = {
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
    const { studentExamReportList, match } = this.props;
    return (
      <div>
        <h2 id="student-exam-report-heading">
          Student Exam Reports
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Student Exam Report
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
                <th>Marks Obtained</th>
                <th>Comments</th>
                <th>Created On</th>
                <th>Created By</th>
                <th>Updated On</th>
                <th>Updated By</th>
                <th>Academic Exam Setting</th>
                <th>Student</th>
                <th>Type Of Grading</th>
                <th>Batch</th>
                <th>Academicyear</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {studentExamReportList.map((studentExamReport, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${studentExamReport.id}`} color="link" size="sm">
                      {studentExamReport.id}
                    </Button>
                  </td>
                  <td>{studentExamReport.marksObtained}</td>
                  <td>{studentExamReport.comments}</td>
                  <td>
                    <TextFormat type="date" value={studentExamReport.createdOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{studentExamReport.createdBy}</td>
                  <td>
                    <TextFormat type="date" value={studentExamReport.updatedOn} format={APP_LOCAL_DATE_FORMAT} />
                  </td>
                  <td>{studentExamReport.updatedBy}</td>
                  <td>
                    {studentExamReport.academicExamSettingId ? (
                      <Link to={`academic-exam-setting/${studentExamReport.academicExamSettingId}`}>
                        {studentExamReport.academicExamSettingId}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {studentExamReport.studentId ? (
                      <Link to={`student/${studentExamReport.studentId}`}>{studentExamReport.studentId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {studentExamReport.typeOfGradingId ? (
                      <Link to={`type-of-grading/${studentExamReport.typeOfGradingId}`}>{studentExamReport.typeOfGradingId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {studentExamReport.batchId ? <Link to={`batch/${studentExamReport.batchId}`}>{studentExamReport.batchId}</Link> : ''}
                  </td>
                  <td>
                    {studentExamReport.academicyearId ? (
                      <Link to={`academic-year/${studentExamReport.academicyearId}`}>{studentExamReport.academicyearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${studentExamReport.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentExamReport.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${studentExamReport.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ studentExamReport }: IRootState) => ({
  studentExamReportList: studentExamReport.entities
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
)(StudentExamReport);
