/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.master.entity;


import java.io.Serializable;
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

import com.pon.pvt.master.dto.StateMasterDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "state_master")
@NamedQueries({
    @NamedQuery(name = "StateMaster.findAll", query = "SELECT s FROM StateMaster s"),
    @NamedQuery(name = "StateMaster.findById", query = "SELECT s FROM StateMaster s WHERE s.id = :id"),
    @NamedQuery(name = "StateMaster.findByName", query = "SELECT s FROM StateMaster s WHERE s.name = :name")})
@SqlResultSetMapping(
        name = "StateMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = StateMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "countryName"), 
                            @ColumnResult(name = "stateName"),                             
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class StateMaster implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "stateId")
    private List<CityMaster> cityMasterList;
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    @ManyToOne
    private CountryMaster countryId;
    @OneToMany(mappedBy = "stateId")
    private List<LoksabhaMaster> loksabhaMasterList;
    @OneToMany(mappedBy = "stateId")
    private List<PartyMaster> partyMasterList;

    public StateMaster() {
    }

    public StateMaster(Integer id) {
        this.id = id;
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

    public List<CityMaster> getCityMasterList() {
        return cityMasterList;
    }

    public void setCityMasterList(List<CityMaster> cityMasterList) {
        this.cityMasterList = cityMasterList;
    }

    public CountryMaster getCountryId() {
        return countryId;
    }

    public void setCountryId(CountryMaster countryId) {
        this.countryId = countryId;
    }

    public List<LoksabhaMaster> getLoksabhaMasterList() {
        return loksabhaMasterList;
    }

    public void setLoksabhaMasterList(List<LoksabhaMaster> loksabhaMasterList) {
        this.loksabhaMasterList = loksabhaMasterList;
    }

    public List<PartyMaster> getPartyMasterList() {
        return partyMasterList;
    }

    public void setPartyMasterList(List<PartyMaster> partyMasterList) {
        this.partyMasterList = partyMasterList;
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
        if (!(object instanceof StateMaster)) {
            return false;
        }
        StateMaster other = (StateMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.StateMaster[ id=" + id + " ]";
    }
    
}
