package com.cesardiaz.backend.f1.backendf1.models;

import java.io.Serializable;

import org.springframework.data.domain.Persistable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractPersistableCustom<PK extends Serializable> implements Persistable<Long> {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.data.domain.Persistable#getId()
         */
        @Override
        public Long getId() {
                return id;
        }

        /**
         * Sets the id of the entity.
         * 
         * @param id the id to set
         */
        protected void setId(final Long id) {

                this.id = id;
        }

        /*
         * (non-Javadoc)
         * 
         * @see org.springframework.data.domain.Persistable#isNew()
         */
        @Override
        public boolean isNew() {

                return null == this.id;
        }

        //We have removed toString(), hashCode() and equals() methods. By adding them end up issues with OpenJPA
}

