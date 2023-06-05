package com.world.CountryCapitals.services;

import com.world.CountryCapitals.controller.AddResponse;
import com.world.CountryCapitals.entity.Country;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class CountryService {

    static HashMap<Integer, Country> countryIdMap;    //Values in a hashmap can have multiple data


    public CountryService()  //hardcoding data (without database)
    {
   countryIdMap = new HashMap<Integer, Country>();


   //Obj created to add new countries,we are passing data. So constructor need to be called in Country Class
        Country india=new Country(1,"India","Delhi");
        Country usa=new Country(2,"USA","Washington");
        Country uk=new Country(3,"UK","London");


        countryIdMap.put(1,india);
        countryIdMap.put(2,usa);
        countryIdMap.put(3,uk);
    }

    public List getAllCountries()
    {
        List countries=new ArrayList(countryIdMap.values());
        return countries;
    }

    public Country getCountryById(int id)  //returns all details of the country based on ID
    {
       Country country= countryIdMap.get(id);
       return  country;
    }

    public Country getCountryByName(String countryName)
           /* cannot get name directly, since inside obj.
            using the id , get object,read name from that obj.
            if that name is equal to our parameter , print the details*/
    {
        Country country=null;
        for (int i :countryIdMap.keySet())
        {
            if(countryIdMap.get(i).getCountryName().equals(countryName));
            country=countryIdMap.get(i);
        }
        return country;
    }

    public Country addCountry(Country country)
            //passing country and capital nsme, id will automatically be generated.
    {
        country.setId(getMaxId());
        countryIdMap.put(country.getId(), country);
         return country;
    }

    //Utility method to get max id
    public int getMaxId() //edited
    {
        int max=0;
        for(int i:countryIdMap.keySet())

            if(max<i)
                max=i;
        return max+1;
    }

    public Country updateCountry(Country country)
    {
        if(country.getId()>0)
            countryIdMap.put(country.getId(),country);
        return country;
    }


    public AddResponse deleteCountry(int id)   // Give the ID, corresponding country data is deleted
    {
countryIdMap.remove(id);
        AddResponse addResponse=new AddResponse();
        addResponse.setMsg("Country data Deleted");
        addResponse.setId(id);
        return  addResponse;
    }


}
