package com.world.CountryCapitals.controller;

import com.world.CountryCapitals.entity.Country;
import com.world.CountryCapitals.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping //either at class or method level
public class CountryController
{

@Autowired //DI //object creation -- CountryService countryService=new CountryService();
    CountryService countryService;


    @GetMapping("/getCountries")
    public ResponseEntity<List<Country>> getCountries()
    {
   try{
       List<Country> countries=countryService.getAllCountries();
       return new ResponseEntity<List<Country>>(countries,HttpStatus.FOUND) ;
   }
   catch (Exception e)
   {
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
    }

    @GetMapping("/getCountries/{id}")
    public ResponseEntity<Country> getCountries(@PathVariable(value="id") int id)
    {
          try {
             Country country= countryService.getCountryById(id);
             //return country; //can return status code,datatype,headers,along with response entity class
              return new ResponseEntity<Country>(country,HttpStatus.FOUND);
          }
       catch(Exception e)
       {
         return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/getCountries/countryName")  //not able to hit api request
    public ResponseEntity<Country> getCountryByName(@RequestParam(value="name") String countryName) {

        try {
            Country country = countryService.getCountryByName(countryName);
            return new ResponseEntity<Country>(country, HttpStatus.FOUND);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/addCountry")
    public ResponseEntity<Country> addCountry(@RequestBody Country country)
    {
     try {
        country = countryService.addCountry(country);
        return new ResponseEntity<Country>(country,HttpStatus.CREATED);
     }
     catch (NoSuchElementException  e) {
         return new ResponseEntity<>(HttpStatus.CONFLICT);
     }
    }

    @PutMapping("/updateCountry/{id}")
    public ResponseEntity<Country> updateCountry(@PathVariable(value="id") int id, @RequestBody Country country)
    {
        try {
            Country existingCountry=countryService.getCountryById(id);
            existingCountry.setCountryName(country.getCountryName());
            existingCountry.setCountryCapital(country.getCountryCapital());

           Country updatedCountry=countryService.updateCountry(existingCountry);
           return new ResponseEntity<Country>(updatedCountry,HttpStatus.OK);
        }
        catch(Exception e)
        {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping(path="deleteCountry/{id}")
    public ResponseEntity<Country> deleteCountry(@PathVariable(value="id") int id)
    {
        Country country=null;
         try {
        country=countryService.getCountryById(id);
        countryService.deleteCountry(id);
         }
        catch (NoSuchElementException e)
        {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
         return new ResponseEntity<Country>(country,HttpStatus.OK);
    }


}
