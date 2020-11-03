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

import com.pon.pvt.master.dto.AssemblyMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.entity.AssemblyMaster;
import com.pon.pvt.master.entity.StateMaster;
import com.pon.pvt.master.repo.AssemblyRepository;
import com.pon.pvt.master.repo.StateRepository;
import com.pon.pvt.master.repo.LoksabhaRepository;
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
public class AssemblyServiceImpl implements AssemblyService {

	static final Logger log = LoggerFactory.getLogger(AssemblyServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName = AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	StateRepository countryRepository;
	
	@Autowired
	LoksabhaRepository loksabhaRepository;	

	@Autowired
	AssemblyRepository assemblyRepository;

	@Override
	public DataTableResults<AssemblyMasterDTO> loadGrid(HttpServletRequest request, String whereClauseForBaseQuery)
			throws CustomRuntimeException {
		log.info("     AssemblyServiceImpl :==> loadGrid ==> Started");
		DataTableResults<AssemblyMasterDTO> dataTableResult;
		try {
			DataTableRequest<AssemblyMaster> dataTableInRQ = new DataTableRequest<AssemblyMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;

			if (whereClauseForBaseQuery.equals(""))
				baseQuery = "select am.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName,"
						+ " am.name as assemblyName, am.assembly_no as assemblyNo," + " (SELECT COUNT(1) FROM assembly_master ) AS totalrecords"
						+ " from assembly_master am, loksabha_master lm , state_master sm"
						+ " where am.loksabha_id=lm.id and lm.state_id=sm.id ";
			else
				baseQuery = "select am.id as ID," + " sm.name as stateName," + " lm.name as loksabhaName,"
						+ " am.name as assemblyName, am.assembly_no as assemblyNo,"
						+ " (SELECT COUNT(1) FROM assembly_master am, loksabha_master lm , state_master sm where am.loksabha_id=lm.id and lm.state_id=sm.id and "
						+ whereClauseForBaseQuery + ") AS totalrecords"
						+ " from assembly_master am, loksabha_master lm , state_master sm"
						+ " where am.loksabha_id=lm.id and lm.state_id=sm.id and " + whereClauseForBaseQuery;

			// System.out.println("baseQuery ="+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "AssemblyMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<AssemblyMasterDTO> assemblyMasterList = query.getResultList();

			dataTableResult = new DataTableResults<AssemblyMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(assemblyMasterList);
			if (!AppUtil.isObjectEmpty(assemblyMasterList)) {
				dataTableResult.setRecordsTotal(assemblyMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(assemblyMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(assemblyMasterList.size()));
				}
			}

		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyServiceImpl :==> loadGrid ==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public AssemblyMasterDTO getReordById(Integer id) throws CustomRuntimeException {
		log.info("     AssemblyServiceImpl :==> getReordById ==> Started");
		AssemblyMasterDTO assemblyMasterDTO;
		try {
			AssemblyMaster assemblyMaster = assemblyRepository.getOne(id);
			assemblyMasterDTO = new AssemblyMasterDTO();
			assemblyMasterDTO.setId(assemblyMaster.getId());
			assemblyMasterDTO.setStateNameId(assemblyMaster.getLoksabhaId().getStateId().getId() + "");
			assemblyMasterDTO.setLoksabhaNameId(assemblyMaster.getLoksabhaId().getId() + "");
			assemblyMasterDTO.setAssemblyName(assemblyMaster.getName());
			assemblyMasterDTO.setAssemblyNo(assemblyMaster.getAssemblyNo());
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyServiceImpl :==> getReordById ==> Ended");
		return assemblyMasterDTO;
	}

	@Override
	public AssemblyMasterDTO saveAndUpdate(AssemblyMasterDTO assemblyMasterDTO) throws CustomRuntimeException {
		log.info("     AssemblyServiceImpl :==> saveAndUpdate ==> Started");
		AssemblyMaster assemblyMaster;
		AssemblyMasterDTO assemblyMasterDTONew;
		try {			
			if (assemblyMasterDTO.getId() != null) {//Edit case				
				assemblyMaster = assemblyRepository.getOne(assemblyMasterDTO.getId());				
			}
			else {	//Add case				
				assemblyMaster = new AssemblyMaster();				
			}
			assemblyMaster.setName(assemblyMasterDTO.getAssemblyName());			
			assemblyMaster.setLoksabhaId(loksabhaRepository.getOne(Integer.parseInt(assemblyMasterDTO.getLoksabhaNameId())));
			assemblyMaster.setAssemblyNo(assemblyMasterDTO.getAssemblyNo());
			AssemblyMaster returnedAssemblyMaster = assemblyRepository.saveAndFlush(assemblyMaster);

			assemblyMasterDTONew = new AssemblyMasterDTO(returnedAssemblyMaster.getId(),
					returnedAssemblyMaster.getLoksabhaId().getStateId().getId() + "",
					returnedAssemblyMaster.getLoksabhaId().getId() + "", returnedAssemblyMaster.getName(),
					returnedAssemblyMaster.getAssemblyNo());
		} catch (Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyServiceImpl :==> saveAndUpdate ==> Ended");
		return assemblyMasterDTONew;
	}

	
	@Override
	public List<NameValue> getAssemblyList(Integer id)throws CustomRuntimeException {
		List<NameValue> assemblyList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     AssemblyServiceImpl :==> assemblyList :==> Started");
		try {
			List<AssemblyMaster> assemblyMasterList=loksabhaRepository.getOne(id).getAssemblyMasterList();		
			for(AssemblyMaster assemblyMaster:assemblyMasterList) {
				nameValue=new NameValue(assemblyMaster.getId(),assemblyMaster.getName());
				assemblyList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     AssemblyServiceImpl :==> assemblyList :==> Ended");
		return assemblyList;
	}
	
	
	
	
	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException {
		log.info("     AssemblyServiceImpl :==> deleteOneRecord ==> Started");
		boolean isRecordDelete = true;
		try {
			assemblyRepository.deleteById(id);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyServiceImpl :==> deleteOneRecord ==> Ended");
		return isRecordDelete;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException {
		log.info("     AssemblyServiceImpl :==> deleteMultipleRecords ==> Started");
		boolean isRecordDelete = true;
		try {
			assemblyRepository.deleteAssemblyWithIds(recordIdArray);
		} catch (Exception ex) {
			isRecordDelete = false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     AssemblyServiceImpl :==> deleteMultipleRecords ==> Started");
		return isRecordDelete;
	}

	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     AssemblyServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("assemblyNameId")&&!fieldName.equals("pollingBoothName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}

			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one
				//System.out.println("Add CCCCC  "+parentIdValue);
				recordFound=this.assemblyRepository.existsByLoksabhaIdAndAssemblyName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    
				//System.out.println("Add BBBBBBBB  "+recordFound);
			}
			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.assemblyRepository.existsByLoksabhaIdAndAssemblyNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				//System.out.println("Edit BBBBBBBB  "+recordFound);
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     AssemblyServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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
