package com.doctorapp.dao;

import java.sql.*;
import java.util.*;

import com.doctorapp.exception.DoctorNotFoundException;
import com.doctorapp.exception.IdNotFoundException;
import com.doctorapp.model.Doctor;
import com.doctorapp.util.DbConnection;
import com.doctorapp.util.Queries;

public class DoctorDaoImpl implements DoctorDao {

	
	PreparedStatement statement = null;
	List<Doctor> doctorResult = new ArrayList<>();

	
	@Override
	public boolean addDoctor(Doctor doctor) {
		Connection connection = DbConnection.getConnection();
		try {
			statement = connection.prepareStatement(Queries.ADD);
			statement.setString(1, doctor.getDoctorName());
			statement.setString(2, doctor.getSpeciality());
			statement.setDouble(3, doctor.getFees());
			statement.setInt(4, doctor.getExperience());
			statement.setTimestamp(5, doctor.getStartTime());
			statement.setTimestamp(6, doctor.getEndTime());
			int result = statement.executeUpdate();
			System.out.println(result);
			if(result==0) {
				return false;
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null) {
				try {
					statement.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public List<Doctor> getAllDoctors() {
		Connection connection = DbConnection.getConnection();

		ResultSet resultSet;
		try {
			statement = connection.prepareStatement(Queries.GETALL);
			resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				
				Doctor doctor = new Doctor();
				String name = resultSet.getString(1);
				int id = resultSet.getInt(2);
				String spec = resultSet.getString(3);
				double fees = resultSet.getDouble(4);
				int exp = resultSet.getInt(5);
				Timestamp stime = resultSet.getTimestamp(6);
				Timestamp etime = resultSet.getTimestamp(7);

				doctor.setDoctorName(name);
				doctor.setDoctorId(id);
				doctor.setSpeciality(spec);
				doctor.setExperience(exp);
				doctor.setFees(fees);
				doctor.setStartTime(stime);
				doctor.setEndTime(etime);
				
				doctorResult.add(doctor);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null) {
				try {
					statement.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return doctorResult;
	}

	@Override
	public boolean updateDoctor(int doctorId, double fees) {
		Connection connection = DbConnection.getConnection();

		try {
			statement=connection.prepareStatement(Queries.UPDATE);
			statement.setDouble(1, fees);
			statement.setInt(2, doctorId);
			int check = statement.executeUpdate();
			if(check==0) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null) {
				try {
					statement.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return true;
	}

	@Override
	public Doctor getById(int doctorId) {
		Connection connection = DbConnection.getConnection();

		ResultSet rs;
		Doctor doctor = new Doctor();
		try {
			statement = connection.prepareStatement(Queries.GETBYID,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setInt(1, doctorId);
			rs = statement.executeQuery();
			if (rs.next()==false) {
				throw new IdNotFoundException("Doctor not found");
			}else {
				rs.beforeFirst();
			rs.next();
			String name = rs.getString(1);
			int id = rs.getInt(2);
			String spec = rs.getString(3);
			double fees = rs.getDouble(4);
			int exp = rs.getInt(5);
			Timestamp stime = rs.getTimestamp(6);
			Timestamp etime = rs.getTimestamp(7);

			doctor.setDoctorName(name);
			doctor.setDoctorId(id);
			doctor.setSpeciality(spec);
			doctor.setExperience(exp);
			doctor.setFees(fees);
			doctor.setStartTime(stime);
			doctor.setEndTime(etime);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return doctor;
	}

	@Override
	public boolean deleteDoctor(int doctorId) {
		Connection connection = DbConnection.getConnection();
		
		try {
			statement= connection.prepareStatement(Queries.DELETE);
			statement.setInt(1, doctorId);
			int check = statement.executeUpdate();
			if(check==0) {
				throw new DoctorNotFoundException("Doctor Not Found");
			}else {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null) {
				try {
					statement.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}

	@Override
	public List<Doctor> getBySpecialization(String speciality) {
		Connection connection = DbConnection.getConnection();

		ResultSet rs=null;
		try {
			statement = connection.prepareStatement(Queries.GETBYSPEC,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, speciality);
			rs = statement.executeQuery();
			if (rs.next()==false) {
				throw new DoctorNotFoundException("Doctor not found");
			}else {
				rs.beforeFirst();
			while(rs.next()) {
				
				Doctor doctor = new Doctor();
				String name = rs.getString(1);
				int id = rs.getInt(2);
				String spec = rs.getString(3);
				double fees = rs.getDouble(4);
				int exp = rs.getInt(5);
				Timestamp stime = rs.getTimestamp(6);
				Timestamp etime = rs.getTimestamp(7);

				doctor.setDoctorName(name);
				doctor.setDoctorId(id);
				doctor.setSpeciality(spec);
				doctor.setExperience(exp);
				doctor.setFees(fees);
				doctor.setStartTime(stime);
				doctor.setEndTime(etime);
				
				doctorResult.add(doctor);
			}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null && rs!=null) {
				try {
					statement.close();
					rs.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return doctorResult;
	}

	@Override
	public List<Doctor> getBySpecilityAndExp(String speciality, int experience) {
		Connection connection = DbConnection.getConnection();

		ResultSet rs=null;
		try {
			statement = connection.prepareStatement(Queries.GETBYSPECANDEXP,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, speciality);
			statement.setInt(2, experience);
			rs = statement.executeQuery();
			if (rs.next()==false) {
				throw new DoctorNotFoundException("Doctor not found");
			}else {
				rs.beforeFirst();
			while(rs.next()) {
				
				Doctor doctor = new Doctor();
				String name = rs.getString(1);
				int id = rs.getInt(2);
				String spec = rs.getString(3);
				double fees = rs.getDouble(4);
				int exp = rs.getInt(5);
				Timestamp stime = rs.getTimestamp(6);
				Timestamp etime = rs.getTimestamp(7);

				doctor.setDoctorName(name);
				doctor.setDoctorId(id);
				doctor.setSpeciality(spec);
				doctor.setExperience(exp);
				doctor.setFees(fees);
				doctor.setStartTime(stime);
				doctor.setEndTime(etime);
				
				doctorResult.add(doctor);
			}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null && rs!=null) {
				try {
					statement.close();
					rs.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return doctorResult;
	}

	public List<Doctor> getBySpecilityAndFees(String specility, Double fees) {
		ResultSet rs=null;
		Connection connection = DbConnection.getConnection();
		try {
			statement = connection.prepareStatement(Queries.GETBYSPECANDFEES, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setString(1, specility);
			statement.setDouble(2, fees);
			rs = statement.executeQuery();
			if (rs.next()==false) {
				throw new DoctorNotFoundException("Doctor not found");
			}else {
				rs.beforeFirst();
			while(rs.next()) {
				
				Doctor doctor = new Doctor();
				String name = rs.getString(1);
				int id = rs.getInt(2);
				String spec = rs.getString(3);
				double fee = rs.getDouble(4);
				int exp = rs.getInt(5);
				Timestamp stime = rs.getTimestamp(6);
				Timestamp etime = rs.getTimestamp(7);

				doctor.setDoctorName(name);
				doctor.setDoctorId(id);
				doctor.setSpeciality(spec);
				doctor.setExperience(exp);
				doctor.setFees(fee);
				doctor.setStartTime(stime);
				doctor.setEndTime(etime);
				
				doctorResult.add(doctor);
			}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null && rs!=null) {
				try {
					statement.close();
					rs.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return doctorResult;
	}

	@Override
	public List<Doctor> getByAvailablity(Timestamp startTime) {
		Connection connection = DbConnection.getConnection();

		ResultSet rs=null;
		try {
			statement = connection.prepareStatement(Queries.GETBYAVAIL,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			statement.setTimestamp(1, startTime);
			rs = statement.executeQuery();
			if (rs.next()==false) {
				throw new DoctorNotFoundException("Doctor not found");
			}else {
				rs.beforeFirst();

			while(rs.next()) {
				
				Doctor doctor = new Doctor();
				String name = rs.getString(1);
				int id = rs.getInt(2);
				String spec = rs.getString(3);
				double fee = rs.getDouble(4);
				int exp = rs.getInt(5);
				Timestamp stime = rs.getTimestamp(6);
				Timestamp etime = rs.getTimestamp(7);

				doctor.setDoctorName(name);
				doctor.setDoctorId(id);
				doctor.setSpeciality(spec);
				doctor.setExperience(exp);
				doctor.setFees(fee);
				doctor.setStartTime(stime);
				doctor.setEndTime(etime);
				
				doctorResult.add(doctor);
			}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(statement!=null && rs!=null) {
				try {
					statement.close();
					rs.close();
					DbConnection.closeConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return doctorResult;
	}

}
