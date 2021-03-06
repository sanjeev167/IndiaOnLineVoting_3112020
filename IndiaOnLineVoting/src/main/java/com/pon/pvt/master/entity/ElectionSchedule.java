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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "election_schedule")
@NamedQueries({
    @NamedQuery(name = "ElectionSchedule.findAll", query = "SELECT e FROM ElectionSchedule e"),
    @NamedQuery(name = "ElectionSchedule.findById", query = "SELECT e FROM ElectionSchedule e WHERE e.id = :id"),
    @NamedQuery(name = "ElectionSchedule.findByStateId", query = "SELECT e FROM ElectionSchedule e WHERE e.stateId = :stateId"),
    @NamedQuery(name = "ElectionSchedule.findByElectionYear", query = "SELECT e FROM ElectionSchedule e WHERE e.electionYear = :electionYear"),
    @NamedQuery(name = "ElectionSchedule.findByElectionFor", query = "SELECT e FROM ElectionSchedule e WHERE e.electionFor = :electionFor"),
    @NamedQuery(name = "ElectionSchedule.findByElectionDate", query = "SELECT e FROM ElectionSchedule e WHERE e.electionDate = :electionDate"),
    @NamedQuery(name = "ElectionSchedule.findByScheduleNo", query = "SELECT e FROM ElectionSchedule e WHERE e.scheduleNo = :scheduleNo"),
    @NamedQuery(name = "ElectionSchedule.findByElectionTypeId", query = "SELECT e FROM ElectionSchedule e WHERE e.electionTypeId = :electionTypeId")})
public class ElectionSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "state_id")
    private Integer stateId;
    @Column(name = "election_year")
    private Integer electionYear;
    @Column(name = "election_for")
    private String electionFor;
    @Column(name = "election_date")
    @Temporal(TemporalType.DATE)
    private Date electionDate;
    @Column(name = "schedule_no")
    private Integer scheduleNo;
    @Column(name = "election_type_id")
    private Integer electionTypeId;
    @JoinColumn(name = "assembly_id", referencedColumnName = "id")
    @ManyToOne
    private AssemblyMaster assemblyId;
    @JoinColumn(name = "election_name_id", referencedColumnName = "id")
    @ManyToOne
    private ElectionNameMaster electionNameId;
    @JoinColumn(name = "loksabha_id", referencedColumnName = "id")
    @ManyToOne
    private LoksabhaMaster loksabhaId;
    @JoinColumn(name = "polling_booth_id", referencedColumnName = "id")
    @ManyToOne
    private PollingBoothMaster pollingBoothId;

    public ElectionSchedule() {
    }

    public ElectionSchedule(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    public Integer getElectionYear() {
        return electionYear;
    }

    public void setElectionYear(Integer electionYear) {
        this.electionYear = electionYear;
    }

    public String getElectionFor() {
        return electionFor;
    }

    public void setElectionFor(String electionFor) {
        this.electionFor = electionFor;
    }

    public Date getElectionDate() {
        return electionDate;
    }

    public void setElectionDate(Date electionDate) {
        this.electionDate = electionDate;
    }

    public Integer getScheduleNo() {
        return scheduleNo;
    }

    public void setScheduleNo(Integer scheduleNo) {
        this.scheduleNo = scheduleNo;
    }

    public Integer getElectionTypeId() {
        return electionTypeId;
    }

    public void setElectionTypeId(Integer electionTypeId) {
        this.electionTypeId = electionTypeId;
    }

    public AssemblyMaster getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(AssemblyMaster assemblyId) {
        this.assemblyId = assemblyId;
    }

    public ElectionNameMaster getElectionNameId() {
        return electionNameId;
    }

    public void setElectionNameId(ElectionNameMaster electionNameId) {
        this.electionNameId = electionNameId;
    }

    public LoksabhaMaster getLoksabhaId() {
        return loksabhaId;
    }

    public void setLoksabhaId(LoksabhaMaster loksabhaId) {
        this.loksabhaId = loksabhaId;
    }

    public PollingBoothMaster getPollingBoothId() {
        return pollingBoothId;
    }

    public void setPollingBoothId(PollingBoothMaster pollingBoothId) {
        this.pollingBoothId = pollingBoothId;
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
        if (!(object instanceof ElectionSchedule)) {
            return false;
        }
        ElectionSchedule other = (ElectionSchedule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.ElectionSchedule[ id=" + id + " ]";
    }
    
}
