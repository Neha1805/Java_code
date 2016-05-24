/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.ticketservice;

import com.walmart.levdetail.*;
import com.walmart.ticketservice.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.FutureTask;
import java.lang.Object;
/**
 *
 * @author Neha
 * This class provides the implementation for TicketService.java class
 */
public class TicketServiceActual implements TicketService {
    /**
* The number of seats in the requested level that are neither held nor reserved
*
* @param venueLevel a numeric venue level identifier to limit the search
* @return the number of tickets available on the provided level
*/

    long time = 60000;
    int sum = 0;
    @Override
    public int numSeatsAvailable(Optional<Integer> venueLevel)
     //public List<Level> numSeatsAvailable(Optional<Integer> venueLevel)
     {
         List<Level> le = new ArrayList<Level>();
         if(venueLevel.isPresent())
         {
             Optional<Level> lvl = TicketBase.getlevels().values().stream().filter(s ->s.getLevel_ID()==venueLevel.get()).findFirst();
             if(!lvl.isPresent()|| lvl == null)  //To check if the value is present or not
             {
                 throw new NullPointerException("Null Values in container");
                 //System.out.println("Requested level is not present");
             }
             else
             {
                 le.add(lvl.get()); //Fetching the value from container object and adding it to list
                               
             }
         }
         else
         {
             //System.out.println("Requested level is not present,Give proper Level number");
             le.addAll(TicketBase.getlevels().values()); //appending all values in list
             
              Integer [] y = le.toArray(new Integer[le.size()]);
             
                 for(int i =0;i<y.length;i++)
                 {
                    sum = sum+(y[i]);
   
                  }
           }
         
     return sum;
     }

   
    @Override
    public SeatHold findAndHoldSeats(int numSeats, Optional<Integer> minLevel, Optional<Integer> maxLevel, String customerEmail) {
        
        int min_level = 1;
        int max_level = 4;
        
        //calculate available_seats
        int available_seats = TicketBase.getlevels().values().stream().mapToInt(s -> s.getAvailableSeats()).sum();
        
        //if numSeats are less then zero throw exception
        if(numSeats <= 0 )
        {
            throw new UnsupportedOperationException("There are no available seats--> findAndHoldSeats()");
        }
        //if numSeats are more then available_seats throw exception
        if(numSeats > available_seats) 
        {
			throw new UnsupportedOperationException("Total seats available"+available_seats);
	}
        
        if(minLevel.isPresent())
        {
            min_level = minLevel.get();
        }
        
        if(maxLevel.isPresent())
        {
            max_level = maxLevel.get();
        }
        
        HashMap<Integer,Integer> book_info = search_seats(numSeats,min_level,max_level);
        SeatHold sh = seat_info(book_info,customerEmail);
        TicketBase.addSeatHolds(sh);
        checktime(sh,time);  
         return sh;
    }

    @Override
    public String reserveSeats(int seatHoldId, String customerEmail)throws NullPointerException {
        SeatHold sh = TicketBase.getseat().get(seatHoldId);
      
            if(sh == null)
        {
            throw new NullPointerException("Null seats->reserveSeats()");
            //System.out.println("Invalid details,So seats cannot be reserved");
        }
        if(sh.getStatus().equals(Status.TEMP_HELD))
        {
            sh.setStatus(Status.BOOKED);
        }
        else if(sh.getStatus().equals(Status.EXPIRED))
        {
            System.out.println("Time Expired,Try Again");
        }
        else
        {
            System.out.println("Try another seat");
        }
        
        
        return String.valueOf(TicketBase.getreserve_conf());
        
         
    }
    
    //This methods tries to search best seats 
    public HashMap<Integer,Integer> search_seats(int numSeats,int min_level,int max_level)
    {
        HashMap<Integer,Integer> ss = new HashMap<Integer,Integer>();
        while(max_level >= min_level && (numSeats!=0))
        {
            Level lv = TicketBase.getlevels().get(max_level);
            int avseat = lv.getAvailableSeats();
            if(avseat >= numSeats) //number of seats available are greater than seats searching for booking
            {
                int diff = avseat - numSeats;
                lv.setAvailableSeats(diff);
                ss.put(lv.getLevel_ID(),numSeats);
                numSeats = 0;
            }
            else
            {
                    ss.put(lv.getLevel_ID(),avseat);
                    numSeats = numSeats - avseat;
            }
            max_level--;
        }
       
        return ss;
    }
    //This method sends the detail about seat to the customer through email
    public SeatHold seat_info(HashMap<Integer,Integer> map1,String customerEmail)
    {
        int price=0;
        HashMap<String,Integer> seatdet = new HashMap<String,Integer>();
        for(HashMap.Entry<Integer, Integer> entry : map1.entrySet())
		{
			price += TicketBase.getlevels().get(entry.getKey()).getPrice()*entry.getValue();
			seatdet.put(TicketBase.getlevels().get(entry.getKey()).getLevel_Name(), entry.getValue());
		}
		Customer cust = new Customer(TicketBase.getcustid(),customerEmail);

		SeatHold sh = new SeatHold(TicketBase.getseat_Id(),Status.TEMP_HELD, cust, 
				System.currentTimeMillis() + 60000, seatdet, + price);
		return sh;
    }
    //This methods keeps the track of seat on hold once the timer expires
    private void checktime(SeatHold sh, long time) {

		FutureTask<String> task = new FutureTask<>(new Timeout(sh.get_holdseat_id(), time));
		EI.getInstance().executor.execute(task);
	}
    
}
