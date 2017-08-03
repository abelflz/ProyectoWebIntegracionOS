package com.Controllers;

import com.Model.Proveedores;
import com.Model.Conexion;
import com.Model.ValidarProveedores;
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
public class ProveedoresController {

    final ValidarProveedores validar;
    final JdbcTemplate jdbc;

    public ProveedoresController() {
        this.validar = new ValidarProveedores();
        Conexion con = new Conexion();
        this.jdbc = new JdbcTemplate(con.conexion());
    }

    @RequestMapping("ProveedoresIndex.com")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        String sql = "select * from Proveedores";
        List datos = this.jdbc.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("Proveedores/Index");
        return mav;
    }

    @RequestMapping(value = "ProveedoresCreate.com", method = RequestMethod.GET)
    public ModelAndView Create() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("Proveedores/Create");
        mav.addObject("proveedores", new Proveedores());
        return mav;
    }

    @RequestMapping(value = "ProveedoresCreate.com", method = RequestMethod.POST)
    public ModelAndView Create(
            @ModelAttribute("proveedores") Proveedores p,
            BindingResult result,
            SessionStatus status
    ) {
        this.validar.validate(p, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("Proveedores/Create");
            mav.addObject("Proveedores", new Proveedores());
            return mav;
        } else {
            this.jdbc.update("insert into Proveedores(Nombre, TipoPersona, cedulaRNC, Balance, CuentaContable, Estado) values (?, ?, ?, ?, ?, ?)",
                    p.getNombre(), p.getTipoPersona(), p.getCedulaRNC(), p.getBalance(), p.getCuentaContable(), p.getEstado());
            return new ModelAndView("redirect:/ProveedoresIndex.com");
        }
    }

    @RequestMapping("ProveedoresDelete.com")
    public ModelAndView Delete(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            this.jdbc.update("delete from Proveedores where Id = " + id + "");
            mav.setViewName("redirect:/ProveedoresIndex.com");
        } catch (DataIntegrityViolationException e) {         
            return new ModelAndView("redirect:/ProveedoresIndex.com", "message", "message");
        }
        return mav;
    }

    @RequestMapping(value = "ProveedoresEdit.com", method = RequestMethod.GET)
    public ModelAndView Edit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        Proveedores datos = this.selectProveedor(id);
        mav.setViewName("Proveedores/Update");
        mav.addObject("proveedores",
                new Proveedores(
                        id, datos.getNombre(), datos.getTipoPersona(), datos.getCedulaRNC(), datos.getBalance(), datos.getCuentaContable(), datos.getEstado()
                )
        );
        return mav;
    }

    @RequestMapping(value = "ProveedoresEdit.com", method = RequestMethod.POST)
    public ModelAndView Edit(
            @ModelAttribute("proveedores") Proveedores p,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {
        this.validar.validate(p, result);

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("id"));
            Proveedores datos = this.selectProveedor(id);
            mav.setViewName("Proveedores/Update");
            mav.addObject("proveedores",
                    new Proveedores(
                            id, datos.getNombre(), datos.getTipoPersona(), datos.getCedulaRNC(), datos.getBalance(), datos.getCuentaContable(), datos.getEstado()
                    )
            );
            return mav;
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            this.jdbc.update("update Proveedores set Nombre = ?, TipoPersona = ?, cedulaRNC = ?, Balance = ?, CuentaContable = ?, Estado = ? where id = ? ",
                    p.getNombre(), p.getTipoPersona(), p.getCedulaRNC(), p.getBalance(), p.getCuentaContable(), p.getEstado(), id);
            return new ModelAndView("redirect:/ProveedoresIndex.com");
        }
    }

    public Proveedores selectProveedor(int id) {
        final Proveedores proveedor = new Proveedores();
        String quer = "SELECT * FROM Proveedores WHERE id='" + id + "'";
        return (Proveedores) jdbc.query(
                quer, new ResultSetExtractor<Proveedores>() {
            public Proveedores extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    proveedor.setNombre(rs.getString("Nombre"));
                    proveedor.setTipoPersona(rs.getString("TipoPersona"));
                    proveedor.setCedulaRNC(rs.getString("CedulaRNC"));
                    proveedor.setBalance(rs.getString("Balance"));
                    proveedor.setCuentaContable(rs.getString("CuentaContable"));
                    proveedor.setEstado(rs.getString("Estado"));
                }
                return proveedor;
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

    @ModelAttribute("ListaTipoPersonas")
    public Map<String, String> listadotipopersonas() {
        Map<String, String> tipo = new LinkedHashMap<>();
        tipo.put("Jurídico", "Jurídico");
        tipo.put("Físico", "Físico");
        return tipo;
    }
}
