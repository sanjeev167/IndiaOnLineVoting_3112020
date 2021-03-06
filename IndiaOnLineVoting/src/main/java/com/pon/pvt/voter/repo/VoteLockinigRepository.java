/**
 * 
 */
package com.pon.pvt.voter.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pvt.voter.entity.VoteLockinig;

/**
 * @author Sanjeev
 *
 */

@Repository
public interface VoteLockinigRepository extends  JpaRepository<VoteLockinig, Integer> {

	@Modifying
	@Transactional
	@Query("delete from VoteLockinig sm where sm.id in ?1")
	void deleteVoteLockinigWithIds(Integer[] recordIdArray);	
	//@Query(value = "SELECT min(price) FROM Product")
	
    @Query("select vl from VoteLockinig vl where vl.id= (select max(vli.id) from VoteLockinig vli where vli.onlineVoterAccountId.id=vl.onlineVoterAccountId.id) and vl.onlineVoterAccountId.id=?1")
	VoteLockinig getTheLatestVoteLock(Integer accountId);

	
}
