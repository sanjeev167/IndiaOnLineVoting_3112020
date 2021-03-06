/**
 * 
 */
package com.pon.pvt.voter.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger; 
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pon.pub.hm.repo.OnlineVoterAccountRepository;
import com.pon.pvt.master.dto.CityMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.entity.CityMaster;
import com.pon.pvt.master.entity.VotersEnrolled;
import com.pon.pvt.master.repo.VotersEnrolledRepository;
import com.pon.pvt.voter.dto.VoteLockinigDTO;
import com.pon.pvt.voter.entity.VoteLockinig;
import com.pon.pvt.voter.repo.VoteLockinigRepository;
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
public class VoteLockinigServiceImpl implements VoteLockinigService{
	private static final Logger log = LoggerFactory.getLogger(VoteLockinigServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;
	
	@Autowired
	VoteLockinigRepository voteLockinigRepository;
	@Autowired
	VotersEnrolledRepository votersEnrolledRepository;
	
	@Autowired
	OnlineVoterAccountRepository onlineVoterAccountRepository;
	
	
	
	
	DateFormat dateFormat = new SimpleDateFormat("mm-dd-yyyy hh:mm:ss");

	@Override
	public DataTableResults<VoteLockinigDTO> loadGrid(HttpServletRequest request, String voterId)
			throws CustomRuntimeException {
		// TODO Auto-generated method stub
		log.info("     VoteLockinigServiceImpl :==> loadGrid ==> Started");
		VotersEnrolled votersEnrolled=votersEnrolledRepository.getOne(Integer.parseInt(voterId));
		 String voterMailId=votersEnrolled.getOnlineVoterAccountList().get(0).getMailId();		
		 String whereClauseForBaseQuery = "and ova.mail_id='"+voterMailId+"'";
		DataTableResults<VoteLockinigDTO> dataTableResult;
		try {
			DataTableRequest<VoteLockinig> dataTableInRQ = new DataTableRequest<VoteLockinig>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;			
			baseQuery = "select vl.id as id, "
					+ " ve.name as name, "
					+ " vl.lock_status as lockStatusId,"
					+ " ve.voter_id as voterId, "			
					+ " vl.req_ip_add as reqIpAdd,  "
					+ " vl.online_voter_account_id as onlineVoterAccountId, "					
					+ " CAST(vl.locked_on AS VARCHAR)  as dateOfLocking, "						
					+ " (Select count (1) from vote_lockinig vl, voters_enrolled ve ,online_voter_account ova, polling_booth_master bm  "
					+ " where vl.voter_id=ve.id and  ova.voter_id=ve.id and ve.booth_id=bm.id " + whereClauseForBaseQuery
					+ " ) as totalrecords " + " from vote_lockinig vl, voters_enrolled ve ,online_voter_account ova, polling_booth_master bm  "
					+ " where vl.voter_id=ve.id and  ova.voter_id=ve.id and  ve.booth_id=bm.id " + whereClauseForBaseQuery;

			// System.out.println("baseQuery ="+baseQuery);

			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "VoteLockinigDTOMapping");

			@SuppressWarnings("unchecked")
			List<VoteLockinigDTO> voteLockinigList = query.getResultList();

			dataTableResult = new DataTableResults<VoteLockinigDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(voteLockinigList);
			if (!AppUtil.isObjectEmpty(voteLockinigList)) {
				dataTableResult.setRecordsTotal(voteLockinigList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(voteLockinigList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(voteLockinigList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VoteLockinigServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}
	
	
	
	@Override
	public DataTableResults<VoteLockinigDTO> loadGridForVoter(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		// TODO Auto-generated method stub
		log.info("     VoteLockinigServiceImpl :==> loadGrid ==> Started");
		
		DataTableResults<VoteLockinigDTO> dataTableResult;
		try {
			DataTableRequest<VoteLockinig> dataTableInRQ = new DataTableRequest<VoteLockinig>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;			
			baseQuery = "select vl.id as id, "
					+ " ve.name as name, "
					+ " vl.lock_status as lockStatusId,"
					+ " ve.voter_id as voterId, "			
					+ " vl.req_ip_add as reqIpAdd,  "
					+ " vl.online_voter_account_id as onlineVoterAccountId, "					
					+ " CAST(vl.locked_on AS VARCHAR)  as dateOfLocking, "						
					+ " (Select count (1) from vote_lockinig vl, voters_enrolled ve ,online_voter_account ova, polling_booth_master bm  "
					+ " where vl.voter_id=ve.id and  ova.voter_id=ve.id and ve.booth_id=bm.id " + whereClauseForBaseQuery
					+ " ) as totalrecords " + " from vote_lockinig vl, voters_enrolled ve ,online_voter_account ova, polling_booth_master bm  "
					+ " where vl.voter_id=ve.id and  ova.voter_id=ve.id and  ve.booth_id=bm.id " + whereClauseForBaseQuery;

			// System.out.println("baseQuery ="+baseQuery);

			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "VoteLockinigDTOMapping");

			@SuppressWarnings("unchecked")
			List<VoteLockinigDTO> voteLockinigList = query.getResultList();

			dataTableResult = new DataTableResults<VoteLockinigDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(voteLockinigList);
			if (!AppUtil.isObjectEmpty(voteLockinigList)) {
				dataTableResult.setRecordsTotal(voteLockinigList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(voteLockinigList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(voteLockinigList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VoteLockinigServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}

	@Override
	public VoteLockinigDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     VoteLockinigServiceImpl :==> getReordById :==> Started");
		VoteLockinigDTO voteLockinigDTO;
		try {
			VoteLockinig voteLockinig =voteLockinigRepository.getOne(id);
			voteLockinigDTO=new VoteLockinigDTO();
			voteLockinigDTO.setId(voteLockinig.getId());
			if(voteLockinig.getVoterId().getVotingMode().equals("0"))
			    voteLockinigDTO.setLockStatus("Not Locked");
			else
				voteLockinigDTO.setLockStatus("Locked");
			
			voteLockinigDTO.setVoterId(voteLockinig.getVoterId().getVoterId());
			voteLockinigDTO.setReqIpAdd(voteLockinig.getReqIpAdd());
			voteLockinigDTO.setOnlineVoterAccountId(voteLockinig.getOnlineVoterAccountId().getId());			
			voteLockinigDTO.setElection_year(voteLockinig.getElectionYear());	
			voteLockinigDTO.setDateOfLocking(AppUtil.convertJavaDateIntoStringDate(voteLockinig.getLockedOn()));
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     VoteLockinigServiceImpl :==> getReordById :==> Ended");
		return voteLockinigDTO;
	}

	@Override
	public VoteLockinigDTO saveAndUpdate(VoteLockinigDTO voteLockinigDTO) throws CustomRuntimeException {
		VoteLockinig voteLockinig;
		log.info("     VoteLockinigServiceImpl :==> saveAndUpdate :==> Started");
		VoteLockinigDTO voteLockinigDTONew;
		try {
			if(voteLockinigDTO.getId()!=null)
				voteLockinig =voteLockinigRepository.getOne(voteLockinigDTO.getId());
			else
				voteLockinig =new VoteLockinig();

			
			voteLockinig.setLockStatus(Integer.parseInt(voteLockinigDTO.getLockStatus())); 	
			VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(voteLockinigDTO.getVoterId());
			votersEnrolled.setVotingMode(voteLockinigDTO.getLockStatus());
			voteLockinig.setVoterId(votersEnrolled);
			voteLockinig.setReqIpAdd(voteLockinigDTO.getReqIpAdd());			
			voteLockinig.setOnlineVoterAccountId(onlineVoterAccountRepository.getOne(voteLockinigDTO.getOnlineVoterAccountId()));
			voteLockinig.setElectionYear(voteLockinigDTO.getElection_year());
			voteLockinig.setLockedOn(new java.util.Date());
			voteLockinig.setActiveC('Y');
			
				
			VoteLockinig returnedVoteLockinig = voteLockinigRepository.saveAndFlush(voteLockinig);
			String lockStatus="Unlocked";
			if(returnedVoteLockinig.getLockStatus()==1)
				lockStatus="Locked";
				
			voteLockinigDTONew=new VoteLockinigDTO(returnedVoteLockinig.getId(), 
													returnedVoteLockinig.getOnlineVoterAccountId().getName(),
													lockStatus, 													
													returnedVoteLockinig.getVoterId().getId()+"", 
													returnedVoteLockinig.getOnlineVoterAccountId().getAadharId()+"",
													returnedVoteLockinig.getReqIpAdd(),
													returnedVoteLockinig.getOnlineVoterAccountId().getId(),
													returnedVoteLockinig.getElectionYear(),
													dateFormat.format(returnedVoteLockinig.getLockedOn())
												
													);		
			
			
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     VoteLockinigServiceImpl :==> saveAndUpdate :==> Ended");
		return voteLockinigDTONew;
	}

	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     VoteLockinigServiceImpl :==> deleteOneRecord :==> Started");
		boolean isDeleted=true;
		try {
			voteLockinigRepository.deleteById(id);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     VoteLockinigServiceImpl :==> deleteOneRecord :==> Ended");
		return isDeleted;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException{
		log.info("     VoteLockinigServiceImpl :==> deleteMultipleRecords :==> Started");
		boolean isDeleted=true;
		try {
			voteLockinigRepository.deleteVoteLockinigWithIds(recordIdArray);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     VoteLockinigServiceImpl :==> deleteMultipleRecords :==> Ended");
		return isDeleted;
	}

	
	@Override
	public List<NameValue> getVoteLockingList(Integer voterId) throws CustomRuntimeException {
		List<NameValue> voteLockingListLocal=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     VoteLockinigServiceImpl :==> getPartyList :==> Started");
		try {
			List<VoteLockinig> voteLockinigList=votersEnrolledRepository.getOne(voterId).getVoteLockinigList();		
			for(VoteLockinig voteLockinig:voteLockinigList) {
				nameValue=new NameValue(voteLockinig.getId(),voteLockinig.getReqIpAdd());
				voteLockingListLocal.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     VoteLockinigServiceImpl :==> getPartyList :==> Ended");
		return voteLockingListLocal;
	}

	@Override
	public List<NameValue> getVoteLockingList() throws CustomRuntimeException {
		List<NameValue> voteLockingListLocal=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     VoteLockinigServiceImpl :==> getPartyList :==> Started");
		try {
			List<VoteLockinig> voteLockinigList=voteLockinigRepository.findAll();		
			for(VoteLockinig voteLockinig:voteLockinigList) {
				nameValue=new NameValue(voteLockinig.getId(),voteLockinig.getReqIpAdd());
				voteLockingListLocal.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     VoteLockinigServiceImpl :==> getPartyList :==> Ended");
		return voteLockingListLocal;
	}
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		
		return false;
	}

	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentValue, String parentId, Object fieldValue,
			String fieldName) throws UnsupportedOperationException, CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean FirstChildValueWithParentIdExists(Object parentIdValue, String parentId, Object fieldValue,
			String fieldName, Object idValue, String idFieldName) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean SecondChildValueWithParentIdExists(Object parentIdValue, String parentId, Object fieldValue,
			String fieldName, Object idValue, String idFieldName) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ThirdChildValueWithParentIdExists(Object parentIdValue, String parentId, Object fieldValue,
			String fieldName, Object idValue, String idFieldName) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] verifyVotingSecret(String secret,String username) throws CustomRuntimeException {
		// TODO Auto-generated method stub
				String status[]=new String[2];
				
					//Next check it in the database whether it exists or not
					if(onlineVoterAccountRepository.findByMailId(username).getSecret().equals(secret)) {
						status[0]="1";
						status[1]="Voting-Secret has been verified. Now, input OTP received at mobile.";
					}else {
						status[0]="0";
						status[1]="Wrong Voting-Secret is supplied.";
					}
				
				return status;
	}
	
	

	@Override
	public VoteLockinig lockUnlockVote(@Valid VoteLockinigDTO voteLockinigDTO) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		VotersEnrolled votersEnrolled= votersEnrolledRepository.findByVoterId(voteLockinigDTO.getVoterId());
		votersEnrolled.setVotingMode(voteLockinigDTO.getLockStatus());		
		
		VoteLockinig voteLockinig=new VoteLockinig();
		voteLockinig.setLockStatus(Integer.parseInt(voteLockinigDTO.getLockStatus()));
		voteLockinig.setVoterId(votersEnrolled);
		voteLockinig.setReqIpAdd(voteLockinigDTO.getReqIpAdd());
		voteLockinig.setActiveC('Y');
		
		
		VoteLockinig voteLockinigNew =voteLockinigRepository.saveAndFlush(voteLockinig);
		
		return voteLockinigNew;
		
		
	}

	@Override
	public String[] verifyMobileOtp(String mobileOtp) throws CustomRuntimeException {
		String status[]=new String[2];		
					
					if(AppUtil.checkMobileOTP(mobileOtp)) {
						status[0]="1";
						status[1]="Mobile-OTP has been verified. Now, pass OTP received at mail.";						
					}else {
						status[0]="0";
						status[1]="Wrong Mobile-OTP is supplied.";
					}
				
				return status;
	}

	@Override
	public String[] verifyMailOtp(String mailOtp) throws CustomRuntimeException {
		String status[]=new String[2];		
		
		if(AppUtil.checkMailOTP(mailOtp)) {
			status[0]="1";
			status[1]="Mail-OTP has been verified. Now, you can go for locking or unlocking your online vote.";						
		}else {
			status[0]="0";
			status[1]="Wrong Mail-OTP is supplied.";
		}
	
	return status;
	}

}
