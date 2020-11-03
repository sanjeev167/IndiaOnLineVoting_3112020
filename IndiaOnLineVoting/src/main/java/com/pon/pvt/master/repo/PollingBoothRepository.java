/**
 * 
 */
package com.pon.pvt.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pon.pvt.master.entity.PollingBoothMaster;


/**
 * @author Sanjeev
 *
 */
@Repository
public interface PollingBoothRepository extends JpaRepository<PollingBoothMaster, Integer> {

	@Query(value = "SELECT * FROM PollingBoothMaster", nativeQuery = true)
	List<PollingBoothMaster> findAllByName(List<String> listOfPollingBoothNames);
	
	@Query("SELECT CASE WHEN COUNT(pm.name) > 0 THEN 'true' ELSE 'false' END FROM PollingBoothMaster pm "
			+ " where pm.assemblyId.id in ?1 and pm.name in ?2")
    public Boolean existsByAssemblyIdAndPollingBoothName(Integer parentId,String pollingBoothName);

	@Modifying
	@Transactional
	@Query("delete from PollingBoothMaster pm where pm.id in ?1")
	void deletePollingBoothWithIds(Integer[] recordIdArray);
	
	@Query("SELECT CASE WHEN COUNT(pm.name) > 0 THEN 'true' ELSE 'false' END FROM PollingBoothMaster pm where pm.assemblyId.id in ?1 and pm.name in ?2 and pm.id <> ?3")	
	boolean existsByAssemblyIdAndPollingBoothNameExceptThisId(int parseInt, String poolingBoothName, int id);
	
}
