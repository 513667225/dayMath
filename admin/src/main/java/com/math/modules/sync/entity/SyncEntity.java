package com.math.modules.sync.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-01-05
 */
@TableName("sync")
public class SyncEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "sync_id", type = IdType.AUTO)
    private Integer syncId;

    private String shipmentId;

    private String asin;

    private Integer inCount;

    public Integer getSyncId() {
        return syncId;
    }

    public SyncEntity setSyncId(Integer syncId) {
        this.syncId = syncId;
        return this;
    }
    public String getShipmentId() {
        return shipmentId;
    }

    public SyncEntity setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public SyncEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public Integer getInCount() {
        return inCount;
    }

    public SyncEntity setInCount(Integer inCount) {
        this.inCount = inCount;
        return this;
    }

    @Override
    public String toString() {
        return "SyncEntity{" +
            "syncId=" + syncId +
            ", shipmentId=" + shipmentId +
            ", asin=" + asin +
            ", inCount=" + inCount +
        "}";
    }
}
