package com.example.thoughtchimp.dummyapplication;

public class MultipleRowModel{

    public int type;
    public String[] activation,validfrom,childname;

    public MultipleRowModel(){ 
    }

    public MultipleRowModel(int type, String[] activation,String[] validfrom){
        this.type = type;
        this.activation = activation;
        this.validfrom=validfrom;
        this.childname=childname;

    }
//    public MultipleRowModel(int type, String[] activation,String[] validfrom,String[] childname){
//        this.type = type;
//        this.activation = activation;
//        this.validfrom=validfrom;
//        this.childname=childname;
//
//    }

}
