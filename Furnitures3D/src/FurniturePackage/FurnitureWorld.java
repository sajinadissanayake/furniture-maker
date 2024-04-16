package FurniturePackage;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class FurnitureWorld extends Application {

    private static final float WIDTH = 1550;
    private static final float HEIGHT = 800;
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final DoubleProperty angleX = new SimpleDoubleProperty(0);
    private final DoubleProperty angleY = new SimpleDoubleProperty(0);
    private Group furnitureGroup = new Group();
    private PhongMaterial backgroundMaterial = new PhongMaterial(); 

    @Override
    public void start(Stage primaryStage) {
        launchFurnitureWorld(primaryStage);
    }

    public void launchFurnitureWorld(Stage primaryStage) {
        BorderPane root = new BorderPane();
        prepareBackground(root);

        HBox navigationPanel = createNavigationPanel(primaryStage); 
        root.setTop(navigationPanel);

        furnitureGroup = new Group();
        root.setCenter(furnitureGroup);

        Camera camera = new PerspectiveCamera();
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        scene.setFill(Color.SILVER);
        scene.setCamera(camera);

        initMouseControl(scene, primaryStage);

        primaryStage.setTitle("Furniture World");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private HBox createNavigationPanel(Stage primaryStage) { 
        HBox navigationPanel = new HBox(10);
        navigationPanel.setStyle("-fx-background-color: transparent; -fx-padding: 10px;");

        // Background Change Button
        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.setOnAction(event -> changeBackground(primaryStage)); 
        styleButton(changeBackgroundButton);

        navigationPanel.getChildren().add(changeBackgroundButton);

        Button chairButton = new Button("Chair");
        chairButton.setOnAction(event -> displayChair());
        styleButton(chairButton);

        Button tableButton = new Button("Table");
        tableButton.setOnAction(event -> displayTable());
        styleButton(tableButton);

        Button bedButton = new Button("Bed");
        bedButton.setOnAction(event -> displayBed());
        styleButton(bedButton);

        Button cupboardButton = new Button("Cupboard");
        cupboardButton.setOnAction(event -> displayCupboard());
        styleButton(cupboardButton);

        Button roundedTableButton = new Button("Rounded Table");
        roundedTableButton.setOnAction(event -> displayRoundedTable());
        styleButton(roundedTableButton);

        Button bookshelfButton = new Button("Bookshelf");
        bookshelfButton.setOnAction(event -> displayBookshelf());
        styleButton(bookshelfButton);

        Button deskButton = new Button("Stool");
        deskButton.setOnAction(event -> displayDesk());
        styleButton(deskButton);

        //ColorPicker to the navigation panel
        ColorPicker colorPicker = new ColorPicker();
        colorPicker.setOnAction(event -> {
            Color newColor = colorPicker.getValue();
            changeColorOfCurrentFurniture(newColor);
        });

        navigationPanel.getChildren().addAll(chairButton, tableButton, bedButton, cupboardButton, roundedTableButton, bookshelfButton, deskButton, colorPicker);

        return navigationPanel;
    }

    private void changeBackground(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Background Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if (selectedFile != null) {
            backgroundMaterial.setDiffuseMap(new Image(selectedFile.toURI().toString()));
        }
    }

   
    private void changeColorOfCurrentFurniture(Color color) {
        PhongMaterial newMaterial = new PhongMaterial(color);
        if (furnitureGroup.getChildren().size() > 0 && furnitureGroup.getChildren().get(0) instanceof SmartGroup) {
            SmartGroup currentFurniture = (SmartGroup) furnitureGroup.getChildren().get(0);
            currentFurniture.getChildren().forEach(node -> {
                if (node instanceof Box) {
                    ((Box) node).setMaterial(newMaterial);
                } else if (node instanceof Cylinder) {
                    ((Cylinder) node).setMaterial(newMaterial);
                }
            });
        }
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: #0078D7; -fx-text-fill: #fff; -fx-border-radius: 5px; -fx-background-radius: 5px; -fx-padding: 5px 10px;");
    }

    private void prepareBackground(BorderPane root) {
        backgroundMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/stage.jpg")));

        Box background = new Box(WIDTH * 3, HEIGHT * 3, 1);
        background.setMaterial(backgroundMaterial);
        background.setTranslateZ(1500);

        root.getChildren().add(background);
    }

    private void displayChair() {
        furnitureGroup.getChildren().clear();
        SmartGroup chairGroup = new SmartGroup();
        prepareChair(chairGroup);
        furnitureGroup.getChildren().add(chairGroup);
        chairGroup.setTranslateX(WIDTH / 2);
        chairGroup.setTranslateY(HEIGHT / 2);
        chairGroup.setTranslateZ(-800);

    }
    private void displayTable() {
        furnitureGroup.getChildren().clear();
        SmartGroup tableGroup = new SmartGroup();
        prepareTable(tableGroup);
        furnitureGroup.getChildren().add(tableGroup);
        tableGroup.setTranslateX(WIDTH / 2);
        tableGroup.setTranslateY(HEIGHT / 2);
        tableGroup.setTranslateZ(-900);
    }

    private void displayBed() {
        furnitureGroup.getChildren().clear();
        SmartGroup bedGroup = new SmartGroup();
        prepareBed(bedGroup);
        furnitureGroup.getChildren().add(bedGroup);
        bedGroup.setTranslateX(WIDTH / 2);
        bedGroup.setTranslateY(HEIGHT / 2);
        bedGroup.setTranslateZ(-900);
    }

    private void displayCupboard() {
        furnitureGroup.getChildren().clear();
        SmartGroup cupboardGroup = new SmartGroup();
        prepareCupboard(cupboardGroup);
        furnitureGroup.getChildren().add(cupboardGroup);
        cupboardGroup.setTranslateX(WIDTH / 2);
        cupboardGroup.setTranslateY(HEIGHT / 2);
        cupboardGroup.setTranslateZ(-900);
    }



    private void displayRoundedTable() {
        furnitureGroup.getChildren().clear();
        SmartGroup roundedTableGroup = new SmartGroup();
        prepareRoundedTable(roundedTableGroup);
        furnitureGroup.getChildren().add(roundedTableGroup);
        roundedTableGroup.setTranslateX(WIDTH / 2);
        roundedTableGroup.setTranslateY(HEIGHT / 2);
        roundedTableGroup.setTranslateZ(-900);
    }

    private void displayBookshelf() {
        furnitureGroup.getChildren().clear();
        SmartGroup bookshelfGroup = new SmartGroup();
        prepareBookshelf(bookshelfGroup);
        furnitureGroup.getChildren().add(bookshelfGroup);
        bookshelfGroup.setTranslateX(WIDTH / 2);
        bookshelfGroup.setTranslateY(HEIGHT / 2);
        bookshelfGroup.setTranslateZ(-900); 
    }


    private void displayDesk() {
        furnitureGroup.getChildren().clear();
        SmartGroup deskGroup = new SmartGroup();
        prepareDesk(deskGroup);
        furnitureGroup.getChildren().add(deskGroup);
        deskGroup.setTranslateX(WIDTH / 2);
        deskGroup.setTranslateY(HEIGHT / 2);
        deskGroup.setTranslateZ(-900);
    }

    private void prepareChair(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/wood1.jpg")));

        // Components of the chair
        Box seat = new Box(100, 10, 80);
        seat.setMaterial(material);
        seat.setTranslateY(-5);

        Box backrest = new Box(100, 120, 5);
        backrest.setMaterial(material);
        backrest.setTranslateY(-60);
        backrest.setTranslateZ(-38);

        Box leg1 = new Box(10, 50, 10);
        leg1.setMaterial(material);
        leg1.setTranslateY(25);
        leg1.setTranslateX(-45);
        leg1.setTranslateZ(30);

        Box leg2 = new Box(10, 50, 10);
        leg2.setMaterial(material);
        leg2.setTranslateY(25);
        leg2.setTranslateX(45);
        leg2.setTranslateZ(30);

        Box leg3 = new Box(10, 50, 10);
        leg3.setMaterial(material);
        leg3.setTranslateY(25);
        leg3.setTranslateX(-45);
        leg3.setTranslateZ(-30);

        Box leg4 = new Box(10, 50, 10);
        leg4.setMaterial(material);
        leg4.setTranslateY(25);
        leg4.setTranslateX(45);
        leg4.setTranslateZ(-30);

        group.getChildren().addAll(seat, backrest, leg1, leg2, leg3, leg4);
    }

    private void prepareTable(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/spec.jpg")));

        // Tabletop
        Box tabletop = new Box(200, 10, 100);
        tabletop.setMaterial(material);
        tabletop.setTranslateY(-15);

        // Legs
        Box leg1 = createTableLeg(material);
        leg1.setTranslateX(-90);
        leg1.setTranslateZ(40);

        Box leg2 = createTableLeg(material);
        leg2.setTranslateX(90);
        leg2.setTranslateZ(40);

        Box leg3 = createTableLeg(material);
        leg3.setTranslateX(-90);
        leg3.setTranslateZ(-40);

        Box leg4 = createTableLeg(material);
        leg4.setTranslateX(90);
        leg4.setTranslateZ(-40);

        group.getChildren().addAll(tabletop, leg1, leg2, leg3, leg4);
    }

    private Box createTableLeg(PhongMaterial material) {
        Box leg = new Box(10, 100, 10);
        leg.setMaterial(material);
        leg.setTranslateY(40);
        return leg;
    }

    private void prepareBed(SmartGroup group) {
        PhongMaterial mattressMaterial = new PhongMaterial();
        mattressMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpg")));
        // Mattress
        Box mattress = new Box(150, 15, 250);
        mattress.setMaterial(mattressMaterial);
        mattress.setTranslateY(-15);

        Box mattress1 = new Box(150, 20, 250);
        mattress.setMaterial(mattressMaterial);
        mattress.setTranslateY(15);

        // Headboard
        Box headboard = new Box(150, 90, 10);
        headboard.setMaterial(mattressMaterial); // Optionally, use a distinct material for contrast
        headboard.setTranslateY(5);
        headboard.setTranslateZ(-130);

        // Legs
        Box leg1 = new Box(150, 70, 10);
        leg1.setMaterial(mattressMaterial);
        leg1.setTranslateY(10);
        leg1.setTranslateX(0);
        leg1.setTranslateZ(120);

        group.getChildren().addAll(mattress,mattress1, headboard, leg1);
    }

    private void prepareCupboard(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg1.jpg")));

        // Main body of the cupboard
        Box body = new Box(200, 300, 50);
        body.setMaterial(material);

        // Shelves
        Box shelf1 = new Box(190, 5, 40);
        shelf1.setMaterial(material);
        shelf1.setTranslateY(-75);

        Box shelf2 = new Box(190, 5, 40);
        shelf2.setMaterial(material);
        shelf2.setTranslateY(0);

        Box shelf3 = new Box(190, 5, 40);
        shelf3.setMaterial(material);
        shelf3.setTranslateY(75);

        // Adding doors
        Box door1 = new Box(95, 290, 2);
        door1.setMaterial(material);
        door1.setTranslateX(-52.5);
        door1.setTranslateZ(-26);

        Box door2 = new Box(95, 290, 2);
        door2.setMaterial(material);
        door2.setTranslateX(52.5);
        door2.setTranslateZ(-26);

        group.getChildren().addAll(body, shelf1, shelf2, shelf3, door1, door2);
    }

    private void prepareRoundedTable(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/gold.jpg")));

        // Tabletop (rounded)
        Cylinder tabletop = new Cylinder(120, 10);
        tabletop.setMaterial(material);
        tabletop.setTranslateY(-20);

        Cylinder tabletop1 = new Cylinder(15, 90);
        tabletop1.setMaterial(material);
        tabletop1.setTranslateY(25);

        Cylinder tabletop2 = new Cylinder(50, 20);
        tabletop2.setMaterial(material);
        tabletop2.setTranslateY(60);

        group.getChildren().addAll(tabletop, tabletop1, tabletop2);
    }


    private void prepareBookshelf(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/bg4.jpeg")));

        // Main body of the bookshelf
        Box body = new Box(150, 200, 10);
        body.setMaterial(material);
        body.setTranslateY(-15);
        body.setTranslateZ(15);


        // Shelves
        Box shelf1 = new Box(140, 5, 40);
        shelf1.setMaterial(material);
        shelf1.setTranslateY(-80);

        Box shelf2 = new Box(140, 5, 40);
        shelf2.setMaterial(material);
        shelf2.setTranslateY(-25);

        Box shelf3 = new Box(140, 5, 40);
        shelf3.setMaterial(material);
        shelf3.setTranslateY(30);

        // Adding vertical separators between shelves
        Box separator1 = new Box(10, 230, 40);
        separator1.setMaterial(material);
        separator1.setTranslateX(-70);


        Box separator2 = new Box(10, 230, 40);
        separator2.setMaterial(material);
        separator2.setTranslateX(70);

        group.getChildren().addAll(body, shelf1, shelf2, shelf3, separator1, separator2);
    }

    private void prepareDesk(SmartGroup group) {
        PhongMaterial material = new PhongMaterial();
        material.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/metal.jpg")));

        // Desk surface
        Box surface = new Box(200, 5, 100);
        surface.setMaterial(material);
        surface.setTranslateY(-15);

        // Legs
        Box leg1 = new Box(10, 30, 10);
        leg1.setMaterial(material);
        leg1.setTranslateX(-90);
        leg1.setTranslateZ(40);

        Box leg2 = new Box(10, 30, 10);
        leg2.setMaterial(material);
        leg2.setTranslateX(90);
        leg2.setTranslateZ(40);

        Box leg3 = new Box(10, 30, 10);
        leg3.setMaterial(material);
        leg3.setTranslateX(-90);
        leg3.setTranslateZ(-40);

        Box leg4 = new Box(10, 30, 10);
        leg4.setMaterial(material);
        leg4.setTranslateX(90);
        leg4.setTranslateZ(-40);

        group.getChildren().addAll(surface, leg1, leg2, leg3, leg4);
    }




    private void initMouseControl(Scene scene, Stage stage) {
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = angleX.get();
            anchorAngleY = angleY.get();
        });

        scene.setOnMouseDragged(event -> {
            angleX.set(anchorAngleX - (anchorY - event.getSceneY()));
            angleY.set(anchorAngleY + anchorX - event.getSceneX());
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double delta = event.getDeltaY();
            furnitureGroup.translateZProperty().set(furnitureGroup.getTranslateZ() + delta);
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    class SmartGroup extends Group {
        SmartGroup() {
            Rotate xRotate = new Rotate(0, Rotate.X_AXIS);
            Rotate yRotate = new Rotate(0, Rotate.Y_AXIS);
            xRotate.angleProperty().bind(angleX);
            yRotate.angleProperty().bind(angleY);
            this.getTransforms().addAll(xRotate, yRotate);
        }
    }
}
