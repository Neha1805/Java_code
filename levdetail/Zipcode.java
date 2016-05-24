/*package com.walmart.levdetail;

//This java class is the resource we have configured to accept RESTful requests
//import .*;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.GET;

@Path("zipcode")
public class Zipcode {
	
	
	@GET
	@Path("{zip}")
	public String getaddress(@PathParam("zip") String zipc){
		
		if(zipc.equals("22312"))
		{
			return("Alexandria:VA");
		
		}
		else if(zipc.equals("22030"))
		{
			return("Fairfax:VA");
			
		}
		else if(zipc.equals("22031"))
		{
			return("Tysons Corner:MD");
			
		}
		else if(zipc.equals("20148"))
		{
			return("Ashburn:VA");
		}
		else
		{
			return("Invalid Zipcode:Invalid Zipcode");
			
		}
		
		
	}
	

}*/
