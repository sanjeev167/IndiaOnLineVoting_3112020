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
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "sensetive_pagelink_master")
@NamedQueries({
    @NamedQuery(name = "SensetivePagelinkMaster.findAll", query = "SELECT s FROM SensetivePagelinkMaster s"),
    @NamedQuery(name = "SensetivePagelinkMaster.findById", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.id = :id"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByName", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.name = :name"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByUrl", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.url = :url"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByCreatedOn", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByCreatedBy", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByUpdatedOn", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.updatedOn = :updatedOn"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByUpdatedBy", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.updatedBy = :updatedBy"),
    @NamedQuery(name = "SensetivePagelinkMaster.findByActiveC", query = "SELECT s FROM SensetivePagelinkMaster s WHERE s.activeC = :activeC")})
public class SensetivePagelinkMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;
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
    private Character activeC;

    public SensetivePagelinkMaster() {
    }

    public SensetivePagelinkMaster(Integer id) {
        this.id = id;
    }

    public SensetivePagelinkMaster(Integer id, Character activeC) {
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensetivePagelinkMaster)) {
            return false;
        }
        SensetivePagelinkMaster other = (SensetivePagelinkMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.SensetivePagelinkMaster[ id=" + id + " ]";
    }
    
}
