package bookstoreJPAdatabase2.domain;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StudentRowMapper implements RowMapper<Student> {
	
	@Override
	public Student mapRow(ResultSet rs, int rowNum) throws SQLException{
		Student student = new Student();
		student.setId(rs.getInt("id"));
		student.setFirstName(rs.getString("first_name"));
		student.setEmail(rs.getString("email"));
		
		return student;
	}

}
