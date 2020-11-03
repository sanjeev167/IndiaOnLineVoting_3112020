/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.master.entity;

import java.io.Serializable;
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

import com.pon.pvt.master.dto.CityMasterDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "city_master")
@NamedQueries({
    @NamedQuery(name = "CityMaster.findAll", query = "SELECT c FROM CityMaster c"),
    @NamedQuery(name = "CityMaster.findById", query = "SELECT c FROM CityMaster c WHERE c.id = :id"),
    @NamedQuery(name = "CityMaster.findByName", query = "SELECT c FROM CityMaster c WHERE c.name = :name")})

@SqlResultSetMapping(
        name = "CityMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = CityMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "countryName"), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "cityName"), 
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class CityMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @JoinColumn(name = "state_id", referencedColumnName = "id")
    @ManyToOne
    private StateMaster stateId;

    public CityMaster() {
    }

    public CityMaster(Integer id) {
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
        if (!(object instanceof CityMaster)) {
            return false;
        }
        CityMaster other = (CityMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pon.pvt.master.entity.CityMaster[ id=" + id + " ]";
    }
    
}
