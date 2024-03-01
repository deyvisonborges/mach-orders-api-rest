package com.mach.machorderrestapi.common.base;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseModel {
    protected UUID id;
    protected Boolean active;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public BaseModel(UUID id, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id != null ? id : UUID.randomUUID();
        this.active = active != null ? active : true;
        this.createdAt = createdAt != null ? createdAt : LocalDateTime.now();
        this.updatedAt = updatedAt != null ? updatedAt : LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public Boolean getActive() {
        return active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}