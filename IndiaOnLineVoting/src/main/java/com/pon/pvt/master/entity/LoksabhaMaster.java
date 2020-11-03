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

import com.pon.pvt.master.dto.LoksabhaMasterDTO;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "loksabha_master")
@NamedQueries({
    @NamedQuery(name = "LoksabhaMaster.findAll", query = "SELECT l FROM LoksabhaMaster l"),
    @NamedQuery(name = "LoksabhaMaster.findById", query = "SELECT l FROM LoksabhaMaster l WHERE l.id = :id"),
    @NamedQuery(name = "LoksabhaMaster.findByName", query = "SELECT l FROM LoksabhaMaster l WHERE l.name = :name"),
    @NamedQuery(name = "LoksabhaMaster.findByCreatedOn", query = "SELECT l FROM LoksabhaMaster l WHERE l.createdOn = :createdOn"),
    @NamedQuery(name = "LoksabhaMaster.findByCreatedBy", query = "SELECT l FROM LoksabhaMaster l WHERE l.createdBy = :createdBy"),
    @NamedQuery(name = "LoksabhaMaster.findByUpdatedOn", query = "SELECT l FROM LoksabhaMaster l WHERE l.updatedOn = :updatedOn"),
    @NamedQuery(name = "LoksabhaMaster.findByUpdatedBy", query = "SELECT l FROM LoksabhaMaster l WHERE l.updatedBy = :updatedBy"),
    @NamedQuery(name = "LoksabhaMaster.findByActiveC", query = "SELECT l FROM LoksabhaMaster l WHERE l.activeC = :activeC"),
    @NamedQuery(name = "LoksabhaMaster.findByLoksabhaNo", query = "SELECT l FROM LoksabhaMaster l WHERE l.loksabhaNo = :loksabhaNo")})
@SqlResultSetMapping(
        name = "LoksabhaMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = LoksabhaMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                		    @ColumnResult(name = "loksabhaNo", type = Integer.class),                		
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"),                             
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class LoksabhaMaster implements Serializable {

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
	    @Column(name = "loksabha_no")
	    private Integer loksabhaNo;
	    @OneToMany(mappedBy = "loksabhaId")
	    private List<AssemblyMaster> assemblyMasterList;
	    @OneToMany(mappedBy = "loksabhaId")
	    private List<LoksabhaCandidateMaster> loksabhaCandidateMasterList;
	    @JoinColumn(name = "state_id", referencedColumnName = "id")
	    @ManyToOne
	    private StateMaster stateId;
	    @OneToMany(mappedBy = "loksabhaId")
	    private List<ElectionSchedule> electionScheduleList;

	    public LoksabhaMaster() {
	    }

	    public LoksabhaMaster(Integer id) {
	        this.id = id;
	    }

	    public LoksabhaMaster(Integer id, Character activeC) {
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

	    public Integer getLoksabhaNo() {
	        return loksabhaNo;
	    }

	    public void setLoksabhaNo(Integer loksabhaNo) {
	        this.loksabhaNo = loksabhaNo;
	    }

	    public List<AssemblyMaster> getAssemblyMasterList() {
	        return assemblyMasterList;
	    }

	    public void setAssemblyMasterList(List<AssemblyMaster> assemblyMasterList) {
	        this.assemblyMasterList = assemblyMasterList;
	    }

	    public List<LoksabhaCandidateMaster> getLoksabhaCandidateMasterList() {
	        return loksabhaCandidateMasterList;
	    }

	    public void setLoksabhaCandidateMasterList(List<LoksabhaCandidateMaster> loksabhaCandidateMasterList) {
	        this.loksabhaCandidateMasterList = loksabhaCandidateMasterList;
	    }

	    public StateMaster getStateId() {
	        return stateId;
	    }

	    public void setStateId(StateMaster stateId) {
	        this.stateId = stateId;
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
	        if (!(object instanceof LoksabhaMaster)) {
	            return false;
	        }
	        LoksabhaMaster other = (LoksabhaMaster) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "com.example.demo.LoksabhaMaster[ id=" + id + " ]";
	    }
	    
	}
