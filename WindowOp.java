package 数据库实验;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class WindowOp extends JFrame {
      JFrame jf;
      JLabel jp1,jp2,jp3,jp4,jp5;
      JTextField jt1,jt2,jt3,jt4,jt5;
      JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7;
      ActionListener listener1,listener2,listener3,listener4,listener5,listener6,listener7;

      public WindowOp(){
            init();
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      }
      void init(){
            setLayout(null);
            jp1=new JLabel("商品号");
            jp2=new JLabel("商品名");
            jp3=new JLabel("品牌");
            jp4=new JLabel("价格");
            jp5=new JLabel("库存量");

            jt1=new JTextField();
            jt1.setPreferredSize(new Dimension(100,30));
            jt2=new JTextField();
            jt2.setPreferredSize(new Dimension (100,30));
            jt3=new JTextField();
            jt1.setPreferredSize(new Dimension (100,30));
            jt4=new JTextField();
            jt1.setPreferredSize(new Dimension (100,30));
            jt5=new JTextField();
            jt1.setPreferredSize(new Dimension (100,30));

            Connection conn=mysqlcon.getConnection();
            Statement state= null;
            ResultSet re = null;
            if(conn!=null){
                  //连接成功
                  try {
                        state = conn.createStatement();
                        re=state.executeQuery("select *FROM testmy");

                  } catch (SQLException throwables) {
                        throwables.printStackTrace();
                  }
            }
            try {
                  if(re.next()){
                        jt1.setText(String.valueOf(re.getInt("id")));
                        jt2.setText(String.valueOf(re.getString("pname")));
                        jt3.setText(String.valueOf(re.getString("brand")));
                        jt4.setText(String.valueOf(re.getDouble("price")));
                        jt5.setText(String.valueOf(re.getInt("stock")));}
                  conn.close();
            } catch (SQLException throwables) {
                  throwables.printStackTrace();
            }

            jb1=new JButton("第一条");
            jb2=new JButton("前一条");
            jb3=new JButton("下一条");
            jb4=new JButton("最后一条");
            jb5=new JButton("插入");
            jb6=new JButton("删除");
            jb7=new JButton("修改");

            //添加监听
            jb1.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        Connection conn=mysqlcon.getConnection();
                        Statement state= null;
                        ResultSet re = null;
                        if(conn!=null){
                              //连接成功
                              try {
                                    state = conn.createStatement();
                                    re=state.executeQuery("select *FROM testmy");

                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }
                        try {
                              if(re.next()){
                                    jt1.setText(String.valueOf(re.getInt("id")));
                                    jt2.setText(String.valueOf(re.getString("pname")));
                                    jt3.setText(String.valueOf(re.getString("brand")));
                                    jt4.setText(String.valueOf(re.getDouble("price")));
                                    jt5.setText(String.valueOf(re.getInt("stock")));}
                              conn.close();
                        } catch (SQLException throwables) {
                              throwables.printStackTrace();
                        }

                  }
            });
            jb2.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        Connection conn = mysqlcon.getConnection();
                        PreparedStatement state2= null;
                        ResultSet re = null;
                        if (conn != null) {
                              //连接成功
                              try {
                                    String id1 = jt1.getText();
                                    state2 = conn.prepareStatement("select *from testmy where id < ?");
                                    state2.setString(1, id1);
                                    re = state2.executeQuery();
                                    int flag=1;
                                    while(re.next()) {
                                          flag=0;
                                          jt1.setText(String.valueOf(re.getInt("id")));
                                          jt2.setText(String.valueOf(re.getString("pname")));
                                          jt3.setText(String.valueOf(re.getString("brand")));
                                          jt4.setText(String.valueOf(re.getDouble("price")));
                                          jt5.setText(String.valueOf(re.getInt("stock")));
                                    }
                                    if(flag==1)
                                          JOptionPane.showConfirmDialog(null, "已经是第一条！", "提示", JOptionPane.PLAIN_MESSAGE);
                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }

                  }
            });
            jb3.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        Connection conn = mysqlcon.getConnection();
                        PreparedStatement state3= null;
                        ResultSet re = null;
                        if (conn != null) {
                              //连接成功
                              try {
                                    String id1=jt1.getText();
                                    state3 = conn.prepareStatement("select *from testmy where id > ?");
                                    state3.setString(1,id1);
                                    re=state3.executeQuery();
                                    if(re.next()) {
                                          jt1.setText(String.valueOf(re.getInt("id")));
                                          jt2.setText(String.valueOf(re.getString("pname")));
                                          jt3.setText(String.valueOf(re.getString("brand")));
                                          jt4.setText(String.valueOf(re.getDouble("price")));
                                          jt5.setText(String.valueOf(re.getInt("stock")));
                                    }
                                    else JOptionPane.showConfirmDialog(null, "已经是最后一条！", "提示", JOptionPane.PLAIN_MESSAGE);
                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }
                  }
            });
            jb4.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        Connection conn=mysqlcon.getConnection();
                        Statement state= null;
                        ResultSet re = null;
                        if(conn!=null){
                              //连接成功
                              try {
                                    state = conn.createStatement();
                                    re=state.executeQuery("select *FROM testmy");

                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }
                        try {
                              while(re.next());
                              if(re.previous()){
                                    jt1.setText(String.valueOf(re.getInt("id")));
                                    jt2.setText(String.valueOf(re.getString("pname")));
                                    jt3.setText(String.valueOf(re.getString("brand")));
                                    jt4.setText(String.valueOf(re.getDouble("price")));
                                    jt5.setText(String.valueOf(re.getInt("stock")));}
                              conn.close();
                        } catch (SQLException throwables) {
                              JOptionPane.showConfirmDialog(null,"已经是最后一条！","提示",JOptionPane.PLAIN_MESSAGE);
                        }
                  }
            });
            jb5.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        PreparedStatement state5= null;
                        try {
                              String id1= jt1.getText();
                              String pname1=jt2.getText();
                              String brand1=jt3.getText();
                              String price1= jt4.getText();
                              String stock1= jt5.getText();
                              Connection conn=mysqlcon.getConnection();
                              state5 = conn.prepareStatement("insert into testmy(id,pname,brand,price,stock) values (?,?,?,?,?)");
                              state5.setString(1,id1);
                              state5.setString(2,pname1);
                              state5.setString(3,brand1);
                              state5.setString(4,price1);
                              state5.setString(5,stock1);
                              int i = state5.executeUpdate();
                              if(i>0) JOptionPane.showConfirmDialog(null,"添加成功！","提示",JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException throwables) {
                              JOptionPane.showConfirmDialog(null,"添加失败！","提示",JOptionPane.PLAIN_MESSAGE);
                        }finally {
                              try {
                                    conn.close();
                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }
                  }
            });
            jb7.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        PreparedStatement state7= null;
                        try {
                              String id1=jt1.getText();
                              String pname1=jt2.getText();
                              String brand1=jt3.getText();
                              String price1= jt4.getText();
                              String stock1= jt5.getText();
                              Connection conn=mysqlcon.getConnection();
                              state7 = conn.prepareStatement("update testmy set pname = ?,brand = ?,price = ?,stock = ? where  id = ?");
                              state7.setString(1,pname1);
                              state7.setString(2,brand1);
                              state7.setString(3,price1);
                              state7.setString(4,stock1);
                              state7.setString(5,id1);
                              int i = state7.executeUpdate();
                              if(i>0) JOptionPane.showConfirmDialog(null,"修改成功！","提示",JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException throwables) {
                              JOptionPane.showConfirmDialog(null,"修改失败！","提示",JOptionPane.PLAIN_MESSAGE);
                        }finally {
                              try {
                                    conn.close();
                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }
                  }
            });
            jb6.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent actionEvent) {
                        PreparedStatement state6= null;
                        try {
                              String id1= jt1.getText();
                              Connection conn=mysqlcon.getConnection();
                              state6 = conn.prepareStatement("delete from testmy where id=?");
                              state6.setString(1,id1);
                              int i = state6.executeUpdate();
                              if(i>0) JOptionPane.showConfirmDialog(null,"删除成功！","提示",JOptionPane.PLAIN_MESSAGE);
                        } catch (SQLException throwables) {
                              JOptionPane.showConfirmDialog(null,"删除失败！","提示",JOptionPane.PLAIN_MESSAGE);
                        }finally {
                              try {
                                    conn.close();
                              } catch (SQLException throwables) {
                                    throwables.printStackTrace();
                              }
                        }

                  }
            });

            add(jp1);
            jp1.setBounds(15,2,40,30);
            add(jt1);
            jt1.setBounds(60,3,150,25);
            add(jp2);
            jp2.setBounds(340,2,40,30);
            add(jt2);
            jt2.setBounds(390,3,150,25);
            add(jp3);
            jp3.setBounds(15,84,40,30);
            add(jt3);
            jt3.setBounds(60,85,150,25);
            add(jp4);
            jp4.setBounds(340,84,40,30);
            add(jt4);
            jt4.setBounds(390,84,150,25);
            add(jp5);
            jp5.setBounds(15,166,40,30);
            add(jt5);
            jt5.setBounds(60,166,150,25);
            add(jb1);
            jb1.setBounds(15,250,80,30);
            add(jb2);
            jb2.setBounds(95,250,80,30);
            add(jb3);
            jb3.setBounds(175,250,80,30);
            add(jb4);
            jb4.setBounds(255,250,90,30);
            add(jb5);
            jb5.setBounds(345,250,80,30);
            add(jb6);
            jb6.setBounds(425,250,80,30);
            add(jb7);
            jb7.setBounds(505,250,80,30);
      }
}
