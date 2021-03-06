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
import com.pon.pvt.voting.dto.VoteDetailDTO;
import com.pon.pvt.voting.dto.VoterDetailsDTO;
import com.pon.pvt.voting.entity.AssemblyBalletForEnrolledVoters;
import com.pon.pvt.voting.entity.MarkOfflineVoter;
import com.pon.pvt.voting.entity.MarkOnlineVoter;
import com.pon.pvt.voting.entity.VoteRepository;
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
public class AssemblyBalletServiceImpl implements AssemblyBalletService{
	private static final Logger log = LoggerFactory.getLogger(AssemblyBalletServiceImpl.class);
	
	
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
	VoteRepositoryRepository voteRepositoryRepository;
	
	@Autowired
	PollingBoothRepository pollingBoothRepository;
	
	@Autowired
	AssemblyRepository assemblyRepository;
	
	@Autowired
	LoksabhaRepository loksabhaRepository;
	
	@Autowired
	PartyRepository partyRepository;
	
	@Autowired
	MarkOfflineVoterRepository markOfflineVoterRepository;
	
	@Autowired
	MarkOnlineVoterRepository markOnlineVoterRepository;
	

	@Override
	public DataTableResults<AssemblyBalletForEnrolledVotersDTO> loadElectionBalletPaperGrid(String voterId,  HttpServletRequest request) throws CustomRuntimeException {
		log.info("     AssemblyBalletServiceImpl :==> loadElectionBalletPaperGrid :==> Started");
		
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voterId);
		Integer assemblyNo=votersEnrolled.getBoothId().getAssemblyId().getAssemblyNo();
		
		String whereClause="	and ve.voter_id='"+voterId+"' ";
		DataTableResults<AssemblyBalletForEnrolledVotersDTO> dataTableResult;
		try {
			DataTableRequest<AssemblyBalletForEnrolledVoters> dataTableInRQ = new DataTableRequest<AssemblyBalletForEnrolledVoters>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			
		String baseQuery="select acm.id as assemblyCandidateId, "
									+ "  am.name as assemblyName,"
									+ "  acm.name as assemblyCandidateName,   "
									+ "  pm.name as partyName,"
									+ "  pm.id as partyNameId,"
									+ "  ps.name_with_ext as symbolName,"
									+ "  (SELECT COUNT(1) "
									+ "  from  voters_enrolled ve,"
									
									+ "  assembly_master am,"
									+ "  assembly_candidate_master acm,"
									+ "  party_master pm,"
									+ "  party_symbol ps   "
									+ "	where "
									+ " am.assembly_no="+assemblyNo+ " and"
									
									+ "	acm.assembly_id=am.id and"
									+ "	acm.party_id=pm.id and"
									+ "	pm.symbol_id=ps.id "									
									+ whereClause									
									+ " ) AS totalrecords "
									
									+ "from  voters_enrolled ve,"
									
									+ " assembly_master am,"
									+ " assembly_candidate_master acm,"
									+ " party_master pm,"
									+ " party_symbol ps   "
									+ "where "
									
									+ " am.assembly_no="+assemblyNo+ " and"
									+ " acm.assembly_id=am.id and"
									+ " acm.party_id=pm.id and"
									+ " pm.symbol_id=ps.id "									
									+ whereClause;
					
		    //System.out.println("baseQuery = "+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
			Query query = entityManager.createNativeQuery(paginatedQuery, "AssemblyBalletForEnrolledVotersDTOMapping");

			@SuppressWarnings("unchecked")
			List<AssemblyBalletForEnrolledVotersDTO> assemblyBalletForEnrolledVotersList = query.getResultList();		

			dataTableResult = new DataTableResults<AssemblyBalletForEnrolledVotersDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(assemblyBalletForEnrolledVotersList);
			if (!AppUtil.isObjectEmpty(assemblyBalletForEnrolledVotersList)) {
				dataTableResult.setRecordsTotal(assemblyBalletForEnrolledVotersList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(assemblyBalletForEnrolledVotersList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(assemblyBalletForEnrolledVotersList.size()));
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
		VoteRepository voteRepositoryInsert=new VoteRepository();
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voteDetailDTO.getVoterId());
		voteRepositoryInsert.setVoterId(votersEnrolled.getId()+"");
		PollingBoothMaster pollingBoothMaster=pollingBoothRepository.getOne(Integer.parseInt(voteDetailDTO.getPollingBoothNameId()));
		voteRepositoryInsert.setPollingBoothId(pollingBoothMaster.getId()+"");
		AssemblyMaster assemblyMaster=assemblyRepository.getOne(Integer.parseInt(voteDetailDTO.getAssemblyNameId()));
		voteRepositoryInsert.setAssemblyId(assemblyMaster.getId()+"");
		LoksabhaMaster loksabhaMaster=loksabhaRepository.getOne(Integer.parseInt(voteDetailDTO.getLoksabhaNameId()));		
		voteRepositoryInsert.setLoksabhaId(loksabhaMaster.getId()+"");
		
	
		
		//System.out.println(voteDetailDTO.getSelectedCandidateIdForVoting());
		//For NOTA Checking		
		if(!voteDetailDTO.getSelectedCandidateIdForVoting().equals("0")) {
			AssemblyCandidateMaster assemblyCandidate =assemblyCandidateRepository.getOne(Integer.parseInt(voteDetailDTO.getSelectedCandidateIdForVoting()));
			voteRepositoryInsert.setCandidateId(assemblyCandidate.getName());
			PartyMaster partyMaster =partyRepository.getOne(Integer.parseInt(voteDetailDTO.getVotingPartyId()));
			voteRepositoryInsert.setVotingPartyId(partyMaster.getName());
			voteRepositoryInsert.setSymbol(partyMaster.getSymbolId().getNameWithExt());
			}else {voteRepositoryInsert.setCandidateId("NOTA");
			voteRepositoryInsert.setVotingPartyId("None");
			voteRepositoryInsert.setSymbol("nota.png");
			}
		
		voteRepositoryInsert.setElectionYear(voteDetailDTO.getElectionYear());
		voteRepositoryInsert.setElectionType(voteDetailDTO.getElectionType());
		//System.out.println("Voting Mode = "+voteDetailDTO.getVotingMode());
		voteRepositoryInsert.setVotingMode(voteDetailDTO.getVotingMode());	
		
		VoteRepository voteRepository=voteRepositoryRepository.saveAndFlush(voteRepositoryInsert);
		
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
