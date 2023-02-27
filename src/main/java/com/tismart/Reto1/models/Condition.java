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
@Table(name = "CONDITIONS", schema = "USERRETO1")
public class Condition {
	
	@Id
	@Column(name="IDCONDITION")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "La descripción no puede ser vacia")
	@NotBlank(message = "La descripción no puede estar en blanco")
	@Size(min = 3, max = 50, message = "La descripción debe contener entre 3 y 50 caracteres")
	@Column(name="CONDITIONDESC")
	private String conditionDescription;
	
	@Column(name="CREATEDAT", updatable = false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@OneToMany(mappedBy = "condition", fetch = FetchType.LAZY)
	private List<Hospital> hospital;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}	

	public String getConditionDescription() {
		return conditionDescription;
	}

	public void setConditionDescription(String conditionDescription) {
		this.conditionDescription = conditionDescription;
	}

	public List<Hospital> getHospital() {
		return hospital;
	}

	public void setHospital(List<Hospital> hospital) {
		this.hospital = hospital;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "Conditios [id=" + id + ", provinceDescription=" + conditionDescription + ", createdAt=" + createdAt
				+ "]";
	}
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
}
