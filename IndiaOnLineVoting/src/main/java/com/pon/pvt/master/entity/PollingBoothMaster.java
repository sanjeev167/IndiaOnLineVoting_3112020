/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.master.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pon.pvt.master.dto.PollingBoothMasterDTO;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "polling_booth_master")
@NamedQueries({
    @NamedQuery(name = "PollingBoothMaster.findAll", query = "SELECT p FROM PollingBoothMaster p"),
    @NamedQuery(name = "PollingBoothMaster.findById", query = "SELECT p FROM PollingBoothMaster p WHERE p.id = :id"),
    @NamedQuery(name = "PollingBoothMaster.findByName", query = "SELECT p FROM PollingBoothMaster p WHERE p.name = :name"),
    @NamedQuery(name = "PollingBoothMaster.findByBoothNo", query = "SELECT p FROM PollingBoothMaster p WHERE p.boothNo = :boothNo"),
    @NamedQuery(name = "PollingBoothMaster.findByCreatedOn", query = "SELECT p FROM PollingBoothMaster p WHERE p.createdOn = :createdOn"),
    @NamedQuery(name = "PollingBoothMaster.findByCreatedBy", query = "SELECT p FROM PollingBoothMaster p WHERE p.createdBy = :createdBy"),
    @NamedQuery(name = "PollingBoothMaster.findByUpdatedOn", query = "SELECT p FROM PollingBoothMaster p WHERE p.updatedOn = :updatedOn"),
    @NamedQuery(name = "PollingBoothMaster.findByUpdatedBy", query = "SELECT p FROM PollingBoothMaster p WHERE p.updatedBy = :updatedBy"),
    @NamedQuery(name = "PollingBoothMaster.findByActiveC", query = "SELECT p FROM PollingBoothMaster p WHERE p.activeC = :activeC")})
@SqlResultSetMapping(
        name = "PollingBoothMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = PollingBoothMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"), 
                            @ColumnResult(name = "assemblyName"),
                            @ColumnResult(name = "pollingBoothName"),
                            @ColumnResult(name = "pollingBoothNo",type = Integer.class),
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class PollingBoothMaster implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "booth_no")
    private Integer boothNo;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIME)
    private Date createdOn;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIME)
    private Date updatedOn;
    @Column(name = "updated_by")
    private Integer updatedBy;
    @Basic(optional = false)
    @Column(name = "active_c")
    private Character activeC='Y';
    @OneToMany(mappedBy = "boothId")
    private List<VotersEnrolled> votersEnrolledList;
    @JoinColumn(name = "assembly_id", referencedColumnName = "id")
    @ManyToOne
    private AssemblyMaster assemblyId;
    @OneToMany(mappedBy = "pollingBoothId")
    private List<ElectionSchedule> electionScheduleList;

    public PollingBoothMaster() {
    }

    public PollingBoothMaster(Integer id) {
        this.id = id;
    }

    public PollingBoothMaster(Integer id, Character activeC) {
        this.id = id;
        this.activeC = activeC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBoothNo() {
        return boothNo;
    }

    public void setBoothNo(Integer boothNo) {
        this.boothNo = boothNo;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Character getActiveC() {
        return activeC;
    }

    public void setActiveC(Character activeC) {
        this.activeC = activeC;
    }

    public List<VotersEnrolled> getVotersEnrolledList() {
        return votersEnrolledList;
    }

    public void setVotersEnrolledList(List<VotersEnrolled> votersEnrolledList) {
        this.votersEnrolledList = votersEnrolledList;
    }

    public AssemblyMaster getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(AssemblyMaster assemblyId) {
        this.assemblyId = assemblyId;
    }

    public List<ElectionSchedule> getElectionScheduleList() {
        return electionScheduleList;
    }

    public void setElectionScheduleList(List<ElectionSchedule> electionScheduleList) {
        this.electionScheduleList = electionScheduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PollingBoothMaster)) {
            return false;
        }
        PollingBoothMaster other = (PollingBoothMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.PollingBoothMaster[ id=" + id + " ]";
    }
    
}
