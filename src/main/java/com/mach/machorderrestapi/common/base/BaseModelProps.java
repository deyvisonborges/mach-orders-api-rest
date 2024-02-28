package com.mach.machorderrestapi.common.base;

import java.time.LocalDateTime;
import java.util.UUID;

public class BaseModelProps {
    protected UUID id;
    protected Boolean active;
    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

    public BaseModelProps(UUID id, Boolean active, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.active = active;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
