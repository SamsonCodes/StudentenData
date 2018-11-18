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
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class StudentDataManager extends Application
{

    private final static String TITLE = "StudentApp";
    private static int id = 10000;
    private ArrayList<Student> students = new ArrayList();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

    @Override
    public void start(Stage primaryStage) throws Exception
    {
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
        loginGrid.add(pwBox, 1, 2);

        Button btn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        loginGrid.add(hbBtn, 1, 4);

        //grid.setGridLinesVisible(true);
        Scene loginScene = new Scene(loginGrid, 400, 275);
        
        //Input Scene
        GridPane inputGrid = new GridPane();
        inputGrid.setAlignment(Pos.CENTER);
        inputGrid.setHgap(10);
        inputGrid.setVgap(10);
        inputGrid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle2 = new Text("Voeg studenten toe");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        inputGrid.add(scenetitle2, 0, 0, 2, 1);
        
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
        
        

        Button addButton = new Button("Voeg toe");
        HBox addButtonBox = new HBox(10);
        addButtonBox.setAlignment(Pos.BOTTOM_RIGHT);
        addButtonBox.getChildren().add(addButton);
        inputGrid.add(addButtonBox, 1, 4);  
        
        Button logoutButton = new Button("Log uit");
        HBox logoutButtonBox = new HBox(10);
        logoutButtonBox.setAlignment(Pos.BOTTOM_LEFT);
        logoutButtonBox.getChildren().add(logoutButton);
        inputGrid.add(logoutButtonBox, 0, 4);
        
        final Text actiontarget = new Text();
        inputGrid.add(actiontarget, 1, 6);
		
		//grid2.setGridLinesVisible(true);
		
        Scene inputScene = new Scene(inputGrid, 400, 275);
        
        //Student scene
        GridPane studentGrid = new GridPane();
        studentGrid.setAlignment(Pos.CENTER);
        studentGrid.setHgap(10);
        studentGrid.setVgap(10);
        studentGrid.setPadding(new Insets(25, 25, 25, 25));
        
        Text studentTitle = new Text("Welkom " + "student");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        studentGrid.add(studentTitle, 0, 0, 2, 1);
        
        Scene studentScene = new Scene(studentGrid, 400, 275);
        Button logoutButton2 = new Button("Log uit");
        HBox logoutButtonBox2 = new HBox(10);
        logoutButtonBox2.setAlignment(Pos.BOTTOM_LEFT);
        logoutButtonBox2.getChildren().add(logoutButton2);
        studentGrid.add(logoutButtonBox2, 0, 4);
        
        btn.setOnAction((ActionEvent e) ->
        {
            String userNameText = userTextField.getText();
            if(userNameText.equals("Admin"))
            {
                primaryStage.setScene(inputScene);
            }
            else if(userNameText.startsWith("s"))
            {
                String idString = userNameText.substring(1, userNameText.length());
                if(isNumeric(idString))
                {
                    int idNumber = Integer.parseInt(idString);
                    if(studentInSystem(idNumber))
                    {
                        primaryStage.setScene(studentScene);
                    }
                }
            }
        });
        
        addButton.setOnAction((ActionEvent e) ->
        {     
            boolean add = true;
            Date birthDate = new Date();
            String firstName = firstNameField.getText();            
            String lastName = lastNameField.getText();
            firstNameField.setText("");
            lastNameField.setText("");
            String dateString = dateField.getText();
            dateField.setText("");
            if(firstName.isEmpty() || lastName.isEmpty() || dateString.isEmpty())
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
            }
            if(add)
            {
                Date enrollDate = new Date();
                Student student = new Student(firstName, lastName, id, birthDate, enrollDate);
                id++;
                students.add(student);
                actiontarget.setFill(Color.DARKGREEN);
                actiontarget.setText("s" + student.getIdNumber() +  " toegevoegd");
            }
            else
            {                
                actiontarget.setFill(Color.FIREBRICK);
                actiontarget.setText("Ingevoerde data ongeldig!");
            }
        });
        
        logoutButton.setOnAction((ActionEvent e) ->
        {
            primaryStage.setScene(loginScene);
        });
        
        logoutButton2.setOnAction((ActionEvent e) ->
        {
            primaryStage.setScene(loginScene);
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

    public static void main(String[] args)
    {
        launch(args);
    }

}
