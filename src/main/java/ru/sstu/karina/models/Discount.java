package ru.sstu.karina.models;

import javax.persistence.*;

@Entity
@Table(name = "discounts")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Item item;

    public Discount(Long id, Item item) {
        this.id = id;
        this.item = item;
    }

    public Discount() {
    }

    public Long getId() {
        return this.id;
    }

    public Item getItem() {
        return this.item;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Discount)) return false;
        final Discount other = (Discount) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$item = this.getItem();
        final Object other$item = other.getItem();
        if (this$item == null ? other$item != null : !this$item.equals(other$item)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Discount;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $item = this.getItem();
        result = result * PRIME + ($item == null ? 43 : $item.hashCode());
        return result;
    }

    public String toString() {
        return "Discount(id=" + this.getId() + ", item=" + this.getItem() + ")";
    }
}
