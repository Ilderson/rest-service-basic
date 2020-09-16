package com.br.crud.rest.example.control;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.br.crud.rest.example.usuario.Usuario;
import com.br.crud.rest.example.usuario.UsuarioRepository;


@Component
public class SchedulerReadCSVFiles {
	
	final static String COMMA_DELIMITER = ";";
	private static final Logger logger = LoggerFactory.getLogger(SchedulerReadCSVFiles.class);
	
	@Autowired
	private UsuarioRepository repository;
	
	
	@Scheduled(fixedRate = 30000)
	public void readDirectory() {
		File[] arrayFiles;	
		
		arrayFiles = finder("D:\\Projetos\\CrudRestPuma\\rest-service-basic\\src\\main\\resources\\app\\files");
		//arrayFiles = finder("..resources/app/files");
		for (File file : arrayFiles) {
			if(readCSVFiles(file))
			file.renameTo(new File(file.getAbsolutePath() + ".old"));				
		} 
	}
	
	public File[] finder(String dirName){
        File dir = new File(dirName);

        return dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".csv"); }
        } );

    }
	
	public boolean readCSVFiles(File nameFile) {
		List<List<String>> records = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(nameFile))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		        String[] values = line.split(COMMA_DELIMITER);
		        records.add(Arrays.asList(values));
		        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(values[2]);  
		        Usuario newuser = new Usuario(Integer.valueOf(values[0]), values[1], values[2], date);
		        
		        try {
		        	repository.save(newuser);
		        } catch (ConstraintViolationException e) {
		        	logger.info(newuser.toString() + " - " + e.getMessage());	
				}		        
		    }
		    return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	


}
