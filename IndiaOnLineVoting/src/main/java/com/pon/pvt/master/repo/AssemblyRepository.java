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

import com.pon.pvt.master.entity.AssemblyMaster;


/**
 * @author Sanjeev
 *
 */
@Repository
public interface AssemblyRepository extends JpaRepository<AssemblyMaster, Integer> {

	@Query(value = "SELECT * FROM AssemblyMaster", nativeQuery = true)
	List<AssemblyMaster> findAllByName(List<String> listOfAssemblyNames);
	
	@Query("SELECT CASE WHEN COUNT(am.name) > 0 THEN 'true' ELSE 'false' END FROM AssemblyMaster am "
			+ " where am.loksabhaId.id in ?1 and am.name in ?2")
    public Boolean existsByLoksabhaIdAndAssemblyName(Integer parentId,String loksabhaName);

	@Modifying
	@Transactional
	@Query("delete from AssemblyMaster am where am.id in ?1")
	void deleteAssemblyWithIds(Integer[] recordIdArray);
	
	@Query("SELECT CASE WHEN COUNT(am.name) > 0 THEN 'true' ELSE 'false' END FROM AssemblyMaster am where am.loksabhaId.id in ?1 and am.name in ?2 and am.id <> ?3")	
	boolean existsByLoksabhaIdAndAssemblyNameExceptThisId(int parseInt, String assemblyName, int id);
}
