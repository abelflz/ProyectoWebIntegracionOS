package com.Controllers;

import com.Model.Conexion;
import com.Model.RegSolCheques;
import com.Model.ValidarRSC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Controller;

@Controller
public class RegistroSolicitudChequesController {

    private ValidarRSC validar;
    private JdbcTemplate jdbc;

    public RegistroSolicitudChequesController() {
        this.validar = new ValidarRSC();
        Conexion con = new Conexion();
        this.jdbc = new JdbcTemplate(con.conexion());
    }

    @RequestMapping("RegistroSolicitudChequesIndex.com")
    public ModelAndView Index() {
        ModelAndView mav = new ModelAndView();
        String sql = "select c.Id, p.Nombre, c.Monto, c.FechaRegistro, c.Estado, c.CuentaContableProveedor,\n"
                + "c.CuentaContableRelCCBanco, cp.Descripcion as ConceptoPago \n"
                + "from RegSolCheque c \n"
                + "inner join proveedores p \n"
                + "on c.ProveedorId = p.Id\n"
                + "inner join concepto_pago cp\n"
                + "on cp.Id = c.ConceptoPagoId";
        List datos = this.jdbc.queryForList(sql);
        mav.addObject("datos", datos);
        mav.setViewName("RegistroSolicitudCheques/Index");
        return mav;
    }

    @RequestMapping("RegistroSolicitudChequesDelete.com")
    public ModelAndView Delete(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        this.jdbc.update("delete from RegSolCheque where id = " + id + "");
        return new ModelAndView("redirect:/RegistroSolicitudChequesIndex.com");
    }

