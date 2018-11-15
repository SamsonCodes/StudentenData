/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentendatamanager;

import java.util.ArrayList;
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
    private static final String LOGIN = "login";
    private static int id = 10000;
    private ArrayList<Student> students;

    private void init(String[] args)
    {
        students = new ArrayList<>();
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        primaryStage.setTitle(TITLE);
        
        //Login Scene
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Welkom");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Gebruikersnaam:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Wachtwoord:");
        grid.add(pw, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        //grid.setGridLinesVisible(true);
        Scene scene = new Scene(grid, 400, 275);
        
        //Scene2
        GridPane grid2 = new GridPane();
        grid2.setAlignment(Pos.CENTER);
        grid2.setHgap(10);
        grid2.setVgap(10);
        grid2.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle2 = new Text("Voeg studenten toe");
        scenetitle2.setFont(Font.font("Tahoma", FontWeight.NORMAL, 30));
        grid2.add(scenetitle2, 0, 0, 2, 1);
        
        Label userName2 = new Label("Naam Student:");
        grid2.add(userName2, 0, 1);

        TextField userTextField2 = new TextField();
        grid2.add(userTextField2, 1, 1);

        Button btn2 = new Button("Voeg toe");
        HBox hbBtn2 = new HBox(10);
        hbBtn2.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn2.getChildren().add(btn2);
        grid2.add(hbBtn2, 1, 4);       
        
        final Text actiontarget = new Text();
        grid2.add(actiontarget, 1, 6);
		
		//grid2.setGridLinesVisible(true);
		
        Scene scene2 = new Scene(grid2, 400, 275);
        
        btn.setOnAction((ActionEvent e) ->
        {
            primaryStage.setScene(scene2);
        });
        
        btn2.setOnAction((ActionEvent e) ->
        {
            actiontarget.setFill(Color.FIREBRICK);
            String name = userTextField2.getText();
            userTextField2.setText("");
            actiontarget.setText(name + " toegevoegd");
        });
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args)
    {
        StudentDataManager app = new StudentDataManager();
        app.init(args);
        launch(args);
    }

}
