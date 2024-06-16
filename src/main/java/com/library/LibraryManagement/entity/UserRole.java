package com.library.LibraryManagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



public enum UserRole {
    ADMIN,
    USER,
    MODERATOR
}