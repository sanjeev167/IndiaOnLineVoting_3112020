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

import com.pon.pvt.master.dto.CountryMasterDTO;
import com.pon.pvt.master.dto.NameValue;
import com.pon.pvt.master.entity.CountryMaster;
import com.pon.pvt.master.repo.CountryRepository;
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
public class CountryServiceImpl implements CountryService {

	static final Logger log = LoggerFactory.getLogger(CountryServiceImpl.class);

	/** The entity manager. */
	@Autowired
	@PersistenceContext(unitName= AppConstants.JPA_UNIT_LOSTVOTE)
	private EntityManager entityManager;

	@Autowired
	CountryRepository countryRepository;

	// Using constructor mapping
	@Override
	public DataTableResults<CountryMasterDTO> loadGrid(HttpServletRequest request,String whereClauseForBaseQuery) throws CustomRuntimeException{
		log.info("     CountryServiceImpl :==> loadGrid :==> Started");
		DataTableResults<CountryMasterDTO> dataTableResult ;
		try {
			DataTableRequest<CountryMaster> dataTableInRQ = new DataTableRequest<CountryMaster>(request);
			PaginationCriteria pagination = dataTableInRQ.getPaginationRequest();

			String baseQuery = "SELECT id as id, name as name, sortname as sortname,phonecode as phonecode,"
					+ " (SELECT COUNT(1) FROM country_master "+whereClauseForBaseQuery+") AS totalrecords  FROM country_master "+whereClauseForBaseQuery;

			String paginatedQuery = AppUtil.buildPaginatedQuery(baseQuery, pagination);
			Query query = entityManager.createNativeQuery(paginatedQuery, "CountryMasterDTOMapping");
			@SuppressWarnings("unchecked")
			List<CountryMasterDTO> countryMasterList = query.getResultList();
			dataTableResult = new DataTableResults<CountryMasterDTO>();
			dataTableResult.setDraw(dataTableInRQ.getDraw());
			dataTableResult.setListOfDataObjects(countryMasterList);
			if (!AppUtil.isObjectEmpty(countryMasterList)) {
				dataTableResult.setRecordsTotal(countryMasterList.get(0).getTotalrecords().toString());
				if (dataTableInRQ.getPaginationRequest().isFilterByEmpty()) {
					dataTableResult.setRecordsFiltered(countryMasterList.get(0).getTotalrecords().toString());
				} else {
					dataTableResult.setRecordsFiltered(Integer.toString(countryMasterList.size()));
				}
			}
		}catch(Exception ex) {			
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> loadGrid :==> Ended");
		return dataTableResult;
	}

	// This will directly put your result into your mapped dto
	@Transactional
	@Override
	public CountryMasterDTO getReordById(Integer id) throws CustomRuntimeException{
		log.info("     CountryServiceImpl :==> getReordById :==> Started");
		List<CountryMasterDTO> countryMasterDTOs;
		try {
			countryMasterDTOs = entityManager
					.createQuery("select new com.pon.pvt.master.dto.CountryMasterDTO(" 
							+ " p.id, "
							+ " p.name, "
							+ " p.sortname, " 
							+ " p.phonecode ) " 
							+ " from CountryMaster p " 
							+ " where p.id =:id",
							CountryMasterDTO.class)
					.setParameter("id", id).getResultList();	

		}catch(Exception ex ) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> getReordById :==> Ended");
		return countryMasterDTOs.get(0);
	}

	@Transactional
	@Override	
	public CountryMasterDTO saveAndUpdate(CountryMasterDTO countryMasterDTO) throws CustomRuntimeException{
		log.info("     CountryServiceImpl :==> saveAndUpdate :==> Stated");
		CountryMaster countryMaster;
		CountryMasterDTO returningCountryMasterDTO;
		try {

			if(countryMasterDTO.getId()!=null)
				countryMaster =countryRepository.getOne(countryMasterDTO.getId());
			else
				countryMaster =new CountryMaster();
			countryMaster.setId(countryMasterDTO.getId());
			countryMaster.setName(countryMasterDTO.getName()); 
			countryMaster.setSortname(countryMasterDTO.getSortname()); 
			countryMaster.setPhonecode(countryMasterDTO.getPhonecode());

			CountryMaster returnedCountryMaster = countryRepository.saveAndFlush(countryMaster);

			returningCountryMasterDTO=new CountryMasterDTO(returnedCountryMaster.getId(), 
					returnedCountryMaster.getName(), 
					returnedCountryMaster.getSortname(), 
					returnedCountryMaster.getPhonecode());
		}catch(Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> saveAndUpdate :==> Ended");
		return returningCountryMasterDTO;
	}

	@Transactional
	@Override
	public boolean deleteOneRecord(Integer id) throws CustomRuntimeException{
		log.info("     CountryServiceImpl :==> deleteOneRecord :==> Stated");
		try {
			countryRepository.deleteById(id);;
		}catch(Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> deleteOneRecord :==> Ended");
		return true;
	}

	@Transactional
	@Override
	public void deleteMultipleRecords(Integer[] recordIdArray) throws CustomRuntimeException{
		log.info("     CountryServiceImpl :==> deleteMultipleRecords :==> Stated");
		try {
			countryRepository.deleteCountryWithIds(recordIdArray);
		}catch(Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> deleteMultipleRecords :==> Ended");
	}

	@Override
	public List<NameValue> getCountryList() throws CustomRuntimeException{
		List<NameValue> countryList=new ArrayList<NameValue>();		
		NameValue nameValue;
		try {
			log.info("     CountryServiceImpl :==> getCountryList :==> Stated");
			List<CountryMaster> countryMasterList=countryRepository.findAll();		
			for(CountryMaster countryMaster:countryMasterList) {
				nameValue=new NameValue(countryMaster.getId(),countryMaster.getName());
				countryList.add(nameValue);
			}	
		}catch(Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> getCountryList :==> Ended");
		return countryList;
	}

	@Override	
	public boolean fieldValueExists(Object fieldValue, String fieldName,Object idValue, String idFieldName)	throws CustomRuntimeException {		
		log.info("     CountryServiceImpl :==> fieldValueExists :==> Stated");
		Assert.notNull(fieldName);
		boolean recordFound=false;
		try {
			
			if (!fieldName.equals("name")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}
			if (!idFieldName.equals("id")) {
				throw ExceptionApplicationUtility.wrapRunTimeException(new UnsupportedOperationException("Field name not supported"));            
			}
			if (fieldValue == null) {
				return false;
			}
			//This is an add case
			if(idValue==null) {
				recordFound=this.countryRepository.existsByCountryName(fieldValue.toString()); 				
			}
			else {				
				recordFound=this.countryRepository.existsByCountryNameExceptThisId(fieldValue.toString(),Integer.parseInt(idValue.toString()));				
				
				//if record found other than this id, it means that there is some other record which has this name. Unique check 
				//should be applied otherwise this check should be avoided.
				
			}
			
		}catch(Exception ex) {
			throw ExceptionApplicationUtility.wrapRunTimeException(ex);
		}
		log.info("     CountryServiceImpl :==> fieldValueExists :==> Ended");
		return recordFound;
	}

	@Override
	public boolean fieldValueExists(Object value, String fieldName) throws CustomRuntimeException {
		// TODO Auto-generated method stub
		return false;
	}


}//End of CountryServiceImpl
