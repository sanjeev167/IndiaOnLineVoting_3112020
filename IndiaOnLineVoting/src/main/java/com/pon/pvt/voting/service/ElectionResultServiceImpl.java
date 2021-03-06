/**
 * 
 */
package com.pon.pvt.voting.service;

import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pon.pvt.voting.dto.LoksabhaBalletForEnrolledVotersDTO;
import com.pon.pvt.voting.entity.LoksabhaBalletForEnrolledVoters;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableRequest;
import com.support.grid_pagination.DataTableResults;
import com.support.grid_pagination.PaginationCriteria;
import com.support.util.AppConstants;
import com.support.util.AppUtil;

/**
 * @author Sanjeev
 *
 */
@Service
public class ElectionResultServiceImpl implements ElectionResultService{
	private static final Logger log = LoggerFactory.getLogger(LoksabhaBalletServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;
	
	@Override
	public DataTableResults<VoteRepository> getAssemblyResult(String yearId, String electionTypeId, String stateId, String loksabhaId,
			String assemblyId,  HttpServletRequest request) throws CustomRuntimeException {
		log.info("     AssemblyBalletServiceImpl :==> getAssemblyResult :==> Started");
		// TODO Auto-generated method stub
		DataTableResults<VoteRepository> dataTableResult;
		try {
			DataTableRequest<VoteRepository> dataTableInRQ = new DataTableRequest<VoteRepository>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		
		 
		
		String whereClauseTotalCount= "  vr.election_type='"+electionTypeId+"' and"
											+ " vr.election_year='"+yearId+"'";
							if(!loksabhaId.equals(""))
								whereClauseTotalCount=whereClauseTotalCount+" and vr.loksabha_id='"+loksabhaId+"'";
							if(!assemblyId.equals(""))
								whereClauseTotalCount=whereClauseTotalCount+" and vr.assembly_id='"+assemblyId+"'";
		
		
		String whereClause= " and vr.election_type='"+electionTypeId+"' and"
							+ " vr.election_year='"+yearId+"'";
		if(!loksabhaId.equals(""))
			whereClause=whereClause+" and vr.loksabha_id='"+loksabhaId+"'";
		if(!assemblyId.equals(""))
			whereClause=whereClause+" and vr.assembly_id='"+assemblyId+"'";
		
		String whereClauseMain= " vro.election_type='"+electionTypeId+"' and"
				+ " vro.election_year='"+yearId+"'";
		if(!loksabhaId.equals(""))
			whereClauseMain=whereClauseMain+" and vro.loksabha_id='"+loksabhaId+"'";
		if(!assemblyId.equals(""))
			whereClauseMain=whereClauseMain+" and vro.assembly_id='"+assemblyId+"'";
		
		
		String baseQuery= "Select distinct(vro.candidate_id) as candidateId, "
				+ "       vro.voting_party_id as votingPartyId,    "
				+ "       vro.symbol, "
				
				+ "      (select count(vr.voting_party_id) as total from vote_repository vr  where  "
				
				+ "       vr.candidate_id=vro.candidate_id and"
				+ "       vr.voting_mode='1' "
				+whereClause
				+ "  Group By vr.voting_party_id) as onlineVotes,"
				
				+ "       (select count(vr.voting_party_id) as total from vote_repository vr  where  "
				
				+ "        vr.candidate_id=vro.candidate_id and  "
				+ "       vr.voting_mode='0' "
				+whereClause
				+ "  Group By vr.voting_party_id) as offlineVotes,"
				
				+ "       count(vro.voting_mode) as totalVotes, RANK() OVER(ORDER BY COUNT(*) DESC) AS rank,"
				
				+ "       (select count(distinct(vr.candidate_id)) from vote_repository vr  where  "
				+whereClauseTotalCount
				+ "       )as totalrecords"
				
				+ "       from vote_repository vro  where  "
				
				+whereClauseMain
				
				+ "       Group By vro.voting_party_id,vro.candidate_id,vro.symbol "
				+ "       order by totalVotes desc";
				
		System.out.println("baseQuery = "+baseQuery);
		String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
		Query query = entityManager.createNativeQuery(paginatedQuery, "VoteRepositoryDTOMapping");

		@SuppressWarnings("unchecked")
		List<VoteRepository> VoteRepositoryList = query.getResultList();		

		dataTableResult = new DataTableResults<VoteRepository>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		dataTableResult.setListOfDataObjects(VoteRepositoryList);
		if (!AppUtil.isObjectEmpty(VoteRepositoryList)) {
			dataTableResult.setRecordsTotal(VoteRepositoryList.get(0).getTotalrecords().toString());
			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(VoteRepositoryList.get(0).getTotalrecords().toString());
			} else {
				dataTableResult.setRecordsFiltered(Integer.toString(VoteRepositoryList.size()));
			}
		}
	}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
	log.info("     AssemblyBalletServiceImpl :==> getAssemblyResult :==> Ended");
	return dataTableResult;
	}

