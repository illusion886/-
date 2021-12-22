package 数据库实验;

import java.sql.*;

public class mysqlop {
      public static void main(String args[])
      {
            String driver="com.mysql.jdbc.Driver";
            String user="root";
            String password="123456";
            String url="jdbc:mysql://localhost:3306/test"+ "?verifyServerCertificate=false"

                + "&useSSL=false"

                + "&requireSSL=false";
            try{
                  //加载数据库驱动
                  Class.forName(driver);
                  //连接数据库
                  Connection conn= DriverManager.getConnection(url,user,password);
                  System.out.println("连接成功!");

                  Statement state=conn.createStatement();
                  ResultSet re=state.executeQuery("select *FROM testmy");
                  while(re.next()){
                        System.out.print(re.getInt("id")+" ");
                        System.out.print(re.getString("pname")+" ");
                        System.out.print(re.getString("brand")+" ");
                        System.out.print(re.getDouble("price")+" ");
                        System.out.println(re.getInt("stock"));
                  }
                  //关闭数据库
                  conn.close();
            }catch(ClassNotFoundException e){
                  e.printStackTrace();
            }catch(SQLException e) {
                  e.printStackTrace();
            }
      }
}
