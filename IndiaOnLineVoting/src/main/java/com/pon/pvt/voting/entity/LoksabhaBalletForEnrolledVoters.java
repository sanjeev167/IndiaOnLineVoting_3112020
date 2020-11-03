/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.voting.entity;

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

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pvt.master.entity.PollingBoothMaster;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.pon.pvt.voting.dto.LoksabhaBalletForEnrolledVotersDTO;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "voters_enrolled")
@NamedQueries({
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findAll", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findById", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.id = :id"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByName", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.name = :name"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findBySex", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.sex = :sex"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByDob", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.dob = :dob"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByFatherName", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.fatherName = :fatherName"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByVoterId", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.voterId = :voterId"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByCreatedOn", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.createdOn = :createdOn"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByCreatedBy", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.createdBy = :createdBy"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByUpdatedOn", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.updatedOn = :updatedOn"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByUpdatedBy", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.updatedBy = :updatedBy"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByActiveC", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.activeC = :activeC"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByVotingMode", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.votingMode = :votingMode"),
    @NamedQuery(name = "LoksabhaBalletForEnrolledVoters.findByAddress", query = "SELECT v FROM LoksabhaBalletForEnrolledVoters v WHERE v.address = :address")})

@SqlResultSetMapping(
        name = "LoksabhaBalletForEnrolledVotersDTOMapping",
        classes = @ConstructorResult(
                targetClass = LoksabhaBalletForEnrolledVotersDTO.class,
                columns = { @ColumnResult(name = "loksabhaCandidateId", type = Integer.class),                		   
                		   
                		    @ColumnResult(name = "loksabhaName"), 
                		    @ColumnResult(name = "loksabhaCandidateName"), 
                		    @ColumnResult(name = "partyName"),
                		    @ColumnResult(name = "partyNameId",type = Integer.class),
                		    
                		    @ColumnResult(name = "symbolName"),                		    
                            @ColumnResult(name = "totalrecords", type = Integer.class)
                            }))
 

public class LoksabhaBalletForEnrolledVoters implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sex")
    private String sex;
    @Column(name = "dob")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Column(name = "father_name")
    private String fatherName;
    
    @Column(name = "voter_id")
    private String voterId;
    @Column(name = "voter_no")
    private Integer voterNo;
    
    
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
    @Column(name = "voting_mode")
    private String votingMode="0";
    @Column(name = "address")
    private String address;
    
    @OneToMany(mappedBy = "voterId")
    private List<VoteLockinig> voteLockinigList;
    @JoinColumn(name = "booth_id", referencedColumnName = "id")
    
    @ManyToOne
    private PollingBoothMaster boothId;
    @OneToMany(mappedBy = "voterId")
    private List<OnlineVoterAccount> onlineVoterAccountList;
    @OneToMany(mappedBy = "voterId")
    private List<VoteRepository> voteRepositoryList;

    public LoksabhaBalletForEnrolledVoters() {
    }

    public LoksabhaBalletForEnrolledVoters(Integer id) {
        this.id = id;
    }

    public LoksabhaBalletForEnrolledVoters(Integer id, Character activeC) {
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    
    
    
    
    public Integer getVoterNo() {
		return voterNo;
	}

	public void setVoterNo(Integer voterNo) {
		this.voterNo = voterNo;
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

    public String getVotingMode() {
        return votingMode;
    }

    public void setVotingMode(String votingMode) {
        this.votingMode = votingMode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<VoteLockinig> getVoteLockinigList() {
        return voteLockinigList;
    }

    public void setVoteLockinigList(List<VoteLockinig> voteLockinigList) {
        this.voteLockinigList = voteLockinigList;
    }

    public PollingBoothMaster getBoothId() {
        return boothId;
    }

    public void setBoothId(PollingBoothMaster boothId) {
        this.boothId = boothId;
    }

    public List<OnlineVoterAccount> getOnlineVoterAccountList() {
        return onlineVoterAccountList;
    }

    public void setOnlineVoterAccountList(List<OnlineVoterAccount> onlineVoterAccountList) {
        this.onlineVoterAccountList = onlineVoterAccountList;
    }

    public List<VoteRepository> getVoteRepositoryList() {
        return voteRepositoryList;
    }

    public void setVoteRepositoryList(List<VoteRepository> voteRepositoryList) {
        this.voteRepositoryList = voteRepositoryList;
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
        if (!(object instanceof LoksabhaBalletForEnrolledVoters)) {
            return false;
        }
        LoksabhaBalletForEnrolledVoters other = (LoksabhaBalletForEnrolledVoters) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.LoksabhaBalletForEnrolledVoters[ id=" + id + " ]";
    }
    
}
