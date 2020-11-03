/**
 * 
 */
package com.pon.pvt.master.service;

import java.util.ArrayList;
import java.util.List;

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

import com.pon.pvt.master.dto.LoksabhaCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.entity.LoksabhaCandidateMaster;
import com.pon.pvt.master.entity.StateMaster;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.LoksabhaCandidateRepository;
import com.pon.pvt.master.repo.StateRepository;
import com.pon.pvt.master.repo.LoksabhaRepository;
import com.pon.pvt.master.repo.PartyRepository;
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
public class LoksabhaCandidateServiceImpl implements LoksabhaCandidateService {

	static final Logger log = LoggerFactory.getLogger(LoksabhaCandidateServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName = AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	StateRepository countryRepository;
	
	@Autowired
	LoksabhaRepository loksabhaRepository;	

	@Autowired
	LoksabhaCandidateRepository loksabhaCandidateRepository;

	@Autowired
	PartyRepository partyRepository;
	
	@Override
	public DataTableResults<LoksabhaCandidateMasterDTO> loadGrid(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     LoksabhaCandidateServiceImpl :==> loadGrid ==> Started");
		DataTableResults<LoksabhaCandidateMasterDTO> dataTableResult;
		try {
			DataTableRequest<LoksabhaCandidateMaster> dataTableInRQ = new DataTableRequest<LoksabhaCandidateMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;

			if (whereClauseForBaseQuery.equals(""))
				baseQuery = "select lcm.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName, pm.name as partyName, " 
						+ " lcm.name as lcandidateName, lcm.lcandidate_no as lcandidateNo, lcm.election_year as electionYear , ps.name_with_ext as imgName ," + " (SELECT COUNT(1) FROM loksabha_candidate_master ) AS totalrecords"
						+ " from loksabha_candidate_master lcm, loksabha_master lm , state_master sm , party_symbol ps, party_master pm "
						+ " where lcm.loksabha_id=lm.id and lm.state_id=sm.id and lcm.party_id=pm.id and    pm.symbol_id=ps.id";
			else
				baseQuery = "select lcm.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName, pm.name as partyName, "
						+ " lcm.name as lcandidateName, lcm.lcandidate_no as lcandidateNo, lcm.election_year as electionYear , ps.name_with_ext as imgName , "
						+ " (SELECT COUNT(1) FROM loksabha_candidate_master lcm, loksabha_master lm , state_master sm, party_symbol ps, party_master pm  where lcm.loksabha_id=lm.id and lm.state_id=sm.id "
						+ whereClauseForBaseQuery + ") AS totalrecords"
						+ " from loksabha_candidate_master lcm, loksabha_master lm , state_master sm, party_master pm, party_symbol ps  "
						+ " where lcm.loksabha_id=lm.id and lm.state_id=sm.id and lcm.party_id=pm.id and    pm.symbol_id=ps.id " + whereClauseForBaseQuery;

			// System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "LoksabhaCandidateMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<LoksabhaCandidateMasterDTO> loksabhaCandidateMasterList = query.getResultList();

			dataTableResult = new DataTableResults<LoksabhaCandidateMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(loksabhaCandidateMasterList);
			if (!AppUtil.isObjectEmpty(loksabhaCandidateMasterList)) {
				dataTableResult.setRecordsTotal(loksabhaCandidateMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(loksabhaCandidateMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(loksabhaCandidateMasterList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     LoksabhaCandidateServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public LoksabhaCandidateMasterDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     LoksabhaCandidateServiceImpl :==> getReordById ==> Started");
		LoksabhaCandidateMasterDTO loksabhaCandidateMasterDTO;
		try {
			LoksabhaCandidateMaster loksabhaCandidateMaster = loksabhaCandidateRepository.getOne(id);
			loksabhaCandidateMasterDTO = new LoksabhaCandidateMasterDTO();
			loksabhaCandidateMasterDTO.setId(loksabhaCandidateMaster.getId());
			loksabhaCandidateMasterDTO.setStateNameId(loksabhaCandidateMaster.getLoksabhaId().getStateId().getId() + "");
			loksabhaCandidateMasterDTO.setLoksabhaNameId(loksabhaCandidateMaster.getLoksabhaId().getId() + "");
			loksabhaCandidateMasterDTO.setLcandidateName(loksabhaCandidateMaster.getName());
			loksabhaCandidateMasterDTO.setLcandidateNo(loksabhaCandidateMaster.getLcandidateNo());
			loksabhaCandidateMasterDTO.setImgName(loksabhaCandidateMaster.getPartyId().getSymbolId().getNameWithExt());
			
			loksabhaCandidateMasterDTO.setElectionYear(Integer.parseInt(loksabhaCandidateMaster.getElectionYear().trim()));
			loksabhaCandidateMasterDTO.setPartyNameId(loksabhaCandidateMaster.getPartyId().getId()+"");
			loksabhaCandidateMasterDTO.setNominated(loksabhaCandidateMaster.getNominatedAs());
			
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     LoksabhaCandidateServiceImpl :==> getReordById ==> Ended");
		return loksabhaCandidateMasterDTO;
	}

	@Override
	public LoksabhaCandidateMasterDTO saveAndUpdate(LoksabhaCandidateMasterDTO loksabhaCandidateMasterDTO) throws CustomRuntimeException {
		log.info("     LoksabhaCandidateServiceImpl :==> saveAndUpdate ==> Started");
		LoksabhaCandidateMaster loksabhaCandidateMaster;
		LoksabhaCandidateMasterDTO loksabhaCandidateMasterDTONew;
		try {			
			if (loksabhaCandidateMasterDTO.getId() != null) {//Edit case				
				loksabhaCandidateMaster = loksabhaCandidateRepository.getOne(loksabhaCandidateMasterDTO.getId());				
			}
			else {	//Add case				
				loksabhaCandidateMaster = new LoksabhaCandidateMaster();				
			}
			loksabhaCandidateMaster.setName(loksabhaCandidateMasterDTO.getLcandidateName());			
			loksabhaCandidateMaster.setLoksabhaId(loksabhaRepository.getOne(Integer.parseInt(loksabhaCandidateMasterDTO.getLoksabhaNameId())));
			loksabhaCandidateMaster.setLcandidateNo(loksabhaCandidateMasterDTO.getLcandidateNo());
			loksabhaCandidateMaster.setElectionYear(loksabhaCandidateMasterDTO.getElectionYear()+"");
			loksabhaCandidateMaster.setPartyId(partyRepository.getOne(Integer.parseInt(loksabhaCandidateMasterDTO.getPartyNameId())));
			loksabhaCandidateMaster.setNominatedAs(loksabhaCandidateMasterDTO.getNominated());
			
			
			LoksabhaCandidateMaster returnedLoksabhaCandidateMaster = loksabhaCandidateRepository.saveAndFlush(loksabhaCandidateMaster);

			loksabhaCandidateMasterDTONew = new LoksabhaCandidateMasterDTO(
																			returnedLoksabhaCandidateMaster.getId(),
																			returnedLoksabhaCandidateMaster.getLoksabhaId().getStateId().getId() + "",
																			returnedLoksabhaCandidateMaster.getLoksabhaId().getId() + "", 
																			returnedLoksabhaCandidateMaster.getName(),
																			Integer.parseInt(returnedLoksabhaCandidateMaster.getElectionYear()),
																			returnedLoksabhaCandidateMaster.getLcandidateNo(),
																			returnedLoksabhaCandidateMaster.getPartyId().getSymbolId().getNameWithExt(),
																			returnedLoksabhaCandidateMaster.getPartyId().getId()+"",
																			returnedLoksabhaCandidateMaster.getNominatedAs()
																	      );	
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     LoksabhaCandidateServiceImpl :==> saveAndUpdate ==> Ended");
		return loksabhaCandidateMasterDTONew;
	}

	
	@Override
	public List<NameValue> getLoksabhaCandidateList(Integer id)throws CustomRuntimeException {
		List<NameValue> loksabhaCandidateList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     LoksabhaCandidateServiceImpl :==> getLoksabhaCandidateList :==> Started");
		try {
			List<LoksabhaCandidateMaster> loksabhaCandidateMasterList=loksabhaRepository.getOne(id).getLoksabhaCandidateMasterList();		
			for(LoksabhaCandidateMaster loksabhaCandidateMaster:loksabhaCandidateMasterList) {
				nameValue=new NameValue(loksabhaCandidateMaster.getId(),loksabhaCandidateMaster.getName());
				loksabhaCandidateList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaCandidateServiceImpl :==> getLoksabhaCandidateList :==> Ended");
		return loksabhaCandidateList;
	}
	
	
	
	
	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     LoksabhaCandidateServiceImpl :==> deleteOneRecord ==> Started");
		boolean isRecordDelete = true;
		try {
			loksabhaCandidateRepository.deleteById(id);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     LoksabhaCandidateServiceImpl :==> deleteOneRecord ==> Ended");
		return isRecordDelete;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {
		log.info("     LoksabhaCandidateServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isRecordDelete = true;
		try {
			loksabhaCandidateRepository.deleteAssemblyWithIds(recordIdArray);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     LoksabhaCandidateServiceImpl :==> deleteMultipleRecords ==> Started");
		return isRecordDelete;
	}

	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     LoksabhaCandidateServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("loksabhaNameId")&&!fieldName.equals("lcandidateName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}

			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one
				//System.out.println("Add CCCCC  "+parentIdValue);
				recordFound=this.loksabhaCandidateRepository.existsByLoksabhaIdAndLoksabhaCandidateName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    
				//System.out.println("Add BBBBBBBB  "+recordFound);
			}
			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.loksabhaCandidateRepository.existsByLoksabhaIdAndLoksabhaCandidateNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				//System.out.println("Edit BBBBBBBB  "+recordFound);
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaCandidateServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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

}
