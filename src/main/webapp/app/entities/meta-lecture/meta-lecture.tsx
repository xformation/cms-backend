import * as React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import FontAwesomeIcon from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './meta-lecture.reducer';
import { IMetaLecture } from 'app/shared/model/meta-lecture.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IMetaLectureProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface IMetaLectureState {
  search: string;
}

export class MetaLecture extends React.Component<IMetaLectureProps, IMetaLectureState> {
  state: IMetaLectureState = {
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
    const { metaLectureList, match } = this.props;
    return (
      <div>
        <h2 id="meta-lecture-heading">
          Meta Lectures
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp; Create new Meta Lecture
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
                <th>Week Day</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Branch</th>
                <th>Department</th>
                <th>Subject</th>
                <th>Teacher</th>
                <th>Term</th>
                <th>Academicyear</th>
                <th>Section</th>
                <th>Batch</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {metaLectureList.map((metaLecture, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${metaLecture.id}`} color="link" size="sm">
                      {metaLecture.id}
                    </Button>
                  </td>
                  <td>{metaLecture.weekDay}</td>
                  <td>{metaLecture.startTime}</td>
                  <td>{metaLecture.endTime}</td>
                  <td>{metaLecture.branchId ? <Link to={`branch/${metaLecture.branchId}`}>{metaLecture.branchId}</Link> : ''}</td>
                  <td>
                    {metaLecture.departmentId ? <Link to={`department/${metaLecture.departmentId}`}>{metaLecture.departmentId}</Link> : ''}
                  </td>
                  <td>{metaLecture.subjectId ? <Link to={`subject/${metaLecture.subjectId}`}>{metaLecture.subjectId}</Link> : ''}</td>
                  <td>{metaLecture.teacherId ? <Link to={`teacher/${metaLecture.teacherId}`}>{metaLecture.teacherId}</Link> : ''}</td>
                  <td>{metaLecture.termId ? <Link to={`term/${metaLecture.termId}`}>{metaLecture.termId}</Link> : ''}</td>
                  <td>
                    {metaLecture.academicyearId ? (
                      <Link to={`academicYear/${metaLecture.academicyearId}`}>{metaLecture.academicyearId}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>{metaLecture.sectionId ? <Link to={`section/${metaLecture.sectionId}`}>{metaLecture.sectionId}</Link> : ''}</td>
                  <td>{metaLecture.batchId ? <Link to={`batch/${metaLecture.batchId}`}>{metaLecture.batchId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${metaLecture.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${metaLecture.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${metaLecture.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ metaLecture }: IRootState) => ({
  metaLectureList: metaLecture.entities
});

const mapDispatchToProps = {
  getSearchEntities,
  getEntities
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MetaLecture);
