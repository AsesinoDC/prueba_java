package org.prueba.model;

import org.prueba.entity.CourseEntity;
import org.prueba.entity.RegistrationEntity;
import org.prueba.persistence.IModel.IRegistrationModel;
import org.prueba.persistence.connection.ConfigDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RegistrationModel implements IRegistrationModel {
    private Connection connection = null;

    //Se crea y se manda a la base de datos
    @Override
    public RegistrationEntity create(RegistrationEntity registrationEntity) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "INSERT INTO REGISTRATION (ID_STUDENT, ID_COURSE) VALUES(?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, registrationEntity.getId_student());
            preparedStatement.setInt(2,registrationEntity.getId_courses());

            boolean result = preparedStatement.execute();

            if(result){
                return registrationEntity;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }

    //Se elimina y se manda a la base de datos
    @Override
    public Boolean delete(Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "DELETE FROM REGISTRATION WHERE ID = ?;";

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
    public ArrayList<RegistrationEntity> readAll() {
        connection = ConfigDB.openConnetion();

        ArrayList<RegistrationEntity> listRegistration = new ArrayList<>();
        RegistrationEntity registration = new RegistrationEntity();

        String sqlQuery = "SELECT * FROM REGISTRATION;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                registration = new RegistrationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_student"),
                        resultSet.getInt("id_course")
                );

                listRegistration.add(registration);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return listRegistration;
    }

    //Se lee un solo objeto
    @Override
    public RegistrationEntity readOnlyOne(Integer id) {
        connection = ConfigDB.openConnetion();
        RegistrationEntity registration = new RegistrationEntity();

        String sqlQuery = "SELECT * FROM REGISTRATION WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);


            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                registration = new RegistrationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_student"),
                        resultSet.getInt("id_course")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return registration;
    }

    //Se busca el id de un objeto
    @Override
    public Integer searchById(Integer id, String option) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "";

        switch (option){
            case "student" ->{sqlQuery = "SELECT ID FROM STUDENT WHERE ID = ?;";}

            case "course" ->{sqlQuery = "SELECT ID FROM COURSE WHERE ID = ?;";}

            case "value" ->{sqlQuery = "SELECT ID_STUDENT FROM REGISTRATION WHERE ID_STUDENT = ?;";}
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                int id_found;

                if (option.equals("value")) {
                    id_found = resultSet.getInt("id_student");
                }
                else {
                    id_found = resultSet.getInt("id");
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
    public RegistrationEntity update(RegistrationEntity registrationEntity, Integer id) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "UPDATE REGISTRATION SET ID_STUDENT = ?, ID_COURSE = ? WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, registrationEntity.getId_student());
            preparedStatement.setInt(2,registrationEntity.getId_courses());
            preparedStatement.setInt(3, id);

            boolean result = preparedStatement.execute();

            if(result){
                return registrationEntity;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }
}
