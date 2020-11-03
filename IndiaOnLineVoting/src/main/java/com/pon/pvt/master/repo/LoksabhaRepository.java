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

import com.pon.pvt.master.entity.LoksabhaMaster;
import com.pon.pvt.master.entity.StateMaster;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface LoksabhaRepository extends JpaRepository<LoksabhaMaster, Integer> {

	@Query(value = "SELECT * FROM LoksabhaMaster", nativeQuery = true)
	List<StateMaster> findAllByName(List<String> listOfLoksabhaNames);
	
	
	@Query("SELECT CASE WHEN COUNT(sm.name) > 0 THEN 'true' ELSE 'false' END FROM LoksabhaMaster sm "
			+ " where sm.stateId.id in ?1 and sm.name in ?2")
    public Boolean existsByStateIdAndLoksabhaName(Integer parentId,String loksabhaName);
	

	@Modifying
	@Transactional
	@Query("delete from LoksabhaMaster sm where sm.id in ?1")
	void deleteLoksabhaWithIds(Integer[] recordIdArray);

	@Query("SELECT CASE WHEN COUNT(name) > 0 THEN 'true' ELSE 'false' END FROM LoksabhaMaster sm where sm.stateId.id in ?1 and sm.name in ?2 and sm.id <> ?3")	
	boolean existsByStateIdAndLoksabhaNameExceptThisId(int parseInt, String loksabhaName, int id);
}
