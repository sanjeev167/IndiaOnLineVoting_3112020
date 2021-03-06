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

import com.pon.pvt.master.dto.PartyMasterDTO;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "party_master")
@NamedQueries({
    @NamedQuery(name = "PartyMaster.findAll", query = "SELECT p FROM PartyMaster p"),
    @NamedQuery(name = "PartyMaster.findById", query = "SELECT p FROM PartyMaster p WHERE p.id = :id"),
    @NamedQuery(name = "PartyMaster.findByName", query = "SELECT p FROM PartyMaster p WHERE p.name = :name"),
    @NamedQuery(name = "PartyMaster.findByCreatedOn", query = "SELECT p FROM PartyMaster p WHERE p.createdOn = :createdOn"),
    @NamedQuery(name = "PartyMaster.findByCreatedBy", query = "SELECT p FROM PartyMaster p WHERE p.createdBy = :createdBy"),
    @NamedQuery(name = "PartyMaster.findByUpdatedOn", query = "SELECT p FROM PartyMaster p WHERE p.updatedOn = :updatedOn"),
    @NamedQuery(name = "PartyMaster.findByActiveC", query = "SELECT p FROM PartyMaster p WHERE p.activeC = :activeC"),
    @NamedQuery(name = "PartyMaster.findByPartyType", query = "SELECT p FROM PartyMaster p WHERE p.partyType = :partyType")})

@SqlResultSetMapping(
        name = "PartyMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = PartyMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "partyName"),                         
                            @ColumnResult(name = "partyType"), 
                            @ColumnResult(name = "base64ImgName"),                             
                            @ColumnResult(name = "totalrecords", type = Integer.class)      		
                            }))


public class PartyMaster implements Serializable {

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
    @Basic(optional = false)
    @Column(name = "active_c")
    private Character activeC='Y';
    @Column(name = "party_type")
    private String partyType;
    @OneToMany(mappedBy = "partyId")
    private List<LoksabhaCandidateMaster> loksabhaCandidateMasterList;
    @OneToMany(mappedBy = "partyId")
    private List<AssemblyCandidateMaster> assemblyCandidateMasterList;
    @JoinColumn(name = "symbol_id", referencedColumnName = "id")
    @ManyToOne
    private PartySymbol symbolId;
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne
    private StateMaster stateId;

    public PartyMaster() {
    }

    public PartyMaster(Integer id) {
        this.id = id;
    }

    public PartyMaster(Integer id, Character activeC) {
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

    public Character getActiveC() {
        return activeC;
    }

    public void setActiveC(Character activeC) {
        this.activeC = activeC;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public List<LoksabhaCandidateMaster> getLoksabhaCandidateMasterList() {
        return loksabhaCandidateMasterList;
    }

    public void setLoksabhaCandidateMasterList(List<LoksabhaCandidateMaster> loksabhaCandidateMasterList) {
        this.loksabhaCandidateMasterList = loksabhaCandidateMasterList;
    }

    public List<AssemblyCandidateMaster> getAssemblyCandidateMasterList() {
        return assemblyCandidateMasterList;
    }

    public void setAssemblyCandidateMasterList(List<AssemblyCandidateMaster> assemblyCandidateMasterList) {
        this.assemblyCandidateMasterList = assemblyCandidateMasterList;
    }

    public PartySymbol getSymbolId() {
        return symbolId;
    }

    public void setSymbolId(PartySymbol symbolId) {
        this.symbolId = symbolId;
    }

    public StateMaster getStateId() {
        return stateId;
    }

    public void setStateId(StateMaster stateId) {
        this.stateId = stateId;
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
        if (!(object instanceof PartyMaster)) {
            return false;
        }
        PartyMaster other = (PartyMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.PartyMaster[ id=" + id + " ]";
    }
    
}
