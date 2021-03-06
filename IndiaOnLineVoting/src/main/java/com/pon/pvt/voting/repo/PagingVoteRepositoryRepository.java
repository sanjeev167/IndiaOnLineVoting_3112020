/**
 * 
 */
package com.pon.pvt.voting.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.pon.pvt.voting.entity.VoteRepository;

/**
 * @author Sanjeev
 *
 */
@Repository
public interface PagingVoteRepositoryRepository extends PagingAndSortingRepository<VoteRepository, Integer>{

	
	//public Page<VoteRepository> getAssemblyBasedPagedResult(String LoksabhaId, String AssemblyId, Pageable pageable);
}
