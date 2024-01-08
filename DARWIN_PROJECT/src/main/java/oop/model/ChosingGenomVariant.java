package oop.model;

public class ChosingGenomVariant {

    public static Genom createGenom(AnimalParameters parameters){
        switch (parameters.getGenomWariant()){
            case 0 -> {
                return new GenomFullPredestination(parameters);
            }
            case 1 -> {
                return new GenomBackAndForth(parameters);
            }
            default -> throw new IllegalArgumentException();
        }
    }

    public static Genom createChildGenom(AnimalParameters parameters, Animal animal1, Animal animal2){
        switch (parameters.getGenomWariant()){
            case 0 -> {
                return new GenomFullPredestination(animal1, animal2, parameters);
            }
            case 1 -> {
                return new GenomBackAndForth(animal1, animal2, parameters);
            }
            default -> throw new IllegalArgumentException();
        }
    }
}
