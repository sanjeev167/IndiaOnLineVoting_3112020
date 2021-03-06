/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.master.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.pon.pvt.master.dto.LoksabhaCandidateMasterDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "loksabha_candidate_master")
@NamedQueries({
    @NamedQuery(name = "LoksabhaCandidateMaster.findAll", query = "SELECT l FROM LoksabhaCandidateMaster l"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findById", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.id = :id"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByName", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.name = :name"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByCreatedOn", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.createdOn = :createdOn"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByCreatedBy", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.createdBy = :createdBy"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByUpdatedOn", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.updatedOn = :updatedOn"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByUpdatedBy", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.updatedBy = :updatedBy"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByActiveC", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.activeC = :activeC"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByElectionYear", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.electionYear = :electionYear"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByLcandidateNo", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.lcandidateNo = :lcandidateNo"),
    @NamedQuery(name = "LoksabhaCandidateMaster.findByNominatedAs", query = "SELECT l FROM LoksabhaCandidateMaster l WHERE l.nominatedAs = :nominatedAs")})


@SqlResultSetMapping(
        name = "LoksabhaCandidateMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = LoksabhaCandidateMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"), 
                            @ColumnResult(name = "partyName"),
                            
                            @ColumnResult(name = "lcandidateName"),
                            @ColumnResult(name = "lcandidateNo",type = Integer.class),
                            @ColumnResult(name = "electionYear",type = Integer.class),
                            @ColumnResult(name = "imgName"),                            
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))



public class LoksabhaCandidateMaster implements Serializable {

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
	    @Column(name = "lcandidate_no")
	    private Integer lcandidateNo;
	    @Column(name = "nominated_as")
	    private Character nominatedAs;
	    @JoinColumn(name = "loksabha_id", referencedColumnName = "id")
	    @ManyToOne
	    private LoksabhaMaster loksabhaId;
	    @JoinColumn(name = "party_id", referencedColumnName = "id")
	    @ManyToOne
	    private PartyMaster partyId;

	    public LoksabhaCandidateMaster() {
	    }

	    public LoksabhaCandidateMaster(Integer id) {
	        this.id = id;
	    }

	    public LoksabhaCandidateMaster(Integer id, Character activeC) {
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

	    public Integer getLcandidateNo() {
	        return lcandidateNo;
	    }

	    public void setLcandidateNo(Integer lcandidateNo) {
	        this.lcandidateNo = lcandidateNo;
	    }

	    public Character getNominatedAs() {
	        return nominatedAs;
	    }

	    public void setNominatedAs(Character nominatedAs) {
	        this.nominatedAs = nominatedAs;
	    }

	    public LoksabhaMaster getLoksabhaId() {
	        return loksabhaId;
	    }

	    public void setLoksabhaId(LoksabhaMaster loksabhaId) {
	        this.loksabhaId = loksabhaId;
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
	        if (!(object instanceof LoksabhaCandidateMaster)) {
	            return false;
	        }
	        LoksabhaCandidateMaster other = (LoksabhaCandidateMaster) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "com.example.demo.LoksabhaCandidateMaster[ id=" + id + " ]";
	    }
	    
	}
