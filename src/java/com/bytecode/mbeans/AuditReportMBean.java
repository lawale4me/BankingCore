/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bytecode.mbeans;


import com.bytecode.core.AdminRepositoryImpl;
import com.bytecode.core.ConManager;
import com.bytecode.dto.AuditReportDTO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Ahmed
 */
@ManagedBean
@ViewScoped
public class AuditReportMBean {
    
    private List<AuditReportDTO> ardto;  
    private List<AuditReportDTO> ardtoDetails;  
    private List<AuditReportDTO> filteredARdto; 
    
    ConManager appManager = new ConManager(new AdminRepositoryImpl());
    

    /**
     * Creates a new instance of AuditReportMBean
     */
    public AuditReportMBean() {
        if (ardto == null) {  
            ardto = appManager.getAllAuditReports();
        }  
    }

    public List<AuditReportDTO> getArdto() {
        return ardto;
    }

    public void setArdto(List<AuditReportDTO> ardto) {
        this.ardto = ardto;
    }

    public List<AuditReportDTO> getArdtoDetails() {
        return ardtoDetails;
    }

    public void setArdtoDetails(List<AuditReportDTO> ardtoDetails) {
        this.ardtoDetails = ardtoDetails;
    }

    public List<AuditReportDTO> getFilteredARdto() {
        return filteredARdto;
    }

    public void setFilteredARdto(List<AuditReportDTO> filteredARdto) {
        this.filteredARdto = filteredARdto;
    }
    
    
    
    
    
}
