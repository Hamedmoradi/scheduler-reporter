package com.pooyabyte.training.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
@ApiModel(description = "All details about the Customer. ")
public class Customer extends Auditable<String> {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@PrimaryKeyJoinColumn(name = "Customer_id")
@ApiModelProperty(notes = "The database generated customer ID")
private Long id;

@Column(name = "national_code")
@ApiModelProperty(notes = "The customer national code.")
private String nationalCode;

@Column(name = "name")
@ApiModelProperty(notes = "The customer first name")
private String name;

@Column(name = "family")
@ApiModelProperty(notes = "The customer family name.")
private String family;

@Column(name = "email_address",nullable=true)
@ApiModelProperty(notes = "The customer EMAIL address.")
private String emailAddress;

@Column(name = "cell_phone",nullable=true)
@ApiModelProperty(notes = "The customer cell phone number.")
private String cellphone;

@Column(name = "created_date", nullable = false, updatable = false)
@CreatedDate
private long createdDate;

@Column(name = "modified_date")
@LastModifiedDate
private long modifiedDate;
public Customer() {
}

public Customer(String nationalCode, String name, String family, String emailAddress, String cellphone) {
    this.nationalCode = nationalCode;
    this.name = name;
    this.family = family;
    this.emailAddress = emailAddress;
    this.cellphone = cellphone;
}

public Long getId() {
    return id;
}

public void setId(long id) {
    this.id = id;
}

public String getNationalCode() {
    return nationalCode;
}

public void setNationalCode(String nationalCode) {
    this.nationalCode = nationalCode;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getFamily() {
    return family;
}

public void setFamily(String family) {
    this.family = family;
}

public String getEmailAddress() {
    return emailAddress;
}

public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
}

public String getCellphone() {
    return cellphone;
}

public void setCellphone(String cellphone) {
    this.cellphone = cellphone;
}

public long getCreatedDate() {
    return createdDate;
}

public void setCreatedDate(long createdDate) {
    this.createdDate = createdDate;
}

public long getModifiedDate() {
    return modifiedDate;
}

public void setModifiedDate(long modifiedDate) {
    this.modifiedDate = modifiedDate;
}

@Override
public String toString() {
    return "Customer{" +
                   "id=" + id +
                   ", nationalCode='" + nationalCode + '\'' +
                   ", name='" + name + '\'' +
                   ", family='" + family + '\'' +
                   ", emailAddress='" + emailAddress + '\'' +
                   ", cellphone='" + cellphone + '\'' +
                   '}';
}
}
