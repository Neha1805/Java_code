/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice;
import java.util.*;
import com.walmart.levdetail.*;
import java.util.concurrent.atomic.AtomicInteger;
/**
 *
 * @author Neha
 */
public class TicketBase {
    
    private static HashMap<Integer,Level>levels = new HashMap(); //Map for level
    private static HashMap<Integer,SeatHold>seat_hold = new HashMap(); //Map for SeatHold
    //
    private static AtomicInteger seat_Id = new AtomicInteger(6250);
    private static AtomicInteger cust_Id = new AtomicInteger(6250);
    private static AtomicInteger reserve = new AtomicInteger(100);
    
    //Levels and their details defined
    static
    {
    Level l1 = new Level(1,"Orchestra",100,25,50,25*50);
    Level l2 = new Level(2,"Main",75,20,100,20*100);
    Level l3 = new Level(3,"Balcony 1",50, 15, 100, 15*100);
    Level l4 = new Level(4,"Balcony 2",40, 15, 100, 15*100);
    
    levels.put(1,l1);
    levels.put(2,l2);
    levels.put(3,l3);
    levels.put(4,l4);
    
    }
    //get the levels from map
    public static HashMap<Integer,Level> getlevels()
    {
        return levels;
    }
    //get all seat # which are put on hold
    public static HashMap<Integer,SeatHold> getseat()
    {
        return seat_hold;
    }
    //get customer id
    public static int getcustid()
    {
        return cust_Id.incrementAndGet();  //incrementAndGet() adds +1 to the value
    }
     //get seat id
    public static int getseat_Id() {
	      
        return seat_Id.incrementAndGet(); //incrementAndGet() adds +1 to the value
    }
     //get a confirmation for reserve seat number
    public static int getreserve_conf() {
		
        return reserve.incrementAndGet(); //incrementAndGet() adds +1 to the value
    }

    //keeps on adding the number of seats which are put on hold
    public static void addSeatHolds(SeatHold seatHold) {
		
            seat_hold.put(seat_Id.get(), seatHold);
    }
}
