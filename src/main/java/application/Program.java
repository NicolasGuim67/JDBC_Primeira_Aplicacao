package application;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DataBaseConnection;
import db.DbException;

import static db.DataBaseConnection.*;

public class Program {
    static void main(String[] args) {
        Connection conn = (Connection) getConnection();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        DataBaseConnection.closeConnection();

        PreparedStatement st = null;
        try{
            conn = DataBaseConnection.getConnection();

            st = conn.prepareStatement(
                    "INSERT INTO pessoas(nome, nascimento, sexo, peso, altura, nacionalidade)" +
                            "VALUES(?, ?, ?, ?, ?, ?)"
                   ,
                    Statement.RETURN_GENERATED_KEYS);


            st.setString(1,"Ana");
            st.setDate(2,java.sql.Date.valueOf("2002-07-15"));
            st.setString(3,"F");
            st.setDouble(4,53.0);
            st.setDouble(5,1.66);
            st.setString(6,"Argentina");


          int rowsAffected =  st.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);

        }
        catch  (SQLException e){
            throw new DbException((e.getMessage()));
        }
        finally {
            DataBaseConnection.closeStatement(st);
            DataBaseConnection.closeConnection();
        }
    }
}
