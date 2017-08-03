/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Controllers;

import com.Model.login;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LogInController {

    @RequestMapping("LogIn.com")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("LogIn/Index");
        mav.addObject("login", new login());
        return mav;
    }

    @RequestMapping(value = "LogIn.com", method = RequestMethod.POST)
    @SuppressWarnings("UseSpecificCatch")
    public ModelAndView Index(
        @ModelAttribute("login") login l,
        SessionStatus status
    ) {
        try {
            String usuario = l.getUsuario();
            String clave = l.getClave();
            String query = "SELECT count(usuario) usuario FROM Login WHERE usuario collate utf8_bin = '"+usuario+"' AND contrasena collate utf8_bin = '"+clave+"'";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cheques", "root", "admin");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            rs.next();
            String hola = rs.getString("usuario");
            if (hola.equals("0")) {
                return new ModelAndView("redirect:/LogIn.com", "message", "message");
            } else { 
                return new ModelAndView("redirect:/index.com");
            }
        } catch (Exception e) {
            ModelAndView mav = new ModelAndView();
            return new ModelAndView("redirect:/LogIn.com");
        }
    }
}
