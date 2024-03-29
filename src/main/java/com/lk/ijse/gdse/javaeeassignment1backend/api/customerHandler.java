package com.lk.ijse.gdse.javaeeassignment1backend.api;

import com.lk.ijse.gdse.javaeeassignment1backend.api.util.Convertor;
import com.lk.ijse.gdse.javaeeassignment1backend.dao.DaoFactory;
import com.lk.ijse.gdse.javaeeassignment1backend.dao.DaoType;
import com.lk.ijse.gdse.javaeeassignment1backend.dao.custom.CustomerDAO;
import com.lk.ijse.gdse.javaeeassignment1backend.dao.exception.ConstrainViolationException;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.CustomerDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.ItemDTO;
import com.lk.ijse.gdse.javaeeassignment1backend.dto.RespondsDTO;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "customer", urlPatterns = "/customer")

public class customerHandler extends HttpServlet {
    private Connection connection;
    private CustomerDAO customerDAO;
    private Convertor convertor;

    @Override
    public void init() throws ServletException {

        try {

            InitialContext initialContext = new InitialContext();
            DataSource pool = (DataSource) initialContext.lookup("java:comp/env/jdbc/Web_Pos");
            this.connection = pool.getConnection();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        this.customerDAO = DaoFactory.getInstance().getDao(DaoType.CUSTOMER, connection);
        this.convertor = new Convertor();
    }

    private boolean handleValidation(CustomerDTO customerDTO) {

        if (customerDTO.getId() == null || !customerDTO.getId().matches("^(C)([0-9]{2,})$"))
            throw new ConstrainViolationException("Invalid customer id !");

        if (customerDTO.getName() == null || !customerDTO.getName().matches("^[A-za-z]{2,}$"))
            throw new ConstrainViolationException("Invalid customer name !");

        if (customerDTO.getAddress() == null || !customerDTO.getAddress().matches("^[A-za-z]{2,}$"))
            throw new ConstrainViolationException("Invalid customer address !");

        if (customerDTO.getContact() == null || !customerDTO.getContact().matches("^(075|077|071|074|078|076|070|072)([0-9]{7})$"))
            throw new ConstrainViolationException("Invalid customer contact !");

        return true;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        resp.setContentType("application/json");

        Jsonb jsonb = JsonbBuilder.create();

        try {

            List<CustomerDTO> list = customerDAO.getAll().stream().map(customer -> convertor.fromCustomer(customer)).
                    collect(Collectors.toList());

            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            jsonb.toJson(new RespondsDTO(200, "Done", list), resp.getWriter());

        }catch (SQLException e){

            jsonb.toJson(new RespondsDTO(400, "Error", e.getLocalizedMessage()), resp.getWriter());
            e.printStackTrace();
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        resp.setContentType("application/json");

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        System.out.println(customerDTO);

        try {

            if (handleValidation(customerDTO)) {

                if (customerDAO.save(convertor.toCustomer(customerDTO)) != null) {

                    jsonb.toJson(new RespondsDTO(200, "Successfully added !", ""), resp.getWriter());
                }
            }
        } catch (SQLException | ConstrainViolationException e) {
            jsonb.toJson(new RespondsDTO(400, "Error !", e.getLocalizedMessage()), resp.getWriter());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        resp.setContentType("application/json");

        Jsonb jsonb = JsonbBuilder.create();
        CustomerDTO customerDTO = jsonb.fromJson(req.getReader(), CustomerDTO.class);

        try {

            if (handleValidation(customerDTO)) {

                if (customerDAO.update(convertor.toCustomer(customerDTO)) != null) {

                    jsonb.toJson(new RespondsDTO(200, "Successfully update !", ""), resp.getWriter());
                }
            }
        } catch (SQLException | ConstrainViolationException e) {
            jsonb.toJson(new RespondsDTO(400, "Error !", e.getLocalizedMessage()), resp.getWriter());
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getContentType() == null || !req.getContentType().toLowerCase().startsWith("application/json")) {
            resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }

        resp.setContentType("application/json");

        Jsonb jsonb = JsonbBuilder.create();
        String customerID = req.getParameter("customerID");

        try {

            if (customerID == null || !customerID.matches("^(C)([0-9]{2,})$"))
                throw new ConstrainViolationException("Invalid customer id !");

            customerDAO.delete(customerID) ;

            jsonb.toJson(new RespondsDTO(200, "Successfully deleted !", ""), resp.getWriter());


        } catch (SQLException | ConstrainViolationException e) {
            jsonb.toJson(new RespondsDTO(400, "Error !", e.getLocalizedMessage()), resp.getWriter());
            e.printStackTrace();
        }
    }
}
