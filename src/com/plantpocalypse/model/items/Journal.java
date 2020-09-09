package com.plantpocalypse.model.items;

public class Journal extends Item{

    public Journal(String name, String description) {
        super(name);
        setDescription(description);
    }

    @Override
    public void use(){
        System.out.println("Used " + getName());
    }

}
