package com.example.practicedummy.temp;

public class Client {

    private String type = "?";

    public void businessLogic(Record record){
        var converter = new FacadeConverter(type);
        var k = converter.convert(record.getKey());
        var v = converter.convert(record.getValue());
    }
}
