/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controllers;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.Model.Conexion;
import com.Model.Cuentas;
import com.Model.fecha;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@ControllerAdvice
public class IndexController {

    final JdbcTemplate jdbc;

    public IndexController() {
        Conexion con = new Conexion();
        this.jdbc = new JdbcTemplate(con.conexion());
    }

    String validate = "get";

    @RequestMapping(value = "index.com", method = RequestMethod.GET)
    public ModelAndView Index() {
        fecha f = new fecha();
        ModelAndView mav = new ModelAndView();
        if (validate.equals("get")) {
            jdbc.update("update Cuentas set Monto = (select CASE WHEN sum(Monto) IS NULL THEN 0 ELSE sum(Monto) END from regsolcheque)");
            String sql = "select * from Cuentas";
            List Cuentas = jdbc.queryForList(sql);
            mav.addObject("Cuentas", Cuentas);
            mav.addObject("fecha", f);
            mav.setViewName("Index/Index");
            return mav;
        } else {
            String sql = "select * from Cuentas";
            List Cuentas = jdbc.queryForList(sql);
            mav.addObject("Cuentas", Cuentas);
            mav.addObject("fecha", f);
            mav.setViewName("Index/Index");
            return mav;
        }
    }

    @RequestMapping(value = "index.com", method = RequestMethod.POST)
    public ModelAndView Index(
            @ModelAttribute("fecha") fecha f
    ) {
        try {
            String sql = "update Cuentas set Monto = (select CASE WHEN sum(Monto) IS NULL THEN 0 ELSE sum(Monto) END from regsolcheque where FechaRegistro between '" + f.getFechainicio() + "' and '" + f.getFechafin() + "')";
            jdbc.update(sql);
            validate = "post";
            return new ModelAndView("redirect:/index.com");
        } catch (Exception e) {
            return new ModelAndView("redirect:/index.com");
        }
    }

    @RequestMapping(value = "Integrando.com", method = RequestMethod.GET)
    public ModelAndView Integrar(
            @ModelAttribute("Cuentas") Cuentas c
    ) {
        //Seleccionar todo lo de la tabla Cuentas
        //Guardarlo en variables
        //pasar variables al servicio web
        //Referencia al Servicio Web
        return new ModelAndView("redirect/:index.com");
    }
}
