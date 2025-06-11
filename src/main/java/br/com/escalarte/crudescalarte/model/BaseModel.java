package br.com.escalarte.crudescalarte.model;

import java.io.Serializable;

public class BaseModel implements Serializable {
    private int id;

    public BaseModel(int id) {
        this.id = id;
    }

    public BaseModel(){
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
