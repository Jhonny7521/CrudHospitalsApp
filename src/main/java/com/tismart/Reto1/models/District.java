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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "DISTRICTS", schema = "USERRETO1")
public class District {

	@Id
	@Column(name="IDDISTRICT")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La descripción no puede ser vacia")
	@NotBlank(message = "La descripción no puede estar en blanco")
	@Size(min = 3, max = 50, message = "La descripción debe contener entre 3 y 50 caracteres")
	@Column(name="DISTRICTDESC")
	private String districtDescription;
	
	@Column(name="CREATEDAT", updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPROVINCE")
	private Province province;
	
	@OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
	private List<Hospital> hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDistrictDescription() {
		return districtDescription;
	}

	public void setDistrictDescription(String districtDescription) {
		this.districtDescription = districtDescription;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "Districts [id=" + id + ", districtDescription=" + districtDescription + ", createdAt=" + createdAt
				+ ", province=" + province + "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
