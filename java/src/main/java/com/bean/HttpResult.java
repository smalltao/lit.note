package com.bean;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;

@Data
public class HttpResult {

    @Setter
    @Getter
    private String code, msg;

    @Setter
    @Getter
    private ArrayList<Object> listResult;

    @Setter
    @Getter
    private HashMap<Object, Object> mapResult;
}
