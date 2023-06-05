package com.world.CountryCapitals.controller;

import com.world.CountryCapitals.entity.Country;
import com.world.CountryCapitals.services.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping //either at class or method level
public class CountryController
{

@Autowired //DI //object creation -- CountryService countryService=new CountryService();
    CountryService countryService;


    @GetMapping("/getCountries")
    public List getCountries()
    {
        return countryService.getAllCountries();
    }
    @GetMapping("/getCountries/{id}")
    public Country getCountries(@PathVariable(value="id") int id)
    {
        return countryService.getCountryById(id);
    }

    @GetMapping("/getCountries/countryName")  //not able to hit api request
    public Country getCountryByName(@RequestParam(value="name") String countryName)
    {
        return getCountryByName(countryName);
    }

@PostMapping("/addCountry")
    public Country addCountry(@RequestBody Country country)
{
return countryService.addCountry(country);
}

    @PutMapping("/updateCountry")
    public Country updateCountry(@RequestBody Country country)
    {
        return countryService.updateCountry(country);
    }

    @DeleteMapping("deleteCountry/{id}")
    public AddResponse deleteCountry(@PathVariable(value="id") int id)
    {
     return  countryService.deleteCountry(id);
    }


}
