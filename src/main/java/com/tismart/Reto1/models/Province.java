package com.tismart.Reto1.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="PROVINCES")
public class Province {
	
	@Id
	@Column(name="IDPROVINCE")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La descripción no puede ser vacia")
	@NotBlank(message = "La descripción no puede estar en blanco")
	@Size(min = 3, max = 50, message = "La descripción debe contener entre 3 y 50 caracteres")
	@Column(name="PROVINCEDESC")
	private String provinceDescription;
	
	@Column(name="CREATEDAT", updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@OneToMany(mappedBy = "province", fetch = FetchType.LAZY)
	private List<District> district;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProvinceDescription() {
		return provinceDescription;
	}

	public void setProvinceDescription(String provinceDescription) {
		this.provinceDescription = provinceDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public List<District> getDistrict() {
		return district;
	}

	public void setDistrict(List<District> district) {
		this.district = district;
	}

	@Override
	public String toString() {
		return "Provinces [id=" + id + ", provinceDescription=" + provinceDescription + ", createdAt=" + createdAt
				+ "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

}
