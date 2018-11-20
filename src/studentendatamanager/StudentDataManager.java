/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentDataManager extends Application
{

    private final static String TITLE = "StudentApp", PATH = "C:\\StudentenDataManager\\";
    private static int id = 10000;
    private ArrayList<Student> students = new ArrayList();
    public final static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    private final static int FRAME_WIDTH = 1000, FRAME_HEIGHT = 800;
    private Student loggedIn;
    

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        loadData();
        primaryStage.setTitle(TITLE);
        
        //Login Scene
        GridPane loginGrid = new GridPane();
        loginGrid.setAlignment(Pos.CENTER);
        loginGrid.setHgap(10);
        loginGrid.setVgap(10);
        loginGrid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welkom");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        loginGrid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Gebruikersnaam:");
        loginGrid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        loginGrid.add(userTextField, 1, 1);

        Label pw = new Label("Wachtwoord:");
        loginGrid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setText("dataridder");
        loginGrid.add(pwBox, 1, 2);

        Button loginButton = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);
        loginGrid.add(hbBtn, 1, 4);
        
        final Text loginMessage = new Text();
        loginGrid.add(loginMessage, 0, 6, 3, 1);

        //grid.setGridLinesVisible(true);
        Scene loginScene = new Scene(loginGrid, FRAME_WIDTH, FRAME_HEIGHT);
        
    //Input Scene
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle2 = new Text("Voeg studenten toe");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        inputGrid.add(scenetitle2, 0, 0, 2, 1);
        
        Button inputLogoutButton = new Button("Log uit");
        HBox logoutButtonBox = new HBox(10);
        logoutButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        logoutButtonBox.getChildren().add(inputLogoutButton);
        inputGrid.add(logoutButtonBox, 0, 7);
        
        Label firstNameLabel = new Label("Voornaam: ");
        inputGrid.add(firstNameLabel, 0, 1);

        TextField firstNameField = new TextField();
        inputGrid.add(firstNameField, 1, 1);
        
        Label lastNameLabel = new Label("Achternaam: ");
        inputGrid.add(lastNameLabel, 0, 2);
        
        TextField lastNameField = new TextField();
        inputGrid.add(lastNameField, 1, 2);
        
        Label dateLabel = new Label("Geboorte datum: ");
        inputGrid.add(dateLabel, 0, 3);   
        
        TextField dateField = new TextField();
        dateField.setText("dd/MM/yyyy");
        inputGrid.add(dateField, 1, 3);      
        
        Label yearLabel = new Label("Studiejaar: ");
        inputGrid.add(yearLabel, 2, 1);
        
        TextField yearField = new TextField();
        inputGrid.add(yearField, 3, 1);

        Button addStudentButton = new Button("Voeg toe");
        HBox addButtonBox = new HBox(10);
        addButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        addButtonBox.getChildren().add(addStudentButton);
        inputGrid.add(addButtonBox, 3, 4);  
        
        TableView<Student> studentTable = new TableView();
        Label studentTableLabel = new Label("Studenten");
        studentTableLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        studentTable.setEditable(false);
        TableColumn idCol = new TableColumn("ID-nummer");
        TableColumn firstNameCol = new TableColumn("Voornaam");
        TableColumn lastNameCol = new TableColumn("Achternaam");
        TableColumn birthDateCol = new TableColumn("Verjaardag");
        TableColumn yearCol = new TableColumn("Jaar");
        ObservableList<Student> studentTableItems = FXCollections.observableArrayList();
        idCol.setCellValueFactory(new PropertyValueFactory<>("idNumber"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        birthDateCol.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        yearCol.setCellValueFactory(new PropertyValueFactory<>("year"));    
        studentTable.getColumns().addAll(idCol, firstNameCol, lastNameCol, birthDateCol, yearCol);
        final VBox studentVbox = new VBox();
        studentVbox.setSpacing(5); //space between components (label and table)
        studentVbox.setPadding(new Insets(0, 0, 0, 0)); //ofset top, right, bottom, left
        studentVbox.getChildren().addAll(studentTableLabel, studentTable);
        inputGrid.add(studentVbox, 0, 5, 4, 1);
        
        Button removeStudentButton = new Button("Verwijder");
        HBox removeStudentBox = new HBox(20);
        removeStudentBox.setAlignment(Pos.BOTTOM_RIGHT);
        removeStudentBox.getChildren().add(removeStudentButton);
        inputGrid.add(removeStudentBox, 3, 6);
        
        final Text inputMessage = new Text();
        inputGrid.add(inputMessage, 1, 7);
		
		//grid2.setGridLinesVisible(true);
		
        Scene inputScene = new Scene(inputGrid, FRAME_WIDTH, FRAME_HEIGHT);
        
    //Student scene
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text studentTitle = new Text("Welkom ");
        studentTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        studentGrid.add(studentTitle, 0, 0, 2, 1);        
        
        ObservableList list = FXCollections.observableArrayList();
        for(Course c: Course.courses)
        {
            list.add(c.getName());
        }
        ChoiceBox cb = new ChoiceBox(list);
        studentGrid.add(cb, 0, 1);   
        
        Button addCourseButton = new Button("Voeg toe");
        HBox addCourseButtonBox = new HBox(10);
        addCourseButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        addCourseButtonBox.getChildren().add(addCourseButton);
        studentGrid.add(addCourseButtonBox, 1, 1);
        
        TableView<Course> courseTable = new TableView();
//        Label tableLabel = new Label("Vakken");
//        tableLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 24));
        courseTable.setEditable(false);
        TableColumn nameCol = new TableColumn("Vak");
        nameCol.setPrefWidth(200);
        TableColumn descriptionCol = new TableColumn("Omschrijving");
        descriptionCol.setPrefWidth(500);
        TableColumn scoreCol = new TableColumn("ECT");
        scoreCol.setPrefWidth(50);
        ObservableList<Course> courseTableItems = FXCollections.observableArrayList();
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("ECT"));    
        courseTable.getColumns().addAll(nameCol, descriptionCol, scoreCol);
        final VBox vbox = new VBox();
        vbox.setSpacing(5); //space between components (label and table)
        vbox.setPadding(new Insets(0,0,0,0)); //ofset top, right, bottom, left
        vbox.getChildren().add(courseTable);
        studentGrid.add(vbox, 0, 2, 4, 1);    
        
        Button delCourseButton = new Button("Verwijder");
        HBox delCourseButtonBox = new HBox(10);
        delCourseButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        delCourseButtonBox.getChildren().add(delCourseButton);
        studentGrid.add(delCourseButtonBox, 3, 3);
        
        Button studentLogoutButton = new Button("Log uit");
        HBox logoutButtonBox2 = new HBox(10);
        logoutButtonBox2.setAlignment(Pos.BOTTOM_LEFT);
        logoutButtonBox2.getChildren().add(studentLogoutButton);
        studentGrid.add(logoutButtonBox2, 0, 4);
        
        Scene studentScene = new Scene(studentGrid, FRAME_WIDTH, FRAME_HEIGHT);
        
    //Button functionality
        loginButton.setOnAction((ActionEvent e) ->
        {
            loginMessage.setText("");
            String userNameText = userTextField.getText();
            if(userNameText.toLowerCase().equals("admin") && pwBox.getText().toLowerCase().equals("dataridder"))
            {
                studentTableItems.clear();
                for(Student s: students)
                {
                    studentTableItems.add(s);
                    System.out.println("Added " + s.getFirstName() + " to student table!");
                }    
                studentTable.setItems(studentTableItems);
                primaryStage.setScene(inputScene);
            }
            else if(userNameText.startsWith("s") && pwBox.getText().toLowerCase().equals("dataridder"))
            {
                String idString = userNameText.substring(1, userNameText.length());
                if(isNumeric(idString))
                {
                    int idNumber = Integer.parseInt(idString);
                    if(studentInSystem(idNumber))
                    {
                        setLoggedIn(getStudent(idNumber));
                        studentTitle.setText(getLoggedIn().getFirstName() + " " + getLoggedIn().getLastName() + " (s" + getLoggedIn().getIdNumber() + ")");  
                        courseTableItems.clear();
                        for(Course c: getLoggedIn().getCourses())
                        {
                            courseTableItems.add(c);
                        }                        
                        courseTable.setItems(courseTableItems);
                        primaryStage.setScene(studentScene);
                    }
                }
            }
            else
            {
                loginMessage.setFill(Color.FIREBRICK);
                loginMessage.setText("Combinatie gebruikersnaam en wachtwoord onbekend");
            }
        });
        
        addStudentButton.setOnAction((ActionEvent e) ->
        {     
            boolean add = true;
            Date birthDate = new Date();
            int year = 0;
            String firstName = firstNameField.getText();            
            String lastName = lastNameField.getText();
            firstNameField.setText("");
            lastNameField.setText("");
            String dateString = dateField.getText();
            dateField.setText("");
            String yearString = yearField.getText();
            yearField.setText("");
            if(firstName.isEmpty() || lastName.isEmpty() || dateString.isEmpty() |! isNumeric(yearString))
            {
                add = false;
            }            
            else
            {  
                try
                {
                    birthDate = sdf.parse(dateString);
                }
                catch (ParseException ex)
                {
                    add = false;
                    Logger.getLogger(StudentDataManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                year = Integer.parseInt(yearString);
            }
            if(add)
            {
                Date enrollDate = new Date();
                Student student = new Student(firstName, lastName, id, birthDate, enrollDate, year);
                id++;
                students.add(student);
                studentTableItems.add(student);
                studentTable.setItems(studentTableItems);
                inputMessage.setFill(Color.DARKGREEN);
                inputMessage.setText("s" + student.getIdNumber() +  " toegevoegd");
                saveData();
            }
            else
            {                
                inputMessage.setFill(Color.FIREBRICK);
                inputMessage.setText("Ingevoerde data ongeldig!");
            }
        });
        
        removeStudentButton.setOnAction((ActionEvent e) ->
        {
            Student target = studentTable.getSelectionModel().getSelectedItem();
            students.remove(target);
            studentTableItems.remove(target);
            studentTable.setItems(studentTableItems);
            inputMessage.setFill(Color.FIREBRICK);
            inputMessage.setText("s" + target.getIdNumber() + " verwijderd");
            saveData();
        });
        
        inputLogoutButton.setOnAction((ActionEvent e) ->
        {
            primaryStage.setScene(loginScene);
        });
        
        studentLogoutButton.setOnAction((ActionEvent e) ->
        {
            primaryStage.setScene(loginScene);
        });
        
        addCourseButton.setOnAction((ActionEvent e) -> {
            for(Course c: Course.courses)
            {
                if(c.getName().equals(cb.getValue()) &! loggedIn.getCourses().contains(c))
                {                    
                    loggedIn.addCourse(c);
                    courseTableItems.add(c);
                    courseTable.setItems(courseTableItems);
                    saveData();
                }
            }
        });
        
        delCourseButton.setOnAction((ActionEvent e) ->
        {
            Course target = courseTable.getSelectionModel().getSelectedItem();
            getLoggedIn().getCourses().remove(target);
            courseTableItems.remove(target);
            courseTable.setItems(courseTableItems);
            saveData();
        });
        
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }
    
    private boolean studentInSystem(int id)
    {
        for(Student s: students)
        {
            if(s.getIdNumber() == id)
                return true;
        }
        return false;
    }
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        int i = Integer.parseInt(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    
    private void setLoggedIn(Student s)
    {
        loggedIn = s;
    }
    
    private Student getLoggedIn()
    {
        return loggedIn;
    }
    
    private Student getStudent(int idNumber)
    {
        Student student = null;
        for(Student s: students)
        {
            if(s.getIdNumber() == idNumber)
                student = s;
        }
        return student;
    }
    
    private void loadData()
    {        
        Course.load(PATH + "courses.txt");
        String data = DataHandler.loadData(PATH + "studentendata.txt");
        for(String studentData: XMLReader.getElementsPlus("student", data))
        {
            Student student = new Student(studentData);
            if(student.getIdNumber() >= id)
            {
                id = student.getIdNumber() + 1;
            }
            students.add(student);
        }
    }
    
    private void saveData()
    {
        DataHandler.createDirectory(PATH);
        ArrayList<String> data = new ArrayList();
        for(Student s: students)
        {
            data.add(s.getSaveData());
        }
        DataHandler.saveData(data, PATH + "studentendata.txt");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
