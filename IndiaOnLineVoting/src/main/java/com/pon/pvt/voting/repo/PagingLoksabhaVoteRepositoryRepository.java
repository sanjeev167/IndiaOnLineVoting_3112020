/**
 * 
 */
package com.pon.pvt.voting.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pon.pvt.voting.entity.LoksabhaVoteRepository;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface PagingLoksabhaVoteRepositoryRepository extends   PagingAndSortingRepository<LoksabhaVoteRepository, Integer>{
	
	
	//public Page<LoksabhaVoteRepository> getLoksabhaBasedPagedResult(String StateId, String LoksabhaId, Pageable pageable);
	
	
}
