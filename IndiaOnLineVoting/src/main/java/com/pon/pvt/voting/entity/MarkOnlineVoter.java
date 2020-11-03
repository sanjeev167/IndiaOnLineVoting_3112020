/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.voting.entity;
import java.io.Serializable;
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
import javax.persistence.Table;

import com.pon.config.db.AttributeEncryptor;
import com.pon.pvt.master.entity.AssemblyMaster;
import com.pon.pvt.master.entity.LoksabhaMaster;
import com.pon.pvt.master.entity.VotersEnrolled;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "mark_online_voter")
@NamedQueries({
    @NamedQuery(name = "MarkOnlineVoter.findAll", query = "SELECT m FROM MarkOnlineVoter m"),
    @NamedQuery(name = "MarkOnlineVoter.findById", query = "SELECT m FROM MarkOnlineVoter m WHERE m.id = :id"),
    @NamedQuery(name = "MarkOnlineVoter.findByElectionType", query = "SELECT m FROM MarkOnlineVoter m WHERE m.electionType = :electionType"),
    @NamedQuery(name = "MarkOnlineVoter.findByElectionYear", query = "SELECT m FROM MarkOnlineVoter m WHERE m.electionYear = :electionYear"),
    @NamedQuery(name = "MarkOnlineVoter.findByHasVoted", query = "SELECT m FROM MarkOnlineVoter m WHERE m.hasVoted = :hasVoted"),
    @NamedQuery(name = "MarkOnlineVoter.findByAssemblyId", query = "SELECT m FROM MarkOnlineVoter m WHERE m.assemblyId = :assemblyId"),
    @NamedQuery(name = "MarkOnlineVoter.findByLoksabhaId", query = "SELECT m FROM MarkOnlineVoter m WHERE m.loksabhaId = :loksabhaId"),
    @NamedQuery(name = "MarkOnlineVoter.findByVoterId", query = "SELECT m FROM MarkOnlineVoter m WHERE m.voterId = :voterId")})
public class MarkOnlineVoter implements Serializable {

	private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "election_type")
    private String electionType;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "election_year")
    private String electionYear;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "has_voted")
    private String hasVoted;
    //@Convert(converter = AttributeEncryptor.class)
    @Column(name = "assembly_id")
    private String assemblyId;
    //@Convert(converter = AttributeEncryptor.class)
    @Column(name = "loksabha_id")
    private String loksabhaId;
    //@Convert(converter = AttributeEncryptor.class)
    @Column(name = "voter_id")
    private String voterId;

    public MarkOnlineVoter() {
    }

    public MarkOnlineVoter(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getElectionType() {
        return electionType;
    }

    public void setElectionType(String electionType) {
        this.electionType = electionType;
    }

    public String getElectionYear() {
        return electionYear;
    }

    public void setElectionYear(String electionYear) {
        this.electionYear = electionYear;
    }

    public String getHasVoted() {
        return hasVoted;
    }

    public void setHasVoted(String hasVoted) {
        this.hasVoted = hasVoted;
    }

    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getLoksabhaId() {
        return loksabhaId;
    }

    public void setLoksabhaId(String loksabhaId) {
        this.loksabhaId = loksabhaId;
    }

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
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
        if (!(object instanceof MarkOnlineVoter)) {
            return false;
        }
        MarkOnlineVoter other = (MarkOnlineVoter) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.MarkOnlineVoter[ id=" + id + " ]";
    }
    
}
