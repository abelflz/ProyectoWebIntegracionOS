package com.Model;

import org.springframework.jdbc.datasource.DriverManagerDataSource;


public class Conexion {
    public DriverManagerDataSource conexion(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://35.202.177.191:3306/cheques");
        dataSource.setUsername("abelflz");
        dataSource.setPassword("alterna255");
        return dataSource;
    }
}
