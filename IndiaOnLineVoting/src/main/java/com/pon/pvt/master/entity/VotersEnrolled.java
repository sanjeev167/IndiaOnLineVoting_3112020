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

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pvt.master.dto.VotersEnrolledDTO;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "voters_enrolled")
@NamedQueries({
    @NamedQuery(name = "VotersEnrolled.findAll", query = "SELECT v FROM VotersEnrolled v"),
    @NamedQuery(name = "VotersEnrolled.findById", query = "SELECT v FROM VotersEnrolled v WHERE v.id = :id"),
    @NamedQuery(name = "VotersEnrolled.findByName", query = "SELECT v FROM VotersEnrolled v WHERE v.name = :name"),
    @NamedQuery(name = "VotersEnrolled.findBySex", query = "SELECT v FROM VotersEnrolled v WHERE v.sex = :sex"),
    @NamedQuery(name = "VotersEnrolled.findByDob", query = "SELECT v FROM VotersEnrolled v WHERE v.dob = :dob"),
    @NamedQuery(name = "VotersEnrolled.findByFatherName", query = "SELECT v FROM VotersEnrolled v WHERE v.fatherName = :fatherName"),
    @NamedQuery(name = "VotersEnrolled.findByCreatedOn", query = "SELECT v FROM VotersEnrolled v WHERE v.createdOn = :createdOn"),
    @NamedQuery(name = "VotersEnrolled.findByCreatedBy", query = "SELECT v FROM VotersEnrolled v WHERE v.createdBy = :createdBy"),
    @NamedQuery(name = "VotersEnrolled.findByUpdatedOn", query = "SELECT v FROM VotersEnrolled v WHERE v.updatedOn = :updatedOn"),
    @NamedQuery(name = "VotersEnrolled.findByUpdatedBy", query = "SELECT v FROM VotersEnrolled v WHERE v.updatedBy = :updatedBy"),
    @NamedQuery(name = "VotersEnrolled.findByActiveC", query = "SELECT v FROM VotersEnrolled v WHERE v.activeC = :activeC"),
    @NamedQuery(name = "VotersEnrolled.findByAddress", query = "SELECT v FROM VotersEnrolled v WHERE v.address = :address"),
    @NamedQuery(name = "VotersEnrolled.findByVoterId", query = "SELECT v FROM VotersEnrolled v WHERE v.voterId = :voterId"),
    @NamedQuery(name = "VotersEnrolled.findByVoterNo", query = "SELECT v FROM VotersEnrolled v WHERE v.voterNo = :voterNo"),
    @NamedQuery(name = "VotersEnrolled.findByVotingMode", query = "SELECT v FROM VotersEnrolled v WHERE v.votingMode = :votingMode")})

@SqlResultSetMapping(
        name = "VotersEnrolledDTOMapping",
        classes = @ConstructorResult(
                targetClass = VotersEnrolledDTO.class,
                columns = { @ColumnResult(name = "id", type = Integer.class), 
                            @ColumnResult(name = "stateName"), 
                            @ColumnResult(name = "loksabhaName"), 
                            @ColumnResult(name = "assemblyName"),
                            @ColumnResult(name = "pollingBoothName"),
                            @ColumnResult(name = "pollingBoothNo",type = Integer.class),
                            
                            @ColumnResult(name = "voterName"),
                            @ColumnResult(name = "sex"),
                            @ColumnResult(name = "dob",type = Date.class),
                            @ColumnResult(name = "fatherName"),
                            @ColumnResult(name = "voterId"),
                            @ColumnResult(name = "voting_mode"),
                            @ColumnResult(name = "address"),
                            
                            @ColumnResult(name = "totalrecords", type = Integer.class)
                            }))
 


public class VotersEnrolled implements Serializable {

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
	    @Column(name = "address")
	    private String address;
	    @Column(name = "voter_id")
	    private String voterId;
	    @Column(name = "voter_no")
	    private Integer voterNo;
	    @Column(name = "voting_mode")
	    private String votingMode;
	    @OneToMany(mappedBy = "voterId")
	    private List<VoteLockinig> voteLockinigList;
	    @JoinColumn(name = "booth_id", referencedColumnName = "id")
	    @ManyToOne
	    private PollingBoothMaster boothId;
	    @OneToMany(mappedBy = "voterId")
	    private List<OnlineVoterAccount> onlineVoterAccountList;

	    public VotersEnrolled() {
	    }

	    public VotersEnrolled(Integer id) {
	        this.id = id;
	    }

	    public VotersEnrolled(Integer id, Character activeC) {
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

	    public String getAddress() {
	        return address;
	    }

	    public void setAddress(String address) {
	        this.address = address;
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

	    public String getVotingMode() {
	        return votingMode;
	    }

	    public void setVotingMode(String votingMode) {
	        this.votingMode = votingMode;
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

	    @Override
	    public int hashCode() {
	        int hash = 0;
	        hash += (id != null ? id.hashCode() : 0);
	        return hash;
	    }

	    @Override
	    public boolean equals(Object object) {
	        // TODO: Warning - this method won't work in the case the id fields are not set
	        if (!(object instanceof VotersEnrolled)) {
	            return false;
	        }
	        VotersEnrolled other = (VotersEnrolled) object;
	        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	            return false;
	        }
	        return true;
	    }

	    @Override
	    public String toString() {
	        return "com.example.demo.VotersEnrolled[ id=" + id + " ]";
	    }
}