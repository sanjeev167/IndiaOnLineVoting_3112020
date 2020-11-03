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

import com.pon.pvt.master.entity.PartySymbol;



/**
 * @author Sanjeev
 *
 */
@Repository
public interface PartySymbolRepository extends JpaRepository<PartySymbol, Integer> {
    
	

	@Query(value = "SELECT * FROM PartySymbol", nativeQuery = true)
	List<PartySymbol> findAllByName(List<String> listOfPartySymbolNames);
	
	/*@Query("SELECT CASE WHEN COUNT(name) > 0 THEN 'true' ELSE 'false' END FROM PartySymbol cm where cm.name in ?1")
    public Boolean existsByPartySymbolName(String partySymbolName);
	

	@Modifying
	@Transactional
	@Query("delete from PartySymbol cm where cm.id in ?1")
	void deletePartySymbolWithIds(Integer[] recordIdArray);

	@Query("SELECT CASE WHEN COUNT(name) > 0 THEN 'true' ELSE 'false' END FROM PartySymbol psm where psm.name in ?1 and cm.id <> ?2")
	boolean existsByPartySymbolNameExceptThisId(String string, int id);*/
}
