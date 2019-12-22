package ru.sstu.karina.models;

import javax.persistence.*;

@Entity
@Table(name = "rates")
public class Rate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long itemId;
    private Long userId;

    private Integer rate;

    public Rate(Long id, Long itemId, Long userId, Integer rate) {
        this.id = id;
        this.itemId = itemId;
        this.userId = userId;
        this.rate = rate;
    }

    public Rate() {
    }

    public Long getId() {
        return this.id;
    }

    public Long getItemId() {
        return this.itemId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public Integer getRate() {
        return this.rate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Rate)) return false;
        final Rate other = (Rate) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$itemId = this.getItemId();
        final Object other$itemId = other.getItemId();
        if (this$itemId == null ? other$itemId != null : !this$itemId.equals(other$itemId)) return false;
        final Object this$userId = this.getUserId();
        final Object other$userId = other.getUserId();
        if (this$userId == null ? other$userId != null : !this$userId.equals(other$userId)) return false;
        final Object this$rate = this.getRate();
        final Object other$rate = other.getRate();
        if (this$rate == null ? other$rate != null : !this$rate.equals(other$rate)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Rate;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $itemId = this.getItemId();
        result = result * PRIME + ($itemId == null ? 43 : $itemId.hashCode());
        final Object $userId = this.getUserId();
        result = result * PRIME + ($userId == null ? 43 : $userId.hashCode());
        final Object $rate = this.getRate();
        result = result * PRIME + ($rate == null ? 43 : $rate.hashCode());
        return result;
    }

    public String toString() {
        return "Rate(id=" + this.getId() + ", itemId=" + this.getItemId() + ", userId=" + this.getUserId() + ", rate=" + this.getRate() + ")";
    }
}
