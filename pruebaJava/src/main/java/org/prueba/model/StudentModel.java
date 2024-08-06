package org.prueba.model;

import org.prueba.entity.CourseEntity;
import org.prueba.entity.StudentEntity;
import org.prueba.persistence.IModel.IStudentModel;
import org.prueba.persistence.connection.ConfigDB;
import org.prueba.persistence.enums.StatusStudent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentModel implements IStudentModel {
    private Connection connection = null;

    //Se crea y se manda a la base de datos
    @Override
    public StudentEntity create(StudentEntity studentEntity) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "INSERT INTO STUDENT (NAME,LAST_NAME,EMAIL,STATUS) VALUES(?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, studentEntity.getName());
            preparedStatement.setString(2,studentEntity.getLast_name());
            preparedStatement.setString(3,studentEntity.getEmail());
            preparedStatement.setString(4,studentEntity.getStatus().name());

            boolean result = preparedStatement.execute();

            if(result){
                return studentEntity;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: El email ya existe.");
                //System.out.println("Query failed: " + e.getMessage());
            } else {
                e.printStackTrace();
                throw new RuntimeException("Query failed: " + e.getMessage());
            }
        }
        ConfigDB.closeConnetion();
        return null;
    }

    //Se elimina y se manda a la base de datos
    @Override
    public Boolean delete(Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "DELETE FROM STUDENT WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);

            boolean result = preparedStatement.execute();

            if(result){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return false;
    }

    //Se lee todos los objetos
    @Override
    public ArrayList<StudentEntity> readAll() {
        connection = ConfigDB.openConnetion();

        ArrayList<StudentEntity> listStudent = new ArrayList<>();
        StudentEntity student = new StudentEntity();

        String sqlQuery = "SELECT * FROM STUDENT;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                student = new StudentEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        StatusStudent.valueOf(resultSet.getString("status"))
                );

                listStudent.add(student);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return listStudent;
    }

    //Se lee un solo objeto
    @Override
    public StudentEntity readStudent(String id, String option) {
        connection = ConfigDB.openConnetion();
        StudentEntity student = new StudentEntity();
        CourseEntity course = new CourseEntity();

        String sqlQuery = "";

        switch (option){
            /*case "id" ->{
                sqlQuery = "SELECT * FROM STUDENT WHERE ID = ?;";
            }
            case "email" ->{
                sqlQuery = "SELECT * FROM STUDENT WHERE EMAIL = ?;";
            }*/

            case "id" ->{
                sqlQuery = "SELECT STUDENT.ID,NAME,LAST_NAME,EMAIL,STATUS,NAME_COURSE FROM STUDENT INNER JOIN COURSE ON STUDENT.ID = COURSE.ID_STUDENT WHERE STUDENT.ID = ?;";
            }
            case "email" ->{
                sqlQuery = "SELECT STUDENT.ID,NAME,LAST_NAME,EMAIL,STATUS,NAME_COURSE FROM STUDENT INNER JOIN COURSE ON STUDENT.ID = COURSE.ID_STUDENT WHERE STUDENT.EMAIL = ?;";
            }
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            switch (option){
                case "id" ->{
                    preparedStatement.setInt(1, Integer.parseInt(id));
                }
                case "email" ->{
                    preparedStatement.setString(1, id);
                }
            }

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if (resultSet.next()){

                student = new StudentEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        StatusStudent.valueOf(resultSet.getString("status"))
                );


            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());

        }
        ConfigDB.closeConnetion();
        return student;
    }

    //Se actualiza el objeto deseado y se manda a la base de datos
    @Override
    public StudentEntity update(StudentEntity studentEntity, Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "UPDATE STUDENT SET NAME = ?, LAST_NAME = ?, EMAIL = ?, STATUS = ? WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, studentEntity.getName());
            preparedStatement.setString(2,studentEntity.getLast_name());
            preparedStatement.setString(3,studentEntity.getEmail());
            preparedStatement.setString(4,studentEntity.getStatus().name());
            preparedStatement.setInt(5, id);

            boolean result = preparedStatement.execute();

            if(result){
                return studentEntity;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: El email ya existe.");
            } else {
                e.printStackTrace();
            }
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }

    //Se busca el id de un objeto
    @Override
    public StudentEntity searchById(StudentEntity student, Integer id) {
        connection = ConfigDB.openConnetion();
        StudentModel studentModel = new StudentModel();

        String sqlQuery = "UPDATE STUDENT SET REGISTRATION = ? WHERE ID = ?;";

        int cont = 1;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,  studentModel.registerStudent(id) + 1);
            preparedStatement.setInt(2, id);

            boolean result = preparedStatement.execute();

            if(result){
                return student;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }

    //Se busca las inscripciones para generar un buen contador
    @Override
    public Integer registerStudent(Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "SELECT REGISTRATION FROM STUDENT WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                int id_found = resultSet.getInt("REGISTRATION");
                return id_found;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }
}
