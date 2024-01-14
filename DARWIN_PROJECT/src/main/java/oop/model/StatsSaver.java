package oop.model;

import oop.presenter.SimulationPresenter;

import java.io.*;
import java.util.List;


public class StatsSaver implements  MapChangeListener{
    private SimulationPresenter simulationPresenter;
    private Statistics statistics;
    private File csvFile;
    private FileWriter fileWriter;


    public StatsSaver(SimulationPresenter simulationPresenter){
        this.csvFile = new File("./Statystyki.csv");
        this.simulationPresenter = simulationPresenter;
    }

    public void setStatistics(){
        this.statistics = this.simulationPresenter.getStats() ;
    }


    @Override
    public void mapChanged(WorldMap worldMap, String message){
        setStatistics();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.csvFile, true))) {
            StringBuilder rowBuilder = new StringBuilder();
            for(String value: this.statistics.getAllStats()) {
                rowBuilder.append(value);
                rowBuilder.append(",");
            }
            writer.write(rowBuilder.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