    @RequestMapping(value = "RegistroSolicitudChequesCreate.com", method = RequestMethod.GET)
    public ModelAndView Create() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("RegistroSolicitudCheques/Create");
        mav.addObject("registro", new RegSolCheques());
        return mav;
    }

    @RequestMapping(value = "RegistroSolicitudChequesCreate.com", method = RequestMethod.POST)
    public ModelAndView Create(
            @ModelAttribute("registro") RegSolCheques rsc,
            BindingResult result,
            SessionStatus status
    ) {
        this.validar.validate(rsc, result);
        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            mav.setViewName("RegistroSolicitudCheques/Create");
            mav.addObject("registro", new RegSolCheques());
            return mav;
        } else {
            int proveedorId = Integer.parseInt(rsc.getProveedorId());
            this.jdbc.update("INSERT INTO RegSolCheque(ProveedorId, Monto, FechaRegistro, Estado, CuentaContableProveedor, \n" +
                "CuentaContableRelCCBanco, ConceptoPagoId) values(?, ?, ?, ?," +
                "(SELECT CuentaContable FROM proveedores WHERE ID = '"+proveedorId+"'),?, ?)", rsc.getProveedorId(), rsc.getMonto(),
                rsc.getFechaRegistro(), rsc.getEstado(), rsc.getCuentaContableRelCCBanco(), rsc.getConceptoId()
            );
            return new ModelAndView("redirect:/RegistroSolicitudChequesIndex.com");
        }
    }

    @RequestMapping(value = "RegistroSolicitudChequesEdit.com", method = RequestMethod.GET)
    public ModelAndView Edit(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        int id = Integer.parseInt(request.getParameter("id"));
        RegSolCheques datos = this.selectRegistro(id);
        mav.setViewName("RegistroSolicitudCheques/Update");
        mav.addObject("registro",
                new RegSolCheques(
                        id, datos.getProveedorId(), datos.getConceptoId(), datos.getMonto(), datos.getFechaRegistro(), datos.getEstado(), datos.getCuentaContableProveedor(),
                        datos.getCuentaContableRelCCBanco()
                )
        );
        return mav;
    }

    @RequestMapping(value = "RegistroSolicitudChequesEdit.com", method = RequestMethod.POST)
    public ModelAndView Edit(
            @ModelAttribute("registro") RegSolCheques rsc,
            BindingResult result,
            SessionStatus status,
            HttpServletRequest request
    ) {
        this.validar.validate(rsc, result);

        if (result.hasErrors()) {
            ModelAndView mav = new ModelAndView();
            int id = Integer.parseInt(request.getParameter("id"));
            RegSolCheques datos = this.selectRegistro(id);
            mav.setViewName("RegistroSolicitudCheques/Update");
            mav.addObject("registro",
                    new RegSolCheques(
                            id, datos.getProveedorId(), datos.getConceptoId(), datos.getMonto(), datos.getFechaRegistro(), datos.getEstado(), datos.getCuentaContableProveedor(),
                            datos.getCuentaContableRelCCBanco()
                    )
            );
            return mav;
        } else {
            int id = Integer.parseInt(request.getParameter("id"));
            int proveedorId = Integer.parseInt(rsc.getProveedorId());
            this.jdbc.update("update RegSolCheque set ProveedorId = ?, Monto = ?, FechaRegistro = ?, Estado = ?, CuentaContableProveedor = (SELECT CuentaContable FROM proveedores WHERE ID = "+proveedorId+"), CuentaContableRelCCBanco = ?,"
                + " ConceptoPagoId=? where id = ? ",
                rsc.getProveedorId(), rsc.getMonto(), rsc.getFechaRegistro(), rsc.getEstado(), rsc.getCuentaContableRelCCBanco(), rsc.getConceptoId(), id);
            return new ModelAndView("redirect:/RegistroSolicitudChequesIndex.com");
        }
    }

    //List proveedores = this.jdbc.queryForList(sql);
    @ModelAttribute("ListaEstados")
    public Map<String, String> listadoEstados() {
        Map<String, String> estado = new LinkedHashMap<>();
        estado.put("Pendiente", "Pendiente");
        estado.put("Anulado", "Anulado");
        estado.put("Cheque Generado", "Cheque Generado");
        return estado;
    }

    @ModelAttribute("ListaProveedores")
    public Map<String, String> listadoProveedores() {
        Map<String, String> proveedor = new LinkedHashMap<>();
        try {
            String query = "select Id, Nombre from proveedores";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cheques", "root", "admin");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                proveedor.put(rs.getString("Id"), rs.getString("Nombre"));
            }
        } catch (Exception e) {

        }
        return proveedor;
    }

    @ModelAttribute("ListaConceptosPagos")
    public Map<String, String> listadoConceptosDePago() {
        Map<String, String> conceptos = new LinkedHashMap<>();
        try {
            String query = "select Id, Descripcion from Concepto_Pago where Estado = 'Activo'";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cheques", "root", "admin");
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()) {
                conceptos.put(rs.getString("Id"), rs.getString("Descripcion"));
            }
        } catch (Exception e) {

        }
        return conceptos;
    }

    public RegSolCheques selectRegistro(int id) {
        final RegSolCheques registro = new RegSolCheques();
        String quer = "SELECT * FROM RegSolCheque WHERE id='" + id + "'";
        return (RegSolCheques) jdbc.query(
                quer, new ResultSetExtractor<RegSolCheques>() {
            public RegSolCheques extractData(ResultSet rs) throws SQLException, DataAccessException {
                if (rs.next()) {
                    registro.setProveedorId(rs.getString("ProveedorId"));
                    registro.setMonto(rs.getString("Monto"));
                    registro.setFechaRegistro(rs.getString("FechaRegistro"));
                    registro.setEstado(rs.getString("Estado"));
                    registro.setCuentaContableProveedor(rs.getString("CuentaContableProveedor"));
                    registro.setCuentaContableRelCCBanco(rs.getString("CuentaContableRelCCBanco"));
                    registro.setConceptoId(rs.getString("ConceptoPagoId"));
                }
                return registro;
            }
        }
        );
    }
}
