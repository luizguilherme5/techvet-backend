package br.com.bovdog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.bovdog.bean.Patient;

// create class Patient DAO for database communication.
public class PatientDAO {
	private final String USER = "root";
	private final String PASSWORD = "root";
	private final String URL = "jdbc:mysql://localhost/chattering?useSSL=false&serverTimezone=UTC";
	
	// create method getAllPatients to return list of Patients.
	public List<Patient> getAllPatients() {
		List<Patient> patients= new ArrayList<Patient>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		
		// treatment for increment in Patient list.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(USER, PASSWORD, URL);
			preparedStatement = connection.prepareStatement("SELECT * FROM patient;");
			results = preparedStatement.executeQuery();
			
			/*
			 * if any results exist in one list, it increments Patients attributes 
			 * in the next list.
			 */
			while(results.next()) {
				Patient patient = new Patient();
				patient.setIdPatient(results.getInt("idPatient"));
				patient.setPatientName(results.getString("PatientName"));
				patient.setSpecie(results.getString("specie"));
				patient.setBirthday(results.getDate("birthday"));
				patient.setCoat(results.getString("coat"));
				patient.setGender(results.getString("gender").charAt(0));
				patient.setBreed(results.getString("breed"));
				patient.setSize(results.getString("size").charAt(0));
				patients.add(patient);
			}
			
		// return error if caught SQL exception.
		} catch (SQLException e) {
			e.printStackTrace();
		
		// return error if caught class exception.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		
		// close SQL statement and database connection. 
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null) {
					connection.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// return Patients list.
		return patients;
	}
	
	// create method to select a specific patient from table by id.
	public Patient getPatientById(int id) {
		Patient patient = new Patient();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String sql = "SELECT * FROM patient WHERE id = ?";
		ResultSet result = null;
		
		// treatment for existance of desired patient id.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection= DriverManager.getConnection(URL, USER, PASSWORD);
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			result = preparedStatement.executeQuery();
			
			// checks if patient list isn't empty.
			if (result.next()) {
				patient.setBirthday(result.getDate("birthday"));
				patient.setCoat(result.getString("coat"));
				patient.setGender(result.getString("gender").charAt(0));
				patient.setPatientName(result.getString("patientName"));
				patient.setBreed(result.getString("breed"));
				patient.setSize(result.getString("size").charAt(0));
				patient.setSpecie(result.getString("specie"));
			}
			
		// return error if caught SQL exception.
		} catch (SQLException e) {
			e.printStackTrace();
			
		// return error if caught class exception.
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		// close SQL statement and database connection. 
		} finally {
			
			try {
				
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
				
			}
			try {
				if (connection != null) {
					connection.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		// returns wanted patient.
		return patient;
	}
	
	// create method for creation of patients in databank.
	public void createPatient(Patient patient) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		// treatment to ensure the successful conclusion of the operation.
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "INSERT INTO patient (patientName, specie, breed, size, gender, birthday, coat) "
					   + "VALUES (?, ?, ?, ?, ?, ?, ?);";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, patient.getPatientName());
			preparedStatement.setString(2, patient.getSpecie());
			preparedStatement.setString(3, patient.getBreed());
			preparedStatement.setString(4, String.valueOf(patient.getSize()));
			preparedStatement.setString(5, String.valueOf(patient.getGender()));
			preparedStatement.setDate(6, new java.sql.Date(patient.getBirthday().getTime()));
			preparedStatement.setString(7, patient.getCoat());
			preparedStatement.executeUpdate();
			
		// return error if caught SQL exception.
		} catch(SQLException e) {
			e.printStackTrace();
		// return error if caught class exception.
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (connection != null) {
					connection.close();
				}
				
			// return error if caught SQL exception.
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
