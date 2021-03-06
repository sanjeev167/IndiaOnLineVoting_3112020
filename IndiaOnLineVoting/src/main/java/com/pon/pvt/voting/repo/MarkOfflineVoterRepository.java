/**
 * 
 */
package com.pon.pvt.voting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pon.pvt.voting.entity.MarkOfflineVoter;

/**
 * @author Sanjeev
 *
 */

@Repository
public interface MarkOfflineVoterRepository extends JpaRepository<MarkOfflineVoter, Integer>{

	MarkOfflineVoter findByVoterId(String voterId);

}
