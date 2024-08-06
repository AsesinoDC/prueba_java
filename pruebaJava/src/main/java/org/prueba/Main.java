package org.prueba;

import org.prueba.controller.CourseController;
import org.prueba.controller.QualificationController;
import org.prueba.controller.RegistrationController;
import org.prueba.controller.StudentController;
import org.prueba.entity.CourseEntity;
import org.prueba.entity.QualificationEntity;
import org.prueba.entity.RegistrationEntity;
import org.prueba.entity.StudentEntity;
import org.prueba.persistence.enums.StatusStudent;

import javax.swing.*;

public class Main {
    //En esta que es la logica de negocia tambien se realiza una interfaz para navegar entre las demas (Esta es la principal interfaz)
    public static void main(String[] args) {
        String option = "";

        do {
            option = JOptionPane.showInputDialog("""
                    Bienvenido a Riwi Academy, que quieres hacer?
                    
                    1. Entrar a estudiantes
                        
                    2. Entrar a cursos
                        
                    3. Entrar a registros
                        
                    4. Entrar a calificaciones
                        
                    5. Salir
                    """);

            switch (option){
                case "1" -> {studentInterface();}

                case "2" -> {courseInterface();}

                case "3" -> {registrationInterface();}

                case "4" -> {qualificationInterface();}

                case "5" -> {
                    JOptionPane.showMessageDialog(null,
                            "Saliste del programa, gracias por usarlo");
                }

                default -> {
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }
        }while (!option.equals("5"));

    }


    //Esta funcione realiza las interfaz, para que se pueda realizar el crud de una forma mas dinamica y eficiente
    public static void studentInterface(){
        StudentController studentController = new StudentController();
        StudentEntity student = new StudentEntity();

        String option = "";

        do {
            option = showOptions("Estudiantes");

            switch (option){
                case "1" -> {
                    String name = JOptionPane.showInputDialog("Dame el nombre del estudiante: ");
                    String last_name = JOptionPane.showInputDialog("Dame el apellido del estudiante: ");
                    String email = JOptionPane.showInputDialog("Dame el email del estudiante: ");
                    StatusStudent status = (StatusStudent) JOptionPane.showInputDialog(null,
                            "Escoje" + StatusStudent.class + ": ",
                            "",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            StatusStudent.values(),
                            StatusStudent.values());

                    student = new StudentEntity(name,last_name,email,status);
                    studentController.create(student);
                }

                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Estos son todos los estudiantes");
                    JOptionPane.showMessageDialog(null, studentController.readAll().toString());
                }

                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Vas a buscar un estudiante");
                    String optionSearch = JOptionPane.showInputDialog("¿Que quieres buscar? \n Por id: \n Por email");

                    switch (optionSearch){
                        case "id" ->{
                            String id = JOptionPane.showInputDialog("Dame el id del estudiante");
                            JOptionPane.showMessageDialog(null, studentController.readStudent(id, "id"));
                        }
                        case "email" ->{
                            String id = JOptionPane.showInputDialog("Dame el email del estudiante");
                            JOptionPane.showMessageDialog(null, studentController.readStudent(id, "email"));
                        }
                    }
                }

                case "4" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del estudiante que quieres actualizar: "));

                    String name = JOptionPane.showInputDialog("Dame el nombre del estudiante: ");
                    String last_name = JOptionPane.showInputDialog("Dame el apellido del estudiante: ");
                    String email = JOptionPane.showInputDialog("Dame el email del estudiante: ");
                    StatusStudent status = (StatusStudent) JOptionPane.showInputDialog(null,
                            "Escoje" + StatusStudent.class + ": ",
                            "",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            StatusStudent.values(),
                            StatusStudent.values());

                    student = new StudentEntity(name,last_name,email,status);
                    studentController.update(student, id);
                }

                case "5" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del estudiante que quieres eliminar: "));
                    studentController.delete(id);
                }

