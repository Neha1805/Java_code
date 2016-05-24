/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.levdetail;

/**
 *
 * @author Neha
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class EI {
    private static EI instance = null;
    public ExecutorService executor = Executors.newFixedThreadPool(5);
    
    private EI(){}
    
    public static EI getInstance(){
        if(instance == null) instance = new EI();
        return instance;
    }
}
