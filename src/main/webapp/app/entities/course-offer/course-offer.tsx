import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, InputGroup, Col, Row, Table } from 'reactstrap';
import { AvForm, AvGroup, AvInput } from 'availity-reactstrap-validation';
// tslint:disable-next-line:no-unused-variable
import { Translate, translate, ICrudSearchAction, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getSearchEntities, getEntities } from './course-offer.reducer';
import { ICourseOffer } from 'app/shared/model/course-offer.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ICourseOfferProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export interface ICourseOfferState {
  search: string;
}

export class CourseOffer extends React.Component<ICourseOfferProps, ICourseOfferState> {
  state: ICourseOfferState = {
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
    const { courseOfferList, match } = this.props;
    return (
      <div>
        <h2 id="course-offer-heading">
          <Translate contentKey="cmsApp.courseOffer.home.title">Course Offers</Translate>
          <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
            <FontAwesomeIcon icon="plus" />&nbsp;
            <Translate contentKey="cmsApp.courseOffer.home.createLabel">Create new Course Offer</Translate>
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
                    placeholder={translate('cmsApp.courseOffer.home.search')}
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
                  <Translate contentKey="cmsApp.courseOffer.desc">Desc</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.courseOffer.college">College</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.courseOffer.department">Department</Translate>
                </th>
                <th>
                  <Translate contentKey="cmsApp.courseOffer.subject">Subject</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {courseOfferList.map((courseOffer, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${courseOffer.id}`} color="link" size="sm">
                      {courseOffer.id}
                    </Button>
                  </td>
                  <td>{courseOffer.desc}</td>
                  <td>{courseOffer.collegeId ? <Link to={`college/${courseOffer.collegeId}`}>{courseOffer.collegeId}</Link> : ''}</td>
                  <td>
                    {courseOffer.departmentId ? <Link to={`department/${courseOffer.departmentId}`}>{courseOffer.departmentId}</Link> : ''}
                  </td>
                  <td>{courseOffer.subjectId ? <Link to={`subject/${courseOffer.subjectId}`}>{courseOffer.subjectId}</Link> : ''}</td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${courseOffer.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${courseOffer.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${courseOffer.id}/delete`} color="danger" size="sm">
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

const mapStateToProps = ({ courseOffer }: IRootState) => ({
  courseOfferList: courseOffer.entities
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
)(CourseOffer);
