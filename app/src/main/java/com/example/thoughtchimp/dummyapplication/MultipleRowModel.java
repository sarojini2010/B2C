package com.example.thoughtchimp.dummyapplication;

public class MultipleRowModel{

    public int type;
    public String[] activation,validfrom;

    public MultipleRowModel(){ 
    }

    public MultipleRowModel(int type, String[] activation,String[] validfrom){
        this.type = type;
        this.activation = activation;
        this.validfrom=validfrom;
    }

}
