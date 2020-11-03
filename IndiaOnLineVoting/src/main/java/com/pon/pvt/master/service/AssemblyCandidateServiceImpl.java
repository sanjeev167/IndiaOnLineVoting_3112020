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

import com.pon.pvt.master.dto.AssemblyCandidateMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.entity.AssemblyCandidateMaster;
import com.pon.pvt.master.entity.StateMaster;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.LoksabhaCandidateRepository;
import com.pon.pvt.master.repo.AssemblyCandidateRepository;
import com.pon.pvt.master.repo.StateRepository;
import com.pon.pvt.master.repo.AssemblyRepository;
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
public class AssemblyCandidateServiceImpl implements AssemblyCandidateService {

	static final Logger log = LoggerFactory.getLogger(AssemblyCandidateServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName = AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	StateRepository countryRepository;
	
	@Autowired
	AssemblyRepository loksabhaRepository;	

	@Autowired
	LoksabhaCandidateRepository loksabhaCandidateRepository;
	
	@Autowired
	AssemblyCandidateRepository assemblyCandidateRepository;

	@Autowired
	PartyRepository partyRepository;
	
	@Override
	public DataTableResults<AssemblyCandidateMasterDTO> loadGrid(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     AssemblyCandidateServiceImpl :==> loadGrid ==> Started");
		DataTableResults<AssemblyCandidateMasterDTO> dataTableResult;
		try {
			DataTableRequest<AssemblyCandidateMaster> dataTableInRQ = new DataTableRequest<AssemblyCandidateMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;

			
				baseQuery = "select acm.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName, am.name as assemblyName, pm.name as partyName, " 
						+ " acm.name as acandidateName, acm.acandidate_no as acandidateNo, acm.election_year as electionYear , ps.name_with_ext as imgName ," 
						+ " (SELECT COUNT(1) from assembly_candidate_master acm, assembly_master am , loksabha_master lm , state_master sm , party_symbol ps, party_master pm "
						+ " where acm.assembly_id=am.id and am.loksabha_id=lm.id and lm.state_id=sm.id and acm.party_id=pm.id and    pm.symbol_id=ps.id "
						+whereClauseForBaseQuery+") AS totalrecords"
						+ " from assembly_candidate_master acm, assembly_master am , loksabha_master lm , state_master sm , party_symbol ps, party_master pm "
						+ " where acm.assembly_id=am.id and am.loksabha_id=lm.id and lm.state_id=sm.id and acm.party_id=pm.id and    pm.symbol_id=ps.id "
						+ whereClauseForBaseQuery;
			

			 System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "AssemblyCandidateMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<AssemblyCandidateMasterDTO> assemblyCandidateMasterList = query.getResultList();

			dataTableResult = new DataTableResults<AssemblyCandidateMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(assemblyCandidateMasterList);
			if (!AppUtil.isObjectEmpty(assemblyCandidateMasterList)) {
				dataTableResult.setRecordsTotal(assemblyCandidateMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(assemblyCandidateMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(assemblyCandidateMasterList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyCandidateServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public AssemblyCandidateMasterDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     AssemblyCandidateServiceImpl :==> getReordById ==> Started");
		AssemblyCandidateMasterDTO assemblyCandidateMasterDTO;
		try {
			AssemblyCandidateMaster assemblyCandidateMaster = assemblyCandidateRepository.getOne(id);
			assemblyCandidateMasterDTO = new AssemblyCandidateMasterDTO();
			assemblyCandidateMasterDTO.setId(assemblyCandidateMaster.getId());
			assemblyCandidateMasterDTO.setStateNameId(assemblyCandidateMaster.getAssemblyId().getLoksabhaId().getStateId().getId() + "");
			assemblyCandidateMasterDTO.setLoksabhaNameId(assemblyCandidateMaster.getAssemblyId().getLoksabhaId().getId() + "");
			assemblyCandidateMasterDTO.setAssemblyNameId(assemblyCandidateMaster.getAssemblyId().getId() + "");
			assemblyCandidateMasterDTO.setAcandidateName(assemblyCandidateMaster.getName());
			assemblyCandidateMasterDTO.setAcandidateNo(assemblyCandidateMaster.getAcandidateNo());
			assemblyCandidateMasterDTO.setImgName(assemblyCandidateMaster.getPartyId().getSymbolId().getNameWithExt());
			
			assemblyCandidateMasterDTO.setElectionYear(Integer.parseInt(assemblyCandidateMaster.getElectionYear().trim()));
			assemblyCandidateMasterDTO.setPartyNameId(assemblyCandidateMaster.getPartyId().getId()+"");
			assemblyCandidateMasterDTO.setNominated(assemblyCandidateMaster.getNominatedAs()+"");
			
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyCandidateServiceImpl :==> getReordById ==> Ended");
		return assemblyCandidateMasterDTO;
	}

	@Override
	public AssemblyCandidateMasterDTO saveAndUpdate(AssemblyCandidateMasterDTO assemblyCandidateMasterDTO) throws CustomRuntimeException {
		log.info("     AssemblyCandidateServiceImpl :==> saveAndUpdate ==> Started");
		AssemblyCandidateMaster assemblyCandidateMaster;
		AssemblyCandidateMasterDTO assemblyCandidateMasterDTONew;
		try {			
			if (assemblyCandidateMasterDTO.getId() != null) {//Edit case				
				assemblyCandidateMaster = assemblyCandidateRepository.getOne(assemblyCandidateMasterDTO.getId());				
			}
			else {	//Add case				
				assemblyCandidateMaster = new AssemblyCandidateMaster();				
			}
			assemblyCandidateMaster.setName(assemblyCandidateMasterDTO.getAcandidateName());			
			assemblyCandidateMaster.setAssemblyId(loksabhaRepository.getOne(Integer.parseInt(assemblyCandidateMasterDTO.getAssemblyNameId())));
			assemblyCandidateMaster.setAcandidateNo(assemblyCandidateMasterDTO.getAcandidateNo());
			assemblyCandidateMaster.setElectionYear(assemblyCandidateMasterDTO.getElectionYear()+"");
			assemblyCandidateMaster.setPartyId(partyRepository.getOne(Integer.parseInt(assemblyCandidateMasterDTO.getPartyNameId())));
			assemblyCandidateMaster.setNominatedAs(assemblyCandidateMasterDTO.getNominated().toCharArray()[0]);
			
			
			AssemblyCandidateMaster returnedAssemblyCandidateMaster = assemblyCandidateRepository.saveAndFlush(assemblyCandidateMaster);

			assemblyCandidateMasterDTONew = new AssemblyCandidateMasterDTO(
																			returnedAssemblyCandidateMaster.getId(),
																			returnedAssemblyCandidateMaster.getAssemblyId().getLoksabhaId().getStateId().getId() + "",
																			returnedAssemblyCandidateMaster.getAssemblyId().getLoksabhaId().getId() + "", 
																			returnedAssemblyCandidateMaster.getAssemblyId().getId() + "", 
																			returnedAssemblyCandidateMaster.getName(),
																			Integer.parseInt(returnedAssemblyCandidateMaster.getElectionYear()),
																			returnedAssemblyCandidateMaster.getAcandidateNo(),
																			returnedAssemblyCandidateMaster.getPartyId().getSymbolId().getNameWithExt(),
																			returnedAssemblyCandidateMaster.getPartyId().getId()+"",
																			returnedAssemblyCandidateMaster.getNominatedAs()+""
																	      );	
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyCandidateServiceImpl :==> saveAndUpdate ==> Ended");
		return assemblyCandidateMasterDTONew;
	}

	
	@Override
	public List<NameValue> getAssemblyCandidateList(Integer id)throws CustomRuntimeException {
		List<NameValue> loksabhaCandidateList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     AssemblyCandidateServiceImpl :==> getAssemblyCandidateList :==> Started");
		try {
			List<AssemblyCandidateMaster> assemblyCandidateMasterList=assemblyCandidateRepository.getOne(id).getAssemblyId().getAssemblyCandidateMasterList();		
			for(AssemblyCandidateMaster assemblyCandidateMaster:assemblyCandidateMasterList) {
				nameValue=new NameValue(assemblyCandidateMaster.getId(),assemblyCandidateMaster.getName());
				loksabhaCandidateList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     AssemblyCandidateServiceImpl :==> getAssemblyCandidateList :==> Ended");
		return loksabhaCandidateList;
	}
	
	
	
	
	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     AssemblyCandidateServiceImpl :==> deleteOneRecord ==> Started");
		boolean isRecordDelete = true;
		try {
			assemblyCandidateRepository.deleteById(id);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyCandidateServiceImpl :==> deleteOneRecord ==> Ended");
		return isRecordDelete;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {
		log.info("     AssemblyCandidateServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isRecordDelete = true;
		try {
			assemblyCandidateRepository.deleteAssemblyWithIds(recordIdArray);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyCandidateServiceImpl :==> deleteMultipleRecords ==> Started");
		return isRecordDelete;
	}

	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     AssemblyCandidateServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("assemblyNameId")&&!fieldName.equals("acandidateName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}

			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one
				//System.out.println("Add CCCCC  "+parentIdValue);
				recordFound=this.assemblyCandidateRepository.existsByAssemblyIdAndAssemblyCandidateName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    
				//System.out.println("Add BBBBBBBB  "+recordFound);
			}
			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.assemblyCandidateRepository.existsByAssemblyIdAndAssemblyCandidateNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				//System.out.println("Edit BBBBBBBB  "+recordFound);
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     AssemblyCandidateServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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
