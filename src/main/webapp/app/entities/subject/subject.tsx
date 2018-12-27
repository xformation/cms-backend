import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './subject.reducer';
import { ISubject } from 'app/shared/model/subject.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ISubjectProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ISubjectState {
  search: string;
}

export class Subject extends React.Component<ISubjectProps, ISubjectState> {
  state: ISubjectState = {
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
    const { subjectList, match } = this.props;
    return (
      <div>
        <h2 id="subject-heading">
          <Translate contentKey="cmsApp.subject.home.title">Subjects</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.subject.home.createLabel">Create new Subject</Translate>
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
                    placeholder={translate('cmsApp.subject.home.search')}
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
                  <Translate contentKey="cmsApp.subject.commonSub">Common Sub</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.subject.electiveSub">Elective Sub</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.subject.periods">Periods</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.subject.student">Student</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.subject.teacher">Teacher</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {subjectList.map((subject, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${subject.id}`} color="link" size="sm">
                      {subject.id}
                    </Button>
                  </td>
                  <td>
                    <Translate contentKey={`cmsApp.Common.${subject.commonSub}`} />
                  </td>
                  <td>
                    <Translate contentKey={`cmsApp.Elective.${subject.electiveSub}`} />
                  </td>
                  <td>{subject.periodsId ? <Link to={`periods/${subject.periodsId}`}>{subject.periodsId}</Link> : ''}</td>
                  <td>{subject.studentId ? <Link to={`student/${subject.studentId}`}>{subject.studentId}</Link> : ''}</td>
                  <td>{subject.teacherId ? <Link to={`teacher/${subject.teacherId}`}>{subject.teacherId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${subject.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${subject.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${subject.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ subject }: IRootState) => ({
  subjectList: subject.entities
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
)(Subject);