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
@Table(name = "admin_user_account")
@NamedQueries({
    @NamedQuery(name = "AdminUserAccount.findAll", query = "SELECT a FROM AdminUserAccount a"),
    @NamedQuery(name = "AdminUserAccount.findById", query = "SELECT a FROM AdminUserAccount a WHERE a.id = :id"),
    @NamedQuery(name = "AdminUserAccount.findByName", query = "SELECT a FROM AdminUserAccount a WHERE a.name = :name"),
    @NamedQuery(name = "AdminUserAccount.findByMailId", query = "SELECT a FROM AdminUserAccount a WHERE a.mailId = :mailId"),
    @NamedQuery(name = "AdminUserAccount.findByPwd", query = "SELECT a FROM AdminUserAccount a WHERE a.pwd = :pwd"),
    @NamedQuery(name = "AdminUserAccount.findByCreatedOn", query = "SELECT a FROM AdminUserAccount a WHERE a.createdOn = :createdOn"),
    @NamedQuery(name = "AdminUserAccount.findByCreatedBy", query = "SELECT a FROM AdminUserAccount a WHERE a.createdBy = :createdBy"),
    @NamedQuery(name = "AdminUserAccount.findByUpdatedOn", query = "SELECT a FROM AdminUserAccount a WHERE a.updatedOn = :updatedOn"),
    @NamedQuery(name = "AdminUserAccount.findByUpdateBy", query = "SELECT a FROM AdminUserAccount a WHERE a.updateBy = :updateBy"),
    @NamedQuery(name = "AdminUserAccount.findByActiveC", query = "SELECT a FROM AdminUserAccount a WHERE a.activeC = :activeC")})
public class AdminUserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mail_id")
    private String mailId;
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIME)
    private Date createdOn;
    @Column(name = "created_by")
    private Integer createdBy;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIME)
    private Date updatedOn;
    @Column(name = "update_by")
    private Integer updateBy;
    @Basic(optional = false)
    @Column(name = "active_c")
    private Character activeC;

    public AdminUserAccount() {
    }

    public AdminUserAccount(Integer id) {
        this.id = id;
    }

    public AdminUserAccount(Integer id, Character activeC) {
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

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
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
        if (!(object instanceof AdminUserAccount)) {
            return false;
        }
        AdminUserAccount other = (AdminUserAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.AdminUserAccount[ id=" + id + " ]";
    }
    
}
