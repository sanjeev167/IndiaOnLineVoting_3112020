/**
 * 
 */
package com.pon.config.sec;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sanjeev
 *
 */
public class ActiveUserStore {
	public List<String> users;
	 
    public ActiveUserStore() {
        users = new ArrayList<String>();
    }

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
    
    
}
