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

import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.dto.PollingBoothMasterDTO;
import com.pon.pvt.master.entity.AssemblyMaster;
import com.pon.pvt.master.entity.PollingBoothMaster;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.StateRepository;
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
public class PollingBoothServiceImpl implements PollingBoothService {

	static final Logger log = LoggerFactory.getLogger(PollingBoothServiceImpl.class);

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

	@Override
	public DataTableResults<PollingBoothMasterDTO> loadGrid(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     PollingBoothServiceImpl :==> loadGrid ==> Started");
		DataTableResults<PollingBoothMasterDTO> dataTableResult;
		try {
			DataTableRequest<PollingBoothMaster> dataTableInRQ = new DataTableRequest<PollingBoothMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;
			//CAST ('100' AS INTEGER);
			if (whereClauseForBaseQuery.equals(""))
				baseQuery = "select pm.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName,"
						+ " am.name as assemblyName,pm.name as pollingBoothName,  pm.booth_no  as pollingBoothNo," + " (SELECT COUNT(1) FROM polling_booth_master ) AS totalrecords"
						+ " from polling_booth_master pm, assembly_master am, loksabha_master lm , state_master sm"
						+ " where am.loksabha_id=lm.id and lm.state_id=sm.id and pm.assembly_id=am.id";
			else
				baseQuery = "select pm.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName,"
						+ " am.name as assemblyName,pm.name as pollingBoothName,  pm.booth_no  as pollingBoothNo,"
						+ " (SELECT COUNT(1) FROM polling_booth_master pm, assembly_master am, loksabha_master lm , state_master sm where am.loksabha_id=lm.id and lm.state_id=sm.id and "
						+ whereClauseForBaseQuery + ") AS totalrecords"
						+ " from polling_booth_master pm, assembly_master am, loksabha_master lm , state_master sm"
						+ " where am.loksabha_id=lm.id and lm.state_id=sm.id and pm.assembly_id=am.id and " + whereClauseForBaseQuery;

			 //System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "PollingBoothMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<PollingBoothMasterDTO> pollingBoothMasterList = query.getResultList();

			dataTableResult = new DataTableResults<PollingBoothMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(pollingBoothMasterList);
			if (!AppUtil.isObjectEmpty(pollingBoothMasterList)) {
				dataTableResult.setRecordsTotal(pollingBoothMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(pollingBoothMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(pollingBoothMasterList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     PollingBoothServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public PollingBoothMasterDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     PollingBoothServiceImpl :==> getReordById ==> Started");
		PollingBoothMasterDTO pollingBoothMasterDTO;
		try {
			PollingBoothMaster pollingBoothMaster = pollingBoothRepository.getOne(id);
			pollingBoothMasterDTO = new PollingBoothMasterDTO();
			pollingBoothMasterDTO.setId(pollingBoothMaster.getId());
			pollingBoothMasterDTO.setStateNameId(pollingBoothMaster.getAssemblyId().getLoksabhaId().getStateId().getId() + "");			
			pollingBoothMasterDTO.setLoksabhaNameId(pollingBoothMaster.getAssemblyId().getLoksabhaId().getId() + "");	
			pollingBoothMasterDTO.setAssemblyNameId(pollingBoothMaster.getAssemblyId().getId() + "");			
			pollingBoothMasterDTO.setPollingBoothName(pollingBoothMaster.getName());
			pollingBoothMasterDTO.setPollingBoothNo(pollingBoothMaster.getBoothNo());			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     PollingBoothServiceImpl :==> getReordById ==> Ended");
		return pollingBoothMasterDTO;
	}

	@Override
	public PollingBoothMasterDTO saveAndUpdate(PollingBoothMasterDTO pollingBoothMasterDTO) throws CustomRuntimeException {
		log.info("     PollingBoothServiceImpl :==> saveAndUpdate ==> Started");
		PollingBoothMaster pollingBoothMaster;
		PollingBoothMasterDTO pollingBoothMasterDTONew;
		try {			
			if (pollingBoothMasterDTO.getId() != null) {//Edit case				
				pollingBoothMaster = pollingBoothRepository.getOne(pollingBoothMasterDTO.getId());				
			}
			else {	//Add case				
				pollingBoothMaster = new PollingBoothMaster();				
			}			
			pollingBoothMaster.setAssemblyId(assemblyRepository.getOne(Integer.parseInt(pollingBoothMasterDTO.getAssemblyNameId())));
			pollingBoothMaster.setName(pollingBoothMasterDTO.getPollingBoothName());
			pollingBoothMaster.setBoothNo(pollingBoothMasterDTO.getPollingBoothNo());
			
			PollingBoothMaster returnedPollingBoothMaster = pollingBoothRepository.saveAndFlush(pollingBoothMaster);

			pollingBoothMasterDTONew = new PollingBoothMasterDTO(
					returnedPollingBoothMaster.getId(),
					returnedPollingBoothMaster.getAssemblyId().getLoksabhaId().getStateId().getId() + "",
					returnedPollingBoothMaster.getAssemblyId().getLoksabhaId().getId() + "", 
					returnedPollingBoothMaster.getAssemblyId().getId() + "",
					returnedPollingBoothMaster.getName(),
					returnedPollingBoothMaster.getBoothNo());			
			
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     PollingBoothServiceImpl :==> saveAndUpdate ==> Ended");
		return pollingBoothMasterDTONew;
	}

	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     PollingBoothServiceImpl :==> deleteOneRecord ==> Started");
		boolean isRecordDelete = true;
		try {
			pollingBoothRepository.deleteById(id);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     PollingBoothServiceImpl :==> deleteOneRecord ==> Ended");
		return isRecordDelete;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {
		log.info("     PollingBoothServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isRecordDelete = true;
		try {
			pollingBoothRepository.deletePollingBoothWithIds(recordIdArray);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     PollingBoothServiceImpl :==> deleteMultipleRecords ==> Started");
		return isRecordDelete;
	}

	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     PollingBoothServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
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
				//System.out.println("Add CCCCC  "+parentIdValue);
				recordFound=this.pollingBoothRepository.existsByAssemblyIdAndPollingBoothName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    
				//System.out.println("Add BBBBBBBB  "+recordFound);
			}
			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.pollingBoothRepository.existsByAssemblyIdAndPollingBoothNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				//System.out.println("Edit BBBBBBBB  "+recordFound);
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PollingBoothServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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
	public List<NameValue> getPollingBoothList(Integer id) throws CustomRuntimeException {
		List<NameValue> pollingBoothList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     PollingBoothServiceImpl :==> pollingBoothList :==> Started");
		try {
			List<PollingBoothMaster> pollingBoothMasterList=assemblyRepository.getOne(id).getPollingBoothMasterList();		
			for(PollingBoothMaster pollingBoothMaster:pollingBoothMasterList) {
				nameValue=new NameValue(pollingBoothMaster.getId(),pollingBoothMaster.getName());
				pollingBoothList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PollingBoothServiceImpl :==> pollingBoothList :==> Ended");
		return pollingBoothList;
	}
	
	
	
	
	

}
