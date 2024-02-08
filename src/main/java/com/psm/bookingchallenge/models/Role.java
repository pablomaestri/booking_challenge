package com.psm.bookingchallenge.models;

import jakarta.persistence.*;


@Entity
@Table(name = "roles")
public class Role {


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long id;
        String username;
        String authority;
        @Column(name = "user_id")
        Long userId;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUsername() {
                return username;
        }

        public void setUsername(String username) {
                this.username = username;
        }

        public String getAuthority() {
                return authority;
        }

        public void setAuthority(String authority) {
                this.authority = authority;
        }

        public Long getUserId() {
                return userId;
        }

        public void setUserId(Long userId) {
                this.userId = userId;
        }
}
