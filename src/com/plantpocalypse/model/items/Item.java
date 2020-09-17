package com.plantpocalypse.model.items;

import java.io.Serializable;

public abstract class Item implements Serializable {
    private String name;
    private String Description;
    private String back;

    /* CONSTRUCTORS */
    public Item() {}
    public Item(String name){
        setName(name);
        setDescription("This is a " + name);
    }

    public Item(String name, String back){
        setName(name);
        setDescription("This is a " + name);
        setBack(back);
    }

    /* ABSTRACT METHODS */
    public abstract void use();

    /* GETTERS AND SETTERS */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

//    @Override
//    public String toString() {
//        return "Item{" +
//                "Description='I am an item'" + '\'' +
//                '}';
//    }
}

