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

import com.pon.pvt.master.entity.PartyMaster;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface PartyRepository extends JpaRepository<PartyMaster, Integer> {

	@Query(value = "SELECT * FROM PartyMaster", nativeQuery = true)
	List<PartyMaster> findAllByName(List<String> listOfPartyNames);
	
	
	@Query("SELECT CASE WHEN COUNT(pm.name) > 0 THEN 'true' ELSE 'false' END FROM PartyMaster pm "
			+ " where pm.stateId.id in ?1 and pm.name in ?2")
    public Boolean existsByStateIdAndPartyName(Integer parentId,String partyName);
	

	@Modifying
	@Transactional
	@Query("delete from PartyMaster pm where pm.id in ?1")
	void deletePartyWithIds(Integer[] recordIdArray);

	@Query("SELECT CASE WHEN COUNT(name) > 0 THEN 'true' ELSE 'false' END FROM PartyMaster pm where pm.stateId.id in ?1 and pm.name in ?2 and pm.id <> ?3")	
	boolean existsByStateIdAndPartyNameExceptThisId(int parseInt, String partyName, int id);
}
