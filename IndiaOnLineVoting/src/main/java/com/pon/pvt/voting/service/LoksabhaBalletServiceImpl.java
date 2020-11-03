/**
 * 
 */
package com.pon.pvt.voting.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pon.pvt.master.entity.AssemblyCandidateMaster;
import com.pon.pvt.master.entity.AssemblyMaster;
import com.pon.pvt.master.entity.LoksabhaCandidateMaster;
import com.pon.pvt.master.entity.LoksabhaMaster;
import com.pon.pvt.master.entity.PartyMaster;
import com.pon.pvt.master.entity.PollingBoothMaster;
import com.pon.pvt.master.entity.VotersEnrolled;
import com.pon.pvt.master.repo.AssemblyCandidateRepository;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.LoksabhaCandidateRepository;
import com.pon.pvt.master.repo.LoksabhaRepository;
import com.pon.pvt.master.repo.PartyRepository;
import com.pon.pvt.master.repo.PollingBoothRepository;
import com.pon.pvt.master.repo.VotersEnrolledRepository;
import com.pon.pvt.voting.dto.AssemblyBalletForEnrolledVotersDTO;
import com.pon.pvt.voting.dto.ElectionDetailDTO;
import com.pon.pvt.voting.dto.LoksabhaBalletForEnrolledVotersDTO;
import com.pon.pvt.voting.dto.VoteDetailDTO;
import com.pon.pvt.voting.dto.VoterDetailsDTO;
import com.pon.pvt.voting.entity.AssemblyBalletForEnrolledVoters;
import com.pon.pvt.voting.entity.LoksabhaBalletForEnrolledVoters;
import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.entity.VoteRepository;
import com.pon.pvt.voting.repo.LoksabhaVoteRepositoryRepository;
import com.pon.pvt.voting.repo.MarkOfflineVoterRepository;
import com.pon.pvt.voting.repo.MarkOnlineVoterRepository;
import com.pon.pvt.voting.repo.VoteRepositoryRepository;
import com.support.custom.exception.CustomRuntimeException;
import com.support.custom.exception.ExceptionApplicationUtility;
import com.support.grid_pagination.DataTableRequest;
import com.support.grid_pagination.DataTableResults;
import com.support.grid_pagination.PaginationCriteria;
import com.support.util.AgeCalculator;
import com.support.util.AppConstants;
import com.support.util.AppUtil;

/**
 * @author Sanjeev
 *
 */

@Service
public class LoksabhaBalletServiceImpl implements LoksabhaBalletService{
	private static final Logger log = LoggerFactory.getLogger(LoksabhaBalletServiceImpl.class);
	
	
	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;
	
	@Autowired
	VotersEnrolledRepository votersEnrolledRepository;
	
	@Autowired
	LoksabhaCandidateRepository loksabhaCandidateRepository;
	
	@Autowired
	AssemblyCandidateRepository assemblyCandidateRepository;
	
	@Autowired
	LoksabhaVoteRepositoryRepository loksabhaVoteRepositoryRepository;
	
	@Autowired
	PollingBoothRepository pollingBoothRepository;
	
	@Autowired
	AssemblyRepository assemblyRepository;
	
	@Autowired
	LoksabhaRepository loksabhaRepository;
	
	@Autowired
	PartyRepository partyRepository;
	
	@Autowired
	MarkOnlineVoterRepository markOnlineVoterRepository;
	
	@Autowired
	MarkOfflineVoterRepository markOfflineVoterRepository;

	@Override
	public DataTableResults<LoksabhaBalletForEnrolledVotersDTO> loadElectionBalletPaperGrid(String voterId,  HttpServletRequest request) throws CustomRuntimeException {
		log.info("     AssemblyBalletServiceImpl :==> loadElectionBalletPaperGrid :==> Started");
		
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voterId);
		Integer loksabhaNo=votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getLoksabhaNo();
		
