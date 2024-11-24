package com.example.todobackend.mappers;

public class PriorityMapper {

    public static String intPriorityToStringPriority(int i){
        String res;
        switch (i){
            case 1->res="не важно";
            case 2->res="важно";
            case 3-> res="очень важно";
            default -> res="";
        }
        return res;
    }

    public static int stringPriorityToIntPriority(String s){
        Integer res;
        switch (s){
            case "не важно"->res=1;
            case "важно"->res=2;
            case "очень важно"-> res=3;
            default -> res=0;
        }
        return res;
    }
}
