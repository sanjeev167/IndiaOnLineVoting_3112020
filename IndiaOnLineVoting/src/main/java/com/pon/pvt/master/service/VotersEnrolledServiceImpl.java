/**
 * 
 */
package com.pon.pvt.master.service;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.ColumnResult;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.internal.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pon.pub.hm.dto.LoginDTO;
import com.pon.pvt.master.dto.VotersEnrolledDTO;
import com.pon.pvt.master.entity.PollingBoothMaster;
import com.pon.pvt.master.entity.VotersEnrolled;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.StateRepository;
import com.pon.pvt.master.repo.VotersEnrolledRepository;
import com.pon.pvt.master.repo.LoksabhaRepository;
import com.pon.pvt.master.repo.PollingBoothRepository;
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
public class VotersEnrolledServiceImpl implements VotersEnrolledService {

	static final Logger log = LoggerFactory.getLogger(VotersEnrolledServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName = AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	LoksabhaRepository loksabhaRepository;	

	@Autowired
	AssemblyRepository assemblyRepository;
	
	@Autowired
	PollingBoothRepository pollingBoothRepository;
	
	@Autowired
	VotersEnrolledRepository votersEnrolledRepository;

	@Override
	public DataTableResults<VotersEnrolledDTO> loadGrid(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> loadGrid ==> Started");
		DataTableResults<VotersEnrolledDTO> dataTableResult;
		try {
			DataTableRequest<VotersEnrolled> dataTableInRQ = new DataTableRequest<VotersEnrolled>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery="select ve.id as ID, "+ 
		      "sm.name as stateName, " +
		      "lm.name as loksabhaName, "+
		      "am.name as assemblyName, "+
		      "pm.name as pollingBoothName, "+  
		      "pm.booth_no  as pollingBoothNo, "+
		      "ve.name as voterName, "+
		      "ve.sex as sex, "+
		      "ve.dob as dob, "+
		      "ve.father_name as fatherName, "+
		      "ve.voter_id as voterId, "+
		      "ve.voting_mode as voting_mode, "+
		      "ve.address as address, "+
		      "(SELECT COUNT(1) FROM voters_enrolled ve, polling_booth_master pm, assembly_master am, loksabha_master lm ,  state_master sm  "+
		      "where ve.booth_id=pm.id and  "+ 
		            "pm.assembly_id=am.id and "+
		            "am.loksabha_id=lm.id and "+
		           " lm.state_id=sm.id "+
		           whereClauseForBaseQuery+") AS totalrecords "+
		            
		            "from voters_enrolled ve, polling_booth_master pm, assembly_master am, loksabha_master lm , state_master sm  "+
		            "where ve.booth_id=pm.id and   "+
		            "pm.assembly_id=am.id and "+
		            "am.loksabha_id=lm.id and "+
		            "lm.state_id=sm.id  "+whereClauseForBaseQuery;
		           
			// System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			entityManager.clear();
			Query query = entityManager.createNativeQuery(paginatedQuery, "VotersEnrolledDTOMapping");

			@SuppressWarnings("unchecked")
			List<VotersEnrolledDTO> votersEnrolledList = query.getResultList();

			dataTableResult = new DataTableResults<VotersEnrolledDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(votersEnrolledList);
			if (!AppUtil.isObjectEmpty(votersEnrolledList)) {
				dataTableResult.setRecordsTotal(votersEnrolledList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(votersEnrolledList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(votersEnrolledList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}
	
	@Override
	public DataTableResults<VotersEnrolledDTO> loadGridOfflineOnline(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> loadGrid ==> Started");
		DataTableResults<VotersEnrolledDTO> dataTableResult;
		try {
			DataTableRequest<VotersEnrolled> dataTableInRQ = new DataTableRequest<VotersEnrolled>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery="select ve.id as ID, "+ 
				      "sm.name as stateName, " +
				      "lm.name as loksabhaName, "+
				      "am.name as assemblyName, "+
				      "pm.name as pollingBoothName, "+  
				      "pm.booth_no  as pollingBoothNo, "+
				      "ve.name as voterName, "+
				      "ve.sex as sex, "+
				      "ve.dob as dob, "+
				      "ve.father_name as fatherName, "+
				      "ve.voter_id as voterId, "+
				      "ve.voting_mode as voting_mode, "+
				      "ve.address as address, "+
				      "(SELECT COUNT(1) FROM voters_enrolled ve, polling_booth_master pm, assembly_master am, loksabha_master lm ,  state_master sm  "+
				      "where ve.booth_id=pm.id and  "+ 
				            "pm.assembly_id=am.id and "+
				            "am.loksabha_id=lm.id and "+
				           " lm.state_id=sm.id "+
				           whereClauseForBaseQuery+") AS totalrecords "+
				            
				            "from voters_enrolled ve, polling_booth_master pm, assembly_master am, loksabha_master lm , state_master sm  "+
				            "where ve.booth_id=pm.id and   "+
				            "pm.assembly_id=am.id and "+
				            "am.loksabha_id=lm.id and "+
				            "lm.state_id=sm.id  "+whereClauseForBaseQuery;

			// System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			entityManager.clear();
			Query query = entityManager.createNativeQuery(paginatedQuery, "VotersEnrolledDTOMapping");

			@SuppressWarnings("unchecked")
			List<VotersEnrolledDTO> votersEnrolledList = query.getResultList();

			dataTableResult = new DataTableResults<VotersEnrolledDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(votersEnrolledList);
			if (!AppUtil.isObjectEmpty(votersEnrolledList)) {
				dataTableResult.setRecordsTotal(votersEnrolledList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(votersEnrolledList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(votersEnrolledList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> loadGridOffline ==> Ended");
		return dataTableResult;
	}
	
	
	// This will directly put your result into your mapped dto
	@Override
	public VotersEnrolledDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> getReordById ==> Started");
		VotersEnrolledDTO votersEnrolledDTO;
		try {
			
			votersEnrolledRepository.flush();
			VotersEnrolled votersEnrolled = votersEnrolledRepository.getOne(id);
			
			
			votersEnrolledDTO = new VotersEnrolledDTO();
			votersEnrolledDTO.setId(votersEnrolled.getId());
			votersEnrolledDTO.setStateNameId(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getStateId().getId() + "");			
			votersEnrolledDTO.setLoksabhaNameId(votersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getId() + "");	
			votersEnrolledDTO.setAssemblyNameId(votersEnrolled.getBoothId().getAssemblyId().getId() + "");			
			votersEnrolledDTO.setPollingBoothName(votersEnrolled.getName());
			votersEnrolledDTO.setPollingBoothNameId(votersEnrolled.getBoothId().getId()+"");
			votersEnrolledDTO.setPollingBoothNo(votersEnrolled.getBoothId().getBoothNo());	
			
			
			votersEnrolledDTO.setVoterName(votersEnrolled.getName());
			votersEnrolledDTO.setSex(votersEnrolled.getSex());	
			
			votersEnrolledDTO.setDob(votersEnrolled.getDob());	
			votersEnrolledDTO.setDobForPage(AppUtil.convertJavaDateIntoStringDate(votersEnrolled.getDob()));
			
			votersEnrolledDTO.setFatherName(votersEnrolled.getFatherName());
			votersEnrolledDTO.setVoterId(votersEnrolled.getVoterId());			
			votersEnrolledDTO.setVoting_mode(votersEnrolled.getVotingMode());
			votersEnrolledDTO.setAddress(votersEnrolled.getAddress());	
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> getReordById ==> Ended");
		return votersEnrolledDTO;
	}

	@Override
	public VotersEnrolledDTO saveAndUpdate(VotersEnrolledDTO votersEnrolledDTO) throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> saveAndUpdate ==> Started");
		VotersEnrolled votersEnrolled;
		VotersEnrolledDTO votersEnrolledDTONew;
		PollingBoothMaster pollingBoothMaster=pollingBoothRepository.getOne(Integer.parseInt(votersEnrolledDTO.getPollingBoothNameId()));
		Integer nextVoterNo=0;
		System.out.println("San id = "+votersEnrolledDTO.getId() );
		try {			
			if (votersEnrolledDTO.getId() != null) {//Edit case				
				votersEnrolled = votersEnrolledRepository.getOne(votersEnrolledDTO.getId());				
			}
			else {	//Add case				
				votersEnrolled = new VotersEnrolled();	
				
				if(votersEnrolledRepository.findLargetVoterNo(pollingBoothMaster.getId())!=null)
				    nextVoterNo=votersEnrolledRepository.findLargetVoterNo(pollingBoothMaster.getId())+1;
				else
					nextVoterNo=1;
				
				
				votersEnrolled.setVoterId(AppUtil.returnVoterId(
																  pollingBoothMaster.getAssemblyId().getLoksabhaId().getStateId().getId()+"".trim(), 
													              pollingBoothMaster.getAssemblyId().getLoksabhaId().getLoksabhaNo()+"".trim(), 
													              pollingBoothMaster.getAssemblyId().getAssemblyNo()+"".trim(), 
													              pollingBoothMaster.getBoothNo()+"".trim(), 
													              nextVoterNo+"".trim()));
				
				votersEnrolled.setVoterNo(nextVoterNo);
			}		
			
			votersEnrolled.setBoothId(pollingBoothMaster);
			votersEnrolled.setName(votersEnrolledDTO.getVoterName());
			votersEnrolled.setSex(votersEnrolledDTO.getSex());
			
			votersEnrolled.setDob(AppUtil.convertStringDateIntoJavaDate(votersEnrolledDTO.getDobForPage()));
			
			votersEnrolled.setFatherName(votersEnrolledDTO.getFatherName());			
			votersEnrolled.setAddress(votersEnrolledDTO.getAddress());		
			
			VotersEnrolled returnedVotersEnrolled = votersEnrolledRepository.saveAndFlush(votersEnrolled);

			votersEnrolledDTONew = new VotersEnrolledDTO(
					returnedVotersEnrolled.getId(),
					returnedVotersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getStateId().getId() + "",
					returnedVotersEnrolled.getBoothId().getAssemblyId().getLoksabhaId().getId() + "", 
					returnedVotersEnrolled.getBoothId().getAssemblyId().getId() + "",					
					returnedVotersEnrolled.getBoothId().getBoothNo()+"",
					
					returnedVotersEnrolled.getName(),
					returnedVotersEnrolled.getSex(),			
					returnedVotersEnrolled.getDob(),	//Coming date is java.util.date. Make it string to show it on page	
					returnedVotersEnrolled.getFatherName(),
					
					returnedVotersEnrolled.getVoterId(),
					
					returnedVotersEnrolled.getVotingMode(),
					returnedVotersEnrolled.getAddress()			
					);		
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> saveAndUpdate ==> Ended");
		return votersEnrolledDTONew;
	}

	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> deleteOneRecord ==> Started");
		boolean isRecordDelete = true;
		try {
			votersEnrolledRepository.deleteById(id);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> deleteOneRecord ==> Ended");
		return isRecordDelete;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {
		log.info("     VotersEnrolledServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isRecordDelete = true;
		try {
			votersEnrolledRepository.deletePollingBoothWithIds(recordIdArray);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     VotersEnrolledServiceImpl :==> deleteMultipleRecords ==> Started");
		return isRecordDelete;
	}

	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     VotersEnrolledServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("stateId")&&!fieldName.equals("cityName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}

			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one
				//System.out.println("Add parentIdValue  "+parentIdValue);
				recordFound=this.votersEnrolledRepository.existsByPollingBoothIdAndVoterEnrolledName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    
				//System.out.println("Add recordFound  "+recordFound);
			}
			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.votersEnrolledRepository.existsByPollingBoothIdAndVoterEnrolledNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				//System.out.println("Edit recordFound  "+recordFound);
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     VotersEnrolledServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
		return recordFound;
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
	public boolean findByVoterName(LoginDTO loginDTO) throws CustomRuntimeException {
		// TODO Auto-generated method stub		
		VotersEnrolled votersEnrolled=votersEnrolledRepository.findByVoterId(loginDTO.getVoterId());		
		if(votersEnrolled!=null && votersEnrolled.getName().equals(loginDTO.getName()))
		     return true;
		else
			return false;
	}

	@Override
	public Integer findTotalRegisteredVoters() throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return votersEnrolledRepository.findTotalRegisteredVoters();
		
	}

	@Override
	public Integer findTotalOnlineVoters() throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return votersEnrolledRepository.findTotalOnlineVoters();
	}

	@Override
	public Integer findTotalOfflineVoters() throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return votersEnrolledRepository.findTotalOfflineVoters();
	}
	
}
