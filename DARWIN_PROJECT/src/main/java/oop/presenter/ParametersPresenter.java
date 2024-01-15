package oop.presenter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import oop.Simulation;
import oop.model.*;

import java.io.IOException;

import static com.sun.javafx.application.ParametersImpl.getParameters;


public class ParametersPresenter{

    private int CELL_WIDTH = 40;
    private int CELL_HEIGHT = 40;

    @FXML
    public Spinner heightSpinner;

    @FXML
    public Spinner basinsSpinner;

    @FXML
    public Spinner widthSpinner;

    @FXML
    public Spinner animalsNumSpinner;

    @FXML
    public Spinner plantNumSpinner;

    @FXML
    public Spinner dailyPlantsNumSpinner;

    @FXML
    public ComboBox mapVariantBox;

    @FXML
    public Spinner animalStartEnergySpinner;

    @FXML
    public Spinner animalReproduceEnergySpinner;

    @FXML
    public Spinner animalLostToReproduceEnergySpinner;

    @FXML
    public Spinner eatEnergySpinner;

    @FXML
    public Spinner genomLengthSpinner;

    @FXML
    public Spinner minMutationSpinner;

    @FXML
    public Spinner maxMutationSpinner;

    @FXML
    public ComboBox genomVariantBox;
    @FXML
    public CheckBox fileSaving;



    public void onSimulationStartClicked(){
        try{
            WorldParameters worldParameters = new WorldParametersBuilder()
                    .setStartAnimalNumber((int) animalsNumSpinner.getValue())
                    .setMapHeight((int) heightSpinner.getValue())
                    .setMapWariant(mapVariantBox.getSelectionModel().getSelectedIndex())
                    .setDailyPlantsAdded((int) dailyPlantsNumSpinner.getValue())
                    .setBasinsNumber((int) basinsSpinner.getValue())
                    .setMapWidth((int) widthSpinner.getValue())
                    .setStartPlantNumber((int) plantNumSpinner.getValue())
                    .build();

            AnimalParameters animalParameters = new AnimalParametersBuilder()
                    .setGenomWariant(genomVariantBox.getSelectionModel().getSelectedIndex())
                    .setEatEnergy((int) eatEnergySpinner.getValue())
                    .setAnimalStartEnergy((int) animalStartEnergySpinner.getValue())
                    .setEnergyToReproduce((int) animalReproduceEnergySpinner.getValue())
                    .setEnergyLostToReproduce((int) animalLostToReproduceEnergySpinner.getValue())
                    .setGenomLength((int) genomLengthSpinner.getValue())
                    .setMinMutation((int) minMutationSpinner.getValue())
                    .setMaxMutation((int) maxMutationSpinner.getValue())
                    .build();

            AbstractWorldMap map = (AbstractWorldMap) ChosingMapVariant.createMap(worldParameters);

            Stage secondStage = new Stage();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulation.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationPresenter presenter = loader.getController();
            presenter.setInicialValues();
            WorldApp.configureSecondStage(secondStage, viewRoot);
            secondStage.show();

            MapChangeListener observer = new ConsoleMapDisplay();
            presenter.setWorldmap(map);
            presenter.setParameters(worldParameters, animalParameters);
            presenter.drawColorBox();
            map.addObserver(presenter);
            map.addObserver(observer);
            if(fileSaving.isSelected()){
                StatsSaver statsSaver = new StatsSaver(presenter);
                map.addObserver(statsSaver);
            }

        } catch(IllegalArgumentException ignored){
            System.out.println(ignored.getMessage());
            System.exit(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
