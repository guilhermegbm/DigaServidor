/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.diga.servidor.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 * @author Guilherme
 */
public class GsonBuilderUtil {

    public static Gson constroiGsonPadrao() {
        return new Gson();
    }

    public static Gson constroiGsonComData(String dateFormat) {
        return new GsonBuilder().setDateFormat(dateFormat).create();
    }
}
