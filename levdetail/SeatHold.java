/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walmart.levdetail;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
//import java.applet.Applet;
/**
 *
 * @author Neha
 * This class contains methods about seat on hold,customer,purchase value etc.
 */
@XmlRootElement
public class SeatHold {
    private int seat_id;
    private Status seat_stats;
    private Customer cust;
    private HashMap<String,Integer> booking_info;
    private int purchase_val;
    private List<ref> links = new ArrayList<>();
    
    public SeatHold(int seat_id, Status seat_stats, Customer cust,long holdTime,HashMap<String, Integer> booking_info, int purchase_val)
    {
        super();
        this.seat_id = seat_id;
        this.seat_stats = seat_stats;
        this.cust = cust;
        this.setInfo(booking_info); 
        this.purchase_val = purchase_val;
    }
    public int get_holdseat_id()
    {
        return seat_id;
    }
    public void set_holdseat_id(int seat_id)
    {
        this.seat_id = seat_id;
    }
    public Status getStatus() {
		return seat_stats;
    }
    public void setStatus(Status seat_stats) {
		this.seat_stats = seat_stats;
    }
    @XmlTransient
    public Customer getCust()
    {
        return cust;
    }
    public void setCust(Customer cust)
    {
        this.cust = cust;
    }
    //Getting information of booked seats
    public HashMap<String,Integer> getinfo() 
    {
		return booking_info;
    }
    //Setting information of booked seats
    public void setInfo(HashMap<String,Integer> booking_info) {
		this.booking_info = booking_info;
	}
    public int getvalue()
    {
        return purchase_val;
    }
    public void setvalue(int purchase_val)
    {
        this.purchase_val = purchase_val;
    }
}
