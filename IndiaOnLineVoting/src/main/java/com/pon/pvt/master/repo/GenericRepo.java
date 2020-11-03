/**
 * 
 */
package com.pon.pvt.master.repo;
import java.util.List;

import com.pon.pvt.master.entity.CountryMaster;
/**
 * @author Sanjeev
 *
 */
public interface GenericRepo {
	
	List<CountryMaster> getCountryMasterList();
	
	
}//end of GenericRepo