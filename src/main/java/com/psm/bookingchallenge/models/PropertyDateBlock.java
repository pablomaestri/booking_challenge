package com.psm.bookingchallenge.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "property_date_blocks")
public class PropertyDateBlock {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        @ManyToOne(fetch = FetchType.EAGER, optional = false)
        @JoinColumn(name = "property_id", nullable = false)
        private Property property;
        private LocalDateTime blockFrom;
        private LocalDateTime blockTo;


        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public Property getProperty() {
                return property;
        }

        public void setProperty(Property property) {
                this.property = property;
        }

        public LocalDateTime getBlockFrom() {
                return blockFrom;
        }

        public void setBlockFrom(LocalDateTime blockFrom) {
                this.blockFrom = blockFrom;
        }

        public LocalDateTime getBlockTo() {
                return blockTo;
        }

        public void setBlockTo(LocalDateTime blockTo) {
                this.blockTo = blockTo;
        }
}
