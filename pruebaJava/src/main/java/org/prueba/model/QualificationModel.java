package org.prueba.model;

import org.prueba.entity.QualificationEntity;
import org.prueba.entity.RegistrationEntity;
import org.prueba.persistence.IModel.IQualificationModel;
import org.prueba.persistence.connection.ConfigDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QualificationModel implements IQualificationModel {
    private Connection connection = null;

    //Se crea y se manda a la base de datos
    @Override
    public QualificationEntity create(QualificationEntity qualificationEntity) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "INSERT INTO QUALIFICATION (ID_COURSE, ID_REGISTRATION, DESCRIPTION, QUALITY) VALUES(?,?,?,?);";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery, PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, qualificationEntity.getId_courses());
            preparedStatement.setInt(2, qualificationEntity.getId_registration());
            preparedStatement.setString(3,qualificationEntity.getDescription());
            preparedStatement.setInt(4, qualificationEntity.getQuality());

            boolean result = preparedStatement.execute();

            if(result){
                return qualificationEntity;
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

        String sqlQuery = "DELETE FROM QUALIFICATION WHERE ID = ?;";

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
    public ArrayList<QualificationEntity> readAll() {
        connection = ConfigDB.openConnetion();

        ArrayList<QualificationEntity> listQualification = new ArrayList<>();
        QualificationEntity qualification = new QualificationEntity();

        String sqlQuery = "SELECT * FROM QUALIFICATION;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            while (resultSet.next()){
                qualification = new QualificationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_course"),
                        resultSet.getInt("id_registration"),
                        resultSet.getString("description"),
                        resultSet.getInt("quality")
                );

                listQualification.add(qualification);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return listQualification;
    }

    //Se lee un solo objeto
    @Override
    public QualificationEntity readOnlyOne(Integer id) {
        connection = ConfigDB.openConnetion();
        QualificationEntity qualification = new QualificationEntity();

        String sqlQuery = "SELECT * FROM QUALIFICATION WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1, id);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                qualification = new QualificationEntity(
                        resultSet.getInt("id"),
                        resultSet.getInt("id_course"),
                        resultSet.getInt("id_registration"),
                        resultSet.getString("description"),
                        resultSet.getInt("quality")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return qualification;
    }

    //Se busca el id de un objeto
    @Override
    public Integer searchById(Integer id, String option) {
        connection = ConfigDB.openConnetion();

        String sqlQuery = "";

        switch (option){
            case "course" -> {sqlQuery = "SELECT ID FROM COURSE WHERE ID = ?;";}

            case "registration" -> {sqlQuery = "SELECT ID FROM REGISTRATION WHERE ID = ?;";}

        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            preparedStatement.setInt(1,id);

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();

            if(resultSet.next()){
                int id_found = resultSet.getInt("id");
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
    public QualificationEntity update(QualificationEntity qualificationEntity, Integer id) {
        connection = ConfigDB.openConnetion();

        /*String sqlQuery = "UPDATE QUALIFICATION SET ID_COURSE = ?, DESCRIPTION = ?, QUALITY = ? WHERE ID = ?;";*/
        String sqlQuery = "UPDATE QUALIFICATION SET QUALITY = ? WHERE ID = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);

            /*preparedStatement.setInt(1, qualificationEntity.getId_courses());
            preparedStatement.setString(2,qualificationEntity.getDescription());*/
            preparedStatement.setInt(1, qualificationEntity.getQuality());
            preparedStatement.setInt(2,id);

            boolean result = preparedStatement.execute();

            if(result){
                return qualificationEntity;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Query failed: " + e.getMessage());
        }
        ConfigDB.closeConnetion();
        return null;
    }
}
