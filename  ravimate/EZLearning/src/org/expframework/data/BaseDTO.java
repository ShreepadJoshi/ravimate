package org.expframework.data;

import java.io.Serializable;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * Base class for Model data objects.
 * 
 * @author ShriKant
 * @version 1.0
 */
public class BaseDTO implements Serializable, Cloneable, Comparable {

    protected static final long serialVersionUID = 4753810862868386037L;

    /**
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringBuilder.reflectionToString(this,
                ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * Check if object passed is equals to the current object
     * 
     * @param o
     *            object to check the equility to
     * @return true if objects are equal else return false
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    /**
     * Compares the current object with the object passed
     * 
     * @param o
     *            object to compare with
     * @return int
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(Object o) {
        return CompareToBuilder.reflectionCompare(this, o);
    }
}