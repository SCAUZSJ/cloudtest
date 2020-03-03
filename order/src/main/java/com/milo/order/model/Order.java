package com.milo.order.model;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

  private Long id;
  private List<Long> bookIds;
  private LocalDateTime createDate;

}
