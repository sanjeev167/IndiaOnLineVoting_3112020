/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pub.hm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ColumnTransformer;

import com.pon.config.db.AttributeEncryptor;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.pon.pvt.master.entity.VotersEnrolled;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "online_voter_account")
@NamedQueries({
    @NamedQuery(name = "OnlineVoterAccount.findAll", query = "SELECT o FROM OnlineVoterAccount o"),
    @NamedQuery(name = "OnlineVoterAccount.findById", query = "SELECT o FROM OnlineVoterAccount o WHERE o.id = :id"),
    @NamedQuery(name = "OnlineVoterAccount.findByName", query = "SELECT o FROM OnlineVoterAccount o WHERE o.name = :name"),
    @NamedQuery(name = "OnlineVoterAccount.findByMailId", query = "SELECT o FROM OnlineVoterAccount o WHERE o.mailId = :mailId"),
    @NamedQuery(name = "OnlineVoterAccount.findByMobile", query = "SELECT o FROM OnlineVoterAccount o WHERE o.mobile = :mobile"),
    @NamedQuery(name = "OnlineVoterAccount.findByPwd", query = "SELECT o FROM OnlineVoterAccount o WHERE o.pwd = :pwd"),
    @NamedQuery(name = "OnlineVoterAccount.findBySecret", query = "SELECT o FROM OnlineVoterAccount o WHERE o.secret = :secret"),
    @NamedQuery(name = "OnlineVoterAccount.findByAadharId", query = "SELECT o FROM OnlineVoterAccount o WHERE o.aadharId = :aadharId"),
    @NamedQuery(name = "OnlineVoterAccount.findByCreatedOn", query = "SELECT o FROM OnlineVoterAccount o WHERE o.createdOn = :createdOn"),
    @NamedQuery(name = "OnlineVoterAccount.findByUpdatedOn", query = "SELECT o FROM OnlineVoterAccount o WHERE o.updatedOn = :updatedOn"),
    @NamedQuery(name = "OnlineVoterAccount.findByIpAdd", query = "SELECT o FROM OnlineVoterAccount o WHERE o.ipAdd = :ipAdd"),
    @NamedQuery(name = "OnlineVoterAccount.findByActiveC", query = "SELECT o FROM OnlineVoterAccount o WHERE o.activeC = :activeC")})
public class OnlineVoterAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "name")
    private String name;
    
    
   
    @Column(name = "mail_id")
    private String mailId;
    
    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "mobile")
    private String mobile;
    
    //@Convert(converter = AttributeEncryptor.class)//It has already been encrypted through security
    @Column(name = "pwd")
    private String pwd;
    
   /* @ColumnTransformer(
            read = "PGP_SYM_DECRYPT(secret, 'secret-key-12345')",
            write = "PGP_SYM_ENCRYPT (?, 'secret-key-12345')"
    )*/
    
   @Convert(converter = AttributeEncryptor.class)
    @Column(name = "secret")
    private String secret;
    
    
    @Column(name = "aadhar_id")
    private String aadharId;
    @Column(name = "created_on")
    @Temporal(TemporalType.TIME)
    private Date createdOn;
    @Column(name = "updated_on")
    @Temporal(TemporalType.TIME)
    private Date updatedOn;
    @Column(name = "ip_add")
    private String ipAdd;
    @Basic(optional = false)
    @Column(name = "active_c")
    private Character activeC='Y';
    @OneToMany(mappedBy = "onlineVoterAccountId")
    private List<VoteLockinig> voteLockinigList;
    @JoinColumn(name = "voter_id", referencedColumnName = "id")
    @ManyToOne
    private VotersEnrolled voterId;

    public OnlineVoterAccount() {
    }

    public OnlineVoterAccount(Integer id) {
        this.id = id;
    }

    public OnlineVoterAccount(Integer id, Character activeC) {
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAadharId() {
        return aadharId;
    }

    public void setAadharId(String aadharId) {
        this.aadharId = aadharId;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public Character getActiveC() {
        return activeC;
    }

    public void setActiveC(Character activeC) {
        this.activeC = activeC;
    }

    public List<VoteLockinig> getVoteLockinigList() {
        return voteLockinigList;
    }

    public void setVoteLockinigList(List<VoteLockinig> voteLockinigList) {
        this.voteLockinigList = voteLockinigList;
    }

    public VotersEnrolled getVoterId() {
        return voterId;
    }

    public void setVoterId(VotersEnrolled voterId) {
        this.voterId = voterId;
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
        if (!(object instanceof OnlineVoterAccount)) {
            return false;
        }
        OnlineVoterAccount other = (OnlineVoterAccount) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.OnlineVoterAccount[ id=" + id + " ]";
    }
    
}