                case "6" -> {
                    JOptionPane.showMessageDialog(null,
                            "Vas a volver al menu principal");
                }

                default -> {
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }
        }while (!option.equals("6"));
    }

    //Esta funcione realiza las interfaz, para que se pueda realizar el crud de una forma mas dinamica y eficiente
    public static void courseInterface(){
        CourseController courseController = new CourseController();
        StudentController studentController = new StudentController();
        StudentEntity student = new StudentEntity();

        CourseEntity course = new CourseEntity();

        String option = "";

        do {
            option = showOptions("Cursos");

            switch (option){
                case "1" -> {
                    String name_course = JOptionPane.showInputDialog("Dame el nombre del curso:").toLowerCase();
                    int id_student = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del estudiante que quieres ingresar al curso:"));

                    if(studentController.registerStudent(id_student) <= 3){
                        if(courseController.searchById(id_student,"") != null){
                            studentController.searchById(student,id_student);

                            course = new CourseEntity(name_course,id_student);
                            courseController.create(course);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"El estudiante no existe");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"El estudiante ya alcanzo el limite de cursos");
                    }

                }

                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Estos son todos los cursos");
                    JOptionPane.showMessageDialog(null, courseController.readAll().toString());
                }

                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Vas a buscar un curso");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del curso"));

                    JOptionPane.showMessageDialog(null,courseController.readOnlyOne(id).toString());
                }

                case "4" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del curso que quieres actualizar: "));

                    String name_course = JOptionPane.showInputDialog("Dame el nombre del curso:").toLowerCase();
                    int id_student = Integer.parseInt(JOptionPane.showInputDialog("Dame id del estudiante que quieres ingresar al curso:"));
                    course = new CourseEntity(name_course,id_student);
                    courseController.update(course,id);
                }

                case "5" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del curso que quieres eliminar: "));

                    if(courseController.searchById(id,"student") == null){
                        JOptionPane.showMessageDialog(null,"Se borrara el curso");
                        courseController.delete(id);
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No se puede eliminar el curso");
                    }

                }

                case "6" -> {
                    JOptionPane.showMessageDialog(null,
                            "Vas a volver al menu principal");
                }

                default -> {
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }
        }while (!option.equals("6"));
    }

    //Esta funcione realiza las interfaz, para que se pueda realizar el crud de una forma mas dinamica y eficiente
    public static void registrationInterface(){
        RegistrationController registrationController = new RegistrationController();
        RegistrationEntity registration = new RegistrationEntity();

        String option = "";

        do {
            option = showOptions("Inscripciones");

            switch (option){
                case "1" -> {
                    int id_student = Integer.parseInt(JOptionPane.showInputDialog("Dame id del estudiante que quieres ingresar a registro:"));
                    int id_course = Integer.parseInt(JOptionPane.showInputDialog("Dame id del curso que quieres ingresar a registro:"));

                    if(registrationController.searchById(id_student, "value") == null){

                        if(registrationController.searchById(id_student, "student") != null){

                            if(registrationController.searchById(id_course, "course") != null){
                                registration = new RegistrationEntity(id_student,id_course);
                                registrationController.create(registration);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"No existe este curso");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No existe este estudiante");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Ya existe el estudiante en un registro");
                    }

                }

                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Estos son todos los registros");
                    JOptionPane.showMessageDialog(null, registrationController.readAll().toString());
                }

                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Vas a buscar un registro");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del registro"));

                    JOptionPane.showMessageDialog(null,registrationController.readOnlyOne(id));
                }

                case "4" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del curso que quieres actualizar: "));

                    int id_student = Integer.parseInt(JOptionPane.showInputDialog("Dame id del estudiante que quieres ingresar al curso:"));
                    int id_course = Integer.parseInt(JOptionPane.showInputDialog("Dame id del estudiante que quieres ingresar al curso:"));

                    if(registrationController.searchById(id_student, "student") != null){
                        if(registrationController.searchById(id_course, "course") != null){
                            registration = new RegistrationEntity(id_student,id_course);
                            registrationController.update(registration,id);
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No existe este curso");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"No existe este estudiante");
                    }
                }

                case "5" -> {
                     int result = JOptionPane.showConfirmDialog(null,"""
                            ¡Advertencia!
                            
                            Si eliminas un registro se borrara todo el historial de calificaciones del estudiante
                            """);

                     if(result == 0){
                         int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del registro que quieres eliminar: "));
                         registrationController.delete(id);
                     }
                     else{
                         JOptionPane.showMessageDialog(null, "No borraste nada, volviendo");
                     }
                }

                case "6" -> {
                    JOptionPane.showMessageDialog(null,
                            "Vas a volver al menu principal");
                }

                default -> {
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }
        }while (!option.equals("6"));
    }

    //Esta funcione realiza las interfaz, para que se pueda realizar el crud de una forma mas dinamica y eficiente
    public static void qualificationInterface(){
        QualificationController qualificationController = new QualificationController();
        QualificationEntity qualification = new QualificationEntity();

        String option = "";

        do {
            option = showOptions("Calificaciones");

            switch (option){
                case "1" -> {
                    int id_course = Integer.parseInt(JOptionPane.showInputDialog("Dame el id del curso que quieres ligar a la calificacion:"));
                    int id_registration = Integer.parseInt(JOptionPane.showInputDialog("Dame el id de la inscripcion que quieres ligar a la calificacion:"));
                    String description = JOptionPane.showInputDialog("Dame la descripcion del porque la calificacion");
                    int quality = Integer.parseInt(JOptionPane.showInputDialog("Dame la calificacion del 0 al 100"));

                    if(quality >= 0 && quality <= 100){
                        if(qualificationController.searchById(id_course, "course") != null){
                            if(qualificationController.searchById(id_registration, "registration") != null){
                                qualification = new QualificationEntity(id_course,id_registration, description, quality);
                                qualificationController.create(qualification);
                            }
                            else{
                                JOptionPane.showMessageDialog(null,"No existe esta inscripcion");
                            }
                        }
                        else{
                            JOptionPane.showMessageDialog(null,"No existe este curso");
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Calificacion invalida");
                    }
                }

                case "2" -> {
                    JOptionPane.showMessageDialog(null, "Estos son todas las calificaciones");
                    JOptionPane.showMessageDialog(null, qualificationController.readAll().toString());
                }

                case "3" -> {
                    JOptionPane.showMessageDialog(null, "Vas a buscar una calificacion");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id de la calificacion"));

                    JOptionPane.showMessageDialog(null, qualificationController.readOnlyOne(id).toString());
                }

                case "4" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id de la calificacion que quieres actualizar: "));

                    int quality = Integer.parseInt(JOptionPane.showInputDialog("Dame la calificacion del 0 al 100"));

                    if(quality >= 0 && quality <= 100){
                        qualification.setQuality(quality);
                        qualificationController.update(qualification,id);
                    }
                    else{
                        JOptionPane.showConfirmDialog(null,"Calificacion invalida");
                    }
                }

                case "5" -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Dame el id de la calificacion que quieres eliminar: "));
                    qualificationController.delete(id);
                }

                case "6" -> {
                    JOptionPane.showMessageDialog(null,
                            "Vas a volver al menu principal");
                }

                default -> {
                    JOptionPane.showMessageDialog(null, "Opcion invalida");
                }
            }
        }while (!option.equals("6"));
    }

    //Aqui se retorna un string para las interfaces
    public static String showOptions(String entity){
        return JOptionPane.showInputDialog(
                "Bienvenido a el menu de " + entity + "\n" +
                        """
                        1. Crear
                        
                        2. Leer todos
                        
                        3. Leer uno
                        
                        4. Actualizar
                        
                        5. Eliminar
                        
                        6. Volver
                        """);
    }

}