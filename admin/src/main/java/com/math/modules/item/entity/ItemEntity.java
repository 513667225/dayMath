package com.math.modules.item.entity;

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
 * @since 2021-12-23
 */
@TableName("item")
public class ItemEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id", type = IdType.AUTO)
    private Integer itemId;

    private String msku;

    private String fnsku;

    private Integer initQuantityShipped;

    private Integer quantityShippedLocal;

    private Integer quantityReceived;

    private Integer fbaProductId;

    private  Integer inCount;

    private  Integer sellerId;

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getInCount() {
        return inCount;
    }

    public void setInCount(Integer inCount) {
        this.inCount = inCount;
    }

    public Integer getItemId() {
        return itemId;
    }

    public ItemEntity setItemId(Integer itemId) {
        this.itemId = itemId;
        return this;
    }
    public String getMsku() {
        return msku;
    }

    public ItemEntity setMsku(String msku) {
        this.msku = msku;
        return this;
    }
    public String getFnsku() {
        return fnsku;
    }

    public ItemEntity setFnsku(String fnsku) {
        this.fnsku = fnsku;
        return this;
    }
    public Integer getInitQuantityShipped() {
        return initQuantityShipped;
    }

    public ItemEntity setInitQuantityShipped(Integer initQuantityShipped) {
        this.initQuantityShipped = initQuantityShipped;
        return this;
    }
    public Integer getQuantityShippedLocal() {
        return quantityShippedLocal;
    }

    public ItemEntity setQuantityShippedLocal(Integer quantityShippedLocal) {
        this.quantityShippedLocal = quantityShippedLocal;
        return this;
    }
    public Integer getQuantityReceived() {
        return quantityReceived;
    }

    public ItemEntity setQuantityReceived(Integer quantityReceived) {
        this.quantityReceived = quantityReceived;
        return this;
    }


    public Integer getFbaProductId() {
        return fbaProductId;
    }

    public void setFbaProductId(Integer fbaProductId) {
        this.fbaProductId = fbaProductId;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
            "itemId=" + itemId +
            ", msku=" + msku +
            ", fnsku=" + fnsku +
            ", initQuantityShipped=" + initQuantityShipped +
            ", quantityShippedLocal=" + quantityShippedLocal +
            ", quantityReceived=" + quantityReceived +
        "}";
    }
}
