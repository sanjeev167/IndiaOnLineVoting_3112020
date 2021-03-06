/**
 * 
 */
package com.pon.pvt.voting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pon.pvt.voting.entity.MarkOnlineVoter;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface MarkOnlineVoterRepository extends JpaRepository<MarkOnlineVoter, Integer>{

	MarkOnlineVoter findByVoterId(String voterId);

}
