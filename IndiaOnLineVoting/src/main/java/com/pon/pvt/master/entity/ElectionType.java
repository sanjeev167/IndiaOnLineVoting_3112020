/**
 * 
 */
package com.pon.pvt.master.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Sanjeev
 */
@Entity
@Table(name = "election_type")
@NamedQueries({
    @NamedQuery(name = "ElectionType.findAll", query = "SELECT e FROM ElectionType e"),
    @NamedQuery(name = "ElectionType.findById", query = "SELECT e FROM ElectionType e WHERE e.id = :id"),
    @NamedQuery(name = "ElectionType.findByName", query = "SELECT e FROM ElectionType e WHERE e.name = :name")})
public class ElectionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;

    public ElectionType() {
    }

    public ElectionType(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ElectionType)) {
            return false;
        }
        ElectionType other = (ElectionType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.demo.ElectionType[ id=" + id + " ]";
    }
    
}
