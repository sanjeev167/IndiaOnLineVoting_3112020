/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pon.pvt.voting.entity;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Convert;
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
import javax.persistence.Transient;

import org.apache.commons.io.IOUtils;

import com.pon.config.db.AttributeEncryptor;
import com.pon.pvt.master.entity.AssemblyMaster;
import com.pon.pvt.master.entity.LoksabhaCandidateMaster;
import com.pon.pvt.master.entity.LoksabhaMaster;
import com.pon.pvt.master.entity.PartyMaster;
import com.pon.pvt.master.entity.PollingBoothMaster;
import com.pon.pvt.master.entity.VotersEnrolled;
import com.support.util.FileResourcesUtils;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "loksabha_vote_repository")
@NamedQueries({
    @NamedQuery(name = "LoksabhaVoteRepository.findAll", query = "SELECT l FROM LoksabhaVoteRepository l"),
    @NamedQuery(name = "LoksabhaVoteRepository.findById", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.id = :id"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByElectionType", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.electionType = :electionType"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByElectionYear", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.electionYear = :electionYear"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByVoterId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.voterId = :voterId"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByVotingPartyId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.votingPartyId = :votingPartyId"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByAssemblyId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.assemblyId = :assemblyId"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByLoksabhaId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.loksabhaId = :loksabhaId"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByVotingMode", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.votingMode = :votingMode"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByVotingDate", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.votingDate = :votingDate"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByCandidateId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.candidateId = :candidateId"),
    @NamedQuery(name = "LoksabhaVoteRepository.findByPollingBoothId", query = "SELECT l FROM LoksabhaVoteRepository l WHERE l.pollingBoothId = :pollingBoothId")})

@SqlResultSetMapping(
        name = "LoksabhaVoteRepositoryDTOMapping",
        classes = @ConstructorResult(
                targetClass = LoksabhaVoteRepository.class,
                columns = {                		   
                		    @ColumnResult(name = "candidateId"), 
                		    @ColumnResult(name = "votingPartyId"), 
                		    @ColumnResult(name = "symbol"),  
                		    @ColumnResult(name = "onlineVotes", type = Integer.class),
                		    @ColumnResult(name = "offlineVotes", type = Integer.class), 
                		     
                		    @ColumnResult(name = "totalVotes", type = Integer.class), 
                		    @ColumnResult(name = "rank", type = Integer.class), 
                            @ColumnResult(name = "totalrecords", type = Integer.class)}))

public class LoksabhaVoteRepository implements Serializable {

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
    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "voter_id")
    private String voterId;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "voting_party_id")
    private String votingPartyId;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "assembly_id")
    private String assemblyId;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "loksabha_id")
    private String loksabhaId;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "voting_mode")
    private String votingMode;
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "voting_date")
    private String votingDate=new Date().toString();
   // @Convert(converter = AttributeEncryptor.class)
    @Column(name = "candidate_id")
    private String candidateId;
    @Convert(converter = AttributeEncryptor.class)
    @Column(name = "polling_booth_id")
    private String pollingBoothId;
    @Column(name = "symbol")
    private String symbol;
    
    
    
    public LoksabhaVoteRepository(String candidateId, String votingPartyId, String symbol, Integer onlineVotes,
			Integer offlineVotes,

			Integer totalVotes, Integer rank, Integer totalrecords) {
		super();

		this.votingPartyId = votingPartyId;
		this.candidateId = candidateId;

		// Prepare base64Img with base64ImgName
		InputStream inputStream = new FileResourcesUtils().getFileFromResourceAsStream("image/" + symbol);
		String fileExt = symbol.substring(symbol.length() - 3);
		byte[] bytes = null;
		try {
			bytes = IOUtils.toByteArray(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Encode
		String resultBase64Encoded = java.util.Base64.getEncoder().encodeToString(bytes);		
		this.symbol = "data:image/"+fileExt+";base64,"+resultBase64Encoded;
		
		
		if(offlineVotes!=null)
			   this.offlineVotes = offlineVotes;
			else
				this.offlineVotes = 0;
		
		if(onlineVotes!=null)
		   this.onlineVotes = onlineVotes;
		else
			this.onlineVotes = 0;
		
		
		this.totalVotes = totalVotes;
		this.rank = rank;
		this.totalrecords = totalrecords;
	}

	private    @Transient Integer offlineVotes=0;
    private    @Transient Integer onlineVotes=0;
    private    @Transient Integer totalVotes=0;
    private    @Transient Integer rank;
    private    @Transient Integer totalrecords;
    
    
    
    public LoksabhaVoteRepository() {
    }

    public LoksabhaVoteRepository(Integer id) {
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

    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public String getVotingPartyId() {
        return votingPartyId;
    }

    public void setVotingPartyId(String votingPartyId) {
        this.votingPartyId = votingPartyId;
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

    public String getVotingMode() {
        return votingMode;
    }

    public void setVotingMode(String votingMode) {
        this.votingMode = votingMode;
    }

    public String getVotingDate() {
        return votingDate;
    }

    public void setVotingDate(String votingDate) {
        this.votingDate = votingDate;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getPollingBoothId() {
        return pollingBoothId;
    }

    public void setPollingBoothId(String pollingBoothId) {
        this.pollingBoothId = pollingBoothId;
    }
    
    
    

    public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	
	

	public Integer getOfflineVotes() {
		return offlineVotes;
	}

	public void setOfflineVotes(Integer offlineVotes) {
		this.offlineVotes = offlineVotes;
	}

	public Integer getOnlineVotes() {
		return onlineVotes;
	}

	public void setOnlineVotes(Integer onlineVotes) {
		this.onlineVotes = onlineVotes;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}

	public void setTotalVotes(Integer totalVotes) {
		this.totalVotes = totalVotes;
	}

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public Integer getTotalrecords() {
		return totalrecords;
	}

	public void setTotalrecords(Integer totalrecords) {
		this.totalrecords = totalrecords;
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
        if (!(object instanceof LoksabhaVoteRepository)) {
            return false;
        }
        LoksabhaVoteRepository other = (LoksabhaVoteRepository) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.LoksabhaVoteRepository[ id=" + id + " ]";
    }
    
}
