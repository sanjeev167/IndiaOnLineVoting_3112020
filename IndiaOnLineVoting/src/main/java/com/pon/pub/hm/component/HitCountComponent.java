/**
 * 
 */
package com.pon.pub.hm.component;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author Sanjeev
 *
 */
@Service
public class HitCountComponent {
	private Integer hitCount=0;
	private Integer liveSessionCount=0;
	public Integer getHitCount() {
		return hitCount;
	}
	
	
	
	public HitCountComponent(Integer hitCount, Integer liveSessionCount) {
		super();
		this.hitCount = hitCount;
		this.liveSessionCount = liveSessionCount;
	}

	public HitCountComponent() {}



	public Integer getLiveSessionCount() {
		return liveSessionCount;
	}



	public void setLiveSessionCount(Integer liveSessionCount) {
		this.liveSessionCount = liveSessionCount;
	}



	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}

	

}
