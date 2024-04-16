package FurniturePackage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FurniturePageController {

    private void displayInNewWindow(Group viewGroup, String title) {
        Scene scene = new Scene(viewGroup, 1550, 800); // You can adjust the size as needed
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onViewChair(ActionEvent event) {
        Chair2D chair2D = new Chair2D();
        Group chairView = chair2D.createChairView();
        displayInNewWindow(chairView, "Chair");
    }

    @FXML
    protected void onViewBed(ActionEvent event) {
        Group bedView = Bed2D.createBedView();
        displayInNewWindow(bedView, "Bed");
    }

    @FXML
    protected void onViewCupboard(ActionEvent event) {
        Group cupboardView = Cupboard2D.createCupboardView();
        displayInNewWindow(cupboardView, "Cupboard");
    }

    @FXML
    protected void onViewTable(ActionEvent event) {
        Group tableView = Table2D.createTableView();
        displayInNewWindow(tableView, "Table");
    }

    @FXML
    protected void onViewRack(ActionEvent event) {
        Group rackView = Rack2D.createRackView();
        displayInNewWindow(rackView, "Rack");
    }

    @FXML
    protected void onViewBench(ActionEvent event) {
        Group benchView = Bench2D.createBenchView();
        displayInNewWindow(benchView, "Bench");
    }

    @FXML
    protected void onViewDoor(ActionEvent event) {
        Group doorView = Door2D.createDoorView();
        displayInNewWindow(doorView, "Door");
    }

    @FXML
    protected void onViewBookshelf(ActionEvent event) {
        Group bookshelfView = Bookshelf2D.createBookshelfView();
        displayInNewWindow(bookshelfView, "Bookshelf");
    }



    @FXML
    protected void onBack(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/FurniturePackage/MainPage.fxml"));
            Scene scene = new Scene(root, 1550, 800);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