	@Override
	public DataTableResults<LoksabhaVoteRepository> getLoksabhaResult(String yearId, String electionTypeId, String stateId,
			String loksabhaId, String assemblyId,  HttpServletRequest request) throws CustomRuntimeException {
		log.info("     AssemblyBalletServiceImpl :==> getLoksabhaResult :==> Started");
		// TODO Auto-generated method stub
		DataTableResults<LoksabhaVoteRepository> dataTableResult;
		try {
			DataTableRequest<LoksabhaVoteRepository> dataTableInRQ = new DataTableRequest<LoksabhaVoteRepository>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
		
		 
		
		String whereClauseTotalCount= "  lr.election_type='"+electionTypeId+"' and"
											+ " lr.election_year='"+yearId+"'";
							if(!loksabhaId.equals(""))
								whereClauseTotalCount=whereClauseTotalCount+" and lr.loksabha_id='"+loksabhaId+"'";
							
		
		
		String whereClause= " and lr.election_type='"+electionTypeId+"' and"
							+ " lr.election_year='"+yearId+"'";
		if(!loksabhaId.equals(""))
			whereClause=whereClause+" and lr.loksabha_id='"+loksabhaId+"'";
		
		
		String whereClauseMain= " lro.election_type='"+electionTypeId+"' and"
				+ " lro.election_year='"+yearId+"'";
		if(!loksabhaId.equals(""))
			whereClauseMain=whereClauseMain+" and lro.loksabha_id='"+loksabhaId+"'";
		
		
		
		String baseQuery= "Select distinct(lro.candidate_id) as candidateId, "
				+ "       lro.voting_party_id as votingPartyId,    "
				+ "       lro.symbol, "
				
				+ "      (select count(lr.voting_party_id) as total from loksabha_vote_repository lr  where  "
				
				+ "       lr.candidate_id=lro.candidate_id and"
				+ "       lr.voting_mode='1' "
				+whereClause
				+ "  Group By lr.voting_party_id) as onlineVotes,"
				
				+ "       (select count(lr.voting_party_id) as total from loksabha_vote_repository lr  where  "
				
				+ "        lr.candidate_id=lro.candidate_id and  "
				+ "       lr.voting_mode='0' "
				+whereClause
				+ "  Group By lr.voting_party_id) as offlineVotes,"
				
				+ "       count(lro.voting_mode) as totalVotes, RANK() OVER(ORDER BY COUNT(*) DESC) AS rank,"
				
				+ "       (select count(distinct(lr.candidate_id)) from loksabha_vote_repository lr  where  "
				+whereClauseTotalCount
				+ "       )as totalrecords"
				
				+ "       from loksabha_vote_repository lro  where  "
				
				+whereClauseMain
				
				+ "       Group By lro.voting_party_id,lro.candidate_id,lro.symbol "
				+ "       order by totalVotes desc";
				
		//System.out.println("baseQuery = "+baseQuery);
		String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
		Query query = entityManager.createNativeQuery(paginatedQuery, "LoksabhaVoteRepositoryDTOMapping");

		@SuppressWarnings("unchecked")
		List<LoksabhaVoteRepository> LoksabhaVoteRepositoryList = query.getResultList();		

		dataTableResult = new DataTableResults<LoksabhaVoteRepository>();
		dataTableResult.setDraw(dataTableInRQ.getDraw());
		dataTableResult.setListOfDataObjects(LoksabhaVoteRepositoryList);
		if (!AppUtil.isObjectEmpty(LoksabhaVoteRepositoryList)) {
			dataTableResult.setRecordsTotal(LoksabhaVoteRepositoryList.get(0).getTotalrecords().toString());
			if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
				dataTableResult.setRecordsFiltered(LoksabhaVoteRepositoryList.get(0).getTotalrecords().toString());
			} else {
				dataTableResult.setRecordsFiltered(Integer.toString(LoksabhaVoteRepositoryList.size()));
			}
		}
	}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
	log.info("     AssemblyBalletServiceImpl :==> getLoksabhaResult :==> Ended");
	return dataTableResult;
	}

}
