package org.prueba.model;

import org.prueba.entity.CourseEntity;
import org.prueba.entity.StudentEntity;
import org.prueba.persistence.IModel.ICourseModel;
import org.prueba.persistence.connection.ConfigDB;
import org.prueba.persistence.enums.StatusStudent;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseModel implements ICourseModel {
    private Connection connection = null;

    //Se crea y se manda a la base de datos
    @Override
    public CourseEntity create(CourseEntity courseEntity) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "INSERT INTO COURSE (NAME_COURSE, ID_STUDENT) VALUES(?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, courseEntity.getName_course());
            preparedStatement.setInt(2,courseEntity.getId_student());

            boolean result = preparedStatement.execute();

            if(result){
                return courseEntity;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: El nombre del curso ya existe.");
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

        String sqlQuery = "DELETE FROM COURSE WHERE ID = ?;";

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
    public ArrayList<CourseEntity> readAll() {
        connection = ConfigDB.openConnetion();

        ArrayList<CourseEntity> listCourse = new ArrayList<>();
        CourseEntity course = new CourseEntity();

        String sqlQuery = "SELECT * FROM COURSE;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                course = new CourseEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name_course"),
                        resultSet.getInt("id_student")
                );

                listCourse.add(course);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return listCourse;
    }

    //Se lee un solo objeto
    @Override
    public CourseEntity readOnlyOne(Integer id) {
        connection = ConfigDB.openConnetion();
        CourseEntity course = new CourseEntity();

        String sqlQuery = "SELECT * FROM COURSE WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);


            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                course = new CourseEntity(
                        resultSet.getInt("id"),
                        resultSet.getString("name_course"),
                        resultSet.getInt("id_student")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return course;
    }

    //Se busca el id de un objeto
    @Override
    public Integer searchById(Integer id, String option) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "SELECT ID FROM STUDENT WHERE ID = ?;";

        switch (option){
            case "student" -> {sqlQuery = "SELECT ID_STUDENT FROM COURSE WHERE ID = ?;";}
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                int id_found;
                if(!option.equals("student")){
                    id_found = resultSet.getInt("id");
                }
                else {
                    id_found = resultSet.getInt("id_student");
                }
                return id_found;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }

    //Se actualiza el objeto deseado y se manda a la base de datos
    @Override
    public CourseEntity update(CourseEntity courseEntity, Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "UPDATE COURSE SET NAME_COURSE = ?, ID_STUDENT = ? WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setString(1, courseEntity.getName_course());
            preparedStatement.setInt(2,courseEntity.getId_student());
            preparedStatement.setInt(3, id);

            boolean result = preparedStatement.execute();

            if(result){
                return courseEntity;
            }

        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Error: El nombre del curso ya existe.");
            } else {
                e.printStackTrace();
                throw new RuntimeException("Query failed: " + e.getMessage());
            }
        }
        ConfigDB.closeConnetion();
        return null;
    }
}
