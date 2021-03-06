/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.voter.entity;

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

import com.pon.pub.hm.entity.OnlineVoterAccount;

import com.pon.pvt.master.entity.VotersEnrolled;
import com.pon.pvt.voter.dto.VoteLockinigDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "vote_lockinig")
@NamedQueries({
    @NamedQuery(name = "VoteLockinig.findAll", query = "SELECT v FROM VoteLockinig v"),
    @NamedQuery(name = "VoteLockinig.findById", query = "SELECT v FROM VoteLockinig v WHERE v.id = :id"),
    @NamedQuery(name = "VoteLockinig.findByLockStatus", query = "SELECT v FROM VoteLockinig v WHERE v.lockStatus = :lockStatus"),
    @NamedQuery(name = "VoteLockinig.findByLockedOn", query = "SELECT v FROM VoteLockinig v WHERE v.lockedOn = :lockedOn"),
    @NamedQuery(name = "VoteLockinig.findByUnlockedOn", query = "SELECT v FROM VoteLockinig v WHERE v.unlockedOn = :unlockedOn"),
    @NamedQuery(name = "VoteLockinig.findByReqIpAdd", query = "SELECT v FROM VoteLockinig v WHERE v.reqIpAdd = :reqIpAdd"),
    @NamedQuery(name = "VoteLockinig.findByLockBy", query = "SELECT v FROM VoteLockinig v WHERE v.lockBy = :lockBy"),
    @NamedQuery(name = "VoteLockinig.findByUnlockBy", query = "SELECT v FROM VoteLockinig v WHERE v.unlockBy = :unlockBy"),
    @NamedQuery(name = "VoteLockinig.findByActiveC", query = "SELECT v FROM VoteLockinig v WHERE v.activeC = :activeC"),
    @NamedQuery(name = "VoteLockinig.findByElectionYear", query = "SELECT v FROM VoteLockinig v WHERE v.electionYear = :electionYear")})


@SqlResultSetMapping(
        name = "VoteLockinigDTOMapping",
        classes = @ConstructorResult(
                targetClass = VoteLockinigDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "name"), 
                            @ColumnResult(name = "lockStatusId", type = Integer.class), 
                            @ColumnResult(name = "voterId"),                                                  
                            @ColumnResult(name = "reqIpAdd"),                             
                            @ColumnResult(name = "onlineVoterAccountId", type = Integer.class),                     
                            @ColumnResult(name = "dateOfLocking" ),                            
                            @ColumnResult(name = "totalrecords", type = Integer.class)
                            }))



public class VoteLockinig implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "lock_status")
    private Integer lockStatus;
    @Column(name = "locked_on")
    @Temporal(TemporalType.TIME)
    private Date lockedOn;
    @Column(name = "unlocked_on")
    @Temporal(TemporalType.TIME)
    private Date unlockedOn;
    @Column(name = "req_ip_add")
    private String reqIpAdd;
    @Column(name = "lock_by")
    private Integer lockBy;
    @Column(name = "unlock_by")
    private Integer unlockBy;
    @Basic(optional = false)
    @Column(name = "active_c")
    private Character activeC;
    @Column(name = "election_year")
    private String electionYear;
    @JoinColumn(name = "online_voter_account_id", referencedColumnName = "id")
    @ManyToOne
    private OnlineVoterAccount onlineVoterAccountId;
    @JoinColumn(name = "voter_id", referencedColumnName = "id")
    @ManyToOne
    private VotersEnrolled voterId;

    public VoteLockinig() {
    }

    public VoteLockinig(Integer id) {
        this.id = id;
    }

    public VoteLockinig(Integer id, Character activeC) {
        this.id = id;
        this.activeC = activeC;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Integer lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Date getLockedOn() {
        return lockedOn;
    }

    public void setLockedOn(Date lockedOn) {
        this.lockedOn = lockedOn;
    }

    public Date getUnlockedOn() {
        return unlockedOn;
    }

    public void setUnlockedOn(Date unlockedOn) {
        this.unlockedOn = unlockedOn;
    }

    public String getReqIpAdd() {
        return reqIpAdd;
    }

    public void setReqIpAdd(String reqIpAdd) {
        this.reqIpAdd = reqIpAdd;
    }

    public Integer getLockBy() {
        return lockBy;
    }

    public void setLockBy(Integer lockBy) {
        this.lockBy = lockBy;
    }

    public Integer getUnlockBy() {
        return unlockBy;
    }

    public void setUnlockBy(Integer unlockBy) {
        this.unlockBy = unlockBy;
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

    public OnlineVoterAccount getOnlineVoterAccountId() {
        return onlineVoterAccountId;
    }

    public void setOnlineVoterAccountId(OnlineVoterAccount onlineVoterAccountId) {
        this.onlineVoterAccountId = onlineVoterAccountId;
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
        if (!(object instanceof VoteLockinig)) {
            return false;
        }
        VoteLockinig other = (VoteLockinig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.VoteLockinig[ id=" + id + " ]";
    }
    
}
