package com.cgfy.user.base.domain.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xiayongshou
 */
public interface BaseModel extends Serializable {
    default void setId(String id){};
    default void setStatus(String status){};
    default void setDone(Date done){};
    default void setCommitted(Date committed){};
    default void setProcessId(String processId){};
}
