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
import com.walmart.levdetail.*;
import com.walmart.ticketservice.TicketBase;
import java.util.HashMap;
import java.util.concurrent.Callable;

public class Timeout implements Callable<String>{
    
    private int hold_seat;
    private long time;  //used for waiting time
    
    public Timeout(int hold_seat, long time) {
		super();
		this.hold_seat = hold_seat;
		this.time = time;
	}
    
  //Computes a result, or throws an exception if unable to do so.
    //Its used for --> each ticket hold should expire within a set number of seconds
    @Override
   public String call() throws Exception {
           
   
       Thread.sleep(time);
       HashMap<String,Integer> seatdet = null;
       if(TicketBase.getseat().get(hold_seat).getStatus().equals(Status.TEMP_HELD))
       {
           synchronized(TicketBase.getseat())
           {
               seatdet = TicketBase.getseat().get(hold_seat).getinfo();
               
               seatdet.forEach((key,value) -> {
		Level level = TicketBase.getlevels().values().stream().filter(s ->s.getLevel_Name()
                        .equals(key)).findFirst().get();					

                //It is used for releasing all the seats which were put on hold
			level.setAvailableSeats(level.getAvailableSeats() + value);

				});
               //Status is expired here
               TicketBase.getseat().get(hold_seat).setStatus(Status.EXPIRED);
               
               
           }
       }
       return Thread.currentThread().getName();
   }
    
}
