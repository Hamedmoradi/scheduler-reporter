package com.pooyabyte.training.dto;


import com.pooyabyte.training.domain.Customer;
import com.pooyabyte.training.domain.EmailNotification;
import com.pooyabyte.training.domain.NotificationMessageType;
import com.pooyabyte.training.domain.SmsNotification;
import com.pooyabyte.training.validation.NullIfAnotherFieldHasValue;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;


@NullIfAnotherFieldHasValue.List({
        @NullIfAnotherFieldHasValue(
                fieldName = "emailChecked",
                fieldValue = "false",
                dependFieldName = "emailAddress"),
        @NullIfAnotherFieldHasValue(
                fieldName = "smsChecked",
                fieldValue = "false",
                dependFieldName = "cellphone")
})
public class ClientDto implements Serializable {

    private Long id;

    @NotBlank
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Length(min = 10, max = 10, message = "nationalCode must be 10 digit")
    private String nationalCode;

    @NotBlank
    @Size(min = 3, max = 30, message = "name must be between 10 and 30 characters")
    private String name;

    @NotBlank
    @Size(min = 3, max = 30, message = "family must be between 10 and 30 characters")
    private String family;

    //    @Email(regexp = ".+@.+\\.[a-z]+")
    //    @Length(min = 5, max = 50, message = "email must be between 5 to 50 character")
    //    @Nullable
    private String emailAddress;

    //     @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "09378288717")
    //    @Length(min = 11, max = 11, message = "cellphone must be 11 number")
    //    @Nullable
    private String cellphone;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date emailCreatedOn;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date smsCreatedOn;

    @NotNull
    @NotEmpty
    private String[] messageTitleService;

    private boolean emailChecked;
    private boolean smsChecked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Date getEmailCreatedOn() {
        return emailCreatedOn;
    }

    public void setEmailCreatedOn(Date emailCreatedOn) {
        this.emailCreatedOn = emailCreatedOn;
    }

    public Date getSmsCreatedOn() {
        return smsCreatedOn;
    }

    public void setSmsCreatedOn(Date smsCreatedOn) {
        this.smsCreatedOn = smsCreatedOn;
    }

    public String[] getMessageTitleService() {
        return messageTitleService;
    }

    public void setMessageTitleService(String[] messageTitleService) {
        this.messageTitleService = messageTitleService;
    }

    public boolean isEmailChecked() {
        return emailChecked;
    }

    public void setEmailChecked(boolean emailChecked) {
        this.emailChecked = emailChecked;
    }

    public boolean isSmsChecked() {
        return smsChecked;
    }

    public void setSmsChecked(boolean smsChecked) {
        this.smsChecked = smsChecked;
    }

    public static ClientDto otherObjectToClientDto(Customer customer, EmailNotification emailNotification,
                                                   SmsNotification smsNotification, NotificationMessageType notificationMessageType) {

        ClientDto clientDto = new ClientDto();
        clientDto.setId(customer.getId());
        clientDto.setNationalCode(customer.getNationalCode());
        clientDto.setName(customer.getName());
        clientDto.setFamily(customer.getFamily());
        clientDto.setEmailAddress(customer.getEmailAddress());
        clientDto.setCellphone(customer.getCellphone());
        clientDto.setEmailCreatedOn(emailNotification.getCreatedOn());
        clientDto.setSmsCreatedOn(smsNotification.getCreatedOn());
        clientDto.setMessageTitleService(notificationMessageType.getMessageTitle());
        return clientDto;

    }

}
