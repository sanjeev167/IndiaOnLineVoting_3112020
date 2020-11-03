/**
 * 
 */
package com.support.custom.validation.interfaceForServices;

import com.support.custom.exception.CustomRuntimeException;

/**
 * @author Sanjeev
 *
 */
public interface TreeMenuValidation {
	
	boolean parentNodeRequiredWithTreeNodeType(
			Object nodeTypeValue, 
			String nodeTypeName, 
			
			Object parentNodeCountValue,
			String parentNodeCount,
			
			Object parentNodeValue, 
			String parentNodeName ) throws UnsupportedOperationException, CustomRuntimeException;

}
