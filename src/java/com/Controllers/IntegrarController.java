/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controllers;

import com.Model.Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author abelf
 */
@ControllerAdvice
public class IntegrarController {

    final JdbcTemplate jdbc;

    public IntegrarController() {
        Conexion con = new Conexion();
        this.jdbc = new JdbcTemplate(con.conexion());
    }

    @RequestMapping(value = "Integrando.com")
    public ModelAndView Integrar() {
        try {
            String monto;
            String query = "select * from Cuentas";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://35.202.177.191:3306/cheques", "abelflz", "alterna255");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            rs.next();
            monto = rs.getString("Monto");

            String endpoint = "http://accountingintegration.azurewebsites.net/api/accountingentry";
            String json = "{"
                    + "	\"description\": \"Asiento de Cheques correspondiente al periodo 2017-08\",\"auxiliary\": {\"id\": 9},\"transactions\": [\n"
                    + "		{\"accountingAccount\": {\"id\": 83},\"origin\": \"CREDIT\",\"amount\": " + monto + "},\n"
                    + "		{\"accountingAccount\": {\"id\": 82},\"origin\": \"DEBIT\",\"amount\": " + monto + "}\n"
                    + "	]\n"
                    + "}";
            RestTemplate rest;
            HttpHeaders headers;

            rest = new RestTemplate();
            headers = new HttpHeaders();
            headers.add("Content-Type", "application/json");
            headers.add("Accept", "*/*");

            @SuppressWarnings("Convert2Diamond")
            HttpEntity<String> requestEntity = new HttpEntity<String>(json, headers);
            ResponseEntity<String> responseEntity = rest.exchange(endpoint, HttpMethod.POST, requestEntity, String.class);
            responseEntity.getStatusCode();
            String id = responseEntity.getBody();
            return new ModelAndView("redirect:/index.com", "a", "Se creó el asiento número: " + id);
        } catch (Exception e) {
            return new ModelAndView("redirect:/index.com", "a", "No se pudo crear el asiento");
        }
    }
}
