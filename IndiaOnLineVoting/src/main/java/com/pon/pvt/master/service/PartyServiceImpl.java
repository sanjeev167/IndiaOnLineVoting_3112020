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
import com.pon.pvt.master.dto.PartyMasterDTO;
import com.pon.pvt.master.dto.ddData;
import com.pon.pvt.master.entity.PartyMaster;
import com.pon.pvt.master.entity.PartySymbol;
import com.pon.pvt.master.repo.StateRepository;
import com.pon.pvt.master.repo.PartyRepository;
import com.pon.pvt.master.repo.PartySymbolRepository;
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
public class PartyServiceImpl implements PartyService{


	private static final Logger log = LoggerFactory.getLogger(PartyServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	PartyRepository partyRepository;

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	PartySymbolRepository partySymbolRepository;
	// Using constructor mapping
	@Override
	public DataTableResults<PartyMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException{
		log.info("     PartyServiceImpl :==> loadGrid :==> Started");
		DataTableResults<PartyMasterDTO> dataTableResult;
		try {
			DataTableRequest<PartyMaster> dataTableInRQ = new DataTableRequest<PartyMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();
			String baseQuery;
			if(whereClauseForBaseQuery.equals(""))
				baseQuery = "SELECT pm.id as id, sm.name as stateName, pm.name as partyName , pm.party_type as partyType, ps.name_with_ext as base64ImgName,"
						+ "  (SELECT COUNT(1) FROM party_master pm, state_master sm, party_symbol ps where pm.state_id=sm.id and pm.symbol_id=ps.id "
						+") AS totalrecords  FROM party_master pm, state_master sm, party_symbol ps "+ 
						"where pm.state_id=sm.id and pm.symbol_id=ps.id";
			else
				baseQuery = "SELECT pm.id as id, sm.name as stateName, pm.name as partyName , pm.party_type as partyType, ps.name_with_ext as base64ImgName,  "
						+ "(SELECT COUNT(1) FROM party_master pm, state_master sm, party_symbol ps where pm.state_id=sm.id  and pm.symbol_id=ps.id "
						+ whereClauseForBaseQuery+") AS totalrecords  FROM party_master pm, state_master sm, party_symbol ps "+ 
						"where pm.state_id=sm.id and pm.symbol_id=ps.id "+whereClauseForBaseQuery;

           System.out.println("baseQuery = "+baseQuery);
			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);		
			Query query = entityManager.createNativeQuery(paginatedQuery, "PartyMasterDTOMapping");

			@SuppressWarnings("unchecked")
			List<PartyMasterDTO> PartyMasterList = query.getResultList();		

			dataTableResult = new DataTableResults<PartyMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(PartyMasterList);
			if (!AppUtil.isObjectEmpty(PartyMasterList)) {
				dataTableResult.setRecordsTotal(PartyMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(PartyMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(PartyMasterList.size()));
				}
			}
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> loadGrid :==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Override
	public PartyMasterDTO getReordById(Integer id) throws CustomRuntimeException{
		log.info("     PartyServiceImpl :==> getReordById :==> Started");
		PartyMasterDTO partyMasterDTO;
		try {
			PartyMaster partyMaster =partyRepository.getOne(id);
			partyMasterDTO=new PartyMasterDTO();
			partyMasterDTO.setId(partyMaster.getId());
			partyMasterDTO.setPartyTypeId(partyMaster.getPartyType());
			partyMasterDTO.setPartyName(partyMaster.getName());
			partyMasterDTO.setStateNameId(partyMaster.getStateId().getId()+"");
			partyMasterDTO.setPartySymbolId(partyMaster.getSymbolId().getId()+"");
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> getReordById :==> Ended");
		return partyMasterDTO;
	}

	@Override
	public PartyMasterDTO saveAndUpdate(PartyMasterDTO partyMasterDTO) throws CustomRuntimeException{
		PartyMaster partyMaster;
		log.info("     PartyServiceImpl :==> saveAndUpdate :==> Started");
		PartyMasterDTO partyMasterDTONew;
		try {
			if(partyMasterDTO.getId()!=null)
				partyMaster =partyRepository.getOne(partyMasterDTO.getId());
			else
				partyMaster =new PartyMaster();

			partyMaster.setId(partyMasterDTO.getId());
			partyMaster.setName(partyMasterDTO.getPartyName()); 		
			partyMaster.setStateId(stateRepository.getOne(Integer.parseInt(partyMasterDTO.getStateNameId()))); 
			partyMaster.setPartyType(partyMasterDTO.getPartyTypeId());			
			partyMaster.setSymbolId(partySymbolRepository.getOne(Integer.parseInt(partyMasterDTO.getPartySymbolId())));
			
			PartyMaster returnedPartyMaster = partyRepository.saveAndFlush(partyMaster);

			partyMasterDTONew=new PartyMasterDTO(returnedPartyMaster.getId(), 
					returnedPartyMaster.getStateId().getName(), 
					returnedPartyMaster.getStateId().getId()+"", 
					returnedPartyMaster.getName(),
					returnedPartyMaster.getPartyType(),
					returnedPartyMaster.getSymbolId()+"");	
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> saveAndUpdate :==> Ended");
		return partyMasterDTONew;
	}

	@Override
	public boolean deleteOneRecord(Integer id)throws CustomRuntimeException {
		log.info("     PartyServiceImpl :==> deleteOneRecord :==> Started");
		boolean isDeleted=true;
		try {
			partyRepository.deleteById(id);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     PartyServiceImpl :==> deleteOneRecord :==> Ended");
		return isDeleted;
	}

	@Transactional
	@Override
	public boolean deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException{
		log.info("     PartyServiceImpl :==> deleteMultipleRecords :==> Started");
		boolean isDeleted=true;
		try {
			partyRepository.deletePartyWithIds(recordIdArray);
		}catch(Exception ex) {
			isDeleted=false;
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
			}
		log.info("     PartyServiceImpl :==> deleteMultipleRecords :==> Ended");
		return isDeleted;
	}

	@Override
	public List<NameValue> getPartyList(Integer id)throws CustomRuntimeException {
		List<NameValue> partyList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     PartyServiceImpl :==> getPartyList :==> Started");
		try {
			List<PartyMaster> partyMasterList=stateRepository.getOne(id).getPartyMasterList();		
			for(PartyMaster partyMaster:partyMasterList) {
				nameValue=new NameValue(partyMaster.getId(),partyMaster.getName());
				partyList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> getPartyList :==> Ended");
		return partyList;
	}
	@Override
	public List<NameValue> getPartyList()throws CustomRuntimeException {
		List<NameValue> partyList=new ArrayList<NameValue>();		
		NameValue nameValue;		
		log.info("     PartyServiceImpl :==> getPartyList :==> Started");
		try {
			List<PartyMaster> partyMasterList=partyRepository.findAll();		
			for(PartyMaster partyMaster:partyMasterList) {
				nameValue=new NameValue(partyMaster.getId(),partyMaster.getName());
				partyList.add(nameValue);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> getPartyList :==> Ended");
		return partyList;
	}

	@Override
	public List<ddData> getPartySymbolList(String contextPath  ) throws CustomRuntimeException {
		// TODO Auto-generated method stub		
		List<ddData> symbolList=new ArrayList<ddData>();		
		ddData ddData;		
		log.info("     PartyServiceImpl :==> getPartySymbolList :==> Started");
		try {
			List<PartySymbol> partySymbolList=partySymbolRepository.findAll();	
			
			for(PartySymbol partySymbol:partySymbolList) {
				ddData=new ddData(
						partySymbol.getNameWithExt(),
						partySymbol.getId()+"",
						"false",
						partySymbol.getDescription(),
						contextPath+"/resources/assets/ec/img/"+partySymbol.getNameWithExt()						
						);				
				symbolList.add(ddData);
			}		
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> getPartySymbolList :==> Ended");
		
		return symbolList;
		
	}
	
	@Override
	public boolean FieldValueWithParentIdAndChildExists(Object parentIdValue, String parentId,Object fieldValue, String fieldName,Object idValue, String idFieldName)
			throws CustomRuntimeException {

		boolean recordFound=false;
		log.info("     PartyServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Started");
		try {
			Assert.notNull(parentId);Assert.notNull(fieldName);

			if (!parentId.equals("stateId")&&!fieldName.equals("partyName") && !idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}

			if (parentIdValue == null && fieldValue==null) {
				return false;
			}
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue==null) { 
				//Case of adding new one				
				recordFound=this.partyRepository.existsByStateIdAndPartyName(Integer.parseInt(parentIdValue+""),
						fieldValue.toString());    				
			}			
			if(!parentIdValue.equals("")&&!fieldValue.equals("")&& idValue!=null) { 
				//Case of editing existing one
				recordFound=this.partyRepository.existsByStateIdAndPartyNameExceptThisId(Integer.parseInt(parentIdValue+""),
						fieldValue.toString(),Integer.parseInt(idValue.toString()));  
				
			}
			
		}catch(Exception ex) {throw ExceptionApplicationUtility.wrapRunTimeException(ex);}
		log.info("     PartyServiceImpl :==> FieldValueWithParentIdAndChildExists :==> Ended");
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



