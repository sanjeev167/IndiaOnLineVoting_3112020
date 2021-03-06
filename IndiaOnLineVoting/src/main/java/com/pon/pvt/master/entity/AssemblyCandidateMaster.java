/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.master.entity;

import com.pon.pvt.voting.entity.VoteRepository;
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

import com.pon.pvt.master.dto.AssemblyCandidateMasterDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "assembly_candidate_master")
@NamedQueries({
    @NamedQuery(name = "AssemblyCandidateMaster.findAll", query = "SELECT a FROM AssemblyCandidateMaster a"),
    @NamedQuery(name = "AssemblyCandidateMaster.findById", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.id = :id"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByName", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.name = :name"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByCreatedOn", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.createdOn = :createdOn"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByCreatedBy", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByUpdatedOn", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.updatedOn = :updatedOn"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByUpdatedBy", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.updatedBy = :updatedBy"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByActiveC", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.activeC = :activeC"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByElectionYear", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.electionYear = :electionYear"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByNominatedAs", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.nominatedAs = :nominatedAs"),
    @NamedQuery(name = "AssemblyCandidateMaster.findByAcandidateNo", query = "SELECT a FROM AssemblyCandidateMaster a WHERE a.acandidateNo = :acandidateNo")})



@SqlResultSetMapping(
        name = "AssemblyCandidateMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = AssemblyCandidateMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"), 
                            @ColumnResult(name = "assemblyName"), 
                            @ColumnResult(name = "partyName"),
                            
                            @ColumnResult(name = "acandidateName"),
                            @ColumnResult(name = "acandidateNo",type = Integer.class),
                            @ColumnResult(name = "electionYear",type = Integer.class),
                            @ColumnResult(name = "imgName"),                            
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class AssemblyCandidateMaster implements Serializable {

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
    @Column(name = "election_year")
    private String electionYear;
    @Column(name = "nominated_as")
    private Character nominatedAs;
    @Column(name = "acandidate_no")
    private Integer acandidateNo;
    @JoinColumn(name = "assembly_id", referencedColumnName = "id")
    @ManyToOne
    private AssemblyMaster assemblyId;
    @JoinColumn(name = "party_id", referencedColumnName = "id")
    @ManyToOne
    private PartyMaster partyId;

    public AssemblyCandidateMaster() {
    }

    public AssemblyCandidateMaster(Integer id) {
        this.id = id;
    }

    public AssemblyCandidateMaster(Integer id, Character activeC) {
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

    public String getElectionYear() {
        return electionYear;
    }

    public void setElectionYear(String electionYear) {
        this.electionYear = electionYear;
    }

    public Character getNominatedAs() {
        return nominatedAs;
    }

    public void setNominatedAs(Character nominatedAs) {
        this.nominatedAs = nominatedAs;
    }

    public Integer getAcandidateNo() {
        return acandidateNo;
    }

    public void setAcandidateNo(Integer acandidateNo) {
        this.acandidateNo = acandidateNo;
    }

    public AssemblyMaster getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(AssemblyMaster assemblyId) {
        this.assemblyId = assemblyId;
    }

    public PartyMaster getPartyId() {
        return partyId;
    }

    public void setPartyId(PartyMaster partyId) {
        this.partyId = partyId;
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
        if (!(object instanceof AssemblyCandidateMaster)) {
            return false;
        }
        AssemblyCandidateMaster other = (AssemblyCandidateMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AssemblyCandidateMaster[ id=" + id + " ]";
    }
    
}
