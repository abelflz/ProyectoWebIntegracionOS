package com.Controllers;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.Model.Conexion;
import com.Model.fecha;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMethod;

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
            jdbc.update("update Cuentas set Monto = (select CASE WHEN sum(Monto) IS NULL THEN 0 ELSE sum(Monto) END from RegSolCheque)");
            String sql = "select * from Cuentas";
            List Cuentas = jdbc.queryForList(sql);
            mav.addObject("Cuentas", Cuentas);
            mav.addObject("fecha", f);
            mav.addObject("id", "");
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
            String sql;
            if (f.getFechainicio().equals("") || f.getFechafin().equals("")) {
                sql = "update Cuentas set Monto = (select CASE WHEN sum(Monto) IS NULL THEN 0 ELSE sum(Monto) END from RegSolCheque)";
                validate = "get";
            } else {
                sql = "update Cuentas set Monto = (select CASE WHEN sum(Monto) IS NULL THEN 0 ELSE sum(Monto) END from RegSolCheque where FechaRegistro between '" + f.getFechainicio() + "' and '" + f.getFechafin() + "')";
                validate = "post";
            }

            jdbc.update(sql);

            return new ModelAndView("redirect:/index.com");
        } catch (Exception e) {
            return new ModelAndView("redirect:/index.com");
        }
    }
}
