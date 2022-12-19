package image;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.System.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class JDBCImg {


    public static void main(String[] args) {

        String cs = "jdbc:mysql://localhost:3306/jdbcimage/images";
        String user = "root";
        String password = "mohitsindhpure@1998";

        String sql = "INSERT INTO Images(Data) VALUES(?)";

        try (Connection con = DriverManager.getConnection(cs, user, password); PreparedStatement pst = con.prepareStatement(sql)) {

            File myFile = new File("/ImageJDBC/src/img/tree.jpg");
            try (FileInputStream fin = new FileInputStream(myFile)) {

                pst.setBinaryStream(1, fin, (int) myFile.length());
                pst.executeUpdate();

            } catch (IOException ex) {
            	System.out.println(ex.getMessage());
            }
        } catch (SQLException ex) {
        	System.out.println(ex.getMessage());
        }
    }
}
