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
import com.pon.pvt.master.dto.LoksabhaMasterDTO;
import com.pon.pvt.master.entity.LoksabhaMaster;
import com.pon.pvt.master.repo.CountryRepository;
import com.pon.pvt.master.repo.LoksabhaRepository;
import com.pon.pvt.master.repo.StateRepository;
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
public class LoksabhaServiceImpl implements LoksabhaService{


	private static final Logger log = LoggerFactory.getLogger(LoksabhaServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	LoksabhaRepository loksabhaRepository;

	@Autowired
    StateRepository stateRepository;
	// Using constructor mapping
	@Override
	public DataTableResults<LoksabhaMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException{
		log.info("     LoksabhaServiceImpl :==> loadGrid :==> Started");
		DataTableResults<LoksabhaMasterDTO> dataTableResult;
		try {
			DataTableRequest<LoksabhaMaster> dataTableInRQ = new DataTableRequest<LoksabhaMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;
			if(whereClauseForBaseQuery.equals(""))
				baseQuery = "SELECT lm.id as id, lm.loksabha_no as loksabhaNo, sm.name as stateName, lm.name as loksabhaName ,  "
						+ "(SELECT COUNT(1) FROM loksabha_master lm, state_master sm where lm.state_id=sm.id "
						+") AS totalrecords  FROM loksabha_master lm , state_master sm "+ 
						"where lm.state_id=sm.id ";
			else
				baseQuery = "SELECT lm.id as id,lm.loksabha_no as loksabhaNo, sm.name as stateName, lm.name as loksabhaName ,  "
						+ "(SELECT COUNT(1) FROM loksabha_master lm, state_master sm where lm.state_id=sm.id and  "
						+ whereClauseForBaseQuery+") AS totalrecords  FROM loksabha_master lm , state_master sm "+ 
						"where lm.state_id=sm.id and "+whereClauseForBaseQuery;

			System.out.println("baseQuery  "+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
			Query query = entityManager.createNativeQuery(paginatedQuery, "LoksabhaMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<LoksabhaMasterDTO> LoksabhaMasterList = query.getResultList();		

			dataTableResult = new DataTableResults<LoksabhaMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(LoksabhaMasterList);
			if (!AppUtil.isObjectEmpty(LoksabhaMasterList)) {
				dataTableResult.setRecordsTotal(LoksabhaMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(LoksabhaMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(LoksabhaMasterList.size()));
				}
			}
		}catch(Exception ex) {
			
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaServiceImpl :==> loadGrid :==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public LoksabhaMasterDTO getReordById(Integer id) throws CustomRuntimeException{
		log.info("     LoksabhaServiceImpl :==> getReordById :==> Started");
		LoksabhaMasterDTO loksabhaMasterDTO;
		try {
			LoksabhaMaster loksabhaMaster =loksabhaRepository.getOne(id);
			loksabhaMasterDTO=new LoksabhaMasterDTO();
			loksabhaMasterDTO.setId(loksabhaMaster.getId());
			loksabhaMasterDTO.setLoksabhaName(loksabhaMaster.getName());
			loksabhaMasterDTO.setStateId(loksabhaMaster.getStateId().getId()+"");
			loksabhaMasterDTO.setLoksabhaNo(loksabhaMaster.getLoksabhaNo());
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaServiceImpl :==> getReordById :==> Ended");
		return loksabhaMasterDTO;
	}

	@Override
	public LoksabhaMasterDTO saveAndUpdate(LoksabhaMasterDTO loksabhaMasterDTO) throws CustomRuntimeException{
		LoksabhaMaster loksabhaMaster;
		log.info("     LoksabhaServiceImpl :==> saveAndUpdate :==> Started");
		LoksabhaMasterDTO loksabhaMasterDTONew;
		try {
			if(loksabhaMasterDTO.getId()!=null)
				loksabhaMaster =loksabhaRepository.getOne(loksabhaMasterDTO.getId());
			else
				loksabhaMaster =new LoksabhaMaster();

			loksabhaMaster.setId(loksabhaMasterDTO.getId());
			loksabhaMaster.setName(loksabhaMasterDTO.getLoksabhaName()); 		
			loksabhaMaster.setStateId(stateRepository.getOne(Integer.parseInt(loksabhaMasterDTO.getStateId()))); 
			loksabhaMaster.setLoksabhaNo(loksabhaMasterDTO.getLoksabhaNo());
			
			LoksabhaMaster returnedLoksabhaMaster = loksabhaRepository.saveAndFlush(loksabhaMaster);

			loksabhaMasterDTONew=new LoksabhaMasterDTO(returnedLoksabhaMaster.getId(), 
					returnedLoksabhaMaster.getStateId().getName(), 
					returnedLoksabhaMaster.getStateId().getId(), 
					returnedLoksabhaMaster.getName(),returnedLoksabhaMaster.getLoksabhaNo());
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaServiceImpl :==> saveAndUpdate :==> Ended");
		return loksabhaMasterDTONew;
	}

	@Override
	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException {
		log.info("     LoksabhaServiceImpl :==> deleteOneRecord :==> Started");
		boolean isDeleted=true;
		try {
			loksabhaRepository.deleteById(id);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     LoksabhaServiceImpl :==> deleteOneRecord :==> Ended");
		return isDeleted;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException{
		log.info("     LoksabhaServiceImpl :==> deleteMultipleRecords :==> Started");
		boolean isDeleted=true;
		try {
			loksabhaRepository.deleteLoksabhaWithIds(recordIdArray);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     LoksabhaServiceImpl :==> deleteMultipleRecords :==> Ended");
		return isDeleted;
	}

	@Override
	public List<NameValue> getLoksabhaList(Integer id)throws CustomRuntimeException {
		List<NameValue> stateList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     LoksabhaServiceImpl :==> getStateList :==> Started");
		try {
			List<LoksabhaMaster> loksabhaMasterList=stateRepository.getOne(id).getLoksabhaMasterList();		
			for(LoksabhaMaster loksabhaMaster:loksabhaMasterList) {
				nameValue=new NameValue(loksabhaMaster.getId(),loksabhaMaster.getName());
				stateList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaServiceImpl :==> getStateList :==> Ended");
		return stateList;
	}


	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     LoksabhaServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("countryId")&&!fieldName.equals("stateName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one				
				recordFound=this.loksabhaRepository.existsByStateIdAndLoksabhaName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    				
			}			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.loksabhaRepository.existsByStateIdAndLoksabhaNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     LoksabhaServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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



