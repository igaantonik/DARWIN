package oop.presenter;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    @FXML
    private GridPane mapGrid;
    @FXML
    public HBox animalColors;


    public void setWorldmap(WorldMap worldmap) {
        this.worldmap = (AbstractWorldMap) worldmap;
    }

    private Vector2d vectorOnGrid(Vector2d vector2d){
        int y = this.height -1 - vector2d.getY() + boundary.lower_left().getY();
        int x = vector2d.getX()  - boundary.lower_left().getX();
        return new Vector2d(x,y);
    }

    public void drawColorBox(){
        for (double i=0; i <= animalParameters.getAnimalStartEnergy(); i+= (double) animalParameters.getAnimalStartEnergy() /20) {
            Rectangle colorRect = new Rectangle(10, 10);
            colorRect.setFill( new Color((double) i / (double) animalParameters.getAnimalStartEnergy(),0.3, 0.3, 0.5));
            animalColors.getChildren().add(colorRect);
        }
    }
    public void drawMap(){
        this.clearGrid();

        this.boundary = worldmap.getCurrentBounds();
        this.height = worldmap.getHeight();
        this.width = worldmap.getWidth();
        mapGrid.minHeight(this.height);
        mapGrid.minWidth(this.width);

        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));

        for(int i = 1; i <= this.width; i++){
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        }
        for(int j = 1; j <= this.height; j++){
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
            return(new Color((double) ((Animal) element).getEnergyLevel() /animalParameters.getAnimalStartEnergy(), 0.3, 0.3,0.5));
        } else if (element instanceof Plant){
            return(new Color(0.2, 1, 0.3, 0.5));
        } else{
            return(new Color(0, 0.2, 1, 0.6));
        }
    }



    @Override
    public void mapChanged(WorldMap worldmap, String message) {
        this.setWorldmap(worldmap);
        Platform.runLater(this::drawMap);
    }

    public void onSimulationStartClicked(){
        try{
            Simulation simulation = new Simulation(worldmap, animalParameters, worldParameters);
            drawColorBox();
            simulation.run();
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
//            List<Vector2d> positions = List.of(new Vector2d(1,1), new Vector2d(5,5));
//            Simulation simulation = new Simulation();
//            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsyncInThreadPool();
        } catch(IllegalArgumentException ignored){
            System.out.println(ignored.getMessage());
            System.exit(0);
        }


    }

    public void setParameters(WorldParameters worldParameters, AnimalParameters animalParameters) {
        this.animalParameters = animalParameters;
        this.worldParameters = worldParameters;
    }

    public void resumeSimnulation(ActionEvent actionEvent) {

    }
}
