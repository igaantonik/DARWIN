package oop.model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.presenter.ParametersPresenter;

import java.io.IOException;


public class WorldApp extends Application {

    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("parameters.fxml"));
        BorderPane viewRoot = loader.load();
        ParametersPresenter presenter = loader.getController();
        this.configureFirstStage(primaryStage, viewRoot);
        primaryStage.show();
        getParameters().getRaw();
    }


    private void configureFirstStage(Stage primaryStage, BorderPane viewRoot) {
        var scene = new Scene(viewRoot);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Parametry");
        primaryStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        primaryStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

    public static void configureSecondStage(Stage secondStage, BorderPane viewRoot){
        var scene = new Scene(viewRoot);
        secondStage.setScene(scene);
        secondStage.setTitle("Symulacja");
        secondStage.minWidthProperty().bind(viewRoot.minWidthProperty());
        secondStage.minHeightProperty().bind(viewRoot.minHeightProperty());
    }

}

