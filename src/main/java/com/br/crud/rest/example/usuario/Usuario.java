package com.br.crud.rest.example.usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.br.crud.rest.example.usuario.Validacoes.CompanyID;
import com.br.crud.rest.example.usuario.Validacoes.DateValidate;
import com.br.crud.rest.example.usuario.Validacoes.EmailValidate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long userId;
	
	@CompanyID
	@NotNull(message = "CompanyId is mandatory")
	private Integer companyId;
	
	@EmailValidate
	@NotBlank(message = "Email is mandatory")
	private String email;
	
	@NotBlank(message = "birthDate is mandatory")
	private String birthDate;
	
	@DateValidate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date dataNiver;
	
	public Usuario() {
		super();
	}
	
	public Usuario(Integer companyId, String email, String birthDate, Date dateBirth){
		super();
		this.companyId = companyId;
		this.email = email;
		this.birthDate = birthDate;
		this.dataNiver = dateBirth;
	}
	
	public Long getUserId() {
		return userId;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}
	
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getBirthDate() {
		return birthDate;
	}
	
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
		
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		Date date;
		try {
			date = df.parse(birthDate);
			this.dataNiver = date;
		} catch (ParseException e) {
			this.dataNiver = null;
		}	
	}
	
	public Date getDataNiver() {
		return dataNiver;
	}

	public void setDataNiver(Date dataNiver) {
		this.dataNiver = dataNiver;
	}

	
	@Override
	public String toString() {		
		return "[" + userId + ";" + companyId + ";" + email + ";" + birthDate + "]";
	}
}
