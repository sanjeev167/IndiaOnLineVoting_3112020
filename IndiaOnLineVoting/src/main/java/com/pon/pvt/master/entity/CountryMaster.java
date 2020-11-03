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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import com.pon.pvt.master.dto.CountryMasterDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "country_master")
@NamedQueries({
    @NamedQuery(name = "CountryMaster.findAll", query = "SELECT c FROM CountryMaster c"),
    @NamedQuery(name = "CountryMaster.findById", query = "SELECT c FROM CountryMaster c WHERE c.id = :id"),
    @NamedQuery(name = "CountryMaster.findBySortname", query = "SELECT c FROM CountryMaster c WHERE c.sortname = :sortname"),
    @NamedQuery(name = "CountryMaster.findByName", query = "SELECT c FROM CountryMaster c WHERE c.name = :name"),
    @NamedQuery(name = "CountryMaster.findByPhonecode", query = "SELECT c FROM CountryMaster c WHERE c.phonecode = :phonecode")})

@SqlResultSetMapping(
        name = "CountryMasterDTOMapping",
        classes = @ConstructorResult(
                targetClass = CountryMasterDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "name"), 
                            @ColumnResult(name = "sortname"), 
                            @ColumnResult(name = "phonecode", type = Integer.class),
                            @ColumnResult(name = "totalrecords", type = Integer.class),
                            }))
public class CountryMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sortname")
    private String sortname;
    @Column(name = "name")
    private String name;
    @Column(name = "phonecode")
    private Integer phonecode;
    @OneToMany(mappedBy = "countryId")
    private List<StateMaster> stateMasterList;

    public CountryMaster() {
    }

    public CountryMaster(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSortname() {
        return sortname;
    }

    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(Integer phonecode) {
        this.phonecode = phonecode;
    }

    public List<StateMaster> getStateMasterList() {
        return stateMasterList;
    }

    public void setStateMasterList(List<StateMaster> stateMasterList) {
        this.stateMasterList = stateMasterList;
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
        if (!(object instanceof CountryMaster)) {
            return false;
        }
        CountryMaster other = (CountryMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.pon.pvt.master.entity.CountryMaster[ id=" + id + " ]";
    }
    
}
