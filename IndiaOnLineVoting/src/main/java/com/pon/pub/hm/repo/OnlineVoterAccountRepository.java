package com.pon.pub.hm.repo;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.pon.pub.hm.entity.OnlineVoterAccount;
import com.pon.pvt.master.entity.VotersEnrolled;


@Repository
public interface OnlineVoterAccountRepository extends  JpaRepository<OnlineVoterAccount, Integer> {

	 @Query("SELECT voter FROM OnlineVoterAccount voter where voter.mailId = ?1 ")
	 public Optional<OnlineVoterAccount> findByEmail(String email);		
	
	@Modifying
	@Transactional
	@Query("delete from OnlineVoterAccount voter where voter.id in ?1")
	void deleteOnlineVoterWithIds(Integer[] recordIdArray);
	
	@Query("SELECT CASE WHEN COUNT(voter.mailId) > 0 THEN 'true' ELSE 'false' END FROM OnlineVoterAccount voter "
			+ " where voter.mailId in ?1")
    public Boolean existsByOnlineVoterLoginId(String email);
	
	@Query("SELECT CASE WHEN COUNT(voter.mailId) > 0 THEN 'true' ELSE 'false' END FROM OnlineVoterAccount voter "
			+ " where voter.mailId in ?1 and voter.id <> ?2")
    public Boolean existsByOnlineVoterLoginIdExceptThisId(String email,Integer id);

	
	public OnlineVoterAccount findByMailId(String mailId);

	public OnlineVoterAccount findBySecret(String secret);

	public OnlineVoterAccount findByVoterId(VotersEnrolled votersEnrolled);
}
