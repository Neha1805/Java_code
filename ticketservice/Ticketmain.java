/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice;
import com.walmart.levdetail.*;
import com.walmart.ticketservice.*;
import java.util.*;
import java.lang.Integer;
/**
 *
 * @author Neha
 */
public class Ticketmain {
    public static void main(String [] s)
    {
        TicketServiceActual ta = new TicketServiceActual();
        //Integer venue = 1;
        System.out.println(ta.search_seats(2, 1, 3));
        //System.out.println(ta.reserveSeats(1, "hh"));
        //ta.findAndHoldSeats(2,1, 3, "neha@gmail.com");
        
    }
}
