package FurniturePackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class SignIn extends Application {
    @Override
    public void start(Stage primaryStage) {
        VBox mainContainer = new VBox(); 
        mainContainer.setAlignment(Pos.CENTER);
        mainContainer.setPadding(new Insets(25));
        mainContainer.setPrefSize(800, 600); 

        StackPane root = new StackPane(); 
        root.setAlignment(Pos.CENTER);

        // Transparent box with white border
        Rectangle border = new Rectangle(600, 400);
        border.setFill(null);
        border.setStroke(Color.WHITE);
        border.setStrokeWidth(5); // Define the border width

        border.setEffect(new javafx.scene.effect.DropShadow(10, 5, 5, Color.WHITE));

        VBox content = new VBox(20);
        content.setAlignment(Pos.CENTER);

        Label lblTitle = new Label("Sign In");
        lblTitle.setId("dashboard-title");

        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Username");
        txtUsername.setPrefWidth(150);

        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Email");
        txtEmail.setPrefWidth(150);

        PasswordField txtpwd = new PasswordField();
        txtpwd.setPromptText("Password");
        txtpwd.setPrefWidth(150);

        Label lblFeedback = new Label("Welcome to the FurniMaker !");
        lblFeedback.setId("feedback-label");

        Hyperlink loginLink = new Hyperlink("Login");
        Button btnSignIn = new Button("Sign In");
        btnSignIn.getStyleClass().add("dashboard-button");

        txtUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            lblFeedback.setText("Input: " + newValue);
        });

        loginLink.setOnAction(e -> {
            System.out.println("Login link clicked...");
            openLoginPage(primaryStage);
        });

        btnSignIn.setOnAction(e -> {
            System.out.println("Sign In button clicked...");
            openLoginPage(primaryStage);
        });

        HBox buttonBox = new HBox(10); 
        buttonBox.getChildren().addAll(loginLink, btnSignIn);
        buttonBox.setAlignment(Pos.CENTER); 

        // Adding components to the content VBox
        content.getChildren().addAll(lblTitle, txtUsername, txtEmail, txtpwd, lblFeedback, buttonBox);

        // Adding the content VBox and border to the StackPane
        root.getChildren().addAll(border, content);

        mainContainer.getChildren().add(root);

        Scene scene = new Scene(mainContainer, 1550, 800);
        scene.getStylesheets().add(getClass().getResource("logsig.css").toExternalForm());

        primaryStage.setTitle("Sign In");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openLoginPage(Stage primaryStage) {
        login login = new login();
        login.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
