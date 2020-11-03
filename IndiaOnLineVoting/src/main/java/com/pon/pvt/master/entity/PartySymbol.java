 

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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "party_symbol")
@NamedQueries({
    @NamedQuery(name = "PartySymbol.findAll", query = "SELECT p FROM PartySymbol p"),
    @NamedQuery(name = "PartySymbol.findById", query = "SELECT p FROM PartySymbol p WHERE p.id = :id"),
    @NamedQuery(name = "PartySymbol.findByNameWithExt", query = "SELECT p FROM PartySymbol p WHERE p.nameWithExt = :nameWithExt"),
    @NamedQuery(name = "PartySymbol.findByDescription", query = "SELECT p FROM PartySymbol p WHERE p.description = :description")})
public class PartySymbol implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name_with_ext")
    private String nameWithExt;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "symbolId")
    private List<PartyMaster> partyMasterList;

    public PartySymbol() {
    }

    public PartySymbol(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameWithExt() {
        return nameWithExt;
    }

    public void setNameWithExt(String nameWithExt) {
        this.nameWithExt = nameWithExt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof PartySymbol)) {
            return false;
        }
        PartySymbol other = (PartySymbol) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.PartySymbol[ id=" + id + " ]";
    }
    
}
