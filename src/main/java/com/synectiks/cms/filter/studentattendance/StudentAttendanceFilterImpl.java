package com.synectiks.cms.filter.studentattendance;

    import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


@Repository
public class StudentAttendanceFilterImpl  {

    @PersistenceContext
    private EntityManager entityManager;

    public List<DailyAttendanceVo> getStudenceAttendance(StudentAttendanceFilterInput filter) {
    	String selectQry = "select st.id, st.student_name, sa.attendance_status as current_date_status, " + 
    			"(select a.attendance_status as prev1_day_status from student_attendance a where a.student_id = st.id and a.lecture_id = (select b.id from lecture b where b.attendancemaster_id = (select c.attendancemaster_id from lecture c where c.id = ?) and b.lec_date = date(lc.lec_date) - 1)),  " + 
    			"(select a.attendance_status as prev2_day_status from student_attendance a where a.student_id = st.id and a.lecture_id = (select b.id from lecture b where b.attendancemaster_id = (select c.attendancemaster_id from lecture c where c.id = ?) and b.lec_date = date(lc.lec_date) - 2)),  " + 
    			"(select a.attendance_status as prev3_day_status from student_attendance a where a.student_id = st.id and a.lecture_id = (select b.id from lecture b where b.attendancemaster_id = (select c.attendancemaster_id from lecture c where c.id = ?) and b.lec_date = date(lc.lec_date) - 3)),  " +
    			"sa.comments "+
    			"from student st, student_attendance sa, lecture lc " + 
    			"where st.id = sa.student_id and sa.lecture_id = lc.id  and " + 
    			"st.branch_id = ? and st.department_id = ? and st.batch_id = ? and st.section_id = ? and sa.lecture_id = ? and lc.lec_date = date(?) ";
        if(filter.getStudentName() != null) {
            selectQry = selectQry + " and st.student_name = ? ";
        }
        Query query = this.entityManager.createNativeQuery(selectQry);
        if(filter != null) {
            query.setParameter(1, Integer.parseInt(filter.getLectureId()));
            query.setParameter(2, Integer.parseInt(filter.getLectureId()));
            query.setParameter(3, Integer.parseInt(filter.getLectureId()));
            query.setParameter(4, Integer.parseInt(filter.getBranchId()));
            query.setParameter(5, Integer.parseInt(filter.getDepartmentId()));
            query.setParameter(6, Integer.parseInt(filter.getBatchId()));
            query.setParameter(7, Integer.parseInt(filter.getSectionId()));
            query.setParameter(8, Integer.parseInt(filter.getLectureId()));
            query.setParameter(9, filter.getAttendanceDate());
            if(filter.getStudentName() != null) {
                query.setParameter(10, filter.getStudentName());
            }
        }

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
