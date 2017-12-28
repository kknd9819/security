package com.zz.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;


@Entity
@Table(name="user")
@Data
public class User implements Serializable{

	private static final long serialVersionUID = 7161884202684396093L;
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String email;
	private Integer sex;
	private String token;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	private String password;
	
	@ManyToOne
	@JoinColumn(name="did")
	@JsonBackReference
	private Deparment deparment;
	
	@ManyToMany(cascade={},fetch = FetchType.EAGER)
	@JoinTable(name="user_role",
				joinColumns={@JoinColumn(name="user_id")},
				inverseJoinColumns={@JoinColumn(name="roles_id")})
	private List<Role> roles;

	
	

}
