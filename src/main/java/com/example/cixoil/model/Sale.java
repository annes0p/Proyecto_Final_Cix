package com.example.cixoil.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.example.cixoil.enums.PaymentMethod;
import com.example.cixoil.enums.TransactionStatus;
import com.example.cixoil.enums.VoucherType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "sale")
public class Sale {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_sale")
  private Long id;

  @JoinColumn(name = "id_user")
  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @JoinColumn(name = "id_client")
  @ManyToOne(fetch = FetchType.LAZY)
  private Client client;

  @CreationTimestamp
  @Column(name = "sale_date", updatable = false)
  private LocalDateTime saleDate;

  @Column(name = "subtotal")
  private BigDecimal subtotal;

  @Column(name = "tax_amount")
  private BigDecimal taxAmount;

  @Column(name = "total")
  private BigDecimal total;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_method")
  private PaymentMethod paymentMethod;

  @Enumerated(EnumType.STRING)
  @Column(name = "voucher_type")
  private VoucherType voucherType;

  @Column(name = "series")
  private String series;

  @Column(name = "number")
  private String number;

  @Enumerated(EnumType.STRING)
  @Column(name = "transaction_status")
  private TransactionStatus transactionStatus;

  @Builder.Default
  @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<SaleDetail> details = new ArrayList<>();

  public void addDetail(SaleDetail detail) {
      details.add(detail);
      detail.setSale(this);
  }
}