		String whereClause="	and ve.voter_id='"+voterId+"' ";
		DataTableResults<LoksabhaBalletForEnrolledVotersDTO> dataTableResult;
		try {
			DataTableRequest<LoksabhaBalletForEnrolledVoters> dataTableInRQ = new DataTableRequest<LoksabhaBalletForEnrolledVoters>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			
		String baseQuery="select lcm.id as loksabhaCandidateId,"
				+ "  lm.name as loksabhaName,"
				+ "  lcm.name as loksabhaCandidateName,"
				+ "  pm.name as partyName,"
				+ "  pm.id as partyNameId,"
				+ "  ps.name_with_ext as symbolName,"
				+ "  (SELECT COUNT(1)"
				+ "  from  voters_enrolled ve,"			
				+ "  loksabha_master lm,"
				+ "  loksabha_candidate_master lcm,"
				+ "  party_master pm,"
				+ "  party_symbol ps"
				+ "  where"
				+ "	lcm.loksabha_id=lm.id and"
				+ "	lcm.party_id=pm.id and"
				+ "	pm.symbol_id=ps.id and"
				+ " lm.loksabha_no="+loksabhaNo
				+   whereClause
				+ " ) AS totalrecords"
			
				+ " from  voters_enrolled ve,"
				+ " loksabha_master lm,"
				+ " loksabha_candidate_master lcm,"
				+ " party_master pm,"
				+ " party_symbol ps"
				+ " where"
				+ " lcm.loksabha_id=lm.id and"
				+ " lcm.party_id=pm.id and"
				+ " pm.symbol_id=ps.id and"
				+ " lm.loksabha_no="+loksabhaNo
				+   whereClause;
					
		    //System.out.println("baseQuery = "+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
			Query query = entityManager.createNativeQuery(paginatedQuery, "LoksabhaBalletForEnrolledVotersDTOMapping");

			@SuppressWarnings("unchecked")
			List<LoksabhaBalletForEnrolledVotersDTO> loksabhaBalletForEnrolledVotersList = query.getResultList();		

			dataTableResult = new DataTableResults<LoksabhaBalletForEnrolledVotersDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(loksabhaBalletForEnrolledVotersList);
			if (!AppUtil.isObjectEmpty(loksabhaBalletForEnrolledVotersList)) {
				dataTableResult.setRecordsTotal(loksabhaBalletForEnrolledVotersList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(loksabhaBalletForEnrolledVotersList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(loksabhaBalletForEnrolledVotersList.size()));
				}
			}
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     AssemblyBalletServiceImpl :==> loadElectionBalletPaperGrid :==> Ended");
		return dataTableResult;
	}


	@Override
	public ElectionDetailDTO loadElectionDetails(String voterId, String electionType) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		ElectionDetailDTO electionDetailDTO=new ElectionDetailDTO();		
		VotersEnrolled votersEnrolled= votersEnrolledRepository.findByVoterId(voterId);
		
		electionDetailDTO.setElectionYear("2020");	
		electionDetailDTO.setElectionType(electionType);
		electionDetailDTO.setStateNameId(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getStateId().getId()+"");
		electionDetailDTO.setLoksabhaNameId(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getId()+"");		
		electionDetailDTO.setAssemblyNameId(votersEnrolled.getBoothId().getAssemblyId().getId()+"");
		electionDetailDTO.setPollingBoothNameId(votersEnrolled.getBoothId().getId()+"");
		
		
		electionDetailDTO.setStateName(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getStateId().getName());
		electionDetailDTO.setLoksabhaName(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getName());		
		electionDetailDTO.setAssemblyName(votersEnrolled.getBoothId().getAssemblyId().getName());
		electionDetailDTO.setPollingBoothName(votersEnrolled.getBoothId().getName());
		
		
			
