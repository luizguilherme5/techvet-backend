package br.com.bovdog.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BathGrooming {
	@Id
	@GeneratedValue
	private int id;
	private String serviceBathGrooming;
	
	// getters and setters for BathGrooming attributes.
	public int getId() {
		return id;
	}
	public void setId(int idBathGrooming){
		this.id = idBathGrooming;
	}
	
	public String getServiceBathGrooming() {
		return serviceBathGrooming;
	}
	public void setServiceBathGrooming(String serviceBathGrooming){
		this.serviceBathGrooming = serviceBathGrooming;
	}

}