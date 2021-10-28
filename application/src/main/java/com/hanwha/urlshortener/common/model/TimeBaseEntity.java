package com.hanwha.urlshortener.common.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
public class TimeBaseEntity {

  @CreationTimestamp
  @Column(updatable = false)
  private LocalDateTime createDateTime;

  @Setter
  @UpdateTimestamp
  private LocalDateTime updateDateTime;
}



