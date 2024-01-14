package oop.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class StatsSaver implements  MapChangeListener{
    private Statistics statistics;
    private File csvFile;
    private FileWriter fileWriter;


    public StatsSaver(Statistics statistics){
        this.csvFile = new File("/DriveName/Users/UserName/Desktop/Statystyki.csv");
        this.statistics = statistics;
        try {
            this.fileWriter = new FileWriter(this.csvFile);
        } catch (IOException e) {

        }
    }
    @Override
    public void mapChanged(WorldMap worldMap, String message){
        for (String data : statistics.getAllStats()) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < data.length(); i++) {
                line.append("\"");
                line.append(data.substring(i,i+1).replaceAll("\"","\"\""));
                line.append("\"");
                if (i != data.length() - 1) {
                    line.append(',');
                }
            }
            line.append("\n");
            try {
                fileWriter.write(line.toString());
            } catch (IOException e) {

            }
        }
    }

    public void closeFile(){
        try {
            this.fileWriter.close();
        } catch (IOException e) {
        }
    }
}
