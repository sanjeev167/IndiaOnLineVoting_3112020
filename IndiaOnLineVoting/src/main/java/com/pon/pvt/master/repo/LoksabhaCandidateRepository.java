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

import com.pon.pvt.master.entity.LoksabhaCandidateMaster;


/**
 * @author Sanjeev
 *
 */
@Repository
public interface LoksabhaCandidateRepository extends JpaRepository<LoksabhaCandidateMaster, Integer> {

	@Query(value = "SELECT * FROM LoksabhaCandidateMaster", nativeQuery = true)
	List<LoksabhaCandidateMaster> findAllByName(List<String> listOfLoksabhaCandidateNames);
	
	@Query("SELECT CASE WHEN COUNT(lcm.name) > 0 THEN 'true' ELSE 'false' END FROM LoksabhaCandidateMaster lcm "
			+ " where lcm.loksabhaId.id in ?1 and lcm.name in ?2")
    public Boolean existsByLoksabhaIdAndLoksabhaCandidateName(Integer parentId,String loksabhaCandidateName);

	@Modifying
	@Transactional
	@Query("delete from LoksabhaCandidateMaster lcm where lcm.id in ?1")
	void deleteAssemblyWithIds(Integer[] recordIdArray);
	
	@Query("SELECT CASE WHEN COUNT(lcm.name) > 0 THEN 'true' ELSE 'false' END FROM LoksabhaCandidateMaster lcm where lcm.loksabhaId.id in ?1 and lcm.name in ?2 and lcm.id <> ?3")	
	boolean existsByLoksabhaIdAndLoksabhaCandidateNameExceptThisId(int parseInt, String loksabhaCandidateName, int id);
}
