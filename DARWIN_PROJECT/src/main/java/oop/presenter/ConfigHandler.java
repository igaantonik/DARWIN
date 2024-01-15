package oop.presenter;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;

import java.io.*;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class ConfigHandler {
    private final Properties properties;


    public ConfigHandler() {
        this.properties = new Properties();
        loadProperties();
    }

    private void loadProperties() {
        URL configFilePath = getClass().getClassLoader().getResource("config.properties");
        try (InputStream input = new FileInputStream(URLDecoder.decode(configFilePath.getFile(), StandardCharsets.UTF_8))){
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void saveSimulationConfig(
            Spinner<Integer> heightSpinner, Spinner<Integer> widthSpinner,
            Spinner<Integer> animalsNumSpinner, Spinner<Integer> plantNumSpinner,
            Spinner<Integer> dailyPlantsNumSpinner, ComboBox<String> mapVariantBox,
            CheckBox fileSavingCheckbox, Spinner<Integer> basinsSpinner,
            Spinner<Integer> animalStartEnergySpinner, Spinner<Integer> animalReproduceEnergySpinner,
            Spinner<Integer> animalLostToReproduceEnergySpinner, Spinner<Integer> eatEnergySpinner,
            Spinner<Integer> genomLengthSpinner, Spinner<Integer> minMutationSpinner,
            Spinner<Integer> maxMutationSpinner, ComboBox<String> genomVariantBox) {

        properties.setProperty("fileSaving", String.valueOf(fileSavingCheckbox.selectedProperty()));
        properties.setProperty("height", String.valueOf(heightSpinner.getValue()));
        properties.setProperty("width", String.valueOf(widthSpinner.getValue()));
        properties.setProperty("animalsNum", String.valueOf(animalsNumSpinner.getValue()));
        properties.setProperty("plantNum", String.valueOf(plantNumSpinner.getValue()));
        properties.setProperty("dailyPlantsNum", String.valueOf(dailyPlantsNumSpinner.getValue()));
        properties.setProperty("mapVariant", String.valueOf(mapVariantBox.getValue()));
        properties.setProperty("animalStartEnergy", String.valueOf(animalStartEnergySpinner.getValue()));
        properties.setProperty("animalReproduceEnergy", String.valueOf(animalReproduceEnergySpinner.getValue()));
        properties.setProperty("animalLostToReproduceEnergy", String.valueOf(animalLostToReproduceEnergySpinner.getValue()));
        properties.setProperty("eatEnergy", String.valueOf(eatEnergySpinner.getValue()));
        properties.setProperty("genomLength", String.valueOf(genomLengthSpinner.getValue()));
        properties.setProperty("minMutation", String.valueOf(minMutationSpinner.getValue()));
        properties.setProperty("maxMutation", String.valueOf(maxMutationSpinner.getValue()));
        properties.setProperty("minMutation", String.valueOf(minMutationSpinner.getValue()));
        properties.setProperty("basins", String.valueOf(basinsSpinner.getValue()));
        properties.setProperty("genomVariant", String.valueOf(genomVariantBox.getValue()));
        properties.setProperty("fileSaving", String.valueOf(fileSavingCheckbox.isSelected()));


        URL configFilePath = getClass().getClassLoader().getResource("config.properties");

        try (OutputStream output = new FileOutputStream(URLDecoder.decode(configFilePath.getFile(), StandardCharsets.UTF_8))) {
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadSimulationConfig(
            Spinner<Integer> heightSpinner, Spinner<Integer> widthSpinner,
            Spinner<Integer> animalsNumSpinner, Spinner<Integer> plantNumSpinner,
            Spinner<Integer> dailyPlantsNumSpinner, ComboBox<String> mapVariantBox,
            CheckBox fileSavingCheckbox, Spinner<Integer> basinsSpinner,
            Spinner<Integer> animalStartEnergySpinner, Spinner<Integer> animalReproduceEnergySpinner,
            Spinner<Integer> animalLostToReproduceEnergySpinner, Spinner<Integer> eatEnergySpinner,
            Spinner<Integer> genomLengthSpinner, Spinner<Integer> minMutationSpinner,
            Spinner<Integer> maxMutationSpinner, ComboBox<String> genomVariantBox) {

        loadProperties();

        heightSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("height")));
        widthSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("width")));
        animalsNumSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("animalsNum")));
        plantNumSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("plantNum")));
        dailyPlantsNumSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("dailyPlantsNum")));
        //basinsSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("basins")));
        animalStartEnergySpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("animalStartEnergy")));
        animalReproduceEnergySpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("animalReproduceEnergy")));
        animalLostToReproduceEnergySpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("animalLostToReproduceEnergy")));
        eatEnergySpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("eatEnergy")));
        genomLengthSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("genomLength")));
        minMutationSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("minMutation")));
        maxMutationSpinner.getValueFactory().setValue(Integer.parseInt(properties.getProperty("maxMutation")));

        String mapVariant = properties.getProperty("mapVariant");
        if (mapVariant != null) {
            int selectedIndex = mapVariantBox.getItems().indexOf(mapVariant);
            if (selectedIndex != -1) {
                mapVariantBox.getSelectionModel().select(selectedIndex);
            }
        }

        String genomVariant = properties.getProperty("genomVariant");
        if (genomVariant != null) {
            int selectedIndex = genomVariantBox.getItems().indexOf(genomVariant);
            if (selectedIndex != -1) {
                genomVariantBox.getSelectionModel().select(selectedIndex);
            }
        }

        fileSavingCheckbox.setSelected(Boolean.parseBoolean(properties.getProperty("fileSaving")));

    }
}
