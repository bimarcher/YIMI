/**
 * 
 */
package com.yimi.archer.eo

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author chezg
 *
 */
@Entity
@Table(name = "f_role")
public class RoleEO{
	@Id
	@GeneratedValue
	long id;
	
	String name;
	
	String right;
	
}
