package test;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
public class TestSQL {  
    public static void main(String[] args){  
        String driver = "org.mariadb.jdbc.Driver";
        String url = "jdbc:mysql://rm-wz9h3t77tr31l38qkpo.mysql.rds.aliyuncs.com:3306/gsscdb";  
        String username = "xlcx";
        String password = "Sxxlkj3826ZS";
        Connection conn = null;  
        Statement stat = null;  
        ResultSet rs = null;  
        try{
            Class.forName(driver); 
            conn = DriverManager.getConnection(url,username,password); 
            stat = conn.createStatement();   
            //String sql1="show tables";
            String sql1="SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA='gsscdb'";
            rs = stat.executeQuery(sql1); 
            
            while(rs.next()){  
            	System.out.println(rs.getString(1));
            }  
        } catch(ClassNotFoundException e){  
            e.printStackTrace();  
        } catch(SQLException e){  
            e.printStackTrace();  
        } finally {  
            if(rs != null){  
                try{  
                    rs.close();  
                } catch(SQLException e){  
                    e.printStackTrace();  
                } finally {  
                    if(stat != null){  
                        try{  
                            stat.close();
                        } catch(SQLException e){  
                            e.printStackTrace();  
                        } finally {  
                            if(conn != null){}  
                                try{  
                                    conn.close();  
                                } catch(SQLException e){  
                                    e.printStackTrace();  
                                }  
                        }  
                    }   
                }  
            }  
        }  
    }  
}  