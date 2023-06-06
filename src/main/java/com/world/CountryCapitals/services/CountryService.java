package com.world.CountryCapitals.services;

import com.world.CountryCapitals.controller.AddResponse;
import com.world.CountryCapitals.entity.Country;
import com.world.CountryCapitals.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Service
public class CountryService {
     @Autowired
     CountryRepository countryRepository;
    public List<Country> getAllCountries()
    {
  return  countryRepository.findAll(); // returns list of country object
    }

    public Country getCountryById(int id)  //returns all details of the country based on ID
    {
    return countryRepository.findById(id).get();
    }

    public Country getCountryByName(String countryName)
    {
List<Country> countries=countryRepository.findAll(); //Captured all data from table
        Country country=null;
        for (Country c:countries) //iterating over each rows
        {
if(c.getCountryName().equalsIgnoreCase(countryName))
    country=c;
        }
        return country;
    }

    public Country addCountry(Country country)
            //passing country and capital name, id will automatically be generated.
    {
    country.setId(getMaxId());
    countryRepository.save(country);
    return  country;
    }

    //Utility method to get max id
    public int getMaxId() //edited
    {
      return countryRepository.findAll().size()+1;
    }

    public Country updateCountry(Country country)
    {
       countryRepository.save(country);
       return country;
    }


    public AddResponse deleteCountry(int id)   // Give the ID, corresponding country data is deleted
    {
  countryRepository.deleteById(id);
  AddResponse addResponse=new AddResponse();
  addResponse.setMsg("ID"+id+"data deleted");
  addResponse.setId(id);
  return addResponse;

    }


}
