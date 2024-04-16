package FurniturePackage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;


public class DashboardApp extends Application {
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
        HBox.setHgrow(navBar, Priority.ALWAYS); // This ensures the navBar can expand and push the profileContainer to the right

        root.setTop(new VBox(topContainer));

        // Main Container for Dashboard Content
        VBox mainContainer = new VBox();
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setPadding(new Insets(25));
        mainContainer.setPrefSize(800, 600);

        Label lblTitle = new Label("");
        lblTitle.setId("dashboard-title");

        StackPane centerContent = new StackPane();
        centerContent.setAlignment(Pos.TOP_CENTER);

        VBox content = new VBox(20);
        content.setAlignment(Pos.TOP_CENTER);

        GridPane boxesGrid = new GridPane();
        boxesGrid.setHgap(20);
        boxesGrid.setVgap(20);
        boxesGrid.setAlignment(Pos.TOP_CENTER);

        Pane boxObject = createBox("Furniture View");
        boxesGrid.add(boxObject, 0, 0);

        Pane boxFurnitureRoom = createBox("Interior Room"); // Added for completeness
        boxesGrid.add(boxFurnitureRoom, 1, 0);

        content.getChildren().add(boxesGrid);
        centerContent.getChildren().addAll( content);
        mainContainer.getChildren().addAll(lblTitle, centerContent);

        root.setCenter(mainContainer);

        // Image Slider and Rectangle in the same row
        HBox imageSliderRow = new HBox(createImageSlider(), centerContent);
        VBox.setMargin(imageSliderRow, new Insets(220, 0, 0, 0)); // Add margin top
        root.setTop(new VBox(topContainer, imageSliderRow));

        Scene scene = new Scene(root, 1550, 800);
        scene.getStylesheets().add(getClass().getResource("dashboard.css").toExternalForm());

        primaryStage.setTitle("Dashboard");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    private HBox createImageSlider() {
        HBox imageSliderContainer = new HBox();
        imageSliderContainer.setAlignment(Pos.CENTER_LEFT);
        ImageView imageView = new ImageView();
        imageView.setFitWidth(600); // Set the width of the images
        imageView.setFitHeight(500); // Set the height of the images

        // List of image paths
        String[] images = new String[]{
                "resources/cs.png",
                "resources/bd.png",
                "resources/bks.png",
        };

        // Using an array to hold the current image index
        final int[] currentIndex = {0};

        // Display the first image initially
        imageView.setImage(new Image(images[currentIndex[0]]));

        // Create a timeline for changing images
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), ev -> {
            currentIndex[0] = (currentIndex[0] + 1) % images.length;
            imageView.setImage(new Image(images[currentIndex[0]]));
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        imageSliderContainer.getChildren().add(imageView);
        return imageSliderContainer;
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
    private void openGalleryPage() {
        Gallery gallery = new Gallery();
        Stage galleryStage = new Stage();
        gallery.start(galleryStage);
    }

    private void openAboutUsPage() {
        AboutUs aboutUs = new AboutUs();
        Stage aboutUsStage = new Stage();
        aboutUs.start(aboutUsStage); // Start the About Us stage
    }

    private Pane createBox(String boxType) {
        VBox box = new VBox();
        box.setPrefSize(150, 100);
        box.setAlignment(Pos.CENTER);
        box.getStyleClass().add("box");

        Button btn = new Button(boxType);
        btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        VBox.setVgrow(btn, Priority.ALWAYS);
        btn.getStyleClass().add("box-button");

        if ("Furniture View".equals(boxType)) {
            btn.setOnAction(event -> loadMainPage()); 
        } else if ("Interior Room".equals(boxType)) {
            btn.setOnAction(event -> {
                FurnitureWorld furnitureWorld = new FurnitureWorld();
                Stage stage = new Stage();
                furnitureWorld.launchFurnitureWorld(stage); 
            });
        } else {
            btn.setOnAction(event -> System.out.println(boxType + " button clicked")); 
        }

        box.getChildren().add(btn);
        return box;
    }


    private void loadMainPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FurniturePackage/MainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root, 1550, 800);
            scene.getStylesheets().add(getClass().getResource("/FurniturePackage/styles.css").toExternalForm());
            primaryStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
