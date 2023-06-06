package com.world.CountryCapitals.repository;

import com.world.CountryCapitals.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Integer>
    //Integer--Datatype Of primary key column
{
}
