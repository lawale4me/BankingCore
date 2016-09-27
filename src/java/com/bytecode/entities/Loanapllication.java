/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Ahmed
 */
@Entity
@Table(name = "loanapllication")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Loanapllication.findAll", query = "SELECT l FROM Loanapllication l"),
    @NamedQuery(name = "Loanapllication.findByLoanid", query = "SELECT l FROM Loanapllication l WHERE l.loanid = :loanid"),
    @NamedQuery(name = "Loanapllication.findByAddress", query = "SELECT l FROM Loanapllication l WHERE l.address = :address"),
    @NamedQuery(name = "Loanapllication.findByAnnualSalary", query = "SELECT l FROM Loanapllication l WHERE l.annualSalary = :annualSalary"),
    @NamedQuery(name = "Loanapllication.findByApprovedby", query = "SELECT l FROM Loanapllication l WHERE l.approvedby = :approvedby"),
    @NamedQuery(name = "Loanapllication.findByBankAccountNo", query = "SELECT l FROM Loanapllication l WHERE l.bankAccountNo = :bankAccountNo"),
    @NamedQuery(name = "Loanapllication.findByBankName", query = "SELECT l FROM Loanapllication l WHERE l.bankName = :bankName"),
    @NamedQuery(name = "Loanapllication.findByBankStatement", query = "SELECT l FROM Loanapllication l WHERE l.bankStatement = :bankStatement"),
    @NamedQuery(name = "Loanapllication.findByChannel", query = "SELECT l FROM Loanapllication l WHERE l.channel = :channel"),
    @NamedQuery(name = "Loanapllication.findByDeniedby", query = "SELECT l FROM Loanapllication l WHERE l.deniedby = :deniedby"),
    @NamedQuery(name = "Loanapllication.findByDept", query = "SELECT l FROM Loanapllication l WHERE l.dept = :dept"),
    @NamedQuery(name = "Loanapllication.findByDob", query = "SELECT l FROM Loanapllication l WHERE l.dob = :dob"),
    @NamedQuery(name = "Loanapllication.findByEmail", query = "SELECT l FROM Loanapllication l WHERE l.email = :email"),
    @NamedQuery(name = "Loanapllication.findByEmpNo", query = "SELECT l FROM Loanapllication l WHERE l.empNo = :empNo"),
    @NamedQuery(name = "Loanapllication.findByEmployer", query = "SELECT l FROM Loanapllication l WHERE l.employer = :employer"),
    @NamedQuery(name = "Loanapllication.findByEmployerAddress", query = "SELECT l FROM Loanapllication l WHERE l.employerAddress = :employerAddress"),
    @NamedQuery(name = "Loanapllication.findByEmployerPhone", query = "SELECT l FROM Loanapllication l WHERE l.employerPhone = :employerPhone"),
    @NamedQuery(name = "Loanapllication.findByEnddate", query = "SELECT l FROM Loanapllication l WHERE l.enddate = :enddate"),
    @NamedQuery(name = "Loanapllication.findByGrade", query = "SELECT l FROM Loanapllication l WHERE l.grade = :grade"),
    @NamedQuery(name = "Loanapllication.findByIdCard", query = "SELECT l FROM Loanapllication l WHERE l.idCard = :idCard"),
    @NamedQuery(name = "Loanapllication.findByJobDesc", query = "SELECT l FROM Loanapllication l WHERE l.jobDesc = :jobDesc"),
    @NamedQuery(name = "Loanapllication.findByKinName", query = "SELECT l FROM Loanapllication l WHERE l.kinName = :kinName"),
    @NamedQuery(name = "Loanapllication.findByKinPhone", query = "SELECT l FROM Loanapllication l WHERE l.kinPhone = :kinPhone"),
    @NamedQuery(name = "Loanapllication.findByKinType", query = "SELECT l FROM Loanapllication l WHERE l.kinType = :kinType"),
    @NamedQuery(name = "Loanapllication.findByKinWork", query = "SELECT l FROM Loanapllication l WHERE l.kinWork = :kinWork"),
    @NamedQuery(name = "Loanapllication.findByLengthOfService", query = "SELECT l FROM Loanapllication l WHERE l.lengthOfService = :lengthOfService"),
    @NamedQuery(name = "Loanapllication.findByLoanAmount", query = "SELECT l FROM Loanapllication l WHERE l.loanAmount = :loanAmount"),
    @NamedQuery(name = "Loanapllication.findByLoandate", query = "SELECT l FROM Loanapllication l WHERE l.loandate = :loandate"),
    @NamedQuery(name = "Loanapllication.findByOfficeEmail", query = "SELECT l FROM Loanapllication l WHERE l.officeEmail = :officeEmail"),
    @NamedQuery(name = "Loanapllication.findByOtherNames", query = "SELECT l FROM Loanapllication l WHERE l.otherNames = :otherNames"),
    @NamedQuery(name = "Loanapllication.findByPayDay", query = "SELECT l FROM Loanapllication l WHERE l.payDay = :payDay"),
    @NamedQuery(name = "Loanapllication.findByPhone", query = "SELECT l FROM Loanapllication l WHERE l.phone = :phone"),
    @NamedQuery(name = "Loanapllication.findByProdCode", query = "SELECT l FROM Loanapllication l WHERE l.prodCode = :prodCode"),
    @NamedQuery(name = "Loanapllication.findByPurpose", query = "SELECT l FROM Loanapllication l WHERE l.purpose = :purpose"),
    @NamedQuery(name = "Loanapllication.findByReferenceno", query = "SELECT l FROM Loanapllication l WHERE l.referenceno = :referenceno"),
    @NamedQuery(name = "Loanapllication.findByRequestdate", query = "SELECT l FROM Loanapllication l WHERE l.requestdate = :requestdate"),
    @NamedQuery(name = "Loanapllication.findBySignature", query = "SELECT l FROM Loanapllication l WHERE l.signature = :signature"),
    @NamedQuery(name = "Loanapllication.findByStatus", query = "SELECT l FROM Loanapllication l WHERE l.status = :status"),
    @NamedQuery(name = "Loanapllication.getPendingLoan", query = "SELECT l FROM Loanapllication l WHERE l.status = :status and l.referenceno = :referenceno "),    
    @NamedQuery(name = "Loanapllication.getApprovedLoan", query = "SELECT l FROM Loanapllication l WHERE l.status = :status and l.referenceno = :referenceno "),    
    @NamedQuery(name = "Loanapllication.getDisbursedLoan", query = "SELECT l FROM Loanapllication l WHERE l.status = :status and l.referenceno = :referenceno "),    
    @NamedQuery(name = "Loanapllication.findBySurname", query = "SELECT l FROM Loanapllication l WHERE l.surname = :surname"),
    @NamedQuery(name = "Loanapllication.findByTenor", query = "SELECT l FROM Loanapllication l WHERE l.tenor = :tenor"),
    @NamedQuery(name = "Loanapllication.findByTitle", query = "SELECT l FROM Loanapllication l WHERE l.title = :title"),
    @NamedQuery(name = "Loanapllication.findByTotalExistingLoan", query = "SELECT l FROM Loanapllication l WHERE l.totalExistingLoan = :totalExistingLoan"),
    @NamedQuery(name = "Loanapllication.findByAuthorizedBy", query = "SELECT l FROM Loanapllication l WHERE l.authorizedBy = :authorizedBy"),
    @NamedQuery(name = "Loanapllication.findByComment", query = "SELECT l FROM Loanapllication l WHERE l.comment = :comment"),
    @NamedQuery(name = "Loanapllication.findByDisbursedBy", query = "SELECT l FROM Loanapllication l WHERE l.disbursedBy = :disbursedBy"),
    @NamedQuery(name = "Loanapllication.findByLoanType", query = "SELECT l FROM Loanapllication l WHERE l.loanType = :loanType"),
    @NamedQuery(name = "Loanapllication.findByApprovedAmount", query = "SELECT l FROM Loanapllication l WHERE l.approvedAmount = :approvedAmount"),
    @NamedQuery(name = "Loanapllication.findByLastTurnover", query = "SELECT l FROM Loanapllication l WHERE l.lastTurnover = :lastTurnover"),
    @NamedQuery(name = "Loanapllication.findByBusinessNature", query = "SELECT l FROM Loanapllication l WHERE l.businessNature = :businessNature"),
    @NamedQuery(name = "Loanapllication.findByBusinessYr", query = "SELECT l FROM Loanapllication l WHERE l.businessYr = :businessYr"),
    @NamedQuery(name = "Loanapllication.findByTenancyInvoice", query = "SELECT l FROM Loanapllication l WHERE l.tenancyInvoice = :tenancyInvoice"),
    @NamedQuery(name = "Loanapllication.findByLandlordName", query = "SELECT l FROM Loanapllication l WHERE l.landlordName = :landlordName"),
    @NamedQuery(name = "Loanapllication.findByPropertyAddress", query = "SELECT l FROM Loanapllication l WHERE l.propertyAddress = :propertyAddress"),
    @NamedQuery(name = "Loanapllication.findByLandlordBankName", query = "SELECT l FROM Loanapllication l WHERE l.landlordBankName = :landlordBankName"),
    @NamedQuery(name = "Loanapllication.findByLandlordAccountNo", query = "SELECT l FROM Loanapllication l WHERE l.landlordAccountNo = :landlordAccountNo"),
    @NamedQuery(name = "Loanapllication.findByChildName", query = "SELECT l FROM Loanapllication l WHERE l.childName = :childName"),
    @NamedQuery(name = "Loanapllication.findByChildSchool", query = "SELECT l FROM Loanapllication l WHERE l.childSchool = :childSchool"),
    @NamedQuery(name = "Loanapllication.findBySchoolBankName", query = "SELECT l FROM Loanapllication l WHERE l.schoolBankName = :schoolBankName"),
    @NamedQuery(name = "Loanapllication.findBySchoolAccountNo", query = "SELECT l FROM Loanapllication l WHERE l.schoolAccountNo = :schoolAccountNo"),
    @NamedQuery(name = "Loanapllication.findBySchoolfeeInvoice", query = "SELECT l FROM Loanapllication l WHERE l.schoolfeeInvoice = :schoolfeeInvoice")})
