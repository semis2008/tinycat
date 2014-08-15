package com.tinycat.dto;

import java.util.Date;

import com.tinycat.pojo.User;

public class UserDTO {
	private Long id;
	private String name;
	private String email;
	private String photo;
	private Date reg_time;

	public static UserDTO init(User user) {
		UserDTO dto = new UserDTO();
		if(user==null)
			return dto;
		dto.setEmail(user.getEmail());
		dto.setId(user.getId());
		dto.setName(user.getName());
		dto.setPhoto(user.getPhoto());
		dto.setReg_time(user.getReg_time());
		return dto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Date getReg_time() {
		return reg_time;
	}

	public void setReg_time(Date reg_time) {
		this.reg_time = reg_time;
	}

}
