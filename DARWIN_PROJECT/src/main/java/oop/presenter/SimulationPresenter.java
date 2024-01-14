package oop.presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import oop.Simulation;
import oop.model.*;

import java.util.List;
import java.util.Map;


public class SimulationPresenter implements MapChangeListener {

    private AbstractWorldMap worldmap;
    private WorldParameters worldParameters;
    private AnimalParameters animalParameters;
    private int CELL_WIDTH = 40;
    private int CELL_HEIGHT = 40;
    private Boundary boundary;
    private int height;
    private int width;
    private Simulation simulation;

    @FXML
    private GridPane mapGrid;
    @FXML
    public HBox animalColors;
    @FXML
    public Label animalNumStats;
    @FXML
    public Label plantsNumStats;
    @FXML
    public Label freeFieldsStats;
    @FXML
    public Label averageEnergyStats;
    @FXML
    public Label popularGenomeStats;
    @FXML
    public Label averageLifeStats;
    @FXML
    public Label averageChildrenNumStats;


    //settery
    public void setWorldmap(WorldMap worldmap) {
        this.worldmap = (AbstractWorldMap) worldmap;
    }

    public void setParameters(WorldParameters worldParameters, AnimalParameters animalParameters) {
        this.animalParameters = animalParameters;
        this.worldParameters = worldParameters;
    }

    // legend

    public void drawColorBox(){
        for (double i=0; i <= animalParameters.getAnimalStartEnergy(); i+= (double) animalParameters.getAnimalStartEnergy() /20) {
            Rectangle colorRect = new Rectangle(10, 10);
            colorRect.setFill( new Color((double)i / (double) animalParameters.getAnimalStartEnergy(),0.5, 0.5, 0.6));
            animalColors.getChildren().add(colorRect);
        }
    }

    // Drawing map
    private Vector2d vectorOnGrid(Vector2d vector2d){
        int y = this.height -1 - vector2d.getY() + boundary.lower_left().getY();
        int x = vector2d.getX()  - boundary.lower_left().getX();
        return new Vector2d(x,y);
    }

    public void drawMap(){
        this.clearGrid();
        this.drawStat();
        this.boundary = worldmap.getCurrentBounds();
        this.height = worldmap.getHeight();
        this.width = worldmap.getWidth();
        mapGrid.minHeight(this.height);
        mapGrid.minWidth(this.width);

        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        for(int i = 1; i < this.width; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for(int j = 1; j < this.height; j++){
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        }
        Map<Vector2d, MapElement> elements = worldmap.getElements();
        for(Vector2d vector: elements.keySet()){
            Vector2d gridVector = vectorOnGrid(vector);
            mapGrid.add(getElement(elements.get(vector)), gridVector.getX(), gridVector.getY());
        }

        }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public Circle getElement(MapElement element){
        Color newcolor = getColor(element);
        Circle circle = new Circle(20, newcolor);
        return circle;
    }

    public Color getColor(MapElement element){
        if(element instanceof Animal){
            return(new Color((double) (double)((Animal) element).getEnergyLevel() /animalParameters.getAnimalStartEnergy(), 0.5, 0.5,0.6));
        } else if (element instanceof Plant){
            return(new Color(0.2, 1, 0.3, 0.5));
        } else{
            return(new Color(0, 0.2, 1, 0.6));
        }
    }

    @Override
    public void mapChanged(WorldMap worldmap, String message) {
        setWorldmap(worldmap);
        Platform.runLater(this::drawMap);
    }

    //statistitcs
    public void drawStat(){
        this.animalNumStats.setText("");
        this.averageEnergyStats.setText("");
        this.averageLifeStats.setText("");
        this.plantsNumStats.setText("");
        this.freeFieldsStats.setText("");
        this.popularGenomeStats.setText("");
        this.averageChildrenNumStats.setText("");
    }

    //button events
    public void onSimulationStartClicked(){
        try{
            Simulation simulation = new Simulation(worldmap, animalParameters, worldParameters);
            this.simulation = simulation;
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsync();
        } catch(IllegalArgumentException ignored){
            System.out.println(ignored.getMessage());
            System.exit(0);
        }
    }



    public void resumeSimulation(ActionEvent actionEvent) {
    }

    public void stopSimulation(ActionEvent actionEvent) {
        this.simulation.pause();
    }
}
