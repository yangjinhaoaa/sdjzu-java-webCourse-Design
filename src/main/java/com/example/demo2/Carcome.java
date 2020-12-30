package com.example.demo2;


import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
@WebServlet(name = "carcome", value = "/carcome")
public class Carcome extends HttpServlet{
    private String message;
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/car_data";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "191109yjh";

    public void init() {
        message = "车辆入场🚙➡️➡️➡️";
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
        //时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String dateTime = df.format(date); // Formats a Date into a date/time string.
        System.out.println(dateTime);  // 2019-07-03 10:14:14

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("<h2>" + "车牌号" + request.getParameter("id") + "</h2>");

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
            String sql,addsql,sqlin;
            String car_id=request.getParameter("id");

            sql = "SELECT car_id,car_color,car_owner,car_brand,status,intime,outtime FROM fixed_car WHERE car_id = '"+car_id+"'";

            ResultSet rs = stmt.executeQuery(sql);

            //out.println("<h1>ss"+rs.next()+"</h1>");
            if(rs.isBeforeFirst()==true){
                out.println("<h4>数据库中有该车辆数据</h4>");
                while(rs.next()){
                    // 通过字段检索
                    //int id  = rs.getInt("id");
                    String car_idshow = rs.getString("car_id");
                    String car_color = rs.getString("car_color");
                    String car_owner = rs.getString("car_owner");
                    String car_brand = rs.getString("car_brand");
                    String status = rs.getString("status");
                    String intime = rs.getString("intime");
                    //intime1=intime;
                    status1 =status;

                    // 输出数据
                    out.println("<div style=\"color:#0000FF\">"+car_owner+"的");
                    out.println(car_color);
                    out.println(car_brand);
                    out.println("</div>");

                }
                if(status1.equals("临时")){
                    out.println("<h1>入场时间："+dateTime+"</h1>");

                    //out.println("<h1>出场时间："+dateTime+"</h1>");
                    sqlin = "UPDATE fixed_car SET intime= '"+dateTime+"'"+" WHERE car_id = '"+car_id+"'";
                    updateflag=stmt.executeUpdate(sqlin);
                    if(updateflag>0){
                        out.println("<h1>临时车辆入场成功</h1>");
                    }else{
                        out.println("<h1>入场失败</h1>");
                    }

                }if(status1.equals("固定")){
                    out.println("<h2>固定车辆，欢迎入场👏 "+car_id+"</h2>");
                }if(status1.equals("黑名单")){
                    out.println("<h1><font color=\"red\">黑名单车辆，禁止入场！"+car_id+"</font></h1>");
                }else if(status1==null){
                    out.println("<h2>没有该车！联系yjh解决下 "+car_id+"</h2>");
                }


            }else {
                addsql="INSERT INTO fixed_car(car_id,car_color,car_owner,car_brand,status,intime,outtime,statusint) VALUES ('"+car_id+"','未知色','不知道谁','未知车','临时','"+dateTime+"','2020-11-11 11:11:11','1')";
                updateflag=stmt.executeUpdate(addsql);
                if(updateflag>0){
                    out.println("<h2>由于数据库里没有该车辆数据，所以已经为您以临时车辆到身份添加到数据库中</h2>");
                    out.println("<h2>入场时间："+dateTime+"</h2>");
                    out.println("<h2>临时车辆，欢迎入场👏 "+car_id+"</h2>");

                }else{
                    out.println("<h2>添加数据失败，请联系yjh调试数据库</h2>");
                }


            }

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
            out.println("<h3>没有该车数据。或者，信息错误，联系开发者☎️</h3>");
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
