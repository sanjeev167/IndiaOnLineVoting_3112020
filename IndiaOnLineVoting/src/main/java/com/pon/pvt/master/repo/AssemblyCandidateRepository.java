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

import com.pon.pvt.master.entity.AssemblyCandidateMaster;


/**
 * @author Sanjeev
 *
 */
@Repository
public interface AssemblyCandidateRepository extends JpaRepository<AssemblyCandidateMaster, Integer> {

	@Query(value = "SELECT * FROM AssemblyCandidateMaster", nativeQuery = true)
	List<AssemblyCandidateMaster> findAllByName(List<String> listOfAssemblyCandidateNames);
	
	@Query("SELECT CASE WHEN COUNT(acm.name) > 0 THEN 'true' ELSE 'false' END FROM AssemblyCandidateMaster acm "
			+ " where acm.assemblyId.id in ?1 and acm.name in ?2")
    public Boolean existsByAssemblyIdAndAssemblyCandidateName(Integer parentId,String assemblyCandidateName);

	@Modifying
	@Transactional
	@Query("delete from AssemblyCandidateMaster acm where acm.id in ?1")
	void deleteAssemblyWithIds(Integer[] recordIdArray);
	
	@Query("SELECT CASE WHEN COUNT(acm.name) > 0 THEN 'true' ELSE 'false' END FROM AssemblyCandidateMaster acm where acm.assemblyId.id in ?1 and acm.name in ?2 and acm.id <> ?3")	
	boolean existsByAssemblyIdAndAssemblyCandidateNameExceptThisId(int parseInt, String assemblyCandidateName, int id);
}
