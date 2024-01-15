package oop.presenter;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
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

    public Label followedanimalEnergy;
    public Label followedanimalEaten;
    public Label followedanimalChildren;
    public Label followedanimalOffspring;
    public Label followedanimalDays;
    public Label followedAnimalDeath;
    public Label followedGenom;
    public Label followedanimalGen;
    private AbstractWorldMap worldmap;
    private WorldParameters worldParameters;
    private AnimalParameters animalParameters;
    private int CELL_WIDTH = 400;
    private int CELL_HEIGHT = 400;
    private Boundary boundary;
    private int height;
    private int width;
    private Simulation simulation;
    private Statistics stats;
    private boolean followingAnimal;
    private Animal animalFollowed;
    private AnimalStatistics animalStatistics;
    private int cellSize;


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
    @FXML
    public GridPane animalStats;
    @FXML
    public Label followeLabel;


    //settery
    public void setInicialValues(){
        this.followingAnimal = false;
        this.setFollowingStats(false);
    }

    public void setFollowingStats(boolean val){
        this.followeLabel.setVisible(val);
        ObservableList<Node> children = animalStats.getChildren();
        for (Node node : children) {
            if (node instanceof Label) {
                ((Label) node).setVisible(val);
            }
        }
    }
    public void setWorldmap(WorldMap worldmap) {
        this.worldmap = (AbstractWorldMap) worldmap;
    }

    public void setParameters(WorldParameters worldParameters, AnimalParameters animalParameters) {
        this.animalParameters = animalParameters;
        this.worldParameters = worldParameters;
    }

    public Statistics getStats(){
        return this.stats;
    }

    // legend

    public void drawColorBox(){
        for (double i=0; i <= animalParameters.getAnimalStartEnergy(); i+= (double) animalParameters.getAnimalStartEnergy() /20) {
            Rectangle colorRect = new Rectangle(10, 10);
            colorRect.setFill( new Color((double)i / (double) animalParameters.getAnimalStartEnergy(),0, 0, 0.6));
            animalColors.getChildren().add(colorRect);
        }
    }

    // Drawing map
    private Vector2d vectorOnGrid(Vector2d vector2d){
        int y = this.height -1 - vector2d.getY() + boundary.lower_left().getY();
        int x = vector2d.getX()  - boundary.lower_left().getX();
        return new Vector2d(x,y);
    }

    public void drawMap() {
        this.clearGrid();
        this.boundary = worldmap.getCurrentBounds();
        this.height = worldmap.getHeight();
        this.width = worldmap.getWidth();
        mapGrid.minHeight(this.height);
        mapGrid.minWidth(this.width);
        this.cellSize = this.calculateCellSize();


        mapGrid.getColumnConstraints().add(new ColumnConstraints(this.cellSize));
        mapGrid.getRowConstraints().add(new RowConstraints(this.cellSize));

        for (int i = 1; i < this.width; i++) {
            mapGrid.getColumnConstraints().add(new ColumnConstraints(this.cellSize));
        }
        for (int j = 1; j < this.height; j++) {
            mapGrid.getRowConstraints().add(new RowConstraints(this.cellSize));
        }
        Map<Vector2d, MapElement> elements = worldmap.getElements();
        for (Vector2d vector : elements.keySet()) {
            Vector2d gridVector = vectorOnGrid(vector);
            mapGrid.add(getElement(elements.get(vector)), gridVector.getX(), gridVector.getY());
            if(elements.get(vector) instanceof Animal) {
                Rectangle invisibleRect = new Rectangle(this.cellSize, this.cellSize, Color.TRANSPARENT);
                mapGrid.add(invisibleRect, gridVector.getX(), gridVector.getY());
                invisibleRect.setOnMouseClicked(event -> startToDisplayAnimalInfo((Animal) elements.get(vector)));
            }
        }
        this.drawStat();
    }

    private int calculateCellSize(){
        int maxCellWidth = this.CELL_WIDTH / (this.width + 1);
        int maxCellHeight = this.CELL_HEIGHT/ (this.height + 1);
        return Math.min(maxCellWidth, maxCellHeight);
    }
    private void clearGrid() {
        mapGrid.getChildren().retainAll(mapGrid.getChildren().get(0));
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    public Circle getElement(MapElement element){
        Color newcolor = getColor(element);
        Circle circle = new Circle(this.cellSize/2, newcolor);
        return circle;
    }

    public Color getColor(MapElement element){
        if(element instanceof Animal){
            return(new Color((double) (double)((Animal) element).getEnergyLevel() /animalParameters.getAnimalStartEnergy(), 0, 0,0.6));
        } else if (element instanceof Plant){
            return(new Color(0.5, 1, 0.5, 1));
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
        stats.statsChanged();
        this.animalNumStats.setText(stats.getAnimalsNumber());
        this.averageEnergyStats.setText(stats.getAverageEnergy());
        this.averageLifeStats.setText(stats.getAverageLifeTime());
        this.plantsNumStats.setText(stats.getPlantsNumber());
        this.freeFieldsStats.setText(stats.getFreeFields());
        this.popularGenomeStats.setText(stats.getMostGenom());
        this.averageChildrenNumStats.setText(stats.getAverageChildren());
        if(followingAnimal){
            displayAnimalStats();
        }
    }

    public void displayAnimalStats(){
        Vector2d gridVector = vectorOnGrid(this.animalFollowed.getPosition());
        mapGrid.add(new  Circle(this.cellSize/2, Color.CYAN), gridVector.getX(), gridVector.getY());
        this.animalStatistics.statsChanged();
        this.followedanimalEnergy.setText(this.animalStatistics.getEnergyLevel());
        this.followedanimalEaten.setText(this.animalStatistics.getPlantsEaten());
        this.followedanimalChildren.setText(this.animalStatistics.getKidsNumber());
        this.followedanimalOffspring.setText(this.animalStatistics.getOffspringNumber());
        this.followedanimalDays.setText(this.animalStatistics.getAge());
        this.followedAnimalDeath.setText(this.animalStatistics.getDeathDate());
        this.followedGenom.setText(this.animalStatistics.getGenom());
        this.followedanimalGen.setText(this.animalStatistics.getActiveGenomPart());
    }

    //button events

    public void startToDisplayAnimalInfo(Animal animal){
        this.followingAnimal = true;
        this.animalFollowed = animal;
        this.animalStatistics = new AnimalStatistics(animal);
        this.setFollowingStats(true);
    }

    public void onSimulationStartClicked(){
        try{
            Simulation simulation = new Simulation(worldmap, animalParameters, worldParameters);
            this.simulation = simulation;
            this.stats = new Statistics(simulation);
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsync();
        } catch(IllegalArgumentException ignored){
            System.out.println(ignored.getMessage());
            System.exit(0);
        }
    }

    public void resumeSimulation(ActionEvent actionEvent) {
        this.simulation.resume();
        SimulationEngine engine = new SimulationEngine(List.of(this.simulation));
        engine.runAsync();
    }

    public void stopSimulation(ActionEvent actionEvent) {
        this.simulation.pause();
        this.setInicialValues();
    }

    public void highlightAnimals(ActionEvent actionEvent) {
        for(Vector2d vector: this.stats.animalsWithMostPopularGenom()){
            Vector2d gridVector = vectorOnGrid(vector);
            mapGrid.add(new  Circle(this.cellSize/2, new Color(1,0,1,1)), gridVector.getX(), gridVector.getY());
        }

    }

    public void highlightPlantsPreferences(ActionEvent actionEvent) {
        for(Vector2d vector: this.stats.mostPreferedByPlants()){
            Vector2d gridVector = vectorOnGrid(vector);
            mapGrid.add(new  Circle(this.cellSize/2, Color.DARKGREEN), gridVector.getX(), gridVector.getY());
        }

    }

}
