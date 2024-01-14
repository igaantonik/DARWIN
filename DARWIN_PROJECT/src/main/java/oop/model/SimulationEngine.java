package oop.model;

import oop.Simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    List<Simulation> simulations;
    List<Thread> simulationsThreads;
    ExecutorService executorService;

    public SimulationEngine(List<Simulation> simulations){
        this.simulations = simulations;
        this.simulationsThreads = new ArrayList<>();
        this.executorService = Executors.newFixedThreadPool(4);
    }

    public void runSync(){
        for(Simulation simulation: this.simulations){
            simulation.run();
        }
    }

    public void awaitSimulationsEnd(){
        for(Thread thread: simulationsThreads){
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void runAsync(){
        for(Simulation simulation: this.simulations){
            simulationsThreads.add(new Thread(simulation));
        }
        for(Thread thread: simulationsThreads){
            thread.start();
        }
//        this.awaitSimulationsEnd();
    }

    public void runAsyncInThreadPool(){

        for(Simulation simulation: this.simulations){
            executorService.submit(new Thread(simulation));
        }
        executorService.shutdown();
        this.awaitSimulationsEnd();
    }


}