		return electionDetailDTO;
	}

	@Override
	public VoterDetailsDTO loadVotersDetails(String voterId) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		VoterDetailsDTO voterDetailsDTO=new VoterDetailsDTO();
		VotersEnrolled votersEnrolled= votersEnrolledRepository.findByVoterId(voterId);
		voterDetailsDTO.setName(votersEnrolled.getName());
		
		voterDetailsDTO.setAge(AgeCalculator.calculateAge(votersEnrolled.getDob()).getYears()+"");
		
		voterDetailsDTO.setVoterId(votersEnrolled.getVoterId());
		
		voterDetailsDTO.setDob(AppUtil.convertJavaDateIntoStringDate(votersEnrolled.getDob()));
		voterDetailsDTO.setSex(votersEnrolled.getSex());
		
		voterDetailsDTO.setFatherName(votersEnrolled.getFatherName());
		voterDetailsDTO.setAddress(votersEnrolled.getAddress());
		return voterDetailsDTO;
	}

	@Override
	@Transactional
	public boolean castVote(@Valid VoteDetailDTO voteDetailDTO) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		try {
		LoksabhaVoteRepository loksabhaVoteRepositoryInsert=new LoksabhaVoteRepository();
		
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voteDetailDTO.getVoterId());
		
		loksabhaVoteRepositoryInsert.setVoterId(votersEnrolled.getId()+"");
		PollingBoothMaster pollingBoothMaster=pollingBoothRepository.getOne(Integer.parseInt(voteDetailDTO.getPollingBoothNameId()));
		loksabhaVoteRepositoryInsert.setPollingBoothId(pollingBoothMaster.getId()+"");
		AssemblyMaster assemblyMaster=assemblyRepository.getOne(Integer.parseInt(voteDetailDTO.getAssemblyNameId()));
		loksabhaVoteRepositoryInsert.setAssemblyId(assemblyMaster.getId()+"");
		LoksabhaMaster loksabhaMaster=loksabhaRepository.getOne(Integer.parseInt(voteDetailDTO.getLoksabhaNameId()));		
		loksabhaVoteRepositoryInsert.setLoksabhaId(loksabhaMaster.getId()+"");
		
		
		
		//System.out.println(voteDetailDTO.getSelectedCandidateIdForVoting());
		//For NOTA Checking	
		if(!voteDetailDTO.getSelectedCandidateIdForVoting().equals("0")) {			
		LoksabhaCandidateMaster loksabhaCandidate =loksabhaCandidateRepository.getOne(Integer.parseInt(voteDetailDTO.getSelectedCandidateIdForVoting()));		
		loksabhaVoteRepositoryInsert.setCandidateId(loksabhaCandidate.getName());
		PartyMaster partyMaster =partyRepository.getOne(Integer.parseInt(voteDetailDTO.getVotingPartyId()));
		loksabhaVoteRepositoryInsert.setVotingPartyId(partyMaster.getName());
		loksabhaVoteRepositoryInsert.setSymbol(partyMaster.getSymbolId().getNameWithExt());
		
		}else {			
			loksabhaVoteRepositoryInsert.setCandidateId("NOTA");
			loksabhaVoteRepositoryInsert.setVotingPartyId("None");
			loksabhaVoteRepositoryInsert.setSymbol("nota.png");
			}
		
		loksabhaVoteRepositoryInsert.setElectionYear(voteDetailDTO.getElectionYear());
		loksabhaVoteRepositoryInsert.setElectionType(voteDetailDTO.getElectionType());
		loksabhaVoteRepositoryInsert.setVotingMode(voteDetailDTO.getVotingMode());	
		
		LoksabhaVoteRepository loksabhaVoteRepository=loksabhaVoteRepositoryRepository.saveAndFlush(loksabhaVoteRepositoryInsert);
		
		//Now mark voter that he has already casted his vote
				if(voteDetailDTO.getVotingMode().equals("0")) {
				MarkOfflineVoter markOfflineVoter=new MarkOfflineVoter();
				markOfflineVoter.setHasVoted("TRUE");
				markOfflineVoter.setElectionType(voteDetailDTO.getElectionType());
				markOfflineVoter.setElectionYear(voteDetailDTO.getElectionYear());
				markOfflineVoter.setAssemblyId(assemblyMaster.getId()+"");
				markOfflineVoter.setLoksabhaId(loksabhaMaster.getId()+"");
				markOfflineVoter.setVoterId(votersEnrolled.getId()+"");			
				markOfflineVoterRepository.saveAndFlush(markOfflineVoter);	
				}
				if(voteDetailDTO.getVotingMode().equals("1")) {
					//Now mark voter that he has already casted his vote
					MarkOnlineVoter markOnlineVoter=new MarkOnlineVoter();
					markOnlineVoter.setHasVoted("TRUE");
					markOnlineVoter.setElectionType(voteDetailDTO.getElectionType());
					markOnlineVoter.setElectionYear(voteDetailDTO.getElectionYear());
					markOnlineVoter.setAssemblyId(assemblyMaster.getId()+"");
					markOnlineVoter.setLoksabhaId(loksabhaMaster.getId()+"");
					markOnlineVoter.setVoterId(votersEnrolled.getId()+"");
					markOnlineVoterRepository.saveAndFlush(markOnlineVoter);	
				}	
		}catch(Exception ex) {ex.printStackTrace();}
		return true;
	}

	
	

}
