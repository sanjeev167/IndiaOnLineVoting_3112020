/**
 * 
 */
package com.support.util;

/**
 * @author Sanjeev
 *
 */
public class AppConstants {	
	
	//Persistence unit 
    public static final String JPA_UNIT_LOSTVOTE ="LostVote";
   
	
	//Define all the entities package names here.
    public static final String[] LOST_VOTE_PKG_ENTITIES_ARRAY=new String[] {"com.pon.pvt.master.entity",
    		                                                                "com.pon.pub.hm.entity",
    		                                                                "com.pon.pvt.voter.entity",
    		                                                                "com.pon.pvt.voting.entity"
                                                                             };    
    
  //Define all the repositories package names here.
    public static final String PKG_REPO_MASTERS = "com.pon.pvt.master.repo";
    
    public static final String PKG_REPO_PUB = "com.pon.pub.hm.repo";
    public static final String PKG_REPO_VOTER = "com.pon.pvt.voter.repo";
    public static final String PKG_REPO_VOTING = "com.pon.pvt.voting.repo";
    
    
    
     
}
