package com.MSBootCoinBootCamp.domain.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document
@ToString
public class LinkedAccount {
    private String accountId;
    private LocalDateTime addedDate;
    private Boolean isMainAccount;
}
