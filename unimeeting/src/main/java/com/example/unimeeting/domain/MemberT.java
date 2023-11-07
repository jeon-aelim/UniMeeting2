package com.example.unimeeting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter
@Setter
@NoArgsConstructor
@Table(name="member")
public class MemberT {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int idx;
  private int meeting_idx;
  private int user_idx;
  private int accepted;
}
