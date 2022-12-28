package com.example.practicedummy.temp;

import java.util.ArrayList;
import java.util.List;

public class FacadeConverter implements Converter<Object>{

    private final Converter converter;

    public FacadeConverter(String type) {
        if(type == null){
            this.converter = new StringConverter();
        }
        else{
            this.converter = new ByteConverter();
        }
    }

    @Override
    public Object convert(String data) {
        return converter.convert(data);
    }
}
