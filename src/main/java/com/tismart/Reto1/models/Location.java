package com.tismart.Reto1.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "LOCATIONS")
public class Location {

	@Id
	@Column(name = "IDLOCATION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La descripción no puede ser vacia")
	@NotBlank(message = "La descripción no puede estar en blanco")
	@Size(min = 3, max = 50, message = "La descripción debe contener entre 3 y 50 caracteres")
	@Column(name = "LOCATIONDESC")
	private String locationDescription;
	
	@Column(name = "CREATEDAT", updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@OneToOne(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Hospital hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationDescription() {
		return locationDescription;
	}

	public void setLocationDescription(String locationDescription) {
		this.locationDescription = locationDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Hospital getHospital() {
		return hospital;
	}

	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", locationDescription=" + locationDescription + ", createdAt=" + createdAt
				+ ", hospital=" + hospital + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
