package oop.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.presenter.SimulationPresenter;

import java.io.IOException;


public class SimulationApp extends Application {
    public void start(Stage primaryStage) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
        BorderPane viewRoot = loader.load();
        SimulationPresenter presenter = loader.getController();
        this.configureStage(primaryStage, viewRoot);
        primaryStage.show();
        getParameters().getRaw();


//        MapChangeListener observer = new ConsoleMapDisplay();
//        presenter.setWorldmap(map);
//        map.addObserver(presenter);
//        map.addObserver(observer);

    }

    private void configureStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Set Parameters");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }
}
