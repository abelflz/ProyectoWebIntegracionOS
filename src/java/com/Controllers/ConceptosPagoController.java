package com.Controllers;

import com.Model.ConceptoPagos;
import com.Model.Conexion;
import com.Model.ValidarConceptosPago;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ConceptosPagoController {

    @SuppressWarnings("FieldMayBeFinal")
    private ValidarConceptosPago validar;
    @SuppressWarnings("FieldMayBeFinal")
    private JdbcTemplate jdbc;

    public ConceptosPagoController() {
        this.validar = new ValidarConceptosPago();
        Conexion con = new Conexion();
        this.jdbc = new JdbcTemplate(con.conexion());
    }

    @RequestMapping("ConceptosPagoIndex.com")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from Concepto_Pago";
        List datos = this.jdbc.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("ConceptosPago/Index");
        return mav;
    }

    @RequestMapping(value = "ConceptosPagoCreate.com", method = RequestMethod.GET)
    public ModelAndView Create() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("ConceptosPago/Create");
        mav.addObject("conceptopagos", new ConceptoPagos());
        return mav;
    }

    @RequestMapping(value = "ConceptosPagoCreate.com", method = RequestMethod.POST)
    public ModelAndView Create(
            @ModelAttribute("conceptopagos") ConceptoPagos cp,
            BindingResult result,
            SessionStatus status
    ) {
        this.validar.validate(cp, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("ConceptosPago/Create");
            mav.addObject("conceptopagos", new ConceptoPagos());
            return mav;
        } else {
            this.jdbc.update("insert into Concepto_Pago(Descripcion, Estado) values (?,?)", cp.getDescripcion(), cp.getEstado());
            return new ModelAndView("redirect:/ConceptosPagoIndex.com");
        }
    }

    @RequestMapping("ConceptosPagoDelete.com")
    public ModelAndView Delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            this.jdbc.update("delete from Concepto_Pago where Id = " + id + "");
            return new ModelAndView("redirect:/ConceptosPagoIndex.com");
        } catch (DataIntegrityViolationException e) {
            return new ModelAndView("redirect:/ConceptosPagoIndex.com", "message", "message");
        }
    }

    @RequestMapping(value = "ConceptosPagoEdit.com", method = RequestMethod.GET)
    public ModelAndView Edit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        ConceptoPagos datos = this.selectUsuario(id);
        mav.setViewName("ConceptosPago/Update");
        mav.addObject("conceptopagos", new ConceptoPagos(id, datos.getDescripcion(), datos.getEstado()));
        return mav;
    }

    @RequestMapping(value = "ConceptosPagoEdit.com", method = RequestMethod.POST)
    public ModelAndView Edit(
            @ModelAttribute("conceptopagos") ConceptoPagos cp,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {
        this.validar.validate(cp, result);

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("id"));
            ConceptoPagos datos = this.selectUsuario(id);
            mav.setViewName("ConceptosPago/Update");
            mav.addObject("conceptopagos", new ConceptoPagos(id, datos.getDescripcion(), datos.getEstado()));
            return mav;
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            this.jdbc.update("update Concepto_Pago set Descripcion = ?, Estado = ? where id = ? ", cp.getDescripcion(), cp.getEstado(), id);
            return new ModelAndView("redirect:/ConceptosPagoIndex.com");
        }
    }

    @SuppressWarnings("Convert2Lambda")
    public ConceptoPagos selectUsuario(int id) {
        final ConceptoPagos pago = new ConceptoPagos();
        String quer = "SELECT * FROM Concepto_Pago WHERE id='" + id + "'";
        return (ConceptoPagos) jdbc.query(
                quer, new ResultSetExtractor<ConceptoPagos>() {
            @SuppressWarnings("override")
            public ConceptoPagos extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    pago.setDescripcion(rs.getString("descripcion"));
                    pago.setEstado(rs.getString("estado"));
                }
                return pago;
            }

        }
        );
    }

    @ModelAttribute("ListaEstados")
    public Map<String, String> listadoEstados() {
        Map<String, String> estado = new LinkedHashMap<>();
        estado.put("Activo", "Activo");
        estado.put("Inactivo", "Inactivo");
        return estado;
    }
}
