package com.example.demo2;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.*;

@WebServlet(name = "carblack", value = "/carblack")
public class Carblack extends HttpServlet{
    private String message;


    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/car_data";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "191109yjh";


    public void init() {
        message = "拉黑车辆!🌚🌚🌚";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //data
        Connection conn = null;
        Statement stmt = null;
        int updateflag;

        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>"+"你拉黑的的车辆是"+request.getParameter("id")+"</h2>");


        try{
            // 注册 JDBC 驱动器
            Class.forName("com.mysql.jdbc.Driver");

            // 打开一个连接
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            if(!conn.isClosed()){
                System.out.println("数据库连接成功！");
            }else{
                out.println("<h2>数据库连接失败</h2>");
            }


            stmt = conn.createStatement();
            String sql,sqlblack;
            String car_id=request.getParameter("id");
            sqlblack = "UPDATE fixed_car SET status = '黑名单' WHERE car_id = '"+car_id+"'";
            sql = "SELECT car_id,car_color,car_owner,car_brand,status FROM fixed_car WHERE status = '黑名单'";


            updateflag=stmt.executeUpdate(sqlblack);
            ResultSet rs = stmt.executeQuery(sql);


            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                //int id  = rs.getInt("id");
                String car_idshow = rs.getString("car_id");
                String car_color = rs.getString("car_color");
                String car_owner = rs.getString("car_owner");
                String car_brand = rs.getString("car_brand");
                String status = rs.getString("status");

                // 输出数据
                out.println("车牌号: " + car_idshow);
                out.println(" 车颜色:  " + car_color);
                out.println(" 车所有者:  "+car_owner);
                out.println(" 品牌： "+car_brand);
                out.println(" 状态： "+status);
                out.println("<br />");

            }
            if(updateflag>0){
                out.println("<h1>拉黑成功</h1>");
            }else{
                out.println("<h1>拉黑失败</h1>");
            }

            //out.println("</body></html>");

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch(SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            out.println("<h3>jdbc错误</h3>");
        } catch(Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            out.println("<h3>class.forname错误</h3>");
        }finally{
            // 最后是用于关闭资源的块
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
                out.println("<h3>最后错误</h3>");
            }
        }
        out.println("</body></html>");
    }
    public void destroy() {
        System.out.println("car blacklist destory");
    }
}
