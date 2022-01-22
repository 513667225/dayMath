package com.math.modules.listing.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2021-12-27
 */
@TableName("listing")
public class ListingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "list_id", type = IdType.AUTO)
    private Integer listId;

    private String listingId;

    private String sellerSku;

    private String fnsku;

    private String itemName;

    private String localSku;

    private String localName;

    private BigDecimal price;

    private String quantity;

    private String asin;

    private String parentAsin;

    private String smallImageUrl;

    private String status;

    private String afnFulfillableQuantity;

    private String reservedFcTransfers;

    private String reservedFcProcessing;

    private String reservedCustomerorders;

    private String afnInboundShippedQuantity;

    private String afnUnsellableQuantity;

    private String afnInboundWorkingQuantity;

    private String afnInboundReceivingQuantity;

    private String currencyCode;

    private BigDecimal landedPrice;

    private BigDecimal listingPrice;

    private String openDate;

    private String listingUpdateDate;

    private String sellerRank;

    private String sellerCategory;

    private Integer reviewNum;

    private String lastStar;

    private String fulfillmentChannelType;

    private String principalInfo;

    private Integer principalUid;

    private String principalName;

    private String shipping;

    private String points;


    private Integer sellerId;

    private  Integer isDelete;


    private  String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    //    其

    public  Integer getIsDelete(){
        return isDelete;
    }

    public  void setIsDelete(Integer isDelete){
        this.isDelete = isDelete;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Integer getListId() {
        return listId;
    }

    public ListingEntity setListId(Integer listId) {
        this.listId = listId;
        return this;
    }
    public String getListingId() {
        return listingId;
    }

    public ListingEntity setListingId(String listingId) {
        this.listingId = listingId;
        return this;
    }
    public String getSellerSku() {
        return sellerSku;
    }

    public ListingEntity setSellerSku(String sellerSku) {
        this.sellerSku = sellerSku;
        return this;
    }
    public String getFnsku() {
        return fnsku;
    }

    public ListingEntity setFnsku(String fnsku) {
        this.fnsku = fnsku;
        return this;
    }
    public String getItemName() {
        return itemName;
    }

    public ListingEntity setItemName(String itemName) {
        this.itemName = itemName;
        return this;
    }
    public String getLocalSku() {
        return localSku;
    }

    public ListingEntity setLocalSku(String localSku) {
        this.localSku = localSku;
        return this;
    }
    public String getLocalName() {
        return localName;
    }

    public ListingEntity setLocalName(String localName) {
        this.localName = localName;
        return this;
    }
    public BigDecimal getPrice() {
        return price;
    }

    public ListingEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
    public String getQuantity() {
        return quantity;
    }

    public ListingEntity setQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }
    public String getAsin() {
        return asin;
    }

    public ListingEntity setAsin(String asin) {
        this.asin = asin;
        return this;
    }
    public String getParentAsin() {
        return parentAsin;
    }

    public ListingEntity setParentAsin(String parentAsin) {
        this.parentAsin = parentAsin;
        return this;
    }
    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public ListingEntity setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
        return this;
    }
    public String getStatus() {
        return status;
    }

    public ListingEntity setStatus(String status) {
        this.status = status;
        return this;
    }
    public String getAfnFulfillableQuantity() {
        return afnFulfillableQuantity;
    }

    public ListingEntity setAfnFulfillableQuantity(String afnFulfillableQuantity) {
        this.afnFulfillableQuantity = afnFulfillableQuantity;
        return this;
    }
    public String getReservedFcTransfers() {
        return reservedFcTransfers;
    }

    public ListingEntity setReservedFcTransfers(String reservedFcTransfers) {
        this.reservedFcTransfers = reservedFcTransfers;
        return this;
    }
    public String getReservedFcProcessing() {
        return reservedFcProcessing;
    }

    public ListingEntity setReservedFcProcessing(String reservedFcProcessing) {
        this.reservedFcProcessing = reservedFcProcessing;
        return this;
    }
    public String getReservedCustomerorders() {
        return reservedCustomerorders;
    }

    public ListingEntity setReservedCustomerorders(String reservedCustomerorders) {
        this.reservedCustomerorders = reservedCustomerorders;
        return this;
    }
    public String getAfnInboundShippedQuantity() {
        return afnInboundShippedQuantity;
    }

    public ListingEntity setAfnInboundShippedQuantity(String afnInboundShippedQuantity) {
        this.afnInboundShippedQuantity = afnInboundShippedQuantity;
        return this;
    }
    public String getAfnUnsellableQuantity() {
        return afnUnsellableQuantity;
    }

    public ListingEntity setAfnUnsellableQuantity(String afnUnsellableQuantity) {
        this.afnUnsellableQuantity = afnUnsellableQuantity;
        return this;
    }
    public String getAfnInboundWorkingQuantity() {
        return afnInboundWorkingQuantity;
    }

    public ListingEntity setAfnInboundWorkingQuantity(String afnInboundWorkingQuantity) {
        this.afnInboundWorkingQuantity = afnInboundWorkingQuantity;
        return this;
    }
    public String getAfnInboundReceivingQuantity() {
        return afnInboundReceivingQuantity;
    }

    public ListingEntity setAfnInboundReceivingQuantity(String afnInboundReceivingQuantity) {
        this.afnInboundReceivingQuantity = afnInboundReceivingQuantity;
        return this;
    }
    public String getCurrencyCode() {
        return currencyCode;
    }

    public ListingEntity setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
        return this;
    }
    public BigDecimal getLandedPrice() {
        return landedPrice;
    }

    public ListingEntity setLandedPrice(BigDecimal landedPrice) {
        this.landedPrice = landedPrice;
        return this;
    }
    public BigDecimal getListingPrice() {
        return listingPrice;
    }

    public ListingEntity setListingPrice(BigDecimal listingPrice) {
        this.listingPrice = listingPrice;
        return this;
    }
    public String getOpenDate() {
        return openDate;
    }

    public ListingEntity setOpenDate(String openDate) {
        this.openDate = openDate;
        return this;
    }
    public String getListingUpdateDate() {
        return listingUpdateDate;
    }

    public ListingEntity setListingUpdateDate(String listingUpdateDate) {
        this.listingUpdateDate = listingUpdateDate;
        return this;
    }
    public String getSellerRank() {
        return sellerRank;
    }

    public ListingEntity setSellerRank(String sellerRank) {
        this.sellerRank = sellerRank;
        return this;
    }
    public String getSellerCategory() {
        return sellerCategory;
    }

    public ListingEntity setSellerCategory(String sellerCategory) {
        this.sellerCategory = sellerCategory;
        return this;
    }
    public Integer getReviewNum() {
        return reviewNum;
    }

    public ListingEntity setReviewNum(Integer reviewNum) {
        this.reviewNum = reviewNum;
        return this;
    }
    public String getLastStar() {
        return lastStar;
    }

    public ListingEntity setLastStar(String lastStar) {
        this.lastStar = lastStar;
        return this;
    }
    public String getFulfillmentChannelType() {
        return fulfillmentChannelType;
    }

    public ListingEntity setFulfillmentChannelType(String fulfillmentChannelType) {
        this.fulfillmentChannelType = fulfillmentChannelType;
        return this;
    }
    public String getPrincipalInfo() {
        return principalInfo;
    }

    public ListingEntity setPrincipalInfo(String principalInfo) {
        this.principalInfo = principalInfo;
        return this;
    }
    public Integer getPrincipalUid() {
        return principalUid;
    }

    public ListingEntity setPrincipalUid(Integer principalUid) {
        this.principalUid = principalUid;
        return this;
    }
    public String getPrincipalName() {
        return principalName;
    }

    public ListingEntity setPrincipalName(String principalName) {
        this.principalName = principalName;
        return this;
    }
    public String getShipping() {
        return shipping;
    }

    public ListingEntity setShipping(String shipping) {
        this.shipping = shipping;
        return this;
    }
    public String getPoints() {
        return points;
    }

    public ListingEntity setPoints(String points) {
        this.points = points;
        return this;
    }

    @Override
    public String toString() {
        return "ListingEntity{" +
            "listId=" + listId +
            ", listingId=" + listingId +
            ", sellerSku=" + sellerSku +
            ", fnsku=" + fnsku +
            ", itemName=" + itemName +
            ", localSku=" + localSku +
            ", localName=" + localName +
            ", price=" + price +
            ", quantity=" + quantity +
            ", asin=" + asin +
            ", parentAsin=" + parentAsin +
            ", smallImageUrl=" + smallImageUrl +
            ", status=" + status +
            ", afnFulfillableQuantity=" + afnFulfillableQuantity +
            ", reservedFcTransfers=" + reservedFcTransfers +
            ", reservedFcProcessing=" + reservedFcProcessing +
            ", reservedCustomerorders=" + reservedCustomerorders +
            ", afnInboundShippedQuantity=" + afnInboundShippedQuantity +
            ", afnUnsellableQuantity=" + afnUnsellableQuantity +
            ", afnInboundWorkingQuantity=" + afnInboundWorkingQuantity +
            ", afnInboundReceivingQuantity=" + afnInboundReceivingQuantity +
            ", currencyCode=" + currencyCode +
            ", landedPrice=" + landedPrice +
            ", listingPrice=" + listingPrice +
            ", openDate=" + openDate +
            ", listingUpdateDate=" + listingUpdateDate +
            ", sellerRank=" + sellerRank +
            ", sellerCategory=" + sellerCategory +
            ", reviewNum=" + reviewNum +
            ", lastStar=" + lastStar +
            ", fulfillmentChannelType=" + fulfillmentChannelType +
            ", principalInfo=" + principalInfo +
            ", principalUid=" + principalUid +
            ", principalName=" + principalName +
            ", shipping=" + shipping +
            ", points=" + points +
        "}";
    }
}
