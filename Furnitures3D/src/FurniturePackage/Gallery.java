package FurniturePackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;

public class Gallery extends Application {
    private Stage primaryStage; 

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        BorderPane root = new BorderPane();

        // Navigation Bar Buttons
        Button homeButton = new Button("Dashboard");
        homeButton.getStyleClass().add("nav-button");
        homeButton.setOnAction(e -> System.out.println("Dashboard!"));

        Button aboutButton = new Button("About");
        aboutButton.getStyleClass().add("nav-button");
        aboutButton.setOnAction(e -> System.out.println("About Clicked!"));

        Button galleryButton = new Button("Gallery");
        galleryButton.getStyleClass().add("nav-button");
        galleryButton.setOnAction(e -> System.out.println("Gallery Clicked!"));

        Button logoutButton = new Button("Logout");
        logoutButton.getStyleClass().add("nav-button");
        logoutButton.setOnAction(e -> System.out.println("Logout Clicked!"));

        // Linked navigation
        logoutButton.setOnAction(e -> root.setCenter(openLogIN(primaryStage)));
        aboutButton.setOnAction(e -> openAboutUsPage());
        galleryButton.setOnAction(e -> openGalleryPage());
        homeButton.setOnAction(e -> root.setCenter(opendash(primaryStage)));

        // Navigation Bar Layout
        HBox navBar = new HBox(10, homeButton, aboutButton,galleryButton, logoutButton);
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setPadding(new Insets(10));

        // Profile Picture
        Image image = new Image("resources/profile.png"); // Adjust the path to your image
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50); // Adjust the size as necessary
        imageView.setFitWidth(50);

        // Creating a circle clip
        Circle clip = new Circle(25, 25, 25); // Assuming the imageView is 50x50, adjust the radius accordingly
        imageView.setClip(clip);

        // Wrap the ImageView in a StackPane
        StackPane imageContainer = new StackPane(imageView);
        imageContainer.getStyleClass().add("image-container");

        // Top-Right Container for Profile Picture
        HBox profileContainer = new HBox(imageContainer);
        profileContainer.setAlignment(Pos.CENTER);
        profileContainer.setPadding(new Insets(10));

        // Top Container for Navigation Bar and Profile Picture
        HBox topContainer = new HBox(navBar, profileContainer);
        HBox.setHgrow(navBar, Priority.ALWAYS); 

        root.setTop(new VBox(topContainer));

        // Main Container for Dashboard Content
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(25));
        mainContainer.setPrefSize(800, 600);

        Label lblTitle = new Label("Gallery");
        lblTitle.setId("dashboard-title");

        // Creating Rectangles
        GridPane rectanglesGrid = new GridPane();
        rectanglesGrid.setHgap(10);
        rectanglesGrid.setVgap(10);
        rectanglesGrid.setAlignment(Pos.CENTER);

        // Array of image paths
        String[] images = {
                "/resources/gallery/live1.jpg",
                "/resources/gallery/dine1.jpg",
                "/resources/gallery/kit1.jpg",
                "/resources/gallery/bed1.jpg",
                "/resources/gallery/live2.jpg",
                "/resources/gallery/dine2.jpg",
                "/resources/gallery/kit2.jpg",
                "/resources/gallery/bed2.jpg",
                "/resources/gallery/live3.jpg",
                "/resources/gallery/dine3.jpg",
                "/resources/gallery/kit3.jpg",
                "/resources/gallery/bed3.jpg",
        };

        // Array of column titles
        String[] columnTitles = {"Living Rooms", "Dining Rooms", "Bed Room", "Kitchen"};

        // Add column titles
        for (int j = 0; j < 4; j++) {
            Label columnTitle = new Label(columnTitles[j]);
            columnTitle.setAlignment(Pos.CENTER);
            columnTitle.setTextFill(Color.WHITE);
            rectanglesGrid.add(columnTitle, j, 0);
        }

        int imageIndex = 0; // Index to track the current image

        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < 4; j++) {
                Rectangle rectangle = new Rectangle(300, 200, Color.TRANSPARENT); 
                rectangle.setStroke(Color.BLACK); // Add a black border to the rectangle

                // Set background image to the rectangle
                Image backgroundImage = new Image(images[imageIndex]);
                rectangle.setFill(new ImagePattern(backgroundImage));

                imageIndex = (imageIndex + 1) % images.length;

                // Add mouse hover event handler
                rectangle.setOnMouseEntered(event -> {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), rectangle);
                    scaleTransition.setToX(1.2);
                    scaleTransition.setToY(1.2);
                    scaleTransition.play();
                });

                // Add mouse exit event handler
                rectangle.setOnMouseExited(event -> {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.2), rectangle);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.play();
                });

                rectanglesGrid.add(rectangle, j, i);
            }
        }

        mainContainer.getChildren().addAll(lblTitle, rectanglesGrid);

        root.setCenter(mainContainer);

        Scene scene = new Scene(root, 1550, 800);
        scene.getStylesheets().add(getClass().getResource("dashboard.css").toExternalForm());

        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void openGalleryPage() {
        Gallery gallery = new Gallery();
        Stage galleryStage = new Stage();
        gallery.start(galleryStage);
    }

    private Node openLogIN(Stage primaryStage) {
        login Login = new login();
        Login.start(primaryStage);
        return null;
    }

    private Node opendash(Stage primaryStage) {
        DashboardApp Dash = new DashboardApp();
        Dash.start(primaryStage);
        return null;
    }

    private void openAboutUsPage() {
        AboutUs aboutUs = new AboutUs();
        Stage aboutUsStage = new Stage();
        aboutUs.start(aboutUsStage); // Start the About Us stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}