public class Loanapllication implements Serializable {
    @OneToMany(mappedBy = "loanId")
    private Collection<Installments> installmentsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "loanid")
    private Integer loanid;
    @Column(name = "address")
    private String address;
    @Column(name = "annualSalary")
    private String annualSalary;
    @Column(name = "approvedby")
    private String approvedby;
    @Column(name = "bankAccountNo")
    private String bankAccountNo;
    @Column(name = "bankName")
    private String bankName;
    @Column(name = "bankStatement")
    private String bankStatement;
    @Column(name = "channel")
    private String channel;
    @Column(name = "deniedby")
    private String deniedby;
    @Column(name = "dept")
    private String dept;
    @Column(name = "dob")
    private String dob;
    @Column(name = "email")
    private String email;
    @Column(name = "empNo")
    private String empNo;
    @Column(name = "employer")
    private String employer;
    @Column(name = "employerAddress")
    private String employerAddress;
    @Column(name = "employerPhone")
    private String employerPhone;
    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enddate;
    @Column(name = "grade")
    private String grade;
    @Column(name = "idCard")
    private String idCard;
    @Column(name = "jobDesc")
    private String jobDesc;
    @Column(name = "kinName")
    private String kinName;
    @Column(name = "kinPhone")
    private String kinPhone;
    @Column(name = "kinType")
    private String kinType;
    @Column(name = "kinWork")
    private String kinWork;
    @Column(name = "lengthOfService")
    private String lengthOfService;
    @Column(name = "loanAmount")
    private String loanAmount;
    @Column(name = "loandate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loandate;
    @Column(name = "officeEmail")
    private String officeEmail;
    @Column(name = "otherNames")
    private String otherNames;
    @Column(name = "payDay")
    private String payDay;
    @Column(name = "phone")
    private String phone;
    @Column(name = "prodCode")
    private String prodCode;
    @Column(name = "purpose")
    private String purpose;
    @Column(name = "referenceno")
    private String referenceno;
    @Column(name = "requestdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date requestdate;
    @Column(name = "signature")
    private String signature;
    @Column(name = "status")
    private Integer status;
    @Column(name = "surname")
    private String surname;
    @Column(name = "tenor")
    private String tenor;
    @Column(name = "title")
    private String title;
    @Column(name = "totalExistingLoan")
    private String totalExistingLoan;
    @Column(name = "authorizedBy")
    private String authorizedBy;
    @Column(name = "comment")
    private String comment;
    @Column(name = "disbursedBy")
    private String disbursedBy;
    @Column(name = "loanType")
    private Integer loanType;
    @Column(name = "approvedAmount")
    private String approvedAmount;
    @Column(name = "lastTurnover")
    private String lastTurnover;
    @Column(name = "businessNature")
    private String businessNature;
    @Column(name = "businessYr")
    private String businessYr;
    @Column(name = "tenancyInvoice")
    private String tenancyInvoice;
    @Column(name = "landlordName")
    private String landlordName;
    @Column(name = "propertyAddress")
    private String propertyAddress;
    @Column(name = "landlordBankName")
    private String landlordBankName;
    @Column(name = "landlordAccountNo")
    private String landlordAccountNo;
    @Column(name = "childName")
    private String childName;
    @Column(name = "childSchool")
    private String childSchool;
    @Column(name = "schoolBankName")
    private String schoolBankName;
    @Column(name = "schoolAccountNo")
    private String schoolAccountNo;
    @Column(name = "schoolfeeInvoice")
    private String schoolfeeInvoice;

    public Loanapllication() {
    }

    public Loanapllication(Integer loanid) {
        this.loanid = loanid;
    }

    public Integer getLoanid() {
        return loanid;
    }

    public void setLoanid(Integer loanid) {
        this.loanid = loanid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }

    public String getApprovedby() {
        return approvedby;
    }

    public void setApprovedby(String approvedby) {
        this.approvedby = approvedby;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankStatement() {
        return bankStatement;
    }

    public void setBankStatement(String bankStatement) {
        this.bankStatement = bankStatement;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getDeniedby() {
        return deniedby;
    }

    public void setDeniedby(String deniedby) {
        this.deniedby = deniedby;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmpNo() {
        return empNo;
    }

    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getEmployerAddress() {
        return employerAddress;
    }

    public void setEmployerAddress(String employerAddress) {
        this.employerAddress = employerAddress;
    }

    public String getEmployerPhone() {
        return employerPhone;
    }

    public void setEmployerPhone(String employerPhone) {
        this.employerPhone = employerPhone;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getKinName() {
        return kinName;
    }

    public void setKinName(String kinName) {
        this.kinName = kinName;
    }

    public String getKinPhone() {
        return kinPhone;
    }

    public void setKinPhone(String kinPhone) {
        this.kinPhone = kinPhone;
    }

    public String getKinType() {
        return kinType;
    }

    public void setKinType(String kinType) {
        this.kinType = kinType;
    }

    public String getKinWork() {
        return kinWork;
    }

    public void setKinWork(String kinWork) {
        this.kinWork = kinWork;
    }

    public String getLengthOfService() {
        return lengthOfService;
    }

    public void setLengthOfService(String lengthOfService) {
        this.lengthOfService = lengthOfService;
    }

    public String getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(String loanAmount) {
        this.loanAmount = loanAmount;
    }

    public Date getLoandate() {
        return loandate;
    }

    public void setLoandate(Date loandate) {
        this.loandate = loandate;
    }

    public String getOfficeEmail() {
        return officeEmail;
    }

    public void setOfficeEmail(String officeEmail) {
        this.officeEmail = officeEmail;
    }

    public String getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(String otherNames) {
        this.otherNames = otherNames;
    }

    public String getPayDay() {
        return payDay;
    }

    public void setPayDay(String payDay) {
        this.payDay = payDay;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProdCode() {
        return prodCode;
    }

    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getReferenceno() {
        return referenceno;
    }

    public void setReferenceno(String referenceno) {
        this.referenceno = referenceno;
    }

    public Date getRequestdate() {
        return requestdate;
    }

    public void setRequestdate(Date requestdate) {
        this.requestdate = requestdate;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTenor() {
        return tenor;
    }

    public void setTenor(String tenor) {
        this.tenor = tenor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotalExistingLoan() {
        return totalExistingLoan;
    }

    public void setTotalExistingLoan(String totalExistingLoan) {
        this.totalExistingLoan = totalExistingLoan;
    }

    public String getAuthorizedBy() {
        return authorizedBy;
    }

    public void setAuthorizedBy(String authorizedBy) {
        this.authorizedBy = authorizedBy;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDisbursedBy() {
        return disbursedBy;
    }

    public void setDisbursedBy(String disbursedBy) {
        this.disbursedBy = disbursedBy;
    }

    public Integer getLoanType() {
        return loanType;
    }

    public void setLoanType(Integer loanType) {
        this.loanType = loanType;
    }

    public String getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(String approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getLastTurnover() {
        return lastTurnover;
    }

    public void setLastTurnover(String lastTurnover) {
        this.lastTurnover = lastTurnover;
    }

    public String getBusinessNature() {
        return businessNature;
    }

    public void setBusinessNature(String businessNature) {
        this.businessNature = businessNature;
    }

    public String getBusinessYr() {
        return businessYr;
    }

    public void setBusinessYr(String businessYr) {
        this.businessYr = businessYr;
    }

    public String getTenancyInvoice() {
        return tenancyInvoice;
    }

    public void setTenancyInvoice(String tenancyInvoice) {
        this.tenancyInvoice = tenancyInvoice;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public String getPropertyAddress() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress = propertyAddress;
    }

    public String getLandlordBankName() {
        return landlordBankName;
    }

    public void setLandlordBankName(String landlordBankName) {
        this.landlordBankName = landlordBankName;
    }

    public String getLandlordAccountNo() {
        return landlordAccountNo;
    }

    public void setLandlordAccountNo(String landlordAccountNo) {
        this.landlordAccountNo = landlordAccountNo;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getChildSchool() {
        return childSchool;
    }

    public void setChildSchool(String childSchool) {
        this.childSchool = childSchool;
    }

    public String getSchoolBankName() {
        return schoolBankName;
    }

    public void setSchoolBankName(String schoolBankName) {
        this.schoolBankName = schoolBankName;
    }

    public String getSchoolAccountNo() {
        return schoolAccountNo;
    }

    public void setSchoolAccountNo(String schoolAccountNo) {
        this.schoolAccountNo = schoolAccountNo;
    }

    public String getSchoolfeeInvoice() {
        return schoolfeeInvoice;
    }

    public void setSchoolfeeInvoice(String schoolfeeInvoice) {
        this.schoolfeeInvoice = schoolfeeInvoice;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loanid != null ? loanid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Loanapllication)) {
            return false;
        }
        Loanapllication other = (Loanapllication) object;
        if ((this.loanid == null && other.loanid != null) || (this.loanid != null && !this.loanid.equals(other.loanid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bytecode.entities.Loanapllication[ loanid=" + loanid + " ]";
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Installments> getInstallmentsCollection() {
        return installmentsCollection;
    }

    public void setInstallmentsCollection(Collection<Installments> installmentsCollection) {
        this.installmentsCollection = installmentsCollection;
    }
    
}
