package com.br.crud.rest.example.usuario.Validacoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import com.br.crud.rest.example.control.ApplicationProperties;
import com.br.crud.rest.example.usuario.Usuario;

public class ValidaRegras {
	public static boolean searchEmailAlready(Usuario newUser) {
		
		PreparedStatement prepareStatement = null;
		Connection connection = null;
		try {
			Properties prop = ApplicationProperties.getProp();
			Class.forName(prop.getProperty("spring.datasource.driver-class-name"));		
			connection = DriverManager.getConnection(prop.getProperty("spring.datasource.url"),
					                                 prop.getProperty("spring.datasource.username"),
					                                 prop.getProperty("spring.datasource.password"));
			String sql = "SELECT * FROM USUARIO WHERE EMAIL='" + newUser.getEmail() + "' AND COMPANY_ID=" + newUser.getCompanyId().intValue();
			prepareStatement = connection.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while(rs.next()) {
				return true;			
			}			
			
			return false;
			
		} catch(SQLException se) { 
	         se.printStackTrace(); 
	    } catch(Exception e) {  
	         e.printStackTrace(); 
	    } finally { 
	         // finally block used to close resources 
	    	try { 
	    		if(prepareStatement!=null) 
	    			prepareStatement.close();  
	        	} catch(SQLException se2) { 
	        	} // nothing we can do 
	        
	    	try { 
	            if(connection!=null) 
	            	connection.close(); 
	        } catch(SQLException se) { 
	            se.printStackTrace(); 
	        } 
	    }
		return false;
	}

}
