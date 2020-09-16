package com.plantpocalypse.model.items;

import com.plantpocalypse.util.reader.NpcReader;

import java.io.Serializable;
import java.util.HashMap;

public class NPC implements Serializable {
    private static final Object NPC = new NpcReader();
    private String name;
    private HashMap<Object, String> description = new HashMap<Object, String>();;
    private Object NpcReader;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NPC(String name) {
        this.name = name;

    }

//    public String respond(){
//        System.out.println(getName() + " says to." + NpcReader.readNPCX);
//        return respond();
//
//    }


}

