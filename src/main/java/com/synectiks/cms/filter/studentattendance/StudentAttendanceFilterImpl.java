package com.synectiks.cms.filter.studentattendance;

    import java.math.BigInteger;
    import java.util.ArrayList;
    import java.util.List;

    import javax.persistence.EntityManager;
    import javax.persistence.PersistenceContext;
    import javax.persistence.Query;
    import java.sql.Date;

    import org.springframework.stereotype.Repository;

//@Component
@Repository
public class StudentAttendanceFilterImpl  {

    @PersistenceContext
    private EntityManager entityManager;

    private String selectQry = "select distinct st.id, st.student_name, " +
        "(select a.attendance_status from student_attendance a where a.student_id = st.id and a.attendance_date = date(?)) as current_date_status, " +
        "(select a.attendance_status as prev1_day_status from student_attendance a where a.student_id = st.id and a.attendance_date = date(date(?) - interval '1 day')),   " +
        "(select a.attendance_status as prev2_day_status from student_attendance a where a.student_id = st.id and a.attendance_date = date(date(?) - interval '2 day')),   " +
        "(select a.attendance_status as prev3_day_status from student_attendance a where a.student_id = st.id and a.attendance_date = date(date(?) - interval '3 day')),   " +
        "sa.comments "+
        "from student st  " +
        "left join student_attendance sa on st.id = sa.student_id  " +
        "inner join department dt on st.department_id = dt.id " +
        "inner join student_subject ss on ss.student_id = st.id " + 
        "inner join subject sb on ss.subject_id = sb.id " +
        "where st.branch_id = ? and dt.id = ? and st.batch_id = ? and st.section_id = ? and sb.id = ? ";

    public List<DailyAttendanceVo> getStudenceAttendance(StudentAttendanceFilterInput filter) {
        if(filter.getStudentName() != null) {
            selectQry = selectQry + " and st.student_name = ? ";
        }
        Query query = this.entityManager.createNativeQuery(selectQry);
        if(filter != null) {
            int i=0;
            //java.sql.Date dt = new java.sql.Date(new java.util.Date(filter.getAttendanceDate()).getTime());
            query.setParameter(++i, filter.getAttendanceDate());
            query.setParameter(++i, filter.getAttendanceDate());
            query.setParameter(++i, filter.getAttendanceDate());
            query.setParameter(++i, filter.getAttendanceDate());
            query.setParameter(++i, Integer.parseInt(filter.getBranchId()));
            query.setParameter(++i, Integer.parseInt(filter.getDepartmentId()));
            query.setParameter(++i, Integer.parseInt(filter.getBatchId()));
            query.setParameter(++i, Integer.parseInt(filter.getSectionId()));
            query.setParameter(++i, Integer.parseInt(filter.getSubjectId()));
            if(filter.getStudentName() != null) {
                query.setParameter(++i, filter.getStudentName());
            }
        }

        List<DailyAttendanceVo> resultList = executeSelectQuery(query);
        return resultList;
    }

    public List<DailyAttendanceVo> getStudenceAttendance() {
        Query query = this.entityManager.createNativeQuery(selectQry);
        List<DailyAttendanceVo> resultList = executeSelectQuery(query);
        return resultList;
    }

    private List<DailyAttendanceVo> executeSelectQuery(Query query) {
        List<DailyAttendanceVo> resultList = new ArrayList<DailyAttendanceVo>();

        List<Object[]> ls = query.getResultList();
        for (Object[] result : ls){
            DailyAttendanceVo da = new DailyAttendanceVo();
            da.setStudentId(String.valueOf((BigInteger)result[0]));
            da.setStudentName((String)result[1]);
            da.setCurrentDateStatus((String)result[2]);
            da.setPreviousOneDayStatus((String)result[3]);
            da.setPreviousTwoDayStatus((String)result[4]);
            da.setPreviousThreeDayStatus((String)result[5]);
            da.setComments((String)result[6]);
            resultList.add(da);
        }
        return resultList;
    }


}
