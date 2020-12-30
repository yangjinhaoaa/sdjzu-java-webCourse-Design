package com.example.demo2;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "carleave", value = "/carleave")
public class Carleave extends HttpServlet {
    private String message;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/car_data";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "191109yjh";

    public void init() {
        message = "车辆离开🚙💨💨💨";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //data
        Connection conn = null;
        Statement stmt = null;
        int updateflag;
        Date date = new Date();
        String status1=null;
        String intime1=null;

        response.setContentType("text/html");
        response.setContentType("text/html;charset=UTF-8");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>" + "已经离开，车牌号" + request.getParameter("id") + "</h2>");

        try {
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if (!conn.isClosed()) {
                System.out.println("数据库连接成功！");

            } else {
                out.println("<h2>数据库连接失败</h2>");
            }


            // 执行 SQL 查询
            stmt = conn.createStatement();
            String sql,sqlleave;
            String car_id=request.getParameter("id");

            sql = "SELECT car_id,car_color,car_owner,car_brand,status,intime,outtime FROM fixed_car WHERE car_id = '"+car_id+"'";

            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                //int id  = rs.getInt("id");
                String car_idshow = rs.getString("car_id");
                String car_color = rs.getString("car_color");
                String car_owner = rs.getString("car_owner");
                String car_brand = rs.getString("car_brand");
                String status = rs.getString("status");
                String intime = rs.getString("intime");
                intime1=intime;
                status1 =status;


                // 输出数据
                out.println("<div style=\"color:#0000FF\">"+car_owner+"的");
                out.println(car_color);
                out.println(car_brand);
                out.println("</div>");

            }

            if(status1.equals("临时")){
                out.println("<h1>入场时间："+intime1+"</h1>");

                //时间
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
                String dateTime = df.format(date); // Formats a Date into a date/time string.
                System.out.println(dateTime);  // 2019-07-03 10:14:14

                out.println("<h1>出场时间："+dateTime+"</h1>");
                sqlleave = "UPDATE fixed_car SET outtime= '"+dateTime+"'"+" WHERE car_id = '"+car_id+"'";
                updateflag=stmt.executeUpdate(sqlleave);
                if(updateflag>0){
                    out.println("<h1>临时车辆离场成功</h1>");
                }else{
                    out.println("<h1>离场失败</h1>");
                }


            }if(status1.equals("固定")){
                out.println("<h2>固定车辆，👋再见 "+car_id+"</h2>");
            }if(status1.equals("黑名单")){
                out.println("<h1>黑名单车辆，怎么进来的？ "+car_id+"</h1>");
            }else if(status1==null){
                out.println("<h2>没有该车！联系yjh解决下 "+car_id+"</h2>");
            }

            // 展开结果集数据库
           /* while (rs.next()) {
                // 通过字段检索
                //int id  = rs.getInt("id");
                String car_id = rs.getString("car_id");
                String car_color = rs.getString("car_color");
                String car_owner = rs.getString("car_owner");
                String car_brand = rs.getString("car_brand");

                // 输出数据
                out.println("car ID: " + car_id);
                out.println(" 车颜色:  " + car_color);
                out.println(" 车所有者:  " + car_owner);
                out.println(" 什么车： " + car_brand);
                out.println("<br />");
            }*/
            //out.println("</body></html>");

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
            out.println("<h3>jdbc错误</h3>");
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
            out.println("<h3>没有该车数据或者，信息错误，联系开发者☎️</h3>");
        } finally {
            // 最后是用于关闭资源的块
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
                out.println("<h3>最后错误</h3>");
            }
        }
        out.println("</body></html>");
    }
    public void destroy() {
    }


}

