/**
 * 
 */
package com.pon.pvt.voting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pon.pvt.voting.entity.LoksabhaVoteRepository;
import com.pon.pvt.voting.entity.VoteRepository;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface LoksabhaVoteRepositoryRepository extends   JpaRepository<LoksabhaVoteRepository, Integer>{
	
	
	

}