package com.tismart.Reto1.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "HOSPITALS", schema = "USERRETO1")
public class Hospital {

	@Id
	@Column(name = "IDHOSPITAL")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	
	@Column(name = "HOSPITALNAME")
	@NotNull(message = "La descripción no puede ser vacia")
	@NotBlank(message = "La descripción no puede estar en blanco")
	@Size(min = 3, max = 50, message = "La descripción debe contener entre 3 y 50 caracteres")
	private String hospitalName;
	
	@Column(name = "HOSPITALAGE")
	@NotNull(message = "El campo antiguedad no puede ser nulo.")
	@DecimalMin(value = "0", message = "El campo antiguedad debe ser mayor o igual a 0.")
	private Integer hospitalAge;
	
	@Column(name = "HOSPITALAREA")
	@NotNull(message = "El campo área no puede ser nulo.")
	@DecimalMin(value = "0", message = "El campo área debe ser mayor o igual a 0.")
	private Double hospitalArea;
	
	@Column(name = "CREATEDAT")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDMANAGER")
	@NotNull(message = "El campo manager no puede ser nulo")
	private Manager manager;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDCONDITION")
	@NotNull(message = "El campo condición no puede ser nulo")
	private Condition condition;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDDISTRICT")
	@NotNull(message = "El campo distrito no puede ser nulo")
	private District district;  
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "IDLOCATION")
	@NotNull(message = "El campo sede no puede ser nulo")
	private Location location;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public Integer getHospitalAge() {
		return hospitalAge;
	}

	public void setHospitalAge(Integer hospitalAge) {
		this.hospitalAge = hospitalAge;
	}

	public Double getHospitalArea() {
		return hospitalArea;
	}

	public void setHospitalArea(Double hospitalArea) {
		this.hospitalArea = hospitalArea;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public District getDistrict() {
		return district;
	}

	public void setDistrict(District district) {
		this.district = district;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Hospital [Id=" + Id + ", hospitalName=" + hospitalName + ", hospitalAge=" + hospitalAge
				+ ", hospitalArea=" + hospitalArea + ", createdAt=" + createdAt + ", condition=" + condition
				+ ", district=" + district + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
