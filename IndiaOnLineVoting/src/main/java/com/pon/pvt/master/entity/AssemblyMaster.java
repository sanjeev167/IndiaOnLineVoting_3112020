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

import com.pon.pvt.master.dto.AssemblyMasterDTO;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "assembly_master")
@NamedQueries({
    @NamedQuery(name = "AssemblyMaster.findAll", query = "SELECT a FROM AssemblyMaster a"),
    @NamedQuery(name = "AssemblyMaster.findById", query = "SELECT a FROM AssemblyMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AssemblyMaster.findByName", query = "SELECT a FROM AssemblyMaster a WHERE a.name = :name"),
    @NamedQuery(name = "AssemblyMaster.findByCreatedOn", query = "SELECT a FROM AssemblyMaster a WHERE a.createdOn = :createdOn"),
    @NamedQuery(name = "AssemblyMaster.findByCreatedBy", query = "SELECT a FROM AssemblyMaster a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "AssemblyMaster.findByUpdatedOn", query = "SELECT a FROM AssemblyMaster a WHERE a.updatedOn = :updatedOn"),
    @NamedQuery(name = "AssemblyMaster.findByUpdatedBy", query = "SELECT a FROM AssemblyMaster a WHERE a.updatedBy = :updatedBy"),
    @NamedQuery(name = "AssemblyMaster.findByActiveC", query = "SELECT a FROM AssemblyMaster a WHERE a.activeC = :activeC"),
    @NamedQuery(name = "AssemblyMaster.findByAssemblyNo", query = "SELECT a FROM AssemblyMaster a WHERE a.assemblyNo = :assemblyNo")})

@SqlResultSetMapping(
        name = "AssemblyMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = AssemblyMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"), 
                            @ColumnResult(name = "assemblyName"),
                            @ColumnResult(name = "assemblyNo",type = Integer.class),
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class AssemblyMaster implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
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
    @Column(name = "assembly_no")
    private Integer assemblyNo;
    @JoinColumn(name = "loksabha_id", referencedColumnName = "id")
    @ManyToOne
    private LoksabhaMaster loksabhaId;
    @OneToMany(mappedBy = "assemblyId")
    private List<PollingBoothMaster> pollingBoothMasterList;
    @OneToMany(mappedBy = "assemblyId")
    private List<AssemblyCandidateMaster> assemblyCandidateMasterList;
    @OneToMany(mappedBy = "assemblyId")
    private List<ElectionSchedule> electionScheduleList;

    public AssemblyMaster() {
    }

    public AssemblyMaster(Integer id) {
        this.id = id;
    }

    public AssemblyMaster(Integer id, Character activeC) {
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

    public Integer getAssemblyNo() {
        return assemblyNo;
    }

    public void setAssemblyNo(Integer assemblyNo) {
        this.assemblyNo = assemblyNo;
    }

    public LoksabhaMaster getLoksabhaId() {
        return loksabhaId;
    }

    public void setLoksabhaId(LoksabhaMaster loksabhaId) {
        this.loksabhaId = loksabhaId;
    }

    public List<PollingBoothMaster> getPollingBoothMasterList() {
        return pollingBoothMasterList;
    }

    public void setPollingBoothMasterList(List<PollingBoothMaster> pollingBoothMasterList) {
        this.pollingBoothMasterList = pollingBoothMasterList;
    }

    public List<AssemblyCandidateMaster> getAssemblyCandidateMasterList() {
        return assemblyCandidateMasterList;
    }

    public void setAssemblyCandidateMasterList(List<AssemblyCandidateMaster> assemblyCandidateMasterList) {
        this.assemblyCandidateMasterList = assemblyCandidateMasterList;
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
        if (!(object instanceof AssemblyMaster)) {
            return false;
        }
        AssemblyMaster other = (AssemblyMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AssemblyMaster[ id=" + id + " ]";
    }
    
}
