/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.datpt.waterrecord.servlet;

import com.datpt.waterrecord.Dao.AreaDAO;
import com.datpt.waterrecord.Dao.CustomerDAO;
import com.datpt.waterrecord.DaoItf.AreaInterface;
import com.datpt.waterrecord.DaoItf.CustomerInterface;
import com.datpt.waterrecord.controller.StaffController;
import com.datpt.waterrecord.model.AreaModel;
import com.datpt.waterrecord.model.CustomerModel;
import com.datpt.waterrecord.model.StaffModel;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;

/**
 *
 * @author HuyGL
 */
@WebServlet(name = "AreaServlet", urlPatterns = {"/AreaServlet"})
public class AreaServlet extends HttpServlet {

    StaffController staffController = new StaffController();
    AreaInterface area = new AreaDAO();
    Gson gson = new Gson();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String index, String listArea)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            if (index != null) {
                out.print("{\"" + index + "\":");
                out.print(listArea);
                out.print("}");
            } else {
                out.print(listArea);
            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        
        String ham = request.getParameter("ham");

        switch (ham) {
            case "DangKyThanhVien":
                String hoTen = request.getParameter("tenNhanVien");
                String soDienThoai = request.getParameter("soDienThoai");
                String diaChi = request.getParameter("diaChi");
                String taiKhoan = request.getParameter("taiKhoan");
                String matKhau = request.getParameter("matKhau");

                Boolean check = staffController.InsertStaffController(hoTen, soDienThoai, diaChi, taiKhoan, matKhau);
                processRequest(request, response, "ketqua", check.toString());
                break;
            case "LayDanhSackTram":
                List<AreaModel> listAllArea = area.getAllArea();
                processRequest(request, response, "Area", gson.toJson(listAllArea).toString());
                break;
            case "KiemTraDangNhap":
                String result;
                taiKhoan = request.getParameter("taiKhoan");
                matKhau = request.getParameter("matKhau");
                StaffModel staffModel = staffController.CheckLogin(taiKhoan, matKhau);
                if (staffModel != null) {
                    result = "{\"ketqua\" : true, \"maNV\" : " + staffModel.getMaNhanVien() + ",\"tenNV\" : \"" + staffModel.getTenNhanVien() + "\"}";
                } else {
                    result = "{ketqua : false}";
                }
                processRequest(request, response, null, result);
                break;
            case "LayDanhSachKhachHang":
                CustomerInterface customers = new CustomerDAO();
                List<CustomerModel> listCustomer = customers.getListCustomer();
                processRequest(request, response, "Customer", gson.toJson(listCustomer).toString());
                break;
            case "LayKhachHangTheoTram":
                CustomerInterface customerArea = new CustomerDAO();
                int maTram = Integer.parseInt(request.getParameter("maTram"));
                List<CustomerModel> listCustomerArea = customerArea.getListCustomerArea(maTram);
                processRequest(request, response,"Customer",gson.toJson(listCustomerArea).toString());
                break;
            case "LayKhachHang":
                CustomerInterface customer = new CustomerDAO();
                int maKhachHang = Integer.parseInt(request.getParameter("maKhachHang"));
                CustomerModel customerModel = customer.getCustomer(maKhachHang);
                processRequest(request, response,"Customer",gson.toJson(customerModel).toString());
                break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");

        String ham = request.getParameter("ham");

        switch (ham) {
            case "DangKyThanhVien":
                String hoTen = request.getParameter("tenNhanVien");
                String soDienThoai = request.getParameter("soDienThoai");
                String diaChi = request.getParameter("diaChi");
                String taiKhoan = request.getParameter("taiKhoan");
                String matKhau = request.getParameter("matKhau");

                Boolean check = staffController.InsertStaffController(hoTen, soDienThoai, diaChi, taiKhoan, matKhau);
                processRequest(request, response, "ketqua", check.toString());
                break;
            case "LayDanhSackTram":
                List<AreaModel> listAllArea = area.getAllArea();
                processRequest(request, response, "Area", gson.toJson(listAllArea).toString());
                break;
            case "KiemTraDangNhap":
                String result;
                taiKhoan = request.getParameter("taiKhoan");
                matKhau = request.getParameter("matKhau");
                StaffModel staffModel = staffController.CheckLogin(taiKhoan, matKhau);
                if (staffModel != null) {
                    result = "{\"ketqua\" : true, \"maNV\" : " + staffModel.getMaNhanVien() + ",\"tenNV\" : \"" + staffModel.getTenNhanVien() + "\"}";
                } else {
                    result = "{ketqua : false}";
                }
                processRequest(request, response, null, result);
                break;
            case "LayDanhSachKhachHang":
                CustomerInterface customers = new CustomerDAO();
                List<CustomerModel> listCustomer = customers.getListCustomer();
                processRequest(request, response, "Customer", gson.toJson(listCustomer).toString());
                break;
            case "LayKhachHangTheoTram":
                CustomerInterface customerArea = new CustomerDAO();
                int maTram = Integer.parseInt(request.getParameter("maTram"));
                List<CustomerModel> listCustomerArea = customerArea.getListCustomerArea(maTram);
                processRequest(request, response,"Customer",gson.toJson(listCustomerArea).toString());
                break;
            case "LayKhachHang":
                CustomerInterface customer = new CustomerDAO();
                int maKhachHang = Integer.parseInt(request.getParameter("maKhachHang"));
                CustomerModel customerModel = customer.getCustomer(maKhachHang);
                processRequest(request, response,"Customer",gson.toJson(customerModel).toString());
                break;
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
