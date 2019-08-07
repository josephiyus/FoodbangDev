package id.foodbang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class OrderData implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("merchant_id")
    @Expose
    private Integer merchantId;
    @SerializedName("customer_id")
    @Expose
    private Integer customerId;
    @SerializedName("package_id")
    @Expose
    private Integer packageId;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("down_payment")
    @Expose
    private Integer downPayment;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("stage")
    @Expose
    private String stage;
    @SerializedName("final_date")
    @Expose
    private Object finalDate;
    @SerializedName("final_location")
    @Expose
    private Object finalLocation;
    @SerializedName("final_portion")
    @Expose
    private Object finalPortion;
    @SerializedName("request_order_date")
    @Expose
    private String requestOrderDate;
    @SerializedName("request_order_location")
    @Expose
    private String requestOrderLocation;
    @SerializedName("request_order_portion")
    @Expose
    private Integer requestOrderPortion;
    @SerializedName("request_order_note")
    @Expose
    private String requestOrderNote;
    @SerializedName("is_booked_decision_done")
    @Expose
    private Boolean isBookedDecisionDone;
    @SerializedName("booked_status")
    @Expose
    private String bookedStatus;
    @SerializedName("booked_due_date")
    @Expose
    private String bookedDueDate;
    @SerializedName("booked_respond_by_type")
    @Expose
    private Object bookedRespondByType;
    @SerializedName("booked_respond_by_id")
    @Expose
    private Object bookedRespondById;
    @SerializedName("booked_respond_at")
    @Expose
    private Object bookedRespondAt;
    @SerializedName("booked_respond_note")
    @Expose
    private Object bookedRespondNote;
    @SerializedName("is_dp_decision_done")
    @Expose
    private Boolean isDpDecisionDone;
    @SerializedName("dp_status")
    @Expose
    private Object dpStatus;
    @SerializedName("dp_due_date")
    @Expose
    private Object dpDueDate;
    @SerializedName("dp_paid")
    @Expose
    private Object dpPaid;
    @SerializedName("dp_respond_by_type")
    @Expose
    private Object dpRespondByType;
    @SerializedName("dp_respond_by_id")
    @Expose
    private Object dpRespondById;
    @SerializedName("dp_respond_at")
    @Expose
    private Object dpRespondAt;
    @SerializedName("dp_respond_note")
    @Expose
    private Object dpRespondNote;
    @SerializedName("dp_confirm_by_type")
    @Expose
    private Object dpConfirmByType;
    @SerializedName("dp_confirm_by_id")
    @Expose
    private Object dpConfirmById;
    @SerializedName("dp_confirm_at")
    @Expose
    private Object dpConfirmAt;
    @SerializedName("dp_confirm_note")
    @Expose
    private Object dpConfirmNote;
    @SerializedName("is_fp_decision_done")
    @Expose
    private Boolean isFpDecisionDone;
    @SerializedName("fp_status")
    @Expose
    private Object fpStatus;
    @SerializedName("fp_due_date")
    @Expose
    private Object fpDueDate;
    @SerializedName("fp_paid")
    @Expose
    private Object fpPaid;
    @SerializedName("fp_respond_by_type")
    @Expose
    private Object fpRespondByType;
    @SerializedName("fp_respond_by_id")
    @Expose
    private Object fpRespondById;
    @SerializedName("fp_respond_at")
    @Expose
    private Object fpRespondAt;
    @SerializedName("fp_respond_note")
    @Expose
    private Object fpRespondNote;
    @SerializedName("fp_confirm_by_type")
    @Expose
    private Object fpConfirmByType;
    @SerializedName("fp_confirm_by_id")
    @Expose
    private Object fpConfirmById;
    @SerializedName("fp_confirm_at")
    @Expose
    private Object fpConfirmAt;
    @SerializedName("fp_confirm_note")
    @Expose
    private Object fpConfirmNote;
    @SerializedName("is_closed")
    @Expose
    private Boolean isClosed;
    @SerializedName("closed_at")
    @Expose
    private Object closedAt;
    @SerializedName("closed_by_type")
    @Expose
    private Object closedByType;
    @SerializedName("closed_by_id")
    @Expose
    private Object closedById;
    @SerializedName("closing_note")
    @Expose
    private Object closingNote;
    @SerializedName("closed_confirm_by_type")
    @Expose
    private Object closedConfirmByType;
    @SerializedName("closed_confirm_by_id")
    @Expose
    private Object closedConfirmById;
    @SerializedName("closed_confirm_at")
    @Expose
    private Object closedConfirmAt;
    @SerializedName("closed_confirm_note")
    @Expose
    private Object closedConfirmNote;
    @SerializedName("closed_status")
    @Expose
    private Object closedStatus;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(Integer downPayment) {
        this.downPayment = downPayment;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public Object getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Object finalDate) {
        this.finalDate = finalDate;
    }

    public Object getFinalLocation() {
        return finalLocation;
    }

    public void setFinalLocation(Object finalLocation) {
        this.finalLocation = finalLocation;
    }

    public Object getFinalPortion() {
        return finalPortion;
    }

    public void setFinalPortion(Object finalPortion) {
        this.finalPortion = finalPortion;
    }

    public String getRequestOrderDate() {
        return requestOrderDate;
    }

    public void setRequestOrderDate(String requestOrderDate) {
        this.requestOrderDate = requestOrderDate;
    }

    public String getRequestOrderLocation() {
        return requestOrderLocation;
    }

    public void setRequestOrderLocation(String requestOrderLocation) {
        this.requestOrderLocation = requestOrderLocation;
    }

    public Integer getRequestOrderPortion() {
        return requestOrderPortion;
    }

    public void setRequestOrderPortion(Integer requestOrderPortion) {
        this.requestOrderPortion = requestOrderPortion;
    }

    public String getRequestOrderNote() {
        return requestOrderNote;
    }

    public void setRequestOrderNote(String requestOrderNote) {
        this.requestOrderNote = requestOrderNote;
    }

    public Boolean getIsBookedDecisionDone() {
        return isBookedDecisionDone;
    }

    public void setIsBookedDecisionDone(Boolean isBookedDecisionDone) {
        this.isBookedDecisionDone = isBookedDecisionDone;
    }

    public String getBookedStatus() {
        return bookedStatus;
    }

    public void setBookedStatus(String bookedStatus) {
        this.bookedStatus = bookedStatus;
    }

    public String getBookedDueDate() {
        return bookedDueDate;
    }

    public void setBookedDueDate(String bookedDueDate) {
        this.bookedDueDate = bookedDueDate;
    }

    public Object getBookedRespondByType() {
        return bookedRespondByType;
    }

    public void setBookedRespondByType(Object bookedRespondByType) {
        this.bookedRespondByType = bookedRespondByType;
    }

    public Object getBookedRespondById() {
        return bookedRespondById;
    }

    public void setBookedRespondById(Object bookedRespondById) {
        this.bookedRespondById = bookedRespondById;
    }

    public Object getBookedRespondAt() {
        return bookedRespondAt;
    }

    public void setBookedRespondAt(Object bookedRespondAt) {
        this.bookedRespondAt = bookedRespondAt;
    }

    public Object getBookedRespondNote() {
        return bookedRespondNote;
    }

    public void setBookedRespondNote(Object bookedRespondNote) {
        this.bookedRespondNote = bookedRespondNote;
    }

    public Boolean getIsDpDecisionDone() {
        return isDpDecisionDone;
    }

    public void setIsDpDecisionDone(Boolean isDpDecisionDone) {
        this.isDpDecisionDone = isDpDecisionDone;
    }

    public Object getDpStatus() {
        return dpStatus;
    }

    public void setDpStatus(Object dpStatus) {
        this.dpStatus = dpStatus;
    }

    public Object getDpDueDate() {
        return dpDueDate;
    }

    public void setDpDueDate(Object dpDueDate) {
        this.dpDueDate = dpDueDate;
    }

    public Object getDpPaid() {
        return dpPaid;
    }

    public void setDpPaid(Object dpPaid) {
        this.dpPaid = dpPaid;
    }

    public Object getDpRespondByType() {
        return dpRespondByType;
    }

    public void setDpRespondByType(Object dpRespondByType) {
        this.dpRespondByType = dpRespondByType;
    }

    public Object getDpRespondById() {
        return dpRespondById;
    }

    public void setDpRespondById(Object dpRespondById) {
        this.dpRespondById = dpRespondById;
    }

    public Object getDpRespondAt() {
        return dpRespondAt;
    }

    public void setDpRespondAt(Object dpRespondAt) {
        this.dpRespondAt = dpRespondAt;
    }

    public Object getDpRespondNote() {
        return dpRespondNote;
    }

    public void setDpRespondNote(Object dpRespondNote) {
        this.dpRespondNote = dpRespondNote;
    }

    public Object getDpConfirmByType() {
        return dpConfirmByType;
    }

    public void setDpConfirmByType(Object dpConfirmByType) {
        this.dpConfirmByType = dpConfirmByType;
    }

    public Object getDpConfirmById() {
        return dpConfirmById;
    }

    public void setDpConfirmById(Object dpConfirmById) {
        this.dpConfirmById = dpConfirmById;
    }

    public Object getDpConfirmAt() {
        return dpConfirmAt;
    }

    public void setDpConfirmAt(Object dpConfirmAt) {
        this.dpConfirmAt = dpConfirmAt;
    }

    public Object getDpConfirmNote() {
        return dpConfirmNote;
    }

    public void setDpConfirmNote(Object dpConfirmNote) {
        this.dpConfirmNote = dpConfirmNote;
    }

    public Boolean getIsFpDecisionDone() {
        return isFpDecisionDone;
    }

    public void setIsFpDecisionDone(Boolean isFpDecisionDone) {
        this.isFpDecisionDone = isFpDecisionDone;
    }

    public Object getFpStatus() {
        return fpStatus;
    }

    public void setFpStatus(Object fpStatus) {
        this.fpStatus = fpStatus;
    }

    public Object getFpDueDate() {
        return fpDueDate;
    }

    public void setFpDueDate(Object fpDueDate) {
        this.fpDueDate = fpDueDate;
    }

    public Object getFpPaid() {
        return fpPaid;
    }

    public void setFpPaid(Object fpPaid) {
        this.fpPaid = fpPaid;
    }

    public Object getFpRespondByType() {
        return fpRespondByType;
    }

    public void setFpRespondByType(Object fpRespondByType) {
        this.fpRespondByType = fpRespondByType;
    }

    public Object getFpRespondById() {
        return fpRespondById;
    }

    public void setFpRespondById(Object fpRespondById) {
        this.fpRespondById = fpRespondById;
    }

    public Object getFpRespondAt() {
        return fpRespondAt;
    }

    public void setFpRespondAt(Object fpRespondAt) {
        this.fpRespondAt = fpRespondAt;
    }

    public Object getFpRespondNote() {
        return fpRespondNote;
    }

    public void setFpRespondNote(Object fpRespondNote) {
        this.fpRespondNote = fpRespondNote;
    }

    public Object getFpConfirmByType() {
        return fpConfirmByType;
    }

    public void setFpConfirmByType(Object fpConfirmByType) {
        this.fpConfirmByType = fpConfirmByType;
    }

    public Object getFpConfirmById() {
        return fpConfirmById;
    }

    public void setFpConfirmById(Object fpConfirmById) {
        this.fpConfirmById = fpConfirmById;
    }

    public Object getFpConfirmAt() {
        return fpConfirmAt;
    }

    public void setFpConfirmAt(Object fpConfirmAt) {
        this.fpConfirmAt = fpConfirmAt;
    }

    public Object getFpConfirmNote() {
        return fpConfirmNote;
    }

    public void setFpConfirmNote(Object fpConfirmNote) {
        this.fpConfirmNote = fpConfirmNote;
    }

    public Boolean getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Boolean isClosed) {
        this.isClosed = isClosed;
    }

    public Object getClosedAt() {
        return closedAt;
    }

    public void setClosedAt(Object closedAt) {
        this.closedAt = closedAt;
    }

    public Object getClosedByType() {
        return closedByType;
    }

    public void setClosedByType(Object closedByType) {
        this.closedByType = closedByType;
    }

    public Object getClosedById() {
        return closedById;
    }

    public void setClosedById(Object closedById) {
        this.closedById = closedById;
    }

    public Object getClosingNote() {
        return closingNote;
    }

    public void setClosingNote(Object closingNote) {
        this.closingNote = closingNote;
    }

    public Object getClosedConfirmByType() {
        return closedConfirmByType;
    }

    public void setClosedConfirmByType(Object closedConfirmByType) {
        this.closedConfirmByType = closedConfirmByType;
    }

    public Object getClosedConfirmById() {
        return closedConfirmById;
    }

    public void setClosedConfirmById(Object closedConfirmById) {
        this.closedConfirmById = closedConfirmById;
    }

    public Object getClosedConfirmAt() {
        return closedConfirmAt;
    }

    public void setClosedConfirmAt(Object closedConfirmAt) {
        this.closedConfirmAt = closedConfirmAt;
    }

    public Object getClosedConfirmNote() {
        return closedConfirmNote;
    }

    public void setClosedConfirmNote(Object closedConfirmNote) {
        this.closedConfirmNote = closedConfirmNote;
    }

    public Object getClosedStatus() {
        return closedStatus;
    }

    public void setClosedStatus(Object closedStatus) {
        this.closedStatus = closedStatus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
